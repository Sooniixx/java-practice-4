import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Клас для роботи з базою даних через JDBC.
 */
public class DatabaseManager {
    private final String url;
    private final String user;
    private final String password;

    /**
     * Конструктор зчитує параметри підключення з properties-файлу.
     *
     * @param propertiesPath шлях до конфігураційного файлу
     * @throws IOException якщо файл неможливо прочитати
     */
    public DatabaseManager(String propertiesPath) throws IOException {
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(propertiesPath)) {
            properties.load(inputStream);
        }

        this.url = properties.getProperty("db.url");
        this.user = properties.getProperty("db.user");
        this.password = properties.getProperty("db.password");
    }

    /**
     * Створює підключення до БД.
     *
     * @return Connection
     * @throws SQLException якщо не вдалося підключитися
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Додає книгу в БД.
     *
     * @param book книга
     * @param quantity кількість
     * @throws SQLException якщо виникла помилка SQL
     */
    public void insertBook(Book book, int quantity) throws SQLException {
        String sql = "INSERT INTO library_books " +
                "(type, title, author, year, price, pages, genre, quantity, " +
                "file_size_mb, file_format, cover_type, weight, duration_hours, narrator, subject_name, grade_level) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, getTypeName(book));
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setInt(4, book.getYear());
            statement.setDouble(5, book.getPrice());
            statement.setInt(6, book.getPages());
            statement.setString(7, book.getGenre().name());
            statement.setInt(8, quantity);

            if (book instanceof AudioBook) {
                AudioBook audioBook = (AudioBook) book;
                statement.setDouble(9, audioBook.getFileSizeMb());
                statement.setString(10, audioBook.getFileFormat());
                statement.setNull(11, java.sql.Types.VARCHAR);
                statement.setNull(12, java.sql.Types.DOUBLE);
                statement.setDouble(13, audioBook.getDurationHours());
                statement.setString(14, audioBook.getNarrator());
                statement.setNull(15, java.sql.Types.VARCHAR);
                statement.setNull(16, java.sql.Types.INTEGER);
            } else if (book instanceof Textbook) {
                Textbook textbook = (Textbook) book;
                statement.setNull(9, java.sql.Types.DOUBLE);
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setString(11, textbook.getCoverType());
                statement.setDouble(12, textbook.getWeight());
                statement.setNull(13, java.sql.Types.DOUBLE);
                statement.setNull(14, java.sql.Types.VARCHAR);
                statement.setString(15, textbook.getSubject());
                statement.setInt(16, textbook.getGradeLevel());
            } else if (book instanceof EBook) {
                EBook eBook = (EBook) book;
                statement.setDouble(9, eBook.getFileSizeMb());
                statement.setString(10, eBook.getFileFormat());
                statement.setNull(11, java.sql.Types.VARCHAR);
                statement.setNull(12, java.sql.Types.DOUBLE);
                statement.setNull(13, java.sql.Types.DOUBLE);
                statement.setNull(14, java.sql.Types.VARCHAR);
                statement.setNull(15, java.sql.Types.VARCHAR);
                statement.setNull(16, java.sql.Types.INTEGER);
            } else if (book instanceof PaperBook) {
                PaperBook paperBook = (PaperBook) book;
                statement.setNull(9, java.sql.Types.DOUBLE);
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setString(11, paperBook.getCoverType());
                statement.setDouble(12, paperBook.getWeight());
                statement.setNull(13, java.sql.Types.DOUBLE);
                statement.setNull(14, java.sql.Types.VARCHAR);
                statement.setNull(15, java.sql.Types.VARCHAR);
                statement.setNull(16, java.sql.Types.INTEGER);
            } else {
                statement.setNull(9, java.sql.Types.DOUBLE);
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setNull(11, java.sql.Types.VARCHAR);
                statement.setNull(12, java.sql.Types.DOUBLE);
                statement.setNull(13, java.sql.Types.DOUBLE);
                statement.setNull(14, java.sql.Types.VARCHAR);
                statement.setNull(15, java.sql.Types.VARCHAR);
                statement.setNull(16, java.sql.Types.INTEGER);
            }

            statement.executeUpdate();
        }
    }

    /**
     * Визначає назву типу для поля type.
     *
     * @param book книга
     * @return назва типу
     */
    private String getTypeName(Book book) {
        if (book instanceof AudioBook) {
            return "AUDIOBOOK";
        }
        if (book instanceof Textbook) {
            return "TEXTBOOK";
        }
        if (book instanceof EBook) {
            return "EBOOK";
        }
        if (book instanceof PaperBook) {
            return "PAPERBOOK";
        }
        return "BOOK";
    }
}