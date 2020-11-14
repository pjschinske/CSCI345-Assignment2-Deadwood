package deadwood;

public class Bank {
    private int bankMoney;

    public Bank(int bankMoney) {
        this.bankMoney = bankMoney;
    }

    public void payMoney(Player player, int payment) {
        bankMoney -= payment;
        player.receiveMoney(payment);
    }

    public void receiveMoney(int payment) {
        bankMoney += payment;
    }
}
