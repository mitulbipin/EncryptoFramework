package com.java.security.framework.common;

import java.util.Scanner;

import com.java.security.framework.algorithm.AlgorithmOutput;
import com.java.security.framework.algorithm.algorithmList;

public class BuildClassMethods {
	static Scanner input = new Scanner(System.in).useDelimiter("\n");
	static int option;

	public static void algorithmSelector(String data) throws Exception {
		algorithmList.listOfAlgorithms();
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
		case 4:
			AlgorithmOutput.caesarAlgorithm(data);
			break;
		case 5:
			AlgorithmOutput.blowFishAlgorithm(data);
			break;
		case 6:
			AlgorithmOutput.base64Encryption(data);
			break;
		default:
			System.out.println("\n" + ConstantsUtils.AlgorithmNotAvailableText);

		}
	}

	public static void additionOfAlgorithm() throws Exception {
		System.out.print(ConstantsUtils.AddNewAlgorithmText);
		String option = input.next();
		if (option.equals("Y"))
			algorithmList.addAlgorithm();
		else
			algorithmList.listOfAlgorithms();
	}
}
