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
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;

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
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Creating the tab layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 783, 554);
		frame.getContentPane().add(tabbedPane);
		
		//Player tab
		JPanel playerTab = new JPanel();
		playerTab.setBorder(null);
		tabbedPane.addTab("Player", null, playerTab, null);
		playerTab.setLayout(null);
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(10, 13, 756, 38);
		playerTab.add(txtName);
		txtName.setColumns(10);
		JTextArea txtrDescription = new JTextArea();
		txtrDescription.setText("Description");
		txtrDescription.setBounds(10, 62, 756, 86);
		playerTab.add(txtrDescription);
		
		JList playerSkillsList = new JList();
		playerSkillsList.setBounds(546, 176, 220, 335);
		playerSkillsList.setBorder(new TitledBorder(null, "Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playerTab.add(playerSkillsList);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 161, 756, 2);
		playerTab.add(separator_4);
		
		JEditorPane picPane = new JEditorPane();
		picPane.setBounds(10, 176, 188, 174);
		playerTab.add(picPane);
		
		JTextField strField = new JTextField();
		strField.setBounds(290, 219, 48, 22);
		playerTab.add(strField);
		strField.setColumns(10);
		
		JTextField dexField = new JTextField();
		dexField.setColumns(10);
		dexField.setBounds(290, 254, 48, 22);
		playerTab.add(dexField);
		
		JTextField IQField = new JTextField();
		IQField.setColumns(10);
		IQField.setBounds(290, 289, 48, 22);
		playerTab.add(IQField);
		
		JLabel strLabel = new JLabel("Strength:");
		strLabel.setBounds(210, 219, 56, 16);
		playerTab.add(strLabel);
		
		JLabel dexLabel = new JLabel("Dexterity:");
		dexLabel.setBounds(210, 254, 56, 16);
		playerTab.add(dexLabel);
		
		JLabel IQLabel = new JLabel("IQ:");
		IQLabel.setBounds(210, 289, 56, 16);
		playerTab.add(IQLabel);
		
		JTextField HPField = new JTextField();
		HPField.setColumns(10);
		HPField.setBounds(474, 219, 48, 22);
		playerTab.add(HPField);
		
		JTextField perField = new JTextField();
		perField.setColumns(10);
		perField.setBounds(474, 254, 48, 22);
		playerTab.add(perField);
		
		JTextField willField = new JTextField();
		willField.setColumns(10);
		willField.setBounds(474, 289, 48, 22);
		playerTab.add(willField);
		
		JLabel HPLabel = new JLabel("HP:");
		HPLabel.setBounds(375, 219, 56, 16);
		playerTab.add(HPLabel);
		
		JLabel perLabel = new JLabel("Perception:");
		perLabel.setBounds(375, 254, 70, 16);
		playerTab.add(perLabel);
		
		JLabel willLabel = new JLabel("Will:");
		willLabel.setBounds(375, 289, 56, 16);
		playerTab.add(willLabel);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(210, 324, 324, 2);
		playerTab.add(separator_5);
		
		JLabel statsLabel = new JLabel("Stats:");
		statsLabel.setBounds(341, 176, 40, 16);
		playerTab.add(statsLabel);
		
		JLabel placeholder = new JLabel("put other stuff here");
		placeholder.setBounds(180, 414, 129, 16);
		playerTab.add(placeholder);
		
		//Room Tab
		JPanel roomsTab = new JPanel();
		tabbedPane.addTab("Rooms", null, roomsTab, null);
		roomsTab.setLayout(null);
		
		JList roomList = new JList();
		roomList.setBounds(12, 13, 194, 498);
		roomList.setBorder(new TitledBorder(null, "Room List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		roomsTab.add(roomList);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(218, 62, 548, 2);
		roomsTab.add(separator);
		
		JLabel roomNameLabel = new JLabel("Room Name:");
		roomNameLabel.setBounds(218, 13, 88, 36);
		roomsTab.add(roomNameLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(218, 201, 548, 2);
		roomsTab.add(separator_1);
		
		JLabel roomDesc = new JLabel("Room Description:");
		roomDesc.setBounds(218, 77, 105, 22);
		roomsTab.add(roomDesc);
		
		JTextArea descArea_1 = new JTextArea();
		descArea_1.setBounds(334, 77, 432, 111);
		roomsTab.add(descArea_1);
		descArea_1.setColumns(10);
		descArea_1.setLineWrap(true);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(218, 302, 548, 2);
		roomsTab.add(separator_2);
		
		JLabel eventsLabel = new JLabel("Events:");
		eventsLabel.setBounds(218, 216, 56, 16);
		roomsTab.add(eventsLabel);
		
		JComboBox eventsCBox = new JComboBox();
		eventsCBox.setBounds(572, 232, 194, 27);
		roomsTab.add(eventsCBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(334, 13, 432, 36);
		roomsTab.add(textPane);
		
		JCheckBox fillItemsChkBox = new JCheckBox("Auto-fill items");
		fillItemsChkBox.setBounds(213, 116, 113, 25);
		roomsTab.add(fillItemsChkBox);
		
		JCheckBox fillNPCChkBox = new JCheckBox("Auto-fill NPCs");
		fillNPCChkBox.setBounds(214, 146, 113, 25);
		roomsTab.add(fillNPCChkBox);
		
		JComboBox haveEventCBox = new JComboBox();
		haveEventCBox.setBounds(310, 232, 194, 27);
		roomsTab.add(haveEventCBox);
		
		JLabel roomEventsLabel = new JLabel("In Room:");
		roomEventsLabel.setBounds(379, 214, 56, 16);
		roomsTab.add(roomEventsLabel);
		
		JLabel availableEventsLabel = new JLabel("Available:");
		availableEventsLabel.setBounds(646, 216, 56, 16);
		roomsTab.add(availableEventsLabel);
		
		JButton evntRmvButton = new JButton("Remove");
		evntRmvButton.setBounds(349, 264, 114, 25);
		roomsTab.add(evntRmvButton);
		
		JButton evntAddButton = new JButton("Add");
		evntAddButton.setBounds(629, 264, 97, 25);
		roomsTab.add(evntAddButton);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(218, 401, 548, 2);
		roomsTab.add(separator_6);
		
		JLabel actionsLabel = new JLabel("Actions:");
		actionsLabel.setBounds(218, 315, 56, 16);
		roomsTab.add(actionsLabel);
		
		JComboBox actCBox = new JComboBox();
		actCBox.setBounds(572, 331, 194, 27);
		roomsTab.add(actCBox);
		
		JComboBox roomActCBox = new JComboBox();
		roomActCBox.setBounds(310, 331, 194, 27);
		roomsTab.add(roomActCBox);
		
		JLabel roomActLabel = new JLabel("In Room:");
		roomActLabel.setBounds(379, 313, 56, 16);
		roomsTab.add(roomActLabel);
		
		JLabel availableActLabel = new JLabel("Available:");
		availableActLabel.setBounds(646, 315, 56, 16);
		roomsTab.add(availableActLabel);
		
		JButton remActButton = new JButton("Remove");
		remActButton.setBounds(349, 363, 114, 25);
		roomsTab.add(remActButton);
		
		JButton addActButton = new JButton("Add");
		addActButton.setBounds(629, 363, 97, 25);
		roomsTab.add(addActButton);
		
		JLabel itemsLabel = new JLabel("Items:");
		itemsLabel.setBounds(218, 418, 56, 16);
		roomsTab.add(itemsLabel);
		
		JComboBox itemsCBox = new JComboBox();
		itemsCBox.setBounds(572, 434, 194, 27);
		roomsTab.add(itemsCBox);
		
		JComboBox roomItemsCBox = new JComboBox();
		roomItemsCBox.setBounds(310, 434, 194, 27);
		roomsTab.add(roomItemsCBox);
		
		JLabel roomItemsLabel = new JLabel("In Room:");
		roomItemsLabel.setBounds(379, 416, 56, 16);
		roomsTab.add(roomItemsLabel);
		
		JLabel availableItemsLabel = new JLabel("Available:");
		availableItemsLabel.setBounds(646, 418, 56, 16);
		roomsTab.add(availableItemsLabel);
		
		JButton removeItemsButton = new JButton("Remove");
		removeItemsButton.setBounds(349, 466, 114, 25);
		roomsTab.add(removeItemsButton);
		
		JButton addItemsButton = new JButton("Add");
		addItemsButton.setBounds(629, 466, 97, 25);
		roomsTab.add(addItemsButton);
		
		
		//Events Tab
		JPanel eventsTab = new JPanel();
		tabbedPane.addTab("Events", null, eventsTab, null);
		eventsTab.setLayout(null);
		
		txtEventName = new JTextField();
		txtEventName.setText("Event Name");
		txtEventName.setBounds(228, 30, 246, 20);
		eventsTab.add(txtEventName);
		txtEventName.setColumns(10);
		
		JTextArea txtrEventDescription = new JTextArea();
		txtrEventDescription.setText("Event Description");
		txtrEventDescription.setBounds(228, 84, 526, 91);
		eventsTab.add(txtrEventDescription);
		
		JList eventList = new JList();
		eventList.setBorder(new TitledBorder(null, "Event List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eventList.setBounds(12, 13, 194, 498);
		eventsTab.add(eventList);
		
		JButton btnNewButton = new JButton("Create Event");
		btnNewButton.setBounds(641, 29, 113, 23);
		eventsTab.add(btnNewButton);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(218, 69, 548, 2);
		eventsTab.add(separator_3);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(218, 188, 548, 2);
		eventsTab.add(separator_7);
		
		JCheckBox locConChkBox = new JCheckBox("Location");
		locConChkBox.setBounds(228, 213, 113, 25);
		eventsTab.add(locConChkBox);
		
		JCheckBox itemConChkBox = new JCheckBox("Item");
		itemConChkBox.setBounds(380, 213, 55, 25);
		eventsTab.add(itemConChkBox);
		
		JCheckBox NPCConChkBox = new JCheckBox("NPC Interaction");
		NPCConChkBox.setBounds(478, 213, 117, 25);
		eventsTab.add(NPCConChkBox);
		
		JCheckBox evntConChkBox = new JCheckBox("Other Event");
		evntConChkBox.setBounds(641, 213, 113, 25);
		eventsTab.add(evntConChkBox);
		
		JComboBox locConCBox = new JComboBox();
		locConCBox.setBounds(228, 247, 90, 22);
		eventsTab.add(locConCBox);
		
		JComboBox itemConCBox = new JComboBox();
		itemConCBox.setBounds(360, 247, 90, 22);
		eventsTab.add(itemConCBox);
		
		JComboBox NPCConCBox = new JComboBox();
		NPCConCBox.setBounds(498, 247, 90, 22);
		eventsTab.add(NPCConCBox);
		
		JComboBox eventConCBox = new JComboBox();
		eventConCBox.setBounds(641, 247, 90, 22);
		eventsTab.add(eventConCBox);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(218, 297, 548, 2);
		eventsTab.add(separator_8);
		
		JList eventSkillList = new JList();
		eventSkillList.setBounds(218, 312, 167, 199);
		eventSkillList.setBorder(new TitledBorder(null, "Skill List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eventsTab.add(eventSkillList);
		
		JCheckBox skillCheckBox = new JCheckBox("Allow Skill");
		skillCheckBox.setBounds(393, 327, 113, 25);
		eventsTab.add(skillCheckBox);
		
		JTextArea successArea = new JTextArea();
		successArea.setText("Success Description");
		successArea.setBounds(390, 397, 182, 114);
		eventsTab.add(successArea);
		
		JTextArea failArea = new JTextArea();
		failArea.setText("Fail Description");
		failArea.setBounds(584, 397, 182, 114);
		eventsTab.add(failArea);
		
		JTextField DCField = new JTextField();
		DCField.setBounds(476, 362, 30, 22);
		eventsTab.add(DCField);
		DCField.setColumns(10);
		
		JLabel DCLabel = new JLabel("Skill Check:");
		DCLabel.setBounds(397, 361, 70, 23);
		eventsTab.add(DCLabel);
		
		JTextArea actionArea = new JTextArea();
		actionArea.setText("Action Description");
		actionArea.setBounds(520, 309, 234, 75);
		eventsTab.add(actionArea);
		
		//Skills Tab
		JPanel skillTab = new JPanel();
		tabbedPane.addTab("Skills", null, skillTab, null);
		skillTab.setBounds(218, 312, 167, 199);
		skillTab.setLayout(null);
		
		JList skillsList = new JList();
		skillsList.setBounds(12, 13, 194, 231);
		skillsList.setBorder(new TitledBorder(null, "Skill List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		skillTab.add(skillsList);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(12, 257, 754, 2);
		skillTab.add(separator_9);
		
		JRadioButton strRadButton = new JRadioButton("Strength");
		strRadButton.setBounds(226, 37, 127, 25);
		skillTab.add(strRadButton);
		
		JRadioButton dexRadButton = new JRadioButton("Dexterity");
		dexRadButton.setBounds(226, 67, 127, 25);
		skillTab.add(dexRadButton);
		
		JRadioButton IQRadButton = new JRadioButton("IQ");
		IQRadButton.setBounds(226, 97, 127, 25);
		skillTab.add(IQRadButton);
		
		JRadioButton HPRadButton = new JRadioButton("Health Points");
		HPRadButton.setBounds(226, 127, 127, 25);
		skillTab.add(HPRadButton);
		
		JRadioButton percRadButton = new JRadioButton("Perception");
		percRadButton.setBounds(226, 157, 127, 25);
		skillTab.add(percRadButton);
		
		JRadioButton willRadButton = new JRadioButton("Will");
		willRadButton.setBounds(226, 187, 127, 25);
		skillTab.add(willRadButton);
		
		JLabel sklChoiceLabel = new JLabel("Base Skill:");
		sklChoiceLabel.setBounds(233, 13, 73, 16);
		skillTab.add(sklChoiceLabel);
		
		JTextField textField = new JTextField();
		textField.setBounds(362, 38, 404, 22);
		skillTab.add(textField);
		textField.setColumns(10);
		
		JLabel skillNameLabel = new JLabel("Skill Name:");
		skillNameLabel.setBounds(364, 13, 73, 16);
		skillTab.add(skillNameLabel);
		
		JLabel skillDescLabel = new JLabel("Skill Description:");
		skillDescLabel.setBounds(361, 71, 73, 16);
		skillTab.add(skillDescLabel);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(361, 97, 405, 85);
		skillTab.add(textPane_1);
		
		JLabel modLabel = new JLabel("Modifier:");
		modLabel.setBounds(361, 206, 56, 16);
		skillTab.add(modLabel);
		
		JTextField modField = new JTextField();
		modField.setBounds(429, 203, 32, 22);
		skillTab.add(modField);
		modField.setColumns(10);
		
		JButton addSkillButton = new JButton("Add Skill");
		addSkillButton.setBounds(572, 206, 194, 38);
		skillTab.add(addSkillButton);
	}
}
