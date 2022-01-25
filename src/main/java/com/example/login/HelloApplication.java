package com.example.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloApplication extends Application {

    Group root = new Group();
    int curr_id = 3;
    @Override
    public void start(Stage stage) throws IOException {
        makeControls();
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void makeControls() {
        Button addToDB = new Button("Add to DB");

        TextField UN = new TextField();
        TextField Pass = new TextField();
        TextField First = new TextField();
        TextField Last = new TextField();

        Label UNLab = new Label("Username");
        Label PassLab = new Label("Password");
        Label FirstLab = new Label("First Name");
        Label LastLab = new Label("Last Name");

        HBox UNBox = new HBox(UNLab, UN);
        HBox PassBox = new HBox(PassLab, Pass);
        HBox FirstBox = new HBox(FirstLab, First);
        HBox LastBox = new HBox(LastLab, Last);

        VBox controls = new VBox(UNBox, PassBox, FirstBox, LastBox, addToDB);

        addToDB.setOnAction(actionEvent -> {
            addToDB(UN.getText(), Pass.getText(), First.getText(), Last.getText());
        });

        root.getChildren().addAll(controls);
    }
    public void addToDB(String UN, String pass, String first, String last) {
        try {
            String url = "jdbc:sqlite:C:/Users/humza.shaikh/IdeaProjects/login/src/main/java/com/example/login/Users";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection(url,"","");
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO users (user_name, password, FIRST, LAST)" +
                    " VALUES ('" + UN + "', '" + pass + "', '" + first + "', '" + last + "') ");
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}