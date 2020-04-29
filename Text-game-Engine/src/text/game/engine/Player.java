package text.game.engine;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player implements java.io.Serializable
{
	private String name, description;
	private int strength = 0, dexterity = 0, iq = 0,maxHealth = 0, health = 0, perception = 0, will=  0;
	private ArrayList<Integer> skills = new ArrayList<>();
	public ArrayList<Integer> inventory = new ArrayList<>();
	public int money = 0;
	
	private int[] equipment = new int[8];
	
	public Player(){
		for (int i = 0; i < equipment.length; i++)
			equipment[i] = -1;
	}
	
	public void equip(int item, int type)
	{
		inventory.remove(item);
		if (equipment[type] != -1)
		{
			unequip(equipment[type], type);
		}
		
		equipment[type] = item;
		
		for (Benefit b : CentralDB.itemList.get(equipment[type]).getBenefits())
		{
			if (b.attribute == "Strength")
				strength += b.modifier;
			else if (b.attribute == "Dexterity")
				dexterity += b.modifier;
			else if (b.attribute == "IQ")
				iq += b.modifier;
			else if (b.attribute == "Health Points")
				health += b.modifier;
			else if (b.attribute == "Perception")
				perception += b.modifier;
			else if (b.attribute == "Will")
				will += b.modifier;
		}		
	}
	
	public void unequip(int item, int type)
	{
		inventory.add(equipment[type]);
		for (Benefit b : CentralDB.itemList.get(equipment[type]).getBenefits())
		{
			if (b.attribute == "Strength")
				strength -= b.modifier;
			else if (b.attribute == "Dexterity")
				dexterity -= b.modifier;
			else if (b.attribute == "IQ")
				iq -= b.modifier;
			else if (b.attribute == "Health Points")
				health -= b.modifier;
			else if (b.attribute == "Perception")
				perception -= b.modifier;
			else if (b.attribute == "Will")
				will -= b.modifier;
		}
	}
	
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setStrength(int strength) {this.strength = strength;}
	public void setDexterity(int dexterity) {this.dexterity = dexterity;}
	public void setIQ(int iq) {this.iq = iq;}
	public void setHealth(int health) {this.health = health;}
	public void setMaxHealth(int health) {
		this.maxHealth = health;
		this.health = health;
		}
	public void setPerception(int perception) {this.perception = perception;}
	public void setWill(int will) {this.will = will;}
	public void setSkills(ArrayList<Integer> skills) {this.skills = skills;}
	public void takeDamgage(int damage) {
		health-=damage;
		if(health<0) {
			health=0;
		}
	}
	public void heal(int heal) {
		health+=heal;
		if(health>maxHealth) {
			health=maxHealth;
		}
	}
	
	public void getInfo()
	{
		System.out.println("Player: " + name + "\n"
				+ "Description:  " + description + "\n"
				+ "Strength: " + strength + "\n"
				+ "Dexterity: " + dexterity + "\n"
				+ "IQ: " + iq + "\n"
				+ "Health: " + health + "\n"
				+ "Perception: " + perception + "\n"
				+ "Will: " + will + "\n"
				//Skills
				);
	}
	
	public String getName() {return name;}
	public String getDescription() {return description;}
	public int getStrength() {return strength;}
	public int getDexterity() {return dexterity;}
	public int getIQ() {return iq;}
	public int getHealth() {return health;}
	public int getMaxHealth() {return maxHealth;}
	public int getPerception() {return perception;}
	public int getWill() {return will;}
	public ArrayList<Integer> getSkills() {return skills;}
	
	public void applyModifiers()
	{
		for (int index : skills)
		{
			Skill skill = CentralDB.skillList.get(index);
			if(skill.getType() == "stregnth")
				strength += skill.getModifier();
			else if(skill.getType() == "dexterity")
				dexterity +=skill.getModifier();
			else if(skill.getType() == "iq")
				iq += skill.getModifier();
			else if(skill.getType() == "health")
				health += skill.getModifier();
			else if(skill.getType() == "perception")
				perception += skill.getModifier();
			else
				will += skill.getModifier();
		}
	}

}
