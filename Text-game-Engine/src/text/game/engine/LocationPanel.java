package text.game.engine;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;


public class LocationPanel extends JPanel
{
	JPanel base;
	JList locationList = new JList();
	JTextArea descArea = new JTextArea();
	JComboBox eventsCBox = new JComboBox();
	JComboBox haveEventCBox = new JComboBox();
	JComboBox actCBox = new JComboBox();
	JComboBox roomActCBox = new JComboBox();
	JComboBox itemsCBox = new JComboBox();
	JComboBox roomItemsCBox = new JComboBox();
	
	public LocationPanel(JPanel base)
	{
		this.base = base;
	}
	
	public JPanel createLocationPanel()
	{
		base.setLayout(null);		
		
		locationList.setBounds(12, 13, 194, 498);
		locationList.setBorder(new TitledBorder(null, "Room List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		base.add(locationList);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(218, 62, 548, 2);
		base.add(separator);
		
		JLabel roomNameLabel = new JLabel("Room Name:");
		roomNameLabel.setBounds(218, 13, 88, 36);
		base.add(roomNameLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(218, 201, 548, 2);
		base.add(separator_1);
		
		JLabel roomDesc = new JLabel("Room Description:");
		roomDesc.setBounds(218, 77, 105, 22);
		base.add(roomDesc);
				
		descArea.setBounds(334, 77, 432, 111);
		base.add(descArea);
		descArea.setColumns(10);
		descArea.setLineWrap(true);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(218, 302, 548, 2);
		base.add(separator_2);
		
		JLabel eventsLabel = new JLabel("Events:");
		eventsLabel.setBounds(218, 216, 56, 16);
		base.add(eventsLabel);
		
		eventsCBox.setBounds(572, 232, 194, 27);
		base.add(eventsCBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(334, 13, 432, 36);
		base.add(textPane);
		
		JCheckBox fillItemsChkBox = new JCheckBox("Auto-fill items");
		fillItemsChkBox.setBounds(213, 116, 113, 25);
		base.add(fillItemsChkBox);
		
		JCheckBox fillNPCChkBox = new JCheckBox("Auto-fill NPCs");
		fillNPCChkBox.setBounds(214, 146, 113, 25);
		base.add(fillNPCChkBox);
		
		haveEventCBox.setBounds(310, 232, 194, 27);
		base.add(haveEventCBox);
		
		JLabel roomEventsLabel = new JLabel("In Room:");
		roomEventsLabel.setBounds(379, 214, 56, 16);
		base.add(roomEventsLabel);
		
		JLabel availableEventsLabel = new JLabel("Available:");
		availableEventsLabel.setBounds(646, 216, 56, 16);
		base.add(availableEventsLabel);
		
		JButton evntRmvButton = new JButton("Remove");
		evntRmvButton.setBounds(349, 264, 114, 25);
		base.add(evntRmvButton);
		
		JButton evntAddButton = new JButton("Add");
		evntAddButton.setBounds(629, 264, 97, 25);
		base.add(evntAddButton);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(218, 401, 548, 2);
		base.add(separator_6);
		
		JLabel actionsLabel = new JLabel("Actions:");
		actionsLabel.setBounds(218, 315, 56, 16);
		base.add(actionsLabel);
		
		actCBox.setBounds(572, 331, 194, 27);
		base.add(actCBox);
		
		roomActCBox.setBounds(310, 331, 194, 27);
		base.add(roomActCBox);
		
		JLabel roomActLabel = new JLabel("In Room:");
		roomActLabel.setBounds(379, 313, 56, 16);
		base.add(roomActLabel);
		
		JLabel availableActLabel = new JLabel("Available:");
		availableActLabel.setBounds(646, 315, 56, 16);
		base.add(availableActLabel);
		
		JButton remActButton = new JButton("Remove");
		remActButton.setBounds(349, 363, 114, 25);
		base.add(remActButton);
		
		JButton addActButton = new JButton("Add");
		addActButton.setBounds(629, 363, 97, 25);
		base.add(addActButton);
		
		JLabel itemsLabel = new JLabel("Items:");
		itemsLabel.setBounds(218, 418, 56, 16);
		base.add(itemsLabel);
		
		itemsCBox.setBounds(572, 434, 194, 27);
		base.add(itemsCBox);
		
		roomItemsCBox.setBounds(310, 434, 194, 27);
		base.add(roomItemsCBox);
		
		JLabel roomItemsLabel = new JLabel("In Room:");
		roomItemsLabel.setBounds(379, 416, 56, 16);
		base.add(roomItemsLabel);
		
		JLabel availableItemsLabel = new JLabel("Available:");
		availableItemsLabel.setBounds(646, 418, 56, 16);
		base.add(availableItemsLabel);
		
		JButton removeItemsButton = new JButton("Remove");
		removeItemsButton.setBounds(349, 466, 114, 25);
		base.add(removeItemsButton);
		
		JButton addItemsButton = new JButton("Add");
		addItemsButton.setBounds(629, 466, 97, 25);
		base.add(addItemsButton);
		
		return base;
	}
}
