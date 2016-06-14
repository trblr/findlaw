package com.dcgenerator.web.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * <code>Set welcome message.</code>
 */
public class AddExecutionplanIndexAction extends DCGeneratorSupport {
	
	private ServletContext context;
	private HttpServletRequest request;
	
	private HttpSession session;
	private HttpServletResponse response;
	
	//properties
	private String propertyName1;
	private String propertyName2;
	private String propertyName3;
	private String propertyName4;
	private String propertyName5;
	private String propertyName6;
	private String propertyName7;
	private String propertyName8;
	private String propertyName9;
	private String propertyName10;
	
	private String propertyValue1;
	private String propertyValue2;
	private String propertyValue3;
	private String propertyValue4;
	private String propertyValue5;
	private String propertyValue6;
	private String propertyValue7;
	private String propertyValue8;
	private String propertyValue9;
	private String propertyValue10;
    
	public String execute() throws Exception 
    {
		
		session = request.getSession();    
        System.out.println("add stepindex");
        String appName = (String)session.getAttribute("application");
        
        //properties
        
        session.setAttribute("propN1"+appName,(String)getPropertyName1());
        session.setAttribute("propN2"+appName,(String)getPropertyName2());
        session.setAttribute("propN3"+appName,(String)getPropertyName3());
        session.setAttribute("propN4"+appName,(String)getPropertyName4());
        session.setAttribute("propN5"+appName,(String)getPropertyName5());
        
        session.setAttribute("propN6"+appName,(String)getPropertyName6());
        session.setAttribute("propN7"+appName,(String)getPropertyName7());
        session.setAttribute("propN8"+appName,(String)getPropertyName8());
        session.setAttribute("propN9"+appName,(String)getPropertyName9());
        session.setAttribute("propN10"+appName,(String)getPropertyName10());
        
        session.setAttribute("propV1"+appName,(String)getPropertyValue1());
        session.setAttribute("propV2"+appName,(String)getPropertyValue2());
        session.setAttribute("propV3"+appName,(String)getPropertyValue3());
        session.setAttribute("propV4"+appName,(String)getPropertyValue4());
        session.setAttribute("propV5"+appName,(String)getPropertyValue5());
        
        session.setAttribute("propV6"+appName,(String)getPropertyValue6());
        session.setAttribute("propV7"+appName,(String)getPropertyValue7());
        session.setAttribute("propV8"+appName,(String)getPropertyValue8());
        session.setAttribute("propV9"+appName,(String)getPropertyValue9());
        session.setAttribute("propV10"+appName,(String)getPropertyValue10());
        //properties
        //request.setAttribute("addStep", "Y");
        request.setAttribute("copyUp", "N");  
       
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
	public String getPropertyName1() {
		return propertyName1;
	}

	public void setPropertyName1(String propertyName1) {
		this.propertyName1 = propertyName1;
	}

	public String getPropertyName2() {
		return propertyName2;
	}

	public void setPropertyName2(String propertyName2) {
		this.propertyName2 = propertyName2;
	}

	public String getPropertyName3() {
		return propertyName3;
	}

	public void setPropertyName3(String propertyName3) {
		this.propertyName3 = propertyName3;
	}

	public String getPropertyName4() {
		return propertyName4;
	}

	public void setPropertyName4(String propertyName4) {
		this.propertyName4 = propertyName4;
	}

	public String getPropertyValue1() {
		return propertyValue1;
	}

	public void setPropertyValue1(String propertyValue1) {
		this.propertyValue1 = propertyValue1;
	}

	public String getPropertyValue2() {
		return propertyValue2;
	}

	public void setPropertyValue2(String propertyValue2) {
		this.propertyValue2 = propertyValue2;
	}

	public String getPropertyValue3() {
		return propertyValue3;
	}

	public void setPropertyValue3(String propertyValue3) {
		this.propertyValue3 = propertyValue3;
	}

	public String getPropertyValue4() {
		return propertyValue4;
	}

	public void setPropertyValue4(String propertyValue4) {
		this.propertyValue4 = propertyValue4;
	}


	public String getPropertyName5() {
		return propertyName5;
	}


	public void setPropertyName5(String propertyName5) {
		this.propertyName5 = propertyName5;
	}


	public String getPropertyValue5() {
		return propertyValue5;
	}


	public void setPropertyValue5(String propertyValue5) {
		this.propertyValue5 = propertyValue5;
	}

	public String getPropertyName6() {
		return propertyName6;
	}

	public void setPropertyName6(String propertyName6) {
		this.propertyName6 = propertyName6;
	}

	public String getPropertyName7() {
		return propertyName7;
	}

	public void setPropertyName7(String propertyName7) {
		this.propertyName7 = propertyName7;
	}

	public String getPropertyName8() {
		return propertyName8;
	}

	public void setPropertyName8(String propertyName8) {
		this.propertyName8 = propertyName8;
	}

	public String getPropertyName9() {
		return propertyName9;
	}

	public void setPropertyName9(String propertyName9) {
		this.propertyName9 = propertyName9;
	}

	public String getPropertyName10() {
		return propertyName10;
	}

	public void setPropertyName10(String propertyName10) {
		this.propertyName10 = propertyName10;
	}

	public String getPropertyValue6() {
		return propertyValue6;
	}

	public void setPropertyValue6(String propertyValue6) {
		this.propertyValue6 = propertyValue6;
	}

	public String getPropertyValue7() {
		return propertyValue7;
	}

	public void setPropertyValue7(String propertyValue7) {
		this.propertyValue7 = propertyValue7;
	}

	public String getPropertyValue8() {
		return propertyValue8;
	}

	public void setPropertyValue8(String propertyValue8) {
		this.propertyValue8 = propertyValue8;
	}

	public String getPropertyValue9() {
		return propertyValue9;
	}

	public void setPropertyValue9(String propertyValue9) {
		this.propertyValue9 = propertyValue9;
	}

	public String getPropertyValue10() {
		return propertyValue10;
	}

	public void setPropertyValue10(String propertyValue10) {
		this.propertyValue10 = propertyValue10;
	}


    
}
