package com.java.security.framework.encrypto;

import java.util.Scanner;

import com.java.security.framework.algorithm.AlgorithmOutput;
import com.java.security.framework.algorithm.algorithmList;
import com.java.security.framework.common.ConstantsUtils;

public class BuildClass {

	static Scanner input = new Scanner(System.in);
	static IEncryptionImpl crypto = new IEncryptionImpl();
	static String data;
	static int option;

	public static void main(String args[]) throws Exception {
		System.out.println(ConstantsUtils.frameworkTitle);
		System.out.println(ConstantsUtils.frameworkLines_Horizontal);
		System.out.print("\n" + ConstantsUtils.inputText);
		data = input.next();
		algorithmList.listOfAlgorithms();
		System.out.println("\n" + ConstantsUtils.frameworkLines_Horizontal);
		algorithmSelector();

	}
	
	public static void algorithmSelector() throws Exception  {
		System.out.print("\n" + ConstantsUtils.userInputText);
		option = input.nextInt();
		switch (option) {
		case 1:
			AlgorithmOutput.RSAEncryptionAlgorithm(data);
			break;
		case 2:
			AlgorithmOutput.substitutionAlgorithm(data);
			break;
		case 3:
			AlgorithmOutput.basicEncryptoAlgorithm(data);
			break;
		default:
			System.out.println("Algorithm not available/ incorrect option");

		}
	}
}