<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*"%>
<%@page import="java.lang.Exception"%>
<%@page import="com.dcgenerator.web.utils.*"%>
<%@page import="com.dcgenerator.web.data.*"%>

<html>
<head>
<title>Environment Creator</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/main.css" />
<script language="JavaScript" src="<%=request.getContextPath()%>/js/SDP_Common.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/general.css" />
<script src="<%=request.getContextPath()%>/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/editPopup.js" type="text/javascript"></script>
<!--<script src="<%=request.getContextPath()%>/js/popup.js" type="text/javascript"></script>-->


<%String strStatus = (String)request.getAttribute("strStatus"); %>

<script type="text/javascript" language="javascript" >


function addMoreDetailsToStep()
{
	//alert("adding more details to ur step");
	document.ExecutionPlanForm.action = "addStepDetails.action";
	document.ExecutionPlanForm.submit();
	
}
function addStepNow(tasktype)
{
	//alert("adding one step"+tasktype);
	document.ExecutionPlanForm.action = "addStep.action?taskType="+tasktype+"&addNowStep=Y";
	document.ExecutionPlanForm.submit();
	
}

function deleteStep(recId)
{
	if(confirm("Are you sure that you want to delete the selected step!"))
	{
		//alert(stepValue);
		document.ExecutionPlanForm.action = "deleteStep.action?" + "recIdInReq="+recId;
		document.ExecutionPlanForm.submit();
	}
}
function updateStepIndex(recId)
{
	//if(confirm("Are you sure that you want to update the selected step!"))
	//{
		//alert(stepValue);
		document.ExecutionPlanForm.action = "updateStepIndex.action?recIdInReq="+recId;
		document.ExecutionPlanForm.submit();
	//}	
}
function updateDetails(recId,taskType)
{
	//if(confirm("Are you sure that you want to update the selected step!"))
	//{		
		document.ExecutionPlanForm.action = "updateStep.action?" + "recIdInReq="+recId+"&taskType="+taskType;
		document.ExecutionPlanForm.submit();
	//}	
}


function saveAllSteps()
{
	document.ExecutionPlanForm.action = "saveSteps.action";
	document.ExecutionPlanForm.submit();
	
}

function copyStepName(recId,taskType)
{
	document.ExecutionPlanForm.action = "copyStep.action?" + "recIdInReq="+recId+"&taskTypeForCopy="+taskType;
	document.ExecutionPlanForm.submit();
}
function saveCopiedStep(stepName,taskType)
{
	document.ExecutionPlanForm.action = "saveCopiedStep.action?" + "stepToCopy="+stepName+"&taskTypeForCopy="+taskType;
	document.ExecutionPlanForm.submit();
	
}


function changeStepId()
{
		
	document.ExecutionPlanForm.action = "sortStep.action";
	document.ExecutionPlanForm.submit();
}

//Update for change - Start
function viewStepDetails(){
	//request data for centering
	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#viewStepDetailsDIV").height();
	var popupWidth = $("#viewStepDetailsDIV").width();
	//centering
	$("#viewStepDetailsDIV").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	
	document.getElementById("viewStepDetailsDIV").style.visibility='visible';
}

function hideViewDetailsDIV(){
	document.getElementById("viewStepDetailsDIV").style.visibility='hidden';
}
//Update for change - End

function setDataVars()
{
	    centerPopup();
		loadPopup();
}			
function fnDeleteCancel()
{
		disablePopup();
}


function init()
{
}

function fnDeleteCancel()
{
	disableEditPopup();
}			

function createNewWorkflowAction()
{
	var postUrl = "createWorkflow.action";	
	document.ExecutionPlanForm.action = postUrl;
	document.ExecutionPlanForm.submit();
}

function clearFlows()
{
	document.ExecutionPlanForm.workflowName.value = "";
	document.ExecutionPlanForm.copyPlans.value = "-1";
	
}

