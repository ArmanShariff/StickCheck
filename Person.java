
public class Person {

    // fields

    String firstName;
    String lastName;
    String position;
    String country;
    String currentTeam;
    String playerType;
    int age;
    int playerNumber;
    
    // constructor

    public Person(String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pPlayerNumber, String pCurrentTeam, String pPlayerType) {
        
        firstName = pFirstName;
        lastName = pLastName;
        position = pPosition;
        country = pCountry;
        age = pAge;
        playerNumber = pPlayerNumber;
        currentTeam = pCurrentTeam;
        playerType = pPlayerType;
        
    }

    // counstructer without player number (for coaches)

    public Person(String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, String pCurrentTeam, String pPlayerType) {
        
        firstName = pFirstName;
        lastName = pLastName;
        position = pPosition;
        country = pCountry;
        age = pAge;
        currentTeam = pCurrentTeam;
        playerType = pPlayerType;
        
    }

    public Person(String pFirstName, String pLastName, String pPosition, String pCountry, int pAge) {
        
        firstName = pFirstName;
        lastName = pLastName;
        position = pPosition;
        country = pCountry;
        age = pAge;
        
    }

    // methods

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

}