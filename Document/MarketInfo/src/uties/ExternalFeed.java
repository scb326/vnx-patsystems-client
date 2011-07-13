package uties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import patsystems.api.delphi.*;
import form.*;

public class ExternalFeed {
	
	protected static final Timer dispatcher = new Timer();
	private final String newline = System.getProperty("line.separator");
	private final String directoryPath = "D:\\MetaTrader 5 Platform\\History\\Datafeed";
	private final String tickFilePath = "D:\\MetaTrader 5 Platform\\History\\Datafeed\\_ticks.txt";
	private final String bookFilePath = "D:\\MetaTrader 5 Platform\\History\\Datafeed\\_books.txt";
	
	protected ConfigInfo configInfo;
	protected ArrayList<SubscribedItem> contractMap;
	protected SubscribedItem subscribedItem;
	protected ConcurrentHashMap<String, Integer> indexMap;
	protected String[] exchanges, commodites,contracts, bids, offers, lasts, tickVols;
	private String exchange, commodity, contract, bid, offer, last, tickVol;
	protected PriceDetail[][] bidDOMs, offerDOMs;
	public ClientAPI clientAPI;
	public frmMain mainForm;
    public boolean IsInitiateData, IsRun;
    
    public ExternalFeed(frmMain main, ConfigInfo info) {
    	contractMap = new ArrayList<SubscribedItem>();
    	indexMap =  new ConcurrentHashMap<String, Integer>(); 
    	IsInitiateData = false;
    	mainForm = main;
    	configInfo = info;    	
    }

    public void start() {
    	IsRun = true;
    	clientAPI = new ClientAPI(this, configInfo);
    }
    
    public void stop() {
    	IsRun = false;
    	clientAPI.stop();
    }
    
    public void readyGetData() {
    	if(IsInitiateData) {
    		return;
    	}
    	
    	File file = new File(directoryPath);
        
        try {
            if(!file.exists()) {
        		file.mkdirs();
        		file = new File(tickFilePath);
        		
        		if(!file.exists()) {
        			file.createNewFile();
        		}
        		
        		file = new File(bookFilePath);
        		if(!file.exists()) {
        			file.createNewFile();
        		}
            } else {
            	file = new File(tickFilePath);
        		if(!file.exists()) {
        			file.createNewFile();
        		}
        		
        		file = new File(bookFilePath);
        		if(!file.exists()) {
        			file.createNewFile();
        		}
            }
    	}
        catch (IOException e) {
        	mainForm.AddLog("Cannot create files: " + e.getMessage());
			e.printStackTrace();
		}
        
    	Enumeration<String> keys = clientAPI.exchangeMap.keys();
        while(keys.hasMoreElements()) {
        	populateCommodity(keys.nextElement());
        }
        
        clientAPI.logFile.addMessage("Total: " + contractMap.size() + " contracts");
    	String commodity, contract, exchange;
    	
    	commodites = new String[contractMap.size()];
    	contracts = new String[contractMap.size()];
    	exchanges = new String[contractMap.size()];
    	bids = new String[contractMap.size()];
    	offers = new String[contractMap.size()];
    	lasts = new String[contractMap.size()];
    	tickVols =  new String[contractMap.size()];
    	bidDOMs = new PriceDetail[contractMap.size()][];
    	offerDOMs = new PriceDetail[contractMap.size()][];
    	
    	for(int index = 0; index < contractMap.size(); index++) {
    		try {
    			SubscribedItem item = contractMap.get(index);
    			commodity = item.commodity;
    			contract = item.contract;    	
    			exchange = item.exchange;
    			
    			indexMap.put(item.exchange + " / " + commodity + " / " + contract, index);		
    			exchanges[index]= exchange;
    			commodites[index]= commodity;
    			contracts[index] = contract;
    			bids[index] = "";
    	    	offers[index] = "";
    	    	lasts[index] = "";
    	    	tickVols[index] = "";
    	    	bidDOMs[index] = null;
    	    	offerDOMs[index] = null;
    		}
    		catch (Exception e) {
    			mainForm.AddLog("Func readyGetData: " + e.getMessage());
    			e.printStackTrace();
    		}
        }
    	
    	for(int index = 0; index < contractMap.size(); index++) {
    		try {
    			SubscribedItem item = contractMap.get(index);
    			SubcribeThread thread = new SubcribeThread(this,item);
    			thread.start();
    		} catch (Exception e) {
    			mainForm.AddLog("Func readyGetData (run thread): " + e.getMessage());
    			e.printStackTrace();
    		}
    	}
    	
    	scheduleGenerator(0, configInfo.TimeRefresh);
    	IsInitiateData = true;
    }

