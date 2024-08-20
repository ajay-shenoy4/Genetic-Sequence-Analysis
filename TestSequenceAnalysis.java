import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * This contains testing methods for the H10CustomApp class.
 *
 * @author Ajay Shenoy
 */
public class TestH10CustomApp {

    /**
     * This calls the testH10CustomApp method and prints out the result.
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        System.out.println("Success: " + testH10CustomApp());
        System.out.println("Complementary Strand Test Success: " + testComplementaryStrand());
    }

    /**
     * Runs a single test case and compares the results with the expected values.
     *
     * @param sequences the input sequences for the test case. This is an array of
     *                  strings where each string represents a nucleotide sequence.
     * @param expectedFrequencies the expected nucleotide frequencies in the sequences.
     *                            This is a string representation of a map where keys
     *                            are nucleotide characters and values are their counts.
     * @param expectedGCContents the expected GC content percentages for each sequence.
     *                           This is a list of doubles representing the percentage
     *                           of GC content for each sequence.
     * @param expectedATContents the expected AT content percentages for each sequence.
     *                           This is a list of doubles representing the percentage
     *                           of AT content for each sequence.
     * @param expectedLengthDistribution the expected distribution of sequence lengths.
     *                                  This is a string representation of a map where
     *                                   keys are sequence lengths and values are their counts.
     * @param expectedMostCommon the expected most common nucleotides in the sequences.
     *                           This is a string representation of a list of nucleotides
     *                           that are most frequent in the sequences.
     *
     * @return true if the test case fails, false otherwise
     */

