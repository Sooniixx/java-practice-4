/**
 * Клас EBook описує електронну книгу.
 */
public class EBook extends Book {
    private double fileSizeMb;
    private String fileFormat;

    public EBook(String title, String author, int year, double price, int pages, Genre genre,
                 double fileSizeMb, String fileFormat) {
        super(title, author, year, price, pages, genre);
        setFileSizeMb(fileSizeMb);
        setFileFormat(fileFormat);
    }

    public double getFileSizeMb() {
        return fileSizeMb;
    }

    public void setFileSizeMb(double fileSizeMb) {
        if (fileSizeMb <= 0) {
            throw new IllegalArgumentException("Розмір файлу має бути більшим за 0.");
        }
        this.fileSizeMb = fileSizeMb;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        if (fileFormat == null || fileFormat.trim().isEmpty()) {
            throw new IllegalArgumentException("Формат файлу не може бути порожнім.");
        }
        this.fileFormat = fileFormat;
    }

    @Override
    public String toString() {
        return "EBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", price=" + getPrice() +
                ", pages=" + getPages() +
                ", genre=" + getGenre() +
                ", fileSizeMb=" + fileSizeMb +
                ", fileFormat='" + fileFormat + '\'' +
                '}';
    }
}