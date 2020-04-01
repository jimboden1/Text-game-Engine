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

public class NPCPanel{
    
    private JPanel base;
    DefaultListModel dlm = new DefaultListModel();
    DefaultListModel commandlm = new DefaultListModel();
    DefaultListModel skilllm = new DefaultListModel();
    DefaultListModel eventlm = new DefaultListModel();
    int selected = -1;
    NPC sNPC;
    JPanel listPanel = new JPanel();
    JList npcList = new JList(dlm);
    JTextField npcName = new JTextField();
    JTextField strength, dexterity, iq, health, perception, will = new JTextField();
    JTextArea npcDescription = new JTextArea();
    JRadioButton other = new JRadioButton("Other");
    JRadioButton enemy = new JRadioButton("Enemy");
    JRadioButton merchant = new JRadioButton("Merchant");
    JPanel npcType = new JPanel();
    JList npcItems = new JList();
    JList npcEvents = new JList();
    JList npcSkills = new JList();
    JPanel customPanel = new JPanel(new BorderLayout(10,10));
    ArrayList<NPC> list = new ArrayList<NPC>();
    CentralDB centralDB;
    
    public NPCPanel(JPanel base, CentralDB cDB){
        this.base = base;
        centralDB = cDB;
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
        upperRight.add(typePanel, BorderLayout.SOUTH);
        rightPanel.add(upperRight);
        
        JPanel bottomRight = new JPanel();
        bottomRight.setLayout(new GridLayout(1,3,10,10));
        
        JPanel item = new JPanel(new BorderLayout(10,10));
        JPanel itemButtons = new JPanel(new GridLayout(1,2,10,10));
        item.add(this.makeScrollList(npcItems, "NPC Items"), BorderLayout.CENTER);
        JButton addItem = new JButton("ADD");
        itemButtons.add(addItem);
        JButton removeItem = new JButton("REMOVE");
        itemButtons.add(removeItem);
        item.add(itemButtons, BorderLayout.SOUTH);
        bottomRight.add(item);
        
        JPanel skill = new JPanel(new BorderLayout(10,10));
        JPanel skillButtons = new JPanel(new GridLayout(1,2,10,10));
        skill.add(this.makeScrollList(npcSkills, "NPC Skills"), BorderLayout.CENTER);
        JButton addSkill = new JButton("ADD");
        skillButtons.add(addSkill);
        JButton removeSkill = new JButton("REMOVE");
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
    }
    
    public void deleteSelectedNPC(){
        if(!npcList.isSelectionEmpty()){
            if(npcList.getSelectedIndices().length > 1){
                int[] selection = npcList.getSelectedIndices();
                dlm.removeRange(selection[0], selection[selection.length-1]);
                for(int i = selection.length-1;i>=0;i--){
                    list.remove(selection[i]);
                    if(selection[i]==selected)
                        selected = -1;
                }
            }
            else{
                int selection = npcList.getSelectedIndex();
                if (selection == selected)
                    selected = -1;
                dlm.remove(selection);
                list.remove(selection);
            }
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
            }
        }
    }
    public NPC pullData(){
        NPC created = new NPC();
        created.setName(npcName.getText());
        created.setDescription(npcDescription.getText());
        created.setType(this.getType());
        return created;
    }
    
    public JScrollPane makeScrollList(JList list, String name){
        JScrollPane js = new JScrollPane(list);
        js.setBorder(new TitledBorder(null, name, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        return js;
    }
    
    public void selectType(NPC npc){
        int type = npc.getType();
        if(type==0){
            merchant.setSelected(true);
        }
        else if(type == 1){
            enemy.setSelected(true);
        }
        else{
            other.setSelected(true);
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
        eventButtons.add(addEvent);
        JButton removeEvent = new JButton("REMOVE");
        eventButtons.add(removeEvent);
        customPanel.add(eventButtons, BorderLayout.SOUTH);
        customPanel.revalidate();
        customPanel.repaint();
    }
    public void enemyPanel(){
        customPanel.removeAll();
        JPanel statusPanel = new JPanel(new GridLayout(10,1,10,10));
        customPanel.revalidate();
        customPanel.repaint();
    }
}