package com.market.trading.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import com.market.trading.bean.delegate.QuoteBeanDelegate;
import com.market.trading.model.FillInfo;
import com.market.trading.model.Quote;
import com.market.trading.model.StatusInfo;
import com.market.trading.model.TradeInfo;
import com.vnx.patsystems.util.Feeder;

public class QuoteBean implements QuoteBeanDelegate{
	public static final String TRADING = "TRADE";
	public static final String ORDERING = "ORDER";
	public static final String CELL_UPDATED_STYLE = "color:blue;";
	public static final String ROW_UPDATED_STYLE = "background-color:#FFFFC4;";
	
	private static boolean isFeederReady = false;
	private List<Quote> quotes = null;
	private List<int[]> updatedQuotes = null;
	private String strStyle = "outputText1";
	
	private TradeInfo inputTradeInfo;
	/**
	 * Trading or ordering. (When press button trade, order)
	 */
	private String status = TRADING;

	private List<StatusInfo> statusInfos = new ArrayList<StatusInfo>(); 
	private List<FillInfo> fillInfors = new ArrayList<FillInfo>() ;
	
	@PostConstruct
	public void initData(){
		//init status = trading in the first time
		status = TRADING;
		
		if(!isFeederReady) {
			isFeederReady = true;
			new Feeder(this);
		}
		
		initStatus();
		initFill() ;
	}
	
	private void initStatus() {
		statusInfos = new ArrayList<StatusInfo>();
		statusInfos.add(new StatusInfo("Giacomo", 34, "VNXSIM", "VRC", "17/7/2011"));
		statusInfos.add(new StatusInfo("Guido Jack", 4, "VNXSIM", "R COFFEE", "14/7/2011"));
		statusInfos.add(new StatusInfo("Marco Botto", 31, "SGX", "RUBBER", "11/7/2011"));
	}
	
