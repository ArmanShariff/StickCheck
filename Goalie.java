

public class Goalie extends Person {

    //fields

    int reflexes;
    int agility;
    int flexibility;
    int reboundControl;
    int puckControl;

    //constructor

    public Goalie (int reflexes, int agility, int flexibility, int reboundControl, int puckControl, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pPlayerNumber, int pContract) {

        super(pFirstName, pLastName, pPosition, pCountry, pAge, pPlayerNumber, pContract);
        
        this.reflexes = reflexes;
        this.agility = agility;
        this.flexibility = flexibility;
        this.reboundControl = reboundControl;
        this.puckControl = puckControl;


    }

    // methods

    

}