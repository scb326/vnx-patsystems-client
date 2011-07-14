package com.market.trading.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.taglib.DataTableTag;

import com.market.trading.bean.delegate.QuoteBeanDelegate;
import com.market.trading.model.Quote;
import com.vnx.patsystems.util.Feeder;
import com.vnx.patsystems.util.Feeder.SubscribedItem;

public class QuoteBean implements QuoteBeanDelegate{
	
	private static boolean isFeederReady = false;
	private List<Quote> quotes = null;
	private int index = 0;
	private HtmlDataTable tblQuote;

	public HtmlDataTable getTblQuote() {
		return tblQuote;
	}

	public void setTblQuote(HtmlDataTable tblQuote) {
		this.tblQuote = tblQuote;
	}

	@PostConstruct
	public void initData(){
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
			quotes.get(index).setAsk(updatedQuote.getAsk());
			quotes.get(index).setBid(updatedQuote.getBid());
			quotes.get(index).setLast(updatedQuote.getLast());
			quotes.get(index).setTickVol(updatedQuote.getTickVol());
		}
	}
}
