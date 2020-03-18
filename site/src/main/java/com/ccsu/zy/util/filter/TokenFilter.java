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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccsu.zy.model.ResponseCode;
import com.ccsu.zy.util.JWTUtil;
import com.google.gson.Gson;

@Component
public class TokenFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
	private String excludedPages;
	private String[] excludedPageArray;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("初始化token拦截器");  
		excludedPages = filterConfig.getInitParameter("excludedPages");
		if (StringUtils.isNotEmpty(excludedPages)) {     
			excludedPageArray = excludedPages.split(",");     
		}  
		return;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("进入token拦截器");
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//判断是否在过滤url之外
		boolean isExcludedPage = false;
		String actionUrl = req.getServletPath().trim();
		for (String action : excludedPageArray) {
			//判断是否在过滤url之外
			if(actionUrl.equals(action)){     
				isExcludedPage = true;
				break;
			}     
		}
		if(isExcludedPage) {
			chain.doFilter(request, response);
		} else {
			try {
			ResponseCode rc = new ResponseCode();
			Gson gson = new Gson();
			//验证token是否存在
			//获取头部信息中的token
			String token = req.getHeader("token").replace("\"", "");
//			Cookie[] cookies = req.getCookies();
//			if(null != cookies) {
//				for (Cookie c : cookies) {
//			 		if (c.getName().equals("token")) {
//			 			token = c.getValue();
//			 			logger.info("获取token");
//			 		}
//			 	}
//			}
			if(null != token) {
				logger.info(token);
				//验证token是否正确
				boolean result = JWTUtil.verify(token);
				if(result) {
					logger.info("token正确");
				    chain.doFilter(request, response); 
				} else {
					// 403 token过期
					logger.info("token过期");
					rc.setCode("50014");
				}
			}else {
				// 401: 未登录
				logger.info("未登录");
				rc.setCode("50008");
			}
			String gsonObject = gson.toJson(rc);
			res.getWriter().write(gsonObject);
			res.getWriter().flush();
			res.getWriter().close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("销毁token拦截器");
		return;
	}

}
