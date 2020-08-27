public class Player extends Person {

    // fields

    double faceoff;
    double shooting;
    double stamina;
    double skating;
    double strength;
    double offensiveAwareness;
    double defensiveAwareness;
    double overall;
    double shootingTendency;
    double staminaBar;

    double originalFaceoff;
    double originalShooting;
    double originalSkating;
    double originalStrength;
    double originalOffensiveAwareness;
    double originalDefensiveAwareness;

    // statistics

    int goals;
    int shots;
    int plusMinus;
    double shootingPercentage;

    // constructor

    public Player(double faceoff, double shooting, double stamina, double skating, double strength,
            double offensiveAwareness, double defensiveAwareness, double shootingTendency, String firstName,
            String lastName, String position, String country, int age, int playerNumber, String currentTeam,
            String playerType) {

        super(firstName, lastName, position, country, age, playerNumber, currentTeam, playerType);

        this.faceoff = faceoff;
        originalFaceoff = faceoff;
        this.shooting = shooting;
        originalShooting = shooting;
        this.stamina = stamina;
        this.skating = skating;
        originalSkating = skating;
        this.strength = strength;
        originalStrength = strength;
        this.offensiveAwareness = offensiveAwareness;
        originalOffensiveAwareness = offensiveAwareness;
        this.defensiveAwareness = defensiveAwareness;
        originalDefensiveAwareness = defensiveAwareness;
        this.shootingTendency = shootingTendency;
        staminaBar = 1.0;
        goals = 0;
        shots = 0;
        plusMinus = 0;

        overall = (shooting + stamina + skating + strength + offensiveAwareness + defensiveAwareness) / 6;
    }

    // get methods

    public double getFaceoff() {
        return this.faceoff;
    }

    public double getShooting() {
        return this.shooting;
    }

    public double getStamina() {
        return this.stamina;
    }

    public double getStaminaBar() {
        return this.staminaBar;
    }

    public double getSkating() {
        return this.skating;
    }

    public double getOffensiveAwareness() {
        return this.offensiveAwareness;
    }

    public double getDefensiveAwareness() {
        return this.defensiveAwareness;
    }

    public double getStrength() {
        return this.strength;
    }

    public double getShootingTendency() {
        return this.shootingTendency;
    }

    public double getOverall() {
        return this.overall;
    }

    public double getGoals() {
        return this.goals;
    }

    public void addGoal() {
        this.goals++;
    }

    public double getShots() {
        return this.shots;
    }

    public void addShot() {
        this.shots++;
        this.shootingPercentage = ((double) goals)/shots;
    }

    public double shootingPercentage() {
        return (this.goals/this.shots);
    }

    // set methods

    public void setFaceoff(double faceoff) {
        this.faceoff = faceoff;
    }

    public void setShooting(double shooting) {
        this.shooting = shooting;
    }

    public void setSkating(double skating) {
        this.skating = skating;
    }

    public void setOffAwareness(double offensiveAwareness) {
        this.offensiveAwareness = offensiveAwareness;
    }

    public void setDefAwareness(double defensiveAwareness) {
        this.defensiveAwareness = defensiveAwareness;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void setStaminaBar(double staminaBar) {
        if (staminaBar < 0.25) {
            staminaBar = 0.25;
        } else {
            this.staminaBar = staminaBar;
        }
    }

    public void regenerateStaminaBar(int time) {
        setStaminaBar(this.staminaBar + (time * 0.002));
        if (staminaBar > 1) {
            setStaminaBar(1.0);
        }
    }

    // stat changes methods

    public void dropPlayerStamina(int drops) {
        setStaminaBar(staminaBar - (staminaBar + drops * drops) / stamina);

    }

    public void dropPlayerStats(double staminaBar) {
        setFaceoff(originalFaceoff * staminaBar);
        setShooting(originalShooting * staminaBar);
        setSkating(originalSkating * staminaBar);
        setOffAwareness(originalOffensiveAwareness * staminaBar);
        setDefAwareness(originalDefensiveAwareness * staminaBar);
        setStrength(originalStrength * staminaBar);
    }

    public void resetPlayerStats() {
        setFaceoff(originalFaceoff);
        setShooting(originalShooting);
        setSkating(originalSkating);
        setOffAwareness(originalOffensiveAwareness);
        setDefAwareness(originalDefensiveAwareness);
        setStrength(originalStrength);
    }

    // toString() method

    public String toString() {

        return super.getFirstName() + " " + super.getLastName();

    }

}
