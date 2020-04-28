package text.game.engine;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

public class PlatformPanel {

	private JFrame base;
	private File currentFile = null;
	public static JPanel main = new JPanel(new BorderLayout(10,10)), leftSide = new JPanel(new GridLayout(8,2)), 
			bottom = new JPanel(new BorderLayout(10,10)), buttonPanel = new JPanel(new GridLayout(2,6,10,10));
	public static JTextField commandLine = new JTextField(200);
	public static JTextArea descriptionArea = new JTextArea();
	public static JLabel name = new JLabel(""), health = new JLabel("0"),
			strength = new JLabel("0"), dexterity = new JLabel("0"),
			iq = new JLabel("0"), perception = new JLabel("0"),
			will = new JLabel("0"), money = new JLabel("0");
	public static JButton button1 = new JButton(""), button2 = new JButton(""),
			button3 = new JButton(""), button4 = new JButton(""),
			button5 = new JButton(""), button6 = new JButton(""),
			button7 = new JButton(""), button8 = new JButton(""),
			button9 = new JButton(""), button10 = new JButton("");
	public static Location here = new Location();
	public static Player player = new Player();
	private SaveFile save;
	public CommandDB database = new CommandDB();
    
    public JFrame initializePlatformPanel(JFrame base) {
    	this.base=base;
    	JLabel namelb = new JLabel("Name: "), healthlb = new JLabel("Health: "),
    			strengthlb = new JLabel("Strength: "), dexteritylb = new JLabel("Dexterity: "),
    			iqlb = new JLabel("IQ: "), perceptionlb = new JLabel("Perception: "),
    			willlb = new JLabel("Will: "), moneylb = new JLabel("Money: ");
    	leftSide.add(namelb);
    	leftSide.add(name);
    	leftSide.add(moneylb);
    	leftSide.add(money);
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
    	player = CentralDB.player;
    	updatePlayerDisplay();
    	
    	here = CentralDB.startScreen;
    	base.getContentPane().setLayout(new BorderLayout(10,10));
    	JMenuBar menu = new JMenuBar();
        JMenu files = new JMenu("File");
        JMenuItem save = new JMenuItem("Save Game"), load = new JMenuItem("Load Game"), saveAs = new JMenuItem("Create New Save Game");
        save.addActionListener(e->save());
        load.addActionListener(e->load());
        saveAs.addActionListener(e->saveAs());
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
    	checkString(commandLine.getText());
    	commandLine.setText("");
    }
    
    public static void updatePlayerDisplay() {
    	name.setText(player.getName());
    	health.setText(player.getHealth() + " / " + player.getMaxHealth());
    	strength.setText("" + player.getStrength());
    	dexterity.setText(""+ player.getDexterity());
    	iq.setText(""+player.getIQ());
    	perception.setText(""+player.getPerception());
    	will.setText(""+player.getWill());
    	money.setText(""+player.money);;
    }
    
    public String checkString(String string) {
		String result = null;
		for(int i=0; i< database.commands.size();i++)
		{
			//Compares Strings regardless of capitalization
			if(string.equalsIgnoreCase(database.commands.get(i).getCommand()))
			{
				database.commands.get(i).runMethod();
			}
				
		}
		//When you call it, just insert a try catch or a requireNotNull
		return result;
	}
    
    public void save() {
		if(currentFile == null) {
			this.saveAs();
		}
		else {
			
			
			String filename = currentFile.getAbsolutePath();
			save = new SaveFile(player,here);
			try
		      
			{ 
				FileOutputStream file = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(save); 

				out.close();
				file.close();

			}
			catch(IOException ex)
			{
				System.out.println("IOException is caught");
				
			}
			
		}
	}
	
	public void load() {
		save = null;
		JFileChooser chooser = new JFileChooser();
		
	    int returnVal = chooser.showOpenDialog(base);
	    if(returnVal == JFileChooser.APPROVE_OPTION)
	    {
	    	try
        	{
	    		currentFile = chooser.getSelectedFile();
			
				String filename = currentFile.getAbsolutePath();
			
            	FileInputStream file = new FileInputStream(filename); 
            	ObjectInputStream in = new ObjectInputStream(file); 
              
            	save = (SaveFile)in.readObject();
            
            	in.close(); 
            	file.close();
            	player = save.getPlayer();
            	here = save.getLocation();
            	updatePlayerDisplay();
              
        	} 
          
        	catch(IOException ex) 
        	{ 
            	System.out.println("IOException is caught"); 
        	} 
          
			catch(ClassNotFoundException ex) 
        	{ 
            	System.out.println("ClassNotFoundException is caught"); 
        	} 
	    }
	}
	
	public void saveAs() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(base);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			currentFile = fileChooser.getSelectedFile();
			this.save();
		}
	}
}
