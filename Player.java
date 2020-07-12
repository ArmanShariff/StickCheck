

public class Player extends Person{
    
    // fields
    int stamina;
    int faceoff;
    int shooting;
    
    // constructor
    public Player(int stamina, int faceoff, int shooting, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pPlayerNumber, int pContract) {

        super(pFirstName, pLastName, pPosition, pCountry, pAge, pPlayerNumber, pContract);

        this.stamina = stamina;
        this.faceoff = faceoff;
        this.shooting = shooting;

    }
}