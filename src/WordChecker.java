import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordChecker{

    // Take every word in dictionary.txt and save it into an array
    public static List<String> readWordsFromFile(String filename) throws IOException{
        
        String filePath = "./src/DICTIONARY/" + filename;
        List<String> dictionary = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String word; 
        while((word = reader.readLine()) != null){
            dictionary.add(word); 
        }
        reader.close();
        return dictionary;
    }

    // Check if the word is inside the dictionary
    public static boolean isWordInDict(String word, List<String> dictionary){
        return dictionary.contains(word);
    }

    // Check if the word already visited
    public static boolean isWordVisited(String word, List<String> visitedList){
        return visitedList.contains(word);
    }

    // Capitalized user inputed string
    public static String capitalizeInput(String input){
        return input.toUpperCase();
    }

    // Generate every possible valid configuration
    public static List<String> makeWordList(String input, List<String> dictionary){
        List<String> permutation = new ArrayList<>();
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        for (int i=0; i<input.length(); i++){
            char[] charArray = input.toCharArray();
            char inputChar = charArray[i];
            for (char c:alphabet){
                if(c != inputChar){
                    charArray[i] = c;
                    String candidate = new String(charArray);
                    if (isWordInDict(candidate, dictionary)){permutation.add(candidate);}
                }
            }
        }
        return permutation;
    }

    public static boolean isInputValid(String start, String end, List<String> dictionary){
        return (start.length() == end.length()) && (isWordInDict(start, dictionary)) && (isWordInDict(end, dictionary));
    }

}

