package ImplementingCeaserCipher;

import edu.duke.FileResource;
import sun.text.normalizer.UCharacter;

public class CeaserCipher {
    public static String encrypt(String input, int key) {
        StringBuilder encyptedMessage = new StringBuilder();
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabets = alphabets.substring(key) + alphabets.substring(0, key);
        for (int i = 0; i < input.length(); i++) {
            int index = alphabets.indexOf(input.charAt(i));
            if (index != -1) {
                encyptedMessage.append(shiftedAlphabets.charAt(index));
            } else if (Character.isAlphabetic(input.charAt(i))) {
                // getting ascii value of uppercase character
                int getAsciiValue = input.charAt(i) - 32;
                // converting it to the character
                char ch = (char) getAsciiValue;
                // getting the shifted character index of tha above character
                index = alphabets.indexOf(ch);
                //System.out.println(input.charAt(i)+" "+(char)getAsciiValue);
                // converting it to lowercase character
                char lowCase = (char) (shiftedAlphabets.charAt(index) + 32);
                encyptedMessage.append(lowCase);
            } else {
                encyptedMessage.append(input.charAt(i));
            }
        }
        return encyptedMessage.toString();
    }

    static void testCeaser() {
        int key = 23;
//        FileResource fr = new FileResource();
//        String message = fr.asString();
//        String encrypted = encrypt(message, key);
//        System.out.println("Message is \n" + message + "\n" + "key is " + key + "\n" + "Encrypted message is \n" + encrypted );
        System.out.println(encrypt("First Legion", 23));
        System.out.println(encrypt("First Legion", 17));
    }

    public static String encryptTwoKeys(String input, int key1, int key2) {

        StringBuilder encyptedMessage = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {

            if (i % 2 == 0) {
                encyptedMessage.append(getEncryptedChar(input.charAt(i), key1));
            } else {
                encyptedMessage.append(getEncryptedChar(input.charAt(i), key2));
            }
        }
        return encyptedMessage.toString();
    }

    static char getEncryptedChar(char input, int key) {
        char result = input;
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabetsKey = alphabets.substring(key) + alphabets.substring(0, key);
        int index = alphabets.indexOf(input);
        if (index != -1) {
            result = shiftedAlphabetsKey.charAt(index);
        } else if (Character.isAlphabetic(input)) {
            // getting ascii value of uppercase character
            int getAsciiValue = input - 32;
            // converting it to the character
            char ch = (char) getAsciiValue;
            // getting the shifted character index of tha above character
            index = alphabets.indexOf(ch);
            //System.out.println(input.charAt(i)+" "+(char)getAsciiValue);
            // converting it to lowercase character
            char lowCase = (char) (shiftedAlphabetsKey.charAt(index) + 32);
            result = lowCase;
        } else {
            result = input;
        }
        return result;
    }

    static void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeys("First Legion", 23, 17));
    }

    public static void main(String args[]) {
        //testCeaser();
        testEncryptTwoKeys();
    }
}
