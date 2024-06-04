package org.example.tugasmodulke6.data;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.tugasmodulke6.books.Book;
import org.example.tugasmodulke6.books.HistoryBook;
import org.example.tugasmodulke6.books.StoryBook;
import org.example.tugasmodulke6.books.TextBook;
import org.example.tugasmodulke6.exception.custom.IllegalAdminAccess;
import org.example.tugasmodulke6.util.iMenu;

import java.util.ArrayList;
import java.util.Scanner;

import static org.example.tugasmodulke6.com.main.LibrarySystem.*;

public class Admin extends User implements iMenu {

    Scanner scanner = new Scanner(System.in);

    public Admin() {
        super("admin");
    }

    public void login(Stage stage) throws IllegalAdminAccess {
        stage.setTitle("Form Login");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Scene scene = new Scene(grid, 768, 432);
        stage.setScene(scene);

        Text scenetitle = new Text("Halaman Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
        grid.add(scenetitle,0,0,2,1);

        Label userName = new Label("Username : ");
        grid.add(userName,0,1);
        TextField userT = new TextField();
        grid.add(userT,1,1);
        scenetitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));


        Label password1 = new Label("Password :");
        grid.add(password1,0,2);
        TextField pwt = new TextField();
        grid.add(pwt,1,2);

        HBox hBox = new HBox(10);
        Button btn = new Button("Log in");
        Button backButton = new Button("Kembali");
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().addAll(btn,backButton);
        grid.add(hBox,0,3,2,1);
        final Text actiontarget = new Text();
        grid.add(actiontarget,1,6);

        btn.setOnAction(event -> {
            String username = userT.getText();
            String password = pwt.getText();
            if (isAdmin(username, password)) {
                showAdminMenu(stage);
            } else {
                showErrorDialog("Error", "Invalid credentials");
            }
        });

        //HBox buttonB = new HBox();
        //Button backButton = new Button("Kembali");
        //buttonB.getChildren().add(backButton);
       // buttonB.setAlignment(Pos.CENTER);
        //grid.add(buttonB,0,3,2,1);
        backButton.setOnAction(event -> startLibrarySystem(stage));


        stage.setScene(scene);
    }

    private boolean isAdmin(String username, String password) {
        return username.equals("nizar") && password.equals("186");
    }

    private void showAdminMenu(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 768, 432);
        root.setAlignment(Pos.CENTER);

        Text welcome = new Text("==== Menu Admin ====");
        welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        Button addStudentButton = new Button("Tambah Mahasiswa");
        Button displayStudentsButton = new Button("Tampilkan Mahasiswa");
        Button inputBookButton = new Button("Input Buku");
        Button displayBooksButton = new Button("Tampilkan Daftar Buku");
        Button logoutButton = new Button("Logout");

        addStudentButton.setOnAction(event -> addStudent(stage));
        displayStudentsButton.setOnAction(event -> displayStudents(stage));
        inputBookButton.setOnAction(event -> inputBook(stage));
        displayBooksButton.setOnAction(event -> displayBooks(stage));
        logoutButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(welcome, addStudentButton, displayStudentsButton, inputBookButton, displayBooksButton, logoutButton);

        stage.setScene(scene);
    }

    private void addStudent(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 768, 432);
        root.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Masukkan Nama: ");
        TextField nameField = new TextField();
        Label nimLabel = new Label("Masukkan NIM: ");
        TextField nimField = new TextField();
        Label facultyLabel = new Label("Masukkan Fakultas: ");
        TextField facultyField = new TextField();
        Label studyProgramLabel = new Label("Masukkan Program Studi: ");
        TextField studyProgramField = new TextField();
        Button addButton = new Button("Tambah");
        Button backButton = new Button("Kembali");

        addButton.setOnAction(event -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String faculty = facultyField.getText();
            String studyProgram = studyProgramField.getText();

            if (nim.length() == 15 && !checkNim(nim)) {
                studentList.add(new Student(nim, name, faculty, studyProgram));
                showAdminMenu(stage);
            } else {
                showErrorDialog("Error", "NIM tidak valid atau sudah terdaftar!");
            }
        });

        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().addAll(nameLabel, nameField, nimLabel, nimField, facultyLabel, facultyField, studyProgramLabel, studyProgramField, addButton, backButton);

        stage.setScene(scene);
    }

    private void inputBook(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 768, 432);
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Masukkan Judul Buku: ");
        TextField titleField = new TextField();
        Label authorLabel = new Label("Masukkan Author Buku: ");
        TextField authorField = new TextField();
        Label categoryLabel = new Label("Masukkan Category Buku: ");
        TextField categoryField = new TextField();
        Label stockLabel = new Label("Masukkan Stok Buku: ");
        TextField stockField = new TextField();
        Button addButton = new Button("Tambah");
        Button backButton = new Button("Kembali");

        addButton.setOnAction(event -> {
            String judul = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            int stok = Integer.parseInt(stockField.getText());

            daftarBuku.add(new Book(generateId("B"), judul, stok, category, author));
            showAdminMenu(stage);
        });

        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().addAll(titleLabel, titleField, authorLabel, authorField, categoryLabel, categoryField, stockLabel, stockField, addButton, backButton);

        stage.setScene(scene);
    }

    private String generateId(String prefix) {
        int id = daftarBuku.size() + 1;
        return prefix + String.format("%03d", id);
    }

    private void displayBooks(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 768, 432);
        root.setAlignment(Pos.CENTER);

        for (Book book : daftarBuku) {
            root.getChildren().add(new Label("ID Buku: " + book.getIdBuku()));
            root.getChildren().add(new Label("Judul: " + book.getJudul()));
            root.getChildren().add(new Label("Author: " + book.getAuthor()));
            root.getChildren().add(new Label("Category: " + book.getCategory()));
            root.getChildren().add(new Label("Stok: " + book.getStok()));
            root.getChildren().add(new Label(""));
        }

        Button backButton = new Button("Kembali");
        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().add(backButton);

        stage.setScene(scene);
    }

    private void displayStudents(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root,768, 432);
        root.setAlignment(Pos.CENTER);

        for (Student student : studentList) {
            root.getChildren().add(new Label("NIM: " + student.getNim()));
            root.getChildren().add(new Label("Nama: " + student.getName()));
            root.getChildren().add(new Label("Fakultas: " + student.getFaculty()));
            root.getChildren().add(new Label("Program Studi: " + student.getStudyProgram()));
            root.getChildren().add(new Label(""));
        }

        Button backButton = new Button("Kembali");
        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().add(backButton);

        stage.setScene(scene);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void menu() {

    }
}
