package text.game.engine;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class PlatformPanel {

	public static JPanel main = new JPanel(new BorderLayout(10,10)), leftSide = new JPanel(new GridLayout(7,2)), 
			bottom = new JPanel(new BorderLayout(10,10)), buttonPanel = new JPanel(new GridLayout(2,5,10,10));
	public static JTextField commandLine = new JTextField(200);
	public static JTextArea descriptionArea = new JTextArea();
	public static JLabel name = new JLabel(""), health = new JLabel("0"),
			strength = new JLabel("0"), dexterity = new JLabel("0"),
			iq = new JLabel("0"), perception = new JLabel("0"),
			will = new JLabel("0");
	public static JButton button1 = new JButton(""), button2 = new JButton(""),
			button3 = new JButton(""), button4 = new JButton(""),
			button5 = new JButton(""), button6 = new JButton(""),
			button7 = new JButton(""), button8 = new JButton(""),
			button9 = new JButton(""), button10 = new JButton("");
	public static Location here = new Location();
	public CentralDB currentGame = new CentralDB();
	public static Player player = new Player();
	public static NPC focus = new NPC();
	public CommandDB database = new CommandDB();
    
    public JFrame initializePlatformPanel(JFrame base) {
    	JLabel namelb = new JLabel("Name: "), healthlb = new JLabel("Health: "),
    			strengthlb = new JLabel("Strength: "), dexteritylb = new JLabel("Dexterity: "),
    			iqlb = new JLabel("IQ: "), perceptionlb = new JLabel("Perception: "),
    			willlb = new JLabel("Will: ");
    	leftSide.add(namelb);
    	leftSide.add(name);
    	leftSide.add(healthlb);
    	leftSide.add(health);
    	leftSide.add(strengthlb);
    	leftSide.add(strength);
    	leftSide.add(dexteritylb);
    	leftSide.add(dexterity);
    	leftSide.add(iqlb);
    	leftSide.add(iq);
    	leftSide.add(perceptionlb);
    	leftSide.add(perception);
    	leftSide.add(willlb);
    	leftSide.add(will);
    	main.add(leftSide, BorderLayout.WEST);
    	
    	here = CentralDB.startScreen;
    	base.getContentPane().setLayout(new BorderLayout(10,10));
    	JMenuBar menu = new JMenuBar();
        JMenu files = new JMenu("File");
        JMenuItem save = new JMenuItem("Save Game"), load = new JMenuItem("Load Game"), saveAs = new JMenuItem("Create New Save Game");
        files.add(saveAs);
        files.add(save);
        files.add(load);
    	menu.add(files);
    	main.add(menu, BorderLayout.NORTH);
    	
    	
    	/*
    	buttonPanel.add(button1);
    	buttonPanel.add(button2);
    	buttonPanel.add(button3);
    	buttonPanel.add(button4);
    	buttonPanel.add(button5);
    	buttonPanel.add(button6);
    	buttonPanel.add(button7);
    	buttonPanel.add(button8);
    	buttonPanel.add(button9);
    	buttonPanel.add(button10);
    	bottom.add(buttonPanel, BorderLayout.CENTER);
    	*/
    	commandLine.addActionListener(e -> checkCommand());
    	bottom.add(commandLine, BorderLayout.NORTH);
    	
    	descriptionArea.setEditable(false);
    	descriptionArea.setText(here.getDescription());
    	JScrollPane js = new JScrollPane(descriptionArea);
    	descriptionArea.setText(here.getDescription());
        main.add(js, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
    	
        database.addBaseCommands();
    	database.addLocationCommands(here);
    	
        base.getContentPane().add(main, BorderLayout.CENTER);
    	return base;
    }
    public void checkCommand() {
    	System.out.println(commandLine.getText());
    	checkString(commandLine.getText());
    	commandLine.setText("");
    }
    public String checkString(String string) {
		String result = null;
		for(int i=0; i< database.commands.size();i++)
		{
			//Compares Strings regardless of capitalization
			if(string.equalsIgnoreCase(database.commands.get(i).getCommand()))
			{
				database.commands.get(i).runMethod();
				System.out.println("command Recongnised as " + database.commands.get(i).getCommand());
			}
				
		}
		//When you call it, just insert a try catch or a requireNotNull
		return result;
	}
}
