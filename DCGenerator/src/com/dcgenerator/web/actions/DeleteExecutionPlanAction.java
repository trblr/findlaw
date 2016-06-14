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

public class DeleteExecutionPlanAction extends DCGeneratorSupport implements ServletContextAware , ServletRequestAware , ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;		
	
    @SuppressWarnings("unchecked")
	public String execute() throws Exception{
    	System.out.println("DeleteExecutionPlanAction.execute - Start");
    	
    	//Get parameters from session
        session = request.getSession();
        String recIdInReq = request.getParameter("recIdInReq");
        String workflowVal = (String)session.getAttribute("workflowValue");
        String appName = (String)session.getAttribute("application");
        if(null==workflowVal)
        	workflowVal="";
        
        
        List resultList = new ArrayList();
        List list = (ArrayList)session.getAttribute("stepList"+appName+workflowVal);
        System.out.println("DeleteExecutionPlanAction.execute - Step to be deleted: " + recIdInReq);
		System.out.println("DeleteExecutionPlanAction.execute - Before Delete: " + list);
		
		if(list != null && list.size() > 0){
			boolean isFound = false;
			for(Iterator iter = list.iterator();iter.hasNext();){
				String deleteValues = (String)iter.next();				
				StringTokenizer  str = new StringTokenizer(deleteValues,",");
				List newList = new ArrayList();
				while(str.hasMoreTokens())
					newList.add(str.nextToken());
				
				String recId = (String)newList.get(5);
				if(recId.equals(recIdInReq)){
					isFound = true;
					System.out.println("DeleteExecutionPlanAction.execute - Found Record. Deleted...");
					continue;
				}
				
				if(isFound){
					deleteValues = (Integer.parseInt((String)newList.get(0)) - 1) + "";
					for(int i=1; i<5; i++)
						deleteValues = deleteValues + "," + (String)newList.get(i);
					deleteValues = deleteValues + "," + (Integer.parseInt((String)newList.get(5)) - 1);
					for(int i=6; i<newList.size(); i++)
						deleteValues = deleteValues + "," + (String)newList.get(i);
					
				}
				
				resultList.add(deleteValues);
			}
		}
		
		System.out.println("DeleteExecutionPlanAction.execute - After Delete: " + resultList);
        session.setAttribute("stepList"+appName+workflowVal, resultList);        
        System.out.println("DeleteExecutionPlanAction.execute - Start");
        return SUCCESS;
    }
   
	
	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
	}
}

