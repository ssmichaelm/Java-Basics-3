package com.ss.jb.three;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Assignment 2
 * Write a Java program to append to an existing file
 */
public class Assignment2 {
	public static void main(String args[]) {
		String endInput = "/end", userInput = "";
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Enter the text you want to append to the existing file");
		System.out.println("(When you're finished, enter \"/end\")");
		
		// While the user does not input "/end", take the user input and append it to the existing file "myfile.txt"
		while(true) {
			// try-with-resources so the writers are automatically closed upon exit
			try (FileWriter fw = new FileWriter("myfile.txt", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw))
			{
				userInput = reader.nextLine();
				
				// If the user input equals "/end", end
				if (userInput.equals(endInput))
					break;
				
			    out.println(userInput);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}			
		}
		
		reader.close();
	}
}
