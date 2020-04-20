package text.game.engine;

import java.util.ArrayList;

public class Location implements java.io.Serializable{
    private String name = "", description = "";
    private ArrayList<Events> events = new ArrayList<>();
    private ArrayList<Location> locations = new ArrayList<>();
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
	public void setLocations(ArrayList<Location> locations) {this.locations = locations;}
	public void setNPCs(ArrayList<NPC> npcs) {this.npcs = npcs;}
    
	public String getName() {return name;}
	public String getDescription() {return description;}
	public ArrayList<Events> getEvents() {return events;}
	public ArrayList<Location> getLocations() {return locations;}
	public ArrayList<NPC> getNPCs() {return npcs;}
    
	
    
}
