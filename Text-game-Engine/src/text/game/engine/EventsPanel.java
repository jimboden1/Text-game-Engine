
package text.game.engine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

public class EventsPanel extends JPanel
{
	DefaultListModel dlm = new DefaultListModel();
	DefaultListModel skillslm = new DefaultListModel();
	DefaultListModel functionslm = new DefaultListModel();
	ArrayList<Events> list = new ArrayList<Events>();
	final String SETTARGET = "Set Target";
	Events sEvent = new Events();
	int selected = -1;
	JRadioButton command = new JRadioButton("Command"), 
			trigger = new JRadioButton("Conditional"), both = new JRadioButton("Both");
	JList functionList = new JList(functionslm);
	JList skillList = new JList(skillslm);
	ArrayList<Integer> skills = new ArrayList<Integer>();
	int itemCon = -1;
	int locationCon = -1;
	int npcCon = -1;
	int eventCon = -1;
	
	JTextField itemConField;
	JTextField npcConField;
	JTextField eventConField;
	JTextField locationConField;
	JButton extra= new JButton(SETTARGET);
	
	JPanel base = new JPanel();
	JTextField txtEventName = new JTextField();
	JTextArea txtEventDescription = new JTextArea();
	JList eventList = new JList(dlm);
	JTextArea successArea = new JTextArea();
	JTextArea failArea = new JTextArea();
	JFormattedTextField DCField;
	JTextArea actionArea = new JTextArea();
	
	public EventsPanel()
	{
	}
	
