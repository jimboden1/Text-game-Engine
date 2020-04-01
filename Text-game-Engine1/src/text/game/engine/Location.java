package text.game.engine;

import java.util.ArrayList;

public class Location implements java.io.Serializable{
    private String name, description;
    private ArrayList<Events> events;
    private ArrayList<Command> commands;
    private ArrayList<Item> items;
    
    public Location() {
    	name = "New Location";
    }

	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setEvents(ArrayList<Events> events) {this.events = events;}
	public void setCommands(ArrayList<Command> commands) {this.commands = commands;}
	public void setItems(ArrayList<Item> items) {this.items = items;}
    
	public String getName() {return name;}
	public String getDescription() {return description;}
	public ArrayList<Events> getEvents() {return events;}
	public ArrayList<Command> getCommands() {return commands;}
	public ArrayList<Item> getItems() {return items;} 
    
	
    
}
