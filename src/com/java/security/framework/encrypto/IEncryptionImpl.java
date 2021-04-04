package com.java.security.framework.encrypto;

import com.java.security.framework.common.ConstantsUtils;
import java.security.*;
import javax.crypto.*;

public class IEncryptionImpl implements IEncryptionDeclaration {
	
 	@Override
	public byte[] encrypt_BasicCrypto(byte[] data) {
		byte[] enc = new byte[data.length];
		
		for(int i = 0;i<data.length;i++){
			enc[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] - 1);
		}
		return enc;
	}

	@Override
	public byte[] decrypt_BasicCrypto(byte[] data) {
		byte[] enc = new byte[data.length];
		
		for(int i = 0;i<data.length;i++){
			enc[i] = (byte) ((i % 2 == 0) ? data[i] - 1 : data[i] + 1);
		}
		return enc;
	}

	@Override
	public String encrypt_SubstitutionAlgorithm(String data) {
		StringBuilder sb = new StringBuilder(data.length());

		for(char c : data.toCharArray())
		sb.append(ConstantsUtils.SubstitutionAlgoKeys.charAt((int)c - 32));

		return sb.toString();

	}
	
	@Override
	public String decrypt_SubstitutionAlgorithm(String data) {
		StringBuilder sb = new StringBuilder(data.length());

		for(char c : data.toCharArray())
		sb.append((char) (ConstantsUtils.SubstitutionAlgoKeys.indexOf((int) c) + 32));

		return sb.toString();
	}
	
	public String encrypt_RSAEncryption(String data) throws Exception {
		
		Signature sign = Signature.getInstance("SHA256withRSA");
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048);
		KeyPair pair = keyPairGen.generateKeyPair(); 
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
		byte[] input = data.getBytes();
		cipher.update(input);
		byte[] cipherText = cipher.doFinal();
		return new String(cipherText,"UTF8");

	}

}