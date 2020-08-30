import java.util.*;

public class Game {
    
    // ArrayList that stores the teams playing in the game
    ArrayList<Team> playingTeams = new ArrayList<Team>();
    ArrayList<Team> teamList = new ArrayList<Team>();

    public Game(String homeTeamName, String awayTeamName, ArrayList<Team> teamList) {
        this.teamList = teamList;
        playingTeams.add(findTeam(homeTeamName));
        playingTeams.add(findTeam(awayTeamName));

    }

    public Team getHomeTeam() {
        return playingTeams.get(0);
    }

    public Team getAwayTeam() {
        return playingTeams.get(1);
    }

    public Team findTeam(String teamName) {
        for (int i = 0; i < teamList.size(); i++) {
            if (teamList.get(i).getTeamName().equals(teamName)) {
                return teamList.get(i);
            }
        }
        return null;
    }
}