package com.java.security.framework.algorithm;

import java.util.Scanner;

import com.java.security.framework.common.ConstantsUtils;
import com.java.security.framework.encrypto.IEncryptionImpl;

public class AlgorithmOutput{
	
	static Scanner input = new Scanner(System.in);
	static IEncryptionImpl crypto = new IEncryptionImpl();

	public static void basicEncryptoAlgorithm(String data){
	
		System.out.print("\nThe encrypted text in "+ConstantsUtils.BasicEncryptoText+" is "
							+new String(crypto.encrypt_BasicCrypto(data.getBytes())));
	}
	
	public static void substitutionAlgorithm(String data) {
		System.out.println("\nThe encrypted text in "+ConstantsUtils.SubstitutionAlgorithmText+" is "
							+crypto.encrypt_SubstitutionAlgorithm(data));
	}
}
