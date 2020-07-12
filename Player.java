

public class Player extends Person{
    
    // fields
    int faceoff;
    int shooting;
    int stamina;
    int skating;
    int strength;
    int offensiveAwareness;
    int defensiveAwareness;
    
    // constructor
    public Player(int faceoff, int shooting, int stamina, int skating, int strength, int offensiveAwareness, int defensiveAwareness, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pPlayerNumber, int pContract) {

        super(pFirstName, pLastName, pPosition, pCountry, pAge, pPlayerNumber, pContract);

        this.faceoff = faceoff;
        this.shooting = shooting;
        this.stamina = stamina;
        this.skating = skating;
        this.strength = strength;
        this.offensiveAwareness = offensiveAwareness;
        this.defensiveAwareness = defensiveAwareness;

    }
}