import java.util.Objects;
import java.util.UUID;

/**
 * Абстрактний базовий клас Book описує книгу.
 */
public abstract class Book implements Comparable<Book>, Identifiable {
    private UUID uuid;
    private String title;
    private String author;
    private int year;
    private double price;
    private int pages;
    private Genre genre;

    /**
     * Конструктор з параметрами.
     *
     * @param title назва книги
     * @param author автор
     * @param year рік видання
     * @param price ціна
     * @param pages кількість сторінок
     * @param genre жанр
     */
    public Book(String title, String author, int year, double price, int pages, Genre genre) {
        this.uuid = UUID.randomUUID();
        setTitle(title);
        setAuthor(author);
        setYear(year);
        setPrice(price);
        setPages(pages);
        setGenre(genre);
    }

    /**
     * Конструктор копіювання.
     *
     * @param other інший об'єкт Book
     */
    public Book(Book other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null.");
        }

        this.uuid = UUID.randomUUID();
        this.title = other.title;
        this.author = other.author;
        this.year = other.year;
        this.price = other.price;
        this.pages = other.pages;
        this.genre = other.genre;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва книги не може бути порожньою.");
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Автор не може бути порожнім.");
        }
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1400 || year > 2100) {
            throw new IllegalArgumentException("Рік видання має бути в межах 1400-2100.");
        }
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за 0.");
        }
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Кількість сторінок повинна бути більшою за 0.");
        }
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Жанр не може бути null.");
        }
        this.genre = genre;
    }

    /**
     * Порівнює книги за назвою, а якщо назви однакові — за автором.
     *
     * @param other інша книга
     * @return результат порівняння
     */
    @Override
    public int compareTo(Book other) {
        int titleCompare = this.title.compareToIgnoreCase(other.title);

        if (titleCompare != 0) {
            return titleCompare;
        }

        return this.author.compareToIgnoreCase(other.author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", pages=" + pages +
                ", genre=" + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year &&
                Double.compare(book.price, price) == 0 &&
                pages == book.pages &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                genre == book.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, price, pages, genre);
    }
}