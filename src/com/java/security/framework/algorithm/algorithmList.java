package com.java.security.framework.algorithm;

import java.util.HashMap;
import java.util.Scanner;

import com.java.security.framework.common.ConstantsUtils;
import com.java.security.framework.encrypto.IEncryptionImpl;

public class algorithmList {
    static IEncryptionImpl crypto = new IEncryptionImpl();
    static Scanner input = new Scanner(System.in);

    public static void listOfAlgorithms() {

        HashMap<String, String> algorithmList = new HashMap<String, String>();
        algorithmList.put("1", ConstantsUtils.RSAlgorithmText);
        algorithmList.put("2", ConstantsUtils.SubstitutionAlgorithmText);
        algorithmList.put("3", ConstantsUtils.BasicEncryptoText);
        algorithmList.put("4", ConstantsUtils.CaesarAlgorithmText);
        algorithmList.put("5", ConstantsUtils.BlowfishAlgorithmText);
        algorithmList.put("6", ConstantsUtils.Base64AlgorithmText);
        algorithmList.put("7", ConstantsUtils.AesEncryptionText);

        System.out.println("\nList of algorithms available:");
        for (String i : algorithmList.keySet())
            System.out.println(i + ". " + algorithmList.get(i));
    }

    public static void addAlgorithm() throws Exception {
        System.out.print("\nEnter the password:");
        String data = input.next();
        if (crypto.generateAndVerifyDigitalSignatures(data)) {
            //SQL Database will be invoked
            System.out.println("Success");
        } else {
            System.out.println("Incorrect password\n");
            listOfAlgorithms();
        }
    }

    public static String checkForExistingSequenceNumber(HashMap<String, String> algorithmList) {

        while (true) {
            System.out.print("\nEnter the sequence number of the new algorithm:");
            String newAlgoSequence = input.next();
            for (String i : algorithmList.keySet()) {

                if (i.contains(newAlgoSequence)) {
                    System.out.println("Sequence number already exists");
                    break;
                } else
                    return newAlgoSequence;

            }
        }

    }

}
