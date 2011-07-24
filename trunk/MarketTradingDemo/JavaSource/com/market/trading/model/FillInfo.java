package com.market.trading.model;

public class FillInfo {

	private String fillId ;
	private String orderId ;
	private String lotNum ;
	private double price ;
	private String filled ;
	private String received ;
	
	public FillInfo(String fillId, String  orderId, String lotNum, double price, String filled,String received) {
		this.fillId = fillId ;
		this.orderId = orderId ;
		this.lotNum = lotNum ;
		this.price = price ;
		this.filled = filled ;
		this.received = received ;
	}

	public String getFillId() {
		return fillId;
	}

	public void setFillId(String fillId) {
		this.fillId = fillId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getLotNum() {
		return lotNum;
	}

	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFilled() {
		return filled;
	}

	public void setFilled(String filled) {
		this.filled = filled;
	}

	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}
}
