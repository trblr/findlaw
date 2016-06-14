package com.dcgenerator.web.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetApplicationAction extends DCGeneratorSupport 
{
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	private String applicationName;
	

	public String execute() throws Exception 
    {
		session = request.getSession();
		String appName = request.getParameter("appVal");
		session.setAttribute("application", appName);
		session.setAttribute("workflowValue", "1");
		
     
		return "success";
    }

	public void setServletContext(ServletContext context) {
	
		this.context = context;
	}
	public void setServletRequest(HttpServletRequest request) {
	
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
	
		this.response = response;
	}
	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
