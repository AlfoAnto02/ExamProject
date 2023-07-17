package it.unicam.cs.AlfonsoAntognozzi.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        String fxmlPath = "..\\app\\src\\main\\java\\it\\unicam\\cs\\AlfonsoAntognozzi\\App\\ScenaStart.fxml"; // Percorso relativo per FXML
        Path fxmlAbsolutePath = Paths.get(System.getProperty("user.dir")).resolve(fxmlPath);
        URL fxmlURL = fxmlAbsolutePath.toUri().toURL();
        Parent root = FXMLLoader.load(fxmlURL);
        Scene scene = new Scene(root,1000,800);
        Image startRobot = new Image("iconRobot.jpg");
        primaryStage.getIcons().add(startRobot);
        String cssPath = "..\\app\\src\\main\\java\\it\\unicam\\cs\\AlfonsoAntognozzi\\App\\application.css"; // Percorso relativo per CSS
        Path cssAbsolutePath = Paths.get(System.getProperty("user.dir")).resolve(cssPath);
        URL cssURL = cssAbsolutePath.toUri().toURL();
        scene.getStylesheets().add(cssURL.toString());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
