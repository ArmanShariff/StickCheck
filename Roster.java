import java.io.*;
import java.util.*;

public class Roster {
    
    // fields

    ArrayList<String[]> temp = new ArrayList<String[]>();
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

    public static void assignPerson(ArrayList<String[]> temp, ArrayList<Player> players, ArrayList<Goalie> goalies, ArrayList<Coach> coaches) {

        String[][]temp2 = new String[temp.size()][23];
        temp.toArray(temp2);

        for (int x = 2, i < 80, x++) {

            if (temp2[x][3].equals("Player")) {

                players.add(temp2[x][3]);
                int faceoff = Integer.parseInt(temp2[x][9]);
                int shooting = Integer.parseInt(temp2[x][10]);
                int stamina = Integer.parseInt(temp2[x][11]);
                int skating = Integer.parseInt(temp2[x][12]);
                int strength = Integer.parseInt(temp2[x][13]);
                int offensiveAwareness = Integer.parseInt(temp2[x][14]);
                int defensiveAwareness = Integer.parseInt(temp2[x][15]);
                Player player = new Player(firstName, lastName, faceoff, shooting, stamina, skating, strength, offensiveAwareness, defensiveeAwareness);
                players.add(Player); 

            } else if (temp2[x][3].equals("Goalie")) {

                goalies.add(temp2[x][3]);
                int reflexes = Integer.parseInt(temp2[x][16]);
                int agility = Integer.parseInt(temp2[x][17]);
                int flexibility = Integer.parseInt(temp2[x][18]);
                int reboundControl = Integer.parseInt(temp2[x][19]);
                int puckControl = Integer.parseInt(temp2[x][20]);
                Goalie goalie = new Goalie(firstName, lastName, reflexes, agility, flexibility, reboundControl, puckControl);
                goalies.add(Goalie);
                
            } else {

                Coach.add(temp2[x][3]);
                int rating = Integer.parseInt(temp2[x][24]);
                Coach coach = new Coach(firstName, lastName, aptitude, specialty, rating);
                coaches.add(Coach);
                
            }
       

        } 

    }

}