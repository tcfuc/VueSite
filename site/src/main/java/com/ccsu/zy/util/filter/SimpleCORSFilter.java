package com.ccsu.zy.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleCORSFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
 
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,  
	                         FilterChain chain) throws IOException, ServletException {  
        try {  
            HttpServletRequest httpRequest = (HttpServletRequest) request;  
            HttpServletResponse httpResponse = (HttpServletResponse) response;  
  
            // 跨域  
            String origin = httpRequest.getHeader("Origin");  
            if (origin == null) {  
                httpResponse.setHeader("Access-Control-Allow-Origin", "*");  
            } else {  
                httpResponse.setHeader("Access-Control-Allow-Origin", origin);  
            }  
            httpResponse.setHeader("Access-Control-Allow-Headers", "access-control-allow-headers,access-control-allow-methods,access-control-allow-origin,token");  
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");  
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS,DELETE");  
            if ( httpRequest.getMethod().equals("OPTIONS") ) {  
                httpResponse.setStatus(HttpServletResponse.SC_OK);  
                return;  
            }  
            chain.doFilter(request, response);  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
 
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
 
	}

}
