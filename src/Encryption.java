// to do: asymmetric, separate presentation from logic...
// by Catherine McMahon

import java.math.BigInteger;
import java.util.*;
import java.util.Scanner;

public class Encryption {
    private static ArrayList<Object> arr;
    private static Scanner sc;
    
    // All presentation logic in main method
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
            System.out.println("Enter the key to success");
            String key = sc.nextLine();
            symmetric(text, key);
        } else if(option.equals("a")) {
            System.out.println("Enter text.");
            String text = sc.nextLine();
            System.out.println("Enter the key to success");
            String key = sc.nextLine();
            asymmetric(text, key);
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
            int g = c - 32; // translate to space
            g -= 13; // Inverse Rot13
            while( g < 0 ){
                g+=95;
            }
            g += 32; // translate back
            decrypt += String.valueOf(Character.toChars(g));
            
        }
        System.out.println("Decrypted text: "+ decrypt);
    }
    
    public static void symmetric(String input, String key) {
        String encrypt = "";
        String decrypt = "";
        
        while(key.length()<input.length()) {
            key+=key;
        }
        
        for(int i=0; i<input.length(); i++) {
            char m = input.charAt(i); // get string value at i
            char k = key.charAt(i); // get key value at i
            encrypt += String.valueOf(Character.toChars(m+k));
        }
        System.out.println("Encrypted text: " + encrypt);
        
        // decryption
        for(int j=0; j<encrypt.length(); j++) {
            decrypt += String.valueOf(Character.toChars(encrypt.charAt(j) - key.charAt(j)));
        }
        System.out.println("Decrypted text: " + decrypt);
    }
    
    public static void asymmetric(String input, String key) {
        String encrypt = "";
        String decrypt = "";
        int value = 0; // end value of Euler's Algorithm
        
        Random rnd = new Random();
        BigInteger p = BigInteger.probablePrime(1024, rnd); // large prime num
        BigInteger q = BigInteger.probablePrime(1024, rnd); // large prime num
        BigInteger n = p.multiply(q); // RSA modulus
        BigInteger totientN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); // φ (totient) N
        BigInteger e = totientN.gcd(BigInteger.ONE);
        while(value!=1) {
            //do equation // plug in values
        }
        //do encryption, base 64, output
        // base 64 -> bytes -> BigInteger
        // decrypt...
        
    }
    
    
    //		BigInteger e = new BigInteger
    // e of public key, relatively prime num (ex: 3, 7, 9..)		
    //		int e = 1 + (int)(Math.random() * totientN); 
    // extended Euclidean algorithm to solve for d of private key
    // use powMod for algorithm
    // Back substitution
    //		RSA Algorithm:
    //			Choose two distinct prime numbers p and q.
    //			Compute n = pq.
    //			Choose an integer e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1; i.e., e and φ(n) are coprime.
    //			Determine d as d ≡ e−1 (mod φ(n)); i.e., d is the modular multiplicative inverse of e (modulo φ(n)).  
    //			This is more clearly stated as: solve for d given d⋅e ≡ 1 (mod φ(n))
}
}