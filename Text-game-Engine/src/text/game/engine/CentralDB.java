package text.game.engine;

import java.util.ArrayList;

public class CentralDB implements java.io.Serializable{
    public static Player player = new Player();
    public static ArrayList<Item> itemList = new ArrayList<Item>();
    public static ArrayList<Skill> skillList = new ArrayList<Skill>();
    public static ArrayList<Events> eventList = new ArrayList<Events>();
    public static ArrayList<Command> commandList =  new ArrayList<Command>();
    public static ArrayList<NPC> npcList = new ArrayList<NPC>();
    public static Location startScreen = new Location();
}
