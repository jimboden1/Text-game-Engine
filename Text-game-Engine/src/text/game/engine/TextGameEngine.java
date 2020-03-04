package text.game.engine;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.*;

public class TextGameEngine {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtEventName;

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
                frame.getContentPane().setLayout(null);
                JMenuBar menu = new JMenuBar();
                menu.setBounds(0, 0, 784, 22);
                JMenu files = new JMenu("Files");
                JMenuItem save = new JMenuItem("Save");
                JMenuItem load = new JMenuItem("Load");
                JMenuItem create = new JMenuItem("New");
                files.add(save);
                files.add(load);
                files.add(create);
                menu.add(files);
                frame.getContentPane().add(menu);
                
		//Creating the tab layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	tabbedPane.setBounds(0, 22, 784, 639);
		frame.getContentPane().add(tabbedPane);

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
		JPanel playerBase = new JPanel();
        PlayerPanel playerTab = new PlayerPanel(playerBase);
		tabbedPane.addTab("Player", null, playerTab.createPlayerPanel(), null);

		
		JPanel roomsBase = new JPanel();
        RoomsPanel roomsTab = new RoomsPanel(roomsBase);
		tabbedPane.addTab("Rooms", null, roomsTab.createRoomsPanel(), null);
		
		JPanel eventsBase = new JPanel();
        EventsPanel eventsTab = new EventsPanel(eventsBase);
		tabbedPane.addTab("Events", null, eventsTab.createEventsPanel(), null);
		

        JPanel itemBase = new JPanel();
        ItemPanel itemTab = new ItemPanel(itemBase);
		tabbedPane.addTab("Item", null, itemTab.createItemPanel(), null);

		
		JPanel skillsBase = new JPanel();
        SkillsPanel skillsTab = new SkillsPanel(skillsBase);
		tabbedPane.addTab("Skills", null, skillsTab.createSkillsPanel(), null);
		
	}
}
