package org.example.tugasmodulke6.com.main;

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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.tugasmodulke6.books.*;
import org.example.tugasmodulke6.data.Admin;
import org.example.tugasmodulke6.data.Student;
import java.util.ArrayList;

public class LibrarySystem {
    public static ArrayList<Book> daftarBuku = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();

    public static void startLibrarySystem(Stage stage) {
        daftarBuku.add(new StoryBook("SB32F", "Diskrit", 17, "Text", "Sofyan"));
        daftarBuku.add(new HistoryBook("HB324", "Kewarganegaraan", 2, "History", "Arif"));
        daftarBuku.add(new TextBook("TB323", "Dragonball", 4, "Story", "Zoro"));

        studentList.add(new Student("202310370311186", "Nizar", "Teknik", "Informatika"));
        studentList.add(new Student("202310370311202", "Revaldo", "Teknik", "Informatika"));
        studentList.add(new Student("20231037031198", "Afla", "Teknik", "Informatika"));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 768, 432);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: gray");

        Text sceneMenu = new Text("==== Library System ====");
        sceneMenu.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));

        Button studentLoginButton = new Button("Login sebagai Mahasiswa");
        Button adminLoginButton = new Button("Login sebagai Admin");
        Button exitButton = new Button("Keluar");

        studentLoginButton.setOnAction(event -> studentLogin(stage));
        adminLoginButton.setOnAction(event -> {
            try {
                new Admin().login(stage);
            } catch (Exception e) {
                showErrorDialog("Error", e.getMessage());
            }
        });
        exitButton.setOnAction(event -> stage.close());

        root.getChildren().addAll(sceneMenu,studentLoginButton, adminLoginButton, exitButton);

        stage.setScene(scene);
        stage.setTitle("Library System");
        stage.show();
    }

    private static void studentLogin(Stage stage) {
        stage.setTitle("Form Login Mahasiswa");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));


        Scene scene = new Scene(grid, 768, 432);
        stage.setScene(scene);

        grid.setStyle("-fx-background-color: gray");

        Text scenetitle = new Text("Login Mahasiswa");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
        grid.add(scenetitle,0,0,2,2);

        Label nim = new Label("NIM :");
        grid.add(nim,0,2);
        TextField nimField = new TextField();
        grid.add(nimField,1,2);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        Button loginButton = new Button("Login");
        grid.add(hBox,1,4);
        Button backButton = new Button("Kembali");
        hBox.getChildren().addAll(loginButton,backButton);

        loginButton.setOnAction(event -> {
            String nimStudent = nimField.getText();
            if (nimStudent.length() == 15 && checkNim(nimStudent)) {
                Student student = new Student(nimStudent);
                student.login(stage);
            } else {
                showErrorDialog("Error", "Nim tidak terdaftar atau tidak valid!");
            }
        });

        backButton.setOnAction(event -> startLibrarySystem(stage));

        grid.getChildren().addAll(scenetitle,nim, nimField);

        stage.setScene(scene);
    }

    private static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean checkNim(String nim) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }
}
