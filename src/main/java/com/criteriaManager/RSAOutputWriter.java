package com.criteriaManager;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.CSVWriter;

import com.debugprint.rsaDebug;
import com.fileManager.RSAFileUtils;

public class RSAOutputWriter {
	static File Path;
	/*
	 * Method to create csv file to display the search results.
	 */
	public static void createOutputFile()
	{
		File pathDir = new File(RSAFileUtils.getFolderPath("/data/output/"));
		Path path = Paths.get(RSAFileUtils.getFolderPath("/data/output")+"/Result.csv");
		if (!pathDir.exists()){
			pathDir.mkdirs();
			rsaDebug.print("Creating dir:" + pathDir.getName());
		}
		else {
			if(Files.exists(path)) {
				rsaDebug.print("Clearing output folder");
				try {
					Files.delete(path);
				}
				catch (IOException e) {
					  System.err.println(e.getMessage());
				      System.err.println("System Err : Failed to delete the output folder");
				}
			}
		}
		try {
			Files.createFile(path);
		} 
		catch (IOException e) {
			  System.err.println(e.getMessage());
		      System.err.println("Failed to create output folder, check your system!!");
	    	}
		String[] ouputHeader = {"Name","Skill","Qualification","Experience","Eligible"};
		updateOutput(ouputHeader);

	}
	/*
	 * Write the search results in the csv file
	 */
	public static void updateOutput(String[] displayStr)
	{
		try {
			FileWriter outputfile = new FileWriter(new 
					 File(RSAFileUtils.getFolderPath("/data/output")+"/Result.csv"),true);
			 
			CSVWriter writer = new CSVWriter(outputfile);
			  
			writer.writeNext(displayStr);
			writer.close();
		}
		catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	

}
