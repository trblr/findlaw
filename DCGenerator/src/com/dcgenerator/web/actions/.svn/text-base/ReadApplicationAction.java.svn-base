package com.dcgenerator.web.actions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class ReadApplicationAction extends DCGeneratorSupport 
{
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	private String applicationName;
	private	static final String SAVE_PATH=System.getenv().get("DC_HOME");
	
	public String execute() throws Exception{
		session = request.getSession();
		
		//Start of the application. Reset all parameters - Start
		try{
			Enumeration sessionVar = session.getAttributeNames();
			while(sessionVar.hasMoreElements())
				session.removeAttribute(sessionVar.nextElement().toString());			
			System.out.println("ReadApplicationAction.execute - Session cleaned");
        }catch(Exception e){
        	System.out.println("ReadApplicationAction.execute - Exception in cleanup: " + e);
        }
		//Start of the application. Reset all parameters - End
		
		try
		{
			File f = new File(ReadApplicationAction.SAVE_PATH + "/results/application.properties");
			if(f.exists()){
		          Properties pro = new Properties();
		          FileInputStream in = new FileInputStream(f);
		          pro.load(in);
		          String appValues = pro.getProperty("applicationNames");
		          System.out.println("applicationNames" + " : " + appValues);

		          StringTokenizer  str = new StringTokenizer(appValues,",");
		          List appList = new ArrayList();
		          int i =0;
		          while(str.hasMoreTokens())
		          {
						String value = str.nextToken();
						appList.add(value);
						if (i==0) {
							session.setAttribute("application", value);
						}
						i++;
		          }
		          System.out.println("application read:"+appList);
		          session.setAttribute("appList", appList);
		        
			}
			else{
		         
		          System.out.println("File not found!");
		          session.removeAttribute("application");
    
			}
		}
		catch(IOException e){
			    System.out.println(e.getMessage());
		}
    
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
