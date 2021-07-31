package com.java.security.framework.encrypto;

import com.java.security.framework.common.ConstantsUtils;
import com.java.security.framework.algorithm.AlgorithmOutput;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class IDecryptionImpl {

    public static byte[] decrypt_BasicCrypto(byte[] data) {

        byte[] enc = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            enc[i] = (byte) ((i % 2 == 0) ? data[i] - 1 : data[i] + 1);
        }
        return enc;
    }

    public static String decrypt_SubstitutionAlgorithm(String data) {
        StringBuilder sb = new StringBuilder(data.length());

        for (char c : data.toCharArray())
            sb.append((char) (ConstantsUtils.SubstitutionAlgoKeys.indexOf((int) c) + 32));

        return sb.toString();
    }

    public static String decryptCaesarAlgorithm(String data) {
        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < data.length(); i++) {
            tmp.append((char) (data.charAt(i) - OFFSET));
        }

        String reversed = new StringBuffer(tmp.toString()).reverse().toString();
        return new String(Base64.getDecoder().decode(reversed));
    }

    public String decryptBlowfishAlgorithm(String data) throws Exception {

        SecretKeySpec key = new SecretKeySpec(data.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedTextToBytes = Base64.getDecoder().decode(data);
        byte[] decrypted = cipher.doFinal(encryptedTextToBytes);

        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public static String decryptBase64Algorithm(String data) {
        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < data.length(); i++) {
            tmp.append((char) (data.charAt(i) - OFFSET));
        }

        String reversed = new StringBuffer(tmp.toString()).reverse().toString();
        return new String(Base64.getMimeDecoder().decode(reversed));
    }


}
