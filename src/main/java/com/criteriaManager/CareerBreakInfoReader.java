package com.criteriaManager;

import com.regexSearch.RSARegexsearch;

public class CareerBreakInfoReader{
	static String[] keywordset_1= {"career break","re-enter","reenter","return to work","returnship"};
	static String[] keywordset_2= {"present","current","till date","tilldate","to date"};
	
	/*	
	 * Method to check whether the candidate is on career break
	 */
	public String getCareerStatus(String fileData)
	{
		boolean keyfound1 = false,keyfound2 = false;
		keyfound1 = checkCareerStatus(fileData,keywordset_1);
		if(!keyfound1)
			keyfound2 = checkCareerStatus(fileData,keywordset_2);
		else
			return "yes";
		return(keyfound2?"no":"Unknown");
	}
	/*
	 * Method that performs regex search
	 */
	private boolean checkCareerStatus(String fileData, String[] keywordList)
	{
		for(String str:keywordList) {
			String strRegex = "\\b"+str+"\\b";
			String outStr = RSARegexsearch.regexWordMatch(strRegex, fileData,true);
			if(outStr.length() > 0) return true;
		}
        return false;
    }
}
