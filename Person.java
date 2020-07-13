
public class Person {

    // fields

    String firstName;
    String lastName;
    String position;
    String country;
    int age;
    int playerNumber;
    int contract;
    
    // constructor

    public Person(String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pPlayerNumber, int pContract) {
        
        firstName = pFirstName;
        lastName = pLastName;
        position = pPosition;
        country = pCountry;
        age = pAge;
        playerNumber = pPlayerNumber;
        contract = pContract;
    }

    public Person(String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pContract) {
        
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