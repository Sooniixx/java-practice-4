import java.util.ArrayList;
import java.util.Scanner;

/**
 * Драйвер програми для демонстрації ієрархії класів,
 * поліморфізму та роботи з файлами.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {

        // 🔥 ЗАВАНТАЖЕННЯ З ФАЙЛУ
        books.addAll(FileService.loadFromFile("input.txt"));

        printHeader();

        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readInt("Оберіть пункт меню: ");

            switch (choice) {
                case 1:
                    createObjectMenu();
                    break;
                case 2:
                    printAllBooks();
                    break;
                case 3:
                    System.out.println("Роботу завершено.");

                    // 🔥 ЗБЕРЕЖЕННЯ У ФАЙЛ
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
        System.out.println("Практична робота №9");
        System.out.println("Тема: робота з файлами, ієрархія класів");
        System.out.println("Предметна область: Book hierarchy");
    }

    /**
     * Головне меню.
     */
    private static void printMainMenu() {
        System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
        System.out.println("1. Створити новий об'єкт");
        System.out.println("2. Вивести всі об'єкти");
        System.out.println("3. Завершити роботу");
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
            if (!input.isEmpty()) return input;
            System.out.println("Порожній рядок!");
        }
    }

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Невірне число!");
            }
        }
    }

    private static double readDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(scanner.nextLine());
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

        int choice = readInt("Жанр: ");
        return values[choice - 1];
    }
}