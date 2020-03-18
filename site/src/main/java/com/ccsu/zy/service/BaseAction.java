package com.ccsu.zy.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.ccsu.zy.util.JSONUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void writerToUiJson(Object object) {
		String lJson = JSONUtil.toJSONString(object);

		PrintWriter pw = getWriter();
		pw.write(lJson);
		close(pw);

	}

	/**
	 * get session
	 */
	public Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	/**
	 * get response by ServletActionContext
	 * 
	 * @return response
	 */
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		return response;
	}

	/**
	 * get request by ServletActionContext
	 * 
	 * @return request
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * open PrintWrite
	 * 
	 * @return pw
	 */
	public PrintWriter getWriter() {
		HttpServletResponse response = this.getResponse();
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pw;
	}

	/**
	 * close PrintWrite
	 * 
	 * @param pw
	 */
	public void close(PrintWriter pw) {
		if (pw != null) {
			pw.close();
		}
	}

}
