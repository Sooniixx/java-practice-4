import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

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
            throw new InvalidFieldValueException("Назва бібліотеки не може бути порожньою.");
        }
        this.name = name;
    }

    public ArrayList<LibraryItem> getItems() {
        return items;
    }

    public void addNewBook(Book bk, int quantity) {
        if (bk == null) {
            throw new InvalidFieldValueException("Книга не може бути null.");
        }
        if (quantity <= 0) {
            throw new InvalidFieldValueException("Кількість повинна бути більшою за 0.");
        }

        for (LibraryItem item : items) {
            if (item.getBook().equals(bk)) {
                item.addQuantity(quantity);
                return;
            }
        }

        items.add(new LibraryItem(bk, quantity));
    }

    public boolean update(Book existingObject, Book newObject) {
        if (existingObject == null || newObject == null) {
            throw new InvalidFieldValueException("Об'єкти для оновлення не можуть бути null.");
        }

        for (LibraryItem item : items) {
            if (item.getBook().getUuid().equals(existingObject.getUuid())) {
                newObject.setUuid(existingObject.getUuid());
                item.setBook(newObject);
                return true;
            }
        }

        throw new ObjectNotFoundException("Об'єкт для оновлення не знайдено.");
    }

    public boolean delete(Book existingObject) {
        if (existingObject == null) {
            throw new InvalidFieldValueException("Об'єкт для видалення не може бути null.");
        }

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getBook().getUuid().equals(existingObject.getUuid())) {
                items.remove(i);
                return true;
            }
        }

        throw new ObjectNotFoundException("Об'єкт для видалення не знайдено.");
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

    public LibraryItem searchByUuid(UUID uuid) {
        for (LibraryItem item : items) {
            if (item.getBook().getUuid().equals(uuid)) {
                return item;
            }
        }

        return null;
    }

    public void printAllItems() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    public void printSortedByTitle() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня.");
            return;
        }

        ArrayList<LibraryItem> sortedItems = new ArrayList<LibraryItem>(items);

        Collections.sort(sortedItems,
                (o1, o2) -> o1.getBook().getTitle().compareToIgnoreCase(o2.getBook().getTitle()));

        for (LibraryItem item : sortedItems) {
            System.out.println(item);
        }
    }

    public void printSortedByAuthor() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня.");
            return;
        }

        ArrayList<LibraryItem> sortedItems = new ArrayList<LibraryItem>(items);

        Collections.sort(sortedItems,
                (o1, o2) -> o1.getBook().getAuthor().compareToIgnoreCase(o2.getBook().getAuthor()));

        for (LibraryItem item : sortedItems) {
            System.out.println(item);
        }
    }

    public void printSortedByYear() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня.");
            return;
        }

        ArrayList<LibraryItem> sortedItems = new ArrayList<LibraryItem>(items);

        Collections.sort(sortedItems,
                (o1, o2) -> Integer.compare(o1.getBook().getYear(), o2.getBook().getYear()));

        for (LibraryItem item : sortedItems) {
            System.out.println(item);
        }
    }

    public ArrayList<String> getShortViewList() {
        ArrayList<String> result = new ArrayList<String>();

        for (LibraryItem item : items) {
            result.add(item.getBook().getTitle() + " | UUID: " + item.getBook().getUuid());
        }

        return result;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public LibraryItem getItemByIndex(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }

        return items.get(index);
    }

    public int size() {
        return items.size();
    }
}