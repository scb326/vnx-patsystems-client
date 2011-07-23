package com.market.trading.model;

import com.market.trading.utils.DateTimeUtils;

/**
 * 
 */

/**
 * @author huanpham
 *
 */
public class HeaderBean {
	private String serverTime;

	public HeaderBean() {
		serverTime = "00:00:00";
	}
	
	public String getServerTime() {
		serverTime = DateTimeUtils.getCurrentDate("HH:mm:ss");
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}
	
}
