import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Book book = new Book("Valid title", "Valid author", 2020, 250.0, 300, "Novel");

        assertThrows(IllegalArgumentException.class, () -> {
            book.setPrice(-10);
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Book("", "Author", 2020, 200.0, 150, "Novel");
        });
    }
}