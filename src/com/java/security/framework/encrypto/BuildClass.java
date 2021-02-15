package com.java.security.framework.encrypto;

import java.util.Scanner;

import com.java.security.framework.algorithm.AlgorithmOutput;

public class BuildClass  {
	
//	static AlgorithmOutput variable = new AlgorithmOutput(); omitting since the respective package has been imported
	static Scanner input = new Scanner(System.in);
	static IEncryptionImpl crypto = new IEncryptionImpl();

	public static void main(String args[])
	{
	
	System.out.println("Enter the text to be encrypted:");
	String data = input.next();
	System.out.print(IEncryptionImpl.listOfAlgorithms());	

	AlgorithmOutput.basicEncryptoAlgorithm(data);
	
		System.out.println(AlgorithmOutput.basicEncryptoAlgorithm(data));
	
	}
}
