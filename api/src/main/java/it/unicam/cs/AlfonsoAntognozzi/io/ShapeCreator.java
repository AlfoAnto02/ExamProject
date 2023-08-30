package it.unicam.cs.AlfonsoAntognozzi.io;

import it.unicam.cs.AlfonsoAntognozzi.model.*;
import it.unicam.cs.AlfonsoAntognozzi.model.Rectangle;
import it.unicam.cs.AlfonsoAntognozzi.util.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the interface IShapeCreator and its task is to recognize if a shape parsed is a Rectangle
 * or a Circle and, then, add it to the shapeList of the Environment.
 */
public class ShapeCreator implements IShapeCreator {
    private final FollowMeParser parser;
    private List<ShapeData> shapeDataList = new ArrayList<>();
    private final IEnvironment<IRobot<IPosition,ICondition>, IShape<IPosition,ICondition,IRobot<IPosition,ICondition>>> environment;

    /**
     * This is the constructor of the class
     *
     * @param Env Environment of the simulation
     */
    public ShapeCreator(IEnvironment <IRobot<IPosition,ICondition>, IShape<IPosition,ICondition,IRobot<IPosition,ICondition>>>Env){
        this.environment = Env ;
        parser = new FollowMeParser(null);
    }


    /**
     * The task of this method is to instantiate a Rectangle if a Rectangle has been parsed through the FollowMeParser
     *
     * @param label label of the rectangle
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @return a new instance of a Rectangle with the passed position, condition, width and height
     */
    private IShape<IPosition,ICondition,IRobot<IPosition,ICondition>> shapeCreator(Condition label, double x, double y, double width, double height){
        return  new Rectangle<>(new Position(x,y), label,height,width);
    }

    /**
     * The task of this method is to instantiate a Circle if a Circle has been parsed through the FollowMeParser
     *
     * @param label label of the Circle
     * @param x x coordinate
     * @param y y coordinate
     * @param radius radius of the Circle
     * @return a new instance of a Circle with the passed position, condition and label.
     */
    private IShape<IPosition,ICondition,IRobot<IPosition,ICondition>> shapeCreator(Condition label, double x, double y, double radius){
        return  new Circle<>(new Position(x,y), label,radius);
    }

    /**
     * This method is used to parse a string using the FollowMeParser and then calling the createShapeList method
     *
     * @param code string passed as input
     * @throws FollowMeParserException throws a specific Exception for the FollowMeParser Class
     */
    public void parseShape(String code) throws FollowMeParserException{
        this.shapeDataList = this.parser.parseEnvironment(code);
        this.createShapeList();
    }

    /**
     * This method is used to parse a file using the FollowMeParser and then calling the createShapeList Method
     *
     * @param file file passed as input
     * @throws FollowMeParserException throws a specific Exception for the FollowMeParser Class
     * @throws IOException throws an IOException if there is an error while reading the file
     */
    public void parseShape(File file) throws FollowMeParserException, IOException {
        this.shapeDataList = this.parser.parseEnvironment(file);
        this.createShapeList();
    }

    /**
     * This method is used to parse a path to a file using the FollowMeParser and then calling the createShapeList Method
     *
     * @param path path to a file passed as input
     * @throws FollowMeParserException throws a specific Exception for the FollowMeParser Class
     * @throws IOException throws an IOException if there is an error while reading the file
     */
    public void parseShape(Path path) throws FollowMeParserException, IOException {
        this.shapeDataList = this.parser.parseEnvironment(path);
        this.createShapeList();
    }

    /**
     * Method used to recognize if a shape parsed is a Circle or a Rectangle and, then, add the same shape to the shapeList
     * of the Environment
     */
    private void createShapeList() {
        for(ShapeData I : this.shapeDataList){
            if(I.shape().equals("CIRCLE")) environment.addShapeToList( this.shapeCreator(new Condition(I.label()),I.args()[0],I.args()[1], I.args()[2]));
            if(I.shape().equals("RECTANGLE")) environment.addShapeToList(this.shapeCreator(new Condition(I.label()),I.args()[0],I.args()[1],I.args()[2],I.args()[3]));
        }
    }


}
