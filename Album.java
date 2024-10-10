import java.util.Date;

public class Album{
    private String title; // 120 bytes
    private String artist; // 120 bytes
    private Date releaseDate;  //  56 bytes
    private String genre; // 120 bytes
    private int trackCount; // 4 bytes
    private int totalDuration;   // in minutes 4 bytes
    private String recordLabel; // 120 bytes
    // Constructor
    public Album(String title, String artist, Date releaseDate, String genre, int trackCount, int totalDuration, String recordLabel) {
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.trackCount = trackCount;
        this.totalDuration = totalDuration;
        this.recordLabel = recordLabel;
    }

    // Getter and Setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }
}