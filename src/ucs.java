import java.util.*;

public class UCS {
    public static WordLadderUtils.WordLadderResult ucsWordLadder 
    (String start, String goal, Set<String> dict){

        PriorityQueue<WordNode> queue = new PriorityQueue<>
        (Comparator.comparingInt(WordNode->WordNode.cost));
        Set<String> visited = new HashSet<>();
        queue.add(new WordNode(start, null, 0, 0));
        visited.add(start);
        int count = 0;
        long  startTime = System.nanoTime();
        while(!queue.isEmpty()){
            WordNode current = queue.poll();
            String currentWord = current.word;
            int currentCost = current.cost;
            count++;

            if (currentWord.equals(goal)){
                long endTime = System.nanoTime();
                List<String> wlPath = WordLadderUtils.constructPath(current);
                long exectime = (endTime-startTime)/1000000;                
                return new WordLadderUtils.WordLadderResult(wlPath, count,exectime);
            }

            for (String Adj : WordLadderUtils.getAdjWords(currentWord, dict)){
                if (!visited.contains(Adj)){
                    queue.add(new WordNode(Adj, current, currentCost + 1,0));
                    visited.add(Adj);
                }
            }
        }
        long endTime = System.nanoTime();
        long exectime = (endTime-startTime)/1000000;                
        return new WordLadderUtils.WordLadderResult(null, count,exectime);
            
    }

    
}
