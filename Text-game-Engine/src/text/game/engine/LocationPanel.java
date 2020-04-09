package text.game.engine;


import java.awt.BorderLayout;
import java.awt.GridLayout;
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
	JCheckBox startScreenChkBox = new JCheckBox("Set as Start Screen");
	
	Location sLoc;
	JList locationList = new JList(dlm);
	JTextPane locationName = new JTextPane();
	JTextArea descArea = new JTextArea();
	JComboBox eventsCBox = new JComboBox();
	JComboBox haveEventCBox = new JComboBox();
	JComboBox actCBox = new JComboBox();
	JComboBox roomActCBox = new JComboBox();
	JComboBox itemsCBox = new JComboBox();
	JComboBox roomItemsCBox = new JComboBox();
	
	ArrayList<Location> list = new ArrayList<Location>();
	CentralDB centralDB;
	
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
		
		eventsCBox.setBounds(572, 232, 194, 27);
		base.add(eventsCBox);
		
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
		
		haveEventCBox.setBounds(310, 232, 194, 27);
		base.add(haveEventCBox);
		
		JLabel roomEventsLabel = new JLabel("In Location:");
		roomEventsLabel.setBounds(379, 214, 76, 16);
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
		
		itemsCBox.setBounds(572, 434, 194, 27);
		base.add(itemsCBox);
		
		roomItemsCBox.setBounds(310, 434, 194, 27);
		base.add(roomItemsCBox);
		
		JLabel roomItemsLabel = new JLabel("In Location:");
		roomItemsLabel.setBounds(379, 416, 76, 16);
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
}
