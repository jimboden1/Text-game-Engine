package text.game.engine;

import java.util.ArrayList;

public class CommandDB {

	public ArrayList<Command> commands = new ArrayList<>();
	
	public CommandDB() {
		
	}
	
	public void clearCommands() {
		commands.removeAll(commands);
		addBaseCommands();
	}
	
	public void addBaseCommands() {
		commands.add(new Command("back", ()-> goBack()));
		commands.add(new Command("check skills", ()-> {
			PlatformPanel.descriptionArea.setText("You have the following skills\n");
			for(Skill skill: PlatformPanel.player.getSkills()) {
				PlatformPanel.descriptionArea.append(skill.getName()+": " + skill.getDescription()+"\n");
			}
		}));
		commands.add(new Command("check self", ()-> {
			PlatformPanel.descriptionArea.setText(PlatformPanel.player.getDescription());
		}));
		commands.add(new Command("check items", ()-> {
			PlatformPanel.descriptionArea.setText("You have the following items\n");
			for(Item item: PlatformPanel.player.inventory) {
				PlatformPanel.descriptionArea.append(item.getName() + ": " + item.getDescription()+"\n");
			}
		}));
	}
	
	public void addLocationCommands(Location here) {
		for(Location move : here.getLocations()) {
			if(move!=null) {
				commands.add(new Command("move to "+ move.getName(), () -> moveTo(move)));
			}
		}
		for(NPC npc:here.getNPCs()) {
			commands.add(new Command("talk "+ npc.getName()));
		}
	}
	
	public void addNPCCommands() {
		
	}
	
	public void goBack() {
		PlatformPanel.descriptionArea.setText(PlatformPanel.here.getDescription());
		PlatformPanel.descriptionArea.append("");
		clearCommands();
	}
	
	
	public void moveTo(Location place) {
		PlatformPanel.here = place;
		goBack();
	}
	
	public void talkTo(NPC target) {
		PlatformPanel.focus = target;
		PlatformPanel.descriptionArea.setText("You approch " + target.getName() + "to Talk to them.\n");
		if(target.getType()==0) {
			PlatformPanel.descriptionArea.append(target.getName() + " is a merchant would you like to buy something?\n");
			for(Item item: target.getItems()) {
				PlatformPanel.descriptionArea.append(item.getName() + " for " + item.getCost() + "\n");
				commands.add(new Command("Buy: " + item.getName()));
			}
		}
	}
}
