package it.unicam.cs.AlfonsoAntognozzi.io;

import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * This class is used to parse the shapes using the shape class hierarchy
 */
public interface IShapeCreator {
    /**
     * Parse a shape using a string
     *
     * @param string string used for parse a shape
     * @throws FollowMeParserException throws a specific Exception for the FollowMeParser Class
     */
    void parseShape(String string) throws FollowMeParserException;

    /**
     * Parse a shape using a file
     *
     * @param file file used for parse a shape
     * @throws FollowMeParserException throws a specific Exception for the FollowMeParser Class
     * @throws IOException throws an IOException if there is an error while reading the file
     */
    void parseShape (File file) throws FollowMeParserException, IOException;

    /**
     * Parse a shape using a path for a file
     *
     * @param path path to a file
     * @throws FollowMeParserException throws a specific Exception for the FollowMeParser Class
     * @throws IOException throws an IOException if there is an error while reading the file
     */
    void parseShape (Path path) throws FollowMeParserException, IOException;


}
