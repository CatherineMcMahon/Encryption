import java.util.ArrayList;
import java.util.Scanner;

public class ROT13 {
	private static String input;
	private static String output;
	private static ArrayList<Object> arr = new ArrayList<Object>();
	
	//must work with punctuation + spaces
	public static void main(String[] args) {
		System.out.println("Input text and hit enter to encrypt with ROT13 algorithm.");
		Scanner sc = new Scanner(System.in);
		
		input = sc.nextLine();
		for(int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			int e = ((((ch - 32) + 13) % 95) +32);
			arr.add(e);
		}				
		System.out.println("Encrypted text: "+ arr.toString());
		
	}
}
