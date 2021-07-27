package com.java.security.framework.encrypto;

public interface IDecryptionDeclaration {

    byte[] decrypt_BasicCrypto(byte[] data);

    String decrypt_SubstitutionAlgorithm(String data);

    String decryptCaesarAlgorithm(String data);

    String decryptBlowfishAlgorithm(String data) throws Exception;

    String decryptBase64Algorithm(String data);
}
