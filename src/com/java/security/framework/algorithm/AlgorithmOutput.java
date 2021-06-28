package com.java.security.framework.algorithm;

import java.util.Scanner;
import java.io.*;

import com.java.security.framework.common.ConstantsUtils;
import com.java.security.framework.encrypto.IEncryptionImpl;

public class AlgorithmOutput {

	static Scanner input = new Scanner(System.in);
	static IEncryptionImpl crypto = new IEncryptionImpl();

	public static void basicEncryptoAlgorithm(String data) {

		System.out.print("\nThe encrypted text in " + ConstantsUtils.BasicEncryptoText + " is "
				+ new String(crypto.encrypt_BasicCrypto(data.getBytes())));
	}

	public static void substitutionAlgorithm(String data) {
		System.out.println("\nThe encrypted text in " + ConstantsUtils.SubstitutionAlgorithmText + " is "
				+ crypto.encrypt_SubstitutionAlgorithm(data));
	}

	public static void RSAEncryptionAlgorithm(String data) throws Exception {
		System.out.println("\nThe encrypted text in " + ConstantsUtils.RSAlgorithmText + " is "
				+ crypto.encrypt_RSAEncryption(data));
		FileWriter RSAEncryptionFile = new FileWriter("RSAEncryptionFile.txt");
		RSAEncryptionFile.write(crypto.encrypt_RSAEncryption(data));
		RSAEncryptionFile.close();
	}
	
	public static void caesarAlgorithm(String data) {
		System.out.println("\nThe encrypted text in " + ConstantsUtils.CaesarAlgorithmText + " is "
				+ crypto.encryptCaesarAlgorithm(data));
	}
	
	public static void blowFishAlgorithm(String data) throws Exception {
		System.out.println("\nThe encrypted text in " + ConstantsUtils.BlowfishAlgorithmText + " is "
				+ crypto.encryptBlowfishAlgorithm(data));
	}
	
	public static void base64Encryption(String data) {
		System.out.println("\nThe encrypted text in " + ConstantsUtils.Base64AlgorithmText + " is "
				+ crypto.encryptBase64Algorithm(data));
	}
	
}