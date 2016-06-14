package com.dcgenerator.web.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.dcgenerator.web.data.DCTaskDO;
import com.dcgenerator.web.data.DCUserInputFieldDO;
import com.dcgenerator.web.utils.DCReadConfigXML;

import java.util.Iterator;

public class UpdateExecutionPlanAction extends DCGeneratorSupport implements ServletContextAware , ServletRequestAware , ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;	
	
    @SuppressWarnings("unchecked")
	public String execute() throws Exception{
    	System.out.println("UpdateExecutionPlanAction.execute - Start");
        session = request.getSession();
        
        String workflowVal = (String)session.getAttribute("workflowValue");
        String appName = (String)session.getAttribute("application");
        String recIdInReq = request.getParameter("recIdInReq");
        String taskType = request.getParameter("taskType");
        System.out.println("UpdateExecutionPlanAction.execute - stepIdForUpdate: " + recIdInReq);
        
        //Set request attributes for update action
        request.setAttribute("isNew", "N");
        request.setAttribute("isAdd", "N");
        request.setAttribute("isUpdate", "Y");//This is update flow hence Y
        
        //Initialize step list
        List originalList = (ArrayList)session.getAttribute("stepList"+appName+workflowVal);
		List updatedList = new ArrayList();
		
		//Populate the task list from the XML object
		DCTaskDO taskDO = null;
		DCReadConfigXML readConfigXML = new DCReadConfigXML();
		Vector taskList = readConfigXML.getTaskList();
		
		//boolean isValid = true;
		for(Iterator taskIter=originalList.iterator(); taskIter.hasNext();){
			String originalValues = (String)taskIter.next();
			StringTokenizer originalStr = new StringTokenizer(originalValues, ",");
			List newList = new ArrayList();
			while(originalStr.hasMoreTokens()){
				newList.add(originalStr.nextToken());
			}
			
			String recIdToUpdate = (String)newList.get(5);
			if(recIdToUpdate.equals(recIdInReq)){
	        	originalValues = recIdToUpdate + "," + taskType + "," + request.getParameter("stepName") + "," 
					+ request.getParameter("agent") + ",copyNo," + recIdToUpdate;
				for(int tl=0; tl<taskList.size(); tl++){
					taskDO = (DCTaskDO)taskList.get(tl);
					if(taskType.equals(taskDO.getTaskName()))
						break;
				}
				
				DCUserInputFieldDO inputFieldDO = null;
				Vector userInputList = taskDO.getTaskUserInputList();
				for(int il=0; il<userInputList.size(); il++){
					inputFieldDO = (DCUserInputFieldDO)userInputList.get(il);
					if("checkbox".equals(inputFieldDO.getFieldType())){
						if("on".equals(request.getParameter(inputFieldDO.getFieldId())))
							originalValues = originalValues + ",Y";
						else
							originalValues = originalValues + ",N";
					}else{
						if(request.getParameter(inputFieldDO.getFieldId()) == null || 
								"".equals(request.getParameter(inputFieldDO.getFieldId())))
							originalValues = originalValues + ", ";
						else
							originalValues = originalValues + "," + request.getParameter(inputFieldDO.getFieldId());
					}
				}
			}
			
			updatedList.add(originalValues);
		}
		
		System.out.println("UpdateExecutionPlanAction.execute - UpdatedList: " + updatedList);
		session.setAttribute("stepList"+appName+workflowVal, updatedList);
		
		request.setAttribute("isNew", "Y");//New entry fields should come
		request.setAttribute("isUpdate", "N");//Update flow is done hence N
		request.setAttribute("isAdd", "N");
        System.out.println("UpdateExecutionPlanAction.execute - End");
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

