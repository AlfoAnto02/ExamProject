package it.unicam.cs.AlfonsoAntognozzi.util;

import java.util.Objects;

public class Position implements IPosition {

    private double x;

    private double y;

    /**
     * This is the constructor of the class
     *
     * @param x initial x
     * @param y initial y
     */
    public Position (double x, double y){
        this.x=x;
        this.y=y;
    }

    /**
     * Method used to get the x coordinate
     *
     * @return the x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Method used to set a new x for the position
     *
     * @param x new x that has to be set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Method used to get the Y coordinate
     *
     * @return the y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Method used to set a new x for the position
     *
     * @param y new y that has to be set
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(position.x, x) == 0 && Double.compare(position.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
