package text.game.engine;

import java.util.ArrayList;

public class CentralDB implements java.io.Serializable{
    public Player player = new Player();
    public ArrayList<Item> itemList = new ArrayList<Item>();
    public ArrayList<Skill> skillList = new ArrayList<Skill>();
    public ArrayList<Events> eventList = new ArrayList<Events>();
    public ArrayList<Command> commandList =  new ArrayList<Command>();
    public ArrayList<NPC> npcList = new ArrayList<NPC>();
}
