package it.unicam.cs.AlfonsoAntognozzi.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class SceneStartController {
    @FXML
    Label robotLable;
    @FXML
    TextField robotField;
    @FXML
    Button nextSceneButton;
    @FXML
    Label errorLabel;

    private int robotGenerated;

    public void changeRobotLable(ActionEvent e) {
        try {
            if (Integer.parseInt(robotField.getText()) <= 0) {
                robotLable.setText("THE VALUE YOU HAVE CHOSEN IS TOO LOW, CHOOSE IT > 0");
                nextSceneButton.setTextFill(Color.RED);
            } else if (Integer.parseInt(robotField.getText()) > 200) {
                robotLable.setText("THE VALUE YOU HAVE CHOSEN IS TOO HIGH, CHOOSE IT < 200");
                nextSceneButton.setTextFill(Color.RED);
            } else {
                nextSceneButton.setTextFill(Color.GREEN);
                robotLable.setText("YOU CHOOSE " + robotField.getText() + " ROBOT!!!");
            }
        }catch(NumberFormatException num){
            robotLable.setText("CHOOSE A NUMBER!!!");
            nextSceneButton.setTextFill(Color.RED);
        }


    }
    public void next(ActionEvent e) throws IOException {
        if(nextSceneButton.getTextFill().equals(Color.RED)){
            errorLabel.setOpacity(1.0);
        }
        else if (nextSceneButton.getTextFill().equals(Color.GREEN)){
            robotGenerated=Integer.parseInt(robotField.getText());
            connectToSecondScene(e);
        }

    }

    private void connectToSecondScene(ActionEvent e) throws IOException {
        URL gameURL = new File("..\\app\\src\\main\\java\\it\\unicam\\cs\\AlfonsoAntognozzi\\App\\ScenaGame.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(gameURL);
        Parent root = loader.load();
        SceneGameController sceneGameController = loader.getController();
        sceneGameController.initializeRobot(robotGenerated);
        URL cssURL = new File("..\\app\\src\\main\\java\\it\\unicam\\cs\\AlfonsoAntognozzi\\App\\application.css").toURI().toURL();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssURL.toString());
        stage.setScene(scene);
        stage.show();
    }

}
