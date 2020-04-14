package text.game.engine;

import java.util.ArrayList;

public class CommandDB {

	public ArrayList<Command> commands = new ArrayList<>();
	
	public CommandDB() {
		commands.add(new Command("back"));
	}
	
	public void clearCommands() {
		commands.removeAll(commands);
		commands.add(new Command("back"));
	}
	
	public void addLocationCommands(Location here) {
		for(Location move:here.getLocations()) {
			commands.add(new Command("move to", move.getName()));
		}
		for(NPC npc:here.getNPCs()) {
			commands.add(new Command("talk", npc.getName()));
		}
	}
	
}
