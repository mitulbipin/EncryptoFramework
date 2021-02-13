package com.java.security.framework.encrypto;

public interface IEncryptionDeclaration {
	byte[] encrypt_BasicCrypto(byte[] data);
	byte[] decrypt_BasicCrypto(byte[] data);
}
