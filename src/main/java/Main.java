import java.util.ArrayList;
import java.util.Scanner;

/**
 * Драйвер програми для демонстрації ієрархії класів,
 * поліморфізму, роботи з файлами та пошуку у колекції.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {

        books.addAll(FileService.loadFromFile("input.txt"));

        printHeader();

        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readInt("Оберіть пункт меню: ");

            switch (choice) {
                case 1:
                    searchMenu();
                    break;
                case 2:
                    createObjectMenu();
                    break;
                case 3:
                    printAllBooks();
                    break;
                case 4:
                    System.out.println("Роботу завершено.");
                    FileService.saveToFile(books, "input.txt");
                    running = false;
                    break;
                default:
                    System.out.println("Невірний пункт меню. Спробуйте ще раз.");
            }
        }

        scanner.close();
    }

    /**
     * Інформаційна шапка програми.
     */
    private static void printHeader() {
        System.out.println("Практична робота №10");
        System.out.println("Тема: пошук у колекціях");
        System.out.println("Предметна область: Book hierarchy");
    }

    /**
     * Головне меню.
     */
    private static void printMainMenu() {
        System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
        System.out.println("1. Пошук об'єкта");
        System.out.println("2. Створити новий об'єкт");
        System.out.println("3. Вивести інформацію про всі об'єкти");
        System.out.println("4. Завершити роботу");
    }

    /**
     * Підменю пошуку.
     */
    private static void searchMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== ПОШУК ===");
            System.out.println("1. Пошук за автором");
            System.out.println("2. Пошук за жанром");
            System.out.println("3. Пошук за роком видання");
            System.out.println("4. Пошук за типом об'єкта");
            System.out.println("5. Повернутися до головного меню");

            int choice = readInt("Оберіть критерій пошуку: ");

            switch (choice) {
                case 1:
                    searchByAuthor();
                    break;
                case 2:
                    searchByGenre();
                    break;
                case 3:
                    searchByYear();
                    break;
                case 4:
                    searchByType();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    /**
     * Пошук за автором.
     */
    private static void searchByAuthor() {
        String author = readNonEmptyString("Введіть автора для пошуку: ");
        ArrayList<Book> results = new ArrayList<Book>();

        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                results.add(book);
            }
        }

        printSearchResults(results);
    }

    /**
     * Пошук за жанром.
     */
    private static void searchByGenre() {
        Genre genre = readGenre();
        ArrayList<Book> results = new ArrayList<Book>();

        for (Book book : books) {
            if (book.getGenre() == genre) {
                results.add(book);
            }
        }

        printSearchResults(results);
    }

    /**
     * Пошук за роком видання.
     */
    private static void searchByYear() {
        int year = readInt("Введіть рік для пошуку: ");
        ArrayList<Book> results = new ArrayList<Book>();

        for (Book book : books) {
            if (book.getYear() == year) {
                results.add(book);
            }
        }

        printSearchResults(results);
    }

    /**
     * Пошук за типом об'єкта.
     */
    private static void searchByType() {
        System.out.println("1. Book");
        System.out.println("2. EBook");
        System.out.println("3. PaperBook");
        System.out.println("4. AudioBook");
        System.out.println("5. Textbook");

        int choice = readInt("Оберіть тип для пошуку: ");
        ArrayList<Book> results = new ArrayList<Book>();

        for (Book book : books) {
            switch (choice) {
                case 1:
                    if (book.getClass() == Book.class) {
                        results.add(book);
                    }
                    break;
                case 2:
                    if (book instanceof EBook && !(book instanceof AudioBook)) {
                        results.add(book);
                    }
                    break;
                case 3:
                    if (book instanceof PaperBook && !(book instanceof Textbook)) {
                        results.add(book);
                    }
                    break;
                case 4:
                    if (book instanceof AudioBook) {
                        results.add(book);
                    }
                    break;
                case 5:
                    if (book instanceof Textbook) {
                        results.add(book);
                    }
                    break;
                default:
                    System.out.println("Невірний вибір типу.");
                    return;
            }
        }

        printSearchResults(results);
    }

    /**
     * Виводить результати пошуку.
     */
    private static void printSearchResults(ArrayList<Book> results) {
        if (results.isEmpty()) {
            System.out.println("Нічого не знайдено.");
            return;
        }

        System.out.println("\n=== РЕЗУЛЬТАТИ ПОШУКУ ===");
        for (Book book : results) {
            System.out.println(book);
        }
    }

    /**
     * Підменю створення об'єктів.
     */
    private static void createObjectMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== СТВОРЕННЯ ОБ'ЄКТА ===");
            System.out.println("1. Book");
            System.out.println("2. EBook");
            System.out.println("3. PaperBook");
            System.out.println("4. AudioBook");
            System.out.println("5. Textbook");
            System.out.println("6. Назад");

            int choice = readInt("Ваш вибір: ");

            switch (choice) {
                case 1:
                    createBook();
                    break;
                case 2:
                    createEBook();
                    break;
                case 3:
                    createPaperBook();
                    break;
                case 4:
                    createAudioBook();
                    break;
                case 5:
                    createTextbook();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }

    private static void createBook() {
        try {
            String title = readNonEmptyString("Назва: ");
            String author = readNonEmptyString("Автор: ");
            int year = readInt("Рік: ");
            double price = readDouble("Ціна: ");
            int pages = readInt("Сторінки: ");
            Genre genre = readGenre();

            books.add(new Book(title, author, year, price, pages, genre));
            System.out.println("Додано Book");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createEBook() {
        try {
            String title = readNonEmptyString("Назва: ");
            String author = readNonEmptyString("Автор: ");
            int year = readInt("Рік: ");
            double price = readDouble("Ціна: ");
            int pages = readInt("Сторінки: ");
            Genre genre = readGenre();
            double size = readDouble("Розмір файлу: ");
            String format = readNonEmptyString("Формат: ");

            books.add(new EBook(title, author, year, price, pages, genre, size, format));
            System.out.println("Додано EBook");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createPaperBook() {
        try {
            String title = readNonEmptyString("Назва: ");
            String author = readNonEmptyString("Автор: ");
            int year = readInt("Рік: ");
            double price = readDouble("Ціна: ");
            int pages = readInt("Сторінки: ");
            Genre genre = readGenre();
            String cover = readNonEmptyString("Обкладинка: ");
            double weight = readDouble("Вага: ");

            books.add(new PaperBook(title, author, year, price, pages, genre, cover, weight));
            System.out.println("Додано PaperBook");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createAudioBook() {
        try {
            String title = readNonEmptyString("Назва: ");
            String author = readNonEmptyString("Автор: ");
            int year = readInt("Рік: ");
            double price = readDouble("Ціна: ");
            int pages = readInt("Сторінки: ");
            Genre genre = readGenre();
            double size = readDouble("Розмір файлу: ");
            String format = readNonEmptyString("Формат: ");
            double duration = readDouble("Тривалість: ");
            String narrator = readNonEmptyString("Диктор: ");

            books.add(new AudioBook(title, author, year, price, pages, genre,
                    size, format, duration, narrator));
            System.out.println("Додано AudioBook");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createTextbook() {
        try {
            String title = readNonEmptyString("Назва: ");
            String author = readNonEmptyString("Автор: ");
            int year = readInt("Рік: ");
            double price = readDouble("Ціна: ");
            int pages = readInt("Сторінки: ");
            Genre genre = readGenre();
            String cover = readNonEmptyString("Обкладинка: ");
            double weight = readDouble("Вага: ");
            String subject = readNonEmptyString("Предмет: ");
            int grade = readInt("Клас: ");

            books.add(new Textbook(title, author, year, price, pages, genre,
                    cover, weight, subject, grade));
            System.out.println("Додано Textbook");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }

        for (Book b : books) {
            System.out.println(b);
        }
    }

    private static String readNonEmptyString(String msg) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Порожній рядок!");
        }
    }

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Від'ємні значення не допускаються!");
                    continue;
                }
                return value;
            } catch (Exception e) {
                System.out.println("Невірне число!");
            }
        }
    }

    private static double readDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double value = Double.parseDouble(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Від'ємні значення не допускаються!");
                    continue;
                }
                return value;
            } catch (Exception e) {
                System.out.println("Невірне число!");
            }
        }
    }

    private static Genre readGenre() {
        Genre[] values = Genre.values();

        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }

        while (true) {
            int choice = readInt("Жанр: ");
            if (choice >= 1 && choice <= values.length) {
                return values[choice - 1];
            }
            System.out.println("Невірний вибір жанру!");
        }
    }
}