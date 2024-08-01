public class WordNode {
    String word;
    WordNode parent;
    int cost;
    int heuristic;

    public WordNode(String word, WordNode parent, int cost, int heuristic){
        this.word = word;
        this.parent = parent;
        this.cost = cost;
        this.heuristic = heuristic;
    }
    
}
