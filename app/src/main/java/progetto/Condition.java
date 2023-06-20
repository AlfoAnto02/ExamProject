package progetto;

public class Condition implements IShapeCondition{

    private final String Label;

    public Condition (String Label){
        if(Label == null) throw new NullPointerException("Can't instance a null label");
        this.Label = Label;
    }

    public String getCondition(){
        return this.Label;
    }
}
