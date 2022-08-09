package com.criteriaManager;

import java.io.FileReader;
import java.util.List;

import com.debugprint.rsaDebug;
import com.fileManager.RSAFileUtils;
import com.opencsv.CSVReader;
import com.regexSearch.RSARegexsearch;

public class TechnicalSkillReader {
	//Secondary Skill List
	private static List<String[]> skillData;
	private static boolean isReady;
	/*
	 * Build Secondary skill List from SecondarySkills.CSV file that 
	 * the recruiter looks for in resume.
	 */
	public TechnicalSkillReader()
	{
		if(skillData != null) return;
		rsaDebug.print("Build Secondary Skill Data");
		try {
			CSVReader reader = new CSVReader(new FileReader(RSAFileUtils.getFolderPath("/data/")+"SecondarySkills.csv"));
			skillData = reader.readAll();
			if(skillData.size()>0)
				isReady = true;
		}
		catch (Exception e) {
            System.err.println(e.getMessage());
	    }
	}
	public boolean canReadTechnicalSkills()
	{
		return isReady;
	}
	/*
	 * Method that iterate through the secondary skill list 
	 * and look for keyword match in resume data
	 * and prepare the output string
	 */
	public String readTechnicalSkill(String fileData)
	{
		StringBuilder outStr = new StringBuilder();
		
        for (String[] strList : skillData) {
        	StringBuilder matchStr = new StringBuilder();
            for (int i = 1; i<strList.length; i++) {
				String strRegex = "\\b"+strList[i]+"\\b";
				String tmpStr = RSARegexsearch.regexWordMatch(strRegex, fileData, true);
				if(tmpStr.length() > 0) {
		            matchStr.append(strList[i]+", ");
		        }
            }
            if(matchStr.length()>0)
            {
            	outStr.append(strList[0]+" ["+matchStr.toString());
            	outStr.append("]"+System.lineSeparator());
            }
            	
		}
        rsaDebug.print(outStr.toString());
        return outStr.toString();
	}
	/*
	 * Method to fetch 10 lines after "skills" word match in resume data
	 * and prepare the output string
	 */
	public String fetchWholeSkillData(String fileData)
	{
		 //To search with multiple patterns, some file contents do not have newline characters
		String[] strRegex = {"skills.*(?:\\R.*){10}","skills.*"};
		
		for(String str:strRegex) {
			String outStr = RSARegexsearch.regexFetchAllMatches(str, fileData);
			if(!outStr.isEmpty()) return outStr; 
		}
		return "";
	}
	
	/*
	 * Method to fetch 5 lines after "certifications" word match in resume data
	 * and prepare the output string
	 */
	public String fetchCertificationData(String fileData)
	{
		//To search with multiple patterns, some file contents do not have newline characters
		String[] strRegex = {"certifi.*(?:\\R.*){5}","certifi.*"};
		
		for(String str:strRegex) {
			String outStr = RSARegexsearch.regexFetchAllMatches(str, fileData);
			if(!outStr.isEmpty()) return outStr;
		}
		return "";
	}
}
