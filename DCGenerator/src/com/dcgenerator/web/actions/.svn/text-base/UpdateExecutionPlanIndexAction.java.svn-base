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

public class UpdateExecutionPlanIndexAction extends DCGeneratorSupport implements ServletContextAware , ServletRequestAware , ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
    @SuppressWarnings("unchecked")
	public String execute() throws Exception{
    	System.out.println("UpdateExecutionPlanIndexAction.execute - Start");
        session = request.getSession(true);       
        
        String recIdInReq = request.getParameter("recIdInReq");
        System.out.println("UpdateExecutionPlanIndexAction.execute - StepID to update: " + recIdInReq);
        
        String workflowVal = (String)session.getAttribute("workflowValue");
        String appName = (String)session.getAttribute("application");
        List newList = (ArrayList)session.getAttribute("stepList" + appName + workflowVal);
		List resultList = new ArrayList();
		if(newList != null && newList.size() > 0){
			for(Iterator iter = newList.iterator();iter.hasNext();){
				String deleteValues = (String)iter.next();				
				StringTokenizer  str = new StringTokenizer(deleteValues,",");
				List valueList = new ArrayList();
				while(str.hasMoreTokens()){
					String value = str.nextToken();
					valueList.add(value);
				}
				
				String recId = (String)valueList.get(5);
				if(recIdInReq.equals(recId)){
					resultList = valueList;
					System.out.println("UpdateExecutionPlanIndexAction.execute - StepRecord to update: " + resultList);
					break;
				}
			}
			
			request.setAttribute("updateList", resultList);
			request.setAttribute("updateTaskType", resultList.get(1));
		}
		
		request.setAttribute("isNew", "N");
        request.setAttribute("isAdd", "N");
        request.setAttribute("isUpdate", "Y");//This is update flow hence Y
        System.out.println("UpdateExecutionPlanIndexAction.execute - End");
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

