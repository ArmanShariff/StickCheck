import java.io.FileNotFoundException;
import java.util.ArrayList;

public class startGame {
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

        Player[] torontoPlayers = new Player[10];
        Player[] newYorkPlayers = new Player[10];
        Player[] detroitPlayers = new Player[10];
        Player[] chicagoPlayers = new Player[10];
        Player[] montrealPlayers = new Player[10];
        Player[] bostonPlayers = new Player[10];

        ArrayList<Player> playerList = new ArrayList<Player>();
        ArrayList<Goalie> goalieList = new ArrayList<Goalie>();
        ArrayList<Coach> coachList = new ArrayList<Coach>();

        playerList = roster.getPlayers();
        goalieList = roster.getGoalies();
        coachList = roster.getCoaches();

        sortTeam("Toronto", toronto, playerList, goalieList, coachList, torontoPlayers);
        sortTeam("New York", newYork, playerList, goalieList, coachList, newYorkPlayers);
        sortTeam("Detroit", detroit, playerList, goalieList, coachList, detroitPlayers);
        sortTeam("Chicago", chicago, playerList, goalieList, coachList, chicagoPlayers);
        sortTeam("Montreal", montreal, playerList, goalieList, coachList, montrealPlayers);
        sortTeam("Boston", boston, playerList, goalieList, coachList, bostonPlayers);

        // System.out.println("Toronto: \n" + toronto);
        // System.out.println("\nNew York: \n" + newYork);
        // System.out.println("\nDetroit: \n" + detroit);
        // System.out.println("\nChicago: \n" + chicago);
        // System.out.println("\nMontreal: \n" + montreal);
        // System.out.println("\nBoston: \n" + boston);

        Simulation game1 = new Simulation(boston, toronto, false);

    }

    //Method that sorts the players, goalies and coaches into their respected teams 
    //and sets the position of the player/goalie as a starter/bench player
    public static void sortTeam(String teamName,Team team, ArrayList<Player> playerRoster, ArrayList<Goalie> goalieRoster, ArrayList<Coach> coachRoster, Player[] arrPlayers) {
        for(int i = 0; i < playerRoster.size(); i++){
            if(playerRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addPlayer(playerRoster.get(i));
                team.setPlayerPosition(playerRoster.get(i), arrPlayers);
            }
        }

        for(int i = 0; i < goalieRoster.size(); i++){
            if(goalieRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addGoalie(goalieRoster.get(i));
                team.setGoaliePosition(goalieRoster.get(i));
            }
        }

        for(int i = 0; i < coachRoster.size(); i++){
            if(coachRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addCoach(coachRoster.get(i));
                team.setCoach(coachRoster.get(i));
            }
        }
    }

}