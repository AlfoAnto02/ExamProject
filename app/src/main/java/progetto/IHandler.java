package progetto;

public interface IHandler{


    /**
     * This method is the method that is invoked ad the beginning of the parse procedure.
     */
    void parsingStarted();

    /**
     * This method is the method that is invoked ad the end of the parse procedure.
     */
    void parsingDone();


    /**
     * Method invoked when a command "MOVE" is parsed.
     *
     */

    void moveCommand(double[] args);

    /**
     * Method invoked when a command "MOVE RANDOM" is parsed.
     *
     */
    void moveRandomCommand(double[] args);

    /**
     * Method invoked when a command "SIGNAL" is parsed.
     *
     * @param label label to signal
     */
    void signalCommand(String label, Robot R);

    /**
     * Method invoked when a command "UNSIGNAL" is parsed.
     *
     * @param label label to unsignal
     */
    void unsignalCommand(String label, Robot R);

    /**
     * Method invoked when a command "FOLLOW" is parsed.
     *
     * @param label label to follow
     */
    void followCommand(String label, double[] args);

    /**
     * Method invoked when a command "STOP" is parsed.
     */
    void stopCommand();

    /**
     * Method invoked when a command "WAIT" is parsed.
     *
     * @param s number of seconds;
     */
    void continueCommand(int s);

    /**
     * Method invoked when a command "REPEAT" is parsed.
     *
     * @param n number of iterations.
     */
    void repeatCommandStart(int n);

    /**
     * Method invoked when a command "UNTIL" is parsed.
     *
     * @param label name of a label
     */
    void untilCommandStart(String label);

    /**
     * Method invoked when a command "DO FOREVER" is parsed.
     */
    void doForeverStart();


    /**
     * Method invoked when a command "DONE" is parsed.
     */
    void doneCommand();


}
