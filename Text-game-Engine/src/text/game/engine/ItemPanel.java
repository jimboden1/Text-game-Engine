package text.game.engine;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.PlainDocument;

public class ItemPanel{
    
    JPanel base;
    DefaultListModel<String> dlm = new DefaultListModel<String>();
    DefaultListModel<String> skilllm = new DefaultListModel<String>();
    DefaultListModel<String> benefitlm = new DefaultListModel<String>();
    JPanel listPanel = new JPanel();
    int selected = -1;
    Item sItem;
    JList<String> itemList = new JList<String>(dlm);
    JTextField itemName = new JTextField();
    JTextField itemCost = new JTextField();
    JTextArea itemDescription = new JTextArea();
    JRadioButton consumable = new JRadioButton("Consumable");
    String[] equipmentType = {"Helm","Chest","Arms","Main Hand","Off Hand","Pants","Shoes", "Accessory"};
    JComboBox<String> equipmentBox = new JComboBox<String>(equipmentType);
    JRadioButton equipment = new JRadioButton("Equipment");
    JRadioButton key = new JRadioButton("Key Item");
    JPanel itemType = new JPanel();
    JList<String> itemBenefits = new JList<String>(benefitlm);
    JList<String> itemSkills = new JList<String>(skilllm);
    ArrayList<Item> list = CentralDB.itemList;
    ArrayList<Benefit> benefitsList = new ArrayList<>();
    ArrayList<Integer> skillsList = new ArrayList<>();
    
    public ItemPanel() {
    	base = new JPanel();
    }
    
    public ItemPanel(JPanel base){
        this.base = base;
    }
    
