package com.criteriaManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.debugprint.rsaDebug;

public class QualificationCriteria implements RSACriteriaList{
	//List to store Qualification related key values.
	private ArrayList<String> ValueList;
	private int searchStatus = 0;

	private enum qRegex{
		BCA("B\\.*C\\.*A\\.*"),
		BSc("B\\.*Sc\\.*"),
		BE("B\\.*E\\.*"),
		BTech("B\\.*Tech\\.*"),
		BCom("B\\.*Com\\.*"),
		BA("B\\.*A\\.*"),
		Bachelor("Bachelor"),
		Bachelors("Bachelors");

		private String regexStr;

		qRegex(final String strVal) {
			this.regexStr = strVal;
		}

	    @Override
	    public String toString() {
	        return this.getregexStr();
	    }

		String getregexStr() {
			return regexStr;
		}

	}
	
	public QualificationCriteria() {
		ValueList = new ArrayList<String>();
	}
	
	public ArrayList<String> getValueList() {
		return ValueList;
	}

	public void setValueList(String skillValue) {
		ValueList.add(skillValue);
	}
	public String getTagValue() {
		return "Qualification";
	}
	public void resetSearchStatus() {
		searchStatus = 0;
	}
	public int getSearchStatus() {
		return searchStatus;
	}
	/*
	 * Search method to find Qualification key value match 
	 * in the file data.
	 */
	public String searchCriteria(String fileData)
	{
		for(String keyValue : ValueList)
		{
			String strRegex;
			try {
				strRegex = "[\\s , : ; - ( \\ \\/ \\[ ]\\b"+qRegex.valueOf(keyValue).toString()+"\\b";
			}catch(IllegalArgumentException e) {
				strRegex = "\\b"+keyValue+"\\b";
			}
			rsaDebug.print("Regex ::"+strRegex);

			Matcher matcher = Pattern.compile(strRegex).matcher(fileData);
			rsaDebug.print(keyValue+" :: "+strRegex);
			while(matcher.find()){
				++searchStatus;
				//rsaDebug.print("Found at " + matcher.group());
				return keyValue;
			}
		}
		return "****";
	}
}
