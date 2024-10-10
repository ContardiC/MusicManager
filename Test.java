import java.io.File;
import java.util.Date;
public class Test {
    public static void main(String[] args) {
        File file = new File("dati.dat");
        //FileManager fileManager = new FileManager(file);
        Date date = new Date(1990,12,25);
        System.out.println(date.toString());
        System.out.println(date.toString().length());
       
    }
}
