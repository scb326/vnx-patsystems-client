package com.market.trading.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import com.market.trading.model.Quote;
import com.vnx.patsystems.util.Feeder;

public class QuoteBean {
	
	private static boolean isFeederReady = false;
	private List<Quote> quotes = new ArrayList<Quote>();
	
	@PostConstruct
	public void initData(){
		if(!isFeederReady) {
			isFeederReady = true;
			new Feeder();
		}
		
		quotes = new ArrayList<Quote>();
		for (int i = 0; i < 5; i++) {
			Quote q = new Quote();
			q.setAsk("Ask" + i);
			q.setBid("Bid" + i);
			q.setLast("Last" + i);
			q.setTitle("Title" + i);
			q.setTickVol("Tick" + i);
			quotes.add(q);
		}
	}
	
	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}
}
