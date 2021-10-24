package com.ss.jb.three;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * Write a Java program to get a list of all file/directory names (including in sub-directories) under a 
 * given directory
 */
public class Assignment1 {
	/*
	 * This method prints out ALL contents of a provided directory; this includes all files within that direct folder and all files in sub-folders
	 */
	public static void main(String args[]) throws IOException {
		String dirName = null;
		
		try (Scanner reader = new Scanner(System.in)){
			System.out.println("Like \"/Users/mmore/Pictures\" or \"C:\\\\Users\\\\mmore\" ");
			System.out.println("Please beware that this lists ALL files under the given directory, including sub-directories.");
			System.out.print("Enter a folder path: ");
			dirName = reader.nextLine();
			File fileObject = new File(dirName);
			
			// If the folder or file does not exist, then exit
			if (!fileObject.exists()) {
				System.out.println("Folder path or file does not exist.");
				return;
				
			}
			
			System.out.print(displayAll(fileObject));
		}
	}
	
	
	/*
	 * This method accepts a File object as a parameter and then displays it and any children out
	 */
	public static String displayAll(File fileObject) {
	    Assignment1 assignment1 = new Assignment1();
	    int indentCount = 0;
	    StringBuilder sb = new StringBuilder();
	    
	    // If the object is a file, print out the file name and get out
	    if (!fileObject.isDirectory()) {
	    	return fileObject.getName();
	    }
	    
	    // If the object is a folder, call "displayDirectory" and print out the children
	    assignment1.displayDirectory(fileObject, indentCount, sb);
	    return sb.toString();
	}

	
	/*
	 * This method accepts a folder, indent count, and StringBuilder as parameters. It then:
	 * 		- Appends the folder name to the StringBuilder
	 * 		- For each file within the given folder, either prints the file if it's a file or recursively calls the method if it's a folder
	 */
	private void displayDirectory(File folder, int indentCount, StringBuilder sb) {
	    sb.append(injectIndents(indentCount)).append("* [").append(folder.getName()).append("] //").append("\n"); // Format the string for the folder name 
	    
	    /* For each file "f" from the given folder's files:
	     * 		If the file "f" is a directory, recursively call the method again with an increased indentCount
	     * 		Else, print out the file name with it's respective indent space
	     */
	    for (File f : folder.listFiles()) {
	        if (f.isDirectory()) {
	            displayDirectory(f, indentCount + 1, sb);
	        }
	        else {
	            printFile(f, indentCount + 1, sb);
	        }
	    }

	}

	
	/*
	 * This method prints out a file's name and its necessary whitespace
	 */
	private void printFile(File f, int indentCount, StringBuilder sb) {
	    sb.append(injectIndents(indentCount)).append("* ").append(f.getName()).append("\n");
	}

	
	/*
	 * This method:
	 * 		- Takes the number of indents needed (indentCount)
	 * 		- Creates a StringBuilder
	 * 		- And appends the correct amount of indents to that StringBuilder, returning it
	 */
	private String injectIndents(int indentCount) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < indentCount; i++) {
	        sb.append("   ");
	    }
	    return sb.toString();
	}
}
