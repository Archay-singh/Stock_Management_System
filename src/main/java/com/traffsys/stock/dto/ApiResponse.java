package com.traffsys.stock.dto;

public class ApiResponse {
	
	private int status;   //status,message and data are for ApiResponse and pageNo and totalrecord are for paging.
	private String message;
	private Object data;
	private int pageNo;
	private long totaleRecord;
	
	public ApiResponse(int status, String message, Object data) {
		
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public long getTotaleRecord() {
		return totaleRecord;
	}
	public void setTotaleRecord(long totaleRecord) {
		this.totaleRecord = totaleRecord;
	}
	public ApiResponse(int status, String message, Object data, int pageNo, long totaleRecord) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.pageNo = pageNo;
		this.totaleRecord = totaleRecord;
	}
	
	
}
