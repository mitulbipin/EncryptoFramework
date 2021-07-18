package com.java.security.framework.encrypto;

import java.util.Scanner;

import com.java.security.framework.common.BuildClassMethods;
import com.java.security.framework.common.ConstantsUtils;

public class BuildClass {

	static Scanner input = new Scanner(System.in).useDelimiter("\n");
//	static IEncryptionImpl crypto = new IEncryptionImpl();
	static String data;

	public static void main(String args[]) throws Exception {
		System.out.println(ConstantsUtils.frameworkTitle);
		System.out.println(ConstantsUtils.frameworkLines_Horizontal);
		
		System.out.print("\n" + ConstantsUtils.inputText);
		data = input.next();
		
		BuildClassMethods.additionOfAlgorithm(); //Password field
//		System.out.println("\n" + ConstantsUtils.frameworkLines_Horizontal);
		BuildClassMethods.algorithmSelector(data);
	}

}