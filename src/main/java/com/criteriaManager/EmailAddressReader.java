package com.criteriaManager;

import com.regexSearch.RSARegexsearch;

public class EmailAddressReader{
	/*
	 * Method to fetch Email address from file data.
	 */
	public String getEmailAdd(String fileData)
	{
		String strRegex = "\\S+@\\S+\\.\\S+";
		String outStr = RSARegexsearch.regexFetchAllMatches(strRegex,fileData);
        return((outStr.length()>0)?outStr:"****");
    }
}
