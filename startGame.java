import java.io.FileNotFoundException;
import java.util.ArrayList;

public class startGame {
    public static void main(String[] args) throws FileNotFoundException {

        
        Roster roster = new Roster("roster.csv");
        Team toronto = new Team(true);
        Team detroit = new Team(false);
        Team chicago = new Team(false);
        Team montreal = new Team(false);
        Team newYork = new Team(false);
        Team boston = new Team(false);

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
    }

    public static void sortTeam(String teamName,Team team, ArrayList<Player> playerRoster, ArrayList<Goalie> goalieRoster, ArrayList<Coach> coachRoster) {
        for(int i = 0; i < playerRoster.size(); i++){
            if(playerRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addPlayer(playerRoster.get(i));
                team.setPlayerPosition(playerRoster.get(i));
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