	public JPanel createEventsPanel()
	{
		base.setLayout(null);
		
		JPanel name = new JPanel();
		name.setBorder(new TitledBorder(null, "Event Name/Command", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		name.setBounds(220, 10, 246, 50);
		name.add(txtEventName);
		base.add(name);
		txtEventName.setColumns(20);
		JLabel plus = new JLabel("+");
		plus.setBounds(480, 30, 30, 10);
		base.add(plus);
		
		JPanel target = new JPanel(new BorderLayout());
		target.setBorder(new TitledBorder(null, "Target", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		target.setBounds(500, 10, 200, 50);
		target.add(extra, BorderLayout.CENTER);
		extra.addActionListener(e->setTarget());
		base.add(target);
		
		ButtonGroup type = new ButtonGroup();
		type.add(command);
		type.add(trigger);
		type.add(both);
		command.setBounds(220, 80, 100, 20);
		base.add(command);
		trigger.setBounds(320, 80,100,20);
		base.add(trigger);
		both.setBounds(420, 80,100,20);
		base.add(both);

		
				
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
		
		JScrollPane functionScroll = makeScrollList(functionList, "Functions");
		functionScroll.setBounds(220, 120, 500, 100);
		base.add(functionScroll);
		
		JButton add = new JButton("Add");
		add.addActionListener(e->addFunction());
		add.setBounds(220, 230, 70, 30);
		base.add(add);


		JButton remove = new JButton("Remove");
		remove.addActionListener(e->removeFunction());
		remove.setBounds(300,230,70,30);
		base.add(remove);
		/*
		JLabel locationConLabel = new JLabel("Location:");
		locationConLabel.setBounds(293, 205, 97, 16);
		base.add(locationConLabel);
		
		locationConField = new JTextField();
		locationConField.setEditable(false);
		locationConField.setBounds(268, 230, 97, 22);
		base.add(locationConField);
		locationConField.setColumns(10);
		
		JButton chngLocation = new JButton("Change");
		chngLocation.setBounds(268, 263, 97, 25);
		base.add(chngLocation);
		chngLocation.addActionListener(e -> changeLocationCon());
		
		JLabel itemConLbl = new JLabel("Item:");
		itemConLbl.setBounds(410, 205, 98, 16);
		base.add(itemConLbl);
		
		itemConField = new JTextField();
		itemConField.setEditable(false);
		itemConField.setColumns(10);
		itemConField.setBounds(377, 230, 97, 22);
		base.add(itemConField);
		
		JButton chngItem = new JButton("Change");
		chngItem.setBounds(377, 263, 97, 25);
		base.add(chngItem);
		chngItem.addActionListener(e -> changeItemCon());
		
		JLabel npcConLabel = new JLabel("NPC Interaction:");
		npcConLabel.setBounds(489, 205, 97, 16);
		base.add(npcConLabel);
		
		npcConField = new JTextField();
		npcConField.setEditable(false);
		npcConField.setColumns(10);
		npcConField.setBounds(486, 230, 97, 22);
		base.add(npcConField);
		
		JButton chngNPC = new JButton("Change");
		chngNPC.setBounds(486, 263, 97, 25);
		base.add(chngNPC);
		chngNPC.addActionListener(e -> changeNPCCon());
		
		JLabel eventConLabel = new JLabel("Other Event:");
		eventConLabel.setBounds(610, 205, 97, 16);
		base.add(eventConLabel);
		
		eventConField = new JTextField();
		eventConField.setEditable(false);
		eventConField.setColumns(10);
		eventConField.setBounds(595, 230, 97, 22);
		base.add(eventConField);
		
		JButton chngOtherEvent = new JButton("Change");
		chngOtherEvent.setBounds(595, 263, 97, 25);
		base.add(chngOtherEvent);
		chngOtherEvent.addActionListener(e -> changeEventCon());
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(218, 297, 548, 2);
		base.add(separator_8);
				
		JScrollPane skillPane = makeScrollList(skillList, "Allowed Skill List");
		skillPane.setBounds(218, 312, 167, 158);
		base.add(skillPane);
		
		JButton addSkillButton = new JButton("Add");
		addSkillButton.setBounds(236, 483, 57, 25);
		base.add(addSkillButton);
		addSkillButton.addActionListener(e -> addSkill());
				
		JButton removeSkillButton = new JButton("Del");
		removeSkillButton.setBounds(306, 483, 57, 25);
		base.add(removeSkillButton);
		removeSkillButton.addActionListener(e -> removeSkill());
				
		successArea.setText("Success Description");
		successArea.setBounds(390, 397, 182, 114);
		base.add(successArea);
				
		failArea.setText("Fail Description");
		failArea.setBounds(584, 397, 182, 114);
		base.add(failArea);
				
		//https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setAllowsInvalid(false);
		DCField = new JFormattedTextField(formatter);
		DCField.setBounds(476, 338, 30, 22);
		base.add(DCField);
		DCField.setColumns(10);
		
		JLabel DCLabel = new JLabel("Skill Check:");
		DCLabel.setBounds(397, 337, 70, 23);
		base.add(DCLabel);
				
		actionArea.setText("Action Description");
		actionArea.setBounds(520, 309, 234, 75);
		base.add(actionArea);
		*/
		
		return base;
	}
	
	private void changeLocationCon()
	{
		JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> locationlm = new DefaultListModel();
		JList<String> locationList = new JList<String>(locationlm);
		locationlm.addElement("none");
		
		for(Location location : CentralDB.locationList)
			locationlm.addElement(location.getName());
		
		main.add(this.makeScrollList(locationList, "Locations"));
		JOptionPane.showMessageDialog(main, main);
		
		if(locationList.getSelectedIndices().length > 1)
			JOptionPane.showMessageDialog(main, "You can only add one location as a condition");
		else
		{
			if(locationList.getSelectedIndex() == 0)
			{
				locationCon = -1;
				locationConField.setText("");
			}
			else if (locationList.isSelectionEmpty())
			{}	
			else
			{
				locationCon = locationList.getSelectedIndex()-1;
				locationConField.setText(CentralDB.locationList.get(locationCon).getName());
			}
		}
	}
	
	private void changeItemCon() 
	{
		JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> itemlm = new DefaultListModel();
		JList<String> itemList = new JList<String>(itemlm);
		itemlm.addElement("none");
		
		for(Item item : CentralDB.itemList)
			itemlm.addElement(item.getName());
		
		main.add(this.makeScrollList(itemList, "Items"));
		JOptionPane.showMessageDialog(main, main);
		
		if(itemList.getSelectedIndices().length > 1)
			JOptionPane.showMessageDialog(main, "You can only add one item as a condition");
		else
		{
			if(itemList.getSelectedIndex() == 0)
			{
				itemCon = -1;
				itemConField.setText("");
			}
			else if (itemList.isSelectionEmpty())
			{}	
			else
			{
				itemCon = itemList.getSelectedIndex()-1;
				itemConField.setText(CentralDB.itemList.get(itemCon).getName());
			}
		}
	}
	
	public void changeNPCCon()
	{
		JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> npclm = new DefaultListModel();
		JList<String> npcList = new JList<String>(npclm);
		npclm.addElement("none");
		
		for(NPC npc : CentralDB.npcList)
			npclm.addElement(npc.getName());
		
		main.add(this.makeScrollList(npcList, "NPCs"));
		JOptionPane.showMessageDialog(main, main);
		
		if(npcList.getSelectedIndices().length > 1)
			JOptionPane.showMessageDialog(main, "You can only add one NPC as a condition");
		else
		{
			if(npcList.getSelectedIndex() == 0)
			{
				npcCon = -1;
				npcConField.setText("");
			}
			else if (npcList.isSelectionEmpty())
			{}	
			else
			{
				npcCon = npcList.getSelectedIndex()-1;
				npcConField.setText(CentralDB.npcList.get(npcCon).getName());
			}
		}
	}
	
	public void changeEventCon()
	{
		JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> eventlm = new DefaultListModel();
		JList<String> eventList = new JList<String>(eventlm);
		eventlm.addElement("none");
		
		for(Events event : CentralDB.eventList)
			eventlm.addElement(event.getName());
		
		main.add(this.makeScrollList(eventList, "Events"));
		JOptionPane.showMessageDialog(main, main);
		
		if(eventList.getSelectedIndices().length > 1)
			JOptionPane.showMessageDialog(main, "You can only add one Event as a condition");
		else
		{
			if(eventList.getSelectedIndex() == 0)
			{
				eventCon = -1;
				eventConField.setText("");
			}
			else if (eventList.isSelectionEmpty())
			{}	
			else
			{
				eventCon = eventList.getSelectedIndex()-1;
				eventConField.setText(CentralDB.eventList.get(npcCon).getName());
			}
		}
	}

	public JScrollPane makeScrollList(JList list, String name){
        JScrollPane js = new JScrollPane(list);
        js.setBorder(new TitledBorder(null, name, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        return js;
    }
	
	public void addSkill() 
	{
		JOptionPane popup = new JOptionPane();
    	JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel nlm = new DefaultListModel();
		JList skillsList = new JList(nlm);
		for(Skill s : CentralDB.skillList)
		{
			nlm.addElement(s.getName());
		}
		main.add(this.makeScrollList(skillsList, "Skills"));
		popup.showMessageDialog(main, main);
		int[] selection = skillsList.getSelectedIndices();
		for (int i : selection)
		{
			skillslm.addElement(CentralDB.skillList.get(i).getName());
			skills.add(i);
		}
	}
	
	public void removeSkill()
	{
		if(!skillList.isSelectionEmpty())
		{
			if(skillList.getSelectedIndices().length > 1)
			{
				int[] selection = skillList.getSelectedIndices();
				for(int i = selection.length-1; i >= 0; i--)
				{
					skillslm.remove(selection[i]);
					skills.remove(selection[i]);
				}
			}
			else
			{
				int selection = skillList.getSelectedIndex();
				skillslm.remove(selection);
				skills.remove(selection);
			}
		}
	}

	public void saveEvent()
	{ 
		for (Command method : sEvent.methods)
    {
    	System.out.println(method.getCommand());
    }
		if(selected == -1){
            if(!txtEventName.getText().equals(""))
            {
            	if(extra.getText().equalsIgnoreCase(SETTARGET)) {
                    dlm.addElement(txtEventName.getText());
            	}
            	else {
            		dlm.addElement(txtEventName.getText()+" "+extra.getText());
            	}
            	sEvent.setName(txtEventName.getText());
                list.add(sEvent);
            }
            else
            {
                dlm.addElement("New Event");
                sEvent.setName("New Event");
                list.add(sEvent);
            }
        }
        else
        {
            selected = list.indexOf(sEvent);
            if(extra.getText().equalsIgnoreCase(SETTARGET)) {
            	dlm.set(selected, txtEventName.getText());
        	}
        	else {
        		dlm.set(selected, txtEventName.getText()+" "+extra.getText());
        	}
            
            sEvent = this.pullData();
            list.set(selected, sEvent);
        }
		CentralDB.eventList = list;
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
                if(sEvent.getTarget()!="") {
                    extra.setText(sEvent.getTarget());
                }

                skillslm.clear();
                functionslm.clear();
                for (Command method : sEvent.methods)
                {
                	System.out.println(method.getCommand());
                	functionslm.addElement(method.getCommand());
                }
            }
        }
    }
	
	public void addNewEvent()
	{
        dlm.addElement("New Event");
        list.add(new Events());
        CentralDB.eventList = list;
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
        if(!extra.getText().equalsIgnoreCase(SETTARGET)) {
            created.setTarget(extra.getText());
        }
        created.methods=sEvent.methods;
        /*
        created.setActionDesc(actionArea.getText());
        created.setSuccessDesc(successArea.getText());
        created.setFailDesc(failArea.getText());
        System.out.println(DCField.getText());
        if (DCField.getText().isEmpty())
        	created.setSkillCheck(-1);
        else
        	created.setSkillCheck(Integer.parseInt(DCField.getText()));
        
        created.setLocation(locationCon);
        created.setItem(itemCon);
        created.setNpc(npcCon);
        created.setOtherEvent(eventCon);
        created.setSkills(skills);*/
        return created;
    }
	public void setTarget() {
		JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> locationlm = new DefaultListModel<String>();
    	JList<String> locationList = new JList<String>(locationlm);
		DefaultListModel<String> npclm = new DefaultListModel<String>();
    	JList<String> npcList = new JList<String>(locationlm);
    	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	locationlm.addElement("none");
    	for(Location location : CentralDB.locationList)
		{
    		locationlm.addElement(location.getName());
		}
    	tabbedPane.addTab("Locations", this.makeScrollList(locationList, "Locations"));
    	npclm.addElement("none");
    	for(NPC npc : CentralDB.npcList)
		{
    		npclm.addElement(npc.getName());
		}
    	tabbedPane.addTab("NPCs", this.makeScrollList(npcList, "NPCs"));
    	
    	
    	main.add(tabbedPane);
    	JOptionPane.showMessageDialog(main, main);
        if(locationList.getSelectedIndices().length>1||npcList.getSelectedIndices().length>1) {
        	JOptionPane.showMessageDialog(main, "You can only select a single npc or location!");
        }
        else if(!locationList.isSelectionEmpty()) {
        	if(locationList.getSelectedIndex() == 0) {
            	extra.setText(SETTARGET);
            }
        	else if(locationList.isSelectionEmpty()) {
        		
        	}
        	else {
        		extra.setText(CentralDB.locationList.get(locationList.getSelectedIndex()-1).getName());
        	}
        }
	}
	
	public void removeFunction() {
		int[] remove = functionList.getSelectedIndices();
		for(int i=remove.length-1; i>=0;i--) {
			functionslm.remove(i);
			sEvent.methods.remove(i);
		}
	}
	
	public void addFunction() {
		JRadioButton display = new JRadioButton("Display"),
				take = new JRadioButton("Take"), give = new JRadioButton("Give")
				, kill = new JRadioButton("Kill"),
				skill = new JRadioButton("Skill"), item = new JRadioButton("Item");
		JPanel main = new JPanel(new BorderLayout(10,10));
		int before = sEvent.methods.size()-1;
		JList<String> displayList = new JList<>();
		int after;
		JTextArea displayText = new JTextArea();
		JPanel changePanel = new JPanel(new BorderLayout(10,10));
		JPanel buttonPanel = new JPanel(new GridLayout(1,5,10,10));
		ButtonGroup type = new ButtonGroup();
		display.addActionListener(e->displayPanel(changePanel,displayText));
		type.add(display);
		take.addActionListener(e->giveAndTakePanel(changePanel,"Take from Player", displayList,skill,item));
		type.add(take);
		give.addActionListener(e->giveAndTakePanel(changePanel,"Give To Player", displayList,skill,item));
		type.add(give);
		kill.addActionListener(e->killPanel(changePanel));
		type.add(kill);
		buttonPanel.add(display);
		buttonPanel.add(take);
		buttonPanel.add(give);
		buttonPanel.add(kill);
		main.add(buttonPanel, BorderLayout.NORTH);
		main.add(changePanel, BorderLayout.CENTER);
		main.setSize(500, 500);
		main.setPreferredSize(new Dimension(500, 500));
		JOptionPane.showMessageDialog(null, main);
		
		if(display.isSelected()&&!displayText.getText().isEmpty()) {
			sEvent.display(displayText.getText());
		}
		else if(give.isSelected()&&!displayList.isSelectionEmpty()) {
			if(skill.isSelected()) {
				sEvent.giveSkill(displayList.getSelectedIndex());
			}
			else if(item.isSelected()) {
				sEvent.giveItem(displayList.getSelectedIndex());
			}
		}
		else if(take.isSelected()&&!displayList.isSelectionEmpty()) {
			if(skill.isSelected()) {
				sEvent.takeSkill(displayList.getSelectedIndex());
			}
			else if(item.isSelected()) {
				sEvent.takeItem(displayList.getSelectedIndex());
			}
		}
		else if(kill.isSelected()) {
			sEvent.kill();
		}
		after = sEvent.methods.size()-1;
		if(before!=after) {
			String add = sEvent.methods.get(after).getCommand();
			functionslm.addElement(add);
		}
	}
	public void displayPanel(JPanel base,JTextArea displayText) {
		base.removeAll();
		base.setBorder(new TitledBorder(null, "Display:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		displayText.setWrapStyleWord(true);
		JScrollPane js = new JScrollPane(displayText);
		base.add(js);
		base.revalidate();
		base.repaint();
	}
	public void giveAndTakePanel(JPanel base,String label,JList displayList,JRadioButton skill, JRadioButton item) {
		base.removeAll();
		base.setLayout(new BorderLayout(10,10));
		base.setBorder(new TitledBorder(null, label, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JPanel buttonPanel = new JPanel(new GridLayout(1,2,10,10));
		buttonPanel.add(skill);
		buttonPanel.add(item);
		ButtonGroup type = new ButtonGroup();
		DefaultListModel<String> skilllm = new DefaultListModel<String>(),
				itemlm = new DefaultListModel<>();
		for(Item items:CentralDB.itemList) {
			itemlm.addElement(items.getName());
		}
		for(Skill skills:CentralDB.skillList) {
			skilllm.addElement(skills.getName());
		}
		type.add(skill);
		skill.addActionListener(e->{
			displayList.setModel(skilllm);
		});
		type.add(item);
		item.addActionListener(e->{
			displayList.setModel(itemlm);
		});
		JScrollPane js = new JScrollPane(displayList);
		base.add(buttonPanel, BorderLayout.NORTH);
		base.add(js, BorderLayout.CENTER);
		base.revalidate();
		base.repaint();
	}
	
	public void killPanel(JPanel base) {
		base.removeAll();
		base.setBorder(new TitledBorder(null, "Kill", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//base.setLayout(new BorderLayout(10,10));
		base.add(new JLabel("This will kill the Player and end the Game"), BorderLayout.CENTER);
		base.revalidate();
		base.repaint();
	}
}

