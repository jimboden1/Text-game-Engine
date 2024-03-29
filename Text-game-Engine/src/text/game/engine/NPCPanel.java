
package text.game.engine;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class NPCPanel{
    
    private JPanel base;
    DefaultListModel<String> dlm = new DefaultListModel<>(), itemlm = new DefaultListModel<>(),
            skilllm = new DefaultListModel<>(), eventlm = new DefaultListModel<>();
    public int selected = -1;
    NPC sNPC = new NPC();
    JPanel listPanel = new JPanel();
    JList<String> npcList = new JList<>(dlm);
    JTextField npcName = new JTextField(), strength = new JTextField("0"),
            dexterity = new JTextField("0"), iq = new JTextField("0"),
            health = new JTextField("0"), perception = new JTextField("0"),
            will = new JTextField("0");
    JTextArea npcDescription = new JTextArea();
    JRadioButton other = new JRadioButton("Other"), enemy = new JRadioButton("Enemy"), 
            merchant = new JRadioButton("Merchant");
    JPanel npcType = new JPanel();
    JList<String> npcItems = new JList<>(itemlm), npcEvents = new JList<>(eventlm),
            npcSkills = new JList<>(skilllm);
    JPanel customPanel = new JPanel(new BorderLayout(10,10));
    ArrayList<NPC> list = new ArrayList<NPC>();
    ArrayList<Integer> skillsList = new ArrayList<>();
    ArrayList<Integer> itemList = new ArrayList<>();
    ArrayList<Integer> eventList = new ArrayList<>();
    
    public NPCPanel(){
        this.base = new JPanel();
    }
    
    public NPCPanel(JPanel base){
        this.base = base;
    }
    
    public JPanel createNPCPanel(){
        base.setLayout(new BorderLayout(10,10));
        base.setBorder(new EmptyBorder(10, 10, 10, 10));
        listPanel.setLayout(new BorderLayout(10,10));
        listPanel.add(this.makeScrollList(npcList, "NPC List"), BorderLayout.CENTER);
        JPanel bp = new JPanel(new GridLayout(1,2,10,10));
        JPanel topBP = new JPanel(new GridLayout(1,2,10,10));
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton create = new JButton("Add NPC");
        JButton delete = new JButton("Delete");
        create.addActionListener(e -> addNewNPC());
        delete.addActionListener(e -> deleteSelectedNPC());
        load.addActionListener(e -> loadNPC());
        save.addActionListener(e -> saveNPC());
        topBP.add(save);
        topBP.add(load);
        bp.add(create);
        bp.add(delete);
        listPanel.add(bp, BorderLayout.SOUTH);
        listPanel.add(topBP, BorderLayout.NORTH);
        
        base.add(listPanel, BorderLayout.WEST);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2,1,10,10));
        JPanel upperRight = new JPanel();
        upperRight.setLayout(new BorderLayout(10,10));
        
        npcName.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        upperRight.add(npcName, BorderLayout.NORTH);
        
        JScrollPane js = new JScrollPane(npcDescription);
        js.setBorder(new TitledBorder(null, "NPC Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        npcDescription.setWrapStyleWord(true);
        upperRight.add(js);
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new GridLayout(1,4,10,10));
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(merchant);
        typePanel.add(merchant);
        typeGroup.add(enemy);
        typePanel.add(enemy);
        typeGroup.add(other);
        typePanel.add(other);
        merchant.addActionListener(e-> merchantPanel());
        enemy.addActionListener(e-> enemyPanel());
        other.addActionListener(e->merchantPanel());
        upperRight.add(typePanel, BorderLayout.SOUTH);
        rightPanel.add(upperRight);
        
        JPanel bottomRight = new JPanel();
        bottomRight.setLayout(new GridLayout(1,3,10,10));
        
        JPanel item = new JPanel(new BorderLayout(10,10));
        JPanel itemButtons = new JPanel(new GridLayout(1,2,10,10));
        item.add(this.makeScrollList(npcItems, "NPC Items"), BorderLayout.CENTER);
        JButton addItem = new JButton("ADD");
        addItem.addActionListener(e -> addItem());
        itemButtons.add(addItem);
        JButton removeItem = new JButton("REMOVE");
        removeItem.addActionListener(e-> removeItem());
        itemButtons.add(removeItem);
        item.add(itemButtons, BorderLayout.SOUTH);
        bottomRight.add(item);
        
        JPanel skill = new JPanel(new BorderLayout(10,10));
        JPanel skillButtons = new JPanel(new GridLayout(1,2,10,10));
        skill.add(this.makeScrollList(npcSkills, "NPC Skills"), BorderLayout.CENTER);
        JButton addSkill = new JButton("ADD");
        addSkill.addActionListener(e -> addSkill());
        skillButtons.add(addSkill);
        JButton removeSkill = new JButton("REMOVE");
        removeSkill.addActionListener(e -> removeSkill());
        skillButtons.add(removeSkill);
        skill.add(skillButtons, BorderLayout.SOUTH);
        bottomRight.add(skill);
        rightPanel.add(bottomRight);
        
        bottomRight.add(customPanel);
        
        base.add(rightPanel, BorderLayout.CENTER);
        
        return base;
    }
    
    public void addNewNPC(){
        dlm.addElement("New NPC");
        list.add(new NPC());
        CentralDB.npcList = list;
    }
    
    public void removeFromAll(int npc)
    {    	
    	for (int k = 0; k < CentralDB.locationList.size(); k++)
    	{
    		for (int i = 0; i < CentralDB.locationList.get(k).getNPCs().size(); i++)
        	{
        		if (CentralDB.locationList.get(k).getNPCs().get(i) == npc)
        		{
        			CentralDB.locationList.get(k).getNPCs().remove(i);
        			
        			for (int j = 0; j < CentralDB.locationList.get(k).getNPCs().size(); j++)
        			{
        				if (CentralDB.locationList.get(k).getNPCs().get(j) > npc)
        					CentralDB.locationList.get(k).getNPCs().set(j, CentralDB.locationList.get(k).getNPCs().get(j)-1);
        			}
        		}
        	}
    	}    	
    }
    
    public void deleteSelectedNPC(){
        if(!npcList.isSelectionEmpty()){
            if(npcList.getSelectedIndices().length > 1){
                int[] selection = npcList.getSelectedIndices();
                for(int i = selection.length-1;i>=0;i--){
                    dlm.remove(selection[i]);
                    list.remove(selection[i]);
                    if(selection[i]==selected)
                        selected = -1;
                    
                    for (int j = 0; j < selection.length; j++)
                    	removeFromAll(selection[j]);
                }
            }
            else{
                int selection = npcList.getSelectedIndex();
                if (selection == selected)
                    selected = -1;
                dlm.remove(selection);
                list.remove(selection);
                removeFromAll(selection);
            }
            CentralDB.npcList = list;
        }
    }
    
    public void saveNPC(){
        if(selected == -1){
            NPC add = new NPC();
            if(!npcName.getText().equals("")){
                dlm.addElement(npcName.getText());
                add.setName(npcName.getText());
                list.add(add);
            }
            else{
                dlm.addElement("New NPC");
                add.setName("New NPC");
                list.add(add);
            }
        }
        else{
            selected = list.indexOf(sNPC);
            dlm.set(selected, npcName.getText());
            sNPC = this.pullData();
            list.set(selected, sNPC);
        }
        CentralDB.npcList = list;
    }
    public void loadNPC(){
        if(!npcList.isSelectionEmpty()){
            if(npcList.getSelectedIndices().length > 1){
                }
            else{
                selected = npcList.getSelectedIndex();
                sNPC = list.get(selected);
                npcName.setText(sNPC.getName());
                npcDescription.setText(sNPC.getDescription());
                this.selectType(sNPC);
                if(sNPC.getType()==1) {
                	strength.setText(""+sNPC.getStrength());
                	health.setText(""+sNPC.getHealth());
                	dexterity.setText(""+sNPC.getDexterity());
                	iq.setText(""+sNPC.getIQ());
                	perception.setText(""+sNPC.getPerception());
                	will.setText(""+sNPC.getWill());
                }
                else {
                	eventList = sNPC.getEvents();
                	eventlm.removeAllElements();
                	for(int event: eventList) {
                		eventlm.addElement(CentralDB.eventList.get(event).getName());
                	}
                }
                skillsList = sNPC.getSkills();
                skilllm.removeAllElements();
                for(int skill: skillsList) {
                	skilllm.addElement(CentralDB.skillList.get(skill).getName());
                }
                itemList = sNPC.getItems();
                itemlm.removeAllElements();
                for(int item: itemList) {
                	itemlm.addElement(CentralDB.itemList.get(item).getName());
                }
            }
        }
    }
    public NPC pullData(){
        NPC created = new NPC();
        created.setName(npcName.getText());
        created.setDescription(npcDescription.getText());
        created.setType(this.getType());
        if(created.getType()==1) {
        	created.setStrength(Integer.parseInt(strength.getText()));
        	created.setHealth(Integer.parseInt(health.getText()));
        	created.setDexterity(Integer.parseInt(dexterity.getText()));
        	created.setIQ(Integer.parseInt(iq.getText()));
        	created.setPerception(Integer.parseInt(perception.getText()));
        	created.setWill(Integer.parseInt(will.getText()));
        }
        else {
        	created.setEvents(eventList);
        }
        created.setItems(itemList);
        created.setSkills(skillsList);
        return created;
    }
    
    public JScrollPane makeScrollList(JList<String> list, String name){
        JScrollPane js = new JScrollPane(list);
        js.setBorder(new TitledBorder(null, name, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        return js;
    }
    
    public void selectType(NPC npc){
        int type = npc.getType();
        if(type==0){
            merchant.setSelected(true);
            merchantPanel();
        }
        else if(type == 1){
            enemy.setSelected(true);
            enemyPanel();
        }
        else{
            other.setSelected(true);
            merchantPanel();
        }
    }
    
    public int getType(){
        int type = -1;
        if(merchant.isSelected()){
            type = 0;
        }
        else if(enemy.isSelected()){
            type = 1;
        }
        else{
            type = 2;
        }
        return type;
    }
    
    public void merchantPanel(){
        customPanel.removeAll();
        JPanel eventButtons = new JPanel(new GridLayout(1,2,10,10));
        customPanel.add(this.makeScrollList(npcEvents, "NPC Events"), BorderLayout.CENTER);
        JButton addEvent = new JButton("ADD");
        addEvent.addActionListener(e -> addEvent());
        eventButtons.add(addEvent);
        JButton removeEvent = new JButton("REMOVE");
        removeEvent.addActionListener(e -> removeEvent());
        eventButtons.add(removeEvent);
        customPanel.add(eventButtons, BorderLayout.SOUTH);
        customPanel.revalidate();
        customPanel.repaint();
    }
    public void enemyPanel(){
        customPanel.removeAll();
        JPanel statusPanel = new JPanel(new GridLayout(6,1));
        strength.setBorder(new TitledBorder(null, "Strength", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        IntFilter.makeIntOnly(strength);
        statusPanel.add(strength);
        dexterity.setBorder(new TitledBorder(null, "Dexterity", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        IntFilter.makeIntOnly(dexterity);
        statusPanel.add(dexterity);
        iq.setBorder(new TitledBorder(null, "IQ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        IntFilter.makeIntOnly(iq);
        statusPanel.add(iq);
        health.setBorder(new TitledBorder(null, "Health", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        IntFilter.makeIntOnly(health);
        statusPanel.add(health);
        perception.setBorder(new TitledBorder(null, "Perception", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        IntFilter.makeIntOnly(perception);
        statusPanel.add(perception);
        will.setBorder(new TitledBorder(null, "Will", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        IntFilter.makeIntOnly(will);
        statusPanel.add(will);
        customPanel.add(statusPanel);
        customPanel.revalidate();
        customPanel.repaint();
    }
    
    public void addSkill() {
    	JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> skills = new DefaultListModel<>();
    	JList<String> skillList = new JList<>(skills);
    	for(Skill skill : CentralDB.skillList)
		{
    		skills.addElement(skill.getName());
		}
    	main.add(this.makeScrollList(skillList, "Skills"));
    	JOptionPane.showMessageDialog(main, main);
        int[] selection = skillList.getSelectedIndices();
        for(int i:selection) {
            skilllm.addElement(CentralDB.skillList.get(i).getName());
            skillsList.add(i);
        }
    }
    
    public void removeSkill() {
    	if(!npcSkills.isSelectionEmpty()){
            if(npcSkills.getSelectedIndices().length > 1){
                int[] selection = npcSkills.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	skilllm.remove(selection[i]);
                	skillsList.remove(selection[i]);
                }
            }
            else{
                int selection = npcSkills.getSelectedIndex();
                skilllm.remove(selection);
                skillsList.remove(selection);
            }
        }
    }
    
    public void addItem() {
    	JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> items = new DefaultListModel<>();
    	JList<String> itemsList = new JList<>(items);
    	for(Item item : CentralDB.itemList)
		{
    		items.addElement(item.getName());
		}
    	main.add(this.makeScrollList(itemsList, "Items"));
    	JOptionPane.showMessageDialog(main, main);
        int[] selection = itemsList.getSelectedIndices();
        for(int i:selection) {
            itemlm.addElement(CentralDB.itemList.get(i).getName());
            itemList.add(i);
        }
    }
    
    public void removeItem() {
    	if(!npcItems.isSelectionEmpty()){
            if(npcItems.getSelectedIndices().length > 1){
                int[] selection = npcItems.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	itemlm.remove(selection[i]);
                	itemList.remove(selection[i]);
                }
            }
            else{
                int selection = npcItems.getSelectedIndex();
                itemlm.remove(selection);
                itemList.remove(selection);
            }
        }
    }
    
    public void addEvent() {
    	JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> events = new DefaultListModel<>();
    	JList<String>  eventsList = new JList<>(events);
    	for(Events event : CentralDB.eventList)
		{
    		events.addElement(event.getName() + " " + event.getTarget());
		}
    	main.add(this.makeScrollList(eventsList, "Events"));
    	JOptionPane.showMessageDialog(main, main);
        int[] selection = eventsList.getSelectedIndices();
        for(int i:selection) {
        	Events event = CentralDB.eventList.get(i);
            eventlm.addElement(CentralDB.eventList.get(i).getName() + " " + event.getTarget());
            eventList.add(i);
        }
    }
    
    public void removeEvent() {
    	if(!npcEvents.isSelectionEmpty()){
            if(npcEvents.getSelectedIndices().length > 1){
                int[] selection = npcEvents.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	eventlm.remove(selection[i]);
                	eventList.remove(selection[i]);
                }
            }
            else{
                int selection = npcEvents.getSelectedIndex();
                eventlm.remove(selection);
                eventList.remove(selection);
            }
        }
    }
    
    public void update() {
		list = CentralDB.npcList;
		dlm.removeAllElements();
		for(NPC npc: list) {
			dlm.addElement(npc.getName());
		}
		if(selected!=-1) {
			npcList.setSelectedIndex(selected);
			loadNPC();
		}
	}
}
