package com.java.security.framework.encrypto;

import com.java.security.framework.common.ConstantsUtils;

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

}