    public static boolean testH10CustomApp() {
        boolean error = false;

        // Test Case 1: Basic Input
        {
            String[] sequences = {"ATCG", "GATTACA", "CCGG"};
            // Expected Results
            String expectedFrequencies = "{A=4, C=4, T=3, G=4}";
            List<Double> expectedGCContents = List.of(50.0, 28.571428571428573, 100.0);
            List<Double> expectedATContents = List.of(50.0, 71.42857142857143, 0.0);
            String expectedLengthDistribution = "{4=2, 7=1}";
            String expectedMostCommon = "[A, C, G]";

            String actualFrequencies =
                    H10CustomApp.calculateNucleotideFrequencies(sequences).toString();
            List<Double> actualGCContents =
                    H10CustomApp.calculateGCContentForAll(sequences);
            List<Double> actualATContents =
                    H10CustomApp.calculateATContentForAll(sequences);
            String actualLengthDistribution =
                    H10CustomApp.calculateSequenceLengthDistribution(sequences).toString();
            String actualMostCommon =
                    H10CustomApp.findMostCommonNucleotides(sequences).toString();

            if (!actualFrequencies.equals(expectedFrequencies)) {
                error = true;
                System.out.println("Test Case 1 - Nucleotide Frequencies: Expected: " +
                        expectedFrequencies + " actual: " + actualFrequencies);
            }
            if (!actualGCContents.equals(expectedGCContents)) {
                error = true;
                System.out.println("Test Case 1 - GC Contents: Expected: " +
                        expectedGCContents + " actual: " + actualGCContents);
            }
            if (!actualATContents.equals(expectedATContents)) {
                error = true;
                System.out.println("Test Case 1 - AT Contents: Expected: " +
                        expectedATContents + " actual: " + actualATContents);
            }
            if (!actualLengthDistribution.equals(expectedLengthDistribution)) {
                error = true;
                System.out.println("Test Case 1 - Length Distribution: Expected: " +
                        expectedLengthDistribution + " actual: " + actualLengthDistribution);
            }
            if (!actualMostCommon.equals(expectedMostCommon)) {
                error = true;
                System.out.println("Test Case 1 - Most Common Nucleotides: Expected: " +
                        expectedMostCommon + " actual: " + actualMostCommon);
            }
        }

        // Test Case 2: Sequences with Only One Nucleotide
        {
            String[] sequences = {"AAAA", "TTTT", "CCCC", "GGGG"};
            // Expected Results
            String expectedFrequencies = "{A=4, C=4, T=4, G=4}";
            List<Double> expectedGCContents = List.of(0.0, 0.0, 100.0, 100.0);
            List<Double> expectedATContents = List.of(100.0, 100.0, 0.0, 0.0);
            String expectedLengthDistribution = "{4=4}";
            String expectedMostCommon = "[A, C, T, G]";

            String actualFrequencies =
                    H10CustomApp.calculateNucleotideFrequencies(sequences).toString();
            List<Double> actualGCContents = H10CustomApp.calculateGCContentForAll(sequences);
            List<Double> actualATContents = H10CustomApp.calculateATContentForAll(sequences);
            String actualLengthDistribution =
                    H10CustomApp.calculateSequenceLengthDistribution(sequences).toString();
            String actualMostCommon =
                    H10CustomApp.findMostCommonNucleotides(sequences).toString();

            if (!actualFrequencies.equals(expectedFrequencies)) {
                error = true;
                System.out.println("Test Case 2 - Nucleotide Frequencies: Expected: "
                        + expectedFrequencies + " actual: " + actualFrequencies);
            }
            if (!actualGCContents.equals(expectedGCContents)) {
                error = true;
                System.out.println("Test Case 2 - GC Contents: Expected: " +
                        expectedGCContents + " actual: " + actualGCContents);
            }
            if (!actualATContents.equals(expectedATContents)) {
                error = true;
                System.out.println("Test Case 2 - AT Contents: Expected: " +
                        expectedATContents + " actual: " + actualATContents);
            }
            if (!actualLengthDistribution.equals(expectedLengthDistribution)) {
                error = true;
                System.out.println("Test Case 2 - Length Distribution: Expected: " +
                        expectedLengthDistribution + " actual: " + actualLengthDistribution);
            }
            if (!actualMostCommon.equals(expectedMostCommon)) {
                error = true;
                System.out.println("Test Case 2 - Most Common Nucleotides: Expected: " +
                        expectedMostCommon + " actual: " + actualMostCommon);
            }
        }

        // Test Case 3: Mixed Content with Empty Sequences
        {
            String[] sequences = {"ATCG", "GATTACA", "", "CCGG", ""};
            // Expected Results
            String expectedFrequencies = "{A=4, C=4, T=3, G=4}";
            List<Double> expectedATContents = List.of(50.0, 71.42857142857143, 0.0, 0.0, 0.0);
            List<Double> expectedGCContents =
                    List.of(50.0, 28.571428571428573, 0.0, 100.0, 0.0);
            String expectedLengthDistribution = "{0=2, 4=2, 7=1}";
            String expectedMostCommon = "[A, C, G]";

            String actualFrequencies =
                    H10CustomApp.calculateNucleotideFrequencies(sequences).toString();
            List<Double> actualGCContents = H10CustomApp.calculateGCContentForAll(sequences);
            List<Double> actualATContents = H10CustomApp.calculateATContentForAll(sequences);
            String actualLengthDistribution =
                    H10CustomApp.calculateSequenceLengthDistribution(sequences).toString();
            String actualMostCommon =
                    H10CustomApp.findMostCommonNucleotides(sequences).toString();

            if (!actualFrequencies.equals(expectedFrequencies)) {
                error = true;
                System.out.println("Test Case 3 - Nucleotide Frequencies: Expected: " +
                        expectedFrequencies + " actual: " + actualFrequencies);
            }
            if (!actualGCContents.equals(expectedGCContents)) {
                error = true;
                System.out.println("Test Case 3 - GC Contents: Expected: "
                        + expectedGCContents + " actual: " + actualGCContents);
            }
            if (!actualATContents.equals(expectedATContents)) {
                error = true;
                System.out.println("Test Case 3 - AT Contents: Expected: "
                        + expectedATContents + " actual: " + actualATContents);
            }
            if (!actualLengthDistribution.equals(expectedLengthDistribution)) {
                error = true;
                System.out.println("Test Case 3 - Length Distribution: Expected: "
                        + expectedLengthDistribution + " actual: " + actualLengthDistribution);
            }
            if (!actualMostCommon.equals(expectedMostCommon)) {
                error = true;
                System.out.println("Test Case 3 - Most Common Nucleotides: Expected: "
                        + expectedMostCommon + " actual: " + actualMostCommon);
            }
        }

        // Test Case 4: All Sequences Identical
        {
            String[] sequences = {"ATCGATCG", "ATCGATCG", "ATCGATCG"};
            // Expected Results
            String expectedFrequencies = "{A=6, C=6, T=6, G=6}";
            List<Double> expectedGCContents = List.of(50.0, 50.0, 50.0);
            List<Double> expectedATContents = List.of(50.0, 50.0, 50.0);
            String expectedLengthDistribution = "{8=3}";
            String expectedMostCommon = "[A, C, T, G]";

            String actualFrequencies =
                    H10CustomApp.calculateNucleotideFrequencies(sequences).toString();
            List<Double> actualGCContents = H10CustomApp.calculateGCContentForAll(sequences);
            List<Double> actualATContents = H10CustomApp.calculateATContentForAll(sequences);
            String actualLengthDistribution =
                    H10CustomApp.calculateSequenceLengthDistribution(sequences).toString();
            String actualMostCommon = H10CustomApp.findMostCommonNucleotides(sequences).toString();

            if (!actualFrequencies.equals(expectedFrequencies)) {
                error = true;
                System.out.println("Test Case 4 - Nucleotide Frequencies: Expected: "
                        + expectedFrequencies + " actual: " + actualFrequencies);
            }
            if (!actualGCContents.equals(expectedGCContents)) {
                error = true;
                System.out.println("Test Case 4 - GC Contents: Expected: " +
                        expectedGCContents + " actual: " + actualGCContents);
            }
            if (!actualATContents.equals(expectedATContents)) {
                error = true;
                System.out.println("Test Case 4 - AT Contents: Expected: " +
                        expectedATContents + " actual: " + actualATContents);
            }
            if (!actualLengthDistribution.equals(expectedLengthDistribution)) {
                error = true;
                System.out.println("Test Case 4 - Length Distribution: Expected: " +
                        expectedLengthDistribution + " actual: " + actualLengthDistribution);
            }
            if (!actualMostCommon.equals(expectedMostCommon)) {
                error = true;
                System.out.println("Test Case 4 - Most Common Nucleotides: Expected: " +
                        expectedMostCommon + " actual: " + actualMostCommon);
            }
        }

        // Test Case 5: Sequences with Non-ATCG Characters
        {
            String[] sequences = {"ATCGXYZF", "TACG1234"};
            // Expected Results
            String expectedFrequencies = "{A=2, C=2, T=2, G=2}";
            List<Double> expectedGCContents = List.of(25.0, 25.0);
            List<Double> expectedATContents = List.of(25.0, 25.0);
            String expectedLengthDistribution = "{8=2}";
            String expectedMostCommon = "[A, C, T, G]";

            String actualFrequencies =
                    H10CustomApp.calculateNucleotideFrequencies(sequences).toString();
            List<Double> actualGCContents = H10CustomApp.calculateGCContentForAll(sequences);
            List<Double> actualATContents = H10CustomApp.calculateATContentForAll(sequences);
            String actualLengthDistribution =
                    H10CustomApp.calculateSequenceLengthDistribution(sequences).toString();
            String actualMostCommon = H10CustomApp.findMostCommonNucleotides(sequences).toString();

            if (!actualFrequencies.equals(expectedFrequencies)) {
                error = true;
                System.out.println("Test Case 5 - Nucleotide Frequencies: Expected: " +
                        expectedFrequencies + " actual: " + actualFrequencies);
            }
            if (!actualGCContents.equals(expectedGCContents)) {
                error = true;
                System.out.println("Test Case 5 - GC Contents: Expected: " + expectedGCContents +
                        " actual: " + actualGCContents);
            }
            if (!actualATContents.equals(expectedATContents)) {
                error = true;
                System.out.println("Test Case 5 - AT Contents: Expected: " +
                        expectedATContents + " actual: " + actualATContents);
            }
            if (!actualLengthDistribution.equals(expectedLengthDistribution)) {
                error = true;
                System.out.println("Test Case 5 - Length Distribution: Expected: " +
                        expectedLengthDistribution + " actual: " + actualLengthDistribution);
            }
            if (!actualMostCommon.equals(expectedMostCommon)) {
                error = true;
                System.out.println("Test Case 5 - Most Common Nucleotides: Expected: " +
                        expectedMostCommon + " actual: " + actualMostCommon);
            }
        }

        // Test Case 6: Very Large Sequences
        {
            String[] sequences = {
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                    "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"
            };
            // Expected Results
            String expectedFrequencies = "{A=68, T=63}";
            List<Double> expectedGCContents = List.of(0.0, 0.0);
            List<Double> expectedATContents = List.of(100.0, 100.0);
            String expectedLengthDistribution = "{68=1, 63=1}";
            String expectedMostCommon = "[A]";

            String actualFrequencies =
                    H10CustomApp.calculateNucleotideFrequencies(sequences).toString();
            List<Double> actualGCContents = H10CustomApp.calculateGCContentForAll(sequences);
            List<Double> actualATContents = H10CustomApp.calculateATContentForAll(sequences);
            String actualLengthDistribution =
                    H10CustomApp.calculateSequenceLengthDistribution(sequences).toString();
            String actualMostCommon = H10CustomApp.findMostCommonNucleotides(sequences).toString();

            if (!actualFrequencies.equals(expectedFrequencies)) {
                error = true;
                System.out.println("Test Case 6 - Nucleotide Frequencies: Expected: " +
                        expectedFrequencies + " actual: " + actualFrequencies);
            }
            if (!actualGCContents.equals(expectedGCContents)) {
                error = true;
                System.out.println("Test Case 6 - GC Contents: Expected: " +
                        expectedGCContents + " actual: " + actualGCContents);
            }
            if (!actualATContents.equals(expectedATContents)) {
                error = true;
                System.out.println("Test Case 6 - AT Contents: Expected: " +
                        expectedATContents + " actual: " + actualATContents);
            }
            if (!actualLengthDistribution.equals(expectedLengthDistribution)) {
                error = true;
                System.out.println("Test Case 6 - Length Distribution: Expected: " +
                        expectedLengthDistribution + " actual: " + actualLengthDistribution);
            }
            if (!actualMostCommon.equals(expectedMostCommon)) {
                error = true;
                System.out.println("Test Case 6 - Most Common Nucleotides: Expected: " +
                        expectedMostCommon + " actual: " + actualMostCommon);
            }
        }

        if (error) {
            System.out.println("Error(s) in test cases.");
        } else {
            System.out.println("All test cases passed.");
        }
        return !error;
    }

