package text.game.engine;

import java.util.ArrayList;

public class Item implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name, description;
    private int cost, type;
    private ArrayList<Integer> skills = new ArrayList<Integer>();
    private ArrayList<Benefit> benefits = new ArrayList<>();
    
    public Item(){
        name = "New Item";
        cost = 0;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(int type){
        this.type = type;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
    public void setSkills(ArrayList<Integer> skills){
        this.skills = skills;
    }
    public void addSkill(int skill){
        if(!skills.contains(skill)){
            skills.add(skill);
        }
    }
    public void removeSkill(int skill){
        if(skills.contains(skill)){
            skills.remove(skill);
        }
    }
    public void setBenefits(ArrayList<Benefit> benefits){
        this.benefits = benefits;
    }
    public void addBenefits(Benefit benefit){
        if(!benefits.contains(benefit)){
            benefits.add(benefit);
        }
    }
    public void removeBenefits(Benefit benefit){
        if(benefits.contains(benefit)){
            benefits.remove(benefit);
        }
    }
    
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getType(){
        return type;
    }
    public int getCost(){
        return cost;
    }
    public ArrayList<Integer> getSkills(){
        return skills;
    }
    public ArrayList<Benefit> getBenefits(){
        return benefits;
    }
}
