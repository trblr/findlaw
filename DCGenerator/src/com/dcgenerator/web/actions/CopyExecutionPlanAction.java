package com.dcgenerator.web.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * <code>Set welcome message.</code>
 */
public class CopyExecutionPlanAction extends DCGeneratorSupport {
	
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		System.out.println("CopyExecutionPlanAction.execute - Start");
        session = request.getSession();
        
        //Find the work flow name
        String workflowVal = (String)session.getAttribute("workflowValue");
        String appName = (String)session.getAttribute("application");
        if(workflowVal == null)
        	workflowVal="";
        
        List newMainList = (ArrayList)session.getAttribute("stepList" + appName + workflowVal);
        List newCopyList = (ArrayList)session.getAttribute("listForCopy" + appName + workflowVal);
        System.out.println("CopyExecutionPlanAction.execute - Before Copy: " + newMainList);        
		if(newCopyList != null){
			for(Iterator iter = newCopyList.iterator();iter.hasNext();){
				String saveValues = (String)iter.next();				
				StringTokenizer  saveToken = new StringTokenizer(saveValues,",");
				List copyList = new ArrayList();
				while(saveToken.hasMoreTokens())
					copyList.add(saveToken.nextToken());
				
				if("copyYes".equals((String)copyList.get(4))){
					saveValues = (String)copyList.get(0) + "," + (String)copyList.get(1) + "," + request.getParameter("stepName") 
						+ "," + request.getParameter("agent") + ",copyNo," + (String)copyList.get(0);
					for(int i=6; i<copyList.size(); i++)
						saveValues = saveValues + "," + (String)copyList.get(i);
				}
				
				newMainList.add(saveValues);
			}
		}
        
		request.setAttribute("isNew", "Y");//Step Copied successfully, hence new step
        request.setAttribute("isAdd", "N");
        request.setAttribute("isUpdate", "N");
        System.out.println("CopyExecutionPlanAction.execute - After Copy: " + newMainList);
        session.setAttribute("stepList"+appName+workflowVal, newMainList);
        session.setAttribute("listForCopy"+appName+workflowVal, null);
        System.out.println("CopyExecutionPlanAction.execute - End");
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
