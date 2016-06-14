package com.dcgenerator.web.data;

public class DCFieldOptionDO {
	
	private	String	optionName;
	private	String	optionValue;
	private	String	optionDisplayValue;
	
	//Getter/Setter method for OptionName
	public void setOptionName(String optionName){
		this.optionName = optionName;
	}
	
	public String getOptionName(){
		return optionName;
	}
	
	//Getter/Setter method for OptionValue
	public void setOptionValue(String optionValue){
		this.optionValue = optionValue;
	}
	
	public String getOptionValue(){
		return optionValue;
	}
	
	//Getter/Setter method for OptionDisplayValue
	public void setOptionDisplayValue(String optionDisplayValue){
		this.optionDisplayValue = optionDisplayValue;
	}
	
	public String getOptionDisplayValue(){
		return optionDisplayValue;
	}
	
	//Overwrite toString method for logs
	public String toString(){
		StringBuffer dcFieldOptionDO = new StringBuffer("<DCFieldOptionDO>\n");
		dcFieldOptionDO.append("<OptionName>" + getOptionName() + "</OptionName>\n");
		dcFieldOptionDO.append("<OptionValue>" + getOptionValue() + "</OptionValue>\n");
		dcFieldOptionDO.append("<OptionDisplayValue>" + getOptionDisplayValue() + "</OptionDisplayValue>\n");
		dcFieldOptionDO.append("</DCFieldOptionDO>\n");
		return dcFieldOptionDO.toString();
	}
}
