
package text.game.engine;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

public class CommandPanel extends JPanel
{
	DefaultListModel dlm = new DefaultListModel();
	DefaultListModel skillslm = new DefaultListModel();
	ArrayList<Command> list = new ArrayList<Command>();
	Command sCommand;
	int selected = -1;
	CentralDB centralDB;
	
	JList skillList = new JList(skillslm);
	ArrayList<Integer> skills = new ArrayList<Integer>();
	int itemCon = -1;
	int locationCon = -1;
	int npcCon = -1;
	
	JTextField itemConField;
	JTextField npcConField;
	JTextField locationConField;
	
	JCheckBox requireSkillCBox;
	
	JPanel base;
	JTextField txtCommandName = new JTextField();
	JTextArea txtCommandDescription = new JTextArea();
	JList commandList = new JList(dlm);
	JComboBox locConCBox = new JComboBox();
	JComboBox itemConCBox = new JComboBox();
	JComboBox NPCConCBox = new JComboBox();
	//JComboBox eventConCBox = new JComboBox();
	JTextArea successArea = new JTextArea();
	JTextArea failArea = new JTextArea();
	JFormattedTextField DCField;
	JTextArea actionArea = new JTextArea();
	
	public CommandPanel()
	{
		base = new JPanel();
	}
	
	/*public CommandPanel(JPanel base, CentralDB centralDB)
	{
		this.base = base;
		this.centralDB = centralDB;
	}*/
	
