package text.game.engine;

import java.util.ArrayList;

public class CommandDB {

	public ArrayList<Command> commands = new ArrayList<>();
	
	public CommandDB() {
		
	}
	
	public void clearCommands() {
		commands.removeAll(commands);
	}
	
	public void addBaseCommands() {
		commands.add(new Command("back", ()-> goBack()));
	}
	
	public void addLocationCommands(Location here) {
		for(Location move:here.getLocations()) {
			commands.add(new Command("move to "+ move.getName(), () -> moveTo(move)));
		}
		for(NPC npc:here.getNPCs()) {
			commands.add(new Command("talk "+ npc.getName()));
		}
	}
	public void goBack() {
		PlatformPanel.descriptionArea.setText(PlatformPanel.here.getDescription());
		PlatformPanel.descriptionArea.append("");
	}
	public void moveTo(Location place) {
		PlatformPanel.here = place;
		goBack();
	}
}
