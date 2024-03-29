package text.game.engine;

import java.util.ArrayList;

public class Player implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String name, description;
	private int strength = 0, dexterity = 0, iq = 0,maxHealth = 0, health = 0, perception = 0, will=  0;
	public int modStrength=0, modDexterity = 0, modIq = 0,modMaxHealth = 0, modPerception = 0, modWill=  0;
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
		boolean first = true;
		ArrayList<Integer> items = PlatformPanel.player.inventory;
		for(int i=0;i<items.size();i++) {
			if(items.get(i)==item&&first) {
				items.remove(i);
				first=false;
			}
		}
		
		PlatformPanel.player.inventory=items;
		if (equipment[type] != -1)
		{
			unequip(equipment[type], type);
		}
		
		equipment[type] = item;
		
		for (Benefit b : CentralDB.itemList.get(equipment[type]).getBenefits())
		{
			if (b.attributePlace == 0)
				strength += b.modifier;
			else if (b.attributePlace == 1)
				dexterity += b.modifier;
			else if (b.attributePlace == 2)
				iq += b.modifier;
			else if (b.attributePlace == 3)
				modMaxHealth += b.modifier;
			else if (b.attributePlace == 4)
				perception += b.modifier;
			else if (b.attributePlace == 5)
				will += b.modifier;
		}
		PlatformPanel.updatePlayerDisplay();
	}
	
	public void unequip(int item, int type)
	{
		inventory.add(equipment[type]);
		for (Benefit b : CentralDB.itemList.get(equipment[type]).getBenefits())
		{
			if (b.attributePlace == 0)
				strength -= b.modifier;
			else if (b.attributePlace == 1)
				dexterity -= b.modifier;
			else if (b.attributePlace == 2)
				iq -= b.modifier;
			else if (b.attributePlace == 3)
				modMaxHealth -= b.modifier;
			else if (b.attributePlace == 4)
				perception -= b.modifier;
			else if (b.attributePlace == 5)
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
	
	public void getModifiers()
	{
		for (int index : skills)
		{
			Skill skill = CentralDB.skillList.get(index);
			if(skill.getType() == "strength")
				modStrength += skill.getModifier();
			else if(skill.getType() == "dexterity")
				modDexterity +=skill.getModifier();
			else if(skill.getType() == "iq")
				modIq += skill.getModifier();
			else if(skill.getType() == "health")
				modMaxHealth += skill.getModifier();
			else if(skill.getType() == "perception")
				modPerception += skill.getModifier();
			else
				modWill += skill.getModifier();
		}
	}
	
	public void applyModifiers() {
		strength += modStrength;
		dexterity +=modDexterity;
		iq += modIq;
		maxHealth +=modMaxHealth;
		perception += modPerception;
		will  += modWill;
	}
	
	public void removeModifiers() {
		strength -= modStrength;
		dexterity -=modDexterity;
		iq -= modIq;
		maxHealth -=modMaxHealth;
		perception -= modPerception;
		will  -= modWill;
	}

}
