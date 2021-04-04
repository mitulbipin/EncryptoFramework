package com.java.security.framework.encrypto;

import java.util.Scanner;

import com.java.security.framework.algorithm.AlgorithmOutput;
import com.java.security.framework.common.algorithmList;

public class BuildClass  {

	static Scanner input = new Scanner(System.in);
	static IEncryptionImpl crypto = new IEncryptionImpl();

	public static void main(String args[]) throws Exception
	{
	
		System.out.print("Enter the text to be encrypted: ");
		String data = input.next();
		System.out.print(algorithmList.listOfAlgorithms());	
	
//		AlgorithmOutput.basicEncryptoAlgorithm(data);
//		AlgorithmOutput.substitutionAlgorithm(data);
		AlgorithmOutput.RSAEncryptionAlgorithm(data);
	
	}
}
