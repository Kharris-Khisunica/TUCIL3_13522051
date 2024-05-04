public class gbfc {
    
    public static int hn_Now(String now, String end){
        char[] nowArray = now.toCharArray();
        char[] endArray = end.toCharArray();
        int hn = 0;
        for(int i=0; i<now.length();i++){
            if (nowArray[i] != endArray[i]){
                hn++;
            }
        }
        return hn;
    }
}
