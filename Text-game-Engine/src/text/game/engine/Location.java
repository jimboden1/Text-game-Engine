package text.game.engine;

import java.util.ArrayList;

public class Location implements java.io.Serializable{
    private String name = "", description = "";
    private ArrayList<Events> events = new ArrayList<>();
    private Location[] locations = new Location[4];
    private ArrayList<NPC> npcs = new ArrayList<>();
    
    public Location() {
    	name = "New Location";
    }
    
    public static Location startScreen() {
    	Location result = new Location();
    	result.name = "Filler Start Screen";
    	result.description = "You are seeing this screen because the creator did not specifiy a start screen for this game.\n"
    			+ "If the creator is a friend of yours... you have my sympathy.\n"
    			+ "Please try playing a different game that has an actual start point.";
    	return result;
    }

	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setEvents(ArrayList<Events> events) {this.events = events;}
	public void setNorth(Location location) {locations[0]=location;}
	public void setEast(Location location) {locations[1]=location;}
	public void setSouth(Location location) {locations[2]=location;}
	public void setWest(Location location) {locations[3]=location;}
	public void setNPCs(ArrayList<NPC> npcs) {this.npcs = npcs;}
    
	public String getName() {return name;}
	public String getDescription() {return description;}
	public ArrayList<Events> getEvents() {return events;}
	public Location[] getLocations() {return locations;}
	public Location getNorth() {return locations[0];}
	public Location getEast() {return locations[1];}
	public Location getSouth() {return locations[2];}
	public Location getWest() {return locations[3];}
	public ArrayList<NPC> getNPCs() {return npcs;}
    
	
    
}
