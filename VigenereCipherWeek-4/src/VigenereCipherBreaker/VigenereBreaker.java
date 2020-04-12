package VigenereCipherBreaker;

import java.io.File;
import java.util.*;

import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String s = sliceString(encrypted, i, klength);
            int a = caesarCracker.getKey(s);
            key[i] = a;
        }
        return key;
    }

    public void breakVigenere() {
        //WRITE YOUR CODE HERE
//        FileResource resource = new FileResource();
//        String s = resource.asString();
//        int[] key = tryKeyLength(s, 5, 'e');
//        VigenereCipher vc = new VigenereCipher(key);
//        String msg = vc.decrypt(s);
//        System.out.println(msg);
//
//        FileResource resource = new FileResource();
//        String s = resource.asString();
//        FileResource fileDict = new FileResource("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/VigenereProgram/dictionaries/English");
//        HashSet<String> hashSet = readDictionary(fileDict);
//        String decyptedMessage = breakForLanguage(s, hashSet);
//        System.out.println(decyptedMessage);

        FileResource resource = new FileResource("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/VigenereTestData/athens_keyflute.txt");
        String message = resource.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File d : dr.selectedFiles()) {
            FileResource resource2 = new FileResource(d.toString());
            HashSet<String> result = new HashSet<String>();
            for (String line : resource2.lines()) {
                line = line.toLowerCase();
                result.add(line);
            }
            languages.put(d.getName(), result);
            //System.out.println("Finished reading " + d.getName());
        }
        HashMap<String, String> decrypted = breakForAllLanguages(message, languages);
        System.out.println(decrypted.get("English"));

    }

    public void testSliceString() {
        System.out.println(sliceString("abcdefghijklm", 0, 3));
        System.out.println(sliceString("abcdefghijklm", 1, 3));
        System.out.println(sliceString("abcdefghijklm", 2, 3));

    }

    public HashSet readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();
        for (String s : fr.words()) {
            s = s.toLowerCase();
            dictionary.add(s);
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String words[] = message.split("\\W+");
        int count = 0;
        for (String word : words) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max = Integer.MIN_VALUE;
        char c = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted, i, c);
            VigenereCipher vc = new VigenereCipher(key);
            String decryptedMeaage = vc.decrypt(encrypted);
            int a = countWords(decryptedMeaage, dictionary);
            if (a > max) {
                max = a;
            }
        }
        for (int j = 1; j <= 100; j++) {
            int[] key = tryKeyLength(encrypted, j, c);
            VigenereCipher vc = new VigenereCipher(key);
            String decryptedMeaage = vc.decrypt(encrypted);
            int a = countWords(decryptedMeaage, dictionary);
            if (a == max) {
                return decryptedMeaage;
            }
        }
        return null;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (String s : dictionary) {
            String slower = s.toLowerCase();
            for (char c : slower.toCharArray()) {
                if (hm.containsKey(c)) {
                    hm.put(c, hm.get(c) + 1);
                } else {
                    hm.put(c, 1);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        char maxChar = ' ';
        for (char c : hm.keySet()) {
            if (hm.get(c) > max) {
                max = hm.get(c);
                maxChar = c;
            }
        }
        return maxChar;
    }

    public HashMap<String, String> breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages) {
        HashMap<String, String> decrpytedMessages = new HashMap<String, String>();
        String language = "";
        int wordcount = 0;
        for (String lang : languages.keySet()) {
            System.out.println("Currently breaking into " + lang);
            String decrypted_string = breakForLanguage(encrypted, languages.get(lang));
            int count = countWords(decrypted_string, languages.get(lang));
            if (wordcount < count) {
                wordcount = count;
                language = lang;
            }
            System.out.println();
            decrpytedMessages.put(lang, decrypted_string);
        }
        System.out.println("The language of this message is " + language);
        System.out.println(wordcount + " valid words\n");
        return decrpytedMessages;
    }

}
