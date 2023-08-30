package it.unicam.cs.AlfonsoAntognozzi.util;

public interface IPosition {
     /**
      * Method used to get the x coordinate
      *
      * @return the x coordinate
      */
     double getX();

     /**
      * Method used to get the Y coordinate
      *
      * @return the y coordinate
      */
     double getY();

     /**
      * Method used to set a new x for the position
      *
      * @param x new x that has to be set
      */
     void setX(double x);

     /**
      * Method used to set a new y for the position
      *
      * @param y new y that has to be set
      */
     void setY(double y);
}
