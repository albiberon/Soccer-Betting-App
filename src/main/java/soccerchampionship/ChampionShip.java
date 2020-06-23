package soccerchampionship;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class BettorNotFoundException extends Exception {

    BettorNotFoundException(String s) {
        super(s);
    }
}

class AlreadyRegisteredException extends Exception {

    AlreadyRegisteredException(String s) {
        super(s);
    }
}

public class ChampionShip {

    private ArrayList<Team> teams;
    private ArrayList<Bettor> bettors;

    public ChampionShip() {
        teams = new ArrayList<Team>();
        bettors = new ArrayList<Bettor>();

    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Bettor> getBettors() {
        return bettors;
    }

    public void setBettors(ArrayList<Bettor> bettors) {
        this.bettors = bettors;
    }

    public void readTeam(String fileName) throws FileNotFoundException, IOException {
        ReadJavalin rJ = new ReadJavalin();
        rJ.readTeamJavaLin(fileName, teams);
    }

    public void registerBettor() throws AlreadyRegisteredException {
        Scanner sc = new Scanner(System.in);
        String email, address, name;
        int age;
        System.out.print("Enter Bettor Name: ");
        name = sc.nextLine();
        for (Bettor b : bettors) {
            if (b.getName().equals(name)) {
                throw new AlreadyRegisteredException("Bettor with this name Already Registered");
            }
        }
        System.out.print("Enter Bettor Age: ");
        age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Bettor email: ");
        email = sc.nextLine();
        System.out.print("Enter Bettor Address: ");
        address = sc.nextLine();

        Bettor B = new Bettor(email, address, name, age);
        bettors.add(B);
        System.out.println("You are registered successfully");

    }

    public void Login(String Name) throws BettorNotFoundException {
        int count = 0;

        for (Bettor b : bettors) {
            if (b.getName().equals(Name)) {
                count = 1;
            }
        }
        if (count == 0) {
            throw new BettorNotFoundException("Bettor Not Found, Login Failed");
        } else {
            System.out.println("Login Successfull");
        }
    }

    public Person getPerson(String name) {
        Person P = null;
        for (Bettor bettor : bettors) {
            if (bettor.getName().equals(name)) {
                P = bettor;
            }
        }
        for (Team t : teams) {
            for (Player p : t.getPlayers()) {
                if (p.getName().equals(name)) {
                    P = p;
                }
            }
        }
        return P;
    }

    public void GenerateMatches() {
        int i;
        for (i = 0; i < teams.size() - 1; i++) {
            System.out.println("Match No. " + (i + 1) + ": " + teams.get(i).getTeamName() + " vs " + teams.get(i + 1).getTeamName());
        }

        System.out.println("Match No. " + (i + 1) + ": " + teams.get(i).getTeamName() + " vs " + teams.get(0).getTeamName());
    }

    public Team GenerateMatchResult(Bettor B) {
        int MatchNo = B.getMatchNo();
        Team t1 = teams.get(MatchNo);
        Team t2 = teams.get(MatchNo + 1);

        int sumAttackT1 = t1.getPlayers().stream().filter(o -> o.getAttack() > 10).mapToInt(Player::getAttack).sum();
        int sumAttackT2 = t2.getPlayers().stream().filter(o -> o.getAttack() > 10).mapToInt(Player::getAttack).sum();
        int sumDefenseT1 = t1.getPlayers().stream().filter(o -> o.getDefense() > 10).mapToInt(Player::getDefense).sum();
        int sumDefenseT2 = t2.getPlayers().stream().filter(o -> o.getDefense() > 10).mapToInt(Player::getDefense).sum();

        int randomLuckT1 = t1.generateRandomNumber(1, 6);
        int randomLuckT2 = t2.generateRandomNumber(1, 6);

        sumAttackT1 += t1.getTeamAttack();
        sumAttackT2 += t2.getTeamAttack();
        sumDefenseT1 += t1.getTeamOffence();
        sumDefenseT2 += t2.getTeamOffence();

        int T1Total = sumAttackT1 + sumDefenseT1;

        int T2Total = sumAttackT2 + sumDefenseT2;

        System.out.println("\nMatch Stats: ");
        System.out.println("Team 1's sum of all Players Attack and Team Attack: " + sumAttackT1);
        System.out.println("Team 1's sum of all Players Defense and Team Offence: " + sumDefenseT1);
        System.out.println("Team 1's Total: " + T1Total);
        if (randomLuckT1 == t1.getRandomLuck()) {
            T1Total += t1.generateRandomNumber(40, 80);
        }
        System.out.println("Team 1's Total after Random Luck match: " + T1Total);

        System.out.println("Team 2's sum of all Players Attack and Team Attack: " + sumAttackT2);
        System.out.println("Team 2's sum of all Players Defense and Team Offence: " + sumDefenseT2);
        System.out.println("Team 2's Total: " + T2Total);
        if (randomLuckT2 == t2.getRandomLuck()) {
            T2Total += t1.generateRandomNumber(40, 80);
        }
        System.out.println("Team 2's Total after Random Luck match: " + T2Total);
        B.setIsBetResultAnnounced(true);
        if (T1Total > T2Total) {
            return t1;
        }
        return t2;
    }

    @Override
    public String toString() {
        return "Registered Teams in ChampionShip:\n " + teams + "\n";
    }

}
