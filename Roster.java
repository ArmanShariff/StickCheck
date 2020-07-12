import java.util.*;

public class Roster {
    
    // fields

    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Goalie> goalies = new ArrayList<Goalie>();
    String filename;
    
    // constructor

    public Roster (String filename) {
        this.filename = filename;
    }

}