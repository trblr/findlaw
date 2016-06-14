package com.dcgenerator.web.data;

import java.util.Vector;

public class DCTaskDO {
	//Parameters to hold XML main task related variables
	private	String	taskName;
	private	String	taskLabel;
	private	String	taskHelp;
	
	//Parameter to hold user input fields
	private	Vector taskUserInputList;
	
	//Getter/Setter method for TaskName
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}
	
	public String getTaskName(){
		return taskName;
	}
	
	//Getter/Setter method for TaskLabel
	public void setTaskLabel(String taskLabel){
		this.taskLabel = taskLabel;
	}
	
	public String getTaskLabel(){
		return taskLabel;
	}
	
	//Getter/Setter method for TaskHelp
	public void setTaskHelp(String taskHelp){
		this.taskHelp = taskHelp;
	}
	
	public String getTaskHelp(){
		return taskHelp;
	}
	
	//Getter/Setter method for TaskLabel
	public void setTaskUserInputList(Vector taskUserInputList){
		this.taskUserInputList = taskUserInputList;
	}
	
	public Vector getTaskUserInputList(){
		return taskUserInputList;
	}
	
	//Overwriting toString method to print
	public String toString(){
		StringBuffer dcTaskDO = new StringBuffer("<DCTaskDO>\n");
		dcTaskDO.append("<TaskName>" + getTaskName() + "</TaskName>\n");
		dcTaskDO.append("<TaskLabel>" + getTaskLabel() + "</TaskLabel>\n");
		dcTaskDO.append("<TaskHelp>" + getTaskHelp() + "</TaskHelp>\n");
		dcTaskDO.append("<TaskUserInputList>" + getTaskUserInputList() + "</TaskUserInputList>\n");
		dcTaskDO.append("</DCTaskDO>");
		return dcTaskDO.toString();
	}
}
