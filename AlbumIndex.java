public class AlbumIndex implements Comparable<AlbumIndex>{
    String key; // Album title
    int value; // Album position
    AlbumIndex(String key, int value){
        this.key=key;
        this.value=value;
    }
    @Override
    public int compareTo(AlbumIndex o) {
        return key.compareTo(o.key);
    }

    public String toString(){
        String res = null;
        res = "{Key: " + key + " value: "+value+"}";
        return res; 
    }
    
}