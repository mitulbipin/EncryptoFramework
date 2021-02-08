package com.java.security.framework.encrypto;

public class BasicCryptoEncryption {

	public static void main(String args[])
	{
	IEncryptionImpl crypto = new IEncryptionImpl();
	String data = "Mitul Bipin";
	String enc = new String(crypto.encrypt(data.getBytes()));
	String dec = new String(crypto.decrypt(enc.getBytes()));
		
	System.out.println("Original: " +data);
	System.out.println("Encrypted: " +enc);
	System.out.println("Decrpyted: " +dec);

	}
}
