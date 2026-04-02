/**
 * Клас PaperBook описує паперову книгу.
 */
public class PaperBook extends Book {
    private String coverType;
    private double weight;

    public PaperBook(String title, String author, int year, double price, int pages, Genre genre,
                     String coverType, double weight) {
        super(title, author, year, price, pages, genre);
        setCoverType(coverType);
        setWeight(weight);
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        if (coverType == null || coverType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип обкладинки не може бути порожнім.");
        }
        this.coverType = coverType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Вага повинна бути більшою за 0.");
        }
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PaperBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", price=" + getPrice() +
                ", pages=" + getPages() +
                ", genre=" + getGenre() +
                ", coverType='" + coverType + '\'' +
                ", weight=" + weight +
                '}';
    }
}