package com.dcgenerator.web.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CopyExecutionPlanIndexAction extends DCGeneratorSupport {
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	   
	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		System.out.println("CopyExecutionPlanIndexAction.execute - Start");
        
        String recIdInReq = request.getParameter("recIdInReq");
        System.out.println("CopyExecutionPlanIndexAction.execute - recIdInReq: " + recIdInReq);
        
        //Read which workflow is this
        session = request.getSession();
        String workflowVal = (String)session.getAttribute("workflowValue");
        String appName = (String)session.getAttribute("application");
        if(null==workflowVal)
        	workflowVal="";
        
        List newCopyList = new ArrayList();
        List mainList = (ArrayList)session.getAttribute("stepList"+appName+workflowVal);
        System.out.println("CopyExecutionPlanIndexAction.execute - StepLists: " + mainList);        
		if(mainList != null){
			for(Iterator iter = mainList.iterator();iter.hasNext();){
				String copyValues = (String)iter.next();				
				StringTokenizer  copyToken = new StringTokenizer(copyValues,",");
				List copyList = new ArrayList();
				while(copyToken.hasMoreTokens())
					copyList.add(copyToken.nextToken());
				
				String recId = (String)copyList.get(5);
				if(recIdInReq.equals(recId)){
					copyValues = (mainList.size() + 1) + "";
					for(int i=1; i<4; i++)
						copyValues = copyValues + "," + (String)copyList.get(i);
					copyValues = copyValues + ",copyYes," + (mainList.size() + 1);
					for(int i=6; i<copyList.size(); i++)
						copyValues = copyValues + "," + (String)copyList.get(i);
					newCopyList.add(copyValues);
					break;
				}
			}
		}
        
		request.setAttribute("isNew", "N");//Step added successfully, hence new step
        request.setAttribute("isAdd", "N");//New step flow will execute
        request.setAttribute("isUpdate", "N");
        session.setAttribute("stepList"+appName+workflowVal, mainList);
        session.setAttribute("listForCopy"+appName+workflowVal, newCopyList);
        System.out.println("CopyExecutionPlanIndexAction.execute - End");
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
}
