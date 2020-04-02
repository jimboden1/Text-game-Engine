package text.game.engine;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.*;

public class TextGameEngine {

	private JFrame frame;

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
                JMenuItem saveAs = new JMenuItem("Save As");
                JMenuItem load = new JMenuItem("Load");
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

		
		JPanel roomsBase = new JPanel();
        LocationPanel roomsTab = new LocationPanel(roomsBase, null);
		tabbedPane.addTab("Locations", null, roomsTab.createLocationPanel(), null);
		
		JPanel eventsBase = new JPanel();
        EventsPanel eventsTab = new EventsPanel(eventsBase);
		tabbedPane.addTab("Events", null, eventsTab.createEventsPanel(), null);
		

        JPanel itemBase = new JPanel();
        ItemPanel itemTab = new ItemPanel(itemBase);
        tabbedPane.addTab("Items", null, itemTab.createItemPanel(), null);
        
                
		JPanel skillsBase = new JPanel();
        SkillsPanel skillsTab = new SkillsPanel(skillsBase);
		tabbedPane.addTab("Skills", null, skillsTab.createSkillsPanel(), null);
		
	}
}
