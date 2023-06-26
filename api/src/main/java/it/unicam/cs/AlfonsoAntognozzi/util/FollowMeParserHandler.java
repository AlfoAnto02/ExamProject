package it.unicam.cs.AlfonsoAntognozzi.util;

/**
 * This interface is implemented to handle parsing of robot programs. Instances of this interface
 * will be used as follows:
 * <ol>
 *     <li>at the beginning of the parsing method {@link #parsingStarted()} parsingStarted()}</li>
 *     <li>each time the parser recognise a command, the corresponding method is invoked</li>
 *     <li>at the end of the parsing procedure method {@link #parsingDone()} is invoked</li>
 * </ol>
 *
 * @see FollowMeParser
 *
 */

public interface FollowMeParserHandler<T extends Object> {
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
     * @param args argomenti del comando.
     */
    void moveCommand(double[] args) throws Exception;

    /**
     * Method invoked when a command "MOVE RANDOM" is parsed.
     *
     * @param args argomenti del comando.
     */
    void moveRandomCommand(double[] args) throws Exception;

    /**
     * Method invoked when a command "SIGNAL" is parsed.
     *
     * @param label label to signal
     */
    void signalCommand(String label) throws Exception;

    /**
     * Method invoked when a command "UNSIGNAL" is parsed.
     *
     * @param label label to unsignal
     */
    void unsignalCommand(String label) throws Exception;

    /**
     * Method invoked when a command "FOLLOW" is parsed.
     *
     * @param label label to follow
     * @param args  command arguments
     */
    void followCommand(String label, double[] args) throws Exception;

    /**
     * Method invoked when a command "STOP" is parsed.
     */
    void stopCommand() throws Exception;

    /**
     * Method invoked when a command "WAIT" is parsed.
     *
     * @    param s number of seconds;
     */
    void continueCommand(int s) throws Exception;

    /**
     * Method invoked when a command "REPEAT" is parsed.
     *
     * @param n number of iterations.
     */
    void repeatCommandStart(int n);

    /**
     * Method invoked when a command "UNTIL" is parsed.
     *
     * @param lable name of a label
     */
    void untilCommandStart(String lable);

    /**
     * Method invoked when a command "DO FOREVER" is parsed.
     */
    void doForeverStart();


    /**
     * Method invoked when a command "DONE" is parsed.
     */
    void doneCommand() throws InterruptedException;

}