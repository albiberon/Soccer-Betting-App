package soccerchampionship;

import java.util.ArrayList;
import java.util.Random;

public class Team implements generateRandom {

    private String teamName;
    private int teamAttack;
    private int teamOffence;
    private int randomLuck;
    private int teamExperience;
    private int gamesWon;
    private int gamesLose;
    private ArrayList<Player> players;

    Team() {
        teamName = "None";
        teamAttack = -1;
        teamOffence = -1;
        randomLuck = -1;
        teamExperience = -1;
        gamesWon = -1;
        gamesLose = -1;
        players = new ArrayList<Player>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamAttack() {
        return teamAttack;
    }

    public void setTeamAttack(int teamAttack) {
        this.teamAttack = teamAttack;
    }

    public int getTeamOffence() {
        return teamOffence;
    }

    public void setTeamOffence(int teamOffence) {
        this.teamOffence = teamOffence;
    }

    public int getRandomLuck() {
        return randomLuck;
    }

    public void setRandomLuck(int randomLuck) {
        this.randomLuck = randomLuck;
    }

    public int getTeamExperience() {
        return teamExperience;
    }

    public void setTeamExperience(int teamExperience) {
        this.teamExperience = teamExperience;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLose() {
        return gamesLose;
    }

    public void setGamesLose(int gamesLose) {
        this.gamesLose = gamesLose;
    }

    public void addPlayer(Player P) {
        this.players.add(P);
    }

    public Team(String teamName, int teamAttack, int teamOffence, int teamExperience, int gamesWon, int gamesLose) {
        this.teamName = teamName;
        this.teamAttack = teamAttack;
        this.teamOffence = teamOffence;
        this.teamExperience = teamExperience;
        this.gamesWon = gamesWon;
        this.gamesLose = gamesLose;
    }

    @Override
    public int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    @Override
    public String toString() {
        return "Team Name: " + teamName + "\nTeam Attack: " + teamAttack + "\nTeam Offence: " + teamOffence + "\nRandom Luck: " + randomLuck + "\nTeam Experience: " + teamExperience + "\nGames Won: " + gamesWon + "\nGames Lose: " + gamesLose + "\nPlayers:\n" + players + "\n";
    }

}
