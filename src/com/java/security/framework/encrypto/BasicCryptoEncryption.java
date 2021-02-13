package com.java.security.framework.encrypto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BasicCryptoEncryption {
	
	static Scanner input = new Scanner(System.in);
	static IEncryptionImpl crypto = new IEncryptionImpl();

	public static void main(String args[])
	{
	
	System.out.println("Enter the text to be encrypted:");
	String data = input.next();
	System.out.print(IEncryptionImpl.listOfAlgorithms());	
//	System.out.print("Choose the algorithm");
//	int option = input.nextInt();

	basicEncryptoAlgorithm(data);
	
		System.out.print(basicEncryptoAlgorithm(data));
	
	}
	
	
	public static List<String> basicEncryptoAlgorithm(String data){
	
		List<String> text = new ArrayList<String>();
		
		text.add(new String(crypto.encrypt_BasicCrypto(data.getBytes())));
		text.add(String(crypto.decrypt_BasicCrypto(text.get(0).getBytes())));
		
		return text;

	}


	private static String String(byte[] decrypt_BasicCrypto) {
		// TODO Auto-generated method stub
		return null;
	} 
}
