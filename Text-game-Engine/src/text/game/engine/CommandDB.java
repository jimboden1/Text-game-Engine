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
			for(int index: PlatformPanel.player.getSkills()) {
				Skill skill = CentralDB.skillList.get(index);
				PlatformPanel.descriptionArea.append(skill.getName()+": " + skill.getDescription()+"\n");
			}
		}));
		commands.add(new Command("check self", ()-> {
			PlatformPanel.descriptionArea.setText(PlatformPanel.player.getDescription());
		}));
		commands.add(new Command("check items", ()-> {
			PlatformPanel.descriptionArea.setText("You have the following items\n");
			for(int index: PlatformPanel.player.inventory) {
				Item item = CentralDB.itemList.get(index);
				PlatformPanel.descriptionArea.append(item.getName() + ": " + item.getDescription()+"\n");
			}
		}));
		for(Location location: CentralDB.locationList) {
			System.out.println(location.getName());
			System.out.println(location.getDescription());
		}
	}
	
	public void addLocationCommands(Location here) {
		for(int move : here.getLocations()) {
			if(move!=-1) {
				commands.add(new Command("move to "+ CentralDB.locationList.get(move).getName(), () -> moveTo(CentralDB.locationList.get(move))));
				System.out.println("Command move to " + CentralDB.locationList.get(move).getName() + " added "+ CentralDB.locationList.get(move).getDescription());
			}
		}
		for(int npcIndex : here.getNPCs()) {
			NPC npc = CentralDB.npcList.get(npcIndex);
			addNPCCommands(npc);
		}
	}
	
	public void addNPCCommands(NPC npc) {
		commands.add(new Command("look at "+ npc.getName(), ()-> { 
			PlatformPanel.descriptionArea.setText(npc.getDescription());
		}));
		commands.add(new Command("approach "+ npc.getName(), ()-> { 
			PlatformPanel.descriptionArea.setText("You approach "+ npc.getName()+ ", what would you like to do?");
			commands.add(new Command ("talk", () ->  {
				PlatformPanel.descriptionArea.setText(npc.getDescription());
			
			}));
			
			if(npc.getType()==0) {
				commands.add(new Command("buy",()-> { 
					PlatformPanel.descriptionArea.setText("What would you like to buy?\n");
					for(int index: npc.getItems()) {
						Item item = CentralDB.itemList.get(index);
						PlatformPanel.descriptionArea.append(item.getName() +": "+ item.getCost()+"\n");
						commands.add(new Command("buy "+item.getName(), ()->{
							if(PlatformPanel.player.money>item.getCost()) {
								PlatformPanel.player.money-=item.getCost();
								PlatformPanel.player.inventory.add(CentralDB.itemList.indexOf(item));
								PlatformPanel.descriptionArea.append("you have purchased "+ item.getName()+"\n");
							}
						}));
					}
				}));
				commands.add(new Command("sell", ()->{
					PlatformPanel.descriptionArea.setText("What would you like to sell?\n");
					for(int index: PlatformPanel.player.inventory) {
						Item item = CentralDB.itemList.get(index);
						PlatformPanel.descriptionArea.append(item.getName() +": "+ item.getCost()+"\n");
						commands.add(new Command("sell "+item.getName(), ()->{
							if(item.getType()!=9) {
								PlatformPanel.player.money+=item.getCost();
								PlatformPanel.player.inventory.remove(CentralDB.itemList.indexOf(item));
								PlatformPanel.descriptionArea.append("you have sold "+ item.getName()+"\n");
							}
						}));
					}
				}));
			}
		}));
	}
	
	public void goBack() {
		clearCommands();
		addLocationCommands(PlatformPanel.here);
		PlatformPanel.descriptionArea.setText(PlatformPanel.here.getDescription());
	}
	
	
	public void moveTo(Location place) {
		PlatformPanel.here = place;
		goBack();
	}
	
}
