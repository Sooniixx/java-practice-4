import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {
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
                    running = false;
                    break;
                default:
                    System.out.println("Невірний пункт меню. Спробуйте ще раз.");
            }
        }

        scanner.close();
    }

    private static void printHeader() {
        System.out.println("Практична робота №8");
        System.out.println("Тема: розширення ієрархії класів, поліморфізм, меню створення об'єктів");
        System.out.println("Предметна область: Book hierarchy");
    }

    private static void printMainMenu() {
        System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
        System.out.println("1. Створити новий об'єкт");
        System.out.println("2. Вивести інформацію про всі об'єкти");
        System.out.println("3. Завершити роботу");
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
            System.out.println("6. Повернутися до головного меню");

            int choice = readInt("Оберіть тип об'єкта: ");

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
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void createBook() {
        try {
            String title = readNonEmptyString("Введіть назву книги: ");
            String author = readNonEmptyString("Введіть автора: ");
            int year = readInt("Введіть рік видання: ");
            double price = readDouble("Введіть ціну: ");
            int pages = readInt("Введіть кількість сторінок: ");
            Genre genre = readGenre();

            books.add(new Book(title, author, year, price, pages, genre));
            System.out.println("Book успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createEBook() {
        try {
            String title = readNonEmptyString("Введіть назву книги: ");
            String author = readNonEmptyString("Введіть автора: ");
            int year = readInt("Введіть рік видання: ");
            double price = readDouble("Введіть ціну: ");
            int pages = readInt("Введіть кількість сторінок: ");
            Genre genre = readGenre();
            double fileSizeMb = readDouble("Введіть розмір файлу (MB): ");
            String fileFormat = readNonEmptyString("Введіть формат файлу: ");

            books.add(new EBook(title, author, year, price, pages, genre, fileSizeMb, fileFormat));
            System.out.println("EBook успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createPaperBook() {
        try {
            String title = readNonEmptyString("Введіть назву книги: ");
            String author = readNonEmptyString("Введіть автора: ");
            int year = readInt("Введіть рік видання: ");
            double price = readDouble("Введіть ціну: ");
            int pages = readInt("Введіть кількість сторінок: ");
            Genre genre = readGenre();
            String coverType = readNonEmptyString("Введіть тип обкладинки: ");
            double weight = readDouble("Введіть вагу книги: ");

            books.add(new PaperBook(title, author, year, price, pages, genre, coverType, weight));
            System.out.println("PaperBook успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createAudioBook() {
        try {
            String title = readNonEmptyString("Введіть назву книги: ");
            String author = readNonEmptyString("Введіть автора: ");
            int year = readInt("Введіть рік видання: ");
            double price = readDouble("Введіть ціну: ");
            int pages = readInt("Введіть кількість сторінок: ");
            Genre genre = readGenre();
            double fileSizeMb = readDouble("Введіть розмір файлу (MB): ");
            String fileFormat = readNonEmptyString("Введіть формат файлу: ");
            double durationHours = readDouble("Введіть тривалість у годинах: ");
            String narrator = readNonEmptyString("Введіть ім'я диктора: ");

            books.add(new AudioBook(title, author, year, price, pages, genre,
                    fileSizeMb, fileFormat, durationHours, narrator));
            System.out.println("AudioBook успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createTextbook() {
        try {
            String title = readNonEmptyString("Введіть назву книги: ");
            String author = readNonEmptyString("Введіть автора: ");
            int year = readInt("Введіть рік видання: ");
            double price = readDouble("Введіть ціну: ");
            int pages = readInt("Введіть кількість сторінок: ");
            Genre genre = readGenre();
            String coverType = readNonEmptyString("Введіть тип обкладинки: ");
            double weight = readDouble("Введіть вагу книги: ");
            String subject = readNonEmptyString("Введіть предмет: ");
            int gradeLevel = readInt("Введіть клас навчання: ");

            books.add(new Textbook(title, author, year, price, pages, genre,
                    coverType, weight, subject, gradeLevel));
            System.out.println("Textbook успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Колекція порожня.");
            return;
        }

        System.out.println("\n=== УСІ ОБ'ЄКТИ ===");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Рядок не може бути порожнім.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    System.out.println("Від'ємні значення не допускаються.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Потрібно ввести ціле число.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Від'ємні значення не допускаються.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Потрібно ввести число.");
            }
        }
    }

    private static Genre readGenre() {
        while (true) {
            System.out.println("Оберіть жанр:");
            Genre[] genres = Genre.values();

            for (int i = 0; i < genres.length; i++) {
                System.out.println((i + 1) + ". " + genres[i]);
            }

            int choice = readInt("Ваш вибір: ");

            if (choice >= 1 && choice <= genres.length) {
                return genres[choice - 1];
            }

            System.out.println("Невірний вибір жанру.");
        }
    }
}