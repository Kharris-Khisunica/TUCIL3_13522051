import java.util.List;

public class WORD {
    private String word;
    private String parent;
    private int g_n;
    private int h_n;

    public WORD(String word, String parent, int g_n, int h_n){
        this.word = word;
        this.parent = parent;
        this.g_n = g_n;
        this.h_n = h_n;
    }
    
    //Getter Setter

    public String getWord(){
        return word;
    }
    public void setWord(String word){
        this.word = word;
    }

    public String getParent(){
        return parent;
    }
    public void setParent(String parent){
        this.parent = parent;
    }

    public int getgn(){
        return g_n;
    }
    public void setgn(int g_n){
        this.g_n = g_n;
    }

    public int gethn(){
        return h_n;
    }
    public void sethn(int h_n){
        this.h_n = h_n;
    }

    public static WORD getParentNode(WORD node, List<WORD> allWord){
        for (WORD data:allWord){
            // Cari Wordnya
            if(data.getWord() == node.getWord()){
                String parentWord = node.getParent();
                for (WORD parentNode : allWord){
                    if(parentNode.getWord() == parentWord){
                        return parentNode;
                    }
                }
            }
        }    
        
        return null; //Parent not found = First Node
    }

    public static WORD makeWord (String word, String parent, int gn, int hn){
        return new WORD(word, parent, gn, hn);

    }

    public static void updateActiveNode(List<String> wordList, WORD parent, String end, List<WORD> activeNode){
        int gn = parent.getgn() + 1;
        String parentWord = parent.getWord();
        for(String data : wordList){
            int hn = gbfc.hn_Now(data, end);
            int index = activeNode.size();

            while (index > 0 && activeNode.get(index-1).gethn() > hn){
                index--;
            }

            activeNode.add(index, makeWord(data, parentWord, gn, hn));


        }
    }

}
