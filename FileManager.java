import java.io.RandomAccessFile;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileManager {
    File file;
    private final int STR_MAX_LENGHT = 60;
    private final long RECORD_SIZE = 
    FileManager(File file) {
        this.file = file;
    }

    public long numberOfRecords() {
        long lenght = 0;
        long res = 0;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            lenght = randomAccessFile.length();
            randomAccessFile.close();
            res = lenght / RECORD_SIZE;
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return res;
    }

    public void writeString(String str) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            if (str == null) {
                new StringValidationException("String cannot be null");
            }
            if (str.length() > STR_MAX_LENGHT) {
                new StringValidationException("String length exceeds maximum allowed");
            }
            try {
                randomAccessFile.writeChars(str);
                for (int i = str.length(); i < STR_MAX_LENGHT; i++) {
                    randomAccessFile.writeChar('\0');
                }
            } catch (IOException e) {
                e.printStackTrace();
                new RuntimeException("Error writing to file", e);
            }
            randomAccessFile.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // TODO 1. writeAlbum method
    // TODO 2. readAlbum method 

    
}