    private void scheduleGenerator(final Integer number, long waitTime) {
        dispatcher.schedule(new TimerTask() {
            public void run() {
                synchronized (number) {       
                	if(!IsRun) {
                		return;
                	}

                    StringBuilder builder = new StringBuilder("");
                    StringBuilder builder_2 = new StringBuilder("");
                    StringBuilder builder_3 = new StringBuilder("");
                    
                	for(int index = 0; index < contractMap.size(); index++) {
                		exchange = exchanges[index];
                    	commodity = commodites[index];
                        contract = contracts[index];
                        
                        String symbol = exchange + "-" + commodity + "-" + contract.replace(" ", "") ;
                        
                        bid = bids[index];
                        offer = offers[index];
                        last = lasts[index];
                        tickVol  = tickVols[index];
                        
                        builder.append(symbol + ",TextFeeder,");
                        
                        if(bid != null && !bid.isEmpty()) {
                        	builder.append(bid);
                        } else {
                        	builder.append("0");
                        }

                        builder.append(",");

                        if(offer != null && !offer.isEmpty()) {
                        	builder.append(offer);
                        } else {
                        	builder.append("0");
                        }
                        
                        builder.append(",");
                        
                        if(last != null && !last.isEmpty()) {
                        	builder.append(last);
                        } else {
                        	builder.append("0");
                        }
                        
                        builder.append(",");
                        
                        if(tickVol != null && !tickVol.isEmpty()) {
                        	builder.append(tickVol);
                        } else {
                        	builder.append("1");
                        }
                        
                        builder.append(newline);
                        
                        boolean hasValue = false;
                        
                        if(bidDOMs[index] != null && offerDOMs[index] != null) {
	                		for(int i = 0; i < 20; i++) {
	                			if(
                					bidDOMs[index][i] == null || 
                					offerDOMs[index][i] == null ||
	                				bidDOMs[index][i].price.isEmpty() || 
	                				offerDOMs[index][i].price.isEmpty() ||
	                				bidDOMs[index][i].price.equalsIgnoreCase("0") || 
	                				offerDOMs[index][i].price.equalsIgnoreCase("0") ||
	                				(bidDOMs[index][i].volume == 0 ) || 
	                				(offerDOMs[index][i].volume == 0)
	                			) {
	                				continue;
	                			}

	                			if(!hasValue) {
	                				hasValue = true;
	                				builder_2.append("Symbol=" + symbol + newline);
	                			}
	                			
	                        	builder_2.append(
	                        		"Sell," + offerDOMs[index][i].price + "," + 
	                        		offerDOMs[index][i].volume + newline
	                        	);
	                        	
	                        	builder_3.append(
	                        		"Buy," + bidDOMs[index][i].price + "," + 
	                        		bidDOMs[index][i].volume + newline
	                        	);
	                        }
	                		
	                		if(hasValue) {
	                			builder_3.append("End" + newline);
	                		}
	                		
	                		builder_2.append(builder_3.toString());
                        }
                    }                     

                	BufferedWriter writer = null;
                	BufferedWriter writer_2 = null;
                	
                	try {
                		writer = new BufferedWriter(new OutputStreamWriter(
                			new FileOutputStream(tickFilePath))
                		);
                        writer.write(builder.toString());
                        writer.flush();
                		writer.close();
                		
                        writer_2 = new BufferedWriter(new OutputStreamWriter(
                        	new FileOutputStream(bookFilePath))
                        );
                		writer_2.write(builder_2.toString());
               			writer_2.flush();
                		writer_2.close();
                	} catch(IOException e) {
                		mainForm.AddLog("Write file error: " + e.getMessage());
                		e.printStackTrace();
                	}
                }
                
                if(IsRun) scheduleGenerator(0, configInfo.TimeRefresh);
            }
        }, waitTime);
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
                	mainForm.AddLog("Load " + exchange + "/" + commodity + "/" + contract.name);
                }                
            }
        }
    }
  
    public void priceUpdate(
    	PriceComposite prices, String exchange, String commodity, String contract, String itemKey
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
    }

    private class SubscribedItem {
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
    	ExternalFeed externalFeed;
    	
    	public SubcribeThread(ExternalFeed externalFeed, SubscribedItem item) {
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
                    	Uties.NowGetTime() + 
                    	" subscribe : " + exchange + " / " + commodity + " / " + contract
                    );
                    mainForm.AddLog("Subscribed " + exchange + "/" + commodity + "/" + contract);
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
