package HashMapAssignment;

import edu.duke.*;

import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> words;
    private ArrayList<String> usedCategories;
    private int replacedWords;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentPrograms/ArraysAndStructuredData/ArrayListWeek-2/src/VerbsAndFruits/datalong";

    public GladLibMap() {
        usedCategories = new ArrayList<>();
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        words = new ArrayList<String>();
        replacedWords = 0;
    }

    private GladLibMap(String source) {
        usedCategories = new ArrayList<>();
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        words = new ArrayList<String>();
        replacedWords = 0;
    }

    public void initializeFromSource(String source) {


        String[] labels = {"country", "noun", "animal",
                "adjective", "name", "color",
                "timeframe", "verb", "fruit"};
        for (String s : labels) {
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {

        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        addUsedCategory(label);
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));
        while (true) {
            int index = words.indexOf(sub);
            if (index == -1) {
                words.add(sub);
                break;
            } else {
                sub = getSubstitute(w.substring(first + 1, last));
            }
        }
        return sub;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate(dataSourceDirectory + "/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println(" Number of words replaced " + words.size());
        System.out.println("Number of words in all the Arraylists in the HashMap = " + totalWordsInMap());
        totalWordsConsidered();
    }

    int totalWordsInMap() {
        int totalWords = 0;
        for (ArrayList v : myMap.values()) {
            totalWords = totalWords + v.size();
        }
        return totalWords;
    }

    void addUsedCategory(String label) {
        if (usedCategories.indexOf(label) == -1) {
            usedCategories.add(label);
        }
    }

    void totalWordsConsidered() {
        ArrayList<String> categories = new ArrayList<>();
        int total = 0;
        System.out.println("\nCategories used in this story are ");
        for (int index = 0; index < usedCategories.size(); index++) {
            String category = usedCategories.get(index);
            categories = myMap.get(category);
            System.out.println("Category: " + category + "\tWords in category: "
                    + categories.size());
            total += categories.size();
        }
        System.out.println("Grand total of possible words: " + total);
    }

    public static void main(String args[]) {
        GladLibMap gladLibMap = new GladLibMap();
        gladLibMap.makeStory();
    }

}