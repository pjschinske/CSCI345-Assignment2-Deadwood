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

//    public void act(Player player) {
//        int roleIndex = -1;
//        for (int i = 0; i < roles.length; i++) {
//            if (roles[i].getPlayer() == player) {
//                roleIndex = i;
//                break;
//            }
//        }
//
//        int diceRoll = random.nextInt(6) + 1;
//        if (diceRoll + player.getPracticeChips()  >= budget) {
//            System.out.print("Success! You completed a scene and got ");
//            shotsLeft--;
//            if (roles[roleIndex].isOnCard()) {
//                player.addCredits(2);
//                System.out.println("two credits.");
//            } else {
//                player.addCredits(1);
//                Deadwood.bank.payMoney(player, 1);
//                System.out.println("a credit and $1.");
//            }
//        } else {
//            System.out.print("Oh no! You messed up the scene. ");
//            if (roles[roleIndex].isOnCard()) {
//                System.out.println("You got nothing.");
//            } else {
//                Deadwood.bank.payMoney(player, 1);
//                System.out.println("You still got $1 for trying.");
//            }
//        }
//        if (shotsLeft == 0)
//            wrap();
//    }
//
//    private void wrap() {
//        System.out.println("That's a wrap!");
//        for (int i = 0; i < roles.length; i++) {
//            if (roles[i].isOnCard()) {
//                int[] diceRolls = new int[budget];
//                //roll the dice
//                for (int j = 0; j < budget; j++) {
//                    diceRolls[j] = random.nextInt(6) + 1;
//                }
//                //sort the dice
//                Arrays.sort(diceRolls);
//                //set up the payouts and distribute them among the players
//                int[] payouts = new int[roles.length];
//                for (int j = budget - 1; j >= 0; j--) {
//                    payouts[((budget - j) % roles.length) - 1] = diceRolls[j];
//                }
//                for (int j = 0; j < roles.length; j++) {
//                    if (roles[j].hasPlayer()) {
//                        Player player = roles[j].getPlayer();
//                        if (roles[j].isOnCard()) {
//                            Deadwood.bank.payMoney(player, payouts[j]);
//                            System.out.println(player.getName() + " got $"
//                                    + payouts[j]);
//                        } else {
//                            Deadwood.bank.payMoney(player, roles[j].getRequiredRank());
//                            System.out.println(player.getName() + " got $"
//                                    + roles[j].getRequiredRank());
//                        }
//                    }
//                }
//            }
//            return;
//        }
//    }

    private int getRole(String roleName) {
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getName().equals(roleName))
                return i;
        }
        return -1;
    }
}
