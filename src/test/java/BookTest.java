import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookTest {

    @Test
    void shouldThrowInvalidFieldValueExceptionWhenInvalidValueInSetter() {
        EBook book = new EBook("Valid title", "Valid author", 2020, 250.0, 300, Genre.SCIENCE, 5.0, "PDF");

        assertThrows(InvalidFieldValueException.class, () -> {
            book.setPrice(-10);
        });
    }

    @Test
    void shouldThrowInvalidFieldValueExceptionWhenInvalidConstructorData() {
        assertThrows(InvalidFieldValueException.class, () -> {
            new EBook("", "Author", 2020, 200.0, 150, Genre.SCIENCE, 5.0, "PDF");
        });
    }
}