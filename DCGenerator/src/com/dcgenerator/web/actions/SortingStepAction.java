package com.dcgenerator.web.actions;

import java.util.ArrayList;
import java.util.Collections;
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

public class SortingStepAction extends DCGeneratorSupport implements ServletContextAware , ServletRequestAware , ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String[] stepId;	
	
    @SuppressWarnings("unchecked")
	public String execute() throws Exception{
    	System.out.println("SortingStepAction.execute - Start");
    	
    	//Get variables from session
        session = request.getSession();       
        String[] recIds = getStepId();
        String appName = (String)session.getAttribute("application");
        String workflowVal = (String)session.getAttribute("workflowValue");
        if(workflowVal == null)
        	workflowVal = "";
        
        List recList = new ArrayList();
        for(int i=0;i<recIds.length;i++)
        	recList.add((recIds[i].trim()));
        System.out.println("SortingStepAction.execute - New Record IDs: " + recList);
        
        int i=-1;
        List list = (ArrayList)session.getAttribute("stepList"+appName+workflowVal);
		List resultList = new ArrayList();
		System.out.println("SortingStepAction.execute - Before Sorting: " + list);
		if(!"".equals(list) || null!=list ){
			System.out.println("SortingStepAction.execute - list is not null!");
			for(Iterator iter = list.iterator();iter.hasNext();){
				i++;
				String updateValues = (String)iter.next();				
				StringTokenizer  str = new StringTokenizer(updateValues,",");
				List newList = new ArrayList();
				while(str.hasMoreTokens()){
					String value = str.nextToken();
					newList.add(value);
				}
				
				String taskType = (String)newList.get(1);
				updateValues = (String)recList.get(i);
				for(int k=1; k<5; k++)
					updateValues = updateValues + "," + (String)newList.get(k);
				updateValues = updateValues + "," + (String)recList.get(i);
				for(int k=6; k<newList.size(); k++)
					updateValues = updateValues + "," + (String)newList.get(k);
				resultList.add(updateValues);
			}
		}
		
		Collections.sort(resultList);
		System.out.println("SortingStepAction.execute - After Sorting: " + resultList);
        session.setAttribute("stepList"+appName+workflowVal, resultList);
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

	public String[] getStepId() {
		return stepId;
	}

	public void setStepId(String[] stepId) {
		this.stepId = stepId;
	}
}

