import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.UUID;

/**
 * JavaFX GUI для роботи з бібліотекою.
 */
public class MainApp extends Application {

    private final Library library = new Library("GUI Library");

    private TextArea listArea;
    private TextArea detailsArea;

    @Override
    public void start(Stage stage) {
        FileService.loadFromFile("input.txt", library);

        Label typeLabel = new Label("Тип:");
        ComboBox<String> typeBox = new ComboBox<String>();
        typeBox.getItems().addAll("EBook", "PaperBook", "AudioBook", "Textbook");
        typeBox.setValue("EBook");

        Label titleLabel = new Label("Назва:");
        TextField titleField = new TextField();

        Label authorLabel = new Label("Автор:");
        TextField authorField = new TextField();

        Label yearLabel = new Label("Рік:");
        TextField yearField = new TextField();

        Label priceLabel = new Label("Ціна:");
        TextField priceField = new TextField();

        Button addButton = new Button("Додати");

        listArea = new TextArea();
        listArea.setEditable(false);
        listArea.setPrefHeight(200);

        Label uuidLabel = new Label("UUID:");
        TextField uuidField = new TextField();
        Button findButton = new Button("Знайти");

        detailsArea = new TextArea();
        detailsArea.setEditable(false);
        detailsArea.setPrefHeight(200);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.add(typeLabel, 0, 0);
        form.add(typeBox, 1, 0);
        form.add(titleLabel, 0, 1);
        form.add(titleField, 1, 1);
        form.add(authorLabel, 0, 2);
        form.add(authorField, 1, 2);
        form.add(yearLabel, 0, 3);
        form.add(yearField, 1, 3);
        form.add(priceLabel, 0, 4);
        form.add(priceField, 1, 4);
        form.add(addButton, 1, 5);

        GridPane searchPane = new GridPane();
        searchPane.setHgap(10);
        searchPane.setVgap(10);
        searchPane.add(uuidLabel, 0, 0);
        searchPane.add(uuidField, 1, 0);
        searchPane.add(findButton, 2, 0);

        addButton.setOnAction(event -> {
            try {
                String type = typeBox.getValue();
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                int year = Integer.parseInt(yearField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());

                Book book;

                if ("EBook".equals(type)) {
                    book = new EBook(title, author, year, price, 100, Genre.SCIENCE, 5.0, "PDF");
                } else if ("PaperBook".equals(type)) {
                    book = new PaperBook(title, author, year, price, 200, Genre.NOVEL, "soft", 0.5);
                } else if ("AudioBook".equals(type)) {
                    book = new AudioBook(title, author, year, price, 150, Genre.FANTASY, 10.0, "MP3", 5.5, "Narrator");
                } else {
                    book = new Textbook(title, author, year, price, 250, Genre.STUDY, "hard", 0.8, "Math", 10);
                }

                library.addNewBook(book, 1);
                updateListArea();
                clearInputFields(titleField, authorField, yearField, priceField);

            } catch (Exception e) {
                detailsArea.setText("Помилка додавання: " + e.getMessage());
            }
        });

        findButton.setOnAction(event -> {
            String uuidText = uuidField.getText().trim();

            try {
                UUID uuid = UUID.fromString(uuidText);
                LibraryItem item = library.searchByUuid(uuid);

                if (item == null) {
                    detailsArea.setText("Не знайдено.");
                } else {
                    detailsArea.setText(item.toString());
                }
            } catch (IllegalArgumentException e) {
                detailsArea.setText("Некоректний формат UUID.");
            }
        });

        updateListArea();

        VBox root = new VBox(12);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(
                new Label("Додавання об'єкта"),
                form,
                new Label("Уся колекція (скорочено)"),
                listArea,
                new Label("Пошук за UUID"),
                searchPane,
                new Label("Повна інформація"),
                detailsArea
        );

        Scene scene = new Scene(root, 700, 700);
        stage.setTitle("Library UUID GUI");
        stage.setScene(scene);
        stage.show();
    }

    private void updateListArea() {
        ArrayList<String> lines = library.getShortViewList();
        StringBuilder builder = new StringBuilder();

        for (String line : lines) {
            builder.append(line).append("\n");
        }

        listArea.setText(builder.toString());
    }

    private void clearInputFields(TextField titleField, TextField authorField, TextField yearField, TextField priceField) {
        titleField.clear();
        authorField.clear();
        yearField.clear();
        priceField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}