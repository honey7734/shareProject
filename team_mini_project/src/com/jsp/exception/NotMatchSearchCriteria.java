package com.jsp.exception;

public class NotMatchSearchCriteria extends Exception{
	
	public NotMatchSearchCriteria() {
		super("SearchCriteria 타입이 아닙니다.");
	}
	
}
