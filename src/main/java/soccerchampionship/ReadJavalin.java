package soccerchampionship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadJavalin {

    public void readTeamJavaLin(String fileName, ArrayList<Team> teams) throws FileNotFoundException, IOException {

        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        String row;
        ArrayList<String> teamData = new ArrayList<String>();
        row = csvReader.readLine();
        row = csvReader.readLine();
        StringTokenizer st = new StringTokenizer(row, ",");
        while (st.hasMoreTokens()) {
            teamData.add(st.nextToken());
        }
        Team t = new Team();

        t.setTeamName(teamData.get(0));
        t.setTeamAttack(Integer.parseInt(teamData.get(1)));
        t.setTeamOffence(Integer.parseInt(teamData.get(2)));
        t.setTeamExperience(Integer.parseInt(teamData.get(3)));
        t.setGamesWon(Integer.parseInt(teamData.get(4)));
        t.setGamesLose(Integer.parseInt(teamData.get(5)));
        t.setRandomLuck(t.generateRandomNumber(1, 6));
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] Playerdata = row.split(",");
            Player P = new Player(Playerdata[0], Integer.parseInt(Playerdata[1]), Integer.parseInt(Playerdata[2]), Integer.parseInt(Playerdata[3]));
            t.addPlayer(P);
        }

        teams.add(t);
    }

}
