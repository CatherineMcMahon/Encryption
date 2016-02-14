// by Catherine McMahon
import java.math.BigInteger;
import java.util.*;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

public class Encryption {
    private static ArrayList<Object> arr;
    private static Scanner sc;
    
    public static void main(String[] args) {
        arr = new ArrayList<Object>();
        sc = new Scanner(System.in);
        
        System.out.println("Enter 'r' for ROT13, 's' for symmetric, and 'a' for asymmetric encryption + decryption.");
        String algorithmType = sc.nextLine();
        
        if(algorithmType.equals("r")) {
            System.out.println("To encrypt enter 'e' and to decrypt enter 'd'.");
            String choice = sc.nextLine();
            if(choice.equals("e")) {
                System.out.println("Please enter text to encrypt.");
                String text = sc.nextLine();
                rot13Encrypt(text);
            } else if(choice.equals("d")) {
                System.out.println("Please enter text to decrypt.");
                String text = sc.nextLine();
                rot13Decrypt(text);
            } else {
                System.out.println("Error. Make sure to type the correct letter.");
                main(args);
            }
        } else if(algorithmType.equals("s")) {
            System.out.println("To encrypt enter 'e' and to decrypt enter 'd'.");
            String choice = sc.nextLine();
            if(choice.equals("e")) {
                System.out.println("Please enter text to encrypt.");
                String text = sc.nextLine();
                System.out.println("Enter the key to success");
                String key = sc.nextLine();
                symmetricEncryption(text, key);
            } else if(choice.equals("d")) {
                System.out.println("Please enter text to decrypt.");
                String text = sc.nextLine();
                System.out.println("Enter the key to success");
                String key = sc.nextLine();
                symmetricDecryption(text, key);
            } else {
                System.out.println("Error. Make sure to type the correct letter.");
                main(args);
            }
        } else if(algorithmType.equals("a")) {
            System.out.println("Please enter text to encrypt + decrypt.");
            String text = sc.nextLine();
            asymmetricEncryption(text);
        } else {
            System.out.println("Error. Make sure to type the correct letter.");
            main(args);
        }
    }
    
    public static void rot13Encrypt(String text) {
        String encryptedText = "";
        
        for(int i=0; i<text.length(); i++) {
            char ch = text.charAt(i);
            int e = ((((ch-32) + 13) % 95) +32);
            encryptedText += String.valueOf(Character.toChars(e));
        }
        
        System.out.println("Encrypted text: "+ encryptedText);
    }
    
    public static void rot13Decrypt(String text) {
        String decryptedText = "";
        
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            int g = c - 32; // translate to space
            g -= 13; // Inverse Rot13
            while( g < 0 ){
                g+=95;
            }
            g += 32; // translate back
            decryptedText += String.valueOf(Character.toChars(g));
        }
        System.out.println("Decrypted text: "+ decryptedText);
    }
    
    public static void symmetricEncryption(String text, String key) {
        String encryptedText = "";
        
        while(key.length()<text.length()) {
            key+=key;
        }
        
        for(int i=0; i<text.length(); i++) {
            char m = text.charAt(i);
            char k = key.charAt(i);
            encryptedText += String.valueOf(Character.toChars(m+k));
        }
        System.out.println("Encrypted text: " + encryptedText);
    }
    
    public static void symmetricDecryption(String text, String key) {
        String decryptedText = "";
        
        for(int j=0; j<text.length(); j++) {
            text += String.valueOf(Character.toChars(text.charAt(j) - key.charAt(j)));
        }
        System.out.println("Decrypted text: " + decryptedText);
    }
    
    public static void asymmetricEncryption(String text) {
        Random rnd = new Random();
        BigInteger p = BigInteger.probablePrime(512, rnd); // large prime num
        BigInteger q = BigInteger.probablePrime(512, rnd); // large prime num
        BigInteger totientN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); // φ (totient) N
        BigInteger n = p.multiply(q); // RSA modulus
        BigInteger e = totientN.gcd(BigInteger.ONE); // for public key (n,e)
        BigInteger d = e.modInverse(totientN); // for private key (n,d)
        
        String initial = "";
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            Integer ch = new Integer(c);
            String cbinary = Integer.toBinaryString(ch);
            while(cbinary.length() < 8) { 
                cbinary = "0" + cbinary; 
            }
            initial += cbinary;
        }
        
        BigInteger m = new BigInteger(initial, 2); 
        m = m.modPow(e, n); 
        byte[] val = m.toByteArray(); 
        String encryptedText = DatatypeConverter.printBase64Binary(val);
        System.out.println("Encrypted Text: " + encryptedText);
        
        // DECRYPTION:
        byte[] array = DatatypeConverter.parseBase64Binary(encryptedText);
        BigInteger x = new BigInteger(array); 
        x = x.modPow(d, n); 
        String decryptedText = DatatypeConverter.printBase64Binary(array);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
