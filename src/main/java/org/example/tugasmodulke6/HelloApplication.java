package org.example.tugasmodulke6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.tugasmodulke6.com.main.LibrarySystem;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Perpustakaan");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Image backgroundImage;
        try {
            backgroundImage = new Image("file:C:/Users/okaya/Downloads/Compressed/Tugas Modul KE6/Tugas Modul KE6/src/main/resources/B1.jpg");
            if (backgroundImage.isError()) {
                System.out.println("Gagal memuat gambar.");
            }
        } catch (Exception e) {
            System.out.println("Kesalahan saat memuat gambar: " + e.getMessage());
            return;
        }

        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        grid.setBackground(new Background(background));

        Scene scene1 = new Scene(grid, 768, 432);
        stage.setScene(scene1);

        Rectangle rectangle = new Rectangle();
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setWidth(600);
        rectangle.setHeight(200);
        rectangle.setFill(Color.WHITE);

        Text welcome = new Text("MULAI PROGRAM?");
        welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));

        Button button = new Button("YA");
        Button button2 = new Button("TIDAK");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(button, button2);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(welcome, buttonBox);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, vBox);
        stackPane.setAlignment(Pos.CENTER);

        grid.add(stackPane, 0, 0);

        button2.setOnAction(actionEvent -> stage.close());
        button.setOnAction(event -> LibrarySystem.startLibrarySystem(stage));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
