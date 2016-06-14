package com.dcgenerator.web.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dcgenerator.web.data.DCTaskDO;
import com.dcgenerator.web.data.DCUserInputFieldDO;
import com.dcgenerator.web.utils.DCReadConfigXML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Iterator;

public class SaveStepsAction extends DCGeneratorSupport{
	
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	private HttpServletRequest request;	
	private HttpServletResponse response;
	private HttpSession session;
	
	public	static final String SAVE_PATH=System.getenv().get("DC_HOME");
	FileOutputStream fo = null;
	
    @SuppressWarnings("unchecked")
	public String execute() throws Exception{
    	System.out.println("SaveStepsAction.execute - Start");
        session = request.getSession();
        
        //Get all the application name list from the session
		List appNameList = (ArrayList)session.getAttribute("appList");
        
        //Initialize and create appName buffer object
		int	appNameCount = 0;
        StringBuffer appNameBuffer = new StringBuffer("applicationNames=");
        for(Iterator appNameItr=appNameList.iterator(); appNameItr.hasNext();){
        	appNameBuffer.append(appNameItr.next());
        	appNameCount++;
        	if(appNameCount != appNameList.size())
        		appNameBuffer.append(",");
        }
        
        //Issue 1 Fix - Start
        //Check if result directory exist or not, if not create it
        try{
        	File checkDir = new File(SaveStepsAction.SAVE_PATH + "/results");
        	if(!checkDir.exists())
        		checkDir.mkdir();
        }catch(Exception e){
        	System.out.println("SaveStepsAction.execute - Exception(CreateDir): " + e);
        }
        //Issue 1 Fix - End
        
        //Write appName buffer object to the system
        try{
        	BufferedWriter appNameWriter = new BufferedWriter(new FileWriter(SaveStepsAction.SAVE_PATH + 
        			"/results/application.properties"));
        	appNameWriter.write(appNameBuffer.toString());
        	appNameWriter.flush();
        	appNameWriter.close();
        	System.out.println("SaveStepsAction.execute - (AppName) write successful.");
        }catch(Exception e){
        	System.out.println("SaveStepsAction.execute - Exception(AppNames): " + e);
        }
        
        //For each appName start saving the property
        String p = "", v = "";
        ArrayList mainList = null;
        String allSteps = (String)request.getParameter("Allsteps");
        for(Iterator appNameItr=appNameList.iterator(); appNameItr.hasNext();){
        	String appName = (String)appNameItr.next();
        	StringBuffer appPropBuffer = new StringBuffer();
        	
        	//Set all the properties from screen
        	for(int i=1; i<11; i++){
        		p = (String)session.getAttribute("propN" + i + appName);
        		v = (String)session.getAttribute("propV" + i + appName);
        		System.out.println("propertyName" + i + ": " + p + ", propertyValue" + i + ": " + v);
        		if(v == null)
        			v = "";
        		if(p != null && !"".equals(p))
        			appPropBuffer.append(p + "=" + v + "\n");
        	}
        	
        	//Save workflows
        	List workflowList = (ArrayList)session.getAttribute("workflowList"+appName);
        	if(workflowList != null && workflowList.size() > 0){
        		if("Y".equals(allSteps)){
        			request.setAttribute("newFlow", "Y");
        			for(int flowList = 1;flowList<=workflowList.size();flowList++){
        				mainList = (ArrayList)session.getAttribute("stepList"+appName+flowList);
			        	String workflowval = (String)workflowList.get(flowList-1);
			        	appPropBuffer = saveAll(workflowval, mainList, appPropBuffer, session, appName);
			        }
        		}else{
        			String workflowVal = (String)session.getAttribute("workflowValue");  
        			mainList = (ArrayList)session.getAttribute("stepList"+appName+workflowVal);
		        	String workflowval = (String)workflowList.get(Integer.parseInt(workflowVal)-1);        	
		        	appPropBuffer = saveAll(workflowval, mainList, appPropBuffer, session, appName);
        		}
        		
        		try{
        			BufferedWriter appPropWriter = new BufferedWriter(new FileWriter(SaveStepsAction.SAVE_PATH + 
        					"/results/DCGenerator_" + appName + ".properties"));
        			appPropWriter.write(appPropBuffer.toString());
        			appPropWriter.flush();
        			appPropWriter.close();
                	System.out.println("SaveStepsAction.execute - (AppName) write successful.");
        		}catch(Exception e){
        		}
        	}
        }
        
        return SUCCESS;
    }
    
    @SuppressWarnings("unchecked")
	public StringBuffer saveAll(String workflowval,List mainlist,StringBuffer buffer,HttpSession session,String appName){
    	DCTaskDO taskDO = null;
		DCReadConfigXML readConfigXML = new DCReadConfigXML();
		Vector taskList = readConfigXML.getTaskList();
		
    	String	stepNum = "";
        if(mainlist!=null && mainlist.size()!=0){
       		for(Iterator iter = mainlist.iterator();iter.hasNext();){
   				String saveValues = (String)iter.next();				
				StringTokenizer  str = new StringTokenizer(saveValues,",");
				List newList = new ArrayList();
				while(str.hasMoreTokens()){
					String value = str.nextToken();
					newList.add(value);
				}
				
				//We have the step broken into tokens, get the correct task DO from the session
				String taskType = (String)newList.get(1);
				for(int tl=0; tl<taskList.size(); tl++){
					taskDO = (DCTaskDO)taskList.get(tl);
					if(taskType.equals(taskDO.getTaskName()))
						break;
				}
				
				//Got the task, start populating buffer
				//This is common to all
				stepNum = (String)newList.get(0);
				buffer.append(workflowval + "_step" + stepNum + ".taskType=" + (String)newList.get(1) + "\n");
				buffer.append(workflowval + "_step" + stepNum + ".stepName=" + (String)newList.get(2) + "\n");
				buffer.append(workflowval + "_step" + stepNum + ".agentName=" + (String)newList.get(3) + "\n");
				
				//This is task specific
				DCUserInputFieldDO userInputDO = null;
				Vector inputDOList = taskDO.getTaskUserInputList();
				for(int il=0; il<inputDOList.size(); il++){
					userInputDO = (DCUserInputFieldDO)inputDOList.get(il);
					buffer.append(workflowval + "_step" + stepNum + "." + userInputDO.getFieldId() + "=" + 
							validateWithProperties(session, (String)newList.get(6 + il), appName) + "\n");
				}
       		}
        }
				
    	return buffer;
    }
    
    private String validateWithProperties(HttpSession session, String validateValue, String appName){
    	String propN = "";
    	for(int i=1; i<11; i++){
    		propN = (String)session.getAttribute("propN" + i + appName);
    		if(propN == null)
    			propN = "";
    		if(propN.equals(validateValue))
    			validateValue = "&{" + propN + "}";
    		
    	}
    	
    	return validateValue;
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


