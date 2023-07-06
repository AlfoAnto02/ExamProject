package it.unicam.cs.AlfonsoAntognozzi.App;

import it.unicam.cs.AlfonsoAntognozzi.io.ShapeCreator;
import it.unicam.cs.AlfonsoAntognozzi.model.*;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParser;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserException;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class SceneGameController <R extends IRobot, S extends IShape> {

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
    Button stepForw;
    @FXML
    ToolBar ButtonMenu;
    @FXML
    VBox ToolMenu;
    private static final double ZOOM_FACTOR = 0.1;
    private double zoomLevel;
    private ShapeCreator SC;
    final Stage commandChooserStage = new Stage();
    final Stage shapeChooserStage = new Stage();
    private Environment <R,S> env;
    private List<Robot> robotList = new ArrayList<>();
    private FollowMeParser gameParser;
    private Point2D lastMouseLocation;;
    private List<ImageView> imageViews = new ArrayList<>();



    public void initializeRobot(int numberOfRobot){
    zoomLevel=gameMap.getScaleX();
    initializeList(numberOfRobot);
    gameMap.getChildren().addAll(imageViews);
    env = new Environment(robotList);
    gameParser = new FollowMeParser(new Handler(env));
    SC = new ShapeCreator<>(env);
    }

    private void initializeList(int numberOfRobot) {
        Random random = new Random();
        for(int i = 0; i<numberOfRobot;i++){
            double xPosition = random.nextDouble(666);
            double yPosition = random.nextDouble(508);
            Image image = new Image("robot che si muove.jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(15);
            imageView.setFitHeight(15);
            imageView.setLayoutX(xPosition);
            imageView.setLayoutY(yPosition);
            imageViews.add(imageView);
            robotList.add(new Robot(new Position(xPosition,yPosition)));
        }
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
            S Shape = env.shapeList().get(i);
            if (Shape instanceof Rectangle) {
                javafx.scene.shape.Rectangle R = new javafx.scene.shape.Rectangle(Shape.getShapePosition().getX(), Shape.getShapePosition().getY(),
                        ((Rectangle) Shape).getWidth(), ((Rectangle) Shape).getHeight());
                R.setFill(Color.TRANSPARENT);
                R.setStroke(Color.BLACK);
                gameMap.getChildren().add(R);
                System.out.println("Questo è un rettangolo");
            } else if (Shape instanceof Circle) {
                javafx.scene.shape.Circle C = new javafx.scene.shape.Circle(Shape.getShapePosition().getX(), Shape.getShapePosition().getY(), ((Circle) Shape).getRadius());
                C.setFill(Color.TRANSPARENT);
                C.setStroke(Color.BLACK);
                gameMap.getChildren().add(C);
                System.out.println("Questo è un cerchio");
            }
        }
    }


    public void stepForwCommand(javafx.event.ActionEvent actionEvent) {
            Executor();
    }

    public void autoPlay(javafx.event.ActionEvent actionEvent) {
        TimerTask task = new TimerTask(){
            public void run(){
                Executor();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 2000);
    }

    private void Executor() {
        this.env.executeNextIstruction();
        for (int i = 0; i < imageViews.size(); i++){
            TranslateTransition transition = new TranslateTransition(Duration.millis(1000), imageViews.get(i));
            transition.setToX(env.robotList().get(i).getRobotPosition().getX()-imageViews.get(i).getLayoutX());
            transition.setToY(env.robotList().get(i).getRobotPosition().getY()-imageViews.get(i).getLayoutY());
            transition.play();
        }
    }

    public void handleMousePressed(MouseEvent event){
        lastMouseLocation = new Point2D(event.getSceneX(), event.getSceneY());
    }


    public void handleMouseDragged(MouseEvent event){
        if(lastMouseLocation!=null){
            double offsetX = event.getSceneX() - lastMouseLocation.getX();
            double offsetY = event.getSceneY() - lastMouseLocation.getY();
            gameMap.setLayoutX(gameMap.getLayoutX()+offsetX);
            gameMap.setLayoutY(gameMap.getLayoutY()+offsetY);
            lastMouseLocation=new Point2D(event.getSceneX(),event.getSceneY());
        }
    }

    public void zoomInEvent(javafx.event.ActionEvent actionEvent){
        zoomIn();
    }

    public void zoomOutEvent(javafx.event.ActionEvent actionEvent){
        zoomOut();
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.PLUS || keyEvent.getCode() == KeyCode.ADD) zoomIn();
        else if (keyEvent.getCode() == KeyCode.MINUS || keyEvent.getCode() == KeyCode.SUBTRACT) zoomOut();
    }
    public void handleScroll(ScrollEvent event){
        double delta = event.getDeltaY();
        if(delta > 0) zoomIn();
        else if(delta < 0) zoomOut();
        event.consume();
    }

    private void zoomIn() {
        zoomLevel+=ZOOM_FACTOR;
        gameMap.setScaleY(zoomLevel);
        gameMap.setScaleX(zoomLevel);
    }

    private void zoomOut() {
        zoomLevel-=ZOOM_FACTOR;
        if(zoomLevel<0) zoomLevel=0;
        gameMap.setScaleY(zoomLevel);
        gameMap.setScaleX(zoomLevel);
    }



}
