import java.io.FileNotFoundException;
import java.util.ArrayList;

public class startGame {
    public static void main(String[] args) throws FileNotFoundException{
        Roster roster = new Roster("roster.csv");

        ArrayList<Player> playerList = new ArrayList<Player>();
        ArrayList<Goalie> goalieList = new ArrayList<Goalie>();
        ArrayList<Coach> coachList = new ArrayList<Coach>();
        
        playerList = roster.getPlayers();
        goalieList = roster.getGoalies();
        coachList = roster.getCoaches();

        
    }
}