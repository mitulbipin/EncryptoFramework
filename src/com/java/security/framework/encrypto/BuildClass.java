package com.java.security.framework.encrypto;

import java.util.Scanner;

import com.java.security.framework.common.BuildClassMethods;
import com.java.security.framework.common.ConstantsUtils;

public class BuildClass {

    static Scanner input = new Scanner(System.in).useDelimiter("\n");
    //	static IEncryptionImpl crypto = new IEncryptionImpl();
    static String data, repetitionOption;
    private static int count = 0;

    public static void main(String args[]) throws Exception {
        System.out.println(ConstantsUtils.frameworkTitle);
        System.out.println(ConstantsUtils.frameworkLines_Horizontal);
        do {
            System.out.print("\n" + ConstantsUtils.inputText);
            data = input.next();

            if (count != 1)
                BuildClassMethods.additionOfAlgorithm(); //Password field

            BuildClassMethods.algorithmSelector(data);
            count++;
            System.out.print("\n" + ConstantsUtils.RepetitionText);
            repetitionOption = input.next();
        } while (repetitionOption.equals("Y"));
    }

}