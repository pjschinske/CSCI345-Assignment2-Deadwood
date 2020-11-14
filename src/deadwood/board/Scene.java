package deadwood.board;

import java.util.List;
import java.util.Random;

public class Scene {

    private String name;
    private String description;
    private int number;
    //roles should be sorted in descending rank order
    private List<Role> roles;
    private int budget;

    private static Random random = new Random();

    public Scene(String name, String description, int number, List<Role> roles, int budget) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.roles = roles;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public int getBudget() {
        return budget;
    }
}
