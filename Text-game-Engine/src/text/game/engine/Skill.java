
package text.game.engine;

public class Skill implements java.io.Serializable
{
    private String name, description, type;
    int modifier;
    
    public Skill() 
    {
    	name = "New Skill";
    	type = "strength";
    	modifier = 0;
    }
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setType(String type){this.type = type;}
    public void setModifier(int modifier) {this.modifier = modifier;}
    
    public int getModifier() {return modifier;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getType(){return type;}
}
