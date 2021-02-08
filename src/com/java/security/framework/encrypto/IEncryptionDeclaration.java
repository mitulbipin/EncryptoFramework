package com.java.security.framework.encrypto;

public interface IEncryptionDeclaration {
	byte[] encrypt(byte[] data);
	byte[] decrypt(byte[] data);
}
