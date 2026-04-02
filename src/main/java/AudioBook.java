/**
 * Клас AudioBook описує аудіокнигу.
 */
public class AudioBook extends EBook {
    private double durationHours;
    private String narrator;

    public AudioBook(String title, String author, int year, double price, int pages, Genre genre,
                     double fileSizeMb, String fileFormat,
                     double durationHours, String narrator) {
        super(title, author, year, price, pages, genre, fileSizeMb, fileFormat);
        setDurationHours(durationHours);
        setNarrator(narrator);
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double durationHours) {
        if (durationHours <= 0) {
            throw new IllegalArgumentException("Тривалість має бути більшою за 0.");
        }
        this.durationHours = durationHours;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        if (narrator == null || narrator.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я диктора не може бути порожнім.");
        }
        this.narrator = narrator;
    }
/**
 * Повертає рядкове представлення аудіокниги.
 */
    @Override
    public String toString() {
        return "AudioBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", price=" + getPrice() +
                ", pages=" + getPages() +
                ", genre=" + getGenre() +
                ", fileSizeMb=" + getFileSizeMb() +
                ", fileFormat='" + getFileFormat() + '\'' +
                ", durationHours=" + durationHours +
                ", narrator='" + narrator + '\'' +
                '}';
    }
}