	public JPanel createCommandPanel()
	{
		base.setLayout(null);
		
		txtCommandName.setText("Command Name");
		txtCommandName.setBounds(228, 30, 246, 20);
		base.add(txtCommandName);
		txtCommandName.setColumns(10);
				
		txtCommandDescription.setText("Command Description");
		txtCommandDescription.setBounds(228, 84, 526, 91);
		base.add(txtCommandDescription);
				
		commandList.setBorder(new TitledBorder(null, "Command List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		commandList.setBounds(12, 43, 194, 433);
		base.add(commandList);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(12, 13, 88, 25);
		base.add(saveButton);
		saveButton.addActionListener(e -> saveCommand());
		
		JButton loadButton = new JButton("Load");
		loadButton.setBounds(118, 13, 88, 25);
		base.add(loadButton);
		loadButton.addActionListener(e -> loadCommand());
		
		JButton addCommandButton = new JButton("Add");
		addCommandButton.setBounds(12, 486, 88, 25);
		base.add(addCommandButton);
		addCommandButton.addActionListener(e -> addNewCommand());
		
		JButton deleteCommandButton = new JButton("Delete");
		deleteCommandButton.setBounds(118, 486, 88, 25);
		base.add(deleteCommandButton);
		deleteCommandButton.addActionListener(e -> deleteSelectedCommand());

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(218, 69, 548, 2);
		base.add(separator_3);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(218, 188, 548, 2);
		base.add(separator_7);
		
		JLabel locationConLabel = new JLabel("Location:");
		locationConLabel.setBounds(340, 205, 97, 16);
		base.add(locationConLabel);
		
		locationConField = new JTextField();
		locationConField.setEditable(false);
		locationConField.setBounds(340, 230, 97, 22);
		base.add(locationConField);
		locationConField.setColumns(10);
		
		JButton chngLocation = new JButton("Change");
		chngLocation.setBounds(340, 263, 97, 25);
		base.add(chngLocation);
		chngLocation.addActionListener(e -> changeLocationCon());
		
		JLabel itemConLbl = new JLabel("Item:");
		itemConLbl.setBounds(450, 205, 98, 16);
		base.add(itemConLbl);
		
		itemConField = new JTextField();
		itemConField.setEditable(false);
		itemConField.setColumns(10);
		itemConField.setBounds(450, 230, 97, 22);
		base.add(itemConField);
		
		JButton chngItem = new JButton("Change");
		chngItem.setBounds(450, 263, 97, 25);
		base.add(chngItem);
		chngItem.addActionListener(e -> changeItemCon());
		
		JLabel npcConLabel = new JLabel("NPC Interaction:");
		npcConLabel.setBounds(560, 205, 97, 16);
		base.add(npcConLabel);
		
		npcConField = new JTextField();
		npcConField.setEditable(false);
		npcConField.setColumns(10);
		npcConField.setBounds(560, 230, 97, 22);
		base.add(npcConField);
		
		JButton chngNPC = new JButton("Change");
		chngNPC.setBounds(560, 263, 97, 25);
		base.add(chngNPC);
		chngNPC.addActionListener(e -> changeNPCCon());
		
		/*JLabel eventConLabel = new JLabel("Other Event:");
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
		chngOtherEvent.addActionListener(e -> changeEventCon());*/
		
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
		
		JCheckBox requireSkillCBox = new JCheckBox("Require Skill");
		requireSkillCBox.setBounds(393, 315, 113, 25);
		base.add(requireSkillCBox);
				
		//https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setAllowsInvalid(false);
		DCField = new JFormattedTextField(formatter);
		DCField.setBounds(476, 350, 30, 22);
		base.add(DCField);
		DCField.setColumns(10);
		
		JLabel DCLabel = new JLabel("Skill Check:");
		DCLabel.setBounds(397, 350, 70, 23);
		base.add(DCLabel);
				
		actionArea.setText("Action Description");
		actionArea.setBounds(520, 309, 234, 75);
		base.add(actionArea);
		
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
	
	/*public void changeEventCon()
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
	}*/

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

	public void saveCommand()
	{
		if(selected == -1){
			Command add = new Command("");
            if(!txtCommandName.getText().equals(""))
            {
                dlm.addElement(txtCommandName.getText());
                add.setCommand(txtCommandName.getText());
                list.add(add);
            }
            else
            {
                dlm.addElement("New Command");
                add.setCommand("New Command");
                list.add(add);
            }
        }
        else
        {
            selected = list.indexOf(sCommand);
            dlm.set(selected, txtCommandName.getText());
            sCommand = this.pullData();
            list.set(selected, sCommand);
        }
		centralDB.commandList = list;
	}
	
	public void loadCommand()
	{
        if(!commandList.isSelectionEmpty())
        {
            if(commandList.getSelectedIndices().length > 1)
            {
            }
            else
            {
                selected = commandList.getSelectedIndex();
                sCommand = list.get(selected);
                txtCommandName.setText(sCommand.getCommand());
                //txtCommandDescription.setText(sCommand.getDescription());
                
                
                skillslm.clear();
                if (requireSkillCBox.isSelected())
               {
                	for (int skill : sCommand.getSkills())
	            	   skillslm.addElement(CentralDB.skillList.get(skill).getName());
                	actionArea.setText(sCommand.getActionDesc());
                    successArea.setText(sCommand.getSuccessDesc());
                    failArea.setText(sCommand.getFailDesc());
               }
               
               if(sCommand.getLocation() == -1)
               {
            	   locationConField.setText("");
            	   locationCon = -1;
               }
               else
               {
            	   locationConField.setText(CentralDB.locationList.get(locationCon).getName());
            	   locationCon = sCommand.getLocation();
               }
               
               if(sCommand.getItem() == -1)
               {
            	   itemConField.setText("");
            	   itemCon = -1;
               }
               else
               {
            	   itemConField.setText(CentralDB.itemList.get(itemCon).getName());
            	   itemCon = sCommand.getItem();
               }
               
               if(sCommand.getNpc() == -1)
               {
            	   npcConField.setText("");
            	   npcCon = -1;
               }
               else
               {
            	   npcConField.setText(CentralDB.npcList.get(npcCon).getName());
            	   npcCon = sCommand.getNpc();
               }
               
               /*if(sEvent.getOtherEvent() == -1)
               {
            	   eventConField.setText("");
            	   eventCon = -1;
               }
               else
               {
            	   eventConField.setText(CentralDB.eventList.get(eventCon).getName());
            	   eventCon = sEvent.getOtherEvent();
               }*/
            }
        }
    }
	
	public void addNewCommand()
	{
        dlm.addElement("New Command");
        list.add(new Command("New Command"));
        centralDB.commandList = list;
    }
	
	public void deleteSelectedCommand()
	{
        if(!commandList.isSelectionEmpty())
        {
            int[] selection = commandList.getSelectedIndices();
            dlm.removeRange(selection[0], selection[selection.length-1]);
        }        
    }
	
	public Command pullData()
	{
		Command created = new Command(txtCommandName.getText());
        created.setCommand(txtCommandName.getText());
        //created.setDescription(txtEventDescription.getText());
        created.setActionDesc(actionArea.getText());
        created.setSuccessDesc(successArea.getText());
        created.setFailDesc(failArea.getText());
        if (DCField.getText().isEmpty())
        	created.setSkillCheck(-1);
        else
        	created.setSkillCheck(Integer.parseInt(DCField.getText()));
        
        created.setLocation(locationCon);
        created.setItem(itemCon);
        created.setNpc(npcCon);
        //created.setOtherEvent(eventCon);
        created.setSkills(skills);
        return created;
    }
}

