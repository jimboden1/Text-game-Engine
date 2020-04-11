package text.game.engine;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class PlatformPanel {

	public static JPanel main = new JPanel(new BorderLayout(10,10)), leftSide = new JPanel(new GridLayout(10,10)), 
			top = new JPanel(), bottom = new JPanel(new BorderLayout(10,10)), buttonPanel = new JPanel(new GridLayout(2,5,10,10));
	public static JTextField commandLine = new JTextField(200);
	public static JTextArea descriptionArea = new JTextArea();
	public static JButton button1 = new JButton(""), button2 = new JButton(""),
			button3 = new JButton(""), button4 = new JButton(""),
			button5 = new JButton(""), button6 = new JButton(""),
			button7 = new JButton(""), button8 = new JButton(""),
			button9 = new JButton(""), button10 = new JButton("");
	public static Location here = new Location();
	public CentralDB currentGame = new CentralDB();
    
    public JFrame initializePlatformPanel(JFrame base,Location start) {
    	base.removeAll();
    	
    	JMenuBar menu = new JMenuBar();
        JMenu files = new JMenu("File");
        JMenuItem save = new JMenuItem("Save Game"), load = new JMenuItem("Load Game"), saveAs = new JMenuItem("Create New Save Game");
        files.add(saveAs);
        files.add(save);
        files.add(load);
    	menu.add(files);
    	top.add(menu);
    	main.add(top, BorderLayout.NORTH);
    	
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
    	
    	JScrollPane js = new JScrollPane(descriptionArea);
        main.add(js, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
    	
    	return base;
    }
    public void checkCommand() {
    	
    }
}
