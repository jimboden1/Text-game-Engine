package text.game.engine;

import java.util.ArrayList;

public class Location implements java.io.Serializable{
    private String name, description;
    private ArrayList<Events> events;
    private ArrayList<Location> locations;
    private ArrayList<Item> items;
    private ArrayList<NPC> npcs;
    
    public Location() {
    	name = "New Location";
    }

	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setEvents(ArrayList<Events> events) {this.events = events;}
	public void setLocations(ArrayList<Location> locations) {this.locations = locations;}
	public void setItems(ArrayList<Item> items) {this.items = items;}
	public void setNPCs(ArrayList<NPC> npcs) {this.npcs = npcs;}
    
	public String getName() {return name;}
	public String getDescription() {return description;}
	public ArrayList<Events> getEvents() {return events;}
	public ArrayList<Location> getLocations() {return locations;}
	public ArrayList<Item> getItems() {return items;} 
	public ArrayList<NPC> getNPCs() {return npcs;}
    
	
    
}
