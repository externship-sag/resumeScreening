package com.criteriaManager;

import com.regexSearch.RSARegexsearch;

public class MobileNumberReader{
	/*
	 * Method to fetch Mobile number from file data.
	 */
	public String getMobileNo(String fileData)
	{
		String strRegex = "\\b((\\d{2}[\\- \\ ])?\\d{10})\\b|(\\b\\d{10}\\b)|(\\b91\\d{10}\\b)";

		String outStr = RSARegexsearch.regexFetchAllMatches(strRegex,fileData);
        return((outStr.length()>0)?outStr:"******");
	}
}
