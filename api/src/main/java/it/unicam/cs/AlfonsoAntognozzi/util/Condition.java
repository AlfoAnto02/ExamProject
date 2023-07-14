package it.unicam.cs.AlfonsoAntognozzi.util;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * This class implements the ICondition interface and its task is to check if the label passed respect the specifics of the
 * project. Everytime this class will be instanced, the constructor will check if the string contains only alphanumeric
 * characters and the underscore "_".
 */
public class Condition implements ICondition {

    private final String label;

    public Condition (String label){
        if(label == null || label.trim().isEmpty()) throw new NullPointerException("Incorrect format of string");
        Pattern p = Pattern.compile("[^A-Za-z0-9 _]");
        Matcher m = p.matcher(label);
        boolean b = m.find();
        if(b) throw new IllegalArgumentException("Special character not accepted in my string");
        else this.label =label;
    }

    public String getCondition(){
        return this.label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return Objects.equals(label, condition.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
