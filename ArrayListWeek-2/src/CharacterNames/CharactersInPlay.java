package CharacterNames;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;

    public CharactersInPlay() {
        names = new ArrayList<>();
        counts = new ArrayList<>();
    }

    void update(String person) {
        int index = names.indexOf(person);
        if (index != -1) {
            counts.set(index, counts.get(index) + 1);
        } else {
            names.add(person);
            counts.add(1);
        }
    }

    void findAllCharacters() {
        FileResource resource = new FileResource();
        String firstOccur = "";
        for (String line : resource.lines()) {
            if (line.indexOf(".") != -1) {
                firstOccur = line.substring(0, line.indexOf("."));
                update(firstOccur);
            }
        }
    }

    void tester() {
        findAllCharacters();
        for (int i = 0; i < names.size(); i++) {
            if (counts.get(i) >= 1) {
                System.out.println(names.get(i) + "\t" + counts.get(i));
            }
        }
        charactersWithNumParts(5, 12);
    }

    void charactersWithNumParts(int num1, int num2) {
        System.out.println("Characters that have between " + num1 + " and " + num2 + " lines:");
        for (int i = 0; i < names.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(names.get(i) + "\t" + counts.get(i));
            }
        }
    }

    public static void main(String args[]) {
        CharactersInPlay charactersInPlay = new CharactersInPlay();
        charactersInPlay.tester();
    }

}
