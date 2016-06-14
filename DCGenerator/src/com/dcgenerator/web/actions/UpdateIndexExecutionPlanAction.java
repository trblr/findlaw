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

public class UpdateIndexExecutionPlanAction extends DCGeneratorSupport implements ServletContextAware , ServletRequestAware , ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
    public String execute() throws Exception 
    {
        session = request.getSession(true);       
        
        String stepNameValue = request.getParameter("step");
        System.out.println("step:"+stepNameValue);
        request.setAttribute("isUpdate", "yes");
        List<List> list = new ArrayList<List>();
		list = (List)session.getAttribute("stepList");
		List resultList = new ArrayList();
		if(!"".equals(list) || null!=list )
		{
			System.out.println("list is not null!");
			for(Iterator iter = list.iterator();iter.hasNext();)
			{								
				String deleteValues = (String)iter.next();				
				StringTokenizer  str = new StringTokenizer(deleteValues,",");
				List newList = new ArrayList();
				while(str.hasMoreTokens())
				{
					String value = str.nextToken();
					newList.add(value);
				}				
				//for(Iterator innerIter = newList.iterator();innerIter.hasNext();)
				//{
					
					//String taskType = (String)innerIter.next();
					String stepName = (String)newList.get(2);
					if(stepName.equals(stepNameValue))
					{	
						resultList = newList;
						System.out.println("updating the step name!");
					}				
			}
			request.setAttribute("updateList", resultList);
			request.setAttribute("taskUpdate", resultList.get(1));
		}        
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

