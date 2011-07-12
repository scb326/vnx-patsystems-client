package com.vnx.patsystems.model;

public class Quote {
	
	private String title;
	private String bid;
	private String ask;
	private String last;
	private String tickVol;
	
	public Quote() {
		title = "";
		bid = "0";
		ask = "0";
		last = "0";
		tickVol = "0";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBid() {
		return bid;
	}
	
	public void setBid(String bid) {
		this.bid = bid;
	}
	
	public String getAsk() {
		return ask;
	}
	
	public void setAsk(String ask) {
		this.ask = ask;
	}
	
	public String getLast() {
		return last;
	}
	
	public void setLast(String last) {
		this.last = last;
	}
	
	public String getTickVol() {
		return tickVol;
	}
	
	public void setTickVol(String tickVol) {
		this.tickVol = tickVol;
	}
}
