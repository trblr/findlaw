<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<%@page import="com.dcgenerator.web.utils.*"%>
<%@page import="com.dcgenerator.web.data.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Environment Creator-View Step</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/main.css" />
<script>
function closeWindow()
{
	window.close();
}
</script>
</head>
<body>
	<%
		DCTaskDO taskDO = null;
		DCReadConfigXML readConfigXML = new DCReadConfigXML();
		Vector taskList = readConfigXML.getTaskList();
		String workflowVal = (String)session.getAttribute("workflowValue"); 
		String appName = (String)session.getAttribute("application");
		List list = (ArrayList)session.getAttribute("stepList"+appName+workflowVal);
		if(list!=null && list.size()!=0){
			for(Iterator iter = list.iterator();iter.hasNext();){
				String displayValues = (String)iter.next();
				StringTokenizer  srt = new StringTokenizer(displayValues,",");
				List viewList = new ArrayList();
				while(srt.hasMoreTokens()){
					String value = srt.nextToken();
					viewList.add(value);
				}
				
				if(null != viewList){
					for(int tl=0; tl<taskList.size(); tl++){
						taskDO = (DCTaskDO)taskList.get(tl);
						if(viewList.get(1).equals(taskDO.getTaskName()))
							break;
					}
					%>
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="sectiontitle" colspan="6" width="100%"><div class="sectiontitle"><%=viewList.get(0)%>.Task Type - <%=viewList.get(1)%></div></td>
						</tr>
						<tr>
							<td class="tableheader">Step Name</td>
							<td > : <%=viewList.get(2)%></td>
						</tr>												
						<tr>
							<td class="tableheader">HostName</td>
							<td> : <%=viewList.get(3)%></td>
						</tr>
						<%
							DCUserInputFieldDO userInputDO = null;
							Vector userInputList = taskDO.getTaskUserInputList();
							for(int il=0; il<userInputList.size(); il++){
								userInputDO = (DCUserInputFieldDO)userInputList.get(il);
						%>
								<tr>
									<td class="tableheader"><%=userInputDO.getFieldLabel()%></td>
									<td> : <%=viewList.get(6 + il)%></td>
								</tr>
						<%}%>
					</table>
				<%}%>
			<%}
		}
	%>
</body>
</html>