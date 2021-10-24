package com.ss.jb.three;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * Assignment 3
 * Write a Java program that counts the number of times a particular character, such as 'e', appears in a file. The character can be specified at the command line.
 */
public class Assignment3 {
	public static void main(String args[]) throws IOException {
		Assignment3 assignment3 = new Assignment3();
		String tempInput = "";
		char c = 0, caseSensitive = 0;
		
		try (Scanner reader = new Scanner(System.in)) {
			System.out.print("Enter the character you want read in the text file \"myfile.txt\": ");
			tempInput = reader.next();
			
			// If what the user input is longer than a single character (i.e. a string), then error
			if (tempInput.length() > 1)
				throw new NotACharacterException(tempInput + " is not a single character.");
			else {
				c = tempInput.charAt(0); // A single character is located at index 0 of a string				
			}
			
			System.out.print("Do you want to read it case-sensitive? Type 'y' for \"yes\" or 'n' for \"no\": ");
			tempInput = reader.next();
			
			// If the user inputs longer than a single character OR the single character is a 'y' or 'n'
			if (tempInput.length() > 1 || !tempInput.equals("y") && !tempInput.equals("Y") && !tempInput.equals("n") && !tempInput.equals("N"))
				throw new NotACharacterException(tempInput + " is not a single character.");
			else {
				caseSensitive = tempInput.charAt(0); // A single character is located at index 0 of a string				
			}
			
			System.out.println();				
		}
		catch (NotACharacterException e) {
			System.out.println("User input is not formatted correctly");
			e.printStackTrace();
			return;
		}
		
		Integer totalCharacters = assignment3.countCharacters(c, caseSensitive);
		System.out.println(c + " appears a total of " + totalCharacters + " times in the file!");
	}
	
	/*
	 * This method, given a character 'c', counts the number of times the character appears in the file "myfile.txt"
	 * Returns: the number of times the character appears (Integer)
	 */
	private Integer countCharacters(char c, char caseSensitive) throws IOException {
		Integer totalCharacters = 0;
		FileReader reader;
		int ch;
				
		try {
			reader  = new FileReader("myfile.txt");
			BufferedReader br = new BufferedReader(reader);
			
			// If the user cares about case-sensitivity
			if (Character.toLowerCase(caseSensitive) == 'y') {
				// Buffer returns -1 when there are no more characters to read, signaling the end of the file. Until then, read
				while((ch = br.read()) != -1){
				if (c == (char)ch)
					totalCharacters++; // Increment if there is a match
				}			
			}
			
			// Else, the user doesn't care about case-sensitivity
			else
			{
				while((ch = br.read()) != -1){
				// Change all characters to lower-case because user doesn't care about case-sensitivity
				if (Character.toLowerCase(c) == Character.toLowerCase((char)ch))
					totalCharacters++;
				}		
			}
				
			br.close();
		}
		
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		return totalCharacters;
	}
}
