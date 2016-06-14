package com.dcgenerator.web.utils;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DCLoadConfiguraton implements Servlet{
	private	static Properties	configProps = null;
	private	static String		DC_HOME = null;
	
	public void destroy() {
		System.out.println("DCLoadConfiguraton.destroy");
	}

	public ServletConfig getServletConfig() {
		System.out.println("DCLoadConfiguraton.getServletConfig");
		return null;
	}

	public String getServletInfo() {
		System.out.println("DCLoadConfiguraton.getServletInfo");
		return null;
	}

	/* This block will be executed at the server startup.
	 * It will load the configuration file
	 * Start the log file for application logging
	 * Load the task library XML
	 */
	public void init(ServletConfig arg0) throws ServletException {
		try{
			System.out.println("Starting Initialization.... ");
			DC_HOME = System.getenv().get("DC_HOME");
			System.out.println("DC_HOME: " + DC_HOME);			
			if(DC_HOME == null || DC_HOME.length() < 1)
				DC_HOME = "C:/";
			
			//Load the task library XML file
			DCReadConfigXML readConfigXML = new DCReadConfigXML();
			readConfigXML.loadXMLFile(DC_HOME + "/config/tasklibrary.xml");
		}catch(Exception e){
			System.out.println("DCLoadConfiguraton.Exception: " + e);
			throw new ServletException();
		}
	}

	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("DCLoadConfiguraton.service");
	}
	
	/**
	 * Method will return property value to the calling class
	 * @param propKey
	 * @return
	 */
	public static String getProperty(String propKey){
		return configProps.getProperty(propKey);
	}
}
