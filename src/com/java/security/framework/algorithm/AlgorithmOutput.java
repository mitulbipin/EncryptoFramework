package com.java.security.framework.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java.security.framework.encrypto.*;

public class AlgorithmOutput{
	
	static Scanner input = new Scanner(System.in);
	static IEncryptionImpl crypto = new IEncryptionImpl();

	public static List<String> basicEncryptoAlgorithm(String data){
		
		List<String> text = new ArrayList<String>();
		
		text.add(new String(crypto.encrypt_BasicCrypto(data.getBytes())));
		text.add(new String(crypto.decrypt_BasicCrypto(text.get(0).getBytes())));
		
		return text;

	}
}
