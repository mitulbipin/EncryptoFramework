package com.java.security.framework.encrypto;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.java.security.framework.common.ConstantsUtils;

public class IEncryptionImpl implements IEncryptionDeclaration {
	
    File fXmlFile = new File("testdata.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	
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
		
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(fXmlFile);
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Algorithm");
	    Node nNode = nList.item(1);
        Element eElement = (Element) nNode;
        
//		Signature sign = Signature.getInstance("SHA256withRSA");
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(eElement.getElementsByTagName("RSAText").item(0).getTextContent());
		keyPairGen.initialize(2048);
		KeyPair pair = keyPairGen.generateKeyPair(); 
		Cipher cipher = Cipher.getInstance(eElement.getElementsByTagName("cipherInstance").item(0).getTextContent());
		cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
		byte[] input = data.getBytes();
		cipher.update(input);
		byte[] cipherText = cipher.doFinal();
		return new String(cipherText,"UTF-8");
	}

}