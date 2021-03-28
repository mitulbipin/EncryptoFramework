package com.java.security.framework.common;

import java.util.HashMap;

public class algorithmList {
	public static HashMap<String,String> listOfAlgorithms() {

		
		HashMap<String, String> algorithmList = new HashMap<String, String>();
		algorithmList.put("1", ConstantsUtils.BasicEncryptoText);
		algorithmList.put("2", ConstantsUtils.SubstitutionAlgorithmText);
		
		return algorithmList;
	}
}
