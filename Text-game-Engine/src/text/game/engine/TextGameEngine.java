package text.game.engine;

// serialization and deserialization code provided by: https://www.geeksforgeeks.org/serialization-in-java/

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

public class TextGameEngine {

	private JFrame frame;
	private File currentFile = null;
    PlayerPanel playerTab = new PlayerPanel();
	private CentralDB centralDB = new CentralDB();
	private LocationPanel roomsTab = new LocationPanel();
    private ItemPanel itemTab = new ItemPanel();
    private NPCPanel npcTab = new NPCPanel();
    private PlatformPanel platPanel = new PlatformPanel();
    EventsPanel eventsTab = new EventsPanel();
    SkillsPanel skillsTab = new SkillsPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextGameEngine window = new TextGameEngine();
					window.frame.setResizable(false);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TextGameEngine() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{

		//creating the GUI
		frame = new JFrame("Text-Based Adventure Engine");
		frame.setBounds(100, 100, 800, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(new BorderLayout());
                JMenuBar menu = new JMenuBar();
                JMenu files = new JMenu("Files");
                JMenuItem save = new JMenuItem("Save");
                save.addActionListener(e->save());
                JMenuItem saveAs = new JMenuItem("Save As");
                saveAs.addActionListener(e->saveAs());
                JMenuItem load = new JMenuItem("Load");
                load.addActionListener(e->load());
                JMenuItem newGame = new JMenuItem("New");
                newGame.addActionListener(e->{
                	centralDB= new CentralDB();
                	CentralDB.loadIntoCentralDB(centralDB);
                	playerTab.update();
                	roomsTab.update();
                	eventsTab.update();
                	itemTab.update();
                	npcTab.update();
                	skillsTab.update();
                });
                JMenu play = new JMenu("Play");
                JMenuItem playNew = new JMenuItem("Load Game");
                playNew.addActionListener(e->play());
                JMenuItem playThis = new JMenuItem("Play This");
                playThis.addActionListener(e->playThis());
                play.add(playNew);
                play.add(playThis);
                files.add(newGame);
                files.add(save);
                files.add(saveAs);
                files.add(load);
                menu.add(files);
                menu.add(play);
                frame.getContentPane().add(menu, BorderLayout.NORTH);
                
		//Creating the tab layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	tabbedPane.setBounds(0, 22, 784, 639);
		frame.getContentPane().add(tabbedPane);

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
		tabbedPane.addTab("Player", null, playerTab.createPlayerPanel(), null);
		tabbedPane.addChangeListener(e->updateAll());
                
        
		tabbedPane.addTab("NPC", null, npcTab.createNPCPanel(), null);

		
		tabbedPane.addTab("Locations", null, roomsTab.createLocationPanel(), null);
		
		tabbedPane.addTab("Events", null, eventsTab.createEventsPanel(), null);
		
        tabbedPane.addTab("Items", null, itemTab.createItemPanel(), null);
        
        
		tabbedPane.addTab("Skills", null, skillsTab.createSkillsPanel(), null);
		
	}
	
	public void save() {
		if(currentFile == null) {
			this.saveAs();
		}
		else {
			
			String filename = currentFile.getAbsolutePath();
			playerTab.savePlayer();
			centralDB.loadOutCentralDB();
			try
		      
			{
				FileOutputStream file = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(centralDB);

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
		centralDB = null;
		JFileChooser chooser = new JFileChooser();
		
	    int returnVal = chooser.showOpenDialog(frame);
	    if(returnVal == JFileChooser.APPROVE_OPTION)
	    {

    		currentFile = chooser.getSelectedFile();
	    	try
        	{
			
				String filename = currentFile.getAbsolutePath();
			
            	FileInputStream file = new FileInputStream(filename); 
            	ObjectInputStream in = new ObjectInputStream(file); 
              
            	centralDB = (CentralDB)in.readObject();
            
            	in.close(); 
            	file.close();
            
            	CentralDB.loadIntoCentralDB(centralDB);


            	playerTab.update();
            	roomsTab.selected = -1;
            	eventsTab.selected = -1;
            	itemTab.selected = -1;
            	npcTab.selected = -1;
            	skillsTab.selected = -1;
            	updateAll();
        	} 
          
        	catch(IOException ex) 
        	{ 
            	System.out.println("IOException is caught"); 
            	System.out.println(ex.getLocalizedMessage());
            	centralDB= new CentralDB();
            	CentralDB.loadIntoCentralDB(centralDB);
            	roomsTab.selected = -1;
            	eventsTab.selected = -1;
            	itemTab.selected = -1;
            	npcTab.selected = -1;
            	skillsTab.selected = -1;
            	updateAll();
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
		currentFile = null;
		int userSelection = fileChooser.showSaveDialog(frame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			currentFile = fileChooser.getSelectedFile().getAbsoluteFile();
			this.save();
		}
	}
	
	public void play() {
		load();
		playThis();
	}
	
	public void playThis() {
		frame.getContentPane().removeAll();
		frame = platPanel.initializePlatformPanel(frame);
		frame.repaint();
		frame.revalidate();
	}
	
	public void editThis() {
		frame.getContentPane().removeAll();
		this.initialize();
		frame.repaint();
		frame.revalidate();
	}
	
	public void updateAll() {
		playerTab.savePlayer();
    	playerTab.update();
    	roomsTab.update();
    	eventsTab.update();
    	itemTab.update();
    	npcTab.update();
    	skillsTab.update();
	}
}
