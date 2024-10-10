import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileManager extends RandomAccessFile {
    // Constructor
     public FileManager(String fileName, String mode) throws IOException {
        super(fileName, mode);
    }
    public void writeString(String str, int maxLenght){
        if(str==null){
            throw new StringValidationException("String cannot be null");
        }
        if(str.length()>maxLenght){
            throw new StringValidationException("String lenght exceeds maximum allowed");
        }
        try{
            writeChars(str);
            for(int i=str.length();i<maxLenght;i++){
                writeChar('\0');
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
