package text.game.engine;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        upperRight.add(itemDescription);
        JPanel typePanel = new JPanel();
        JRadioButton consumable = new JRadioButton("Consumable");
        typePanel.add(consumable);
        upperRight.add(consumable, BorderLayout.SOUTH);
        rightPanel.add(upperRight);
        
        JPanel bottomRight = new JPanel();
        bottomRight.setLayout(new GridLayout(2,1,10,10));
        
        itemBenefits.setBorder(new TitledBorder(null, "Item Benefits", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JButton addBenefit = new JButton("ADD");
        JButton removeBenefit = new JButton("REMOVE");
        bottomRight.add(itemBenefits);
        
        itemSkills.setBorder(new TitledBorder(null, "Item Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        bottomRight.add(itemSkills);
        JButton addSkill = new JButton("ADD");
        JButton removeSkill = new JButton("REMOVE");
        rightPanel.add(bottomRight);
        
        base.add(rightPanel, BorderLayout.CENTER);
        
        return base;
    }

}
