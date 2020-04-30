
package text.game.engine;

import java.util.ArrayList;

public class Events implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int type = 0;//0 is command only, 1 checks a condition, and 2 takes a command and then checks a condition
	private String name, target="";
    public ArrayList<Integer> connectedEvents = new ArrayList<>();
    private int location=-1, item=-1, skill=-1;
    public ArrayList<Command> methods = new ArrayList<>();

	public String getName() {return name;}
	public String getTarget() {return target;}
	public void setName(String name) {this.name = name;}
	public void setTarget(String description) {this.target = description;}
	
	public Events() {
		name = "New Event";
	}
	
	public void runMethod() {
		for(Command method: methods) {
			method.runMethod();
		}
	}
	public void display(String display) {
		methods.add(new Command("Display: "+display,()->{
			PlatformPanel.descriptionArea.setText(display);
		}));
	}
	public void append(String display) {
		Command append = new Command("",()->{
			PlatformPanel.descriptionArea.append(display);
		});
		append.runMethod();
	}
	public void giveSkill(int skill) {
		String skillName = CentralDB.skillList.get(skill).getName();
		methods.add(new Command("Give Player "+skillName,()->{
			ArrayList<Integer> skills = PlatformPanel.player.getSkills();
			skills.add(skill);
			PlatformPanel.player.setSkills(skills);
			append("You gain the "+ skillName + " skill");
		}));
	}
	public void takeSkill(int skill) {
		String skillName = CentralDB.skillList.get(skill).getName();
		methods.add(new Command("Give Player "+skillName,()->{
			ArrayList<Integer> skills = PlatformPanel.player.getSkills();
			boolean first = true;
			for(int i=0;i<skills.size();i++) {
				if(skills.get(i)==skill&&first) {
					skills.remove(i);
					first=false;
				}
			}
			PlatformPanel.player.setSkills(skills);
			append("You have lost the "+ skillName + " skill");
		}));
	}
	public void giveItem(int item) {
		String itemName = CentralDB.itemList.get(item).getName();
		methods.add(new Command("Give Player "+ itemName,()->{
			PlatformPanel.player.inventory.add(item);
			append("You added "+ itemName + " to your inventory");
		}));
	}
	public void takeItem(int item) {
		String itemName = CentralDB.itemList.get(item).getName();
		methods.add(new Command("Take "+itemName+" from Player.",()->{
			boolean first = true;
			ArrayList<Integer> items = PlatformPanel.player.inventory;
			for(int i=0;i<items.size();i++) {
				if(items.get(i)==item&&first) {
					items.remove(i);
					first=false;
				}
			}
			PlatformPanel.player.inventory=items;
			append("You have lost "+ itemName + " from your inventory");
		}));
	}
	public void kill() {
		methods.add(new Command("Kill Player",()->{
			CommandDB.commands.clear();
			PlatformPanel.descriptionArea.setText("Game Over");
		}));
	}
	
	public boolean checkCondition() {
		if(location!=-1) {
			if(PlatformPanel.here.equals(CentralDB.locationList.get(location))) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(skill!=-1) {
			if(PlatformPanel.player.getSkills().contains(skill)) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(item!=-1) {
			if(PlatformPanel.player.inventory.contains(item)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public int getType() {return type;}
	public void setType(int type) {this.type = type;}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getSkill() {
		return skill;
	}
	public void setSkill(int skill) {
		this.skill = skill;
	}
}
