

public class Player extends Person {
    
    // fields

    int faceoff;
    int shooting;
    int stamina;
    int skating;
    int strength;
    int offensiveAwareness;
    int defensiveAwareness;
    
    // constructor

    public Player(int faceoff, int shooting, int stamina, int skating, int strength, int offensiveAwareness, int defensiveAwareness, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pPlayerNumber) {

        super(pFirstName, pLastName, pPosition, pCountry, pAge, pPlayerNumber);

        this.faceoff = faceoff;
        this.shooting = shooting;
        this.stamina = stamina;
        this.skating = skating;
        this.strength = strength;
        this.offensiveAwareness = offensiveAwareness;
        this.defensiveAwareness = defensiveAwareness;

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
        return this.strength;
    }

    public int getDefensiveAwareness() {
        return this.strength;
    }
}