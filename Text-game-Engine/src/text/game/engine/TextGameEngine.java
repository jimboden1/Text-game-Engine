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
import javax.swing.JSeparator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
		frame.setBounds(100, 100, 450, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Creating the tab layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 332);
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
		roomsTab.setLayout(null);
		
		JList roomList = new JList();
		roomList.setBounds(12, 13, 88, 278);
		roomList.setBorder(new TitledBorder(null, "Room List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		roomsTab.add(roomList);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(112, 43, 308, 2);
		roomsTab.add(separator);
		
		JLabel roomNameLabel = new JLabel("Room Name:");
		roomNameLabel.setBounds(112, 0, 88, 45);
		roomsTab.add(roomNameLabel);
		
		JTextField roomNameText = new JTextField();
		roomNameText.setBounds(226, 13, 194, 22);
		roomsTab.add(roomNameText);
		roomNameText.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(112, 168, 308, 2);
		roomsTab.add(separator_1);
		
		JLabel roomDesc = new JLabel("Room Description:");
		roomDesc.setBounds(212, 44, 105, 22);
		roomsTab.add(roomDesc);
		
		JTextArea descArea = new JTextArea();
		descArea.setBounds(112, 72, 308, 83);
		roomsTab.add(descArea);
		descArea.setColumns(10);
		descArea.setLineWrap(true);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(112, 209, 308, 2);
		roomsTab.add(separator_2);
		
		JLabel eventsLabel = new JLabel("Events:");
		eventsLabel.setBounds(112, 168, 56, 43);
		roomsTab.add(eventsLabel);
		
		JComboBox actionsCBox = new JComboBox();
		actionsCBox.setBounds(226, 217, 194, 27);
		roomsTab.add(actionsCBox);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(112, 249, 308, 2);
		roomsTab.add(separator_3);
		
		JLabel actionsLabel = new JLabel("Actions:");
		actionsLabel.setBounds(112, 209, 56, 42);
		roomsTab.add(actionsLabel);
		
		JComboBox eventsCBox = new JComboBox();
		eventsCBox.setBounds(226, 177, 194, 27);
		roomsTab.add(eventsCBox);
		
		JLabel npcLabel = new JLabel("NPCs:");
		npcLabel.setBounds(112, 249, 56, 42);
		roomsTab.add(npcLabel);
		
		JComboBox npcCBox = new JComboBox();
		npcCBox.setBounds(226, 257, 194, 27);
		roomsTab.add(npcCBox);
		
		
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
