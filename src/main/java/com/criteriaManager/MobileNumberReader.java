package com.criteriaManager;

import com.regexSearch.RSARegexsearch;

public class MobileNumberReader{
	/*
	 * Method to fetch Mobile number from file data.
	 */
	public String getMobileNo(String fileData)
	{
		String strRegex = "\\b[-0-9]{10,12}\\b";
		String outStr = RSARegexsearch.regexFetchAllMatches(strRegex,fileData);
        return((outStr.length()>0)?outStr:"******");
	}
}
