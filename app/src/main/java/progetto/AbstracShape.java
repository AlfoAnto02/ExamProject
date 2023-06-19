package progetto;

public abstract  class AbstracShape implements IShape {
    private Position ShapePosition;
    private final Condition Shapecondition;

    public AbstracShape (Position p, Condition S){
        this.ShapePosition = p;

        this.Shapecondition = S;
    }

    public Position  getShapePosition (){
        return this.ShapePosition;
    }
    public Condition getShapecondition(){
        return this.Shapecondition;
    }

    public void setShapePosition(Position p){
        this.getShapePosition().setX(p.getX());
        this.getShapePosition().setY(p.getY());
    }


}
