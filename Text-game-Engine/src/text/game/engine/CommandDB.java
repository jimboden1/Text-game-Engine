package text.game.engine;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextArea;

public class CommandDB {

	public static ArrayList<Command> commands = new ArrayList<>();
	private Random random = new Random();
	private ArrayList<NPC> enemies = new ArrayList<>();
	private JTextArea display = PlatformPanel.descriptionArea;
	
	public CommandDB() {
		
	}
	
	public void clearCommands() {
		commands.removeAll(commands);
		addBaseCommands();
	}
	
	public void addBaseCommands() {
		commands.add(new Command("back", ()-> goBack()));
		commands.add(new Command("check skills", ()-> {
			display.setText("You have the following skills\n");
			for(int index: PlatformPanel.player.getSkills()) {
				Skill skill = CentralDB.skillList.get(index);
				display.append(skill.getName()+": " + skill.getDescription()+"\n");
			}
		}));
		commands.add(new Command("check self", ()-> {
			display.setText(PlatformPanel.player.getDescription());
		}));
		commands.add(new Command("check items", ()-> checkItems()));
		
		commands.add(new Command("help", () -> help()));
	}
	
	public void checkItems() {
		display.setText("You have the following items\n");
		for(int index: PlatformPanel.player.inventory) {
			Item item = CentralDB.itemList.get(index);
			display.append(item.getName() + ": " + item.getDescription()+"\n");
			if(item.getType()!=0&&item.getType()!=9) {
				commands.add(new Command("equip "+item.getName(),()-> {
					PlatformPanel.player.equip(index, item.getType());
					checkItems();
					display.append("You equip "+item.getName()+"\n");
				}));
			}
			else {
				useItem(index);
				}
		}
	}
	
	public void useItem(int index) {
		Item item = CentralDB.itemList.get(index);
		if(item.getType()==0) {
			commands.add(new Command("use "+item.getName(),()-> {
				Events event = new Events();
				event.takeItem(index);
				event.runMethod();
				for(int skill:item.getSkills()) {
					event.giveSkill(skill);
				}
				for(Benefit benefit:item.getBenefits()) {
					if(benefit.attributePlace==0) {
						PlatformPanel.player.setStrength(PlatformPanel.player.getStrength()+benefit.modifier);
					}
					else if(benefit.attributePlace==1) {
						PlatformPanel.player.setDexterity(PlatformPanel.player.getDexterity()+benefit.modifier);
					}
					else if(benefit.attributePlace==2) {
						PlatformPanel.player.setIQ(PlatformPanel.player.getIQ()+benefit.modifier);
					}
					else if(benefit.attributePlace==3) {
						PlatformPanel.player.heal(benefit.modifier);
					}
					else if(benefit.attributePlace==4) {
						PlatformPanel.player.setPerception(PlatformPanel.player.getPerception()+benefit.modifier);
					}
					else {
						PlatformPanel.player.setWill(PlatformPanel.player.getWill()+benefit.modifier);
					}
				}
				PlatformPanel.updatePlayerDisplay();
				clearCommands();
				checkItems();
				display.append("You used "+item.getName()+"\n");
			}));
		}
	}
	
	public void help()
	{
		String commandList = "";
		for (Command c : commands)
		{
			commandList += c.getCommand() + "\n";
		}
		display.append("Here is the list of available commands:\n" + commandList);
	}
	
