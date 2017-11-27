package com.hc.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	 private FilterConfig config = null;
	 private String encoding = null;
	    
	@Override
	public void destroy() {
		config = null;
        encoding = null;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 
		        if (encoding == null) {
		            encoding = config.getInitParameter("encoding");
		           }
		           request.setCharacterEncoding(encoding);
		           response.setCharacterEncoding(encoding);
		           chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		 this.config = config;
		
	}



}
