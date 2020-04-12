package ImplementingCeaserCipher;

public class WordPlay {

    static boolean isVowel(char ch) {
        // System.out.println("Is the character '" + ch + "' is a vowel ");
        String lowCaseVowelString = "aeiou", upCaseVowel = "AEIOU";
        if (lowCaseVowelString.contains("" + ch) || upCaseVowel.contains("" + ch)) {
            return true;
        }
        return false;
    }

    static void testIsVowel() {
        System.out.println(isVowel('c'));
        System.out.println(isVowel('A'));
        System.out.println(isVowel('k'));
        System.out.println(isVowel('O'));
        System.out.println(isVowel('U'));

    }

    static String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(phrase.charAt(i))) {
                sb.append(ch);
            } else {
                sb.append(phrase.charAt(i));
            }
        }
        return sb.toString();
    }

    static void testReplaceVowel() {
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(replaceVowels("Old id gold", '#'));
        System.out.println(replaceVowels("Atlantic Ocean", '~'));
    }

    static String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder();
        int charCount = 0;
        for (int i = 0; i < phrase.length(); i++) {
            charCount++;
            if (phrase.charAt(i) == Character.toLowerCase(ch) || phrase.charAt(i) == Character.toUpperCase(ch)) {
                if (charCount % 2 != 0 && i % 2 == 0) {
                    sb.append('*');
                } else {
                    sb.append("+");
                }

            } else {
                sb.append(phrase.charAt(i));
            }
        }
        return sb.toString();
    }

    static void testEmphasize() {
        System.out.println(emphasize("Hello World", 'o'));
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

    public static void main(String args[]) {

        // testIsVowel();
        //testReplaceVowel();
        testEmphasize();
    }

}
