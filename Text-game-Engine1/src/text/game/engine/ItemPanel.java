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
    DefaultListModel dlm = new DefaultListModel();
    DefaultListModel skilllm = new DefaultListModel();
    DefaultListModel benefitlm = new DefaultListModel();
    JPanel listPanel = new JPanel();
    int selected = -1;
    Item sItem;
    JList itemList = new JList(dlm);
    JTextField itemName = new JTextField();
    JTextField itemCost = new JTextField();
    JTextArea itemDescription = new JTextArea();
    JRadioButton consumable = new JRadioButton("Consumable");
    String[] equipmentType = {"Helm","Chest","Arms","Main Hand","Off Hand","Pants","Shoes", "Accessory"};
    JComboBox equipmentBox = new JComboBox(equipmentType);
    JRadioButton equipment = new JRadioButton("Equipment");
    JRadioButton key = new JRadioButton("Key Item");
    JPanel itemType = new JPanel();
    JList itemBenefits = new JList(benefitlm);
    JList itemSkills = new JList(skilllm);
    ArrayList<Item> list = new ArrayList<Item>();
    CentralDB centralDB;
    
    public ItemPanel(JPanel base, CentralDB cDB){
        this.base = base;
        centralDB = cDB;
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
        benefitButtons.add(addBenefit);
        JButton removeBenefit = new JButton("REMOVE");
        benefitButtons.add(removeBenefit);
        benefitPanel.add(benefitButtons, BorderLayout.EAST);
        bottomRight.add(benefitPanel);
        
        JPanel skillsPanel = new JPanel(new BorderLayout(10,10));
        JPanel skillsButtons = new JPanel(new GridLayout(2,1,10,10));
        skillsPanel.add(this.makeScrollList(itemSkills, "Item Skills"), BorderLayout.CENTER);
        JButton addSkill = new JButton("ADD");
        skillsButtons.add(addSkill);
        JButton removeSkill = new JButton("REMOVE");
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
    }
    
    public void deleteSelectedItem(){
        if(!itemList.isSelectionEmpty()){
            if(itemList.getSelectedIndices().length > 1){
                int[] selection = itemList.getSelectedIndices();
                dlm.removeRange(selection[0], selection[selection.length-1]);
                for(int i = selection.length-1;i>=0;i--){
                    list.remove(selection[i]);
                    if(selection[i]==selected)
                        selected = -1;
                }
            }
            else{
                int selection = itemList.getSelectedIndex();
                if (selection == selected)
                    selected = -1;
                dlm.remove(selection);
                list.remove(selection);
            }
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
            
        }
        created.setDescription(itemDescription.getText());
        created.setType(this.getType());
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
}