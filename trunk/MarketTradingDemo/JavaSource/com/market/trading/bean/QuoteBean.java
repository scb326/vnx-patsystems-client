package com.market.trading.bean;

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
	private static boolean isFeederReady = false;
	private List<Quote> quotes = null;
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
			if (lastUpdated >= 0) {
				quotes.get(lastUpdated).setAskUpdated(false);
				quotes.get(lastUpdated).setBidUpdated(false);
				quotes.get(lastUpdated).setLastUpdated(false);
				quotes.get(lastUpdated).setTickVolUpdated(false);
			}
			
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
			} else if (newVal < oldVal) {
				quotes.get(index).setAskUpdated(true);
				quotes.get(index).setAskStatus("dec");
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
			} else if (newVal < oldVal) {
				quotes.get(index).setBidUpdated(true);
				quotes.get(index).setBidStatus("dec");
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
			} else if (newVal < oldVal) {
				quotes.get(index).setLastUpdated(true);
				quotes.get(index).setLastStatus("dec");
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
			} else if (newVal < oldVal) {
				quotes.get(index).setTickVolUpdated(true);
				quotes.get(index).setTickVolStatus("dec");
			}
			quotes.get(index).setTickVol(updatedQuote.getTickVol());
			
			lastUpdated = index;
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
