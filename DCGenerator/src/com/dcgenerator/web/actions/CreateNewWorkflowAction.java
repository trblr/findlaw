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

public class CreateNewWorkflowAction extends DCGeneratorSupport implements ServletContextAware , ServletRequestAware , ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;	
	private String workflowName;
	private String copyPlans;
	
    public String execute() throws Exception 
    {     	
        session = request.getSession(true);  
        System.out.println("CreateNewWorkflow action");
        List workflowList = new ArrayList();
        String appName = (String)session.getAttribute("application");
        workflowList = (List)session.getAttribute("workflowList"+appName);
        
		if("".equals(workflowList) || null == workflowList)
        {
			workflowList = new ArrayList();
        } 
		
		workflowList.add(getWorkflowName());
		//System.out.println("setting the workflow while creating:"+Integer.toString(workflowList.size()));
		
		session.setAttribute("workflowValue", Integer.toString(workflowList.size()));
		System.out.println("copyplans:"+getCopyPlans());
		session.setAttribute("workflowList"+appName, workflowList);
		
		int iterInc = 0;
		String copyVal = "";
		if(!"-1".equals(getCopyPlans()) && null!=getCopyPlans())
		{
			for(Iterator iter = workflowList.iterator(); iter.hasNext();)
			{
				iterInc++;
				String val = (String)iter.next();
				String actVal  = getCopyPlans();
				if(actVal.equals(val))
				{
					System.out.println("in if loop create new:"+val);
					copyVal = Integer.toString(iterInc);
					System.out.println(copyVal);
					break;
				}
				
			}
    	}
		if(!"".equals(copyVal)){
			//System.out.println("setting the values!!");
			List newWorkFlowSteps = new ArrayList();
			List stepList = (ArrayList)session.getAttribute("stepList"+appName+copyVal);
			if(stepList != null){
				for(int i=0; i<stepList.size(); i++)
					newWorkFlowSteps.add(stepList.get(i));
			}
			
			System.out.println("shoban:"+copyVal);
			System.out.println("shoban1:"+Integer.toString(workflowList.size()));
			session.setAttribute("stepList"+appName+Integer.toString(workflowList.size()),newWorkFlowSteps);
			request.setAttribute("copyUp", "N");
		}
		
		//request.setAttribute("copyUp", "N");
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
	public String getWorkflowName() {
		return workflowName;
	}


	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}




	public String getCopyPlans() {
		return copyPlans;
	}


	public void setCopyPlans(String copyPlans) {
		this.copyPlans = copyPlans;
	}


 
}

