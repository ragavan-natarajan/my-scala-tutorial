package online.ragavan;
import java.util.Map;
import static java.util.Map.Entry;
import java.util.HashMap;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Computes the total group count of all the words in the second column in a
 * tab-delimited two column file.
 *
 * The first and second columns represent the article title and text,
 * respectively. This code merely takes the second column across all rows, and
 * computes for each word, the total occurrence of that word across the entire
 * file.
 *
 * Author: Ragavan Natarajan Email: n.ragav@gmail.com
 */
public class WordCount {

    /*
     * Main method
     */
    public static void main(String[] argv) {

        long startTime = System.currentTimeMillis();

        if (argv == null || argv.length != 2) {
                System.out.println("Usage: <input file> <output file>");
                System.exit(1);
        }

        String inputFileName = argv[0];
        File inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
                System.out.println("The input file " + 
                        inputFileName + " was not found");
                System.exit(1);
        }

        String outputFileName = argv[1];
        File outputFile = new File(outputFileName);
        if (outputFile.exists()) {
            System.out.println("The output file " + 
                    outputFileName + " already exists.");
            System.exit(1);
        }

        System.out.println("Counting the words...");

        Map<String, Long> wordCountMap = countWordsInFile(inputFile);
        System.out.println("Counted the words in " + 
                (System.currentTimeMillis() - startTime)+ " ms");

        System.out.println("Writing the word counts to " + outputFileName);
        writeWordCount(outputFile, wordCountMap);

        System.out.println("All tasks completed in " + 
                (System.currentTimeMillis() - startTime) + " ms");
    }

    /**
     * Responsible for writing the count to the output file.
     */
    private static void writeWordCount(File outputFile, 
            Map<String, Long> wordCountMap) {

        try(BufferedWriter out = 
                new BufferedWriter(new FileWriter(outputFile))) {
            for (Map.Entry<String, Long> entry: wordCountMap.entrySet()) {
                out.write(entry.getKey() + "\t" + entry.getValue());
                out.newLine();
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }


    /**
     * Counts for each word its total occurrence in the entire corpus.
     */ 
    private static Map<String, Long> countWordsInFile(File inputFile) {
        Map<String, Long> wordCountMap = new HashMap<>();

        try(BufferedReader input = 
                new BufferedReader(new FileReader(inputFile))) {

            String line = null;

            while ((line = input.readLine()) != null) {
                /*
                 * The line contains title and text, delimited by a TAB. We are
                 * interested in counting the words in the text, so we just
                 * consider that column.
                 */
                String text = line.split("\t")[1];
                String[] words = text.split(" ");
                for (String word: words) {
                    long existingCount = 0;
                    if (wordCountMap.containsKey(word)) {
                           existingCount = wordCountMap.get(word); 
                    }
                    wordCountMap.put(word, ++existingCount);
                }
            }

        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return wordCountMap;
    }
}
