package text.game.engine;

import java.util.ArrayList;

public class Command {
    private String command;
    public Method method;
    
    String ActionDesc;
    String SuccessDesc;
    String failDesc;
    ArrayList<Integer> skills = new ArrayList<Integer>();
    int location = -1;
    int item = -1;
    int npc = -1;
    int skillCheck = -1;
    
    public Command(String command, Method method){
        this.command= command;
        this.method = method;
    }
    public Command(String command){
        this.command= command;
        this.method = null;
    }
    public void setCommand(String command){this.command = command;}
    public void setTarget(Method method){this.method = method;}
    public String getCommand(){return command;}
    public Method getTarget(){return method;}
    public void runMethod() { method.method();}
	public Method getMethod() {
		return method;
	}
	
	public String getActionDesc() {return ActionDesc;}
	public String getSuccessDesc() {return SuccessDesc;}
	public String getFailDesc() {return failDesc;}
	public void setMethod(Method method) {this.method = method;}
	public void setActionDesc(String actionDesc) {ActionDesc = actionDesc;}
	public void setSuccessDesc(String successDesc) {SuccessDesc = successDesc;}
	public void setFailDesc(String failDesc) {this.failDesc = failDesc;}
	public ArrayList<Integer> getSkills() {return skills;}
	public int getLocation() {return location;}
	public int getItem() {return item;}
	public int getNpc() {return npc;}
	public int getSkillCheck() {return skillCheck;}
	public void setSkills(ArrayList<Integer> skills) {this.skills = skills;}
	public void setLocation(int location) {this.location = location;}
	public void setItem(int item) {this.item = item;}
	public void setNpc(int npc) {this.npc = npc;} 
    public void setSkillCheck(int skillCheck) {this.skillCheck = skillCheck;}
    
}
