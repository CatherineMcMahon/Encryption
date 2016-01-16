// to do: fix ROT13 decryption bugs, symmetrical + asymmetrical logic, separate presentation from logic 
// by Catherine McMahon

import java.util.*;
import java.util.Scanner;
public class Encryption {
	private static ArrayList<Object> arr;
	private static Scanner sc;
	
	//must work with punctuation + spaces
	public static void main(String[] args) {
		arr = new ArrayList<Object>();
		sc = new Scanner(System.in);

		System.out.println("Enter 'r' for ROT13, 's' for symmetric, and 'a' for asymmetric encryption + decryption.");
		String option = sc.nextLine();
		if(option.equals("r")) {
			System.out.println("Enter text.");
			String text = sc.nextLine();
			rot13(text);
		} else if(option.equals("s")) {
			System.out.println("Enter text.");
			String text = sc.nextLine();
			symmetric(text);
		} else if(option.equals("a")) {
			System.out.println("Enter text.");
			String text = sc.nextLine();
			asymmetric(text);
		} else {
			main(args);
		}
	}
	
	public static void rot13(String input) {
		 	String encrypt = "";
		 	String decrypt = "";
		 	
		 	//encryption
			for(int i=0; i<input.length(); i++) {
				char ch = input.charAt(i);
				int e = ((((ch-32) + 13) % 95) +32);
				
				encrypt += String.valueOf(Character.toChars(e));
			}				
			System.out.println("Encrypted text: "+ encrypt);
			
			// decryption
			for(int i=0; i<encrypt.length(); i++) {
				char c = encrypt.charAt(i);
				int g = (((c+32) - 13) % 95 -32);
				
				if(g<0) {
					g = g*(-1);
					decrypt += String.valueOf(Character.toChars(g));
				} else {
					decrypt += String.valueOf(Character.toChars(g));
				}
			}
			System.out.println("Decrypted text: "+ decrypt);
	}
	
	public static void symmetric(String input) {
		
	}
	
	public static void asymmetric(String input) {
		
	}
}