package com.java.security.framework.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Properties;
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

    public static boolean generateAndVerifyDigitalSignatures(String data) throws Exception {
        InputStream input = new FileInputStream("resources/framework.properties");
        Properties prop = new Properties();
        prop.load(input);

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(prop.getProperty("framework.encryption.type"));
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey_UserInput = pair.getPrivate();
        PrivateKey privKey_BackendValue = pair.getPrivate();

        Signature sign_UserInput = Signature.getInstance("SHA256withDSA");
        Signature sign_BackendValue = Signature.getInstance("SHA256withDSA");

        sign_UserInput.initSign(privKey_UserInput);
        sign_BackendValue.initSign(privKey_BackendValue);

        byte[] bytes_UserInput = data.getBytes();
        byte[] bytes_BackendValue = prop.getProperty("framework.admin.password").getBytes();

        sign_UserInput.update(bytes_UserInput);
        sign_BackendValue.update(bytes_BackendValue);

        byte[] signature_BackendValue = sign_BackendValue.sign();

        sign_UserInput.initVerify(pair.getPublic());
        sign_BackendValue.initVerify(pair.getPublic());
        sign_UserInput.update(bytes_UserInput);
        sign_BackendValue.initVerify(pair.getPublic());
        sign_BackendValue.update(bytes_BackendValue);

        return sign_UserInput.verify(signature_BackendValue);
    }
}
