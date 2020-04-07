package text.game.engine;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class LocationPanel
{
	private JPanel base;
	DefaultListModel dlm = new DefaultListModel();
	DefaultListModel eventlm = new DefaultListModel();
	DefaultListModel commandlm = new DefaultListModel();
	DefaultListModel itemlm = new DefaultListModel();
	int selected = -1;
	
	ArrayList<Location> list = new ArrayList<Location>();
	CentralDB centralDB;
	
	ArrayList<Item> iList = new ArrayList<Item>();
	ArrayList<Events> eList = new ArrayList<Events>();
	
	Location sLoc;
	JList locationList = new JList(dlm);
	JList itemList = new JList(itemlm);
	JList eventsList = new JList(eventlm);
	JTextPane locationName = new JTextPane();
	JTextArea descArea = new JTextArea();
	JComboBox eventsCBox = new JComboBox(centralDB.eventList.toArray());
	JComboBox haveEventCBox = new JComboBox();
	JComboBox actCBox = new JComboBox(centralDB.commandList.toArray());
	JComboBox roomActCBox = new JComboBox();
	JComboBox itemsCBox = new JComboBox();
	JComboBox roomItemsCBox = new JComboBox();
	
	JFrame addItemFrame = new JFrame();
	JFrame addEventFrame = new JFrame();
	JList addItemList = new JList();
	JList addEventList = new JList();
	
	public LocationPanel(JPanel base, CentralDB cDB)
	{
		this.base = base;
		centralDB = cDB;
	}
	
	public JPanel createLocationPanel()
	{
		base.setLayout(null);
		
		//System.out.println(centralDB.itemList.toArray());
		
		//i/temsCBox.setModel(new DefaultComboBoxModel(centralDB.itemList.toArray()));
		
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
		
		JCheckBox fillItemsChkBox = new JCheckBox("Auto-fill items");
		fillItemsChkBox.setBounds(213, 116, 113, 25);
		base.add(fillItemsChkBox);
		
		JCheckBox fillNPCChkBox = new JCheckBox("Auto-fill NPCs");
		fillNPCChkBox.setBounds(214, 146, 113, 25);
		base.add(fillNPCChkBox);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(218, 401, 548, 2);
		base.add(separator_6);
		
		JLabel actionsLabel = new JLabel("Commands:");
		actionsLabel.setBounds(218, 315, 76, 16);
		base.add(actionsLabel);
		
		actCBox.setBounds(572, 331, 194, 27);
		base.add(actCBox);
		
		roomActCBox.setBounds(310, 331, 194, 27);
		base.add(roomActCBox);
		
		JLabel roomActLabel = new JLabel("In Location:");
		roomActLabel.setBounds(379, 313, 76, 16);
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
		
		itemList.setBounds(286, 416, 218, 95);
		base.add(itemList);
		itemList.setBorder(new TitledBorder(null, "Item List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton removeItem = new JButton("Remove Item");
		removeItem.setBounds(572, 472, 113, 25);
		removeItem.addActionListener(e -> removeItem());
		base.add(removeItem);
		
		JButton addItem = new JButton("Add Item");
		addItem.setBounds(572, 431, 113, 25);
		base.add(addItem);
		addItem.addActionListener(e -> addNewItem());
		
		
		eventsList.setBorder(new TitledBorder(null, "Events List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eventsList.setBounds(286, 211, 218, 85);
		base.add(eventsList);
		
		JButton removeEventButton = new JButton("Remove Event");
		removeEventButton.setBounds(572, 257, 120, 25);
		removeEventButton.addActionListener(e -> removeEvent());
		base.add(removeEventButton);
		
		JButton addEventButton = new JButton("Add Event");
		addEventButton.setBounds(572, 216, 120, 25);
		addEventButton.addActionListener(e -> addNewEvent());
		base.add(addEventButton);
		
		return base;
	}
	
	public void addNewEvent()
	{
		
		addEventFrame.setBounds(100, 100, 400, 300);
		addEventFrame.setLayout(null);		
				
		
		addEventList.setBorder(new TitledBorder(null, "Event List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		addEventList.setBounds(0, 0, 400, 220);
		
		
		String eventString[] = new String[centralDB.eventList.toArray().length];
		for(int i = 0; i < centralDB.eventList.toArray().length; i++)
		{
			eventString[i] = centralDB.eventList.get(i).getName();
		}
		
		addEventList.setListData(eventString);
		addEventFrame.add(addEventList);
		
		JButton addEventButton = new JButton("Add Event");
		addEventButton.setBounds(140, 223, 113, 25);
		addEventFrame.add(addEventButton);
		addEventButton.addActionListener(e -> addEvent(addEventList.getSelectedIndex()));
		
		addEventFrame.setVisible(true);
	}
	
	public void addEvent(int index)
	{
		eventlm.addElement(centralDB.eventList.get(index).getName());
		eList.add(centralDB.eventList.get(index));
		addEventList.removeAll(); 
		addEventFrame.setVisible(false);
	}
	
	public void removeEvent()
	{
		if(!eventsList.isSelectionEmpty())
        {
            int[] selection = eventsList.getSelectedIndices();
            eventlm.removeRange(selection[0], selection[selection.length-1]);
        } 
	}
	
	public void addNewItem()
	{
		
		addItemFrame.setBounds(100, 100, 400, 300);
		addItemFrame.setLayout(null);		
				
		
		addItemList.setBorder(new TitledBorder(null, "Item List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		addItemList.setBounds(0, 0, 400, 220);
		
		
		String itemString[] = new String[centralDB.itemList.toArray().length];
		for(int i = 0; i < centralDB.itemList.toArray().length; i++)
		{
			itemString[i] = centralDB.itemList.get(i).getName();
		}
		
		addItemList.setListData(itemString);
		addItemFrame.add(addItemList);
		
		JButton addItemButton = new JButton("Add Item");
		addItemButton.setBounds(140, 223, 113, 25);
		addItemFrame.add(addItemButton);
		addItemButton.addActionListener(e -> addItem(addItemList.getSelectedIndex()));
		
		addItemFrame.setVisible(true);
	}
	
	public void addItem(int index)
	{
		itemlm.addElement(centralDB.itemList.get(index).getName());
		sLoc.setItems(iList);
		addItemList.removeAll();
		addItemFrame.setVisible(false);
	}
	
	public void removeItem()
	{
		if(!itemList.isSelectionEmpty())
        {
            int[] selection = itemList.getSelectedIndices();
            itemlm.removeRange(selection[0], selection[selection.length-1]);
        } 
	}
	
	public void addNewLocation()
	{
        dlm.addElement("New Location");
        list.add(new Location());
        itemList.removeAll();
        centralDB.locationList = list;
    }

	public void deleteSelectedLocation()
	{
        if(!locationList.isSelectionEmpty())
        {
            int[] selection = locationList.getSelectedIndices();
            dlm.removeRange(selection[0], selection[selection.length-1]);
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
	                add.setItems(null);
	                list.add(add);
	            }
	            else
	            {
	                dlm.addElement("New Location");
	                add.setName("New Location");
	                add.setItems(null);
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
		 centralDB.locationList = list;
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
                itemList.removeAll();
                sLoc = list.get(selected);
                locationName.setText(sLoc.getName());
                descArea.setText(sLoc.getDescription());
                System.out.println(sLoc.getItems().toArray());
                if(sLoc.getItems() != null)
                	itemList.setListData(sLoc.getItems().toArray());
                //this.selectType(sLoc);
            }
        }
    }
	
	public Location pullData()
	{
        Location created = new Location();
        created.setName(locationName.getText());
        created.setDescription(descArea.getText());
        created.setItems(iList);
        return created;
    }
}
