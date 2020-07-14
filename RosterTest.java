import java.io.FileNotFoundException;
import java.util.*;

class RosterTest {

    public static void main(String args[]) throws FileNotFoundException {

        ArrayList<Player> playersMain = new ArrayList<Player>();
        ArrayList<Goalie> goaliesMain = new ArrayList<Goalie>();
        ArrayList<Coach> coachesMain = new ArrayList<Coach>();

        Roster roster = new Roster("roster.csv");

        playersMain = roster.getPlayers();

        for (int i = 0; i < playersMain.size(); i++) {   
            System.out.print(playersMain.get(i));
        }

    }

}