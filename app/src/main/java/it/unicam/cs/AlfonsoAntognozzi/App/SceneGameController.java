package it.unicam.cs.AlfonsoAntognozzi.App;

import it.unicam.cs.AlfonsoAntognozzi.model.Environment;
import it.unicam.cs.AlfonsoAntognozzi.model.Handler;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParser;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserException;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SceneGameController {

    @FXML
    AnchorPane gameMap;
    @FXML
    Button zoomIn;
    @FXML
    Button zommOut;
    @FXML
    Button addShapes;
    @FXML
    Button addCommand;
    @FXML
    Button stepBack;
    @FXML
    Button stepForw;
    final Stage commandChooserStage = new Stage();
    final Stage shapeChooserStage = new Stage();
    private Environment env;
    private List<IRobot> robotList = new ArrayList<>();
    private Handler gameHandler;
    private FollowMeParser gameParser;


    public void initilizeRobot(int numberOfRobot){
        Random random = new Random();
        for(int i = 0; i<numberOfRobot;i++){
            double xPosition = random.nextDouble(1000);
            double yPosition = random.nextDouble(900);
            robotList.add(new Robot(new Position(xPosition,yPosition)));
            Image image = new Image("C:\\Users\\Alfonso Antognozzi\\Progetto\\app\\src\\main\\resources\\robot che si muove.jpg");

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            AnchorPane.setTopAnchor(imageView,yPosition);
            AnchorPane.setLeftAnchor(imageView,xPosition);
            gameMap.getChildren().add(imageView);

        }
    env = new Environment(robotList);
    gameParser = new FollowMeParser(new Handler(env));
    }
    public void addCommandToGame(javafx.event.ActionEvent actionEvent) throws Exception {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Add a command file!");
            FileSystemView view = FileSystemView.getFileSystemView();
            String desktopPath = view.getHomeDirectory().getPath();
            fileChooser.setInitialDirectory(new File(desktopPath));
            File selectedFile = fileChooser.showOpenDialog(commandChooserStage);
            gameParser.parseRobotProgram(selectedFile);
        }catch (NullPointerException e ){ System.out.println("Non hai selezionato un file!");}
    }

    public void addShapesToGame(javafx.event.ActionEvent actionEvent) throws FollowMeParserException, IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Add a shape file!");
            FileSystemView view = FileSystemView.getFileSystemView();
            String desktopPath = view.getHomeDirectory().getPath();
            fileChooser.setInitialDirectory(new File(desktopPath));
            File selectedFile = fileChooser.showOpenDialog(shapeChooserStage);
            gameParser.parseEnvironment(selectedFile);
        }catch (NullPointerException e ){ System.out.println("Non hai selezionato un file!");}
    }

    public void stepForwCommand(javafx.event.ActionEvent actionEvent) {

    }
}
