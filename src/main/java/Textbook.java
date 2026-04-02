/**
 * Клас Textbook описує навчальну книгу.
 */
public class Textbook extends PaperBook {
    private String subject;
    private int gradeLevel;

    /**
     * Конструктор з параметрами.
     */
    public Textbook(String title, String author, int year, double price, int pages, Genre genre,
                    String coverType, double weight,
                    String subject, int gradeLevel) {
        super(title, author, year, price, pages, genre, coverType, weight);
        setSubject(subject);
        setGradeLevel(gradeLevel);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Предмет не може бути порожнім.");
        }
        this.subject = subject;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        if (gradeLevel <= 0 || gradeLevel > 12) {
            throw new IllegalArgumentException("Клас має бути від 1 до 12.");
        }
        this.gradeLevel = gradeLevel;
    }

    @Override
    public String toString() {
        return "Textbook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", price=" + getPrice() +
                ", pages=" + getPages() +
                ", genre=" + getGenre() +
                ", coverType='" + getCoverType() + '\'' +
                ", weight=" + getWeight() +
                ", subject='" + subject + '\'' +
                ", gradeLevel=" + gradeLevel +
                '}';
    }
}