package com.vnx.patsystems.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.market.trading.bean.delegate.QuoteBeanDelegate;
import com.market.trading.model.Quote;

import patsystems.api.delphi.*;

public class Feeder {
	
	private ArrayList<SubscribedItem> contractMap;
	private ConcurrentHashMap<String, Integer> indexMap;
	private String[] exchanges, commodites,contracts, bids, offers, lasts, tickVols;
	private String bid, offer, last, tickVol;
	private PriceDetail[][] bidDOMs, offerDOMs;
    private boolean isContractCounted;
    
    public ClientAPI clientAPI;
    private QuoteBeanDelegate quoteBean = null;
    
    public Feeder(QuoteBeanDelegate quoteBean) {
    	this.quoteBean = quoteBean;
    	contractMap = new ArrayList<SubscribedItem>();
    	indexMap =  new ConcurrentHashMap<String, Integer>(); 
    	isContractCounted = false;
    	clientAPI = new ClientAPI(this);
    }
    
    public void countContracts() {
    	if(isContractCounted) {
    		return;
    	}
        
    	Enumeration<String> keys = clientAPI.exchangeMap.keys();
        while(keys.hasMoreElements()) {
        	populateCommodity(keys.nextElement());
        }
        
        clientAPI.logFile.addMessage("Total: " + contractMap.size() + " contracts");
    	String commodity, contract, exchange, symbols[];
    	
    	commodites = new String[contractMap.size()];
    	contracts = new String[contractMap.size()];
    	exchanges = new String[contractMap.size()];
    	bids = new String[contractMap.size()];
    	offers = new String[contractMap.size()];
    	lasts = new String[contractMap.size()];
    	tickVols =  new String[contractMap.size()];
    	bidDOMs = new PriceDetail[contractMap.size()][];
    	offerDOMs = new PriceDetail[contractMap.size()][];
    	symbols = new String[contractMap.size()];
    	
    	//HUAN
    	List<Quote> lstQuotes = new LinkedList<Quote>();
    	Quote quote = null;
    	
    	for(int index = 0; index < contractMap.size(); index++) {
    		try {
    			SubscribedItem item = contractMap.get(index);
    			commodity = item.commodity;
    			contract = item.contract;    	
    			exchange = item.exchange;
    			
    			symbols[index] = exchange + "-" + commodity + "-" + contract.replace(" ", "");
    			
    			indexMap.put(item.exchange + " / " + commodity + " / " + contract, index);		
    			exchanges[index]= exchange;
    			commodites[index]= commodity;
    			contracts[index] = contract;
    			bids[index] = "0";
    	    	offers[index] = "0";
    	    	lasts[index] = "0";
    	    	tickVols[index] = "0";
    	    	bidDOMs[index] = null;
    	    	offerDOMs[index] = null;
    	    	
    	    	//HUAN
    	    	quote = new Quote();
    	    	quote.setTitle(symbols[index]);
    	    	quote.setBid(bids[index]);
    	    	quote.setAsk(offers[index]);
    	    	quote.setLast(lasts[index]);
    	    	quote.setTickVol(tickVols[index]);
    	    	lstQuotes.add(quote);
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
        }
    	
    	//Initialize quote tables
    	this.quoteBean.fillQuotes(lstQuotes);
    	
    	Quote quoteTables[] = 
    		new Quote[contractMap.size()];
    	
    	for(int index = 0; index < contractMap.size(); index++) {
    		try {
    			quoteTables[index] = new Quote();
    			quoteTables[index].setTitle(symbols[index]);
    			SubscribedItem item = contractMap.get(index);
    			SubcribeThread thread = new SubcribeThread(this, item);
    			thread.start();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}

    	isContractCounted = true;
    }

    private void populateCommodity(String exchange) {
        if (!exchange.isEmpty()) {
            Enumeration<String> keys = clientAPI.commodityMap.keys();
            while (keys.hasMoreElements()) {
                Commodity commodity = clientAPI.commodityMap.get(keys.nextElement());
                if (commodity.exchange.name.equalsIgnoreCase(exchange)) {
                	populateContract(exchange,commodity.name);
                }
            }
        }
    }
    
    private void populateContract(String exchange, String commodity) {
        if (exchange != "" && commodity != "") {
            Enumeration<String> keys = clientAPI.contractMap.keys();
            while (keys.hasMoreElements()) {
                Contract contract = clientAPI.contractMap.get(keys.nextElement());
                if(
                	contract.exchange.name.equalsIgnoreCase(exchange) && 
                	contract.commodity.name.equalsIgnoreCase(commodity)
                ) {
                	contractMap.add(new SubscribedItem(exchange, commodity, contract.name));
                }                
            }
        }
    }
  
    public void priceUpdate(
    	PriceComposite prices, String exchange, String commodity, String contract, 
    	String itemKey
    ) {
    	int index = indexMap.get(itemKey);
    	
    	exchanges[index] = exchange;
    	commodites[index]= commodity;
    	contracts[index]= contract;
    	bids[index]=  prices.bid.price;
    	offers[index]=  prices.offer.price;
    	lasts[index]=  prices.last[0].price;
    	tickVols[index]=  Integer.toString(prices.offer.volume);
    	bidDOMs[index] = prices.bidDOM;
    	offerDOMs[index] = prices.offerDOM;
    	
    	if(!isContractCounted) {
    		return;
    	}
    	
    	//Automatically update quote tables
    	Quote quoteTables[] = 
        	new Quote[contractMap.size()]; 
        
//    	for(int i = 0; i < contractMap.size(); i++) {
//    		exchange = exchanges[i];
//        	commodity = commodites[i];
//            contract = contracts[i];
//            
//            String symbol = exchange + "-" + commodity + "-" + contract.replace(" ", "");
//            
//            bid = bids[i];
//            offer = offers[i];
//            last = lasts[i];
//            tickVol  = tickVols[i];
//            
//            quoteTables[i] = new Quote();
//            quoteTables[i].setTitle(symbol);
//            quoteTables[i].setBid(bid);
//            quoteTables[i].setAsk(offer);
//            quoteTables[i].setLast(last);
//            quoteTables[i].setTickVol(tickVol);
//            
//            System.out.println("---------------------------------------------------------------");
//            System.out.println(
//            	itemKey + " " + index + " | " + symbol + " | " + bid + " | " + offer + " | " + last + " | " + tickVol
//            );
    		String symbol = exchanges[index] + "-" + commodites[index] + "-" + contracts[index].replace(" ", "");
    		
            System.out.println("---------------------------------------------------------------");
            System.out.println(
            	itemKey + " " + index + " | " + symbol + " | " + bids[index] + " | " + offers[index] 
            	 + " | " + lasts[index] + " | " + tickVols[index]);
            Quote q = new Quote();
            q.setTitle(symbol);
            q.setBid(bids[index]);
            q.setAsk(offers[index]);
            q.setLast(lasts[index]);
            q.setTickVol(tickVols[index]);
            
            quoteBean.updateRow(index, q);
    }

    public ArrayList<SubscribedItem> getContractMap() {
		return contractMap;
	}

	public void setContractMap(ArrayList<SubscribedItem> contractMap) {
		this.contractMap = contractMap;
	}

	public class SubscribedItem {
        protected String exchange;
        protected String commodity;
        protected String contract;

        public SubscribedItem(String exchange, String commodity, String contract) {
            this.exchange = exchange;
            this.commodity = commodity;
            this.contract = contract;
        }
    }
    
    public class SubcribeThread extends Thread {   
    	SubscribedItem item;
    	Feeder externalFeed;
    	
    	public SubcribeThread(Feeder externalFeed, SubscribedItem item) {
    		this.item = item;
    		this.externalFeed = externalFeed;
    	}
    	
        public void run() {
        	try {
        		subscribe(item.exchange, item.commodity,item.contract);
        	} catch(Exception e) {
        		clientAPI.logFile.addMessage(e.toString());
        	}
        }

        private void subscribe(String exchange, String commodity, String contract) throws Exception {
            if (exchange == "" | commodity == "" | contract == "") {
                clientAPI.logFile.addMessage(
                	"Cannot subscribe to " + exchange + " / " + commodity + " / " + contract
                );
            } else {
            	if (!clientAPI.subscriberMap.containsKey(
            			exchange + " / " + commodity + " / " + contract)
            		) {
                    clientAPI.doSubscribe(exchange, commodity, contract);
                    clientAPI.subscriberMap.put(
                    	exchange + " / " + commodity + " / " + contract, externalFeed
                    );
                    clientAPI.logFile.addMessage(
                    	" subscribe : " + exchange + " / " + commodity + " / " + contract
                    );
                } else {
                	clientAPI.logFile.addMessage(
                		"Func subscribe: Cannot resubscribe to a contract that is already " +
                		"subscribed to in another window"
                	);
                	System.out.println(
                		"Func subscribe: Cannot resubscribe to a contract that is already " +
                		"subscribed to in another window"
                	);
                }
            }
        }
    }
}
