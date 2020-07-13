

public class Coach extends Person {

    //fields

    String position;
    String specialty;
    int rating;

    //constructor

    public Coach(String position, String specialty, int rating, String pFirstName, String pLastName, String pPosition, String pCountry, int pAge) {

        super(pFirstName, pLastName, pPosition, pCountry, pAge);

        this.position = position;
        this.specialty = specialty;
        this.rating = rating;

    } 

    //methods

}