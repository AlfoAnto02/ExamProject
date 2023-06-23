package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.Objects;

public class Condition implements IShapeCondition{

    private final String Label;

    public Condition (String Label){
        if(Label == null) throw new NullPointerException("Can't instance a null label");
        this.Label = Label;
    }

    public String getCondition(){
        return this.Label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return Objects.equals(Label, condition.Label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Label);
    }
}
