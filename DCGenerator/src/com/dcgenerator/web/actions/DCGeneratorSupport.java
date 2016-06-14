package com.dcgenerator.web.actions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Base Action class for the Tutorial package.
 */
public class DCGeneratorSupport extends ActionSupport implements ServletContextAware , ServletRequestAware , ServletResponseAware{

	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
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
