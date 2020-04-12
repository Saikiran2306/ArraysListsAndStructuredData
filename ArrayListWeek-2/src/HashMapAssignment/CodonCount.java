package HashMapAssignment;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> codonCount;

    public CodonCount() {
        codonCount = new HashMap<>();
    }

    void buildCodonMap(int start, String dna) {
        int count = 0;
        codonCount.clear();
        for (int i = start; i < dna.length() - 2; i += 3) {
            String codon = dna.substring(i, i + 3);
            if (codonCount.containsKey(codon)) {
                int getCount = codonCount.get(codon);
                codonCount.put(codon, getCount + 1);
            } else {
                count++;
                codonCount.put(codon, 1);
            }
        }
        System.out.println("Reading frame starting with " + start +
                ", results in " + count + " unique codons");
    }

    String getMostCommonCodon() {
        String mostRepeatedCodon = "";
        int mostRepeatedCodonCount = Integer.MIN_VALUE;
        for (String s : codonCount.keySet()) {
            if (codonCount.get(s) > mostRepeatedCodonCount) {
                mostRepeatedCodonCount = codonCount.get(s);
                mostRepeatedCodon = s;
            }
        }
        System.out.println("The most repeated codon  with count " + mostRepeatedCodonCount + " is ");
        return mostRepeatedCodon;
    }

    void printCodonCounts(int start, int end) {
        System.out.println("The codons with count between " + start + " and " + end + " are ");
        for (String s : codonCount.keySet()) {
            int count = codonCount.get(s);
            if (count >= start && count <= end) {
                System.out.println(s + " " + count);
            }
        }
    }

    void test() {
        int start = 2;
        String dna = "CGTTCAAGTTCAA";
        dna = dna.toUpperCase();
        buildCodonMap(start, dna);
        System.out.println(getMostCommonCodon());
        printCodonCounts(1, 5);
    }

    public static void main(String args[]) {
        CodonCount codonCount = new CodonCount();
        codonCount.test();
    }

}