function saveAllStepsForAllWorkFlows()
{	
	document.ExecutionPlanForm.action = "saveSteps.action?Allsteps=Y";
	document.ExecutionPlanForm.submit();
}
function saveProperties()
{	
	document.ExecutionPlanForm.action = "addIndex.action";
	document.ExecutionPlanForm.submit();
}
function createApplication()
{	
	document.ExecutionPlanForm.action = "createApplication.action";

	document.ExecutionPlanForm.submit();
}

	
</script>
</head>
<body onload="javascript:init();">
<s:form name="ExecutionPlanForm" theme="simple" >

	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td> 
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<div class="logo"><img src="<%=request.getContextPath()%>/images/logos-web.jpg" width="144" height="62" /></div>
						</td>
						<td>
							<div class="title"><img src="<%=request.getContextPath()%>/images/title.jpg" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" bgcolor="#000000"><img src="<%=request.getContextPath()%>/images/spacer.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td>
				<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="leftnav" width="146" valign="top">
						<div class="leftnav">
							<font size="2"><div class="activeappitem"><a href="<%=request.getContextPath()%>/jsp/welcome.jsp" style="text-decoration: none;"><%out.println("Home");%></a></div></font>
							<font size="2"><b>My Applications</b></font>
							<%String appName = (String)session.getAttribute("application");
							List appList = new ArrayList();
								appList = (List)session.getAttribute("appList"); 
								if(appList!=null && appList.size()!=0)
								{%>
								
									<%
									int i=0;
									for(Iterator iterApp = appList.iterator();iterApp.hasNext();)
									{ 				
									
										String newapp = (String)iterApp.next();
										if(newapp.equals(appName))
										{%>
										<div class="activeappitem"><a href="<%=request.getContextPath()%>/setApplication.action?appVal=<%=newapp%>" style="text-decoration: none;"><%=newapp%></a></div>
										<%}else
										{%>
										<div class="appitem"><a href="<%=request.getContextPath()%>/setApplication.action?appVal=<%=newapp%>" style="text-decoration: none;"><%=newapp%></a></div>
										<%} %>
									<%}
								}%>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<b><font color="blue"><b>CREATE APPS</b></font></b>
							<br>
							<b>Enter App Name</b><br>
							<b><input type="text" name="applicationName" size="22"></b><br><br>
							<b>Select App To Copy</b><br>
							<b>
								<select name="copyApps">
									<option value="-1">Please select</option>
									<%if(appList!=null && appList.size()!=0){
											for(Iterator iterApp = appList.iterator();iterApp.hasNext();){
												String copyAppName = (String)iterApp.next();
										%>
												<option value="<%=copyAppName%>"><%=copyAppName%></option>
										<%}%>
									<%}%>
								</select>
							</b><br><br>
							<b><input type="button" value="SUBMIT" onclick="javascript:createApplication();"></b><br>
						</div>
					</td>
					<td width="1"><div class="separator"><img src="<%=request.getContextPath()%>/images/spacer.gif" width="1" height="700" /></div></td>
					<td valign="top">
						<%String successString = (String)request.getAttribute("successString");
						if("Y".equals(successString))
						{%>
						<center><table class="workflownav" border="0" cellpadding="0" cellspacing="0">
								<tr>
								<td class="appitem"><font size="2" color="#CC0000"><b><%out.println("Property file is generated successfully");%></b></font>
								</td>
								</tr>
						</table></center>
						
							
						<%} %>
						<%if(appList!=null && appList.size()!=0 && !"Y".equals(successString)){ %>
							<table class="workflownav" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<%	List workflowList = new ArrayList();
											String workflowVal = (String)session.getAttribute("workflowValue");
											
								        	workflowList = (List)session.getAttribute("workflowList"+appName);
								        	int workflowNumber = 0;
											if(workflowList!=null && workflowList.size()!=0)
								        	{
												for(Iterator iterWork = workflowList.iterator();iterWork.hasNext();)
												{ 
													workflowNumber++;
													String newWorkFlow = (String)iterWork.next();
												if(workflowVal.equals(Integer.toString(workflowNumber)) && !"Y".equals(request.getAttribute("newFlow")))
															{
															%>
											<td class="activeworkflowtab"><a href="<%=request.getContextPath()%>/jsp/setWorkflow.action?workflowNum=<%=Integer.toString(workflowNumber)%>" style="text-decoration: none;"><%=newWorkFlow%></a></td>							
											
													<%}else{ %>
											<td class="inactiveworkflowtab"><span class="inactivelink"><a href="<%=request.getContextPath()%>/jsp/setWorkflow.action?workflowNum=<%=Integer.toString(workflowNumber)%>" style="text-decoration: none;"><%=newWorkFlow%></a></span></td>
											
													<%}
												}
											}%>
										<%if("Y".equals(request.getAttribute("newFlow"))) { %>
										<td class="activeworkflowtab"><a href="<%=request.getContextPath()%>/jsp/createWorkflowIndex.action" style="text-decoration: none;"> Create New Env/Complete Property File</a></td>
										<%}else{ %>
										<td class="inactiveworkflowtab"><span class="inactivelink"><a href="<%=request.getContextPath()%>/jsp/createWorkflowIndex.action" > Create New Env/Complete Property File</a></span></td>
										<%} %>
								</tr>
							</table> 
							<div class="maincontent">
								<%if(("Y".equals(request.getAttribute("newFlow")) || workflowList==null || workflowList.size()==0 )){ %>
									<table class="section" border="0" cellspacing="0" cellpadding="0" width="700">
										<tr>
											<td class="sectiontitle" colspan="2" width="100%"><div class="sectiontitle">Enter Data</div></td>
										</tr>
										<tr>
											<td> Enter Env Name for prop File(required)</td>
											<td ><input size="40" type="text" name="workflowName"/></td>
										</tr>
										<tr>
											<td> Select the Env to Copy</td>
											<td><%	List flowList = new ArrayList();
													flowList = (ArrayList)session.getAttribute("workflowList"+appName);
													 %>
													<select name="copyPlans">
														<option value="-1">Please select</option>
														<%if(flowList!=null && flowList.size()!=0)
														{	
															for(Iterator iter =flowList.iterator();iter.hasNext();)
														    {
																String val = (String)iter.next();%>
														<option value="<%=val%>"><%=val%></option>
														<%} }%>	
													</select>											
											</td>
										</tr>							
										<tr>
											<td><input type="button" class="submitbutton" value="Submit" onclick="javascript:createNewWorkflowAction()"/></td>
											<td><input type="button" class="submitbutton" value="Clear" onclick="javascript:clearFlows()"/>
											</td>
										</tr>			
										
									</table>
									<table class="section" border="0" cellspacing="0" cellpadding="0" width="700">
										<tr>
											<td class="sectiontitle" colspan="2" width="100%"><div class="sectiontitle">Complete Property File Generation</div></td>
										</tr>
										<tr>
											<td align="center"><span class="datalabel">
												<input type="button" class="submitbutton" value="Generate Property File" onclick="javascript:saveAllStepsForAllWorkFlows();" />
											</span></td>										
										</tr>
									</table>
									<%}%>
		   							<!-- New workFlow Step - Start -->
										<%
											if(workflowList!=null && workflowList.size()!=0 && !"Y".equals(request.getAttribute("newFlow"))){
										%>
												<!-- Properties - Start -->
												<table class="section" border="0" cellspacing="0" cellpadding="0" width="700">
													<tr><td class="sectiontitle" colspan="3"><div class="sectiontitle">Properties</div></td></tr>
													<tr>
														<td width="325" align="left" class="tableheader">Name</td>
														<td width="225" align="left" class="tableheader">Value</td>
														<td align="left" class="tableheader"></td>
		   										</tr>
										<%
												String p = "", v = "";
												for(int i=1; i<11; i++){
													p = (String)session.getAttribute("propN" + i + appName);
													if("".equals(p) || null == p)
														p = "";
													
													v = (String)session.getAttribute("propV" + i + appName);
													if("".equals(v) || null == v)
														v = "";
										%>
													<tr>
														<td><span class="datalabel"><input size="40" type="text" name="propertyName<%=i%>" value="<%=p%>" /></span></td>
														<td><input size="40" type="text" name="propertyValue<%=i%>" value="<%=v%>" /></td>
													</tr>
											<%}%>
										
													<tr>
														<td colspan="3"><span class="datalabel"><input type="button" class="submitbutton" value="Save Properties" onclick="javascript:saveProperties();" /></span></td>
													</tr>
												</table>
												<!-- Properties - End -->
												<!-- Steps - Start -->
												<table class="section" border="0" cellspacing="0" cellpadding="0" width="700">
													<tr><td class="sectiontitle" colspan="6"><div class="sectiontitle">Execution Plan</div></td></tr>
													<tr><td colspan="6"><s:actionerror/></td></tr>
													<tr>
														<td>&nbsp;</td>
														<td class="tableheader">Step</td>
														<td class="tableheader">Name</td>
														<td class="tableheader">Task Type</td>
														<td class="tableheader">HostName</td>
														<td>&nbsp;</td>
													</tr>
													<!-- List Steps - Start -->
													<%
														if(workflowVal == null)
								        			workflowVal="";
								        		
														List list = (List)session.getAttribute("stepList" + appName + workflowVal);
														if(list!=null && list.size()!=0){
															String taskType =null;
															String stepName=null;
															String agentName=null;
															for(Iterator iter = list.iterator();iter.hasNext();){
																String displayValues = (String)iter.next();
																StringTokenizer  srt = new StringTokenizer(displayValues,",");
																List newList = new ArrayList();
																while(srt.hasMoreTokens()){
																	String value = srt.nextToken();
																	newList.add(value);
																}
													%>
																<tr>
																	<td>&nbsp;</td>
																	<td>
																		<select name="stepId" value="<%=newList.get(0)%>">
																			<%
																				for(int i=1;i<=list.size();i++){
																					if((i + "").equals(newList.get(0))){
																					%>
																						<option selected><%=i%></option>
																					<%}else{%>
																						<option><%=i%></option>
																					<%}%>
																			<%}%>
																		</select>
																	</td>
																		<td><%=newList.get(2)%></td>
																		<td><%=newList.get(1)%></td>
																		<td><%=newList.get(3)%></td>
																	<td>
																		<span class="icon"><img src="<%=request.getContextPath()%>/images/edit.gif" height="16" width="16" alt="edit" onclick="javascript:updateStepIndex('<%=newList.get(5)%>')"/></span>
																		<span class="icon"><img src="<%=request.getContextPath()%>/images/icon_delete.gif" height="16" width="16" alt="delete" onclick="javascript:deleteStep('<%=newList.get(5)%>')"/></span>
																		<span class="icon"><img src="<%=request.getContextPath()%>/images/copy_image.gif" height="16" width="16" alt="copy" onclick="javascript:copyStepName('<%=newList.get(5)%>','<%=newList.get(1)%>')"/></span>
																	</td>
																</tr>
													<%
															}
														}
														
														if(request.getAttribute("isNew") != null && "N".equals(request.getAttribute("isNew"))){
															List newCopyList = (List)session.getAttribute("listForCopy" + appName + workflowVal);
															if(newCopyList != null && newCopyList.size() > 0){
																for(Iterator iter = newCopyList.iterator();iter.hasNext();){
																	String displayValues = (String)iter.next();
																	StringTokenizer  srt = new StringTokenizer(displayValues,",");
																	List newList = new ArrayList();
																	while(srt.hasMoreTokens()){
																		String value = srt.nextToken();
																		newList.add(value);
																	}
																%>
																	<tr>
																		<td>&nbsp;</td>
																		<td><%=newList.get(0)%></td>
																		<td><input type="text" name="stepName" value="<%=newList.get(2)%>"/></td>
																		<td><%=newList.get(1)%></td>
																		<td><input type="text" name="agent" value="<%=newList.get(3)%>"/></td>
																		<td>
																			<input type="button" class="submitbutton" value="Save" onclick="javascript:saveCopiedStep('<%=newList.get(2)%>','<%=newList.get(1)%>')"/>
																		</td>
																	</tr>
																<%
																}
															}
														}else{
															session.setAttribute("listForCopy"+appName+workflowVal, null);
														}
													%>
													<!-- List Steps - End -->
													
													<!-- New Steps - Start -->
													<%if(request.getAttribute("isNew") == null || "Y".equals(request.getAttribute("isNew"))){%>
															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td class="addstepline"><input type="text" name="stepNameDum" value="Enter Step Name"/></td>
																<td class="addstepline">
																	<select name="taskType">
																		<%
																			try{
																				DCTaskDO taskDO = null;
																				DCReadConfigXML readConfigXML = new DCReadConfigXML();
																				Vector taskList = readConfigXML.getTaskList();
																				for(int tl=0; tl<taskList.size(); tl++){
																					taskDO = (DCTaskDO)taskList.get(tl);
																		%>
																					<option value="<%=taskDO.getTaskName()%>"><%=taskDO.getTaskLabel()%></option>
																		<%
																				}
																			}catch(Exception e){
																				System.out.println("index.jsp - Exception(New Step): " + e);
																			}
																		%>
																	</select>
																</td>
																<td class="addstepline"><input type="text" name="agentDum" value="Enter Host Name"/></td>
																<td>
																	<span class="icon"><input type="image" src="<%=request.getContextPath()%>/images/next.png" height="16" width="16" name="addstep" value="true" alt="Add Step" onclick="javascript:addMoreDetailsToStep();"/></span>
																</td>
															</tr>
															<%if(list!=null && list.size()!=0){%>
																<tr>
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="submitbutton" value="View Steps" onclick="javascript:viewStepDetails();" /></td>
																	<td><input type="button" class="submitbutton" value="Save Sorting" onclick="javascript:changeStepId();" /></td>										
																	<td><input type="button" class="submitbutton" value="Generate File" onclick="javascript:saveAllSteps();" /></td>
																</tr>
															<%}%>
													<%}%>
													<!-- New Steps - End -->
													<!-- Add Steps - Start -->
													<%
														if(request.getAttribute("isAdd") != null && "Y".equals(request.getAttribute("isAdd"))){
															String taskType = request.getParameter("taskType");
													%>
															<tr>
																<td >&nbsp;</td>
																<td colspan="5">
																	<div class="subhead">Add A Step - <%=taskType%></div>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td width="200">Step Name<font color="red">*</font></td>
																			<%
																				String tmpStepName = (String)request.getParameter("stepNameDum");
																				if(tmpStepName == null || "".equals(tmpStepName)){
																					tmpStepName = (String)request.getParameter("stepName");
																					if(tmpStepName == null || "".equals(tmpStepName)){
																						tmpStepName = "";
																					}
																				}
																			%>
																			<td><input type="text" name="stepName" value="<%=tmpStepName%>"/></td>
																		</tr>												
																		<tr>
																			<td>HostName<font color="red">*</font></td>
																			<%
																				String tmpHostName = (String)request.getParameter("agentDum");
																				if(tmpHostName == null || "".equals(tmpHostName)){
																					tmpHostName = (String)request.getParameter("agent");
																					if(tmpHostName == null || "".equals(tmpHostName)){
																						tmpHostName = "";
																					}
																				}
																			%>
																			<td><input type="text" name="agent" value="<%=tmpHostName%>"/></td>
																			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>=requred fields</td>
																		</tr>
																		<!-- Task type related field - Start -->
																		<%
																			try{
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
																			%>
																					<tr>
																						<td>
																							<%=userInputDO.getFieldLabel()%>
																							<%if(userInputDO.getFieldRequired()){%>
																									<font color="red">*</font>
																							<%}%>
																						</td>
																						<td>
																							<%
																								String fieldValue = (String)request.getParameter(userInputDO.getFieldId());
																								if(fieldValue == null || "".equals(fieldValue)){
																									fieldValue = "";
																								}
																							%>
																							<%if("text".equals(userInputDO.getFieldType())){%>
																									<input type="text" name="<%=userInputDO.getFieldId()%>" value="<%=fieldValue%>"/>
																							<%}%>
																							<%if("checkbox".equals(userInputDO.getFieldType())){%>
																									<input type="checkbox" name="<%=userInputDO.getFieldId()%>"
																									<%if(userInputDO.getFieldDefaultState() || "on".equals(fieldValue)){%>
																										checked
																									<%}%>
																									>
																							<%}%>
																							<%if("dropdown".equals(userInputDO.getFieldType())){
																									DCFieldOptionDO optionDO = null;
																									Vector selectList = userInputDO.getFieldOptionList();
																							%>
																									<select name="<%=userInputDO.getFieldId()%>">
																										<%for(int sl=0; sl<selectList.size(); sl++){
																												optionDO = (DCFieldOptionDO)selectList.get(sl);
																										%>
																												<option value="<%=optionDO.getOptionValue()%>" 
																													<%if(fieldValue.equals(optionDO.getOptionValue())){%>
																														selected
																													<%}%>
																												>
																												<%=optionDO.getOptionDisplayValue()%></option>
																										<%}%>
																									</select>
																							<%}%>
																						</td>
																					</tr>
																			<%}
																			}catch(Exception e){
																				System.out.println("index.jsp - Exception(Add Step): " + e);
																			}
																		%>
																		<!-- Task type related field - End -->
																	</table>
																	<div><input type="button" class="submitbutton" value="Save Step" onclick="javascript:addStepNow('<%=taskType%>');"/></div>
																</td>
															</tr>
													<%}%>
													<!-- Add Steps - End -->
													
													<!-- Update Steps - Start -->
													<%
														if(request.getAttribute("isUpdate") != null && "Y".equals(request.getAttribute("isUpdate"))){
															String taskType = (String)request.getAttribute("updateTaskType");
															List taskToUpdate = (ArrayList)request.getAttribute("updateList");
													%>
															<tr>
																<td >&nbsp;</td>
																<td colspan="5">
																	<div class="subhead">Update Step - <%=taskType%></div>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td width="200">Step Name<font color="red">*</font></td>
																			<td><input type="text" name="stepName" value="<%=taskToUpdate.get(2)%>"/></td>
																		</tr>
																		<tr>
																			<td>HostName<font color="red">*</font></td>
																			<td><input type="text" name="agent" value="<%=taskToUpdate.get(3)%>"/></td>
																		</tr>
																		<%
																			try{
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
																				%>
																					<tr>
																						<td>
																							<%=userInputDO.getFieldLabel()%>
																							<%if(userInputDO.getFieldRequired()){%>
																									<font color="red">*</font>
																							<%}%>
																						</td>
																						<td>
																							<%if("text".equals(userInputDO.getFieldType())){%>
																									<input type="text" name="<%=userInputDO.getFieldId()%>" value="<%=taskToUpdate.get(il + 6)%>"/>
																							<%}%>
																							<%if("checkbox".equals(userInputDO.getFieldType())){%>
																									<input type="checkbox" name="<%=userInputDO.getFieldId()%>"
																									<%if("Y".equals(taskToUpdate.get(il + 6))){%>
																										checked
																									<%}%>
																									>
																							<%}%>
																							<%if("dropdown".equals(userInputDO.getFieldType())){
																									DCFieldOptionDO optionDO = null;
																									Vector selectList = userInputDO.getFieldOptionList();
																							%>
																									<select name="<%=userInputDO.getFieldId()%>">
																										<%for(int sl=0; sl<selectList.size(); sl++){
																												optionDO = (DCFieldOptionDO)selectList.get(sl);
																										%>
																												<option value="<%=optionDO.getOptionValue()%>" 
																													<%if((taskToUpdate.get(il + 6)).equals(optionDO.getOptionValue())){%>
																														selected
																													<%}%>
																												>
																												<%=optionDO.getOptionDisplayValue()%></option>
																										<%}%>
																									</select>
																							<%}%>
																						</td>
																					</tr>
																			<%}
																			}catch(Exception e){
																				System.out.println("index.jsp - Exception(Update Step): " + e);
																			}
																		%>
																		
																		
																		<tr>
																			<td></td>
																			<td>
																				<input type="button" class="submitbutton" value="Update" onclick="javascript:updateDetails('<%=taskToUpdate.get(5)%>','<%=taskToUpdate.get(1)%>')"/>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
													<%}%>
													<!-- Update Steps - End -->
												</table>
												<!-- Steps - End -->
										<%}%>
										<!-- New workFlow Step - End -->
										
										<%}%>
								</table>															
								</div>
							</td>
						</tr>					
					</table>				
				</td>
			</tr>
		</table>
	</s:form>
	</body>
	</html>
	
	<!--   For popup opening functionality-->
	
<div id="viewStepDetailsDIV" style="visibility:hidden">
	<s:form name="viewStepDetailsForm">
		<jsp:include page="/jsp/viewStep.jsp"/>
		<br>
 		<input type="button" id="closeViewDetails" name="closeViewDetails" value="Close" onclick="javascript:hideViewDetailsDIV();"/>
	</s:form>
</div>