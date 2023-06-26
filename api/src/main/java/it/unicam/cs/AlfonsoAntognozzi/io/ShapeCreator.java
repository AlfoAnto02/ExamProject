package it.unicam.cs.AlfonsoAntognozzi.io;

import it.unicam.cs.AlfonsoAntognozzi.model.*;
import it.unicam.cs.AlfonsoAntognozzi.model.Rectangle;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParser;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserException;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import it.unicam.cs.AlfonsoAntognozzi.util.ShapeData;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShapeCreator<S extends IShape> implements IShapeCreator {

    private FollowMeParser parser;
    private List<ShapeData> shapeDataList = new ArrayList<>();
    private Environment Env;


    public ShapeCreator(Environment Env){
        this.Env=Env;
        parser = new FollowMeParser(null);
    }



    private S shapeCreator(Condition label, double x, double y, double width, double higth){
        return (S) new Rectangle(new Position(x,y), label,higth,width);
    }

    private S shapeCreator(Condition label, double x, double y, double radius){
        return (S) new Circle(new Position(x,y), label,radius);
    }

    public void parseShape(String code) throws FollowMeParserException{
        this.shapeDataList=this.parser.parseEnvironment(code);
        this.createShapeList();
    }

    public void parseShape(File file) throws FollowMeParserException, IOException {
        this.shapeDataList=this.parser.parseEnvironment(file);
        this.createShapeList();
    }

    public void parseShape(Path path) throws FollowMeParserException, IOException {
        this.shapeDataList=this.parser.parseEnvironment(path);
        this.createShapeList();
    }

    private void createShapeList() {
        for(ShapeData I : this.shapeDataList){
            if(I.shape().equals("CIRCLE")) Env.addShapeToList(this.shapeCreator(new Condition(I.label()),I.args()[0],I.args()[1], I.args()[2]));
            if(I.shape().equals("RECTANGLE")) Env.addShapeToList(this.shapeCreator(new Condition(I.label()),I.args()[0],I.args()[1],I.args()[2],I.args()[3]));
        }
    }


}
