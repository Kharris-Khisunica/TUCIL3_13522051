import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictLoader {
    private Set<String> dict;

    public DictLoader(String filename){
        dict = new HashSet<>();
        readDict(filename);
    }
    public void readDict(String filename){
        File myFile = new File(filename);
        Scanner scanner = null;
        try{
            scanner = new Scanner(myFile);
            while (scanner.hasNextLine()){
                String word = scanner.nextLine().trim();
                dict.add(word);
            }
        } catch (FileNotFoundException e){
            System.out.println("File Not Found. Please Try Again!");
            e.printStackTrace();
        } finally{
            if (scanner != null){
                scanner.close();
            }
        }
    }
    public Set<String> getDict(){
        return dict;
    }
}
