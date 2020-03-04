package text.game.engine;

import java.util.ArrayList;

public class Player
{
	private String name, description;
	private int strength, dexterity, iq, health, perception, will;
	private ArrayList skills;
	
	public Player() {}
	
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setStrength(int strength) {this.strength = strength;}
	public void setDexterity(int dexterity) {this.dexterity = dexterity;}
	public void setIQ(int iq) {this.iq = iq;}
	public void setHealth(int health) {this.health = health;}
	public void setPerception(int perception) {this.perception = perception;}
	public void setWill(int will) {this.will = will;}
	public void setSkills(ArrayList skills) {this.skills = skills;}
	
	public String getName() {return name;}
	public String getDescription() {return description;}
	public int getStrength() {return strength;}
	public int getDexterity() {return dexterity;}
	public int getIQ() {return iq;}
	public int getHealth() {return health;}
	public int getPerception() {return perception;}
	public int getWill() {return will;}
	public ArrayList getSkills() {return skills;}

}
