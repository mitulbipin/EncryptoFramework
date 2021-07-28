package com.java.security.framework.common;

import java.util.Scanner;

import com.java.security.framework.algorithm.AlgorithmOutput;
import com.java.security.framework.algorithm.algorithmList;

public class commonMethods {
    static Scanner input = new Scanner(System.in).useDelimiter("\n");
    static int option;

    //This method is used in Build.java
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
            case 7:
                AlgorithmOutput.AesEncryption(data);
                break;

            default:
                System.out.println("\n" + ConstantsUtils.AlgorithmNotAvailableText);

        }
    }

    //This method is used in Build.java
    public static void additionOfAlgorithm() throws Exception {
        System.out.print(ConstantsUtils.AddNewAlgorithmText);
        String option = input.next();
        if (option.equals("Y"))
            algorithmList.addAlgorithm();
    }

    //This method is used in algorithmOutput.java
    public static void algorithmOutputMethod(String algorithmName, Object encryptedText, Object decryptedText) {
        System.out.println("The encrypted text in " + algorithmName + " is " + encryptedText);
        System.out.print("\nDo you want to decrypt it? (Y/N):");
        String option = input.next();
        try{
            if(option.equals("Y"))
                System.out.println(decryptedText);
        }catch (Exception e){
            System.out.println("Decryption not available");
        }

    }
}
