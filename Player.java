

public class Player extends Person {
    
    // fields

    int faceoff;
    int shooting;
    int stamina;
    int skating;
    int strength;
    int offensiveAwareness;
    int defensiveAwareness;
    int overall;
    
    // constructor

    public Player(int faceoff, int shooting, int stamina, int skating, int strength, int offensiveAwareness, int defensiveAwareness, String firstName, String lastName, String position, String country, int age, int playerNumber, String currentTeam, String playerType) {

        super(firstName, lastName, position, country, age, playerNumber, currentTeam, playerType);

        this.faceoff = faceoff;
        this.shooting = shooting;
        this.stamina = stamina;
        this.skating = skating;
        this.strength = strength;
        this.offensiveAwareness = offensiveAwareness;
        this.defensiveAwareness = defensiveAwareness;
        overall = faceoff + shooting + stamina + skating + strength + offensiveAwareness + defensiveAwareness;
    }

    // get methods

    public int getFaceoff() {
        return this.faceoff;
    }

    public int getShooting() {
        return this.shooting;
    }

    public int getStamina() {
        return this.stamina;
    }

    public int getSkating() {
        return this.skating;
    }

    public int getOffensiveAwareness() {
        return this.offensiveAwareness;
    }

    public int getDefensiveAwareness() {
        return this.defensiveAwareness;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getOverall() { 
        return this.overall;
    }

    // toString() method

    public String toString(){

        return super.getFirstName();

    }

}