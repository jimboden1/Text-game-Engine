package text.game.engine;

import java.util.ArrayList;

public class NPC
{
	private String name, description;
	//ToDo add type
	private ArrayList actions;
	private ArrayList events;
	private ArrayList skills;
	public NPC() {}
	
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	//ToDo setType
	public void setActions(ArrayList actions) {this.actions = actions;}
	public void setEvents(ArrayList events) {this.events = events;}
	public void setSkills(ArrayList skills) {this.skills = skills;}
	
	public String getName() {return name;}
	public String getDescription() {return description;}
	//ToDo getType
	public ArrayList getActions() {return actions;}
	public ArrayList getEvents() {return events;}
	public ArrayList getSkills() {return skills;}
	

}
