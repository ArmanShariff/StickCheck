

public class Goalie extends Person {

    //fields

    int reflexes;
    int agility;
    int flexibility;
    int reboundControl;
    int puckControl;

    //constructor

    public Goalie (int reflexes, int agility, int flexibility, int reboundControl, int puckControl, String firstName, String lastName, String position, String country, int age, int playerNumber, String currentTeam, String playerType) {

        super(firstName, lastName, position, country, age, playerNumber, currentTeam, playerType);
        
        this.reflexes = reflexes;
        this.agility = agility;
        this.flexibility = flexibility;
        this.reboundControl = reboundControl;
        this.puckControl = puckControl;


    }

    // methods

    public int getReflexes() {
        return this.reflexes;
    }

    public int getAgility() {
        return this.agility;
    }

    public int getFlexibility() {
        return flexibility;
    }

    public int getReboundControl() {
        return reboundControl;
    }
    
    public int getPuckControl() {
        return puckControl;
    }

    // toString() method

    public String toString(){

        return super.getFirstName();

    }

}