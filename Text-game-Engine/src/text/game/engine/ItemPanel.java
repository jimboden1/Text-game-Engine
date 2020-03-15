package text.game.engine;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ItemPanel extends JPanel{
    
    JPanel base;
    JPanel listPanel = new JPanel();
    JList itemList = new JList();
    JPanel itemName = new JPanel();
    JTextArea itemDescription = new JTextArea();
    JPanel itemType = new JPanel();
    JList itemBenefits = new JList();
    JList itemSkills = new JList();
    
    public ItemPanel(JPanel base){
        this.base = base;
    }
    
    public JPanel createItemPanel(){
        base.setLayout(new BorderLayout(10,10));
        base.setBorder(new EmptyBorder(10, 10, 10, 10));
        listPanel.setLayout(new BorderLayout(10,10));
        itemList.setBorder(new TitledBorder(null, "Item List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JScrollPane js=new JScrollPane(itemList);
        js.setVisible(true);
        add(js);
        listPanel.add(itemList, BorderLayout.CENTER);
        JPanel bp = new JPanel();
        JButton create = new JButton("Add Item");
        JButton delete = new JButton("Delete");
        bp.setLayout(new GridLayout(1,2,10,10));
        bp.add(create);
        bp.add(delete);
        listPanel.add(bp, BorderLayout.SOUTH);
        
        base.add(listPanel, BorderLayout.WEST);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2,1,10,10));
        JPanel upperRight = new JPanel();
        upperRight.setLayout(new BorderLayout(10,10));
        
        
        itemName.setLayout(new GridLayout(2,2,10,10));
        itemName.add(new JLabel("Name:"));
        itemName.add(new JLabel("Cost:"));
        itemName.add(new JTextField());
        itemName.add(new JTextField(20));
        upperRight.add(itemName, BorderLayout.NORTH);
        
        
        itemDescription.setBorder(new TitledBorder(null, "Item Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        itemDescription.setWrapStyleWord(true);
        upperRight.add(itemDescription);
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new GridLayout(1,4,10,10));
        ButtonGroup itemGroup = new ButtonGroup();
        JRadioButton consumable = new JRadioButton("Consumable");
        itemGroup.add(consumable);
        typePanel.add(consumable);
        JRadioButton equipment = new JRadioButton("Equipment");
        itemGroup.add(equipment);
        typePanel.add(equipment);
        JRadioButton key = new JRadioButton("Key Item");
        itemGroup.add(key);
        typePanel.add(key);
        upperRight.add(typePanel, BorderLayout.SOUTH);
        rightPanel.add(upperRight);
        
        JPanel bottomRight = new JPanel();
        bottomRight.setLayout(new GridLayout(2,1,10,10));
        
        JPanel benefitPanel = new JPanel(new BorderLayout(10,10));
        JPanel benefitButtons = new JPanel(new GridLayout(2,1,10,10));
        itemBenefits.setBorder(new TitledBorder(null, "Item Benefits", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        benefitPanel.add(itemBenefits, BorderLayout.CENTER);
        JButton addBenefit = new JButton("ADD");
        benefitButtons.add(addBenefit);
        JButton removeBenefit = new JButton("REMOVE");
        benefitButtons.add(removeBenefit);
        benefitPanel.add(benefitButtons, BorderLayout.EAST);
        bottomRight.add(benefitPanel);
        
        JPanel skillsPanel = new JPanel(new BorderLayout(10,10));
        JPanel skillsButtons = new JPanel(new GridLayout(2,1,10,10));
        itemSkills.setBorder(new TitledBorder(null, "Item Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        skillsPanel.add(itemSkills, BorderLayout.CENTER);
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

}
