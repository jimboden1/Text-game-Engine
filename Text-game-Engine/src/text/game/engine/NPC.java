package text.game.engine;

import java.util.ArrayList;

public class NPC
{
	private String name, description;
	private int type;
	private ArrayList commands;
	private ArrayList events;
	private ArrayList skills;
	public NPC() {
            name = "New NPC";
            type = 0;
        }
	
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setType(int type){this.type=type;}
	public void setCommands(ArrayList actions) {this.commands = actions;}
	public void setEvents(ArrayList events) {this.events = events;}
	public void setSkills(ArrayList skills) {this.skills = skills;}
	
	public String getName() {return name;}
	public String getDescription() {return description;}
	public int getType(){return type;}
	public ArrayList getCommands() {return commands;}
	public ArrayList getEvents() {return events;}
	public ArrayList getSkills() {return skills;}
	

}
