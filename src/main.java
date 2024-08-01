import java.util.Scanner;
import java.util.Set;
import java.io.IOException;
import java.util.List;



public class Main{

    public static void greeting(){
        System.out.println("================================================");
        System.out.println("Welcome To Kharris Khisunica's (13522051) Word Ladder.");
        System.out.println();
        
        
        System.out.println("__        __            _   _              _     _           \r\n" + //
                        "\\ \\      / /__  _ __ __| | | |    __ _  __| | __| | ___ _ __ \r\n" + //
                        " \\ \\ /\\ / / _ \\| '__/ _` | | |   / _` |/ _` |/ _` |/ _ \\ '__|\r\n" + //
                        "  \\ V  V / (_) | | | (_| | | |__| (_| | (_| | (_| |  __/ |   \r\n" + //
                        "   \\_/\\_/ \\___/|_|  \\__,_| |_____\\__,_|\\__,_|\\__,_|\\___|_|   ");


        
        System.out.println();
        System.out.println("================================================");
    }
    
    public static int algorithmOpt(Scanner scanner){

        int choice;

        while (true){
            System.out.println();
            System.out.println("Algorithm List:");
            System.out.println("1. Unified Cost Search (UCS)");
            System.out.println("2. Greedy Best-First-Search (GBFS)");
            System.out.println("3. A* Algorithm");
            System.out.println("4. Exit");
            System.out.print("Please input your choice (1/2/3/4): ");
            choice = scanner.nextInt();

            if(choice >=1 && choice <=4){
                break;
            }
            else{
                System.out.println("Your input is invalid. Please enter 1/2/3/4");
            }   
        }
        return choice;
    }
   
    public static void exitGreeting(){
        System.out.println("Thank you for using Kharris' Word Ladder (*^_^*)");
    }
    
    public static boolean getRestartChoice(Scanner scanner){
        String choice;

        while (true){
            System.out.print("Do you want to restart the program? (y/n): ");
            choice = scanner.next();

            if(choice.equalsIgnoreCase("y")){
                return true;
            }
            else if (choice.equalsIgnoreCase("n")){
                return false;
            }
            else{
                System.out.println("Invalid choice. Please choose 'y' or 'n' ");
            }
        }
    }

    public static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder = new ProcessBuilder();
        try {
            if (os.contains("win")) {
                processBuilder.command("cmd", "/c", "cls");
            } 
            Process process = processBuilder.inheritIO().start();
            process.waitFor(); 
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing screen: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        
        String filename = "src/dictionary.txt";
        Scanner scanner = new Scanner(System.in);
        DictLoader dictLoader = new DictLoader(filename);
        Set<String> dict = dictLoader.getDict();
        
        boolean restart;

        do {
        greeting();
        System.out.println("================================================");
        List<String> inputWord = WordLadderUtils.readInput(scanner, dict);
        String start = inputWord.get(0);
        String goal = inputWord.get(1);

        int choice = algorithmOpt(scanner);
        System.out.println();
        System.out.println("================================================");
        
        switch (choice) {
        case 1: 
        WordLadderUtils.WordLadderResult ladder = UCS.ucsWordLadder(start, goal, dict);
        
        if (ladder != null){
            System.out.println("UCS Word Ladder: " );
            WordLadderUtils.printPath(ladder.getWordLadderPath());
            System.out.println();
            System.out.println("UCS Visited Nodes Amount: " + ladder.getVisitedNum());
            System.out.println("Time: " + ladder.getExecTime() + "ms");
        }
        else{
            System.out.println("Womp Womp. There's no possible solution from " 
            + start + " to " + goal + " o(T-To)");
        }
        break;

        case 2:

        ladder = GBFS.gbfsWordLadder(start, goal, dict);
    
        if (ladder != null){
            System.out.println("GBFS Word Ladder: ");
            WordLadderUtils.printPath(ladder.getWordLadderPath());
            System.out.println();
            System.out.println("GBFS' Visited Nodes Amount: "+ ladder.getVisitedNum());
            System.out.println("Time: " + ladder.getExecTime() + "ms");
        }
        else{
            System.out.println("Womp Womp. There's no possible solution from " + start 
            + " to " + goal + " o(T-To)");
        }
        break;

        case 3:
        
        ladder = AStar.aStarWordLadder(start, goal, dict);
        if (ladder != null){
            System.out.println("A* Word Ladder: ");
            WordLadderUtils.printPath(ladder.getWordLadderPath());
            System.out.println();
            System.out.println("A* Visited Nodes Amount: " + ladder.getVisitedNum());
            System.out.println("Time: " + ladder.getExecTime() + "ms");
        }
        else{
            System.out.println("Womp Womp. There's no possible solution from " + start 
            + " to " + goal + " o(T-To)");
        }
        break;
    
        case 4:
            exitGreeting();
            scanner.close();
            return;
        
        default:
            break;
        }
        restart = getRestartChoice(scanner);

        }while (restart);

        scanner.close();       
    }
}