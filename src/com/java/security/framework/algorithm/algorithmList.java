package com.java.security.framework.algorithm;

import java.util.HashMap;
import com.java.security.framework.common.ConstantsUtils;

public class algorithmList {
	public static void listOfAlgorithms() {

		HashMap<String, String> algorithmList = new HashMap<String, String>();
		algorithmList.put("1", ConstantsUtils.RSAlgorithmText);
		algorithmList.put("2", ConstantsUtils.SubstitutionAlgorithmText);
		algorithmList.put("3", ConstantsUtils.BasicEncryptoText);
		
		System.out.println("\nList of algorithms available:");
		for(String i : algorithmList.keySet())
			System.out.println(i+". " + algorithmList.get(i)); 
	}

}