package deadwood.board;

import java.util.ArrayList;
import java.util.List;

//Basic node for board. May be extended for extra features
public class Place {

    private String name;
    private List<Place> neighbors;

    public Place(String name) {
        this.name = name;
        neighbors = new ArrayList<>(4);
    }

    public Place(String name, List<Place> neighbors) {
        this(name);
        this.neighbors = neighbors;
    }

    public void addNeighbor(Place neighbor) {
        neighbors.add(neighbor);
    }

    public List<Place> getNeighbors() {
        return neighbors;
    }

    public String getName() {
        return name;
    }
}
