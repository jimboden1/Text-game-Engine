package text.game.engine;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class LocationPanel
{
	private JPanel base;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	DefaultListModel<String> eventlm = new DefaultListModel<String>();
	DefaultListModel<String> commandlm = new DefaultListModel<String>();
	DefaultListModel<String> itemlm = new DefaultListModel<String>();
	int selected = -1;
	JCheckBox startScreenChkBox = new JCheckBox("Set as Start Screen");
	
	ArrayList<Item> iList = new ArrayList<Item>();
	JList itemList = new JList(itemlm);
	
	JList eventsList = new JList(eventlm);
	
	Location sLoc;
	JList<String> locationList = new JList<String>(dlm);
	JTextPane locationName = new JTextPane();
	JTextArea descArea = new JTextArea();
	JComboBox<String> eventsCBox = new JComboBox<String>();
	JComboBox<String> haveEventCBox = new JComboBox<String>();
	JComboBox<String> actCBox = new JComboBox<String>();
	JComboBox<String> roomActCBox = new JComboBox<String>();
	JComboBox<String> npcCBox = new JComboBox<String>();
	JComboBox<String> roomNpcCBox = new JComboBox<String>();
	
	ArrayList<Location> list = new ArrayList<Location>();
	CentralDB centralDB;
	
	public LocationPanel()
	{
		base = new JPanel();

	}
	
	public LocationPanel(JPanel base, CentralDB cDB)
	{
		this.base = base;
		centralDB = cDB;
	}
	
	public JPanel createLocationPanel()
	{
		base.setLayout(null);		
		
		locationList.setBounds(12, 43, 194, 433);
		locationList.setBorder(new TitledBorder(null, "Location List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		base.add(locationList);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(12, 13, 88, 25);
		base.add(saveButton);
		saveButton.addActionListener(e -> saveLocation());
		
		JButton loadButton = new JButton("Load");
		loadButton.setBounds(118, 13, 88, 25);
		base.add(loadButton);
		loadButton.addActionListener(e -> loadLocation());
		
		JButton addLocButton = new JButton("Add");
		addLocButton.setBounds(12, 486, 88, 25);
		base.add(addLocButton);
		addLocButton.addActionListener(e -> addNewLocation());
		
		JButton deleteLocButton = new JButton("Delete");
		deleteLocButton.setBounds(118, 486, 88, 25);
		base.add(deleteLocButton);
		deleteLocButton.addActionListener(e -> deleteSelectedLocation());
		
		JSeparator separator = new JSeparator();
		separator.setBounds(218, 62, 548, 2);
		base.add(separator);
		
		JLabel roomNameLabel = new JLabel("Location Name:");
		roomNameLabel.setBounds(218, 13, 88, 36);
		base.add(roomNameLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(218, 201, 548, 2);
		base.add(separator_1);
		
		JLabel roomDesc = new JLabel("Location Description:");
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
		
		locationName.setBounds(334, 13, 432, 36);
		base.add(locationName);
		
		startScreenChkBox.setBounds(210, 105, 113, 25);
		base.add(startScreenChkBox);
		
		JCheckBox fillItemsChkBox = new JCheckBox("Auto-fill items");
		fillItemsChkBox.setBounds(210, 130, 113, 25);
		base.add(fillItemsChkBox);
		
		JCheckBox fillNPCChkBox = new JCheckBox("Auto-fill NPCs");
		fillNPCChkBox.setBounds(210, 155, 113, 25);
		base.add(fillNPCChkBox);
		
		eventsList.setBorder(new TitledBorder(null, "Events List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eventsList.setBounds(286, 211, 218, 85);
		base.add(eventsList);
		
		JButton removeEventButton = new JButton("Remove Event");
		removeEventButton.setBounds(572, 257, 120, 25);
		//removeEventButton.addActionListener(e -> removeEvent());
		base.add(removeEventButton);
		
		JButton addEventButton = new JButton("Add Event");
		addEventButton.setBounds(572, 216, 120, 25);
		//addEventButton.addActionListener(e -> addNewEvent());
		base.add(addEventButton);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(218, 401, 548, 2);
		base.add(separator_6);
		
		JLabel actionsLabel = new JLabel("Locations:");
		actionsLabel.setBounds(218, 315, 76, 16);
		base.add(actionsLabel);
		
		JLabel northLbl = new JLabel("North:");
		northLbl.setBounds(296, 317, 97, 16);
		base.add(northLbl);
		
		JTextField northTxt = new JTextField();
		northTxt.setBounds(296, 341, 97, 22);
		base.add(northTxt);
		northTxt.setColumns(10);
		
		JLabel southLbl = new JLabel("South:");
		southLbl.setBounds(405, 317, 98, 16);
		base.add(southLbl);
		
		JTextField southTxt = new JTextField();
		southTxt.setColumns(10);
		southTxt.setBounds(405, 341, 97, 22);
		base.add(southTxt);
		
		JLabel eastLbl = new JLabel("East:");
		eastLbl.setBounds(515, 317, 97, 16);
		base.add(eastLbl);
		
		JTextField eastTxt = new JTextField();
		eastTxt.setColumns(10);
		eastTxt.setBounds(515, 341, 97, 22);
		base.add(eastTxt);
		
		JLabel westLbl = new JLabel("West:");
		westLbl.setBounds(624, 317, 97, 16);
		base.add(westLbl);
		
		JTextField westTxt = new JTextField();
		westTxt.setColumns(10);
		westTxt.setBounds(624, 341, 97, 22);
		base.add(westTxt);
		
		JButton chngNorthButton = new JButton("Change");
		chngNorthButton.setBounds(304, 364, 80, 25);
		base.add(chngNorthButton);
		
		JButton chngSouthButton = new JButton("Change");
		chngSouthButton.setBounds(415, 364, 80, 25);
		base.add(chngSouthButton);
		
		JButton chngEastButton = new JButton("Change");
		chngEastButton.setBounds(525, 364, 80, 25);
		base.add(chngEastButton);
		
		JButton chngWestButton = new JButton("Change");
		chngWestButton.setBounds(634, 364, 80, 25);
		base.add(chngWestButton);
		
		/*JLabel npcLabel = new JLabel("NPCs:");
		npcLabel.setBounds(218, 418, 76, 16);
		base.add(npcLabel);
		
		npcCBox.setBounds(572, 434, 194, 27);
		base.add(npcCBox);
		
		roomNpcCBox.setBounds(310, 434, 194, 27);
		base.add(roomNpcCBox);*/
		
		JLabel itemsLabel = new JLabel("Items:");
		itemsLabel.setBounds(218, 418, 56, 16);
		base.add(itemsLabel);
		
		itemList.setBounds(286, 416, 218, 95);
		base.add(itemList);
		itemList.setBorder(new TitledBorder(null, "Item List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton removeItem = new JButton("Remove Item");
		removeItem.setBounds(572, 472, 113, 25);
		//removeItem.addActionListener(e -> removeItem());
		base.add(removeItem);
		
		JButton addItem = new JButton("Add Item");
		addItem.setBounds(572, 431, 113, 25);
		base.add(addItem);
		//addItem.addActionListener(e -> addNewItem());
		
		return base;
	}
	
	public void addNewLocation()
	{
        dlm.addElement("New Location");
        list.add(new Location());
    }

	public void deleteSelectedLocation()
	{
        if(!locationList.isSelectionEmpty())
        {
            int[] selection = locationList.getSelectedIndices();
            dlm.removeRange(selection[0], selection[selection.length-1]);
            selected = -1;
        }        
    }
	
	public void saveLocation()
	{
		 if(selected == -1){
	            Location add = new Location();
	            if(!locationName.getText().equals(""))
	            {
	                dlm.addElement(locationName.getText());
	                add.setName(locationName.getText());
	                list.add(add);
	            }
	            else{
	                dlm.addElement("New Location");
	                add.setName("New Location");
	                list.add(add);
	            }
	        }
	        else
	        {
	            selected = list.indexOf(sLoc);
	            dlm.set(selected, locationName.getText());
	            sLoc = this.pullData();
	            list.set(selected, sLoc);
	        }
	}
	
	public void loadLocation()
	{
        if(!locationList.isSelectionEmpty())
        {
            if(locationList.getSelectedIndices().length > 1)
            {
            }
            else
            {
                selected = locationList.getSelectedIndex();
                sLoc = list.get(selected);
                locationName.setText(sLoc.getName());
                descArea.setText(sLoc.getDescription());
                if(CentralDB.startScreen.equals(sLoc)) {
                	startScreenChkBox.setSelected(true);
                }
                else {
                	startScreenChkBox.setSelected(false);
                }
                //this.selectType(sLoc);
            }
        }
    }
	
	public Location pullData()
	{
        Location created = new Location();
        created.setName(locationName.getText());
        created.setDescription(descArea.getText());
        if (startScreenChkBox.isSelected()) {
    		CentralDB.startScreen = created;
        }
        return created;
    }
	
	public void update() {
		list = CentralDB.locationList;
		dlm.removeAllElements();
		for(Location location: list) {
			System.out.println(location.getName());
			dlm.addElement(location.getName());
		}
	}
}
