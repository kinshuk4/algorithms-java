package com.vaani.dsa.algo.ds.string;

import java.util.Scanner;
//https://github.com/kchanin/CaesarCipher/blob/master/src/com/chanin/Main.java
public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int stringLength = scanner.nextInt();
        String message = scanner.next();
        int rotation = scanner.nextInt();
        String codedMessage = "";

        for (int i = 0; i < stringLength; i++){

            codedMessage += (char) rotate((int)message.charAt(i), rotation);

        }
        System.out.println(codedMessage);
    }

    public static int rotate(int value, int rotation){
        rotation %= 26;
        if (value > 64 && value < 91) {
            value += rotation;
            if (value > 90) {
                value = 65 + (value % 91);
            }
            return value;
        }else if (value > 96 && value < 123){
            value += rotation;
            if (value > 122){
                value = 97 + (value % 123);
            }
            return value;
        }
        return value;
    }

    static String caesarCipher(String s, int k) {
        k = k % 26;
        StringBuilder passwd = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            int t = (ch + k);

            if (ch >= 'a' && ch <= 'z') {
                if (t > 'z') {
                    t = 'a' + (t - 'z') - 1;
                }
                passwd.append((char) (t));
            } else if (ch >= 'A' && ch <= 'Z') {
                if (t > 'Z') {
                    t = 'A' + (t - 'Z') - 1;
                }
                passwd.append((char) (t));
            } else {
                passwd.append((char) ch);
            }
        }

        return passwd.toString();
    }

}
