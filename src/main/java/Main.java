import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {
        printHeader();

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Оберіть пункт меню: ");

            switch (choice) {
                case 1:
                    createBook();
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
        System.out.println("Практична робота №7");
        System.out.println("Тема: наслідування, поліморфізм, ArrayList");
        System.out.println("Базовий клас: Book");
    }

    private static void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Створити звичайну книгу");
        System.out.println("2. Вивести всі книги");
        System.out.println("3. Завершити роботу");
    }

    private static void createBook() {
        try {
            String title = readNonEmptyString("Введіть назву книги: ");
            String author = readNonEmptyString("Введіть автора: ");
            int year = readInt("Введіть рік видання: ");
            double price = readDouble("Введіть ціну: ");
            int pages = readInt("Введіть кількість сторінок: ");
            Genre genre = readGenre();

            Book book = new Book(title, author, year, price, pages, genre);
            books.add(book);

            System.out.println("Книгу успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Список книг порожній.");
            return;
        }

        System.out.println("\nСписок усіх книг:");
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

            int i;
            for (i = 0; i < genres.length; i++) {
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