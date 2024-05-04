import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test{

    public static void main(String[] args) throws IOException{

        Scanner scanner = new Scanner(System.in);

        //Read Dictionary
        String filename = "dictionary.txt";

        List<String> dictionary = WordChecker.readWordsFromFile(filename);


        //Start Word
        System.out.print("Enter the Start Word: ");
        String userStart = scanner.nextLine();
        String start = WordChecker.capitalizeInput(userStart);

        //End Word
        System.out.print("Enter the End Word: ");
        String userEnd = scanner.nextLine();
        String end = WordChecker.capitalizeInput(userEnd);

        //Check if the word valid
        while(!WordChecker.isInputValid(start, end, dictionary)){
            System.out.println("Your Input is invalid. Please retry");
                 //Start Word
            System.out.print("Enter the Start Word: ");
            userStart = scanner.nextLine();
            start = WordChecker.capitalizeInput(userStart);

            //End Word
            System.out.print("Enter the End Word: ");
            userEnd = scanner.nextLine();
            end = WordChecker.capitalizeInput(userEnd);

        }

        List<WORD> visitedWords = new ArrayList<>();
        List<WORD> activeNode = new ArrayList<>();

        WORD startWord = WORD.makeWord(start, "", 0, gbfc.hn_Now(start, end));

        visitedWords.add(startWord);
        // Make every possible valid combination 
        List<String> wordList = WordChecker.makeWordList(start, dictionary);
        System.out.print(wordList);

        //Link the wordList with the parent
        WORD.updateActiveNode(wordList, startWord, end, activeNode);
        
        System.out.print(activeNode);
        // Check H_N
        System.out.print("H_N Now is: ");
        System.out.print(gbfc.hn_Now(start, end));

        scanner.close();

    }
}

