package com.amazecreationz.server;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(description = "For adding cors headers", asyncSupported = true, urlPatterns = { "/*" })
public class CORSFilter implements Filter {

    public CORSFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
 
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
 
        HttpServletResponse resp = (HttpServletResponse) response;
 
        if (httpRequest.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        
        chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
