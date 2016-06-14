package com.dcgenerator.web.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dcgenerator.web.data.DCTaskDO;
import com.dcgenerator.web.data.DCUserInputFieldDO;
import com.dcgenerator.web.utils.DCReadConfigXML;
/**
 * <code>Set welcome message.</code>
 */
@SuppressWarnings("serial")
public class AddExecutionPlanAction extends DCGeneratorSupport {
	
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;

	@SuppressWarnings("unchecked")
	/**
	 * Method will execute web request
	 */
	public String execute() throws Exception{
		System.out.println("AddExecutionPlanAction.execute - Start");
		session = request.getSession();        
		String workflowVal = (String)session.getAttribute("workflowValue");
		String appName = (String)session.getAttribute("application");
		System.out.println("AddExecutionPlanAction.execute - WorkFlow: " + workflowVal);
		
		//Set properties into session
		for(int i=1; i<11; i++)
			session.setAttribute("propN" + i + appName, (String)request.getParameter("propertyName" + i));
		
		//Get the previous records, if any, into local variable and set record ID variable
		String recId = "1";
		List newList = (ArrayList)session.getAttribute("stepList" + appName + workflowVal);
		if(newList == null || newList.size() == 0)
			newList = new ArrayList<String>();
		else
			recId=Integer.toString(newList.size()+1);
		
		//Get taskType and addNowStep variable from request
		request.setAttribute("isNew", "N");
        request.setAttribute("isAdd", "Y");//This is add flow hence Y
        request.setAttribute("isUpdate", "N");
        String taskType = request.getParameter("taskType");
        String addNowStep = request.getParameter("addNowStep");        
        if("Y".equals(addNowStep)){
        	if(isNotValid(request.getParameter("stepName"), "Enter Step Name"))
        		addActionError(getText("StepName is missing"));
        	
        	if(isNotValid(request.getParameter("agent"), "Enter Host Name"))
        		addActionError(getText("HostName is missing"));
        	
        	try{
        		String addStep = recId + "," + taskType + "," + request.getParameter("stepName") + "," + 
        			request.getParameter("agent") + ",copyNo" + "," + recId;
        		DCTaskDO taskDO = null;
				DCReadConfigXML readConfigXML = new DCReadConfigXML();
				Vector taskList = readConfigXML.getTaskList();
				for(int tl=0; tl<taskList.size(); tl++){
					taskDO = (DCTaskDO)taskList.get(tl);
					if(taskType.equals(taskDO.getTaskName()))
						break;
				}
				
				DCUserInputFieldDO userInputDO = null;
				Vector taskInputList = taskDO.getTaskUserInputList();
				for(int il=0; il<taskInputList.size(); il++){
					userInputDO = (DCUserInputFieldDO)taskInputList.get(il);
					if(userInputDO.getFieldRequired())
						if(isNotValid(request.getParameter(userInputDO.getFieldId()), ""))
							addActionError(getText(userInputDO.getFieldLabel() + " is missing"));
					if("checkbox".equals(userInputDO.getFieldType())){
						if("on".equals(request.getParameter(userInputDO.getFieldId())))
							addStep = addStep + ",Y";
						else
							addStep = addStep + ",N";
					}else{
						if(request.getParameter(userInputDO.getFieldId()) == null || 
								"".equals(request.getParameter(userInputDO.getFieldId())))
							addStep = addStep + ", ";
						else
							addStep = addStep + "," + request.getParameter(userInputDO.getFieldId());
					}
				}
				
				if(getActionErrors().size() > 0){
					System.out.println("AddExecutionPlanAction.execute - End(Input)");
					return "input";
				}
				
				System.out.println("AddExecutionPlanAction.execute - Adding: " + addStep);
				newList.add(addStep);
        	}catch(Exception e){
        		System.out.println("AddExecutionPlanAction.execute - Exception: " + e);
        	}
        }
        	
        System.out.println("AddExecutionPlanAction.execute - New List: " + newList);
        request.setAttribute("isNew", "Y");//Step added successfully, hence new step
        request.setAttribute("isAdd", "N");//New step flow will execute
        request.setAttribute("isUpdate", "N");
        session.setAttribute("stepList" + appName + workflowVal, newList);        
        System.out.println("AddExecutionPlanAction.execute - End");
        return "success";
    }
	
	/**
	 * Method will check if the input value is not null and not blank and not equal to invalid value
	 * @param value, inValidValue
	 * @return
	 */
	private boolean isNotValid(String value, String inValidValue){
		if(value == null || "".equals(value) || inValidValue.equals(value))
			return true;
		return false;
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
