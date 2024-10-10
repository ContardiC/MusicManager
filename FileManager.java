import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileManager extends RandomAccessFile {
    // Constructor
     public FileManager(String fileName, String mode) throws IOException {
        super(fileName, mode);
    }
    public boolean writeString(String str, int maxLenght){
        if(str.length()==maxLenght){
            try {
                writeChars(str);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(str.length()<maxLenght){
            
        }
        return true;
    }
    
    
}
