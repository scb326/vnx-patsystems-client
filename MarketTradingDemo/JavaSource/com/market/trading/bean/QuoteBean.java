package com.market.trading.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlOutputText;

import org.richfaces.component.html.HtmlDataTable;

import com.market.trading.bean.delegate.QuoteBeanDelegate;
import com.market.trading.model.Quote;
import com.vnx.patsystems.util.Feeder;

public class QuoteBean implements QuoteBeanDelegate{
	public static final String TRADING = "TRADE";
	public static final String ORDERING = "ORDER";
	public static final String CELL_UPDATED_STYLE = "color:blue;";
	public static final String ROW_UPDATED_STYLE = "background-color:#FFFFC4;";
	
	private static boolean isFeederReady = false;
	private List<Quote> quotes = null;
	private List<int[]> updatedQuotes = null;
	private int lastUpdated = -1;
	private HtmlDataTable tblQuote;
	private HtmlOutputText outTxtBid;
	private HtmlOutputText outTxtAsk;
	private HtmlOutputText outTxtLast;
	private HtmlOutputText outTxtTickVol;
	private String strStyle = "outputText1";
	
	/**
	 * Trading or ordering. (When press button trade, order)
	 */
	private String status = TRADING;

	public HtmlDataTable getTblQuote() {
		return tblQuote;
	}

	public void setTblQuote(HtmlDataTable tblQuote) {
		this.tblQuote = tblQuote;
	}

	@PostConstruct
	public void initData(){
		//init status = trading in the first time
		status = TRADING;
		
		if(!isFeederReady) {
			isFeederReady = true;
			new Feeder(this);
		}
		
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
				oldVal = Double.parseDouble(quotes.get(index).getAsk());
			} catch (Exception e) {
				oldVal = 0;
			}
			
			try {
				newVal = Double.parseDouble(updatedQuote.getAsk());
			} catch (Exception e) {
				newVal = 0;
			}
			
			if (newVal > oldVal) {
				quotes.get(index).setAskUpdated(true);
				quotes.get(index).setAskStatus("inc");
				quotes.get(index).setAskStyleClass(CELL_UPDATED_STYLE);
			} else if (newVal < oldVal) {
				quotes.get(index).setAskUpdated(true);
				quotes.get(index).setAskStatus("dec");
				quotes.get(index).setAskStyleClass(CELL_UPDATED_STYLE);
			}
			quotes.get(index).setAsk(updatedQuote.getAsk());
			
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
				oldVal = Double.parseDouble(quotes.get(index).getTickVol());
			} catch (Exception e) {
				oldVal = 0;
			}
			
			try {
				newVal = Double.parseDouble(updatedQuote.getTickVol());
			} catch (Exception e) {
				newVal = 0;
			}
			
			
			if (newVal > oldVal) {
				quotes.get(index).setTickVolUpdated(true);
				quotes.get(index).setTickVolStatus("inc");
				quotes.get(index).setTickVolStyleClass(CELL_UPDATED_STYLE);
			} else if (newVal < oldVal) {
				quotes.get(index).setTickVolUpdated(true);
				quotes.get(index).setTickVolStatus("dec");
				quotes.get(index).setTickVolStyleClass(CELL_UPDATED_STYLE);
			}
			quotes.get(index).setTickVol(updatedQuote.getTickVol());
			
			if (quotes.get(index).isAskUpdated() || quotes.get(index).isBidUpdated()
				|| quotes.get(index).isLastUpdated() || quotes.get(index).isTickVolUpdated()) {
				
				quotes.get(index).setAskStyleClass(quotes.get(index).getAskStyleClass() + ROW_UPDATED_STYLE);
				quotes.get(index).setBidStyleClass(quotes.get(index).getBidStyleClass() + ROW_UPDATED_STYLE);
				quotes.get(index).setLastStyleClass(quotes.get(index).getLastStyleClass() + ROW_UPDATED_STYLE);
				quotes.get(index).setTickVolStyleClass(quotes.get(index).getTickVolStyleClass() + ROW_UPDATED_STYLE);
				
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
//				if (lastUpdated >= 0) {
//					quotes.get(lastUpdated).setAskUpdated(false);
//					quotes.get(lastUpdated).setBidUpdated(false);
//					quotes.get(lastUpdated).setLastUpdated(false);
//					quotes.get(lastUpdated).setTickVolUpdated(false);
//					quotes.get(lastUpdated).setAskStyleClass("");
//					quotes.get(lastUpdated).setBidStyleClass("");
//					quotes.get(lastUpdated).setLastStyleClass("");
//					quotes.get(lastUpdated).setTickVolStyleClass("");
//				}
			}
//			
//			lastUpdated = index;
		}
	}
	
	public void refreshScreen() {
		if (this.updatedQuotes != null) {
			//System.out.println(this.updatedQuotes.size());
			ArrayList<int[]> listRemove = new ArrayList<int[]>();
			
			for (int i = 0; i < this.updatedQuotes.size(); i++) {
				if (this.updatedQuotes.get(i)[1] >= 6) {
					quotes.get(this.updatedQuotes.get(i)[0]).setAskUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setBidUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setLastUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setTickVolUpdated(false);
					quotes.get(this.updatedQuotes.get(i)[0]).setAskStyleClass("");
					quotes.get(this.updatedQuotes.get(i)[0]).setBidStyleClass("");
					quotes.get(this.updatedQuotes.get(i)[0]).setLastStyleClass("");
					quotes.get(this.updatedQuotes.get(i)[0]).setTickVolStyleClass("");
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

	public HtmlOutputText getOutTxtBid() {
		return outTxtBid;
	}

	public void setOutTxtBid(HtmlOutputText outTxtBid) {
		this.outTxtBid = outTxtBid;
	}

	public HtmlOutputText getOutTxtAsk() {
		return outTxtAsk;
	}

	public void setOutTxtAsk(HtmlOutputText outTxtAsk) {
		this.outTxtAsk = outTxtAsk;
	}

	public HtmlOutputText getOutTxtLast() {
		return outTxtLast;
	}

	public void setOutTxtLast(HtmlOutputText outTxtLast) {
		this.outTxtLast = outTxtLast;
	}

	public HtmlOutputText getOutTxtTickVol() {
		return outTxtTickVol;
	}

	public void setOutTxtTickVol(HtmlOutputText outTxtTickVol) {
		this.outTxtTickVol = outTxtTickVol;
	}

	public String getStrStyle() {
		return strStyle;
	}

	public void setStrStyle(String strStyle) {
		this.strStyle = strStyle;
	}

}
