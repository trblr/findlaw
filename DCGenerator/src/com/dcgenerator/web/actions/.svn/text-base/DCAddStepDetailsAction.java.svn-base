package com.dcgenerator.web.actions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class DCAddStepDetailsAction extends DCGeneratorSupport{
	
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	
	/**
	 * Method will set the request variables
	 */
	public String execute() throws Exception{
		request.setAttribute("isNew", "N");
        request.setAttribute("isAdd", "Y");//This is add flow hence Y
        request.setAttribute("isUpdate", "N");
        request.setAttribute("isCopy", "N");
        
		return SUCCESS;
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
}
