import java.io.*;
import java.util.ArrayList;

/**
 * Клас для роботи з файлами.
 */
public class FileService {

    /**
     * Завантажує об'єкти з файлу.
     */
    public static ArrayList<Book> loadFromFile(String fileName) {
        ArrayList<Book> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(";");

                    String type = parts[0];

                    String title = parts[1];
                    String author = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);
                    int pages = Integer.parseInt(parts[5]);
                    Genre genre = Genre.valueOf(parts[6]);

                    switch (type) {
                        case "BOOK":
                            list.add(new Book(title, author, year, price, pages, genre));
                            break;

                        case "EBOOK":
                            double fileSize = Double.parseDouble(parts[7]);
                            String format = parts[8];
                            list.add(new EBook(title, author, year, price, pages, genre, fileSize, format));
                            break;

                        case "PAPERBOOK":
                            String cover = parts[7];
                            double weight = Double.parseDouble(parts[8]);
                            list.add(new PaperBook(title, author, year, price, pages, genre, cover, weight));
                            break;

                        case "AUDIOBOOK":
                            double fs = Double.parseDouble(parts[7]);
                            String fmt = parts[8];
                            double duration = Double.parseDouble(parts[9]);
                            String narrator = parts[10];
                            list.add(new AudioBook(title, author, year, price, pages, genre, fs, fmt, duration, narrator));
                            break;

                        case "TEXTBOOK":
                            String coverT = parts[7];
                            double weightT = Double.parseDouble(parts[8]);
                            String subject = parts[9];
                            int grade = Integer.parseInt(parts[10]);
                            list.add(new Textbook(title, author, year, price, pages, genre, coverT, weightT, subject, grade));
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("Помилка в рядку: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }

        return list;
    }

    /**
     * Зберігає об'єкти у файл.
     */
    public static void saveToFile(ArrayList<Book> list, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (Book b : list) {
                writer.write(serialize(b));
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }

    /**
     * Перетворює об'єкт у рядок.
     */
    private static String serialize(Book b) {

        if (b instanceof AudioBook) {
            AudioBook a = (AudioBook) b;
            return "AUDIOBOOK;" + a.getTitle() + ";" + a.getAuthor() + ";" + a.getYear() + ";" +
                    a.getPrice() + ";" + a.getPages() + ";" + a.getGenre() + ";" +
                    a.getFileSizeMb() + ";" + a.getFileFormat() + ";" +
                    a.getDurationHours() + ";" + a.getNarrator();
        }

        if (b instanceof Textbook) {
            Textbook t = (Textbook) b;
            return "TEXTBOOK;" + t.getTitle() + ";" + t.getAuthor() + ";" + t.getYear() + ";" +
                    t.getPrice() + ";" + t.getPages() + ";" + t.getGenre() + ";" +
                    t.getCoverType() + ";" + t.getWeight() + ";" +
                    t.getSubject() + ";" + t.getGradeLevel();
        }

        if (b instanceof EBook) {
            EBook e = (EBook) b;
            return "EBOOK;" + e.getTitle() + ";" + e.getAuthor() + ";" + e.getYear() + ";" +
                    e.getPrice() + ";" + e.getPages() + ";" + e.getGenre() + ";" +
                    e.getFileSizeMb() + ";" + e.getFileFormat();
        }

        if (b instanceof PaperBook) {
            PaperBook p = (PaperBook) b;
            return "PAPERBOOK;" + p.getTitle() + ";" + p.getAuthor() + ";" + p.getYear() + ";" +
                    p.getPrice() + ";" + p.getPages() + ";" + p.getGenre() + ";" +
                    p.getCoverType() + ";" + p.getWeight();
        }

        return "BOOK;" + b.getTitle() + ";" + b.getAuthor() + ";" + b.getYear() + ";" +
                b.getPrice() + ";" + b.getPages() + ";" + b.getGenre();
    }
}