package text.game.engine;

import java.util.ArrayList;

public class NPC implements java.io.Serializable
{
	private String name, description;
	private int type;//0 is merchant; 1 is enemy; 2 is other
	private ArrayList<Command> commands;
	private ArrayList<Events> events;
        private int strength, dexterity, iq, health, perception, will;
	private ArrayList<Skill> skills;
        
	public NPC() {
            name = "New NPC";
            type = 0;
        }
	
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setType(int type){this.type=type;}
	public void setStrength(int strength) {this.strength = strength;}
	public void setDexterity(int dexterity) {this.dexterity = dexterity;}
	public void setIQ(int iq) {this.iq = iq;}
	public void setHealth(int health) {this.health = health;}
	public void setPerception(int perception) {this.perception = perception;}
	public void setWill(int will) {this.will = will;}
	public void setCommands(ArrayList actions) {this.commands = actions;}
	public void setEvents(ArrayList events) {this.events = events;}
	public void setSkills(ArrayList skills) {this.skills = skills;}
	
	public String getName() {return name;}
	public String getDescription() {return description;}
	public int getType(){return type;}
	public int getStrength() {return strength;}
	public int getDexterity() {return dexterity;}
	public int getIQ() {return iq;}
	public int getHealth() {return health;}
	public int getPerception() {return perception;}
	public int getWill() {return will;}
	public ArrayList<Command> getCommands() {return commands;}
	public ArrayList<Events> getEvents() {return events;}
	public ArrayList<Skill> getSkills() {return skills;}
	

}
