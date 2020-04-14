package text.game.engine;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextGameEngine {

	private JFrame frame;
	private File currentFile = null;
	private CentralDB centralDB = new CentralDB();
	private LocationPanel roomsTab = new LocationPanel();
    private ItemPanel itemTab = new ItemPanel();
    private NPCPanel npcTab = new NPCPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextGameEngine window = new TextGameEngine();
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
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(new BorderLayout());
                JMenuBar menu = new JMenuBar();
                JMenu files = new JMenu("Files");
                JMenuItem save = new JMenuItem("Save");
                save.addActionListener(e->save());
                JMenuItem saveAs = new JMenuItem("Save As");
                JMenuItem load = new JMenuItem("Load");
                load.addActionListener(e->load());
                JMenuItem create = new JMenuItem("New");
                JMenu play = new JMenu("Play");
                JMenuItem playNew = new JMenuItem("Load Game");
                JMenuItem playThis = new JMenuItem("Play This");
                play.add(playNew);
                play.add(playThis);
                files.add(save);
                files.add(saveAs);
                files.add(load);
                files.add(create);
                menu.add(files);
                menu.add(play);
                frame.getContentPane().add(menu, BorderLayout.NORTH);
                
		//Creating the tab layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	tabbedPane.setBounds(0, 22, 784, 639);
		frame.getContentPane().add(tabbedPane);

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
		JPanel playerBase = new JPanel();
        PlayerPanel playerTab = new PlayerPanel(playerBase);
		tabbedPane.addTab("Player", null, playerTab.createPlayerPanel(), null);
                
        
		tabbedPane.addTab("NPC", null, npcTab.createNPCPanel(), null);

		
		tabbedPane.addTab("Locations", null, roomsTab.createLocationPanel(), null);
		
		JPanel eventsBase = new JPanel();
        EventsPanel eventsTab = new EventsPanel(eventsBase);
		tabbedPane.addTab("Events", null, eventsTab.createEventsPanel(), null);
		

        JPanel itemBase = new JPanel();
        tabbedPane.addTab("Items", null, itemTab.createItemPanel(), null);
        
                
		JPanel skillsBase = new JPanel();
        SkillsPanel skillsTab = new SkillsPanel(skillsBase);
		tabbedPane.addTab("Skills", null, skillsTab.createSkillsPanel(), null);
		
	}
	
	public void save() {
		if(currentFile == null) {
			this.saveAs();
		}
		else {
			
			
			String filename = currentFile.getAbsolutePath();
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
	    	try
        	{
			
				String filename = currentFile.getAbsolutePath();
			
            	FileInputStream file = new FileInputStream(filename); 
            	ObjectInputStream in = new ObjectInputStream(file); 
              
            	centralDB = (CentralDB)in.readObject();
            
            	in.close(); 
            	file.close();
            
            	CentralDB.loadIntoCentralDB(centralDB);
            	roomsTab.update();
            	itemTab.update();
              
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

		int userSelection = fileChooser.showSaveDialog(frame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			currentFile = fileChooser.getSelectedFile();
			this.save();
		}
	}
}
