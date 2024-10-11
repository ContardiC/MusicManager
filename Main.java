import java.io.File;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // Crea un oggetto FileManager per interagire con il file
        FileManager fileManager = new FileManager(new File("albums.dat"));

        // Crea due oggetti Album con dati di esempio
        Album album1 = new Album("Dark Side of the Moon", "Pink Floyd", new Date(1973, 2, 1), "Progressive rock", 10, 43, "Harvest Records");
        Album album2 = new Album("Rumours", "Fleetwood Mac", new Date(1977, 2, 4), "Soft rock", 11, 40, "Warner Bros. Records");

        // Scrivi gli album nel file
        fileManager.writeAlbum(album1);
        fileManager.writeAlbum(album2);

        // Leggi tutti gli album dal file e stampali
        List<Album> albums = fileManager.readAllAlbums();
        for (Album album : albums) {
            System.out.println(album); // Assumendo che la classe Album abbia un metodo toString() che stampa i dettagli dell'album
        }
    }
}