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
    
    // constructor
 
    public Player(double faceoff, double shooting, double stamina, double skating, double strength, double offensiveAwareness, double defensiveAwareness, double shootingTendency, String firstName, String lastName, String position, String country, int age, int playerNumber, String currentTeam, String playerType) {
 
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
        staminaBar = 1;
 
        overall = (shooting + stamina + skating + strength + offensiveAwareness + defensiveAwareness)/6;
    }
 
    // get methods
 
    public double getFaceoff() {
        return this.faceoff;
    }

    public void setFaceoff(double faceoff) {
        this.faceoff = faceoff;
    }
 
    public double getShooting() {
        return this.shooting;
    }
    
    public void setShooting(double shooting) {
        this.shooting = shooting;
    }

    public double getStamina() {
        return this.stamina;
    }

    public double getSkating() {
        return this.skating;
    }

    public void setSkating(double skating) {
        this.skating = skating;
    }
 
    public double getOffensiveAwareness() {
        return this.offensiveAwareness;
    }

    public void setOffAwareness(double offensiveAwareness) {
        this.offensiveAwareness = offensiveAwareness;
    }
 
    public double getDefensiveAwareness() {
        return this.defensiveAwareness;
    }

    public void setDefAwareness(double defensiveAwareness) {
        this.defensiveAwareness = defensiveAwareness;
    }
 
    public double getStrength() {
        return this.strength;
    }
    
    public void setStrength(double strength) {
        this.strength = strength;
    }
 
    public double getShootingTendency() {
        return this.shootingTendency;
    }
    
    public double getStaminaBar() {
        return this.staminaBar;
    }

    public void setStaminaBar(double staminaBar) {
        this.staminaBar = staminaBar;
    }

    public void dropStamina(int drops) {
        double droppedStamina = staminaBar - (staminaBar + drops*drops)/stamina;
        setStaminaBar(droppedStamina);
    }

    public void dropStats(double staminaBar) {
        setFaceoff(originalFaceoff * staminaBar);
        setShooting(originalShooting * staminaBar);
        setSkating(originalSkating * staminaBar);
        setOffAwareness(originalOffensiveAwareness * staminaBar);
        setDefAwareness(originalDefensiveAwareness * staminaBar);
        setStrength(originalStrength * staminaBar);
    }

    public void ResetStats() {
        setFaceoff(originalFaceoff);
        setShooting(originalShooting);
        setSkating(originalSkating);
        setOffAwareness(originalOffensiveAwareness);
        setDefAwareness(originalDefensiveAwareness);
        setStrength(originalStrength);
    }

    public double getOverall() { 
        return this.overall;
    }
 
    // toString() method
 
    public String toString(){
 
        return super.getFirstName() + " " + super.getLastName();
 
    }
 
}
 

