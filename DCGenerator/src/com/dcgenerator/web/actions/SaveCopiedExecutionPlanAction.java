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
public class SaveCopiedExecutionPlanAction extends DCGeneratorSupport {
	
	private ServletContext context;
	private HttpServletRequest request;
	
	private String stepName;
	private String agent;
	private HttpSession session;
	private HttpServletResponse response;
	
	
    
	public String execute() throws Exception 
    {
                
        String tasktype = request.getParameter("taskTypeForCopy");
        String stepName = request.getParameter("stepToCopy");
        ArrayList mainlist = new ArrayList();
        session = request.getSession();
        mainlist = (ArrayList)session.getAttribute("stepList");
        System.out.println("mainList:"+mainlist);
        request.setAttribute("copyUp", "N");         
        List resultList = new ArrayList();
		if(!"".equals(mainlist) || null!=mainlist )
		{
			System.out.println("list for save:"+mainlist);
			for(Iterator iter = mainlist.iterator();iter.hasNext();)
			{		
				boolean iscopy = false;			
				String saveValues = (String)iter.next();				
				StringTokenizer  str = new StringTokenizer(saveValues,",");
				List copyList = new ArrayList();
				while(str.hasMoreTokens())
				{
					String value = str.nextToken();
					copyList.add(value);
				}				
				//for(Iterator innerIter = copyList.iterator();innerIter.hasNext();)
				//{
					//String taskType = (String)innerIter.next();
					String stepNameVal = (String)copyList.get(2);
					//String stepNameVal = (String)innerIter.next();
					if(stepNameVal.equals("enter stepname"))
					{	
						iscopy = true;		
						System.out.println("copying the step !");
						
					}
				//}			
					
					
				if(iscopy==true)
				{
					saveValues = copyStep(saveValues, tasktype,getStepName(),getAgent());					
					resultList.add(saveValues);	
				}
				else
				{
					resultList.add(saveValues);
				}
				System.out.println("resultlist after copy:"+resultList);
			}
		}
        
        
        
        
        session.setAttribute("stepList", resultList);
        
        return "success";
    }
	
	public String copyStep(String copyValues,String tasktype,String newStep, String newagent)
	{
		String copyToken=null;
		if(tasktype.equals("deployear"))
        {
        	
        	StringTokenizer  str = new StringTokenizer(copyValues,",");
			List copyList = new ArrayList();
			while(str.hasMoreTokens())
			{
				String value = str.nextToken();
				copyList.add(value);
			}
			String recId = (String)copyList.get(0);
        	String stepName1 = (String)copyList.get(2);
            String agentName = (String)copyList.get(3);
            String earLocation = (String)copyList.get(5);
            String consoleAppName = (String)copyList.get(6);
                               
                      
            copyToken = recId+","+tasktype+","+newStep+","+newagent+","+"copyNo"+","+earLocation+","+consoleAppName;        
            
        }
        else if(tasktype.equals("deletefile"))
        {
        	StringTokenizer  str = new StringTokenizer(copyValues,",");
			List copyList = new ArrayList();
			while(str.hasMoreTokens())
			{
				String value = str.nextToken();
				copyList.add(value);
			}
			String recId = (String)copyList.get(0);
        	String stepName2 = (String)copyList.get(2);
          	String agentName = (String)copyList.get(3);
        	String inputFile =(String)copyList.get(5);
            String inputDir = (String)copyList.get(6);
            String inputFilesetDir = (String)copyList.get(7);
            String inputFilesetincludes = (String)copyList.get(8);
            String inputFilesetExcludes = (String)copyList.get(9);
            String inputVerbose = (String)copyList.get(10);       
           
           
            copyToken = recId+","+tasktype+","+newStep+","+newagent+","+"copyNo"+","+inputFile+","+inputDir+","+inputFilesetDir+","+inputFilesetincludes+","+inputFilesetExcludes+","+inputVerbose;            
                   	
        	
        }  
	return copyToken;
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
	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

}
