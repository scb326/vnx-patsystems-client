package com.market.trading.model;

public class StatusInfo {
	private String orderId;
	private int status;
	private String exchange;
	private String commodity;
	private String contractDate;
	
	public StatusInfo() {
	}
	
	public StatusInfo(String orderId, int status, String exchange, String commodity, String contractDate){
		this.orderId = orderId;
		this.status = status;
		this.exchange = exchange;
		this.commodity = commodity;
		this.contractDate = contractDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	
}
