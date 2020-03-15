
package text.game.engine;

import java.util.ArrayList;

public class Item {
    private String name, description;
    private int cost, type;
    private ArrayList<Skill> skills = new ArrayList<Skill>();
    public Item(){
        name = "new Item";
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
    public void setSkills(ArrayList<Skill> skills){
        this.skills = skills;
    }
    public void addSkill(Skill skill){
        if(!skills.contains(skill)){
            skills.add(skill);
        }
    }
    public void removeSkill(Skill skill){
        if(skills.contains(skill)){
            skills.remove(skill);
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
    public ArrayList<Skill> getSkills(){
        return skills;
    }
}
