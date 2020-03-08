/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.game.engine;

import java.util.ArrayList;

public class Item {
    private String name, description, slot;
    private int cost;
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
    public void setSlot(String slot){
        this.slot = slot;
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
    public String getSlot(){
        return slot;
    }
    public int getCost(){
        return cost;
    }
    public ArrayList<Skill> getSkills(){
        return skills;
    }
}
