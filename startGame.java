import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StartGame {
    public static void main(String[] args) throws FileNotFoundException {
        //Initiailize roster through csv file
        //Initialize the 6 rosters
        //Initialize the list of players, goalies and coaches
        
        Roster roster = new Roster("roster.csv");

        Team toronto = new Team("Toronto", true);
        Team newYork = new Team("New York", false);
        Team detroit = new Team("Detroit", false);
        Team chicago = new Team("Chicago", false);
        Team montreal = new Team("Montreal", false);
        Team boston = new Team("Boston", false);

        ArrayList<Player> playerList = new ArrayList<Player>();
        ArrayList<Goalie> goalieList = new ArrayList<Goalie>();
        ArrayList<Coach> coachList = new ArrayList<Coach>();

        playerList = roster.getPlayers();
        goalieList = roster.getGoalies();
        coachList = roster.getCoaches();

        sortTeam("Toronto", toronto, playerList, goalieList, coachList);
        sortTeam("New York", newYork, playerList, goalieList, coachList);
        sortTeam("Detroit", detroit, playerList, goalieList, coachList);
        sortTeam("Chicago", chicago, playerList, goalieList, coachList);
        sortTeam("Montreal", montreal, playerList, goalieList, coachList);
        sortTeam("Boston", boston, playerList, goalieList, coachList);

        System.out.println("Toronto: \n" + toronto);
        System.out.println("\nNew York: \n" + newYork);
        System.out.println("\nDetroit: \n" + detroit);
        System.out.println("\nChicago: \n" + chicago);
        System.out.println("\nMontreal: \n" + montreal);
        System.out.println("\nBoston: \n" + boston);
        
        Simulation game1 = new Simulation(toronto, montreal, false);
    }

    //Method that sorts the players, goalies and coaches into their respected teams 
    //and sets the position of the player/goalie as a starter/bench player
    public static void sortTeam(String teamName,Team team, ArrayList<Player> playerRoster, ArrayList<Goalie> goalieRoster, ArrayList<Coach> coachRoster) {
        
        // loop through an array list of all players
        for (int i = 0; i < playerRoster.size(); i++) {
            // if the player belongs to the team, add them to that team in the correct position.
            if (playerRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addPlayer(playerRoster.get(i));
                team.setPlayerPosition(playerRoster.get(i));
            }
        }

        // loop through an array list of all goalies
        for (int i = 0; i < goalieRoster.size(); i++){
            // if the goalie belongs to the team, add them to that team in the correct position.
            if (goalieRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addGoalie(goalieRoster.get(i));
                team.setGoaliePosition(goalieRoster.get(i));
            }
        }

        // loop through an array list of all coaches, and add them to their team
        for (int i = 0; i < coachRoster.size(); i++){
            if (coachRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addCoach(coachRoster.get(i));
                team.setCoach(coachRoster.get(i));
            }
        }
    }

}