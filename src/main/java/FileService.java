import java.io.*;

/**
 * Клас для роботи з файлами.
 */
public class FileService {

    /**
     * Завантажує об'єкти з файлу в бібліотеку.
     *
     * @param fileName ім'я файлу
     * @param library бібліотека
     */
    public static void loadFromFile(String fileName, Library library) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(";");

                    String type = parts[0];
                    int quantity = Integer.parseInt(parts[1]);

                    String title = parts[2];
                    String author = parts[3];
                    int year = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    int pages = Integer.parseInt(parts[6]);
                    Genre genre = Genre.valueOf(parts[7]);

                    switch (type) {
                        case "EBOOK":
                            double fileSize = Double.parseDouble(parts[8]);
                            String format = parts[9];
                            library.addNewBook(new EBook(title, author, year, price, pages, genre, fileSize, format), quantity);
                            break;

                        case "PAPERBOOK":
                            String cover = parts[8];
                            double weight = Double.parseDouble(parts[9]);
                            library.addNewBook(new PaperBook(title, author, year, price, pages, genre, cover, weight), quantity);
                            break;

                        case "AUDIOBOOK":
                            double fs = Double.parseDouble(parts[8]);
                            String fmt = parts[9];
                            double duration = Double.parseDouble(parts[10]);
                            String narrator = parts[11];
                            library.addNewBook(new AudioBook(title, author, year, price, pages, genre, fs, fmt, duration, narrator), quantity);
                            break;

                        case "TEXTBOOK":
                            String coverT = parts[8];
                            double weightT = Double.parseDouble(parts[9]);
                            String subject = parts[10];
                            int grade = Integer.parseInt(parts[11]);
                            library.addNewBook(new Textbook(title, author, year, price, pages, genre, coverT, weightT, subject, grade), quantity);
                            break;

                        default:
                            System.out.println("Невідомий тип об'єкта: " + type);
                    }

                } catch (Exception e) {
                    System.out.println("Помилка в рядку: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }

    /**
     * Зберігає бібліотеку у файл.
     *
     * @param library бібліотека
     * @param fileName ім'я файлу
     */
    public static void saveToFile(Library library, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (LibraryItem item : library.getItems()) {
                writer.write(serialize(item));
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }

    /**
     * Перетворює елемент бібліотеки у рядок.
     *
     * @param item елемент бібліотеки
     * @return рядок для збереження
     */
    private static String serialize(LibraryItem item) {
        Book b = item.getBook();
        int quantity = item.getQuantity();

        if (b instanceof AudioBook) {
            AudioBook a = (AudioBook) b;
            return "AUDIOBOOK;" + quantity + ";" + a.getTitle() + ";" + a.getAuthor() + ";" + a.getYear() + ";" +
                    a.getPrice() + ";" + a.getPages() + ";" + a.getGenre() + ";" +
                    a.getFileSizeMb() + ";" + a.getFileFormat() + ";" +
                    a.getDurationHours() + ";" + a.getNarrator();
        }

        if (b instanceof Textbook) {
            Textbook t = (Textbook) b;
            return "TEXTBOOK;" + quantity + ";" + t.getTitle() + ";" + t.getAuthor() + ";" + t.getYear() + ";" +
                    t.getPrice() + ";" + t.getPages() + ";" + t.getGenre() + ";" +
                    t.getCoverType() + ";" + t.getWeight() + ";" +
                    t.getSubject() + ";" + t.getGradeLevel();
        }

        if (b instanceof EBook) {
            EBook e = (EBook) b;
            return "EBOOK;" + quantity + ";" + e.getTitle() + ";" + e.getAuthor() + ";" + e.getYear() + ";" +
                    e.getPrice() + ";" + e.getPages() + ";" + e.getGenre() + ";" +
                    e.getFileSizeMb() + ";" + e.getFileFormat();
        }

        if (b instanceof PaperBook) {
            PaperBook p = (PaperBook) b;
            return "PAPERBOOK;" + quantity + ";" + p.getTitle() + ";" + p.getAuthor() + ";" + p.getYear() + ";" +
                    p.getPrice() + ";" + p.getPages() + ";" + p.getGenre() + ";" +
                    p.getCoverType() + ";" + p.getWeight();
        }

        return "";
    }
}