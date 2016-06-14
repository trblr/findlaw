package com.dcgenerator.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicationCreateAction extends DCGeneratorSupport{
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	private String applicationName;
	

	public String execute() throws Exception{
		System.out.println("ApplicationCreateAction.execute - Start");
		session = request.getSession();
		
		String appName= getApplicationName();
		session.setAttribute("application", appName);
		
		//Create new application in session
		List applist = new ArrayList();   
        List tempList = (ArrayList)session.getAttribute("appList");
        if(tempList != null && tempList.size() != 0)
	        for(int i=0;i<tempList.size();i++)
	        	applist.add(tempList.get(i));
        
        applist.add(appName);        
        session.setAttribute("appList", applist);
        System.out.println("ApplicationCreateAction.execute - AppList: " + applist);
        
        //If copy attribute was set, copy application from that object
        String copyApps = (String)request.getParameter("copyApps");
        System.out.println("ApplicationCreateAction.execute - AppToCopyFrom: " + copyApps);
        if(copyApps != null && !"-1".equals(copyApps)){
        	//Copy the properties
        	for(int i=1; i<11; i++){
        		session.setAttribute("propN" + i + appName, session.getAttribute("propN" + i + copyApps));
        		session.setAttribute("propV" + i + appName, session.getAttribute("propV" + i + copyApps));
        	}
        	
        	List newWFList = new ArrayList(), newStepList = new ArrayList(), oldStepList = new ArrayList();//Initialize the variables
        	List oldWFList = (ArrayList)session.getAttribute("workflowList" + copyApps);//Get all WorkFlow names
        	System.out.println("ApplicationCreateAction.execute - oldWFList: " + oldWFList);
        	if(oldWFList != null){
	        	for(int wl=0; wl<oldWFList.size(); wl++){//Add WFs to the new list
	        		String workFlowName = (String)oldWFList.get(wl);
	        		System.out.println("ApplicationCreateAction.execute - workFlowName: " + workFlowName);
	        		oldStepList = (ArrayList)session.getAttribute("stepList" + copyApps + (wl + 1));
	        		newStepList = new ArrayList();
	        		if(oldStepList != null){
		        		for(int sl=0; sl<oldStepList.size(); sl++)
		        			newStepList.add(oldStepList.get(sl));
	        		}
	        		newWFList.add(workFlowName);
	        		session.setAttribute("stepList" + appName + (wl + 1), newStepList);
	        	}
        	}
        	
        	session.setAttribute("workflowList" + appName, newWFList);//Set the WorkFlow list in session
        }
        
        System.out.println("ApplicationCreateAction.execute - End");
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
