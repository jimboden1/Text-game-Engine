package text.game.engine;

import java.util.ArrayList;

public class CentralDB implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static Player player = new Player();
    public static ArrayList<Item> itemList = new ArrayList<Item>();
    public static ArrayList<Skill> skillList = new ArrayList<Skill>();
    public static ArrayList<Events> eventList = new ArrayList<Events>();
    public static ArrayList<Command> commandList =  new ArrayList<Command>();
    public static ArrayList<Location> locationList =  new ArrayList<Location>();
    public static ArrayList<NPC> npcList = new ArrayList<NPC>();
    public static Location startScreen = Location.startScreen();
    public Player playerTemp = new Player();
    public ArrayList<Item> itemListTemp = new ArrayList<Item>();
    public ArrayList<Skill> skillListTemp = new ArrayList<Skill>();
    public ArrayList<Events> eventListTemp = new ArrayList<Events>();
    public ArrayList<Command> commandListTemp =  new ArrayList<Command>();
    public ArrayList<Location> locationListTemp =  new ArrayList<Location>();
    public ArrayList<NPC> npcListTemp = new ArrayList<NPC>();
    public Location startScreenTemp = Location.startScreen();

    public void loadOutCentralDB() {
    	this.playerTemp = player;
    	this.itemListTemp = itemList;
    	this.skillListTemp = skillList;
    	this.playerTemp = player;
    	this.eventListTemp = eventList;
    	this.commandListTemp = commandList;
    	this.locationListTemp = locationList;
    	this.npcListTemp = npcList;
    	this.startScreenTemp = startScreen;
    }
    
    public static void loadIntoCentralDB(CentralDB centralDB) {
    	player = centralDB.playerTemp;
    	itemList = centralDB.itemListTemp;
    	skillList = centralDB.skillListTemp;
    	eventList = centralDB.eventListTemp;
    	commandList = centralDB.commandListTemp;
    	locationList = centralDB.locationListTemp;
    	npcList = centralDB.npcListTemp;
    	startScreen = centralDB.startScreenTemp;
    }
    
    public ArrayList<Integer> changeIndex(ArrayList<Integer> list, int index)
    {
    	ArrayList<Integer> newList = list;
    	
    	for (int item : newList)
    	{
    		if (item == index)
    			newList.remove(item);
    		else if (item < index)
    			item = item-1;
    	}
    	
    	return newList;
    }
    
}
