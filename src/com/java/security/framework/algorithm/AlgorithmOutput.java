package com.java.security.framework.algorithm;

import java.io.*;

import com.java.security.framework.common.ConstantsUtils;
import com.java.security.framework.common.commonMethods;
import com.java.security.framework.encrypto.IEncryptionImpl;

public class AlgorithmOutput {

    static IEncryptionImpl crypto = new IEncryptionImpl();

    public static void basicEncryptoAlgorithm(String data) {

        commonMethods.algorithmOutputMethod(
                ConstantsUtils.BasicEncryptoText,
                new String(crypto.encrypt_BasicCrypto(data.getBytes())),
                null);

    }

    public static void substitutionAlgorithm(String data) {
        commonMethods.algorithmOutputMethod(
                ConstantsUtils.SubstitutionAlgorithmText,
                crypto.encrypt_SubstitutionAlgorithm(data)[0],
                crypto.encrypt_SubstitutionAlgorithm(data)[1]);
    }

    public static void RSAEncryptionAlgorithm(String data) throws Exception {
        commonMethods.algorithmOutputMethod(
                ConstantsUtils.RSAlgorithmText, crypto.encrypt_RSAEncryption(data), null);
        FileWriter RSAEncryptionFile = new FileWriter("RSAEncryptionFile.txt");
        RSAEncryptionFile.write(crypto.encrypt_RSAEncryption(data));
        RSAEncryptionFile.close();
    }

    public static void caesarAlgorithm(String data) {
        commonMethods.algorithmOutputMethod(
                ConstantsUtils.CaesarAlgorithmText,
                crypto.encryptCaesarAlgorithm(data)[0],
                crypto.encryptCaesarAlgorithm(data)[1]);
    }

    public static void blowFishAlgorithm(String data) throws Exception {
        commonMethods.algorithmOutputMethod(
                ConstantsUtils.BlowfishAlgorithmText,
                crypto.encryptBlowfishAlgorithm(data)[0],
                crypto.encryptBlowfishAlgorithm(data)[1]);
    }

    public static void base64Encryption(String data) {
        commonMethods.algorithmOutputMethod(
                ConstantsUtils.Base64AlgorithmText,
                crypto.encryptBase64Algorithm(data)[0],
                crypto.encryptBase64Algorithm(data)[1]);
    }

    public static void AesEncryption(String data) throws Exception {
        System.out.println("\nThe Encryption key size is " + crypto.encryptAesEncryptionAlgorithm(data)[1] + "bits");
        commonMethods.algorithmOutputMethod(
                ConstantsUtils.AesEncryptionText,
                crypto.encryptAesEncryptionAlgorithm(data)[0],
                crypto.encryptAesEncryptionAlgorithm(data)[2]);
    }

}