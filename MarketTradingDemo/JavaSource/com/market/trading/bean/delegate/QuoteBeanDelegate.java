package com.market.trading.bean.delegate;

import java.util.List;

import com.market.trading.model.Quote;

public interface QuoteBeanDelegate {
	public void fillQuotes(List<Quote> lstQuotes);
	public void updateRow(int index, Quote updatedQuote);
}
