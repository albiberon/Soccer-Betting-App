package soccerchampionship;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class WriteJavalin {

    public void writeResultJavaLin(Bettor B, ChampionShip obj) throws FileNotFoundException, IOException {
        boolean win = false;
        if (obj.GenerateMatchResult(B).equals(B.getBettorTeam())) {
            win = true;
            B.setBalance(B.getBalance() + (B.getBidAmount() * 1.2));
            B.setBetsWon(B.getBetsWon() + 1);
            B.setBidAmount(0);
        } else {
            B.setBetsLose(B.getBetsLose() + 1);
            B.setBalance(B.getBalance() - (B.getBidAmount() * 0.8));
            B.setBidAmount(0);
        }
        System.out.println("Result Generated in the text file! Thank you");
        try {
            String ResultfileName = B.getName() + "_BetResult.txt";
            try (FileWriter myWriter = new FileWriter(ResultfileName)) {
                myWriter.write(B.toString());
                if (win) {
                    myWriter.write("Congratulations! You have won the Bet!!");
                } else {
                    myWriter.write("Unfortunately! You have Lost the Bet!!");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
