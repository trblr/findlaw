package com.dcgenerator.web.utils;

import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dcgenerator.web.data.DCFieldOptionDO;
import com.dcgenerator.web.data.DCTaskDO;
import com.dcgenerator.web.data.DCUserInputFieldDO;

public class DCReadConfigXML {
	private	static Vector taskDOList = null; //Variable will hold task library XML
	
	/**
	 * Method will load the XML into the session variable
	 */
	public int loadXMLFile(String XML_FILE){
		try{
			System.out.println("Loading XML File");
			//Create object to hold XML document object
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder xmlBuilder = domFactory.newDocumentBuilder();
			Document xmlDoc = xmlBuilder.parse(XML_FILE);
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//Initialize the variables
			DCTaskDO 	dcTaskDO = null;
			int			fieldCount = 1;
			taskDOList = new Vector();
			
			//Start traversing the XML object
			NodeList taskNodeList = (NodeList)xPath.evaluate("//tasklibrary/*", xmlDoc, XPathConstants.NODESET);
			for(int tn=0; tn<taskNodeList.getLength(); tn++){
				Element taskElement = (Element)taskNodeList.item(tn);
				if(!"task".equals(taskElement.getNodeName())) //If it is not TASK node then no point in going further
					continue;
				
				//Initialize variable and Check for attributes
				dcTaskDO = new DCTaskDO();
				dcTaskDO.setTaskName(taskElement.getAttribute("name"));
				dcTaskDO.setTaskLabel(taskElement.getAttribute("label"));
				
				//Set help if available
				if(taskElement.getElementsByTagName("help") != null)
					dcTaskDO.setTaskHelp(taskElement.getElementsByTagName("help").item(0).getTextContent());
				
				//Get the user input and check that it is not null...
				if(taskElement.getElementsByTagName("userinput") == null)
					continue;
				
				fieldCount = 1;
				Vector taskUserInputList = new Vector();
				NodeList userInputList = (NodeList)taskElement.getElementsByTagName("userinput").item(0).getChildNodes();
				for(int uil=0; uil<userInputList.getLength(); uil++){
					Node userInputNode = userInputList.item(uil);
					if(userInputNode.getNodeType() != Node.ELEMENT_NODE) //Check if it is a ELEMENT Node or not
						continue;
					
					//If the user input if field then add it
					if("field".equals(userInputNode.getNodeName())){
						DCUserInputFieldDO userInputFieldDO = populateUserInputFieldDO((Element)userInputNode, false, fieldCount);
						taskUserInputList.add(userInputFieldDO);
						fieldCount++;
					}
					
					//If it is required block then we have to traverse it again
					if("required".equals(userInputNode.getNodeName())){
						NodeList requiredNodeList = ((Element)userInputNode).getElementsByTagName("field");
						for(int rl=0; rl<requiredNodeList.getLength(); rl++){
							DCUserInputFieldDO userInputFieldDO = populateUserInputFieldDO((Element)requiredNodeList.item(rl), true, fieldCount);
							taskUserInputList.add(userInputFieldDO);
							fieldCount++;
						}
					}
				}
				
				//Set the object in the vector
				dcTaskDO.setTaskUserInputList(taskUserInputList);
				taskDOList.add(dcTaskDO);
			}
			
			System.out.println("DCReadConfigXML.loadXMLFile - Total Task: " + taskDOList.size());
		}catch(Exception e){
			System.out.println("DCReadConfigXML.loadXMLFile - Exception: " + e);
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Method will populate the UserInputDO based on the node passed
	 * @param fieldNode, isRequired, fieldCount
	 * @return
	 * @throws Exception
	 */
	public DCUserInputFieldDO populateUserInputFieldDO(Element fieldNode, boolean isRequired, int fieldCount) throws Exception{
		DCUserInputFieldDO userInputFieldDO = new DCUserInputFieldDO();
		userInputFieldDO.setFieldRequired(isRequired);
		userInputFieldDO.setFieldNumber(fieldCount);
		userInputFieldDO.setFieldId(fieldNode.getAttribute("id"));
		userInputFieldDO.setFieldType(fieldNode.getAttribute("type"));
		userInputFieldDO.setFieldLabel(fieldNode.getAttribute("label"));
		userInputFieldDO.setFieldValue(fieldNode.getAttribute("value"));
		
		if("checkbox".equals(userInputFieldDO.getFieldType()))//If this is check box type
			if(fieldNode.getAttribute("default") != null && fieldNode.getAttribute("default").length() > 0 && "true".equalsIgnoreCase(fieldNode.getAttribute("default")))
				userInputFieldDO.setFieldDefaultState(true);
		
		if("dropdown".equals(userInputFieldDO.getFieldType())){//If this is drop down type
			Vector fieldOptionList = new Vector();
			NodeList optionNodeList = fieldNode.getElementsByTagName("option");
			for(int ol=0; ol<optionNodeList.getLength(); ol++){
				//Initialize and start setting the values for FieldOptionDO
				Element optionElement = (Element)optionNodeList.item(ol);
				DCFieldOptionDO fieldOptionDO = new DCFieldOptionDO();
				fieldOptionDO.setOptionDisplayValue(optionElement.getTextContent());
				fieldOptionDO.setOptionName(optionElement.getAttribute("name"));
				fieldOptionDO.setOptionValue(optionElement.getAttribute("value"));
				fieldOptionList.add(fieldOptionDO);
			}
			
			userInputFieldDO.setFieldOptionList(fieldOptionList);
		}
		
		return userInputFieldDO;
	}
	
	/**
	 * Method will return task list to the calling class or method
	 * @return
	 */
	public Vector getTaskList(){
		return taskDOList;
	}
}
