public class Team {
    private Player sRW;
    private Player sC;
    private Player sLW;
    private Player sLD;
    private Player sRD;
    private Player bRW;
    private Player bC;
    private Player bLW;
    private Player bLD;
    private Player bRD;
    private Goalie sG;
    private Goalie bG;

    private boolean userTeam;

    public Team(boolean userTeamr) {
        this.userTeam = userTeam;
    }

    // public void addPlayer(Player player) {
    //     playerRoster.add(player);
    // }

    // public ArrayList<Player> getPlayerRoster() {
    //     return playerRoster;
    // }

    // public void addGoalie(Goalie goalie) {
    //     goalieRoster.add(goalie);
    // }

    // public ArrayList<Goalie> getGoalieRoster() {
    //     return goalieRoster;
    // }

    // public String toString() {
    //     String printStatement = "Player Roster: \n";
    //     for (int i = 0; i < playerRoster.size(); i++) {
    //         printStatement += playerRoster.get(i) + ", ";
    //     }

    //     printStatement += "\nGoalie Roster: \n";
    //     for (int j = 0; j < goalieRoster.size(); j++) {
    //         printStatement += goalieRoster.get(j) + ", ";
    //     }

    //     return printStatement;
    // }

}