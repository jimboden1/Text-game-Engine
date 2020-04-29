package text.game.engine;

import java.util.ArrayList;
import java.util.Random;

public class CommandDB {

	public static ArrayList<Command> commands = new ArrayList<>();
	private Random random = new Random();
	private ArrayList<NPC> enemies = new ArrayList<>();
	
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
		
		commands.add(new Command("equip all", () -> {
			for (int item : CentralDB.player.inventory)
			{
				if (CentralDB.itemList.get(CentralDB.player.inventory.get(item)).getType() == 1)
					CentralDB.player.equip(CentralDB.player.inventory.get(item), item);
			}
		}));
		
		commands.add(new Command("help", () -> help()));
		
		for(Location location: CentralDB.locationList) {
			System.out.println(location.getName());
			System.out.println(location.getDescription());
		}
	}
	
	public void help()
	{
		String commandList = "";
		for (Command c : commands)
		{
			commandList += c.getCommand() + "\n";
		}
		PlatformPanel.descriptionArea.append("Here is the list of available commands:\n" + commandList);
	}
	
	public void addLocationCommands(Location here) {
		for(int move : here.getLocations()) {
			if(move!=-1) {
				commands.add(new Command("move to "+ CentralDB.locationList.get(move).getName(), () -> moveTo(CentralDB.locationList.get(move))));
				System.out.println("Command move to " + CentralDB.locationList.get(move).getName() + " added "+ CentralDB.locationList.get(move).getDescription());
			}
		}
		enemies.clear();
		for(int npcIndex : here.getNPCs()) {
			NPC npc = CentralDB.npcList.get(npcIndex);
			addNPCCommands(npc);
			if(npc.getType()==1) {
				enemies.add(npc);
			}
		}
		for(int index: here.getEvents()) {
			Events event = CentralDB.eventList.get(index);
			if(event.getType()==0) {
				commands.add(new Command(event.getName()+" "+event.getTarget(), ()->event.runMethod()));
			}
			else if(event.getType()==1) {
				if(event.checkCondition()) {
					event.runMethod();
				}
			}
			else {
				commands.add(new Command(event.getName()+" "+event.getTarget(), ()->{
					if(event.checkCondition()) {
						event.runMethod();
					}
				}));
			}
		}
		if(!enemies.isEmpty()) {
			commands.add(new Command("explore",()-> {
				int index = random.nextInt(enemies.size());
				battle(enemies.get(index));
			}));
		}
		commands.add(new Command("rest",()-> {
			PlatformPanel.descriptionArea.append("You rest for a bit to regain your strength");
			PlatformPanel.player.heal(50);
			PlatformPanel.updatePlayerDisplay();
		}));
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
								PlatformPanel.updatePlayerDisplay();
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
								PlatformPanel.updatePlayerDisplay();
							}
						}));
					}
				}));
			}
		}));
		for(int index: npc.getEvents()) {
			Events event = CentralDB.eventList.get(index);
			if(event.getType()==0) {
				commands.add(new Command(event.getName()+" "+event.getTarget(), ()->event.runMethod()));
			}
			else if(event.getType()==1) {
				if(event.checkCondition()) {
					event.runMethod();
				}
			}
			else {
				commands.add(new Command(event.getName()+" "+event.getTarget(), ()->{
					if(event.checkCondition()) {
						event.runMethod();
					}
				}));
			}
		}
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
	
	public void battle(NPC enemy) {
		commands.removeAll(commands);
		PlatformPanel.descriptionArea.setText("You are fighting " + enemy.getName()+" they have "+ enemy.getHealth()+" health left.\n");
		commands.add(new Command("attack",()->{
			PlatformPanel.descriptionArea.append("You attack " + enemy.getName()+"\n");
			if(attackCheck(PlatformPanel.player.getPerception(),PlatformPanel.player.getDexterity(),enemy.getPerception(),enemy.getDexterity())) {
				PlatformPanel.descriptionArea.append("You hit " + enemy.getName()+" for "+ PlatformPanel.player.getStrength()/2+" damage.\n");
				enemy.takeHealth(PlatformPanel.player.getStrength()/2);
				if(enemy.getHealth()==0) {
					goBack();
				}
				else {
					enemyAttack(enemy);
				}
			}
		}));
		commands.add(new Command("run",()-> { 
			int escapeRoll = random.nextInt(100)+1;
			if(escapeRoll+ PlatformPanel.player.getDexterity()>=enemy.getDexterity()) {
				goBack();
			}
			else {
				enemyAttack(enemy);
			}
		}));
	}
	
	public void enemyAttack(NPC enemy) {
		PlatformPanel.descriptionArea.append(enemy.getName()+" Attacks!\n");
		if(attackCheck(enemy.getPerception(),enemy.getDexterity(),PlatformPanel.player.getPerception(),PlatformPanel.player.getDexterity())) {
			PlatformPanel.descriptionArea.append(enemy.getName()+"hits you for "+ enemy.getStrength()/2+" damage.\n");
			PlatformPanel.player.takeDamgage(enemy.getStrength()/2);
			PlatformPanel.updatePlayerDisplay();
			if(PlatformPanel.player.getHealth()==0) {
				commands.removeAll(commands);
				PlatformPanel.descriptionArea.append("Game Over");
			}
		}
	}
	
	public boolean attackCheck(int attackerPer, int attackerDex, int defenderPer, int defenderDex) {
		int attackRoll = random.nextInt(100) + 1;
		if(attackRoll+attackerPer+(attackerDex)/4 >= 50+defenderPer/2+defenderDex/2) {
			return true;
		}
		else
			return false;
	}
}
