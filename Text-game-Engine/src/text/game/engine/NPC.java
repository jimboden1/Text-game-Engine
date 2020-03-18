package text.game.engine;

import java.util.ArrayList;

public class NPC implements java.io.Serializable
{
	private String name, description;
	private int type;//0 is merchant; 1 is enemy; 2 is other
	private ArrayList<Command> commands;
	private ArrayList<Events> events;
	private ArrayList<Skill> skills;
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
	public ArrayList<Command> getCommands() {return commands;}
	public ArrayList<Events> getEvents() {return events;}
	public ArrayList<Skill> getSkills() {return skills;}
	

}
