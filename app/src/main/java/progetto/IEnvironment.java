package progetto;

import java.util.List;

public interface IEnvironment <R extends Robot, S extends AbstracShape>{
     List<R> getRobotList();

     List<S> getShapeList();

}
