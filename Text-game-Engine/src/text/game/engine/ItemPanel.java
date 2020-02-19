package text.game.engine;


import javax.swing.*;
import javax.swing.border.TitledBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author darkf
 */
public class ItemPanel extends JPanel{
    
    JPanel base;
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
        itemList.setBounds(10, 10, 195, 500);
        itemList.setBorder(new TitledBorder(null, "Item List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        base.add(itemList);
        
        itemName.setBounds(210, 10, 600, 50);
        itemName.setBorder(new TitledBorder(null, "Item Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        itemName.add(new JTextField(20));
        base.add(itemName);
        
        itemDescription.setBounds(210, 70, 600, 100);
        itemDescription.setBorder(new TitledBorder(null, "Item Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        base.add(itemDescription);
        
        itemBenefits.setBounds(210, 180, 600, 100);
        itemBenefits.setBorder(new TitledBorder(null, "Item Benefits", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        base.add(itemBenefits);
        
        itemSkills.setBounds(210, 290, 600, 100);
        itemSkills.setBorder(new TitledBorder(null, "Item Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        base.add(itemSkills);
        
        
        return base;
    }

}
