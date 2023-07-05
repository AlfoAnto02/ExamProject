package it.unicam.cs.AlfonsoAntognozzi.App;

import it.unicam.cs.AlfonsoAntognozzi.io.ShapeCreator;
import it.unicam.cs.AlfonsoAntognozzi.model.*;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParser;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserException;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SceneGameController {

    @FXML
    AnchorPane generalMap;
    @FXML
    Pane gameMap;
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
    @FXML
    ToolBar ButtonMenu;
    private static final double ZOOM_FACTOR = 0.1;
    private double zoomLevel;
    private ShapeCreator SC;
    final Stage commandChooserStage = new Stage();
    final Stage shapeChooserStage = new Stage();
    private Environment env;
    private List<IRobot> robotList = new ArrayList<>();
    private Handler gameHandler;
    private FollowMeParser gameParser;


    public void initilizeRobot(int numberOfRobot){
        zoomLevel=gameMap.getScaleX();
        Random random = new Random();
        for(int i = 0; i<numberOfRobot;i++){
            double xPosition = random.nextDouble(999);
            double yPosition = random.nextDouble(763);
            robotList.add(new Robot(new Position(xPosition,yPosition)));
            Image image = new Image("robot che si muove.jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(15);
            imageView.setFitHeight(15);
            gameMap.getChildren().add(imageView);
        }
        System.out.println(gameMap.getChildren().size());
    env = new Environment(robotList);
    gameParser = new FollowMeParser(new Handler(env));
    SC = new ShapeCreator<>(env);
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
            SC.parseShape(selectedFile);
            generateSelectedShapes();
        }catch (NullPointerException e ){ System.out.println("Non hai selezionato un file!");}
    }

    private void generateSelectedShapes() {
        for (int i = 0; i < env.shapeList().size(); i++) {
            IShape S = env.shapeList().get(i);
            if (S instanceof Rectangle) {
                javafx.scene.shape.Rectangle R = new javafx.scene.shape.Rectangle(S.getShapePosition().getX(), S.getShapePosition().getY(),
                        ((Rectangle) S).getWidth(), ((Rectangle) S).getHeight());
                R.setFill(Color.TRANSPARENT);
                R.setStroke(Color.BLACK);
                gameMap.getChildren().add(R);
                System.out.println("Questo è un rettangolo");
            } else if (S instanceof Circle) {
                javafx.scene.shape.Circle C = new javafx.scene.shape.Circle(S.getShapePosition().getX(), S.getShapePosition().getY(), ((Circle) S).getRadius());
                C.setFill(Color.TRANSPARENT);
                C.setStroke(Color.BLACK);
                gameMap.getChildren().add(C);
                System.out.println("Questo è un cerchio");
            }
        }
    }


    public void stepForwCommand(javafx.event.ActionEvent actionEvent) {
        if(this.env.hasNextInstruction()) {
            this.env.executeNextIstruction();
            for(int i = 0; i < env.robotList().size(); i++){
                TranslateTransition translate = new TranslateTransition();
                translate.setDuration(Duration.millis(1000));
                translate.setNode(gameMap.getChildren().get(i));
                translate.setByX(env.robotList().get(i).getRobotPosition().getX()- gameMap.getChildren().get(i).getLayoutX());
                translate.setByY(env.robotList().get(i).getRobotPosition().getY()- gameMap.getChildren().get(i).getLayoutY());
                translate.play();
            }
        }
    }

    public void zoomInEvent(javafx.event.ActionEvent actionEvent){
        zoomLevel+=ZOOM_FACTOR;
        gameMap.setScaleY(zoomLevel);
        gameMap.setScaleX(zoomLevel);

    }

    public void zoomOutEvent(javafx.event.ActionEvent actionEvent){
        zoomLevel-=ZOOM_FACTOR;
        if(zoomLevel<0) zoomLevel=0;
        gameMap.setScaleY(zoomLevel);
        gameMap.setScaleX(zoomLevel);

    }

}
