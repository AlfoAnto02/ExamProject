package it.unicam.cs.AlfonsoAntognozzi.model;

public interface IHandler{


    /**
     * This method is the method that is invoked ad the beginning of the parse procedure.
     */
    void parsingStarted() throws Exception;

    /**
     * This method is the method that is invoked ad the end of the parse procedure.
     */
    void parsingDone() throws Exception; //Works


    /**
     * Method invoked when a command "MOVE" is parsed.
     *
     */

    void moveCommand(double[] args) throws Exception; //Works

    /**
     * Method invoked when a command "MOVE RANDOM" is parsed.
     *
     */
    void moveRandomCommand(double[] args) throws Exception; //Works

    /**
     * Method invoked when a command "SIGNAL" is parsed.
     *
     * @param label label to signal
     */
    void signalCommand(String label) throws Exception; //Works

    /**
     * Method invoked when a command "UNSIGNAL" is parsed.
     *
     * @param label label to unsignal
     */
    void unsignalCommand(String label) throws Exception; //Works

    /**
     * Method invoked when a command "FOLLOW" is parsed.
     *
     * @param label label to follow
     */
    void followCommand(String label, double[] args) throws Exception;

    /**
     * Method invoked when a command "STOP" is parsed.
     */
    void stopCommand() throws Exception; //Works

    /**
     * Method invoked when a command "WAIT" is parsed.
     *
     * @param s number of seconds;
     */
    void continueCommand(int s) throws Exception; //works

    /**
     * Method invoked when a command "REPEAT" is parsed.
     *
     * @param n number of iterations.
     */
    void repeatCommandStart(int n) throws Exception; //works

    /**
     * Method invoked when a command "UNTIL" is parsed.
     *
     * @param label name of a label
     */
    void untilCommandStart(String label) throws Exception; //Works

    /**
     * Method invoked when a command "DO FOREVER" is parsed.
     */
    void doForeverStart() throws Exception; //Works


    /**
     * Method invoked when a command "DONE" is parsed.
     */
    void doneCommand() throws Exception; //Works


}
