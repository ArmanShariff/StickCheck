// make all players accessable by position in an array
// so we can make line changes possible

import java.util.ArrayList;

public class Team {
    
    private String teamName;
    private String abbreviation;

    // Arraylists of the rosters
    private ArrayList<Player> playerRoster = new ArrayList<Player>();
    private ArrayList<Goalie> goalieRoster = new ArrayList<Goalie>();
    private ArrayList<Coach> coachRoster = new ArrayList<Coach>(); 

    // stores the games this team plays 
    private ArrayList<Game> season = new ArrayList<Game>();
    private static Schedule seasonSchedule;

    Lines teamLines;
    OnIce teamOnIce;
    
    // initialize the goalie and coach
    private Goalie sG;
    private Goalie bG;
    private Coach coach;

    // Keep track of statistics
    int shotCount;
    int score;
    int wins;           // (TO-DO) wins and losses don't currently do anything
    int losses;

    public Team (String teamName, String abbreviation, boolean userTeam) {

        this.teamName = teamName;
        this.abbreviation = abbreviation;
    }

    public void lineChange(int time) {
        this.teamOnIce.lineChange(time);
    }
    
    public Lines getTeamLines(){
        return this.teamLines;
    }

    public void createLines() {
        
        Lines newLines = new Lines(playerRoster);
        this.teamLines = newLines;

    }
    public String getTeamName() {
        return teamName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Goalie getGoalie() {
        return sG;
    }
    
    public Goalie getBackupGoalie() {
        return bG;
    }

    public int getScore() {
        return score;
    }

    public int getShotCount() {
        return shotCount;
    }

    public Player getRoster(int i) {
		return playerRoster.get(i);
    }
    
    public Goalie getGoalieRoster(int i) {
		return goalieRoster.get(i);
	}

    // set methods
    
    public void setScore() {
        this.score = score + 1;
    }

    public void reSetScore() {
        this.score = 0;
    }

    public void setShotCount() {
        this.shotCount = shotCount + 1;
    }

    public void reSetShotCount() {
        this.shotCount = 0;
    }

    public void addPlayer(Player player) {
        playerRoster.add(player);
    }

    public void addGoalie(Goalie goalie) {
        goalieRoster.add(goalie);
    }

    //Adds coach to the team
    public void addCoach(Coach coach) {
        coachRoster.add(coach);
    }

    public ArrayList<Player> getPlayerRoster() {
        return playerRoster;
    }

    //Sets the goalie into the starting lineup/bench
    public void setGoaliePosition(Goalie goalie) {
        if (goalie.getPosition().equals("Goaltender")) {
            if (sG == null) {
                sG = goalie;
            } else {
                bG = goalie;
            }
        }
    }

    //Sets the coach onto the team
    public void setCoach(Coach coach) {
        if (coach.getPosition().equals("Coach")) {
            this.coach = coach;
        }
    }

    public void setOnIce(OnIce teamOnIce) {
        this.teamOnIce = teamOnIce;
    }

    public Player getCenter(){
        return teamOnIce.getCenter();
    }

    public Player getLeftWing() {
        return teamOnIce.getLeftWing();
    }

    public Player getRightWing() {
        return teamOnIce.getRightWing();
    }

    public Player getLeftDefence() {
        return teamOnIce.getLeftDefence();
    }

    public Player getRightDefence() {
        return teamOnIce.getRightDefence();
    }

    public Player getOnIce(int i) {
        return teamOnIce.getOnIce(i);
    }

    public static void setSeasonSchedule(Schedule newSeasonSchedule) {
        seasonSchedule = newSeasonSchedule;
    }

    public void makeSchedule() {
        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < 3; j++) {
                Game temp = seasonSchedule.getWeek(i).getGame(j);
                if (temp.getAwayTeamName().equals(this.teamName) || temp.getHomeTeamName().equals(this.teamName)) {
                    this.season.add(temp);
                }
            }
        }
    }

    public Game getTeamSchedule(int i) {

        return this.season.get(i);

    }

    public String toString() {

        return teamLines.toString();

     }

}