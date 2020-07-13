

public class Coach extends Person {

    //fields

    String aptitude;
    String specialty;
    int rating;

    //constructor

<<<<<<< HEAD
    public Coach(String aptitude, String specialty, int rating, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge, int pContract) {
=======
    public Coach(String position, String specialty, int rating, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge) {
>>>>>>> bad51be25feb414826ac9199130a6c0477ca06a9

        super(pFirstName, pLastName, pPosition, pCountry, pAge);

        this.aptitude = aptitude;
        this.specialty = specialty;
        this.rating = rating;

    } 

    //methods

<<<<<<< HEAD
    public String getAptitude() {
        return aptitude;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getRating() {
        return rating;
    }
    
=======
>>>>>>> bad51be25feb414826ac9199130a6c0477ca06a9
}