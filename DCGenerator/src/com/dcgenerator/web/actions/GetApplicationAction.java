package com.dcgenerator.web.actions;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetApplicationAction extends DCGeneratorSupport 
{
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	public String execute() throws Exception 
	{
		session = request.getSession();
		List applist =new ArrayList();     
        applist = (ArrayList)session.getAttribute("appList");
        if(applist!=null && applist.size()!=0){
		for(Iterator appIter = applist.iterator();appIter.hasNext();)
        {
        	String appName = (String)appIter.next();
				String thisLine;
				String new1 = "";
				try {
					List a = null;
					StringBuffer sb1 = new StringBuffer();
					int i = 1;
					int k = 1;
					String eachLine = "";
					List<String> workList = new ArrayList<String>();
					Set<String> env = new HashSet<String>();
					BufferedReader br = null;
					
					//Fix for Issue 3
					BufferedReader br1 = new BufferedReader(new FileReader(SaveStepsAction.SAVE_PATH + "/results/DCGenerator_"+appName+".properties"));
					while ((eachLine = br1.readLine()) != null) 
					{
						if (eachLine.split("_").length != 1) 
						{					
							env.add(eachLine.split("_")[0]);
						} 
						else 
						{					
							System.out.println("Propert name" + eachLine.split("=")[0]);
							session.setAttribute("propN" + k + appName,eachLine.split("=")[0]);
							System.out.println("Propert value" + eachLine.split("=")[1]);
							session.setAttribute("propV" + k + appName,eachLine.split("=")[1]);	
						}
						k++;
					}
					
					Iterator<String> iterEnv = env.iterator();
					List resultWorkList = new ArrayList();
					while (iterEnv.hasNext()) 
					{
						String vals = iterEnv.next();
						resultWorkList.add(vals);				
					}
					
					String s = "";
					String sa[] = {};
					int stepV=1;
					Iterator<String> iter = env.iterator();
					while (iter.hasNext()) 
					{
						//Fix for Issue 3
						br=	new BufferedReader(new FileReader(SaveStepsAction.SAVE_PATH + "/results/DCGenerator_"+appName+".properties"));
						String environ = iter.next();
						System.out.println("Env value" + environ);
						a = new ArrayList();
						
						i = 1;
						new1="";
						String df = "";
						while (true) 
						{
							System.out.println("Inside  loop");				
							thisLine = br.readLine();
							if (thisLine != null) 
							{							
								if (thisLine.contains(environ+"_")) 
								{
									System.out.println("Inside  loop2");
		
									System.out.println("The this line" + thisLine);
									if (thisLine.contains("step" + i)) 
									{
										System.out.println("inside " + i);
										new1 = new1 + (thisLine.split("=")[1]);
										new1 = new1 + ",";
										System.out.println("added string:" + new1);
									} else {
										System.out.println("In else");
										if (new1.length() != 0) {
											s = i
													+ ","
													+ new1.substring(0,
															new1.length() - 1);
											sa = s.split(",", 5);
		
											for (int g = 0; g < sa.length; g++) {
												if (g == 4) {
													df = df + "CopyNo" + "," + i + "," + sa[g];
												} else {
													df = df + sa[g] + ",";
												}
		
											}
											System.out.println("Adding to list"+df);
											a.add(df);
											System.out.println("Size of list"+a.size());
											df = "";
											i++;
											new1 = thisLine.split("=")[1];
											new1 = new1 + ",";
										}
									}							
								}						
								
							} else {
								
								if (new1.length() != 0) 
								{
									s = i + "," + new1.substring(0, new1.length() - 1);
									sa = s.split(",", 5);							
									for (int t = 0; t < sa.length; t++) 
									{
										if (t ==4) 
										{
											df = df + "CopyNo" + "," + i + "," + sa[t];
										} else 
										{
											df = df + sa[t] + ",";
										}
									}						
									a.add(df);							
								}
								break;
							}
						}
						
						br=null;
						if (thisLine != null) 
						{
							new1 = thisLine.split("=")[1];
							new1 = new1 + ",";
						}
						//System.out.println(environ + a.get(0));
						// System.out.println("kknjaa:" + a.get(1));
						
						session.setAttribute("stepList"+appName+stepV,a);
						System.out.println("session list:"+stepV);
						session.setAttribute("workflowValue", "1");
						session.setAttribute("workflowList"+appName, resultWorkList);
						stepV++;
					}
					
					
		
				} 
				catch (IOException e) 
				{
					System.err.println("Error: " + e);
				}
				request.setAttribute("copyUp", "N");    
        	}
        }
	
		return "success";
	}

	public void setServletContext(ServletContext context) 
	{
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) 
	{
		this.response = response;
	}
}

