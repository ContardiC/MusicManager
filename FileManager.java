import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.Vector;
import java.util.Collections;
public class FileManager {
    private static final int STR_MAX_LENGTH = 60;
    private static final int RECORD_SIZE = 416; // Assumiamo che RECORD_SIZE sia calcolato correttamente in base alla struttura dell'Album
    private final File file;
    private Vector<Album> albums;
    private Vector<AlbumIndex> albumIndexs;
    public FileManager(File file) {
        this.file = file;
    }

    public long numberOfRecords()  {
        long res=0;
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(file, "r");
            res =  raf.length() / RECORD_SIZE;
        } catch (FileNotFoundException e) {
        }catch(IOException e){}
        return res;
    }

    public void writeAlbum(Album album) throws IOException {
        try{
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(RECORD_SIZE * numberOfRecords());
            writeString(raf, album.getTitle());
            writeString(raf, album.getArtist());
            raf.writeLong(album.getReleaseDate().getTime());  // Scrive la data come timestamp
            writeString(raf, album.getGenre());
            raf.writeInt(album.getTrackCount());
            raf.writeInt(album.getTotalDuration());
            writeString(raf, album.getRecordLabel());
        }catch(FileNotFoundException e){

        }
    }

    private void writeString(RandomAccessFile raf, String str) {
        if (str == null) {
            new StringValidationException("String cannot be null");
        }
        if (str.length() > STR_MAX_LENGTH) {
            new StringValidationException("String length exceeds maximum allowed");
        }
        try {
            raf.writeChars(str);
            for (int i = str.length(); i < STR_MAX_LENGTH; i++) {
                raf.writeChar('\0');
            }
        } catch (IOException e) {}
    }

    public Album readAlbum(long recordNumber) {
            Album album = null;
            RandomAccessFile randomAccessFile;
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                randomAccessFile.seek(recordNumber * RECORD_SIZE);
                String title = readString(randomAccessFile);
                String artist = readString(randomAccessFile);
                long releaseDateMillis = randomAccessFile.readLong();
                Date releaseDate = new Date(releaseDateMillis);
                String genre = readString(randomAccessFile);
                int trackCount = randomAccessFile.readInt();
                int totalDuration = randomAccessFile.readInt();
                String recordLabel = readString(randomAccessFile);
                album = new Album(title, artist, releaseDate, genre, trackCount, totalDuration, recordLabel);
                randomAccessFile.close();
            } catch (FileNotFoundException e) {
            }catch(IOException e){}    
            return album;
    }
    

    private String readString(RandomAccessFile raf) throws IOException {
        StringBuilder sb = new StringBuilder(STR_MAX_LENGTH);
        for (int i = 0; i < STR_MAX_LENGTH; i++) {
            char c = raf.readChar();
            sb.append(c);
        }
        return sb.toString();
    }

    public Vector<Album> readAllAlbums()  {
        Vector<Album> albums = new Vector<Album>();
        long numRecords = numberOfRecords();
        for (int i = 0; i < numRecords; i++) {
            albums.add(readAlbum(i));
        }
        return albums;
    }

    public void createIndex(){
        albums = readAllAlbums();
        albumIndexs = new Vector<>();
        AlbumIndex albumIndexTemp;
        for(int i=0;i<albums.size();i++){
             albumIndexTemp= new AlbumIndex(albums.get(i).getTitle(), i);
             albumIndexs.add(albumIndexTemp);
        }
    }
    public void orderIndex(){
        Collections.sort(albumIndexs);
    }

    public Album getAlbum(int pos){
        albums = readAllAlbums();
        if(pos>=albums.size() || pos<0){
            return null;
        }
        return albums.get(pos);
    }
    public Album getAlbum(String key){
        albums = readAllAlbums();
        for(int i=0;i<albums.size();i++){
            if(albums.get(i).getTitle().trim().equals(key.trim())){
                return albums.get(i);
            }
        }
        return null;
    }

    public Vector<AlbumIndex> showIndex(){
        return albumIndexs;
    }
}