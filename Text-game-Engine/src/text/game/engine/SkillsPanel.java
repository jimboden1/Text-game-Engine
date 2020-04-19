package text.game.engine;


import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SkillsPanel
{
	JPanel base;
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> skillsList = new JList<String>(listModel);
	JTextField textField = new JTextField();
	JTextPane textPane_1 = new JTextPane();
	JTextField modField = new JTextField();
        JRadioButton strRadButton = new JRadioButton("Strength");
        JRadioButton dexRadButton = new JRadioButton("Dexterity");
        JRadioButton iqRadButton = new JRadioButton("IQ");
        JRadioButton hpRadButton = new JRadioButton("Health Points");
        JRadioButton percRadButton = new JRadioButton("Perception");
        JRadioButton willRadButton = new JRadioButton("Will");
        ArrayList<Skill> skillList= new ArrayList<Skill>();
        int selected = -1;
        Skill sSkill;
        
	public SkillsPanel()
	{
		this.base = new JPanel();
	}
	
	public JPanel createSkillsPanel()
	{
		base.setLayout(null);
		JButton addSkill= new JButton("Add New");
		JButton saveSkill= new JButton("Save");
		JButton loadSkill= new JButton("Load");
		JButton deleteSkill = new JButton("Delete");

		saveSkill.setBounds(10,10,90,30);
		saveSkill.addActionListener(e->saveSkill());
		base.add(saveSkill);
		
		addSkill.setBounds(10,485,90,30);
		addSkill.addActionListener(e->addNewSkill());
		base.add(addSkill);
		
		loadSkill.setBounds(110,10,90,30);
		loadSkill.addActionListener(e->loadSkill());
		base.add(loadSkill);
		
		deleteSkill.setBounds(110,485,90,30);
		deleteSkill.addActionListener(e->deleteSelectedSkill());
		base.add(deleteSkill);
		
		JScrollPane js = new JScrollPane(skillsList);
		js.setBounds(10, 50, 195, 430);
		js.setBorder(new TitledBorder(null, "Skill List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		base.add(js);
		
		
                ButtonGroup statGroup = new ButtonGroup();
                
                statGroup.add(strRadButton);
		strRadButton.setBounds(230, 37, 127, 25);
		base.add(strRadButton);
		
                statGroup.add(dexRadButton);
		dexRadButton.setBounds(230, 67, 127, 25);
		base.add(dexRadButton);
		
                statGroup.add(iqRadButton);
		iqRadButton.setBounds(230, 97, 127, 25);
		base.add(iqRadButton);
		
                statGroup.add(hpRadButton);
		hpRadButton.setBounds(230, 127, 127, 25);
		base.add(hpRadButton);
		
                statGroup.add(percRadButton);
		percRadButton.setBounds(230, 157, 127, 25);
		base.add(percRadButton);
		
                statGroup.add(willRadButton);
		willRadButton.setBounds(230, 187, 127, 25);
		base.add(willRadButton);
		
		JLabel sklChoiceLabel = new JLabel("Base Skill:");
		sklChoiceLabel.setBounds(233, 13, 73, 16);
		base.add(sklChoiceLabel);
		
		textField.setBounds(362, 38, 404, 22);
		base.add(textField);
		textField.setColumns(10);
		
		JLabel skillNameLabel = new JLabel("Skill Name:");
		skillNameLabel.setBounds(364, 13, 73, 16);
		base.add(skillNameLabel);
		
		JLabel skillDescLabel = new JLabel("Skill Description:");
		skillDescLabel.setBounds(361, 71, 73, 16);
		base.add(skillDescLabel);
				
		textPane_1.setBounds(361, 97, 405, 85);
		base.add(textPane_1);
		
		JLabel modLabel = new JLabel("Modifier:");
		modLabel.setBounds(230, 217, 56, 16);
		base.add(modLabel);
		
		IntFilter.makeIntOnly(modField);
		modField.setBounds(230, 233, 50, 22);
		base.add(modField);
		modField.setColumns(10);
		
		return base;
	}
	
	public Skill pullData() {

		Skill skill = new Skill();
		String name, description,type;
		name = textField.getText();
		description = textPane_1.getText();
		if(strRadButton.isSelected())
			type = "strength";
		else if(dexRadButton.isSelected())
			type = "dexterity";
		else if(iqRadButton.isSelected())
			type = "iq";
		else if(hpRadButton.isSelected())
			type = "health";
		else if(percRadButton.isSelected())
			type = "perception";
		else
			type = "will";
		skill.setName(name);
		skill.setDescription(description);
		skill.setType(type);
		skill.setModifier(Integer.parseInt(modField.getText()));

		return skill;
	}
	
	public void saveSkill(){
        if(selected == -1){
            Skill add = new Skill();
            if(!textField.getText().equals("")){
            	listModel.addElement(textField.getText());
                add.setName(textField.getText());
                sSkill = this.pullData();
                skillList.add(add);
            }
            else{
            	listModel.addElement("New Skill");
                add.setName("New Skill");
                skillList.add(add);
            }
        }
        else{
            selected = skillList.indexOf(sSkill);
            listModel.set(selected, textField.getText());
            sSkill = this.pullData();
            skillList.set(selected, sSkill);
        }
        CentralDB.skillList = skillList;
    }
	
	public void addNewSkill() {
		Skill add = new Skill();
    	listModel.addElement("New Skill");
    	skillList.add(add);
        CentralDB.skillList = skillList;
	}
	
	public void deleteSelectedSkill(){
        if(!skillsList.isSelectionEmpty()){
            if(skillsList.getSelectedIndices().length > 1){
                int[] selection = skillsList.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	listModel.remove(selection[i]);
                	skillList.remove(selection[i]);
                    if(selection[i] == selected)
                        selected = -1;
                }
            }
            else{
                int selection = skillsList.getSelectedIndex();
                if (selection == selected)
                    selected = -1;
                listModel.remove(selection);
                skillList.remove(selection);
            }
            CentralDB.skillList = skillList;
        }
    }
	
	public void loadSkill(){
		selected = skillsList.getSelectedIndex();
		if(selected>=0) {
			sSkill = skillList.get(selected);
			textField.setText(sSkill.getName());
			textPane_1.setText(sSkill.getDescription());
			if(sSkill.getType()=="strength")
				strRadButton.setSelected(true);
			else if(sSkill.getType()=="dexterity")
				dexRadButton.setSelected(true);
			else if(sSkill.getType()=="iq")
				iqRadButton.setSelected(true);
			else if(sSkill.getType()=="health")
				hpRadButton.setSelected(true);
			else if(sSkill.getType()=="perception")
				percRadButton.setSelected(true);
			else
				willRadButton.setSelected(true);
			modField.setText(""+sSkill.getModifier());
		}
	}
	
	public void update() {
		skillList = CentralDB.skillList;
		listModel.removeAllElements();
		for(Skill skill: skillList) {
			System.out.println(skill.getName());
			listModel.addElement(skill.getName());
		}
	}
}
