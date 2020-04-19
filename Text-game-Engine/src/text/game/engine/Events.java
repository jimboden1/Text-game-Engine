
package text.game.engine;

public class Events implements java.io.Serializable{
	private String name, description;
    private Location location;
    private Item item;
    private NPC npc;
    private Events otherEvent;
    private Skill skill;
    private int skillCheck;
    private String actionDesc;
    private String successDesc;
    private String failDesc;

	public String getName() {return name;}
	public String getDescription() {return description;}
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public Location getLocation() {return location;}
	public Item getItem() {return item;}
	public NPC getNpc() {return npc;}
	public Events getOtherEvent() {return otherEvent;}
	public void setLocation(Location location) {this.location = location;}
	public void setItem(Item item) {this.item = item;}
	public void setNpc(NPC npc) {this.npc = npc;}
	public void setOtherEvent(Events otherEvent) {this.otherEvent = otherEvent;}
	public Skill getSkill() {return skill;}
	public int getSkillCheck() {return skillCheck;}
	public String getActionDesc() {return actionDesc;}
	public String getSuccessDesc() {return successDesc;}
	public String getFailDesc() {return failDesc;}
	public void setSkill(Skill skill) {this.skill = skill;}
	public void setSkillCheck(int skillCheck) {this.skillCheck = skillCheck;}
	public void setActionDesc(String actionDesc) {this.actionDesc = actionDesc;}
	public void setSuccessDesc(String successDesc) {this.successDesc = successDesc;}
	public void setFailDesc(String failDesc) {this.failDesc = failDesc;}
}
