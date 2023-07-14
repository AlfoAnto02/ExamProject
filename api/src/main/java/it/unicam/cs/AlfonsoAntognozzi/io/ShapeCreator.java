package it.unicam.cs.AlfonsoAntognozzi.io;

import it.unicam.cs.AlfonsoAntognozzi.model.*;
import it.unicam.cs.AlfonsoAntognozzi.model.Rectangle;
import it.unicam.cs.AlfonsoAntognozzi.util.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShapeCreator implements IShapeCreator {

    private final FollowMeParser parser;
    private List<ShapeData> shapeDataList = new ArrayList<>();
    private final IEnvironment<IRobot<IPosition,ICondition>, IShape<IPosition,ICondition,IRobot<IPosition,ICondition>>> environment;


    public ShapeCreator(IEnvironment <IRobot<IPosition,ICondition>, IShape<IPosition,ICondition,IRobot<IPosition,ICondition>>>Env){
        this.environment = Env ;
        parser = new FollowMeParser(null);
    }



    private IShape<IPosition,ICondition,IRobot<IPosition,ICondition>> shapeCreator(Condition label, double x, double y, double width, double height){
        return  new Rectangle<>(new Position(x,y), label,height,width);
    }

    private IShape<IPosition,ICondition,IRobot<IPosition,ICondition>> shapeCreator(Condition label, double x, double y, double radius){
        return  new Circle<>(new Position(x,y), label,radius);
    }

    public void parseShape(String code) throws FollowMeParserException{
        this.shapeDataList = this.parser.parseEnvironment(code);
        this.createShapeList();
    }

    public void parseShape(File file) throws FollowMeParserException, IOException {
        this.shapeDataList = this.parser.parseEnvironment(file);
        this.createShapeList();
    }

    public void parseShape(Path path) throws FollowMeParserException, IOException {
        this.shapeDataList = this.parser.parseEnvironment(path);
        this.createShapeList();
    }

    private void createShapeList() {
        for(ShapeData I : this.shapeDataList){
            if(I.shape().equals("CIRCLE")) environment.addShapeToList( this.shapeCreator(new Condition(I.label()),I.args()[0],I.args()[1], I.args()[2]));
            if(I.shape().equals("RECTANGLE")) environment.addShapeToList(this.shapeCreator(new Condition(I.label()),I.args()[0],I.args()[1],I.args()[2],I.args()[3]));
        }
    }


}
