package Goalies;

public class Goalie {
    private int reflexes;
    private String name;

    public Goalie(String name, int reflexes){
        this.name = name;
        this.reflexes = reflexes;
    }

    public int getReflexes(){
        return reflexes;
    }

    public String getName(){
        return name;
    }
}