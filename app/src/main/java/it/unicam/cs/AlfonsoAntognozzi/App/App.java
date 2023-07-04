package it.unicam.cs.AlfonsoAntognozzi.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL startURL = new File("\\ExamProject\\app\\src\\main\\java\\it\\unicam\\cs\\AlfonsoAntognozzi\\App\\ScenaStart.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(startURL);
        Scene scene = new Scene(root,1000,800);
        Image robottino = new Image("robottino.jpg");
        primaryStage.getIcons().add(robottino);
        URL cssURL = new File("\\ExamProject\\app\\src\\main\\java\\it\\unicam\\cs\\AlfonsoAntognozzi\\App\\application.css").toURI().toURL();
        scene.getStylesheets().add(cssURL.toString());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
