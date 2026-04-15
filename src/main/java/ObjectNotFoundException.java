/**
 * Виняток для ситуації, коли об'єкт не знайдено.
 */
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}