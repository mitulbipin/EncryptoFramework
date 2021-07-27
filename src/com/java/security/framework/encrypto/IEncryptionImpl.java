package com.java.security.framework.encrypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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
    Properties prop = new Properties();
    InputStream input = null;

    @Override
    public byte[] encrypt_BasicCrypto(byte[] data) {
        byte[] enc = new byte[data.length];

        for (int i = 0; i < data.length; i++) {
            enc[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] - 1);
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

        input = new FileInputStream("resources/framework.properties");
        prop.load(input);

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(prop.getProperty("framework.encryption.type"));
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey_UserInput = pair.getPrivate();
        PrivateKey privKey_BackendValue = pair.getPrivate();

        Signature sign_UserInput = Signature.getInstance("SHA256withDSA");
        Signature sign_BackendValue = Signature.getInstance("SHA256withDSA");

        sign_UserInput.initSign(privKey_UserInput);
        sign_BackendValue.initSign(privKey_BackendValue);

        byte[] bytes_UserInput = data.getBytes();
        byte[] bytes_BackendValue = prop.getProperty("framework.admin.password").getBytes();

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

    @Override
    public String encryptCaesarAlgorithm(String data) {
        final int OFFSET = 4;

        String b64encoded = Base64.getEncoder().encodeToString(data.getBytes());
        String reverse = new StringBuffer(b64encoded).reverse().toString();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char) (reverse.charAt(i) + OFFSET));
        }
        return tmp.toString();
    }

    @Override
    public String encryptBlowfishAlgorithm(String data) throws Exception {

        SecretKeySpec key = new SecretKeySpec(data.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));

    }

    @Override
    public String encryptBase64Algorithm(String data) {

        String b64encoded = Base64.getEncoder().encodeToString(data.getBytes());
        String reverse = new StringBuffer(b64encoded).reverse().toString();

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char) (reverse.charAt(i) + OFFSET));
        }
        return tmp.toString();
    }

    @Override
    public String[] encryptAesEncryptionAlgorithm(String data) throws Exception {

        int AES_LENGTH = ConstantsUtils.keyLength128;
        final String AES_TYPE = ConstantsUtils.AesCbcPkc5s;
        KeyGenerator keyGen = KeyGenerator.getInstance(ConstantsUtils.AesText);
        keyGen.init(128);

        Cipher aesCipher = Cipher.getInstance(AES_TYPE); //The encryption type needs to be mentioned
        SecretKey secretKey = keyGen.generateKey();

        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[AES_LENGTH / 8];
        secureRandom.nextBytes(bytes);

        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(bytes));

        byte[] byteDataToEncrypt = data.getBytes();
        byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);

        String test1 = Base64.getEncoder().withoutPadding().encodeToString(byteCipherText);
        String test2 = String.valueOf(AES_LENGTH);

        String[] test = {test1, test2};

        return test;

    }

}