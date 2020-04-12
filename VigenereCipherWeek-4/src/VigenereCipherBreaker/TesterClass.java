package VigenereCipherBreaker;

import edu.duke.FileResource;

public class TesterClass {

    public static void main(String args[]) {
//        CaesarCipher caesarCipher=new CaesarCipher(10);
//        FileResource resource=new FileResource("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/VigenereTestData/titus-small.txt");
//        String message=resource.asString();
//        System.out.println(caesarCipher.decrypt(message));

        VigenereBreaker vigenereBreaker = new VigenereBreaker();
//        vigenereBreaker.testSliceString();
//        vigenereBreaker.breakVigenere();

        vigenereBreaker.breakVigenere();
    }

}
