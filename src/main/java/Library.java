import java.util.ArrayList;

/**
 * Клас Library зберігає колекцію книг з інформацією про кількість.
 */
public class Library {
    private String name;
    private ArrayList<LibraryItem> items;

    /**
     * Конструктор з параметрами.
     *
     * @param name назва бібліотеки
     */
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

    /**
     * Додає нову книгу в бібліотеку з урахуванням кількості.
     * Якщо книга вже існує, збільшує її кількість.
     *
     * @param bk книга
     * @param quantity кількість
     */
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

    /**
     * Пошук за автором.
     */
    public ArrayList<LibraryItem> searchByAuthor(String author) {
        ArrayList<LibraryItem> results = new ArrayList<LibraryItem>();

        for (LibraryItem item : items) {
            if (item.getBook().getAuthor().equalsIgnoreCase(author)) {
                results.add(item);
            }
        }

        return results;
    }

    /**
     * Пошук за жанром.
     */
    public ArrayList<LibraryItem> searchByGenre(Genre genre) {
        ArrayList<LibraryItem> results = new ArrayList<LibraryItem>();

        for (LibraryItem item : items) {
            if (item.getBook().getGenre() == genre) {
                results.add(item);
            }
        }

        return results;
    }

    /**
     * Пошук за роком видання.
     */
    public ArrayList<LibraryItem> searchByYear(int year) {
        ArrayList<LibraryItem> results = new ArrayList<LibraryItem>();

        for (LibraryItem item : items) {
            if (item.getBook().getYear() == year) {
                results.add(item);
            }
        }

        return results;
    }

    /**
     * Виводить усі книги бібліотеки.
     */
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
 * Виводить відсортовані елементи бібліотеки.
 */
public void printSortedItems() {
    if (items.isEmpty()) {
        System.out.println("Бібліотека порожня.");
        return;
    }

    ArrayList<LibraryItem> sortedItems = new ArrayList<LibraryItem>(items);

    sortedItems.sort(new java.util.Comparator<LibraryItem>() {
        @Override
        public int compare(LibraryItem o1, LibraryItem o2) {
            return o1.getBook().compareTo(o2.getBook());
        }
    });

    for (LibraryItem item : sortedItems) {
        System.out.println(item);
    }
}
}