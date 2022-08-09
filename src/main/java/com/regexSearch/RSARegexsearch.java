package com.regexSearch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.debugprint.rsaDebug;

public final class RSARegexsearch {
	
	private RSARegexsearch() {}
	/*
	 * Method that looks for a word match. Returns after the first word occurrence.
	 */
	public static String regexWordMatch(String regexKeyword,String strdata,boolean caseFlagNeeded)
	{
		Pattern pattern;
		if(caseFlagNeeded)
			pattern = Pattern.compile(regexKeyword,Pattern.CASE_INSENSITIVE);
		else
			pattern = Pattern.compile(regexKeyword);
		
        Matcher matcher = pattern.matcher(strdata);
        rsaDebug.print("Keyvalue = "+regexKeyword);

        if(matcher.find()){
            rsaDebug.print("Match found::" +matcher.group(0));
            return matcher.group();
        }
		return "";
	}
	/*
	 * Method that looks for all the occurrences of a word.
	 */
	public static String regexFetchAllMatches(String regexKeyword,String strdata)
	{
		StringBuilder outStr = new StringBuilder();
		Pattern pattern = Pattern.compile(regexKeyword,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(strdata);
        rsaDebug.print("Keyvalue = "+regexKeyword);
        String newLine = System.getProperty("line.separator");
        while(matcher.find()){
            rsaDebug.print("Match found::" +matcher.group(0));
            outStr.append(matcher.group()+newLine);
        }
		return outStr.toString();
	}
}
