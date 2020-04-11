package text.game.engine;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.*;

public class TextGameEngine {

	private JFrame frame;
	private String fileLocation =  new File(".").getAbsolutePath();
	private CentralDB centralDB = new CentralDB();
	private LocationPanel roomsTab = new LocationPanel();
    private ItemPanel itemTab = new ItemPanel();
	

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
                files.add(save);
                files.add(saveAs);
                files.add(load);
                files.add(create);
                menu.add(files);
                frame.getContentPane().add(menu, BorderLayout.NORTH);
                
		//Creating the tab layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	tabbedPane.setBounds(0, 22, 784, 639);
		frame.getContentPane().add(tabbedPane);

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
		JPanel playerBase = new JPanel();
        PlayerPanel playerTab = new PlayerPanel(playerBase);
		tabbedPane.addTab("Player", null, playerTab.createPlayerPanel(), null);
                
        JPanel NPCBase = new JPanel();
        NPCPanel npcTab = new NPCPanel(NPCBase);
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
		if(fileLocation.equals(null)) {
			this.saveAs();
		}
		else {
			String filename = "file.ser";
			centralDB.loadOutCentralDB();
			try
		      
			{ 
				centralDB.counter++;
				FileOutputStream file = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(centralDB); 

				out.close();
				file.close();
				System.out.println("Object has been serialized"); 

			}
			catch(IOException ex)
			{
				System.out.println("IOException is caught");
				
			}
		}
	}
	
	public void load() {
		centralDB = null;
		try
        {    
			String filename = "file.ser";
			
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            centralDB = (CentralDB)in.readObject();
            
            in.close(); 
            file.close();
            
            CentralDB.loadIntoCentralDB(centralDB);
            roomsTab.update();
            itemTab.update();
              
            System.out.println("Object has been deserialized ");
			System.out.println("Count: " + centralDB.counter);
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
	
	public void saveAs() {
		
	}
}
