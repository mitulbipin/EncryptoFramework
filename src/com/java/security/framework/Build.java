package com.java.security.framework;

import com.java.security.framework.common.commonMethods;
import com.java.security.framework.common.ConstantsUtils;

import java.util.Scanner;

public class Build {
    static Scanner input = new Scanner(System.in).useDelimiter("\n");
    public static String data, repetitionOption;
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        System.out.println(ConstantsUtils.frameworkTitle);
        System.out.println(ConstantsUtils.frameworkLines_Horizontal);
        do {
            System.out.print("\n" + ConstantsUtils.inputText);
            data = input.next();

            if (count != 1)
                commonMethods.additionOfAlgorithm(); //Password field

            commonMethods.algorithmSelector(data);
            count++;
            System.out.print("\n" + ConstantsUtils.RepetitionText);
            repetitionOption = input.next();
        } while (repetitionOption.equals("Y"));
    }
}
