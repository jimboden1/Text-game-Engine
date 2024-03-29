package text.game.engine;

import java.util.ArrayList;

public class Location implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String name = "", description = "";
    private ArrayList<Integer> events = new ArrayList<>();
    private int[] locations = {-1,-1,-1,-1};
    private ArrayList<Integer> npcs = new ArrayList<>();
    
    private boolean autoFillNpcs = false;
    
    public Location() {
    	name = "New Location";
    }
    
    public static Location startScreen() {
    	Location result = new Location();
    	result.name = "Filler Start Screen";
    	result.description = "You are seeing this screen because the creator did not specifiy a start screen for this game.\n"
    			+ "If the creator is a friend of yours... you have my sympathy.\n"
    			+ "Please try playing a different game that has an actual start point.\n";
    	return result;
    }

	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setEvents(ArrayList<Integer> events) {this.events = events;}
	public void setNorth(int location) {locations[0]=location;}
	public void setEast(int location) {locations[1]=location;}
	public void setSouth(int location) {locations[2]=location;}
	public void setWest(int location) {locations[3]=location;}
	public void setNPCs(ArrayList<Integer> npcs) {this.npcs = npcs;}
    
	public String getName() {return name;}
	public String getDescription()
	{
		if (autoFillNpcs)
		{
			String npcDescs = "\n";
			for (int i = 0; i < npcs.size()-1; i++)
			{

				npcDescs += CentralDB.npcList.get(npcs.get(i)).getName() + ", ";
					
			}
			npcDescs += CentralDB.npcList.get(npcs.get(npcs.size()-1)).getName() + ".\n";
			return description + npcDescs;
		}
		else
			return description;
	}
	
	public String getRawDescription()
	{
		return description;
	}
	
	public ArrayList<Integer> getEvents() {return events;}
	public int[] getLocations() {return locations;}
	public int getNorth() {return locations[0];}
	public int getEast() {return locations[1];}
	public int getSouth() {return locations[2];}
	public int getWest() {return locations[3];}
	public ArrayList<Integer> getNPCs() {return npcs;}

	public boolean isAutoFillNpcs() {return autoFillNpcs;}
	public void setAutoFillNpcs(boolean autoFillNpcs) {this.autoFillNpcs = autoFillNpcs;}
    
	
    
}
