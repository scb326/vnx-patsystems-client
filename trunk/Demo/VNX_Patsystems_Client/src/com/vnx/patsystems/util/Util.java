package com.vnx.patsystems.util;

import com.vnx.patsystems.model.Quote;

public class Util {
	
	private static Quote quoteTables[];
	
	public static void setQuoteTable(Quote quoteTables[]) {
		Util.quoteTables = quoteTables;
	}
	
	public static Quote[] getQuoteTable() {
		return quoteTables;
	}
}
