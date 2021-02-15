package com.java.security.framework.encrypto;

import java.util.ArrayList;
import java.util.List;
import com.java.security.framework.common.*;

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
public static List<String> listOfAlgorithms() {//Enter password in order to add a new algorithm into the list
	List<String> algorithmList = new ArrayList<String>();
	
	
	algorithmList.add(ConstantsUtils.BasicEncryptoText);
	
	return algorithmList;
	
}//plan to move the method to the common package.

}