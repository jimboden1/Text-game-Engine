
package text.game.engine;

public class Skill implements java.io.Serializable
{
    private String name, description;
    private boolean strength, dexterity, iq, health, perception, will;
    public Skill() 
    {
    	strength = false;
    	dexterity = false;
    	iq = false;
    	health = false;
    	perception = false;
    	will = false;
    }
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setType(String type)
    {
    	if(type == "strength")
    		strength = true;
    	else if(type == "dexterity")
    		dexterity = true;
    	else if(type == "iq")
    		iq = true;
    	else if(type=="health")
    		health = true;
    	else if(type=="perception")
    		perception = true;
    	else
    		will = true;
    }
}
