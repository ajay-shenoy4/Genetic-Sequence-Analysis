///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Analysis of Genetic Sequences
// Course:          CS 200, Summer 2024
//
// Author:          Ajay Shenoy
// Email:           ashenoy3@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No citations used.
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. Describe the problem you wrote the program to solve: I wrote a program
// to analyze and calculate many aspects of a genetic sequence, such as the
// frequency of certain letters, lengths, number of sequences, and
// most common letters (nucleotides).
// 2. Why did you choose arrays vs ArrayLists? In other words, what are the
//    differences and how did you take those into account?
// I chose to use arrays for the input and storage of DNA sequences due to their fixed
// size nature, this is appropriate since the number of sequences is defined by the user
// input and is constant during processing.
// 3. How did you decide which test cases to create?
// I considered many possible scenarios when making my test cases and made sure all of
// the scenarios were covered. I covered basic functionality, lack of input, a lot of input,
// varied inputs, and other errors.
// 4. What did you learn from this assignment:
// In this assignment I learned how to incorporate biological concepts into computer programing
// and how to implement genetic concepts. I learned about arrays and handling as well as testing.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.NoSuchElementException;


/**
 * This application processes genetic sequences to compute various genetic metrics.
 *
 * @author Ajay Shenoy
 */
public class H10CustomApp {

    /**
     * Calculates the nucleotide frequencies across all provided DNA sequences.
     *
     * @param sequences An array of DNA sequences.
     * @return frequencyMap A map with nucleotide as the key and its frequency as the value.
     * @author Ajay Shenoy
     */
    public static Map<Character, Integer> calculateNucleotideFrequencies(String[] sequences) {
        if (sequences == null) {
            throw new IllegalArgumentException("Sequences array cannot be null");
        }
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (String sequence : sequences) {
            if (sequence != null) {
                for (char nucleotide : sequence.toUpperCase().toCharArray()) {
                    if ("ATCG".indexOf(nucleotide) != -1) {
                        frequencyMap.put(nucleotide, frequencyMap.getOrDefault(nucleotide, 0) + 1);
                    }
                }
            }
        }
        return frequencyMap;
    }

    /**
     * Calculates the GC content of each given DNA sequence.
     *
     * @param sequences An array of DNA sequences.
     * @return gcContents A list of GC content percentages for each sequence.
     */
    public static List<Double> calculateGCContentForAll(String[] sequences) {
        if (sequences == null) {
            throw new IllegalArgumentException("Sequences array cannot be null");
        }
        List<Double> gcContents = new ArrayList<>();
        for (String sequence : sequences) {
            if (sequence != null && !sequence.isEmpty()) {
                int gCount = 0;
                int cCount = 0;
                for (char nucleotide : sequence.toUpperCase().toCharArray()) {
                    if (nucleotide == 'G') gCount++;
                    else if (nucleotide == 'C') cCount++;
                }
                double gcContent = (gCount + cCount) * 100.0 / sequence.length();
                gcContents.add(gcContent);
            } else {
                gcContents.add(0.0); // Assuming GC content is 0% for empty sequences
            }
        }
        return gcContents;
    }

    /**
     * Calculates the AT content of each given DNA sequence.
     *
     * @param sequences An array of DNA sequences.
     * @return atContents A list of AT content percentages for each sequence.
     */
    public static List<Double> calculateATContentForAll(String[] sequences) {
        if (sequences == null) {
            throw new IllegalArgumentException("Sequences array cannot be null");
        }
        List<Double> atContents = new ArrayList<>();
        for (String sequence : sequences) {
            if (sequence != null && !sequence.isEmpty()) {
                int aCount = 0;
                int tCount = 0;
                for (char nucleotide : sequence.toUpperCase().toCharArray()) {
                    if (nucleotide == 'A') aCount++;
                    else if (nucleotide == 'T') tCount++;
                }
                double atContent = (aCount + tCount) * 100.0 / sequence.length();
                atContents.add(atContent);
            } else {
                atContents.add(0.0); // Assuming AT content is 0% for empty sequences
            }
        }
        return atContents;
    }