    public JPanel createItemPanel(){
        base.setLayout(new BorderLayout(10,10));
        base.setBorder(new EmptyBorder(10, 10, 10, 10));
        listPanel.setLayout(new BorderLayout(10,10));
        listPanel.add(this.makeScrollList(itemList, "Item List"), BorderLayout.CENTER);
        JPanel bp = new JPanel();
        JPanel topBP = new JPanel(new GridLayout(1,2,10,10));
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton create = new JButton("Add Item");
        JButton delete = new JButton("Delete");
        create.addActionListener(e -> addNewItem());
        delete.addActionListener(e -> deleteSelectedItem());
        load.addActionListener(e -> loadItem());
        save.addActionListener(e -> saveItem());
        topBP.add(save);
        topBP.add(load);
        bp.setLayout(new GridLayout(1,2,10,10));
        bp.add(create);
        bp.add(delete);
        listPanel.add(topBP, BorderLayout.NORTH);
        listPanel.add(bp, BorderLayout.SOUTH);
        
        base.add(listPanel, BorderLayout.WEST);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2,1,10,10));
        JPanel upperRight = new JPanel();
        upperRight.setLayout(new BorderLayout(10,10));
        
        
        JPanel costName = new JPanel(new GridLayout(2,2,10,10));
        costName.add(new JLabel("Name:"));
        costName.add(new JLabel("Cost:"));
        PlainDocument doc = (PlainDocument) itemCost.getDocument();
        doc.setDocumentFilter(new IntFilter());

        costName.add(itemName);
        costName.add(itemCost);
        upperRight.add(costName, BorderLayout.NORTH);
        
        
        itemDescription.setBorder(new TitledBorder(null, "Item Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        itemDescription.setWrapStyleWord(true);
        upperRight.add(itemDescription);
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new GridLayout(1,4,10,10));
        ButtonGroup itemGroup = new ButtonGroup();
        itemGroup.add(consumable);
        typePanel.add(consumable);
        itemGroup.add(equipment);
        typePanel.add(equipment);
        equipmentBox.setSelectedIndex(0);
        typePanel.add(equipmentBox);
        itemGroup.add(key);
        typePanel.add(key);
        upperRight.add(typePanel, BorderLayout.SOUTH);
        rightPanel.add(upperRight);
        
        JPanel bottomRight = new JPanel();
        bottomRight.setLayout(new GridLayout(2,1,10,10));
        
        JPanel benefitPanel = new JPanel(new BorderLayout(10,10));
        JPanel benefitButtons = new JPanel(new GridLayout(2,1,10,10));
        benefitPanel.add(this.makeScrollList(itemBenefits, "Item Benefits"), BorderLayout.CENTER);
        JButton addBenefit = new JButton("ADD");
        addBenefit.addActionListener(e -> addBenefit());
        benefitButtons.add(addBenefit);
        JButton removeBenefit = new JButton("REMOVE");
        removeBenefit.addActionListener(e -> removeBenefit());
        benefitButtons.add(removeBenefit);
        benefitPanel.add(benefitButtons, BorderLayout.EAST);
        bottomRight.add(benefitPanel);
        
        JPanel skillsPanel = new JPanel(new BorderLayout(10,10));
        JPanel skillsButtons = new JPanel(new GridLayout(2,1,10,10));
        skillsPanel.add(this.makeScrollList(itemSkills, "Item Skills"), BorderLayout.CENTER);
        JButton addSkill = new JButton("ADD");
        addSkill.addActionListener(e -> addSkill());
        skillsButtons.add(addSkill);
        JButton removeSkill = new JButton("REMOVE");
        removeSkill.addActionListener(e -> removeSkill());
        skillsButtons.add(removeSkill);
        skillsPanel.add(skillsButtons, BorderLayout.EAST);
        bottomRight.add(skillsPanel);
        rightPanel.add(bottomRight);
        
        base.add(rightPanel, BorderLayout.CENTER);
        
        return base;
    }
    
    
    public void addNewItem(){
        dlm.addElement("New Item");
        list.add(new Item());
        CentralDB.itemList = list;
    }
    
    public void removeFromAll(int item)
    {
    	for (int i = 0; i < CentralDB.player.inventory.size(); i++)
    	{
    		if (CentralDB.player.inventory.get(i) == item)
    		{
    			CentralDB.player.inventory.remove(i);
    			
    			for (int j = 0; j < CentralDB.player.inventory.size(); j++)
    			{
    				if (CentralDB.player.inventory.get(j) > item)
    					CentralDB.player.inventory.set(j, CentralDB.player.inventory.get(j)-1);
    			}
    		}
    	}
    	
    	for (int k = 0; k < CentralDB.npcList.size(); k++)
    	{
    		for (int i = 0; i < CentralDB.npcList.get(k).getItems().size(); i++)
        	{
        		if (CentralDB.npcList.get(k).getItems().get(i) == item)
        		{
        			CentralDB.npcList.get(k).getItems().remove(i);
        			
        			for (int j = 0; j < CentralDB.npcList.get(k).getItems().size(); j++)
        			{
        				if (CentralDB.npcList.get(k).getItems().get(j) > item)
        					CentralDB.npcList.get(k).getItems().set(j, CentralDB.npcList.get(k).getItems().get(j)-1);
        			}
        		}
        	}
    	}    	
    }
    
    public void deleteSelectedItem(){
        if(!itemList.isSelectionEmpty()){
            if(itemList.getSelectedIndices().length > 1){
                int[] selection = itemList.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	dlm.remove(selection[i]);
                    list.remove(selection[i]);
                    if(selection[i] == selected)
                        selected = -1;
                    
                    for (int j = 0; j < selection.length; j++)
                    {
                    	removeFromAll(selection[j]);
                    }
                    
                }
            }
            else{
                int selection = itemList.getSelectedIndex();
                if (selection == selected)
                    selected = -1;
                dlm.remove(selection);
                list.remove(selection);
                removeFromAll(selection);
            }
            CentralDB.itemList = list;
        }
    }
    
    public void saveItem(){
        if(selected == -1){
            Item add = new Item();
            if(!itemName.getText().equals("")){
                dlm.addElement(itemName.getText());
                add.setName(itemName.getText());
                sItem = this.pullData();
                list.add(add);
            }
            else{
                dlm.addElement("New Item");
                add.setName("New Item");
                list.add(add);
            }
        }
        else{
            selected = list.indexOf(sItem);
            dlm.set(selected, itemName.getText());
            sItem = this.pullData();
            list.set(selected, sItem);
        }
        CentralDB.itemList = list;
    }
    public void loadItem(){
        if(!itemList.isSelectionEmpty()){
            if(itemList.getSelectedIndices().length > 1){
                }
            else{
                selected = itemList.getSelectedIndex();
                sItem = list.get(selected);
                itemName.setText(sItem.getName());
                itemDescription.setText(sItem.getDescription());
                itemCost.setText(""+sItem.getCost());
                benefitsList = sItem.getBenefits();
                benefitlm.removeAllElements();
                for(Benefit benefit: benefitsList) {
                	benefitlm.addElement(benefit.attribute + ": " + benefit.modifier);
                }
                skillsList = sItem.getSkills();
                skilllm.removeAllElements();
                for(int skill:skillsList) {
                	skilllm.addElement(CentralDB.skillList.get(skill).getName());
                }
                this.selectType(sItem);
            }
        }
    }
    public Item pullData(){
        Item created = new Item();
        created.setName(itemName.getText());
        try{
            created.setCost(Integer.parseInt(itemCost.getText()));
        }
        catch(Exception e){
        	created.setCost(0);
        }
        created.setDescription(itemDescription.getText());
        created.setType(this.getType());
        created.setBenefits(benefitsList);
        created.setSkills(skillsList);
        return created;
    }
    
    public JScrollPane makeScrollList(JList list, String name){
        JScrollPane js = new JScrollPane(list);
        js.setBorder(new TitledBorder(null, name, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        return js;
    }
    
    public void selectType(Item item){
        int type = item.getType();
        if(type==0){
            consumable.setSelected(true);
        }
        else if(type >= 1 && type!=9){
            equipment.setSelected(true);
            equipmentBox.setSelectedIndex(type-1);
        }
        else{
            key.setSelected(true);
        }
    }
    
    public int getType(){
        if(consumable.isSelected()){
            return 0;
        }
        else if(equipment.isSelected()){
            return 1+equipmentBox.getSelectedIndex();
        }
        else{
            return 9;
        }
    }
    
    public void addBenefit() {
    	JOptionPane popup = new JOptionPane();
    	Benefit benefit = new Benefit();
    	JPanel main = new JPanel(new BorderLayout(10,10));
    	JTextField mod = new JTextField("0");
    	IntFilter.makeIntOnly(mod);
    	String[] stats = {"Strength", "Dexterity", "IQ", "Health Points", "Perception", "Will"};
    	
    	mod.setBorder(new TitledBorder(null, "Modifier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	
        JComboBox<String> statbox = new JComboBox<>(stats);
        
        main.add(mod, BorderLayout.NORTH);
        main.add(statbox, BorderLayout.CENTER);
        
        popup.showMessageDialog(main, main);
        if(mod.getText().isEmpty()) {
        	benefit.modifier = 0;
        }
        else {
            benefit.modifier = Integer.parseInt(mod.getText());
        }
        benefit.attributePlace =statbox.getSelectedIndex();
        benefit.makeAttribute(statbox.getSelectedIndex());
        
        benefitlm.addElement(benefit.attribute + ": " + benefit.modifier);
        benefitsList.add(benefit);
    }
    
    public void removeBenefit() {
    	if(!itemBenefits.isSelectionEmpty()){
            if(itemBenefits.getSelectedIndices().length > 1){
                int[] selection = itemBenefits.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	benefitlm.remove(selection[i]);
                	benefitsList.remove(selection[i]);
                }
            }
            else{
                int selection = itemBenefits.getSelectedIndex();
                benefitlm.remove(selection);
                benefitsList.remove(selection);
            }
        }
    }
    
    public void addSkill() {
    	JOptionPane popup = new JOptionPane();
    	JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel skills = new DefaultListModel();
    	JList skillList = new JList(skills);
    	for(Skill skill : CentralDB.skillList)
		{
    		skills.addElement(skill.getName());
		}
    	main.add(this.makeScrollList(skillList, "Skills"));
        popup.showMessageDialog(main, main);
        int[] selection = skillList.getSelectedIndices();
        for(int i:selection) {
        	if(!skillsList.contains(i)) {
                skilllm.addElement(CentralDB.skillList.get(i).getName());
                skillsList.add(i);
        	}
        }
    }
    
    public void removeSkill() {
    	if(!itemSkills.isSelectionEmpty()){
            if(itemSkills.getSelectedIndices().length > 1){
                int[] selection = itemSkills.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	skilllm.remove(selection[i]);
                	skillsList.remove(selection[i]);
                }
            }
            else{
                int selection = itemSkills.getSelectedIndex();
                skilllm.remove(selection);
                skillsList.remove(selection);
            }
        }
    	
    }
    
    public void update() {
		list = CentralDB.itemList;
		dlm.removeAllElements();
		for(Item item: list) {
			System.out.println(item.getName());
			dlm.addElement(item.getName());
		}
	}
}
