import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Клас Library зберігає колекцію книг з інформацією про кількість.
 */
public class Library {
    private String name;
    private ArrayList<LibraryItem> items;

    public Library(String name) {
        setName(name);
        this.items = new ArrayList<LibraryItem>();
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

    public ArrayList<LibraryItem> getItems() {
        return items;
    }

    public void addNewBook(Book bk, int quantity) {
        if (bk == null) {
            throw new IllegalArgumentException("Книга не може бути null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість повинна бути більшою за 0.");
        }

        for (LibraryItem item : items) {
            if (item.getBook().equals(bk)) {
                item.addQuantity(quantity);
                return;
            }
        }

        items.add(new LibraryItem(bk, quantity));
    }

    public ArrayList<LibraryItem> searchByAuthor(String author) {
        ArrayList<LibraryItem> results = new ArrayList<LibraryItem>();

        for (LibraryItem item : items) {
            if (item.getBook().getAuthor().equalsIgnoreCase(author)) {
                results.add(item);
            }
        }

        return results;
    }

    public ArrayList<LibraryItem> searchByGenre(Genre genre) {
        ArrayList<LibraryItem> results = new ArrayList<LibraryItem>();

        for (LibraryItem item : items) {
            if (item.getBook().getGenre() == genre) {
                results.add(item);
            }
        }

        return results;
    }

    public ArrayList<LibraryItem> searchByYear(int year) {
        ArrayList<LibraryItem> results = new ArrayList<LibraryItem>();

        for (LibraryItem item : items) {
            if (item.getBook().getYear() == year) {
                results.add(item);
            }
        }

        return results;
    }

    public void printAllItems() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня.");
            return;
        }

        for (LibraryItem item : items) {
            System.out.println(item);
        }
    }

    /**
     * Сортування за назвою.
     */
    public void printSortedByTitle() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня.");
            return;
        }

        ArrayList<LibraryItem> sortedItems = new ArrayList<LibraryItem>(items);

        Collections.sort(sortedItems, new Comparator<LibraryItem>() {
            @Override
            public int compare(LibraryItem o1, LibraryItem o2) {
                return o1.getBook().getTitle().compareToIgnoreCase(o2.getBook().getTitle());
            }
        });

        for (LibraryItem item : sortedItems) {
            System.out.println(item);
        }
    }

    public void printSortedByAuthor() {
        System.out.println("Сортування за автором буде реалізовано далі.");
    }

    public void printSortedByYear() {
        System.out.println("Сортування за роком буде реалізовано далі.");
    }
}