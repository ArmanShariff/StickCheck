import java.io.*;
import java.util.*;

public class Roster {
    
    // fields

    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Goalie> goalies = new ArrayList<Goalie>();
    ArrayList<Coach> coaches = new ArrayList<Coach>();
    String fileName;
    
    // constructor

    public Roster (String fileName) throws FileNotFoundException {
        
        this.fileName = fileName;
        readFile(fileName, players, goalies, coaches);

    }

    // methods

    public static void readFile(String fileName, ArrayList<Player> players, ArrayList<Goalie> goalies,ArrayList<Coach> coaches) throws FileNotFoundException{

        ArrayList<String[]> temp = new ArrayList<String[]>();

        File file = new File(fileName);
    
        // check that the file exists
        
        if ( file.exists() ) {

            // before trying to create a
            // Create a Scanner from the file. 
            // This statement can cause a FileNotFoundException.
                  
            Scanner inFile = new Scanner( file );
                  
            // For each line in the file, read the line and add it to the array list
            // Use the results of calling the hasNext method to 
            // determine if you are at the end of the file before 
            // reading the next line of the file.
                  
            while ( inFile.hasNext()) {

                // read the next line
                String line = inFile.nextLine();
                // Split line by "," and add it to the array list

                temp.add(line.split(","));
            }

            assignPerson(ArrayList<String[]> temp, ArrayList<Player> players, ArrayList<Goalie> goalies,ArrayList<Coach> coaches);

            // close the Scanner object attached to the file
            inFile.close();

        }

    }

}