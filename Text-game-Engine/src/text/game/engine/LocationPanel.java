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
	DefaultListModel<String> npclm = new DefaultListModel<String>();
	int selected = -1;
	JTextField[] positionFields = new JTextField[4];
	JCheckBox startScreenChkBox = new JCheckBox("<html>Set as<br>Start Screen</html>");
	JCheckBox fillNPCChkBox = new JCheckBox("Auto-fill NPCs");
	int[] locations = {-1,-1,-1,-1};
	
	ArrayList<Integer> events = new ArrayList<>();
	ArrayList<Integer> npcs = new ArrayList<>();
	JList<String> npcList = new JList<>(npclm);
	JList<String> eventsList = new JList<>(eventlm);
	
	Location sLoc;
	JList<String> locationList = new JList<String>(dlm);
	JTextPane locationName = new JTextPane();
	JTextArea descArea = new JTextArea();
	
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
		
		JLabel roomDesc = new JLabel("<html>Location<br>Description:</html>");
		roomDesc.setBounds(218, 70, 115, 35);
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
		
		startScreenChkBox.setBounds(210, 110, 113, 25);
		base.add(startScreenChkBox);
		
		/*JCheckBox fillItemsChkBox = new JCheckBox("Auto-fill items");
		fillItemsChkBox.setBounds(210, 140, 113, 25);
		base.add(fillItemsChkBox);*/
		
		fillNPCChkBox.setBounds(210, 165, 113, 25);
		base.add(fillNPCChkBox);
		
		JScrollPane eventPane = makeScrollList(eventsList, "Events List");
		eventPane.setBounds(286, 211, 218, 85);
		base.add(eventPane);
		
		JButton removeEventButton = new JButton("Remove Event");
		removeEventButton.setBounds(572, 257, 120, 25);
		removeEventButton.addActionListener(e -> removeEvent());
		base.add(removeEventButton);
		
		JButton addEventButton = new JButton("Add Event");
		addEventButton.setBounds(572, 216, 120, 25);
		addEventButton.addActionListener(e -> addEvent());
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
		
		positionFields[0] = new JTextField();
		positionFields[0].setEditable(false);
		positionFields[0].setBounds(296, 341, 97, 22);
		base.add(positionFields[0]);
		positionFields[0].setColumns(10);
		
		JLabel southLbl = new JLabel("South:");
		southLbl.setBounds(405, 317, 98, 16);
		base.add(southLbl);
		
		positionFields[2] = new JTextField();
		positionFields[2].setEditable(false);
		positionFields[2].setColumns(10);
		positionFields[2].setBounds(405, 341, 97, 22);
		base.add(positionFields[2]);
		
		JLabel eastLbl = new JLabel("East:");
		eastLbl.setBounds(515, 317, 97, 16);
		base.add(eastLbl);
		
		positionFields[1] = new JTextField();
		positionFields[1].setEditable(false);
		positionFields[1].setColumns(10);
		positionFields[1].setBounds(515, 341, 97, 22);
		base.add(positionFields[1]);
		
		JLabel westLbl = new JLabel("West:");
		westLbl.setBounds(624, 317, 97, 16);
		base.add(westLbl);
		
		positionFields[3] = new JTextField();
		positionFields[3].setEditable(false);
		positionFields[3].setColumns(10);
		positionFields[3].setBounds(624, 341, 97, 22);
		base.add(positionFields[3]);
		
		JButton chngNorthButton = new JButton("Change");
		chngNorthButton.setBounds(304, 364, 80, 25);
		chngNorthButton.addActionListener(e->changeLocation(0));
		base.add(chngNorthButton);
		
		JButton chngSouthButton = new JButton("Change");
		chngSouthButton.setBounds(415, 364, 80, 25);
		chngSouthButton.addActionListener(e->changeLocation(2));
		base.add(chngSouthButton);
		
		JButton chngEastButton = new JButton("Change");
		chngEastButton.setBounds(525, 364, 80, 25);
		chngEastButton.addActionListener(e->changeLocation(1));
		base.add(chngEastButton);
		
		JButton chngWestButton = new JButton("Change");
		chngWestButton.setBounds(634, 364, 80, 25);
		chngWestButton.addActionListener(e->changeLocation(3));
		base.add(chngWestButton);
		
		/*JLabel npcLabel = new JLabel("NPCs:");
		npcLabel.setBounds(218, 418, 76, 16);
		base.add(npcLabel);
		
		npcCBox.setBounds(572, 434, 194, 27);
		base.add(npcCBox);
		
		roomNpcCBox.setBounds(310, 434, 194, 27);
		base.add(roomNpcCBox);*/
		
		JScrollPane npcPane = makeScrollList(npcList, "NPC List");
		npcPane.setBounds(286, 416, 218, 95);
		base.add(npcPane);
		
		JButton removeNPC = new JButton("Remove NPC");
		removeNPC.setBounds(572, 472, 113, 25);
		removeNPC.addActionListener(e -> removeNPC());
		base.add(removeNPC);
		
		JButton addNPC = new JButton("Add NPC");
		addNPC.setBounds(572, 431, 113, 25);
		base.add(addNPC);
		addNPC.addActionListener(e -> addNPC());
		
		JLabel npcListLabel = new JLabel("NPCs:");
		npcListLabel.setBounds(218, 418, 56, 16);
		base.add(npcListLabel);
		
		return base;
	}
	
	public JScrollPane makeScrollList(JList list, String name){
        JScrollPane js = new JScrollPane(list);
        js.setBorder(new TitledBorder(null, name, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        return js;
    }
	
	public void addNewLocation()
	{
        dlm.addElement("New Location");
        list.add(new Location());
        CentralDB.locationList = list;
    }

	public void deleteSelectedLocation()
	{
		if(!locationList.isSelectionEmpty()){
            if(locationList.getSelectedIndices().length > 1){
                int[] selection = locationList.getSelectedIndices();
                for(int i = selection.length-1;i>=0;i--){
                    dlm.remove(selection[i]);
                    list.remove(selection[i]);
                    if(selection[i]==selected)
                        selected = -1;
                }
            }
            else{
                int selection = locationList.getSelectedIndex();
                if (selection == selected)
                    selected = -1;
                dlm.remove(selection);
                list.remove(selection);
            }
            CentralDB.locationList = list;
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
		 CentralDB.locationList = list;
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
                descArea.setText(sLoc.getRawDescription());
                if(CentralDB.startScreen.equals(sLoc)) {
                	startScreenChkBox.setSelected(true);
                }
                else {
                	startScreenChkBox.setSelected(false);
                }
                if(sLoc.isAutoFillNpcs())
                	fillNPCChkBox.setSelected(true);
                else
                	fillNPCChkBox.setSelected(false);
                eventlm.clear();
                for(int event: sLoc.getEvents()) {
                	eventlm.addElement(CentralDB.eventList.get(event).getName());
                }
                npclm.clear();
                for(int npc: sLoc.getNPCs()) {
                	npclm.addElement(CentralDB.npcList.get(npc).getName());
                }
                int i = 0;
                for(int location: sLoc.getLocations()) {
                	if(location == -1) {
                		positionFields[i].setText("");
                		locations[i]=-1;
                	}
                	else {
                		positionFields[i].setText(CentralDB.locationList.get(location).getName());
                		locations[i]=location;
                	}
                	i++;
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
        if(fillNPCChkBox.isSelected())
        		created.setAutoFillNpcs(true);
        created.setEvents(events);
        created.setNorth(locations[0]);
        created.setEast(locations[1]);
        created.setSouth(locations[2]);
        created.setWest(locations[3]);
        created.setNPCs(npcs);
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
	
	public void addNPC() {
    	JOptionPane popup = new JOptionPane();
    	JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel nlm = new DefaultListModel();
    	JList npcList = new JList(nlm);
    	for(NPC npc : CentralDB.npcList)
		{
    		nlm.addElement(npc.getName());
		}
    	main.add(this.makeScrollList(npcList, "NPCs"));
        popup.showMessageDialog(main, main);
        int[] selection = npcList.getSelectedIndices();
        for(int i:selection) {
        	npclm.addElement(CentralDB.npcList.get(i).getName());
        	npcs.add(i);
        }
    }
	
	public void removeNPC() {
    	if(!npcList.isSelectionEmpty()){
            if(npcList.getSelectedIndices().length > 1){
                int[] selection = npcList.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	npclm.remove(selection[i]);
                	npcs.remove(selection[i]);
                }
            }
            else{
                int selection = npcList.getSelectedIndex();
                npclm.remove(selection);
                npcs.remove(selection);
            }
        }
    }
	
	public void addEvent() {
    	JOptionPane popup = new JOptionPane();
    	JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel elm = new DefaultListModel();
    	JList eventList = new JList(elm);
    	for(Events event : CentralDB.eventList)
		{
    		elm.addElement(event.getName());
		}
    	main.add(this.makeScrollList(eventList, "Events"));
        popup.showMessageDialog(main, main);
        int[] selection = eventList.getSelectedIndices();
        for(int i:selection) {
        	eventlm.addElement(CentralDB.eventList.get(i).getName());
        	events.add(i);
        }
    }
	
	public void removeEvent() {
    	if(!eventsList.isSelectionEmpty()){
            if(eventsList.getSelectedIndices().length > 1){
                int[] selection = eventsList.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	eventlm.remove(selection[i]);
                	events.remove(selection[i]);
                }
            }
            else{
                int selection = eventsList.getSelectedIndex();
                eventlm.remove(selection);
                events.remove(selection);
            }
        }
    }
	
	public void changeLocation(int position) {
    	JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> locationlm = new DefaultListModel<String>();
    	JList<String> locationList = new JList<String>(locationlm);
    	locationlm.addElement("none");
    	for(Location location : CentralDB.locationList)
		{
    		locationlm.addElement(location.getName());
		}
    	main.add(this.makeScrollList(locationList, "Locations"));
    	JOptionPane.showMessageDialog(main, main);
        if(locationList.getSelectedIndices().length>1) {
        	JOptionPane.showMessageDialog(main, "You can only add a single location to a position");
        }
        else {
        	if(locationList.getSelectedIndex() == 0) {
            	locations[position] = -1;
            	positionFields[position].setText("");
            }
        	else if(locationList.isSelectionEmpty()) {
        		
        	}
        	else {
        		locations[position] = locationList.getSelectedIndex()-1;
        		positionFields[position].setText(CentralDB.locationList.get(locations[position]).getName());
        	}
        }
    }
}
