// make all players accessable by position in an array
// so we can make line changes possible

import java.util.ArrayList;

public class Team {
    
    private boolean userTeam;                           // Boolean to declare which team the user chose
    private String teamName;

    // Arraylists of the rosters
    private ArrayList<Player> playerRoster = new ArrayList<Player>();
    private ArrayList<Goalie> goalieRoster = new ArrayList<Goalie>();
    private ArrayList<Coach> coachRoster = new ArrayList<Coach>();
    
    // stores all the players currently on the ice
    Player[] onIce = new Player[5];

    /*
    *  fowardLines[i][j] - i controls the line, j controls position
    *  LeftWing([0][0]) - Center([0][1]) - RightWing([0][2])
    *  LeftWing([1][0]) - Center([1][1]) - RightWing([1][2])
    */ 
    
    Player[][] forwardLines = new Player[2][3];
    Player[][] defenceLines = new Player[2][2];
    
    // initialize the goalie and coach
    private Goalie sG;
    private Goalie bG;
    private Coach coach;
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Constructor

    public Team (String teamName, boolean userTeam) {

        this.teamName = teamName;
        this.userTeam = userTeam;

    }


    // Keep track of shot count and score in a game
    int shotCount;
    int score;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // get methods
    public String getTeamName() {
        return teamName;
    }

    public Goalie getsG() {
        return sG;
    }

    public Player getOnIce(int i) {
        return this.onIce[i];
    }

    public int getScore() {
        return score;
    }

    public int getShotCount()
    {
        return shotCount;
    }

    public Player getsC() {
        return getOnIce(1);
    }

    public Player getsLW() {
        return getOnIce(0);
    }

    public Player getsRW() {
        return getOnIce(2);
    }

    public Player getsLD() {
        return getOnIce(3);
    }

    public Player getsRD() {
        return getOnIce(4);
    }

    // set methods
    
    public void setScore() {
        this.score = score + 1;
    }

    public void reSetScore() {
        this.score = 0;
    }

    public void setShotCount()
    {
        this.shotCount = shotCount + 1;
    }

    public void reSetShotCount()
    {
        this.shotCount = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // class methods

    public void addPlayer(Player player) {
        playerRoster.add(player);
    }

    //Adds goalie to the team
    public void addGoalie(Goalie goalie) {
        goalieRoster.add(goalie);
    }

    //Adds coach to the team
    public void addCoach(Coach coach) {
        coachRoster.add(coach);
    }

    //Method checks whether the player is a center, wing or defense
    //Sets the corresponding players into the starting lineup/bench
    //TO DO: If bench player has a higher overall than the starter, switch them
    public void setPlayerPosition(Player player) {
        
        if (player.getPosition().equals("Left Wing")) {
            
            if (forwardLines[0][0] == null) {
                forwardLines[0][0] = player;
                this.onIce[0] = player;
            } else {
                forwardLines[1][0] = player;
            }
        }

        else if (player.getPosition().equals("Center")) {
            if (forwardLines[0][1] == null) {
                forwardLines[0][1] = player;
                this.onIce[1] = player;
            } else {
                forwardLines[1][1] = player;
            }
        }

        else if (player.getPosition().equals("Right Wing")) {
            if (forwardLines[0][2] == null) {
                forwardLines[0][2] = player;
                this.onIce[2] = player;
            } else {
                forwardLines[1][2] = player;
            }
        }

        else if (player.getPosition().equals("Left Defence")) {
            if (defenceLines[0][0] == null) {
                defenceLines[0][0] = player;
                this.onIce[3] = player;
            } else {
                defenceLines[1][0] = player;
            }
        }

        else if (player.getPosition().equals("Right Defence")) {
            if (defenceLines[0][1] == null) {
                defenceLines[0][1] = player;
                this.onIce[4] = player;
            } else {
                defenceLines[1][1] = player;
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

        String printStatement = "Lines: \n";

        for (int i = 0; i < forwardLines.length; i ++) {
            printStatement += "Line " + (i+1);
            for (int j = 0; j < forwardLines[i].length; j++) {
                printStatement += " " + forwardLines[i][j].getLastName();
            }
            printStatement += "\n";
        }

        for (int i = 0; i < defenceLines.length; i ++) {
            printStatement += "Line " + (i+1);
            for (int j = 0; j < defenceLines[i].length; j++) {
                printStatement += " " + defenceLines[i][j].getLastName();
            }
            printStatement += "\n";
        }
        return printStatement;
    }

	public Player getRoster(int i) {
		return playerRoster.get(i);
	}

}