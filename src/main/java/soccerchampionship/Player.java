package soccerchampionship;

public class Player extends Person {

    private int attack;
    private int defense;

    public Player(String name, int age) {
        super(name, age);
    }

    public Player(String name, int age, int attack, int defense) {
        super(name, age);
        this.attack = attack;
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        String Details = super.toString();
        String replace = Details.replace("Person", "Player");
        return replace + " Attack: " + attack + " Defense:" + defense + "\n";
    }

}
