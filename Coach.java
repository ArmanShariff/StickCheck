

public class Coach extends Person {

    //fields

    String aptitude;
    String specialty;
    int rating;

    //constructor

    public Coach(String aptitude, String specialty, int rating, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pContract) {

        super(pFirstName, pLastName, pPosition, pCountry, pAge, pContract);

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
    
}