    /**
     * Calculates the distribution of sequence lengths in a list of DNA sequences.
     *
     * @param sequences An array of DNA sequences.
     * @return lengthDistribution A map with sequence length as the key and its frequency
     * as the value.
     */
    public static Map<Integer, Integer> calculateSequenceLengthDistribution(String[] sequences) {
        if (sequences == null) {
            throw new IllegalArgumentException("Sequences array cannot be null");
        }
        Map<Integer, Integer> lengthDistribution = new HashMap<>();
        for (String sequence : sequences) {
            if (sequence != null) {
                int length = sequence.length();
                lengthDistribution.put(length, lengthDistribution.getOrDefault(length, 0) + 1);
            }
        }
        return lengthDistribution;
    }

    /**
     * Finds the most common nucleotides across all provided DNA sequences.
     * If there are ties, all most common nucleotides are returned.
     *
     * @param sequences An array of DNA sequences.
     * @return mostCommonNucleotides A list of the most frequently occurring nucleotides.
     */
    public static List<Character> findMostCommonNucleotides(String[] sequences) {
        if (sequences == null) {
            throw new IllegalArgumentException("Sequences array cannot be null");
        }
        Map<Character, Integer> frequencyMap = calculateNucleotideFrequencies(sequences);
        if (frequencyMap.isEmpty()) {
            return new ArrayList<>();
        }

        int maxFrequency = frequencyMap.values().stream().max(Integer::compare).orElse(0);
        List<Character> mostCommonNucleotides = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                mostCommonNucleotides.add(entry.getKey());
            }
        }
        return mostCommonNucleotides;
    }

    /**
     * Generates the complementary DNA strand for the given input strand.
     *
     * @param strand the input DNA strand.
     * @return the complementary DNA strand.
     */
    public static String generateComplementaryStrand(String strand) {
        StringBuilder complementaryStrand = new StringBuilder();
        for (char nucleotide : strand.toCharArray()) {
            switch (nucleotide) {
                case 'A':
                    complementaryStrand.append('T');
                    break;
                case 'T':
                    complementaryStrand.append('A');
                    break;
                case 'C':
                    complementaryStrand.append('G');
                    break;
                case 'G':
                    complementaryStrand.append('C');
                    break;
                default:
                    throw new IllegalArgumentException("Invalid nucleotide: " + nucleotide);
            }
        }
        return complementaryStrand.toString();
    }

    /**
     * Main method to interact with the user and process DNA sequences.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the number of sequences:");
            if (!scanner.hasNextLine()) {
                throw new NoSuchElementException("No input found for number of sequences");
            }
            int numSequences = Integer.parseInt(scanner.nextLine().trim());

            if (numSequences <= 0) {
                throw new IllegalArgumentException("Number of sequences must " +
                        "be a positive integer");
            }

            String[] sequences = new String[numSequences];

            for (int i = 0; i < numSequences; i++) {
                System.out.println("Enter sequence " + (i + 1) + ":");
                if (!scanner.hasNextLine()) {
                    throw new NoSuchElementException("No input found for sequence " + (i + 1));
                }
                sequences[i] = scanner.nextLine().trim();
            }

            System.out.println("Nucleotide Frequencies: " +
                    calculateNucleotideFrequencies(sequences));
            System.out.println("GC Content of each sequence: " +
                    calculateGCContentForAll(sequences));
            System.out.println("AT Content of each sequence: " +
                    calculateATContentForAll(sequences));
            System.out.println("Sequence Length Distribution: " +
                    calculateSequenceLengthDistribution(sequences));
            System.out.println("Most Common Nucleotides: " +
                    findMostCommonNucleotides(sequences));

            // Generate and display the complementary strands for each sequence
            System.out.println("Complementary Strands:");
            for (int i = 0; i < sequences.length; i++) {
                String complementaryStrand = generateComplementaryStrand(sequences[i]);
                System.out.println("Sequence " + (i + 1) + ": " + sequences[i]);
                System.out.println("Complementary Strand: " + complementaryStrand);
            }

        } catch (NoSuchElementException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format. Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
