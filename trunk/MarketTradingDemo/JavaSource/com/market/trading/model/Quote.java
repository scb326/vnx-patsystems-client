package com.market.trading.model;

public class Quote {
	
	private String title;
	private String bid;
	private String ask;
	private String last;
	private String tickVol;
	
	private boolean bidUpdated = false;
	private String bidStatus = "";
	private String bidStyleClass = "";
	
	private boolean askUpdated = false;
	private String askStatus = "";
	private String askStyleClass = "";
	
	private boolean lastUpdated = false;
	private String lastStatus = "";
	private String lastStyleClass = "";
	
	private boolean tickVolUpdated = false;
	private String tickVolStatus = "";
	private String tickVolStyleClass = "";
	
	//Updated row style
	private String rowStyle = "";
	
	public String getBidStyleClass() {
		return bidStyleClass;
	}

	public void setBidStyleClass(String bidStyleClass) {
		this.bidStyleClass = bidStyleClass;
	}

	public String getAskStyleClass() {
		return askStyleClass;
	}

	public void setAskStyleClass(String askStyleClass) {
		this.askStyleClass = askStyleClass;
	}

	public String getLastStyleClass() {
		return lastStyleClass;
	}

	public void setLastStyleClass(String lastStyleClass) {
		this.lastStyleClass = lastStyleClass;
	}

	public String getTickVolStyleClass() {
		return tickVolStyleClass;
	}

	public void setTickVolStyleClass(String tickVolStyleClass) {
		this.tickVolStyleClass = tickVolStyleClass;
	}

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

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public String getAskStatus() {
		return askStatus;
	}

	public void setAskStatus(String askStatus) {
		this.askStatus = askStatus;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public String getTickVolStatus() {
		return tickVolStatus;
	}

	public void setTickVolStatus(String tickVolStatus) {
		this.tickVolStatus = tickVolStatus;
	}

	public boolean isBidUpdated() {
		return bidUpdated;
	}

	public void setBidUpdated(boolean bidUpdated) {
		this.bidUpdated = bidUpdated;
	}

	public boolean isAskUpdated() {
		return askUpdated;
	}

	public void setAskUpdated(boolean askUpdated) {
		this.askUpdated = askUpdated;
	}

	public boolean isLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(boolean lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public boolean isTickVolUpdated() {
		return tickVolUpdated;
	}

	public void setTickVolUpdated(boolean tickVolUpdated) {
		this.tickVolUpdated = tickVolUpdated;
	}

	public String getRowStyle() {
		return rowStyle;
	}

	public void setRowStyle(String rowStyle) {
		this.rowStyle = rowStyle;
	}
}
