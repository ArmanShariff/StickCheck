package Players;

public class Player {
	private int skating;
    private String name;

    public Player(String name, int skating){
        this.name = name;
        this.skating = skating;
    }

    public int getSkating(){
        return skating;
    }

    public String getName(){
        return name;
    }
}