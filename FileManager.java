import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileManager {
    private static final int STR_MAX_LENGTH = 60;
    private static final int RECORD_SIZE = 496; // Assumiamo che RECORD_SIZE sia calcolato correttamente in base alla struttura dell'Album
    private final File file;

    public FileManager(File file) {
        this.file = file;
    }

    public long numberOfRecords() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            return raf.length() / RECORD_SIZE;
        }
    }

    public void writeAlbum(Album album) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(RECORD_SIZE * numberOfRecords());

            writeString(raf, album.getTitle());
            writeString(raf, album.getArtist());
            raf.writeLong(album.getReleaseDate().getTime());  // Scrive la data come timestamp
            writeString(raf, album.getGenre());
            raf.writeInt(album.getTrackCount());
            raf.writeInt(album.getTotalDuration());
            writeString(raf, album.getRecordLabel());
        }
    }

    private void writeString(RandomAccessFile raf, String str) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        if (str.length() > STR_MAX_LENGTH) {
            throw new IllegalArgumentException("String length exceeds maximum allowed");
        }
        raf.writeChars(str);
        for (int i = str.length(); i < STR_MAX_LENGTH; i++) {
            raf.writeChar('\0');
        }
    }

    public Album readAlbum(long recordNumber) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.seek(recordNumber * RECORD_SIZE);

            String title = readString(raf);
            String artist = readString(raf);
            long releaseDateMillis = raf.readLong();
            Date releaseDate = new Date(releaseDateMillis);
            String genre = readString(raf);
            int trackCount = raf.readInt();
            int totalDuration = raf.readInt();
            String recordLabel = readString(raf);

            return new Album(title, artist, releaseDate, genre, trackCount, totalDuration, recordLabel);
        }
    }

    private String readString(RandomAccessFile raf) throws IOException {
        StringBuilder sb = new StringBuilder(STR_MAX_LENGTH);
        for (int i = 0; i < STR_MAX_LENGTH; i++) {
            char c = raf.readChar();
            if (c == '\0') {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public List<Album> readAllAlbums() throws IOException {
        List<Album> albums = new ArrayList<>();
        long numRecords = numberOfRecords();
        for (long i = 0; i < numRecords; i++) {
            albums.add(readAlbum(i));
        }
        return albums;
    }
}