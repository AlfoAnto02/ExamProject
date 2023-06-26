package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Condition implements IShapeCondition{

    private final String Label;

    public Condition (String Label){
        if(Label == null || Label.trim().isEmpty()) throw new NullPointerException("Incorrect format of string");
        Pattern p = Pattern.compile("[^A-Za-z0-9 _]");
        Matcher m = p.matcher(Label);
        boolean b = m.find();
        if(b) throw new IllegalArgumentException("Special character not accepted in my string");
        else this.Label=Label;
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
