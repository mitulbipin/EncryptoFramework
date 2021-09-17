package com.java.security.framework.encrypto;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;

import com.java.security.framework.common.ConstantsUtils;

public class IEncryptionImpl implements IEncryptionDeclaration {

//    File fXmlFile = new File("testdata.xml");
//    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    String[] result;

    @Override
    public String[] encrypt_BasicCrypto(byte[] data) {

        byte[] enc = new byte[data.length];
        for (int i = 0; i < data.length; i++)
            enc[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] - 1);

        String encryptedText = new String(enc);
        String decryptedText = new String(IDecryptionImpl.decrypt_BasicCrypto(enc));

        return new String[]{encryptedText,decryptedText};
    }

    @Override
    public String[] encrypt_SubstitutionAlgorithm(String data) {
        StringBuilder sb = new StringBuilder(data.length());

        for (char c : data.toCharArray())
            sb.append(ConstantsUtils.SubstitutionAlgoKeys.charAt((int) c - 32));

        String decryptedText =  IDecryptionImpl.decrypt_SubstitutionAlgorithm(sb.toString());
        result = new String[]{sb.toString(), decryptedText};

        return result;
    }

    public String[] encrypt_RSAEncryption(String data) throws Exception {

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
        byte[] input = data.getBytes();
        cipher.update(input);
        byte[] cipherText = cipher.doFinal();

        Cipher cipherDecrypt = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, pair.getPrivate());
        byte[] decrypt = cipherDecrypt.doFinal(cipherText);

        String encryptedText = new String(cipherText, StandardCharsets.UTF_8);
        String DecryptedText = new String(decrypt, StandardCharsets.UTF_8);

        return new String[] {encryptedText,DecryptedText};
    }

    @Override
    public String[] encryptCaesarAlgorithm(String data) {
        final int OFFSET = 4;

        String b64encoded = Base64.getEncoder().encodeToString(data.getBytes());
        String reverse = new StringBuffer(b64encoded).reverse().toString();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char) (reverse.charAt(i) + OFFSET));
        }

        result = new String[]{tmp.toString(), IDecryptionImpl.decryptCaesarAlgorithm(tmp.toString())};

        return result;
    }

    @Override
    //Encryption and Decryption are in the same method since it's a symmetrical encryption.
    //secretKey is an object which cannot be returned along with a String.
    public String[] encryptBlowfishAlgorithm(String data) throws Exception {

        SecretKeySpec key = new SecretKeySpec(data.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        String encryptedText = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));

        Cipher cipherToDecrypt = Cipher.getInstance("Blowfish");
        cipherToDecrypt.init(Cipher.DECRYPT_MODE, key);

        byte[] encryptedTextToBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decrypted = cipherToDecrypt.doFinal(encryptedTextToBytes);

        String decryptedText = new String(decrypted, StandardCharsets.UTF_8);
       return new String[]{encryptedText, decryptedText};
    }

    @Override
    public String[] encryptBase64Algorithm(String data) {

        String b64encoded = Base64.getEncoder().encodeToString(data.getBytes());
        String reverse = new StringBuffer(b64encoded).reverse().toString();

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char) (reverse.charAt(i) + OFFSET));
        }

        result = new String[]{tmp.toString(), IDecryptionImpl.decryptBase64Algorithm(tmp.toString())};

        return result;
    }

    @Override
    //Encryption and Decryption are in the same method since it's a symmetrical encryption.
    //secretKey is an object which cannot be returned along with a String.
    public String[] encryptAesEncryptionAlgorithm(String data) throws Exception {

        int AES_LENGTH = ConstantsUtils.keyLength128;
        final String AES_TYPE = ConstantsUtils.AesCbcPkc5s;
        KeyGenerator keyGen = KeyGenerator.getInstance(ConstantsUtils.AesText);
        keyGen.init(128); //128, 192, 256

        Cipher aesCipher = Cipher.getInstance(AES_TYPE); //The encryption type needs to be mentioned

        SecretKey secretKey = keyGen.generateKey();
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[AES_LENGTH / 8];
        secureRandom.nextBytes(bytes);

        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(bytes));

        byte[] byteDataToEncrypt = data.getBytes();
        byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);

        String encryptedText = Base64.getEncoder().withoutPadding().encodeToString(byteCipherText);
        String keyLength = String.valueOf(AES_LENGTH);

        Cipher aesCipherForDecryption = Cipher.getInstance(AES_TYPE);
        aesCipherForDecryption.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(bytes));
        byte[] byteOfDecryptedText = aesCipherForDecryption.doFinal(byteCipherText);
        String decryptedText = new String(byteOfDecryptedText);

        return new String[]{encryptedText, keyLength, decryptedText};
    }
}