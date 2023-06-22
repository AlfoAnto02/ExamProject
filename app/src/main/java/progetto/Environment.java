package progetto;

import java.util.List;
import java.util.stream.Collectors;

public record Environment(List<IShape> shapeList, List<IRobot> robotList) implements IEnvironment {


    public List<IRobot> getCollisionList() {
        List<IRobot> collisionList = this.robotList
                .stream()
                .filter(R -> R.checkCollision(this.shapeList) != null)
                .collect(Collectors.toList());
        return collisionList;
    }


}
