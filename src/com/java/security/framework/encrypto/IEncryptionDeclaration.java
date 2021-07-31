package com.java.security.framework.encrypto;

public interface IEncryptionDeclaration {

    String[] encrypt_BasicCrypto(byte[] data);

    String[] encrypt_SubstitutionAlgorithm(String data);

    String[] encrypt_RSAEncryption(String data) throws Exception;

    String[] encryptCaesarAlgorithm(String data);

    String[] encryptBlowfishAlgorithm(String data) throws Exception;

    String[] encryptBase64Algorithm(String data);

    String[] encryptAesEncryptionAlgorithm(String data) throws Exception;
}
