

public class Coach extends Person {

    //fields

    String aptitude;
    String specialty;
    int rating;

    //constructor

    public Coach(String aptitude, String specialty, int rating, String firstName, String lastName, String position, String country, int age, String currentTeam, String playerType) {

        super(firstName, lastName, position, country, age, currentTeam, playerType);

        this.aptitude = aptitude;
        this.specialty = specialty;
        this.rating = rating;

    } 

    //methods

    public String getAptitude() {
        return aptitude;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getRating() {
        return rating;
    }

    // toString() method

    public String toString(){

        return super.getFirstName() + " " + super.getLastName();

    }
    
}