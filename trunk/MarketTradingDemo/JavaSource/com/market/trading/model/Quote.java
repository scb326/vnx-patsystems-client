package com.market.trading.model;

public class Quote {
	
	private String icon = "icon";
	private String contract;
	private String bid;
	private String bidVol;
	private String offer;
	private String offerVol;
	private String last;
	private String lastVol;
	private String dom = "view";
	
	private boolean bidUpdated = false;
	private String bidStatus = "";
	private String bidStyleClass = "";
	
	private boolean bidVolUpdated = false;
	private String bidVolStatus = "";
	private String bidVolStyleClass = "";
	
	private boolean offerUpdated = false;
	private String offerStatus = "";
	private String offerStyleClass = "";
	
	private boolean offerVolUpdated = false;
	private String offerVolStatus = "";
	private String offerVolStyleClass = "";
	
	private boolean lastUpdated = false;
	private String lastStatus = "";
	private String lastStyleClass = "";
	
	private boolean lastVolUpdated = false;
	private String lastVolStatus = "";
	private String lastVolStyleClass = "";
	
	//Updated row style
	private String rowStyle = "";
	
	public String getBidStyleClass() {
		return bidStyleClass;
	}

	public void setBidStyleClass(String bidStyleClass) {
		this.bidStyleClass = bidStyleClass;
	}

	public String getLastStyleClass() {
		return lastStyleClass;
	}

	public void setLastStyleClass(String lastStyleClass) {
		this.lastStyleClass = lastStyleClass;
	}

	public Quote() {
		contract = "";
		bid = "0";
		offer = "0";
		last = "0";
		lastVol = "0";
	}
	
	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getBid() {
		return bid;
	}
	
	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBidVol() {
		return bidVol;
	}

	public void setBidVol(String bidVol) {
		this.bidVol = bidVol;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getOfferVol() {
		return offerVol;
	}

	public void setOfferVol(String offerVol) {
		this.offerVol = offerVol;
	}

	public String getLast() {
		return last;
	}
	
	public void setLast(String last) {
		this.last = last;
	}

	public String getLastVol() {
		return lastVol;
	}

	public void setLastVol(String lastVol) {
		this.lastVol = lastVol;
	}

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public boolean isBidUpdated() {
		return bidUpdated;
	}

	public void setBidUpdated(boolean bidUpdated) {
		this.bidUpdated = bidUpdated;
	}

	public boolean isLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(boolean lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getRowStyle() {
		return rowStyle;
	}

	public void setRowStyle(String rowStyle) {
		this.rowStyle = rowStyle;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDom() {
		return dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public boolean isBidVolUpdated() {
		return bidVolUpdated;
	}

	public void setBidVolUpdated(boolean bidVolUpdated) {
		this.bidVolUpdated = bidVolUpdated;
	}

	public String getBidVolStatus() {
		return bidVolStatus;
	}

	public void setBidVolStatus(String bidVolStatus) {
		this.bidVolStatus = bidVolStatus;
	}

	public String getBidVolStyleClass() {
		return bidVolStyleClass;
	}

	public void setBidVolStyleClass(String bidVolStyleClass) {
		this.bidVolStyleClass = bidVolStyleClass;
	}

	public boolean isOfferUpdated() {
		return offerUpdated;
	}

	public void setOfferUpdated(boolean offerUpdated) {
		this.offerUpdated = offerUpdated;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	public String getOfferStyleClass() {
		return offerStyleClass;
	}

	public void setOfferStyleClass(String offerStyleClass) {
		this.offerStyleClass = offerStyleClass;
	}

	public boolean isOfferVolUpdated() {
		return offerVolUpdated;
	}

	public void setOfferVolUpdated(boolean offerVolUpdated) {
		this.offerVolUpdated = offerVolUpdated;
	}

	public String getOfferVolStatus() {
		return offerVolStatus;
	}

	public void setOfferVolStatus(String offerVolStatus) {
		this.offerVolStatus = offerVolStatus;
	}

	public String getOfferVolStyleClass() {
		return offerVolStyleClass;
	}

	public void setOfferVolStyleClass(String offerVolStyleClass) {
		this.offerVolStyleClass = offerVolStyleClass;
	}

	public boolean isLastVolUpdated() {
		return lastVolUpdated;
	}

	public void setLastVolUpdated(boolean lastVolUpdated) {
		this.lastVolUpdated = lastVolUpdated;
	}

	public String getLastVolStatus() {
		return lastVolStatus;
	}

	public void setLastVolStatus(String lastVolStatus) {
		this.lastVolStatus = lastVolStatus;
	}

	public String getLastVolStyleClass() {
		return lastVolStyleClass;
	}

	public void setLastVolStyleClass(String lastVolStyleClass) {
		this.lastVolStyleClass = lastVolStyleClass;
	}
}