    /**
     * Test the generateComplementaryStrand function to ensure it returns the correct complementary strand.
     * @return true if all test cases pass, false otherwise
     */
    public static boolean testComplementaryStrand() {
        boolean error = false;

        // Test Case 1: Basic sequences
        {
            String sequence = "ATCG";
            String expectedComplementaryStrand = "TAGC";

            String actualComplementaryStrand = H10CustomApp.generateComplementaryStrand(sequence);

            if (!actualComplementaryStrand.equals(expectedComplementaryStrand)) {
                error = true;
                System.out.println("Test Case 1 - Complementary Strand: Expected: " +
                        expectedComplementaryStrand + " actual: " + actualComplementaryStrand);
            }
        }

        // Test Case 2: Empty sequence
        {
            String sequence = "";
            String expectedComplementaryStrand = "";

            String actualComplementaryStrand = H10CustomApp.generateComplementaryStrand(sequence);

            if (!actualComplementaryStrand.equals(expectedComplementaryStrand)) {
                error = true;
                System.out.println("Test Case 2 - Complementary Strand: Expected: " +
                        expectedComplementaryStrand + " actual: " + actualComplementaryStrand);
            }
        }

        // Test Case 3: Sequence with invalid characters
        {
            String sequence = "ATCGX";
            String expectedComplementaryStrand = "TAGC";

            String actualComplementaryStrand = H10CustomApp.generateComplementaryStrand(sequence);

            if (!actualComplementaryStrand.equals(expectedComplementaryStrand)) {
                error = true;
                System.out.println("Test Case 3 - Complementary Strand: Expected: " +
                        expectedComplementaryStrand + " actual: " + actualComplementaryStrand);
            }
        }

        // Test Case 4: Longer sequence
        {
            String sequence = "GATTACA";
            String expectedComplementaryStrand = "CTAATGT";

            String actualComplementaryStrand = H10CustomApp.generateComplementaryStrand(sequence);

            if (!actualComplementaryStrand.equals(expectedComplementaryStrand)) {
                error = true;
                System.out.println("Test Case 4 - Complementary Strand: Expected: " +
                        expectedComplementaryStrand + " actual: " + actualComplementaryStrand);
            }
        }

        return !error;
    }
}
