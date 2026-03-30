import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books;

    public Library(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва бібліотеки не може бути порожньою.");
        }
        this.name = name;
        this.books = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва бібліотеки не може бути порожньою.");
        }
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Книга не може бути null.");
        }
        books.add(book);
    }

    public void printBooks() {
        if (books.isEmpty()) {
            System.out.println("У бібліотеці немає книг.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", booksCount=" + books.size() +
                '}';
    }
}