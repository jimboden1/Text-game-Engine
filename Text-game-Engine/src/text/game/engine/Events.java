
package text.game.engine;

import java.util.ArrayList;

public class Events implements java.io.Serializable{
	private String name, description;
    private int location = -1;
    private int item = -1;
    private int npc = -1;
    private int otherEvent = -1;
    private ArrayList<Integer> skills = new ArrayList<>();
    private int skillCheck;
    private String actionDesc;
    private String successDesc;
    private String failDesc;

	public String getName() {return name;}
	public String getDescription() {return description;}
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public int getLocation() {return location;}
	public int getItem() {return item;}
	public int getNpc() {return npc;}
	public int getOtherEvent() {return otherEvent;}
	public void setLocation(int location) {this.location = location;}
	public void setItem(int item) {this.item = item;}
	public void setNpc(int npc) {this.npc = npc;}
	public void setOtherEvent(int otherEvent) {this.otherEvent = otherEvent;}
	public ArrayList<Integer> getSkills() {return skills;}
	public int getSkillCheck() {return skillCheck;}
	public String getActionDesc() {return actionDesc;}
	public String getSuccessDesc() {return successDesc;}
	public String getFailDesc() {return failDesc;}
	public void setSkills(ArrayList<Integer> skills) {this.skills = skills;}
	public void setSkillCheck(int skillCheck) {this.skillCheck = skillCheck;}
	public void setActionDesc(String actionDesc) {this.actionDesc = actionDesc;}
	public void setSuccessDesc(String successDesc) {this.successDesc = successDesc;}
	public void setFailDesc(String failDesc) {this.failDesc = failDesc;}
}
