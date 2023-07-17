package it.unicam.cs.AlfonsoAntognozzi.App;

import it.unicam.cs.AlfonsoAntognozzi.io.ShapeCreator;
import it.unicam.cs.AlfonsoAntognozzi.model.*;
import it.unicam.cs.AlfonsoAntognozzi.util.*;
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
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class SceneGameController  {

    @FXML
    AnchorPane generalMap;
    @FXML
    Pane gameMap;
    @FXML
    Button zoomIn;
    @FXML
    Button zoomOut;
    @FXML
    Button addShapes;
    @FXML
    Button addCommand;
    @FXML
    Button stepForward;
    @FXML
    ToolBar ButtonMenu;
    @FXML
    VBox ToolMenu;
    private static final double ZOOM_FACTOR = 0.1;
    private double zoomLevel;
    private ShapeCreator SC;
    final Stage commandChooserStage = new Stage();
    final Stage shapeChooserStage = new Stage();
    private Environment <IRobot<IPosition, ICondition>,IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> env;
    private final List<IRobot<IPosition, ICondition>> robotList = new ArrayList<>();
    private FollowMeParser gameParser;
    private Point2D lastMouseLocation;
    private final List<ImageView> imageViews = new ArrayList<>();
    private double gameMapWidth;
    private double gameMapHeight;

    /***
     * This method is used to initialize the robotList of the environment with a random value for the Position between
     * -(gameMapWidth/2),gameMapWidth/2 and -(gameMapHeight/2),gameMapHeight/2). It is also used for initialize the local
     * variable of the class.
     * @param numberOfRobot Number of robot to generate
     */
    public void initializeRobot(int numberOfRobot){
    gameMapWidth=gameMap.getPrefWidth();
    gameMapHeight=gameMap.getPrefHeight();
    zoomLevel=gameMap.getScaleX();
    initializeList(numberOfRobot);
    gameMap.getChildren().addAll(imageViews);
    env = new Environment<>(robotList);
    gameParser = new FollowMeParser(new Handler<>(env));
    SC = new ShapeCreator(env);
    }
    /*
    This a support Method.
     */
    private void initializeList(int numberOfRobot) {
        Random random = new Random();
        for(int i = 0; i<numberOfRobot;i++){
            double xPosition = random.nextDouble(-(gameMapWidth/2),gameMapWidth/2);
            double yPosition = random.nextDouble(-(gameMapHeight/2),gameMapHeight/2);
            robotList.add(new Robot(new Position(xPosition,yPosition)));
            Image image = new Image("mapRobot.jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            imageView.setLayoutX(getTranslateX(xPosition));
            imageView.setLayoutY(getTranslateY(yPosition));
            imageViews.add(imageView);
        }
    }

    /*
    Those method getTranslateX and getTranslateY are used for translate the robot position based on the coordinates of
    the Cartesian Axes to the Computer position based on Computer coordinates. The difference is that in the Cartesian Axes
    the center (0,0) is in the middle of the Pane meanwhile in the Computer the (0,0) is located in the top left corner.
     */
    private double getTranslateX(double x){
        return (x+gameMapWidth/2);
    }

    private double getTranslateY(double y){
        return (gameMapHeight/2-y);
    }


    /*
    Method used for add a command file to the game.
     */
    public void addCommandToGame(javafx.event.ActionEvent actionEvent) throws Exception {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Add a command file!");
            FileSystemView view = FileSystemView.getFileSystemView();
            String desktopPath = view.getHomeDirectory().getPath();
            fileChooser.setInitialDirectory(new File(desktopPath));
            File selectedFile = fileChooser.showOpenDialog(commandChooserStage);
            gameParser.parseRobotProgram(selectedFile);
        }catch (NullPointerException e ){ System.out.println("You didn't select a command File!");}
    }

    /*
    Method used for add a shape file to the game.
     */
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
        }catch (NullPointerException e ){ System.out.println("You didn't select a shape file");}
    }

    /*
    Method used to generate the shapes selected from the shape file.
     */
    private void generateSelectedShapes() {
        for (int i = 0; i < env.getShapeList().size(); i++) {
            IShape<IPosition, ICondition, IRobot<IPosition, ICondition>> Shape = env.getShapeList().get(i);
            if (Shape instanceof Rectangle) {
                javafx.scene.shape.Rectangle R = new javafx.scene.shape.Rectangle(getTranslateX(Shape.getShapePosition().getX()), getTranslateY(Shape.getShapePosition().getY()),
                        ((Rectangle<?, ?, ?>) Shape).getWidth(), ((Rectangle<?, ?, ?>) Shape).getHeight());
                R.setFill(Color.TRANSPARENT);
                R.setStroke(Paint.valueOf("FCBC58"));
                R.setStrokeWidth(2);
                gameMap.getChildren().add(R);
            } else if (Shape instanceof Circle) {
                javafx.scene.shape.Circle C = new javafx.scene.shape.Circle(getTranslateX(Shape.getShapePosition().getX()), getTranslateY(Shape.getShapePosition().getY()), ((Circle<?, ?, ?>) Shape).getRadius());
                C.setFill(Color.TRANSPARENT);
                C.setStroke(Paint.valueOf("F57C51"));
                C.setStrokeWidth(2);
                gameMap.getChildren().add(C);
            }
        }
    }


    /*
    Method used to continue the simulation manually.
     */
    public void stepForwardCommand(javafx.event.ActionEvent actionEvent) {
            Executor();
    }

    /*
    Method used to continue the simulation automatically.
     */
    public void autoPlay(javafx.event.ActionEvent actionEvent) {
        TimerTask task = new TimerTask(){
            public void run(){
                Executor();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1400);
    }


    /*
    Method used for execute the nextInstruction in the Environment and translate the robot positions in the game map.
     */
    private void Executor() {
        this.env.executeNextInstruction();
        for (int i = 0; i < imageViews.size(); i++){
            TranslateTransition transition = new TranslateTransition(Duration.millis(1000), imageViews.get(i));
            transition.setToX(getTranslateX(env.getRobotList().get(i).getRobotPosition().getX())-imageViews.get(i).getLayoutX());
            transition.setToY(getTranslateY(env.getRobotList().get(i).getRobotPosition().getY())-imageViews.get(i).getLayoutY());
            transition.play();
        }
    }

    /*
    Those are GUI method for handle the zoomIn and zoomOut even and for move the game map Pane with the mouse.
     */
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
