package com.market.trading.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.market.trading.model.Quote;

public class QuoteBean {
	List<Quote> quotes = new ArrayList<Quote>();

	@PostConstruct
	public void initData(){
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
