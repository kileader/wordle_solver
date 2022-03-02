package wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Wordle {
    
    public static void main(String[] args) {
        String smallFile = "C:/Users/Kevin/Documents/STS/wordle/src/main/resources/words";
        String bigFile = "C:/Users/Kevin/Documents/STS/wordle/src/main/resources/moreWords";
        ArrayList<char[]> wordChars = new ArrayList<>();
        ArrayList<char[]> moreWordChars = new ArrayList<>();
        
        try (Stream<String> stream = Files.lines(Paths.get(smallFile))) {
            stream.forEach((line) -> {
                String[] lineWords = line.split(" ");
                for (String word : lineWords) {
                    word = word.toUpperCase();
                    wordChars.add(word.toCharArray());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (Stream<String> stream = Files.lines(Paths.get(bigFile))) {
            stream.forEach((line) -> {
                String[] lineWords = line.split(" ");
                for (String word : lineWords) {
                    moreWordChars.add(word.toCharArray());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int[][] letterCounts = getLetterCounts(wordChars);
        displayLetterCounts(letterCounts);
        
    }
    
    public static int[][] getLetterCounts(List<char[]> wordChars) {
        int[][] letterCounts = new int[5][26];
        
        for (char[] chars : wordChars) {
            for (int i = 0; i < 5; i++) {
                char letter = chars[i];
                int ord = letter - 65;
                letterCounts[i][ord]++;
            }
        }
        return letterCounts;
    }
    
    public static void displayLetterCounts(int[][] letterCounts) {
        for (int i = 65; i < 91; i++) {
            char letter = (char) i;
            System.out.print(letter + "\t");
        }
        System.out.println();
        for (int[] placeCounts : letterCounts) {
            for (int letterCount : placeCounts) {
                System.out.print(letterCount + "\t");
            }
            System.out.println();
        }
        
        for (int i = 0; i < 26; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                count += letterCounts[j][i];
            }
            System.out.print(count + "\t");
        }
    }
    
}
