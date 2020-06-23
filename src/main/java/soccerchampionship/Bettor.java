package soccerchampionship;

public class Bettor extends Person implements Bet {

    private String email;
    private String address;
    private int matchNo;
    private boolean IsBetResultAnnounced;
    private int betsWon;
    private int betsLose;
    private Team BettorTeam;
    private double balance;
    private double bidAmount;

    public Bettor(String email, String address, String name, int age) {
        super(name, age);
        this.email = email;
        this.address = address;
        matchNo = -1;
        betsWon = 0;
        betsLose = 0;
        IsBetResultAnnounced = true;
        BettorTeam = new Team();
        balance = 5000;
    }

    public int getMatchNo() {
        return matchNo;
    }

    public Team getBettorTeam() {
        return BettorTeam;
    }

    public Bettor(String name, int age) {
        super(name, age);
    }

    public boolean isIsBetResultAnnounced() {
        return IsBetResultAnnounced;
    }

    public void setIsBetResultAnnounced(boolean IsBetResultAnnounced) {
        this.IsBetResultAnnounced = IsBetResultAnnounced;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBet() {
        return matchNo;
    }

    public int getBetsWon() {
        return betsWon;
    }

    public void setBetsWon(int betsWon) {
        this.betsWon = betsWon;
    }

    public int getBetsLose() {
        return betsLose;
    }

    public void setBetsLose(int betsLose) {
        this.betsLose = betsLose;
    }

    public void placeBet(int MatchNo, Team t, int amount) {
        this.matchNo = MatchNo;
        this.BettorTeam = t;
        this.bidAmount = amount;
        this.balance -= amount;

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    @Override
    public String toString() {
        String Details = super.toString();
        String replace = Details.replace("Person", "Bettor");
        return replace + "\nEmail: " + email + "\nAddress: " + address + "\nMatchNo: " + matchNo
                + "\nIsBetResultAnnounced: " + IsBetResultAnnounced + "\nBets Won: " + betsWon + "\nBets Lost: "
                + betsLose + "\n\n\nBettorTeam: " + BettorTeam + "\n\n\nBalance: " + balance + "\n\n\n";
    }


}
