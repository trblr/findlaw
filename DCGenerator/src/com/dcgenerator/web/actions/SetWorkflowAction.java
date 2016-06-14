package com.dcgenerator.web.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import java.util.Iterator;

public class SetWorkflowAction extends DCGeneratorSupport implements ServletContextAware , ServletRequestAware , ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;	
	  


	public String execute() throws Exception 
    {     	
        session = request.getSession(true);  
        System.out.println("setworkflow action");
        String workflowNumber = (String)request.getParameter("workflowNum");
        String appName = (String)session.getAttribute("application");
        System.out.println("setting the workflow:"+workflowNumber);
        
		session.setAttribute("workflowValue", workflowNumber);		
		request.setAttribute("copyUp", "N");
        return SUCCESS;
    }
   
	
	public void setServletContext(ServletContext arg0) {
		
		this.context = arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response=arg0;
	}
	
    
}

