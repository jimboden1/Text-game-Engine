
package text.game.engine;

public class Events implements java.io.Serializable{
    private String name, description;
    
    public String getName() {return name;}
    public String getDescription() {return description;}
    
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
}
