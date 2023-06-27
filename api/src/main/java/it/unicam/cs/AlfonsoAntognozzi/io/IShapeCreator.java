package it.unicam.cs.AlfonsoAntognozzi.io;

import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface IShapeCreator {

    void parseShape(String s) throws FollowMeParserException;

    void parseShape (File file) throws FollowMeParserException, IOException;

    void parseShape (Path path) throws FollowMeParserException, IOException;


}
