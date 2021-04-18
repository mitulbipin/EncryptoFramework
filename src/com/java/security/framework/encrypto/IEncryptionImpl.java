package com.java.security.framework.encrypto;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;

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

		for (int i = 0; i < data.length; i++) {
			enc[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] - 1);
		}
		return enc;
	}

	@Override
	public byte[] decrypt_BasicCrypto(byte[] data) {
		byte[] enc = new byte[data.length];

		for (int i = 0; i < data.length; i++) {
			enc[i] = (byte) ((i % 2 == 0) ? data[i] - 1 : data[i] + 1);
		}
		return enc;
	}

	@Override
	public String encrypt_SubstitutionAlgorithm(String data) {
		StringBuilder sb = new StringBuilder(data.length());

		for (char c : data.toCharArray())
			sb.append(ConstantsUtils.SubstitutionAlgoKeys.charAt((int) c - 32));

		return sb.toString();

	}

	@Override
	public String decrypt_SubstitutionAlgorithm(String data) {
		StringBuilder sb = new StringBuilder(data.length());

		for (char c : data.toCharArray())
			sb.append((char) (ConstantsUtils.SubstitutionAlgoKeys.indexOf((int) c) + 32));

		return sb.toString();
	}

	public String encrypt_RSAEncryption(String data) throws Exception {

		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName(ConstantsUtils.AlgorithmText);
		Node nNode = nList.item(1);
		Element eElement = (Element) nNode;

		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(eElement.getElementsByTagName("RSAText").item(0).getTextContent());
		keyPairGen.initialize(2048);
		KeyPair pair = keyPairGen.generateKeyPair();
		Cipher cipher = Cipher.getInstance(eElement.getElementsByTagName("cipherInstance").item(0).getTextContent());
		cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
		byte[] input = data.getBytes();
		cipher.update(input);
		byte[] cipherText = cipher.doFinal();
		return new String(cipherText, "UTF-8");
	}

	public boolean generateAndVerifyDigitalSignatures(String data) throws Exception {

		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName(ConstantsUtils.AlgorithmText);
		Node nNode = nList.item(4);
		Element eElement = (Element) nNode;

		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(eElement.getElementsByTagName("DSAText").item(0).getTextContent());
		keyPairGen.initialize(2048);
		KeyPair pair = keyPairGen.generateKeyPair();
		PrivateKey privKey_UserInput = pair.getPrivate();
		PrivateKey privKey_BackendValue = pair.getPrivate();

		Signature sign_UserInput = Signature.getInstance("SHA256withDSA");
		Signature sign_BackendValue = Signature.getInstance("SHA256withDSA");

		sign_UserInput.initSign(privKey_UserInput);
		sign_BackendValue.initSign(privKey_BackendValue);

		byte[] bytes_UserInput = data.getBytes();
		byte[] bytes_BackendValue = eElement.getElementsByTagName("Value").item(0).getTextContent().getBytes();

		sign_UserInput.update(bytes_UserInput);
		sign_BackendValue.update(bytes_BackendValue);

		byte[] signature_BackendValue = sign_BackendValue.sign();

		sign_UserInput.initVerify(pair.getPublic());
		sign_BackendValue.initVerify(pair.getPublic());
		sign_UserInput.update(bytes_UserInput);
		sign_BackendValue.initVerify(pair.getPublic());
		sign_BackendValue.update(bytes_BackendValue);

		return sign_UserInput.verify(signature_BackendValue);

	}
}