	public void addLocationCommands(Location here) {
		for(int move : here.getLocations()) {
			if(move!=-1) {
				commands.add(new Command("move to "+ CentralDB.locationList.get(move).getName(), () -> moveTo(CentralDB.locationList.get(move))));
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
			display.append("\nYou rest for a bit to regain your strength\n");
			PlatformPanel.player.heal(50);
			PlatformPanel.updatePlayerDisplay();
		}));
	}
	
	public void addNPCCommands(NPC npc) {
		if(npc.getType()!=1) {
			
		
		commands.add(new Command("look at "+ npc.getName(), ()-> { 
			display.setText(npc.getDescription());
		}));
		commands.add(new Command("approach "+ npc.getName(), ()-> { 
			clearCommands();
			display.setText("You approach "+ npc.getName()+ ", what would you like to do?");
			commands.add(new Command ("talk", () ->  {
				display.setText(npc.getDescription());
			
			}));
			if(npc.getType()==0) {
				commands.add(new Command("buy",()-> { 
					display.setText("What would you like to buy?\n");
					for(int index: npc.getItems()) {
						Item item = CentralDB.itemList.get(index);
						display.append(item.getName() +": "+ item.getCost()+"\n");
						commands.add(new Command("buy "+item.getName(), ()->{
							if(PlatformPanel.player.money>item.getCost()) {
								PlatformPanel.player.money-=item.getCost();
								PlatformPanel.player.inventory.add(CentralDB.itemList.indexOf(item));
								display.append("you have purchased "+ item.getName()+"\n");
								PlatformPanel.updatePlayerDisplay();
							}
						}));
					}
				}));
				commands.add(new Command("sell", ()->{
					display.setText("What would you like to sell?\n");
					for(int index: PlatformPanel.player.inventory) {
						Item item = CentralDB.itemList.get(index);
						display.append(item.getName() +": "+ item.getCost()+"\n");
						commands.add(new Command("sell "+item.getName(), ()->{
							if(item.getType()!=9) {
								PlatformPanel.player.money+=item.getCost();
								PlatformPanel.player.inventory.remove(CentralDB.itemList.indexOf(item));
								display.append("you have sold "+ item.getName()+"\n");
								PlatformPanel.updatePlayerDisplay();
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
		}));
		}
		
	}
	
	public void goBack() {
		clearCommands();
		addLocationCommands(PlatformPanel.here);
		display.setText(PlatformPanel.here.getDescription());
	}
	
	
	public void moveTo(Location place) {
		PlatformPanel.here = place;
		goBack();
	}
	
	public void battle(NPC enemy) {
		commands.removeAll(commands);
		PlatformPanel.player.getModifiers();
		PlatformPanel.player.applyModifiers();
		PlatformPanel.updatePlayerDisplay();
		display.setText("You are fighting " + enemy.getName()+".\n");
		commands.add(new Command("attack",()->{
			display.setText("You are fighting " + enemy.getName()+" they have "+ enemy.getHealth()+" health left.\n");
			display.append("You attack " + enemy.getName()+"\n");
			if(attackCheck(PlatformPanel.player.getPerception(),PlatformPanel.player.getDexterity(),enemy.getPerception(),enemy.getDexterity())) {
				display.append("You hit " + enemy.getName()+" for "+ PlatformPanel.player.getStrength()/2+" damage.\n");
				enemy.takeHealth(PlatformPanel.player.getStrength()/2);
				if(enemy.getHealth()==0) {
					display.append("You win " + enemy.getName()+" lies defeted at your feet enter back to return\n");
					PlatformPanel.player.removeModifiers();
					PlatformPanel.updatePlayerDisplay();
					if(!enemy.getItems().isEmpty()) {
						int loot = random.nextInt(enemy.getItems().size());
						PlatformPanel.player.inventory.add(enemy.getItems().get(loot));
					}
					clearCommands();
				}
				else {
					enemyAttack(enemy);
				}
			}
			else {
				display.append("You miss!\n");
				enemyAttack(enemy);
			}
		}));
		commands.add(new Command("run",()-> { 
			display.setText("You are fighting " + enemy.getName()+".\n");
			int escapeRoll = random.nextInt(100)+1;
			if(escapeRoll+ PlatformPanel.player.getDexterity()>=enemy.getDexterity()) {
				goBack();
				PlatformPanel.player.removeModifiers();
			}
			else {
				enemyAttack(enemy);
			}
		}));
	}
	
	public void enemyAttack(NPC enemy) {
		display.append(enemy.getName()+" Attacks!\n");
		if(attackCheck(enemy.getPerception(),enemy.getDexterity(),PlatformPanel.player.getPerception(),PlatformPanel.player.getDexterity())) {
			display.append(enemy.getName()+" hits you for "+ enemy.getStrength()/2+" damage.\n");
			PlatformPanel.player.takeDamgage(enemy.getStrength()/2);
			PlatformPanel.updatePlayerDisplay();
			if(PlatformPanel.player.getHealth()==0) {
				commands.removeAll(commands);
				display.append("Game Over");
			}
		}
		else {
			display.append("They miss!\n");
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
