package deadwood.board;

import deadwood.Player;

public class Role {
    private String name;
    private String line;
    private int requiredRank;
    private Player player;

    public Role(String name, String line, int requiredRank) {
        this.name = name;
        this.line = line;
        this.requiredRank = requiredRank;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public int getRequiredRank() {
        return requiredRank;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean hasPlayer() {
        return player != null;
    }
}
