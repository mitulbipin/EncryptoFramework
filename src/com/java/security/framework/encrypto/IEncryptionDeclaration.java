package com.java.security.framework.encrypto;

public interface IEncryptionDeclaration {
	byte[] encrypt_BasicCrypto(byte[] data);

	byte[] decrypt_BasicCrypto(byte[] data);

	String encrypt_SubstitutionAlgorithm(String data);

	String decrypt_SubstitutionAlgorithm(String data);

	String encrypt_RSAEncryption(String data) throws Exception;
}