	private void initFill() {
		fillInfors.add(new FillInfo("AEX        103", "145598", "S 50", 305.00, "09/11/04 10:35:04", "09/11/04 10:36:04")) ;
		fillInfors.add(new FillInfo("AEX        103", "145599", "B 1",  303.08, "09/11/04 10:37:04", "09/11/04 10:38:04")) ;
		fillInfors.add(new FillInfo("AEX        103", "145600", "S 1",  306.02, "09/11/04 10:39:04", "09/11/04 10:40:04")) ;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	@Override
	public void fillQuotes(List<Quote> lstQuotes) {
		if (lstQuotes != null) {
			quotes = lstQuotes;
		} else {
			System.out.println("Nulllll");
		}
	}

	@Override
	public void updateRow(int index, Quote updatedQuote) {
		if (index < quotes.size()) {
			//quotes.get(index).setTitle(updatedQuote.getTitle());
			double oldVal = 0;
			double newVal = 0;
			
			try {
				oldVal = Double.parseDouble(quotes.get(index).getBid());
			} catch (Exception e) {
				oldVal = 0;
			}
			
			try {
				newVal = Double.parseDouble(updatedQuote.getBid());
			} catch (Exception e) {
				newVal = 0;
			}
			
			if (newVal > oldVal) {
				quotes.get(index).setBidUpdated(true);
				quotes.get(index).setBidStatus("inc");
				quotes.get(index).setBidStyleClass(CELL_UPDATED_STYLE);
			} else if (newVal < oldVal) {
				quotes.get(index).setBidUpdated(true);
				quotes.get(index).setBidStatus("dec");
				quotes.get(index).setBidStyleClass(CELL_UPDATED_STYLE);
			}
			quotes.get(index).setBid(updatedQuote.getBid());
			
			
			
			try {
				oldVal = Double.parseDouble(quotes.get(index).getBidVol());
			} catch (Exception e) {
				oldVal = 0;
			}
			
			try {
				newVal = Double.parseDouble(updatedQuote.getBidVol());
			} catch (Exception e) {
				newVal = 0;
			}
		
			if (newVal > oldVal) {
				quotes.get(index).setBidVolUpdated(true);
				quotes.get(index).setBidVolStatus("inc");
				quotes.get(index).setBidVolStyleClass(CELL_UPDATED_STYLE);
			} else if (newVal < oldVal) {
				quotes.get(index).setBidVolUpdated(true);
				quotes.get(index).setBidVolStatus("dec");
				quotes.get(index).setBidVolStyleClass(CELL_UPDATED_STYLE);
			}
			quotes.get(index).setBidVol(updatedQuote.getBidVol());
			
			
			
			try {
				oldVal = Double.parseDouble(quotes.get(index).getOffer());
			} catch (Exception e) {
				oldVal = 0;
			}
			
			try {
				newVal = Double.parseDouble(updatedQuote.getOffer());
			} catch (Exception e) {
				newVal = 0;
			}
			
			
			
			if (newVal > oldVal) {
				quotes.get(index).setOfferUpdated(true);
				quotes.get(index).setOfferStatus("inc");
				quotes.get(index).setOfferStyleClass(CELL_UPDATED_STYLE);
			} else if (newVal < oldVal) {
				quotes.get(index).setOfferUpdated(true);
				quotes.get(index).setOfferStatus("dec");
				quotes.get(index).setOfferStyleClass(CELL_UPDATED_STYLE);
			}
			quotes.get(index).setOffer(updatedQuote.getOffer());
			
			
			try {
				oldVal = Double.parseDouble(quotes.get(index).getOfferVol());
			} catch (Exception e) {
				oldVal = 0;
			}
			
			try {
				newVal = Double.parseDouble(updatedQuote.getOfferVol());
			} catch (Exception e) {
				newVal = 0;
			}
			
			if (newVal > oldVal) {
				quotes.get(index).setOfferVolUpdated(true);
				quotes.get(index).setOfferVolStatus("inc");
				quotes.get(index).setOfferVolStyleClass(CELL_UPDATED_STYLE);
			} else if (newVal < oldVal) {
				quotes.get(index).setOfferVolUpdated(true);
				quotes.get(index).setOfferVolStatus("dec");
				quotes.get(index).setOfferVolStyleClass(CELL_UPDATED_STYLE);
			}
			quotes.get(index).setOfferVol(updatedQuote.getOfferVol());
			
			
			
			try {
				oldVal = Double.parseDouble(quotes.get(index).getLast());
			} catch (Exception e) {
				oldVal = 0;
			}
			
			try {
				newVal = Double.parseDouble(updatedQuote.getLast());
			} catch (Exception e) {
				newVal = 0;
			}
			
			
			if (newVal > oldVal) {
				quotes.get(index).setLastUpdated(true);
				quotes.get(index).setLastStatus("inc");
				quotes.get(index).setLastStyleClass(CELL_UPDATED_STYLE);
			} else if (newVal < oldVal) {
				quotes.get(index).setLastUpdated(true);
				quotes.get(index).setLastStatus("dec");
				quotes.get(index).setLastStyleClass(CELL_UPDATED_STYLE);
			}
			quotes.get(index).setLast(updatedQuote.getLast());
			
			
			
			
			try {
				oldVal = Double.parseDouble(quotes.get(index).getLastVol());
			} catch (Exception e) {
				oldVal = 0;
			}
			
			try {
				newVal = Double.parseDouble(updatedQuote.getLastVol());
			} catch (Exception e) {
				newVal = 0;
			}
			
			
			if (newVal > oldVal) {
				quotes.get(index).setLastVolUpdated(true);
				quotes.get(index).setLastVolStatus("inc");
				quotes.get(index).setLastVolStyleClass(CELL_UPDATED_STYLE);
			} else if (newVal < oldVal) {
				quotes.get(index).setLastVolUpdated(true);
				quotes.get(index).setLastVolStatus("dec");
				quotes.get(index).setLastVolStyleClass(CELL_UPDATED_STYLE);
			}
			quotes.get(index).setLastVol(updatedQuote.getLastVol());
			
			if (quotes.get(index).isBidUpdated() || quotes.get(index).isBidVolUpdated()
				|| quotes.get(index).isOfferUpdated() || quotes.get(index).isOfferVolUpdated()
				|| quotes.get(index).isLastUpdated() || quotes.get(index).isLastVolUpdated()) {
				
				quotes.get(index).setRowStyle(ROW_UPDATED_STYLE);
				
				//Add updated row into list updated rows if not existing
				if (updatedQuotes == null) {
					updatedQuotes = new LinkedList<int[]>();
				}
				
				boolean isExisted = false;
				
				for (int[] quote : updatedQuotes) {
					if (index == quote[0]) {
						quote[1] = 0;
						isExisted = true;
						break;
					}
				}
				
				if (isExisted == false) {
					updatedQuotes.add(new int[] {index, 0});
				}
			}
		}
	}
	
	public void refreshScreen() {
		if (this.updatedQuotes != null) {
			//System.out.println(this.updatedQuotes.size());
			ArrayList<int[]> listRemove = new ArrayList<int[]>();
			
			for (int i = 0; i < this.updatedQuotes.size(); i++) {
				if (this.updatedQuotes.get(i)[1] >= 6) {
					quotes.get(this.updatedQuotes.get(i)[0]).setBidUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setBidVolUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setOfferUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setOfferVolUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setLastUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setLastVolUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setBidStyleClass("");
					quotes.get(this.updatedQuotes.get(i)[0]).setBidVolStyleClass("");
					quotes.get(this.updatedQuotes.get(i)[0]).setOfferStyleClass("");
					quotes.get(this.updatedQuotes.get(i)[0]).setOfferVolStyleClass("");
					quotes.get(this.updatedQuotes.get(i)[0]).setLastStyleClass("");
					quotes.get(this.updatedQuotes.get(i)[0]).setLastVolStyleClass("");
					quotes.get(this.updatedQuotes.get(i)[0]).setRowStyle("");
					listRemove.add(this.updatedQuotes.get(i));
				} else {
					this.updatedQuotes.get(i)[1]++;
				}
			}
			
			for (int[] item : listRemove) {
				this.updatedQuotes.remove(item);
			}
		}
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void changeStatus(){
		if(TRADING.equals(status)){
			status = ORDERING;
		}else{
			status = TRADING;
		}
	}

	public String getStrStyle() {
		return strStyle;
	}

	public void setStrStyle(String strStyle) {
		this.strStyle = strStyle;
	}

	public TradeInfo getInputTradeInfo() {
		return inputTradeInfo;
	}

	public void setInputTradeInfo(TradeInfo inputTradeInfo) {
		this.inputTradeInfo = inputTradeInfo;
	}

	public List<StatusInfo> getStatusInfos() {
		return statusInfos;
	}

	public void setStatusInfos(List<StatusInfo> statusInfos) {
		this.statusInfos = statusInfos;
	}

	public List<FillInfo> getFillInfors() {
		return fillInfors;
	}

	public void setFillInfors(List<FillInfo> fillInfors) {
		this.fillInfors = fillInfors;
	}

}
