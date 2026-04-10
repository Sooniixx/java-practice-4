import java.util.ArrayList;
import java.util.Scanner;

/**
 * Драйвер програми для роботи з бібліотекою.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library("My Library");

    public static void main(String[] args) {

        FileService.loadFromFile("input.txt", library);

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
                    library.printAllItems();
                    break;
                case 4:
                    System.out.println("Роботу завершено.");
                    FileService.saveToFile(library, "input.txt");
                    running = false;
                    break;
                default:
                    System.out.println("Невірний пункт меню. Спробуйте ще раз.");
            }
        }

        scanner.close();
    }

    private static void printHeader() {
        System.out.println("Практична робота №11");
        System.out.println("Тема: колекції, агрегація, класи-обгортки");
        System.out.println("Предметна область: бібліотека");
    }

    private static void printMainMenu() {
        System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
        System.out.println("1. Пошук об'єкта");
        System.out.println("2. Створити новий об'єкт");
        System.out.println("3. Вивести інформацію про всі об'єкти");
        System.out.println("4. Завершити роботу");
    }

    private static void searchMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== ПОШУК ===");
            System.out.println("1. Пошук за автором");
            System.out.println("2. Пошук за жанром");
            System.out.println("3. Пошук за роком видання");
            System.out.println("4. Повернутися до головного меню");

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
                    back = true;
                    break;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }

    private static void searchByAuthor() {
        String author = readNonEmptyString("Введіть автора: ");
        ArrayList<LibraryItem> results = library.searchByAuthor(author);
        printSearchResults(results);
    }

    private static void searchByGenre() {
        Genre genre = readGenre();
        ArrayList<LibraryItem> results = library.searchByGenre(genre);
        printSearchResults(results);
    }

    private static void searchByYear() {
        int year = readInt("Введіть рік: ");
        ArrayList<LibraryItem> results = library.searchByYear(year);
        printSearchResults(results);
    }

    private static void printSearchResults(ArrayList<LibraryItem> results) {
        if (results.isEmpty()) {
            System.out.println("Нічого не знайдено.");
            return;
        }

        for (LibraryItem item : results) {
            System.out.println(item);
        }
    }

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
            int quantity = readInt("Кількість: ");

            library.addNewBook(new Book(title, author, year, price, pages, genre), quantity);
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
            int quantity = readInt("Кількість: ");

            library.addNewBook(new EBook(title, author, year, price, pages, genre, size, format), quantity);
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
            int quantity = readInt("Кількість: ");

            library.addNewBook(new PaperBook(title, author, year, price, pages, genre, cover, weight), quantity);
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
            int quantity = readInt("Кількість: ");

            library.addNewBook(new AudioBook(title, author, year, price, pages, genre,
                    size, format, duration, narrator), quantity);
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
            int quantity = readInt("Кількість: ");

            library.addNewBook(new Textbook(title, author, year, price, pages, genre,
                    cover, weight, subject, grade), quantity);
            System.out.println("Додано Textbook");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
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