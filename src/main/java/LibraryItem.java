/**
 * Клас-обгортка для збереження книги та її кількості в бібліотеці.
 */
public class LibraryItem {
    private Book book;
    private int quantity;

    /**
     * Конструктор з параметрами.
     *
     * @param book книга
     * @param quantity кількість
     */
    public LibraryItem(Book book, int quantity) {
        setBook(book);
        setQuantity(quantity);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Книга не може бути null.");
        }
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість повинна бути більшою за 0.");
        }
        this.quantity = quantity;
    }

    /**
     * Збільшує кількість книги.
     *
     * @param value значення для додавання
     */
    public void addQuantity(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Кількість для додавання повинна бути більшою за 0.");
        }
        this.quantity += value;
    }

    @Override
    public String toString() {
        return book.toString() + ", quantity=" + quantity;
    }
}