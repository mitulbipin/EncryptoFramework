package com.java.security.framework.algorithm;

import java.io.*;
import java.util.Scanner;

import com.java.security.framework.common.ConstantsUtils;
import com.java.security.framework.encrypto.IEncryptionImpl;

public class AlgorithmOutput {

    static IEncryptionImpl crypto = new IEncryptionImpl();
    static Scanner input = new Scanner(System.in).useDelimiter("\n");

    public static void basicEncryptoAlgorithm(String data) {
        System.out.print("\nThe encrypted text in " + ConstantsUtils.BasicEncryptoText + " is "
                + new String(crypto.encrypt_BasicCrypto(data.getBytes())));
    }

    public static void substitutionAlgorithm(String data) {
        System.out.println("\nThe encrypted text in " + ConstantsUtils.SubstitutionAlgorithmText + " is "
                + crypto.encrypt_SubstitutionAlgorithm(data));
    }

    public static void RSAEncryptionAlgorithm(String data) throws Exception {
        System.out.println("\nThe encrypted text in " + ConstantsUtils.RSAlgorithmText + " is "
                + crypto.encrypt_RSAEncryption(data));
        FileWriter RSAEncryptionFile = new FileWriter("RSAEncryptionFile.txt");
        RSAEncryptionFile.write(crypto.encrypt_RSAEncryption(data));
        RSAEncryptionFile.close();
    }

    public static void caesarAlgorithm(String data) {
        System.out.println("\nThe encrypted text in " + ConstantsUtils.CaesarAlgorithmText + " is "
                + crypto.encryptCaesarAlgorithm(data));
    }

    public static void blowFishAlgorithm(String data) throws Exception {
        System.out.println("\nThe encrypted text in " + ConstantsUtils.BlowfishAlgorithmText + " is "
                + crypto.encryptBlowfishAlgorithm(data));
    }

    public static void base64Encryption(String data) {
//        System.out.println("\nThe encrypted text in " + ConstantsUtils.Base64AlgorithmText + " is "
//                + crypto.encryptBase64Algorithm(data));
        algorithmOutputMethod(ConstantsUtils.Base64AlgorithmText, crypto.encryptBase64Algorithm(data), null);
    }

    public static void AesEncryption(String data) throws Exception {
        System.out.println("\nThe Encryption key size is " + crypto.encryptAesEncryptionAlgorithm(data)[1] + "bits");
        algorithmOutputMethod(ConstantsUtils.AesEncryptionText,
                crypto.encryptAesEncryptionAlgorithm(data)[0], crypto.encryptAesEncryptionAlgorithm(data)[2]);
    }

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