package text.game.engine;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class EventsPanel extends JPanel
{
	DefaultListModel dlm = new DefaultListModel();
	ArrayList<Events> list = new ArrayList<Events>();
	Events sEvent;
	int selected = -1;
	CentralDB centralDB;
	
	JPanel base;
	JTextField txtEventName = new JTextField();
	JTextArea txtEventDescription = new JTextArea();
	JList eventList = new JList(dlm);
	JComboBox locConCBox = new JComboBox();
	JComboBox itemConCBox = new JComboBox();
	JComboBox NPCConCBox = new JComboBox();
	JComboBox eventConCBox = new JComboBox();
	JList eventSkillList = new JList();
	JTextArea successArea = new JTextArea();
	JTextArea failArea = new JTextArea();
	JTextField DCField = new JTextField();
	JTextArea actionArea = new JTextArea();
	
	public EventsPanel(JPanel base, CentralDB centralDB)
	{
		this.base = base;
		this.centralDB = centralDB;
	}
	
	public JPanel createEventsPanel()
	{
		base.setLayout(null);
		
		txtEventName.setText("Event Name");
		txtEventName.setBounds(228, 30, 246, 20);
		base.add(txtEventName);
		txtEventName.setColumns(10);
				
		txtEventDescription.setText("Event Description");
		txtEventDescription.setBounds(228, 84, 526, 91);
		base.add(txtEventDescription);
				
		eventList.setBorder(new TitledBorder(null, "Event List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eventList.setBounds(12, 43, 194, 433);
		base.add(eventList);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(12, 13, 88, 25);
		base.add(saveButton);
		saveButton.addActionListener(e -> saveEvent());
		
		JButton loadButton = new JButton("Load");
		loadButton.setBounds(118, 13, 88, 25);
		base.add(loadButton);
		loadButton.addActionListener(e -> loadEvent());
		
		JButton addEventButton = new JButton("Add");
		addEventButton.setBounds(12, 486, 88, 25);
		base.add(addEventButton);
		addEventButton.addActionListener(e -> addNewEvent());
		
		JButton deleteEventButton = new JButton("Delete");
		deleteEventButton.setBounds(118, 486, 88, 25);
		base.add(deleteEventButton);
		deleteEventButton.addActionListener(e -> deleteSelectedEvent());
		
		/*JButton btnNewButton = new JButton("Create Event");
		btnNewButton.setBounds(641, 29, 113, 23);
		base.add(btnNewButton);*/
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(218, 69, 548, 2);
		base.add(separator_3);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(218, 188, 548, 2);
		base.add(separator_7);
		
		JCheckBox locConChkBox = new JCheckBox("Location");
		locConChkBox.setBounds(228, 213, 113, 25);
		base.add(locConChkBox);
		
		JCheckBox itemConChkBox = new JCheckBox("Item");
		itemConChkBox.setBounds(380, 213, 55, 25);
		base.add(itemConChkBox);
		
		JCheckBox NPCConChkBox = new JCheckBox("NPC Interaction");
		NPCConChkBox.setBounds(478, 213, 117, 25);
		base.add(NPCConChkBox);
		
		JCheckBox evntConChkBox = new JCheckBox("Other Event");
		evntConChkBox.setBounds(641, 213, 113, 25);
		base.add(evntConChkBox);
				
		locConCBox.setBounds(228, 247, 90, 22);
		base.add(locConCBox);
				
		itemConCBox.setBounds(360, 247, 90, 22);
		base.add(itemConCBox);
				
		NPCConCBox.setBounds(498, 247, 90, 22);
		base.add(NPCConCBox);
				
		eventConCBox.setBounds(641, 247, 90, 22);
		base.add(eventConCBox);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(218, 297, 548, 2);
		base.add(separator_8);
				
		eventSkillList.setBounds(218, 312, 167, 199);
		eventSkillList.setBorder(new TitledBorder(null, "Skill List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		base.add(eventSkillList);
		
		JCheckBox skillCheckBox = new JCheckBox("Allow Skill");
		skillCheckBox.setBounds(393, 327, 113, 25);
		base.add(skillCheckBox);
				
		successArea.setText("Success Description");
		successArea.setBounds(390, 397, 182, 114);
		base.add(successArea);
				
		failArea.setText("Fail Description");
		failArea.setBounds(584, 397, 182, 114);
		base.add(failArea);
				
		DCField.setBounds(476, 362, 30, 22);
		base.add(DCField);
		DCField.setColumns(10);
		
		JLabel DCLabel = new JLabel("Skill Check:");
		DCLabel.setBounds(397, 361, 70, 23);
		base.add(DCLabel);
				
		actionArea.setText("Action Description");
		actionArea.setBounds(520, 309, 234, 75);
		base.add(actionArea);
		
		return base;
	}
	
	public void saveEvent()
	{
		if(selected == -1){
            Events add = new Events();
            if(!txtEventName.getText().equals(""))
            {
                dlm.addElement(txtEventName.getText());
                add.setName(txtEventName.getText());
                list.add(add);
            }
            else
            {
                dlm.addElement("New Location");
                add.setName("New Location");
                list.add(add);
            }
        }
        else
        {
            selected = list.indexOf(sEvent);
            dlm.set(selected, txtEventName.getText());
            sEvent = this.pullData();
            list.set(selected, sEvent);
        }
		centralDB.eventList = list;
	}
	
	public void loadEvent()
	{
        if(!eventList.isSelectionEmpty())
        {
            if(eventList.getSelectedIndices().length > 1)
            {
            }
            else
            {
                selected = eventList.getSelectedIndex();
                sEvent = list.get(selected);
                txtEventName.setText(sEvent.getName());
                txtEventDescription.setText(sEvent.getDescription());
                actionArea.setText(sEvent.getActionDesc());
                successArea.setText(sEvent.getSuccessDesc());
                failArea.setText(sEvent.getFailDesc());
                //this.selectType(sLoc);
            }
        }
    }
	
	public void addNewEvent()
	{
        dlm.addElement("New Event");
        list.add(new Events());
        centralDB.eventList = list;
    }
	
	public void deleteSelectedEvent()
	{
        if(!eventList.isSelectionEmpty())
        {
            int[] selection = eventList.getSelectedIndices();
            dlm.removeRange(selection[0], selection[selection.length-1]);
        }        
    }
	
	public Events pullData()
	{
        Events created = new Events();
        created.setName(txtEventName.getText());
        created.setDescription(txtEventDescription.getText());
        created.setActionDesc(actionArea.getText());
        created.setSuccessDesc(successArea.getText());
        created.setFailDesc(failArea.getText());
        return created;
    }
}
