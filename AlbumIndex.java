import java.io.File;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    
}