

public class Coach extends Person {

    //fields

    String aptitude;
    String specialty;
    int rating;

    //constructor

    public Coach(String position, String specialty, int rating, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge) {

        super(pFirstName, pLastName, pPosition, pCountry, pAge);

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