package MostFrequentWord;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index != -1) {
                myFreqs.set(index, myFreqs.get(index) + 1);
            } else {
                myWords.add(word);
                myFreqs.add(1);
            }
        }


    }

    int findIndexOfMax() {
        int maxIndex = 0;
        for (int i = 1; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }

        }
        return maxIndex;
    }

    void tester() {
        findUnique();
        System.out.println("The number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        }
        int maxCountIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(maxCountIndex) + " " + myFreqs.get(maxCountIndex));
    }

    public static void main(String args[]) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
