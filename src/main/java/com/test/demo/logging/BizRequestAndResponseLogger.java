package com.test.demo.logging;

import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

public interface BizRequestAndResponseLogger {
	
	void logError(String loggerName, String message, boolean procced);
	
	void logRequest(String loggerName, Object obj, boolean procced);
	
	void logResponse(String loggerName, Object obj, boolean procced);

	ExchangeFilterFunction logRequest(String reqLogName);

	ExchangeFilterFunction logResponse(String resLogName);

}