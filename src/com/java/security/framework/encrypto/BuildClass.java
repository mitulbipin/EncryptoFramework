package com.java.security.framework.encrypto;

import java.util.Scanner;

import com.java.security.framework.algorithm.AlgorithmOutput;
import com.java.security.framework.algorithm.algorithmList;
import com.java.security.framework.common.ConstantsUtils;

public class BuildClass {

	static Scanner input = new Scanner(System.in);
	static IEncryptionImpl crypto = new IEncryptionImpl();

	public static void main(String args[]) throws Exception {
		System.out.println(ConstantsUtils.frameworkTitle);
		System.out.println(ConstantsUtils.frameworkLines_Horizontal);
		System.out.print("\n" + ConstantsUtils.inputText);
		String data = input.next();
		System.out.println("\n" + ConstantsUtils.frameworkLines_Horizontal);
		System.out.print(algorithmList.listOfAlgorithms());

//		AlgorithmOutput.basicEncryptoAlgorithm(data);
//		AlgorithmOutput.substitutionAlgorithm(data);
		AlgorithmOutput.RSAEncryptionAlgorithm(data);

	}
}
