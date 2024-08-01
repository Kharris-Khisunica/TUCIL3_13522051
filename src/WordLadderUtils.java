import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

class WordLadderUtils{
    public static List<String> readInput(Scanner scanner, Set<String> dict){
        String start;
        String goal;
        while(true){
            System.out.print("Please input the Start word: ");
            start = scanner.nextLine().toUpperCase().trim();
            System.out.print("Please input the Goal Word: ");
            goal = scanner.nextLine().toUpperCase().trim();
            
            if (isInDict(start, dict) && isInDict(goal,dict) && start.length() == goal.length()){
                break;
            } else{
                System.out.println("Ups. It seems like your words are either not the same length or not in the dictionary.");
                System.out.println();
            }
        }
        return Arrays.asList(start,goal);
    }

    public static boolean isInDict(String word, Set<String> dict){
        return dict.contains(word);
    }

    public static int getHN(String word_now, String word_goal){
        int missmatched = 0;
        
        for (int i=0; i<word_now.length(); i++){
            if(word_now.charAt(i) != word_goal.charAt(i)){
                missmatched++;
            }
        }
        return missmatched;
    }

    public static List<String> getAdjWords(String word, Set<String> dict){
        List<String> adjWords = new ArrayList<>();
        char[] wordChars = word.toCharArray();

        for (int i=0; i<wordChars.length; i++){
            char oriChar = wordChars[i];
            for (char c = 'A'; c<='Z'; c++){
                if (c != oriChar){
                wordChars[i] = c;
                String newWord = new String(wordChars);
                if (dict.contains(newWord)){
                    adjWords.add(newWord);
                    }
                }
            }
            wordChars[i] = oriChar;
        }
        return adjWords;
    }

    public static List<String> constructPath(WordNode node){
        List<String> path = new ArrayList<>();
        while (node != null){
            path.add(0, node.word);
            node = node.parent;
        }

        return path;
    }

    public static class WordLadderResult{
        private List<String> wordLadderPath;
        private int visitedNum;
        private long exectime;

        public WordLadderResult(List<String> wordLadderPath, int visitedNum, long exectime){
            this.wordLadderPath = wordLadderPath;
            this.visitedNum = visitedNum;
            this.exectime = exectime;
        }
    
        public List<String> getWordLadderPath(){
            return this.wordLadderPath;
        }
    
        public int getVisitedNum(){
            return this.visitedNum;
        }
        
        public long getExecTime(){
            return this.exectime;
        }
    }

    public static String highlightDiff(String word1, String goal){
        StringBuilder result = new StringBuilder();
        String Green = "\u001B[32m";
        String Reset = "\u001B[0m";

        for (int i=0; i<word1.length(); i++){
            if (word1.charAt(i) == goal.charAt(i)){
                result.append(Green).append(word1.charAt(i)).append(Reset);
            }
            else{
                result.append(word1.charAt(i));
            }
        }

        return result.toString();
    }

    public static void printPath(List<String> wlPath){
        String goal = wlPath.get(wlPath.size()-1);
        for (int i=0; i<wlPath.size(); i++){
            String wordNow = wlPath.get(i);
            
            if(i == wlPath.size()-1){
                System.out.print(highlightDiff(goal, goal));
            }
            else{
                System.out.print(highlightDiff(wordNow, goal) + "->");
            }
            

        }
    }

    
    
}

