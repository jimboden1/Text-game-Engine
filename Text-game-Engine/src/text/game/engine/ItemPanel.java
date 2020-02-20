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
        base.setLayout(null);
        listPanel.setBounds(10, 10, 195, 500);
        listPanel.setLayout(new BorderLayout());
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
        
        base.add(listPanel);
        
        itemName.setBounds(210, 10, 600, 50);
        itemName.setLayout(new GridLayout(2,2,10,10));
        itemName.add(new JLabel("Name:"));
        itemName.add(new JLabel("Cost:"));
        itemName.add(new JTextField(20));
        itemName.add(new JTextField(20));
        base.add(itemName);
        
        itemDescription.setBounds(210, 70, 600, 100);
        itemDescription.setBorder(new TitledBorder(null, "Item Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        base.add(itemDescription);
        
        itemBenefits.setBounds(210, 180, 600, 100);
        itemBenefits.setBorder(new TitledBorder(null, "Item Benefits", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        base.add(itemBenefits);
        
        itemBenefits.setBounds(210, 180, 600, 100);
        itemBenefits.setBorder(new TitledBorder(null, "Item Benefits", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        base.add(itemBenefits);
        
        itemSkills.setBounds(210, 290, 600, 100);
        itemSkills.setBorder(new TitledBorder(null, "Item Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        base.add(itemSkills);
        
        
        return base;
    }

}
