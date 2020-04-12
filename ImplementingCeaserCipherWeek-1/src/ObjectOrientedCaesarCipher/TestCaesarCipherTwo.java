package ObjectOrientedCaesarCipher;

import ImplementingCeaserCipher.CeaserCipher;
import edu.duke.FileResource;

public class TestCaesarCipherTwo {

    static int[] countLetters(String message) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int count[] = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alphabets.indexOf(ch);
            if (index != -1) {
                count[index]++;
            }
        }
        return count;
    }

    static int maxIndex(int values[]) {
        int maxValue = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                index = i;
            }
        }
        return index;
    }

    private String decrypt(String encrypted, int key) {
        CeaserCipher cc = new CeaserCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
    }

    private String halfOfString(String message, int start) {
        String answer = "";
        for (int k = start; k < message.length(); k += 2) {
            answer = answer + message.charAt(k);
        }
        return answer;
    }

    private int getKey(String s) {
        int[] letterFreqs = countLetters(s);
        int maxindex = maxIndex(letterFreqs);
        int dkey = maxindex - 4;
        if (maxindex < 4) {
            dkey = 26 - (4 - maxindex);
        }
        return dkey;
    }

    public void breakCaesarCipher(String input) {
        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        CaesarCipherTwo c = new CaesarCipherTwo(key1, key2);
        System.out.println("\nEncrypted message:" + input);
        System.out.println("Two keys found: key1= " + key1 + ", key2= " + key2);
        System.out.println("Decrypted message:" + c.decrypt(input));
    }


    public void simpleTest() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo c = new CaesarCipherTwo(17, 3);
        String encrypted = c.encrypt(message);
        System.out.println(encrypted);
        System.out.println(c.decrypt(encrypted));
        breakCaesarCipher("Akag tjw Xibhr awoa aoee xakex znxag xwko");
    }

    public static void main(String args[]) {
        TestCaesarCipherTwo testCaesarCipherTwo = new TestCaesarCipherTwo();
        testCaesarCipherTwo.simpleTest();
    }

}
