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

public class ViewStepAction extends DCGeneratorSupport implements ServletContextAware , ServletRequestAware , ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;		
	
    public String execute() throws Exception 
    {    	
        session = request.getSession(true);       
        List<List> list = new ArrayList<List>();
        /*
         *to know which workflow you are in....... 
         */
        
        String workflowVal = (String)session.getAttribute("workflowValue");
        String appName = (String)session.getAttribute("application");
		list = (List)session.getAttribute("stepList"+appName+workflowVal);
		/*List resultList = new ArrayList();
		if(!"".equals(list) || null!=list )
		{
			
			for(Iterator iter = list.iterator();iter.hasNext();)
			{								
				String viewValues = (String)iter.next();				
				StringTokenizer  str = new StringTokenizer(viewValues,",");
				List newList = new ArrayList();
				while(str.hasMoreTokens())
				{
					String value = str.nextToken();
					newList.add(value);
				}	
				
					String step=  (String)newList.get(0);
					if(step.equals(stepId))
					{
						resultList = newList;					
						System.out.println("selected step list:"+resultList);
					}					
			}
		}*/
		//System.out.println("rs:"+resultList);
		//request.setAttribute("stepViewList", resultList);
		//request.setAttribute("task", resultList.get(1));
		request.setAttribute("strStatus","success");
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

