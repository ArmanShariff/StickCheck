import java.util.ArrayList;

public class Brian {
    private ArrayList<Player> playerRoster = new ArrayList<Player>();
    private ArrayList<Goalie> goalieRoster = new ArrayList<Goalie>();
    private boolean userRoster;

    public Brian(boolean userRoster) {
        this.userRoster = userRoster;
    }
    
	public void addPlayer(Player player) {
        playerRoster.add(player);
    }

    public ArrayList<Player> getPlayerRoster() {
        return playerRoster;
    }

    public void addGoalie(Goalie goalie) {
        goalieRoster.add(goalie);
    }

    public ArrayList<Goalie> getGoalieRoster() {
        return goalieRoster;
    }

    public String toString() {
        String printStatement = "Player Roster: \n";
        for(int i = 0; i < playerRoster.size(); i++) {
            printStatement += playerRoster.get(i) + ", ";
        }
        
        printStatement += "\nGoalie Roster: \n";
        for(int j = 0; j < goalieRoster.size(); j++) {
            printStatement += goalieRoster.get(j) + ", ";
        }

        return printStatement;
    }
}