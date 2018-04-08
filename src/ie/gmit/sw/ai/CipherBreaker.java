package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ie.gmit.sw.ai.Playfair;
import ie.gmit.sw.ai.Key;

public class CipherBreaker {
	
	static Scanner console = new Scanner(System.in);
	//static Playfair play = new Playfair();
	
	public static void main(String[] args)throws Throwable, Exception {
		
		System.out.println("Do you want to: \n1. Decrypt\n2. Exit");
		menuChoice();	
	}
	
	public static void menuChoice() throws Exception {
		int choice = console.nextInt();
	
		switch(choice) {
			case 1:
				System.out.println("Enter File Name: ");
				String file = console.next();
				file += ".txt";
				
				BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
				StringBuilder builder = new StringBuilder();
				String line;
				
				while((line = buffer.readLine()) != null) {
					builder.append(line.toUpperCase().replace("J", ""));
				}
				System.out.println("Generating key");
				String key = Key.newInstance().genKey();
				System.out.println(key);
				Playfair play = new Playfair(file);
				play.decrpytText(key);
				
				break;
			case 2:
				System.out.println("Bye bye :)");
				break;
			default:
				System.out.println("\nPlease choose a vaild number!!\n");
				System.out.println("Do you want to: \n1. Decrypt\n2. Exit");
					
				menuChoice();
				break;
				
		}
	}
	
	/*public static void usePlayfair(String text) throws Exception{
		String decrypt;
		System.out.println("What's the key to decrypt?");
		String key = console.next();
		key = key.toUpperCase().replaceAll("^A-Za-z0-9 ]", "");
		if(key.length() != 25) {
			System.out.println("It has to be 25 numbers long!");
			usePlayfair(text);
		}
		System.out.println("Doing things");
		decrypt = play.decrpytText(key, text);
	}*/

}
