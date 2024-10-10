import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileManager extends RandomAccessFile {
    // Constructor
     public FileManager(String fileName, String mode) throws IOException {
        super(fileName, mode);
    }

    
    
}
