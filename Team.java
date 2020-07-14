import java.util.ArrayList;

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
    private Coach coach;

    private boolean userTeam;

    private ArrayList<Player> playerRoster = new ArrayList<Player>();
    private ArrayList<Goalie> goalieRoster = new ArrayList<Goalie>();
    private ArrayList<Coach> coachRoster = new ArrayList<Coach>();

    public Team(boolean userTeam) {
        this.userTeam = userTeam;
    }

    public void addPlayer(Player player) {
        playerRoster.add(player);
    }

    // public ArrayList<Player> getPlayerRoster() {
    // return playerRoster;
    // }

    public void addGoalie(Goalie goalie) {
        goalieRoster.add(goalie);
    }

    // public ArrayList<Goalie> getGoalieRoster() {
    // return goalieRoster;
    // }

    public void addCoach(Coach coach) {
        coachRoster.add(coach);
    }

    public void setPlayerPosition(Player player) {
        if (player.getPosition().equals("Center")) {
            if (sC == null) {
                sC = player;
            } else {
                bC = player;
            }
        }
        else if (player.getPosition().equals("Left Wing")) {
            if (sLW == null) {
                sLW = player;
            } else {
                bLW = player;
            }
        }
        else if (player.getPosition().equals("Right Wing")) {
            if (sRW == null) {
                sRW = player;
            } else {
                bRW = player;
            }
        }
        else if (player.getPosition().equals("Right Defence")) {
            if (sRD == null) {
                sRD = player;
            } else {
                bRD = player;
            }
        }
        else if (player.getPosition().equals("Left Defence")) {
            if (sLD == null) {
                sLD = player;
            } else {
                bLD = player;
            }
        }
    }

    public void setGoaliePosition(Goalie goalie) {
        if(goalie.getPosition().equals("Goaltender")) {
            if (sG == null) {
                sG = goalie;
            } else {
                bG = goalie;
            }
        }
    }

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


        // String printStatement = "Player Roster: \n";
        // for (int i = 0; i < playerRoster.size(); i++) {
        //     printStatement += playerRoster.get(i) + "\n";
        // }

        // printStatement += "\nGoalie Roster: \n";
        // for (int i = 0; i < goalieRoster.size(); i++) {
        //     printStatement += goalieRoster.get(i) + "\n";
        // }

        // printStatement += "\nCoach Roster: \n";
        // for (int i = 0; i < coachRoster.size(); i++) {
        //     printStatement += coachRoster.get(i) + "\n";
        // }

        return printStatement;
    }

}