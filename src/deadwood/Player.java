package deadwood;

import deadwood.board.Place;
import deadwood.board.Role;

import java.util.Random;

/**
 * Stores info about a player.
 */
public class Player {

    private String name;
    private int rank;
    private int credits;
    private int money;
    private int practiceChips;
    private Place currentLocation;
    private Role currentRole;

    private static Random rand = new Random();

    public Player() {
    }

    public Player(String name, int rank) {
        this.name = name;
        this.rank = rank;
        this.credits = 0;
        this.money = 0;
    }

    public String getName() {
        return name;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        if (rank > this.rank)
            this.rank = rank;
    }

    public int getCredits() {
        return credits;
    }

    public void addCredits(int credits) {
        this.credits += credits;
    }

    public int getPracticeChips() {
        return practiceChips;
    }

    public void clearPracticeChips() {
        practiceChips = 0;
    }

    public int payBank(Bank bank, int payment) {
        if (money < payment) {
            System.out.println("Not enough money in " + name + "'s bank account.");
            return -1;
        } else {
            money -= payment;
            bank.receiveMoney(payment);
            return payment;
        }
    }

    public void receiveMoney(int payment) {
        this.money += payment;
    }

    public int getMoney() {
        return money;
    }

    public boolean rehearse(int budget) {
        if (practiceChips < budget) {
            practiceChips++;
            System.out.printf("You now have %d practice chips.", practiceChips);
            return true;
        } else {
            System.out.println("You have the maximum amount of practice chips.");
            return false;
        }
    }

//    public boolean act(int budget) {
//        int diceRoll = rand.nextInt(6) + 1;
//        if (diceRoll + practiceChips >= budget) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void setCurrentLocation(Place currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Place getCurrentLocation() {
        return currentLocation;
    }

    public void setRole(Role role) {
        currentRole = role;
    }

    public Role getRole() {
        return currentRole;
    }
}
