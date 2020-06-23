package soccerchampionship;

import java.io.IOException;
import static java.lang.System.exit;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class WrongInputException extends Exception {

    WrongInputException(String s) {
        super(s);
    }
}

class InsufficientBettingBalance extends Exception {

    InsufficientBettingBalance(String s) {
        super(s);
    }
}

class PlayerFoundInLoginException extends Exception {

    PlayerFoundInLoginException(String s) {
        super(s);
    }
}

public class SoccerChampionshipRunner {

    static boolean loginSuccessful;


    public static void MainMenu(ChampionShip obj) throws WrongInputException, PlayerFoundInLoginException, IOException {
        System.out.println("\n\n1. Register as a Better.");
        System.out.println("2. Login.");
        System.out.println("3. Exit.\n\n");
        Scanner sc = new Scanner(System.in);
        int opt = -1;
        System.out.print("Enter option: ");
        opt = sc.nextInt();
        if (opt < 1 || opt > 3) {
            throw new WrongInputException("Not an appropriate input to the main menu");
        } else {
            if (opt == 1) {
                try {
                    obj.registerBettor();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            } else if (opt == 2) {
                String n;
                sc.nextLine();
                System.out.print("Enter Login Id (Name): ");
                n = sc.nextLine();
                try {
                    loginSuccessful = true;
                    obj.Login(n);

                    while (loginSuccessful) {
                        Person P = null;
                        try {
                            P = obj.getPerson(n);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        if (P instanceof Player) {
                            throw new PlayerFoundInLoginException("Player cannot Bet, Login unsuccessfull");
                        } else {
                            Bettor B = (Bettor) P;
                            BettorMenu(obj, B);
                        }
                    }

                } catch (BettorNotFoundException ex) {
                    System.out.println(ex);
                    loginSuccessful = false;
                }
            } else {
                exit(0);
            }
        }
    }

    public static void BettorMenu(ChampionShip obj, Bettor B) throws WrongInputException, IOException {

        System.out.println("\n\nWelcome Mr. " + B.getName());
        System.out.println("Bettor Details: ");
        System.out.println("Bettor Age: " + B.getAge() + "\nBetter Email: " + B.getEmail() + "\nBetter Address: " + B.getAddress() + "\nBets Won: "
                + B.getBetsWon() + "\nBets Lost: " + B.getBetsLose() + "\nBetter Team: " + B.getBettorTeam().getTeamName()
                + "Better Balance: " + B.getBalance() + " Better Bid Amount: " + B.getBidAmount());
        System.out.println("1. View Championship Schedule.");
        System.out.println("2. Place Bet");
        System.out.println("3. Generate Result to a file.");
        System.out.println("4. Back to main menu.\n\n");
        Scanner sc = new Scanner(System.in);
        int opt = -1;
        System.out.print("Enter option: ");
        opt = sc.nextInt();
        if (opt < 1 || opt > 4) {
            throw new WrongInputException("Not an appropriate input to the main menu");
        } else {
            if (opt == 1) {
                obj.GenerateMatches();
            } else if (opt == 2) {
                System.out.print("Enter Match No: ");
                int MatchNo = sc.nextInt();
                if (MatchNo < 1 || MatchNo > obj.getTeams().size()) {
                    throw new WrongInputException("Not an appropriate input to the Bet Selection");
                } else {
                    if (B.isIsBetResultAnnounced()) {
                        System.out.println("Select Team on which you want to place the Bet:");
                        System.out.println("1. " + obj.getTeams().get(MatchNo - 1).getTeamName());
                        System.out.println("2. " + obj.getTeams().get(MatchNo).getTeamName());
                        int team = sc.nextInt();
                        if (team < 1 || team > 2) {
                            throw new WrongInputException("Not an appropriate input to the Team Selection");
                        }
                        Team t = obj.getTeams().get(MatchNo + team - 2);
                        System.out.print("Enter Amount of Bet: ");
                        int amount = sc.nextInt();
                        if (amount > B.getBalance()) {
                            try {
                                throw new InsufficientBettingBalance("You have insufficient balance");
                            } catch (InsufficientBettingBalance ex) {
                                System.out.println(ex);
                            }

                        }
                        B.placeBet(MatchNo, t, amount);
                        System.out.println("Congratulations!! Bet successfully generated in the database");
                        System.out.println("Match No." + MatchNo + ": " + obj.getTeams().get(MatchNo - 1).getTeamName() + " vs " + obj.getTeams().get(MatchNo).getTeamName());
                        System.out.println("\n\nTeam Details: " + t);
                        B.setIsBetResultAnnounced(false);
                    } else {
                        System.out.println("You cannot Bet on any other match before the result of your previous bet announced");
                    }

                }
            } else if (opt == 3) {
                WriteJavalin wJ = new WriteJavalin();
                wJ.writeResultJavaLin(B, obj);
            } else if (opt == 4) {
                try {
                    MainMenu(obj);
                } catch (PlayerFoundInLoginException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ChampionShip championShipObj = new ChampionShip();
        championShipObj.readTeam("FCB.csv");
        championShipObj.readTeam("RMD.csv");
        championShipObj.readTeam("DTMN.csv");
        championShipObj.readTeam("BMNC.csv");
        while (true) {
            try {
                MainMenu(championShipObj);
            } catch (WrongInputException | PlayerFoundInLoginException ex) {
                System.out.println(ex);
            }
        }

    }

}
