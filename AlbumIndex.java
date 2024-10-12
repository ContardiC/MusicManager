public class AlbumIndex implements Comparable<AlbumIndex>{
    String key; // Album title
    long value; // Album position
    @Override
    public int compareTo(AlbumIndex o) {
        return key.compareTo(o.key);
    }
    public void createIndex(){
        
    }
}