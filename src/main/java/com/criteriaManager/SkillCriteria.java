package com.criteriaManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.debugprint.rsaDebug;

public class SkillCriteria implements RSACriteriaList {
	//List to store Skill related key values.
	private ArrayList<String> ValueList;
	private int searchStatus = 0;
	
	public SkillCriteria() {
		
		ValueList = new ArrayList<>();
	}

	public ArrayList<String> getValueList() {
		return ValueList;
	}

	public void setValueList(String skillValue) {
		ValueList.add(skillValue);
	}
	
	public String getTagValue() {
		return "Skill";
	}
	
	public int getSearchStatus() {
		return searchStatus;
	}
	
	public void resetSearchStatus() {
		searchStatus = 0;
	}
	/*
	 * Search method to find Skill key value match 
	 * in the file data.
	 */
	public String searchCriteria(String fileData)
	{	
		for(String keyValue : ValueList)
		{
			String strRegex = "\\b"+keyValue+"\\b";
	        Pattern pattern = Pattern.compile(strRegex,Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(fileData);
	        rsaDebug.print("Keyvalue = "+strRegex);

	        while(matcher.find()){
	        	++searchStatus;
	            rsaDebug.print("Skill Match found");
	            return keyValue;
	        }
		}
		return "****";
	}

}
