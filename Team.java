import java.util.ArrayList;

public class Team {

    //Initialize the 13 positions 
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
    private Coach coach;

    //Boolean to declare which team the user chose
    private boolean userTeam;

    //Arraylists of the rosters
    private ArrayList<Player> playerRoster = new ArrayList<Player>();
    private ArrayList<Goalie> goalieRoster = new ArrayList<Goalie>();
    private ArrayList<Coach> coachRoster = new ArrayList<Coach>();

    //Array of players on the team
    // >Easier to loop through for future
    private Player[] arrPlayers = new Player[10]; 

    public Team(boolean userTeam) {
        this.userTeam = userTeam;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // get methods

    public Player getsC() {
        return sC;
    }

    public Player getsLW() {
        return sLW;
    }

    public Player getsRW() {
        return sRW;
    }

    public Player getsLD() {
        return sLD;
    }
    public Player getsRD() {
        return sRD;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // class methods

    public void addPlayer(Player player) {
        playerRoster.add(player);
    }

    // public ArrayList<Player> getPlayerRoster() {
    // return playerRoster;
    // }

    //Adds goalie to the team
    public void addGoalie(Goalie goalie) {
        goalieRoster.add(goalie);
    }

    // public ArrayList<Goalie> getGoalieRoster() {
    // return goalieRoster;
    // }

    //Adds coach to the team
    public void addCoach(Coach coach) {
        coachRoster.add(coach);
    }

    //Method checks whether the player is a center, wing or defense
    //Sets the corresponding players into the starting lineup/bench
    //TO DO: If bench player has a higher overall than the starter, switch them
    public void setPlayerPosition(Player player, Player[] arrPlayers) {
        if (player.getPosition().equals("Center")) {
            if (sC == null) {
                sC = player;
                arrPlayers[0] = sC;
            } else {
                bC = player;
                arrPlayers[5] = bC;
            }
        }
        else if (player.getPosition().equals("Left Wing")) {
            if (sLW == null) {
                sLW = player;
                arrPlayers[1] = sLW;
            } else {
                bLW = player;
                arrPlayers[6] = bLW;
            }
        }
        else if (player.getPosition().equals("Right Wing")) {
            if (sRW == null) {
                sRW = player;
                arrPlayers[2] = sRW;
            } else {
                bRW = player;
                arrPlayers[7] = bRW;
            }
        }
        else if (player.getPosition().equals("Right Defence")) {
            if (sRD == null) {
                sRD = player;
                arrPlayers[3] = sRD;
            } else {
                bRD = player;
                arrPlayers[8] = bRD;
            }
        }
        else if (player.getPosition().equals("Left Defence")) {
            if (sLD == null) {
                sLD = player;
                arrPlayers[4] = sLD;
            } else {
                bLD = player;
                arrPlayers[9] = bLD;
            }
        }
    }

    //Sets the goalie into the starting lineup/bench
    public void setGoaliePosition(Goalie goalie) {
        if(goalie.getPosition().equals("Goaltender")) {
            if (sG == null) {
                sG = goalie;
            } else {
                bG = goalie;
            }
        }
    }

    //Sets the coach onto the team
    public void setCoach(Coach coach) {
        if(coach.getPosition().equals("Coach")) {
            this.coach = coach;
        }
    }

    public String toString() {
        String printStatement = "Player Roster: \n";
        printStatement += "Starting Center: " + sC + "\n";
        printStatement += "Starting Left Wing: " + sLW + "\n";
        printStatement += "Starting Right Wing: " + sRW + "\n";
        printStatement += "Starting Left Defense: " + sLD + "\n";
        printStatement += "Starting Right Defense: " + sRD + "\n";
        printStatement += "Bench Center: " + bC + "\n";
        printStatement += "Bench Left Wing: " + bLW + "\n";
        printStatement += "Bench Right Wing: " + bRW + "\n";
        printStatement += "Bench Left Defense: " + bLD + "\n";
        printStatement += "Bench Right Defense: " + bRD + "\n";
        printStatement += "\nGoalie Roster: \n";
        printStatement += "Starting Goalie: " + sG + "\n";
        printStatement += "Bench Goalie: " + bG + "\n";
        printStatement += "\nCoach Roster: \n";
        printStatement += "Coach: " + coach;

        return printStatement;
    }

}