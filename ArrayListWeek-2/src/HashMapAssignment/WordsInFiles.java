package HashMapAssignment;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {

    private HashMap<String, ArrayList> wordToFile;

    public WordsInFiles() {
        wordToFile = new HashMap<>();
    }

    void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (!wordToFile.containsKey(word)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(f.getName());
                wordToFile.put(word, list);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list = wordToFile.get(word);
                if (!list.contains(f.getName())) {
                    list.add(f.getName());
                }
            }
        }
    }

    void buildWordFileMap() {
        wordToFile.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    int maxNumber() {
        int maxNum = Integer.MIN_VALUE;
        for (ArrayList s : wordToFile.values()) {
            if (s.size() > maxNum) {
                maxNum = s.size();
            }
        }
        return maxNum;
    }

    ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        int count = 0;
        for (String word : wordToFile.keySet()) {
            int tempCount = wordToFile.get(word).size();
            if (tempCount == number) {
                words.add(word);
                count++;
            }
        }
        System.out.println("Total words repeated with " + number + " times is " + count);
        return words;
    }

    void printFilesIn(String word) {
        System.out.println("\nThe word " + word + " is in the following files: ");
        for (String s : wordToFile.keySet()) {
            if (s.equals(word)) {
                ArrayList wordAndFiles = wordToFile.get(s);
                for (int i = 0; i < wordAndFiles.size(); i++) {
                    System.out.println(wordAndFiles.get(i));
                }
            }
        }
    }

    void tester() {
        buildWordFileMap();
        ArrayList wordsInNumFiles = wordsInNumFiles(1);
        System.out.println("The words are:");
        for (int i = 0; i < wordsInNumFiles.size(); i++) {
            System.out.println(wordsInNumFiles.get(i));
        }
        System.out.println("\nMaximum number of words in all the files given = " + maxNumber());
        printFilesIn("birds");
        System.out.println("\n" + "The map is ");
        for (String s : wordToFile.keySet()) {
            System.out.println(s + wordToFile.get(s));
        }

    }

    public static void main(String args[]) {
        WordsInFiles wordsInFiles = new WordsInFiles();
        wordsInFiles.tester();
    }
}
