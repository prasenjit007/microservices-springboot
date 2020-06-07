package com.microservices.zuul;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter{
	private Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);
	
	@Override
	public boolean shouldFilter() {
		// can add business logic to enable or disable this filter at real time
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request =  RequestContext.getCurrentContext().getRequest();
		
		logger.info("Request --> {}",request);
		logger.info("Request URI --> {}",request.getRequestURI());
		logger.info("Request PORT --> {}",request.getLocalPort());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}