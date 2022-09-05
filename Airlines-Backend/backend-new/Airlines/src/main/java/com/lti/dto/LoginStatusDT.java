package com.lti.dto;

public class LoginStatusDT {

	private boolean result;
	private int fetchedId;
	private String fetchedName;
	
	public int getFetchedId() {
		return fetchedId;
	}
	public void setFetchedId(int fetchedId) {
		this.fetchedId = fetchedId;
	}
	public String getFetchedName() {
		return fetchedName;
	}
	public void setFetchedName(String fetchedName) {
		this.fetchedName = fetchedName;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public int getFetchedUserId() {
		return fetchedId;
	}
	public void setFetchedUserId(int fetchedId) {
		this.fetchedId = fetchedId;
	}
	
}
