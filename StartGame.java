import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class StartGame {
    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        // Initiailize roster through csv file
        // Initialize the 6 rosters
        // Initialize the list of players, goalies and coaches

        Roster roster = new Roster("roster.csv");

        // create team objects
        Team toronto = new Team("Toronto", "TOR", false);
        Team newYork = new Team("New York", "NYR", false);
        Team detroit = new Team("Detroit", "DET", false);
        Team chicago = new Team("Chicago", "CHI", false);
        Team montreal = new Team("Montreal", "MON", false);
        Team boston = new Team("Boston", "BOS", false);

        // list to store team objects
        ArrayList<Team> teamList = new ArrayList<Team>();

        // add teams to team list
        teamList.add(toronto);
        teamList.add(newYork);
        teamList.add(detroit);
        teamList.add(chicago);
        teamList.add(montreal);
        teamList.add(boston);

        Schedule schedule = new Schedule("schedule.csv", teamList);
        Team.setSeasonSchedule(schedule);

        for (int i = 0; i < teamList.size(); i++) {
            teamList.get(i).makeSchedule();
        }

        ArrayList<Player> playerList = new ArrayList<Player>();
        ArrayList<Goalie> goalieList = new ArrayList<Goalie>();
        ArrayList<Coach> coachList = new ArrayList<Coach>();

        playerList = roster.getPlayers();
        goalieList = roster.getGoalies();
        coachList = roster.getCoaches();

        // adds players to their teams
        sortTeam("Toronto", toronto, playerList, goalieList, coachList);
        sortTeam("New York", newYork, playerList, goalieList, coachList);
        sortTeam("Detroit", detroit, playerList, goalieList, coachList);
        sortTeam("Chicago", chicago, playerList, goalieList, coachList);
        sortTeam("Montreal", montreal, playerList, goalieList, coachList);
        sortTeam("Boston", boston, playerList, goalieList, coachList);

        System.out.println(teamList.get(1).getTeamName());
        System.out.println(teamList.get(2).getTeamName());
        System.out.println(teamList.get(3).getTeamName());

        // organizes players within their teams
        for (int i = 0; i < teamList.size(); i++) {
            teamList.get(i).createLines();
        }

        // swaps tavares and matthews
        toronto.getTeamLines().editForwardLines(0, 1, 1, 1);

        // for (int i = 0; i < 82; i++) {
        GUI gui = new GUI(teamList, toronto, montreal);
        // }

        System.out.println("Toronto: \n" + toronto);
        System.out.println("\nMontreal: \n" + montreal);

        for (int i = 0; i < 10; i++) {
            System.out.println(toronto.getTeamSchedule(i).getHomeTeamName() + " VS "
                    + toronto.getTeamSchedule(i).getAwayTeamName());
        }

    }

    // Method that sorts the players, goalies and coaches into their respected teams
    // and sets the position of the player/goalie as a starter/bench player
    public static void sortTeam(String teamName, Team team, ArrayList<Player> playerRoster, ArrayList<Goalie> goalieRoster, ArrayList<Coach> coachRoster) {
        // loop through an array list of all players
        for (int i = 0; i < playerRoster.size(); i++) {
            // if the player belongs to the team, add them to that team in the correct
            // position.
            if (playerRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addPlayer(playerRoster.get(i));
            }
        }

        // loop through an array list of all goalies
        for (int i = 0; i < goalieRoster.size(); i++) {
            // if the goalie belongs to the team, add them to that team in the correct
            // position.
            if (goalieRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addGoalie(goalieRoster.get(i));
                team.setGoaliePosition(goalieRoster.get(i));
            }
        }

        // loop through an array list of all coaches, and add them to their team
        for (int i = 0; i < coachRoster.size(); i++) {
            if (coachRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addCoach(coachRoster.get(i));
                team.setCoach(coachRoster.get(i));
            }
        }

    }

}