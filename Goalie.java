

public class Goalie extends Person {

    //fields

    double reflexes;
    double agility;
    double flexibility;
    double reboundControl;
    double puckControl;
    double stamina;
    double staminaBar;

    double originalReflexes;
    double originalAgility;
    double originalFlexibility;
    double originalReboundControl;
    double originalPuckControl;

    int totalShotsFaced;
    int totalShotsSaved;

    // TODO: GAA & track games played
    int totalGoalsAgainst;
    int gamesPlayed;

    //constructor

    public Goalie (double stamina, double reflexes, double agility, double flexibility, double reboundControl, double puckControl, String firstName, String lastName, String position, String country, int age, int playerNumber, String currentTeam, String playerType) {

        super(firstName, lastName, position, country, age, playerNumber, currentTeam, playerType);
        
        this.stamina = stamina;
        this.reflexes = reflexes;
        originalReflexes = reflexes;
        this.agility = agility;
        originalAgility = agility;
        this.flexibility = flexibility;
        originalFlexibility = flexibility;
        this.reboundControl = reboundControl;
        originalReboundControl = reboundControl;
        this.puckControl = puckControl;
        originalPuckControl = puckControl;
        staminaBar = 1;

    }

    // get methods
    public String getSavePercentage() {
        Double doubleTotalShotsFaced = Double.valueOf(this.totalShotsFaced);
        Double doubleTotalShotsSaved = Double.valueOf(this.totalShotsSaved);
        Double doubleUnrounded = doubleTotalShotsSaved/doubleTotalShotsFaced;
        Double doubleRounded = (double)Math.round(doubleUnrounded * 1000d)/1000d;
        String rounded = (String.valueOf(doubleRounded) + "%");
        
        if (doubleTotalShotsFaced > 0) {
            return rounded;
        } else {
            return "N/A";
        }
    }

    public int getSaves() {
        return totalShotsSaved;
    }

    public int getShotsAgainst() {
        return totalShotsFaced;
    }

    public double getStamina() {
        return this.stamina;
    }

    public double getStaminaBar() {
        return this.staminaBar;
    }

    public double getReflexes() {
        return this.reflexes;
    }

    public double getAgility() {
        return this.agility;
    }

    public double getFlexibility() {
        return flexibility;
    }

    public double getReboundControl() {
        return reboundControl;
    }
    
    public double getPuckControl() {
        return puckControl;
    }

    // set methods

    public void setReflexes(double reflexes) {
        this.reflexes = reflexes;
    }
    
    public void setAgility(double agility) {
        this.agility = agility;
    }

    public void setFlexibility(double flexibility) {
        this.flexibility = flexibility;
    }

    public void setReboundControl(double reboundControl) {
        this.reboundControl = reboundControl;
    }

    public void setPuckControl(double puckControl) {
        this.puckControl = puckControl;
    }

    public void setStaminaBar(double staminaBar) {
        if (staminaBar < 0.25) {
            staminaBar = 0.25;
        } else {
            this.staminaBar = staminaBar;
        }
    }

    // stat change methods

    public void dropGoalieStamina(int drops) {
        setStaminaBar(staminaBar - (staminaBar + drops*drops)/stamina);
    }

    public void dropGoalieStats(double staminaBar) {
        setReflexes(originalReflexes * staminaBar);
        setAgility(originalAgility * staminaBar);
        setFlexibility(originalFlexibility * staminaBar);
        setReboundControl(originalReboundControl * staminaBar);
        setPuckControl(originalPuckControl * staminaBar);
    }

    public void resetGoalieStats() {
        setReflexes(originalReflexes);
        setAgility(originalAgility);
        setFlexibility(originalFlexibility);
        setReboundControl(originalReboundControl);
        setPuckControl(originalPuckControl);
    }

    // update shots against and goals against

    public void addShot() {
        this.totalShotsFaced = this.totalShotsFaced + 1;
    }
    
    public void addShotSaved() {
        this.totalShotsSaved = this.totalShotsSaved + 1;
    }

    public void addGoalsAgainst() {
        this.totalGoalsAgainst = this.totalGoalsAgainst + 1;
    }

    // toString() method

    public String toString(){

        return super.getFirstName() + " " + super.getLastName();

    }

}