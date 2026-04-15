import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibraryExceptionTest {

    @Test
    void shouldThrowInvalidFieldValueExceptionWhenCreatingBookWithEmptyTitle() {
        assertThrows(InvalidFieldValueException.class, () -> {
            new EBook("", "Author", 2024, 100.0, 200, Genre.SCIENCE, 5.0, "PDF");
        });
    }

    @Test
    void shouldThrowObjectNotFoundExceptionWhenDeletingNonExistingObject() {
        Library library = new Library("Test Library");
        Book book = new EBook("Java", "Author", 2024, 100.0, 200, Genre.SCIENCE, 5.0, "PDF");

        assertThrows(ObjectNotFoundException.class, () -> {
            library.delete(book);
        });
    }

    @Test
    void shouldThrowObjectNotFoundExceptionWhenUpdatingNonExistingObject() {
        Library library = new Library("Test Library");
        Book oldBook = new EBook("Java", "Author", 2024, 100.0, 200, Genre.SCIENCE, 5.0, "PDF");
        Book newBook = new EBook("Java 2", "Author", 2024, 120.0, 210, Genre.SCIENCE, 6.0, "EPUB");

        assertThrows(ObjectNotFoundException.class, () -> {
            library.update(oldBook, newBook);
        });
    }
}