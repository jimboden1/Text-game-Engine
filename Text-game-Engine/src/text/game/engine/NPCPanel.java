/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.game.engine;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author darkf
 */
public class NPCPanel{
    
    private JPanel base;
    DefaultListModel dlm = new DefaultListModel();
    JPanel listPanel = new JPanel();
    JList npcList = new JList(dlm);
    JTextField npcName = new JTextField();
    JTextArea npcDescription = new JTextArea();
    JPanel npcType = new JPanel();
    JList npcCommands = new JList();
    JList npcEvents = new JList();
    JList npcSkills = new JList();
    ArrayList<NPC> list = new ArrayList<NPC>();
    
    public NPCPanel(JPanel base){
        this.base = base;
    }
    
    public JPanel createNPCPanel(){
        base.setLayout(new BorderLayout(10,10));
        base.setBorder(new EmptyBorder(10, 10, 10, 10));
        listPanel.setLayout(new BorderLayout(10,10));
        npcList.setBorder(new TitledBorder(null, "NPC List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JScrollPane js=new JScrollPane(npcList);
        js.setVisible(true);
        listPanel.add(npcList, BorderLayout.CENTER);
        JPanel bp = new JPanel(new GridLayout(1,2,10,10));
        JPanel topBP = new JPanel(new GridLayout(1,2,10,10));
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton create = new JButton("Add Item");
        JButton delete = new JButton("Delete");
        create.addActionListener(e -> addNewNPC());
        delete.addActionListener(e -> deleteSelectedNPC());
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
        
        
        npcDescription.setBorder(new TitledBorder(null, "NPC Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        npcDescription.setWrapStyleWord(true);
        upperRight.add(npcDescription);
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new GridLayout(1,4,10,10));
        ButtonGroup itemGroup = new ButtonGroup();
        JRadioButton merchant = new JRadioButton("Merchant");
        itemGroup.add(merchant);
        typePanel.add(merchant);
        JRadioButton enemy = new JRadioButton("Enemy");
        itemGroup.add(enemy);
        typePanel.add(enemy);
        JRadioButton other = new JRadioButton("Other");
        itemGroup.add(other);
        typePanel.add(other);
        upperRight.add(typePanel, BorderLayout.SOUTH);
        rightPanel.add(upperRight);
        
        JPanel bottomRight = new JPanel();
        bottomRight.setLayout(new GridLayout(1,3,10,10));
        
        JPanel command = new JPanel(new BorderLayout(10,10));
        JPanel commandButtons = new JPanel(new GridLayout(1,2,10,10));
        npcCommands.setBorder(new TitledBorder(null, "NPC Commands", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        command.add(npcCommands, BorderLayout.CENTER);
        JButton addCommand = new JButton("ADD");
        commandButtons.add(addCommand);
        JButton removeCommand = new JButton("REMOVE");
        commandButtons.add(removeCommand);
        command.add(commandButtons, BorderLayout.SOUTH);
        bottomRight.add(command);
        
        JPanel event = new JPanel(new BorderLayout(10,10));
        JPanel eventButtons = new JPanel(new GridLayout(1,2,10,10));
        npcEvents.setBorder(new TitledBorder(null, "NPC Events", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        event.add(npcEvents, BorderLayout.CENTER);
        JButton addEvent = new JButton("ADD");
        eventButtons.add(addEvent);
        JButton removeEvent = new JButton("REMOVE");
        eventButtons.add(removeEvent);
        event.add(eventButtons, BorderLayout.SOUTH);
        bottomRight.add(event);
        
        JPanel skill = new JPanel(new BorderLayout(10,10));
        JPanel skillButtons = new JPanel(new GridLayout(1,2,10,10));
        npcSkills.setBorder(new TitledBorder(null, "NPC Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        skill.add(npcSkills, BorderLayout.CENTER);
        JButton addSkill = new JButton("ADD");
        skillButtons.add(addSkill);
        JButton removeSkill = new JButton("REMOVE");
        skillButtons.add(removeSkill);
        skill.add(skillButtons, BorderLayout.SOUTH);
        bottomRight.add(skill);
        rightPanel.add(bottomRight);
        
        
        
        base.add(rightPanel, BorderLayout.CENTER);
        
        return base;
    }
    
    public void addNewNPC(){
        dlm.addElement("New NPC");
        list.add(new NPC());
    }
    
    public void deleteSelectedNPC(){
        if(!npcList.isSelectionEmpty()){
            if(npcList.getSelectedIndices().length > 1){
                int[] selection = npcList.getSelectedIndices();
                dlm.removeRange(selection[0], selection[selection.length-1]);
                for(int i = selection.length-1;i>=0;i--){
                    list.remove(selection[i]);
                }
            }
            else{
                int selection = npcList.getSelectedIndex();
                dlm.remove(selection);
                list.remove(selection);
            }
        }
    }
    
}
