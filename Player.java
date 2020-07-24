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
    double currStamina;
    
    // constructor
 
    public Player(double faceoff, double shooting, double stamina, double skating, double strength, double offensiveAwareness, double defensiveAwareness, double shootingTendency, String firstName, String lastName, String position, String country, int age, int playerNumber, String currentTeam, String playerType) {
 
        super(firstName, lastName, position, country, age, playerNumber, currentTeam, playerType);
 
        this.faceoff = faceoff;
        this.shooting = shooting;
        this.stamina = stamina;
        this.skating = skating;
        this.strength = strength;
        this.offensiveAwareness = offensiveAwareness;
        this.defensiveAwareness = defensiveAwareness;
        this.shootingTendency = shootingTendency;
        currStamina = 1;
 
        overall = (shooting + stamina + skating + strength + offensiveAwareness + defensiveAwareness)/6;
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
    
    public double getCurrStamina() {
        return this.currStamina;
    }

    public void setCurrStamina(double currStamina) {
        this.currStamina = currStamina;
    }

    public void dropStamina() {
        double droppedStamina = currStamina - (((100 - stamina)/stamina)/100);
        setCurrStamina(droppedStamina);
    }

    public double getOverall() { 
        return this.overall;
    }
 
    // toString() method
 
    public String toString(){
 
        return super.getFirstName() + " " + super.getLastName();
 
    }
 
}
 

