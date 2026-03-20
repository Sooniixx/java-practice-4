import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Book[] books = new Book[5];

        books[0] = new Book("Кобзар", "Тарас Шевченко", 1840, 250.0);
        books[1] = new Book("Лісова пісня", "Леся Українка", 1911, 180.0);
        books[2] = new Book("Тигролови", "Іван Багряний", 1944, 220.0);

        System.out.println("Введіть дані для 4-ї книги:");
        System.out.print("Назва: ");
        String title1 = scanner.nextLine();
        System.out.print("Автор: ");
        String author1 = scanner.nextLine();
        System.out.print("Рік видання: ");
        int year1 = Integer.parseInt(scanner.nextLine());
        System.out.print("Ціна: ");
        double price1 = Double.parseDouble(scanner.nextLine());

        books[3] = new Book(title1, author1, year1, price1);

        System.out.println("\nВведіть дані для 5-ї книги:");
        System.out.print("Назва: ");
        String title2 = scanner.nextLine();
        System.out.print("Автор: ");
        String author2 = scanner.nextLine();
        System.out.print("Рік видання: ");
        int year2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Ціна: ");
        double price2 = Double.parseDouble(scanner.nextLine());

        books[4] = new Book(title2, author2, year2, price2);

        System.out.println("\nСписок усіх книг:");
        for (Book book : books) {
            System.out.println(book.toString());
        }

        scanner.close();
    }
}