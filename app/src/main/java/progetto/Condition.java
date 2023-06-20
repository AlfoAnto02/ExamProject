package progetto;

public class Condition implements IShapeCondition{

    private final String Label;

    public Condition (String Label){
        this.Label = Label;
    }

    public String getCondition(){
        return this.Label;
    }
}
