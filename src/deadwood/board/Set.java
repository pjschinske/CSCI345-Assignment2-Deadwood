package deadwood.board;

import deadwood.Deadwood;
import deadwood.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

//A Place that can hold a Scene
public class Set extends Place {
    private Scene scene;
    private List<Role> roles;
    private int shots;
    private int shotsLeft;

    private static Random random = new Random();

    public Set(String name, int shots) {
        super(name);
        this.shots = shots;
        this.shotsLeft = shots;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void clearScene() {
        this.scene = null;
    }

    public void rehearse(Player player) {
        player.rehearse(scene.getBudget());
    }

    public void act(Player player) {
        int roleIndex = -1;
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getPlayer() == player) {
                roleIndex = i;
                break;
            }
        }
        boolean isOnCard = false;
        List<Role> onCardRoles = scene.getRoles();
        if (roleIndex == -1) {
            isOnCard = true;
            for (int i = 0; i < onCardRoles.size(); i++) {
                if (onCardRoles.get(i).getPlayer() == player) {
                    roleIndex = i;
                    break;
                }
            }
        }

        int diceRoll = random.nextInt(6) + 1;

        if (isOnCard) {
            if (diceRoll + player.getPracticeChips() >= scene.getBudget()) {
                shotsLeft--;
                System.out.println("Success! You completed the shot and got two credits.");
                player.addCredits(2);
            } else {
                System.out.println("Oh no! You messed up the shot and got nothing.");
            }
        } else {
            Deadwood.bank.payMoney(player, 1);
            if (diceRoll + player.getPracticeChips() >= scene.getBudget()) {
                shotsLeft--;
                System.out.println("Success! You completed the shot and got a credit and $1.");
                player.addCredits(1);
            } else {
                System.out.println("Oh no! You messed up the shot. You still get $1.");
            }
        }

        if (shotsLeft == 0)
            wrap();
    }

    private void wrap() {
        System.out.println("That's a wrap!");
        List<Role> onCardRoles = scene.getRoles();
        for (int i = 0; i < onCardRoles.size(); i++) {
            if (onCardRoles.get(i).hasPlayer()) {
                int[] diceRolls = new int[scene.getBudget()];
                //roll the dice
                for (int j = 0; j < scene.getBudget(); j++) {
                    diceRolls[j] = random.nextInt(6) + 1;
                }
                //sort the dice
                Arrays.sort(diceRolls);
                //set up the payouts and distribute them among the players
                int[] payouts = new int[onCardRoles.size()];

                for (int j = scene.getBudget() - 1; j >= 0; j--) {
                    payouts[((scene.getBudget() - j) % onCardRoles.size()) - 1] = diceRolls[j];
                }

                for (int j = 0; j < onCardRoles.size(); j++) {
                    if (onCardRoles.get(j).hasPlayer()) {
                        Player player = onCardRoles.get(j).getPlayer();
                        Deadwood.bank.payMoney(player, payouts[j]);
                        System.out.println(player.getName() + " got $" + payouts[j]);
                    }
                }
                for (int j = 0; j < roles.size(); j++) {
                    if (roles.get(j).hasPlayer()) {
                        Player player = roles.get(j).getPlayer();
                        Deadwood.bank.payMoney(player, roles.get(j).getRequiredRank());
                        System.out.println(player.getName() + " got $" + roles.get(j).getRequiredRank());
                    }
                }
            }
            break;
        }
        for (int i = 0; i < onCardRoles.size(); i++) {
            if (onCardRoles.get(i).hasPlayer()) {
                onCardRoles.get(i).getPlayer().setRole(null);
                onCardRoles.get(i).setPlayer(null);
            }
        }
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).hasPlayer()) {
                roles.get(i).getPlayer().setRole(null);
                roles.get(i).setPlayer(null);
            }
        }
    }

    public void reset() {
        shotsLeft = shots;
        clearScene();
    }

    public Role getRole(Player player) {
        for (Role role: roles)
            if (role.getPlayer() == player)
                return role;
        for (Role role: scene.getRoles()) {
            if (role.hasPlayer() && role.getPlayer() == player)
                return role;
        }
        return null;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getShotsLeft() {
        return shotsLeft;
    }
}
