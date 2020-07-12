<<<<<<< HEAD
public class Goalie extends Person{
    
    // fields
    
    
    // constructor
    public Goalie(int stamina, int faceoff, int shooting, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pPlayerNumber, int pContract) {

        super(pFirstName, pLastName, pPosition, pCountry, pAge, pPlayerNumber, pContract);

        

    }
=======


public class Goalie extends Person {

    //fields
    int reflexes;
    int agility;
    int flexibility;
    int reboundControl;
    int puckControl;

    //constructor
    public Goalie (int reflexes, int agility, int flexibility, int reboundControl, int puckControl, String pFirstName, String pLastName, String pPosition, String pCountry, String pAge, String pPlayerNumber, String pContract) {

        super(pFirstName, pLastName, pPosition, pCountry, pAge, pPlayerNumber, pContract);

        this.reflexes = reflexes;
        this.agility = agility;
        this.flexibility = flexibility;
        this.reboundControl = reboundControl;
        this.puckControl = puckControl;


    }


>>>>>>> a971f095ce191a8682b5b819bc3c1817239b6ee7
}