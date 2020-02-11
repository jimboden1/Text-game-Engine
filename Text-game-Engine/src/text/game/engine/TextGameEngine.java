package text.game.engine;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JButton;

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
		frame.setBounds(100, 100, 450, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Creating the tab layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 312);
		frame.getContentPane().add(tabbedPane);
		
		//Player tab
		JPanel playerTab = new JPanel();
		playerTab.setBorder(null);
		tabbedPane.addTab("Player", null, playerTab, null);
		playerTab.setLayout(null);
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(10, 11, 86, 20);
		playerTab.add(txtName);
		txtName.setColumns(10);
		JTextArea txtrDescription = new JTextArea();
		txtrDescription.setText("Description");
		txtrDescription.setBounds(10, 42, 270, 82);
		playerTab.add(txtrDescription);
		
		JTextArea txtrStartingSkillList = new JTextArea();
		txtrStartingSkillList.setText("Starting Skill List");
		txtrStartingSkillList.setBounds(10, 140, 270, 82);
		playerTab.add(txtrStartingSkillList);
		
		//Room Tab
		JPanel roomsTab = new JPanel();
		tabbedPane.addTab("Rooms", null, roomsTab, null);
		
		//Events Tab
		JPanel eventsTab = new JPanel();
		tabbedPane.addTab("Events", null, eventsTab, null);
		eventsTab.setLayout(null);
		
		txtEventName = new JTextField();
		txtEventName.setText("Event Name");
		txtEventName.setBounds(173, 2, 246, 20);
		eventsTab.add(txtEventName);
		txtEventName.setColumns(10);
		
		JTextArea txtrEventDescription = new JTextArea();
		txtrEventDescription.setText("Event Description");
		txtrEventDescription.setBounds(173, 27, 246, 68);
		eventsTab.add(txtrEventDescription);
		
		JTextArea txtrEventConditions = new JTextArea();
		txtrEventConditions.setText("Event Conditions");
		txtrEventConditions.setBounds(173, 101, 246, 60);
		eventsTab.add(txtrEventConditions);
		
		JTextArea txtrEvent = new JTextArea();
		txtrEvent.setText("Event Actions");
		txtrEvent.setBounds(173, 165, 246, 68);
		eventsTab.add(txtrEvent);
		
		JList eventList = new JList();
		eventList.setBorder(new TitledBorder(null, "Event List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eventList.setBounds(0, 2, 163, 282);
		eventsTab.add(eventList);
		
		JButton btnNewButton = new JButton("Create Event");
		btnNewButton.setBounds(236, 244, 97, 23);
		eventsTab.add(btnNewButton);
		
		//Extra Tab
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
	}
}
