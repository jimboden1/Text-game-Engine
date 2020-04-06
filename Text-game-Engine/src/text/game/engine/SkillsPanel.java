package text.game.engine;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SkillsPanel
{
	JPanel base;
	DefaultListModel listModel = new DefaultListModel();
	JList skillsList = new JList(listModel);
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
	
	public SkillsPanel(JPanel base)
	{
		this.base = base;
	}
	
	public JPanel createSkillsPanel()
	{
		base.setLayout(null);
		
		skillsList.setBounds(12, 13, 194, 231);
		skillsList.setBorder(new TitledBorder(null, "Skill List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		base.add(skillsList);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(12, 257, 754, 2);
		base.add(separator_9);
		
                ButtonGroup statGroup = new ButtonGroup();
                
                statGroup.add(strRadButton);
		strRadButton.setBounds(226, 37, 127, 25);
		base.add(strRadButton);
		
                statGroup.add(dexRadButton);
		dexRadButton.setBounds(226, 67, 127, 25);
		base.add(dexRadButton);
		
                statGroup.add(iqRadButton);
		iqRadButton.setBounds(226, 97, 127, 25);
		base.add(iqRadButton);
		
                statGroup.add(hpRadButton);
		hpRadButton.setBounds(226, 127, 127, 25);
		base.add(hpRadButton);
		
                statGroup.add(percRadButton);
		percRadButton.setBounds(226, 157, 127, 25);
		base.add(percRadButton);
		
                statGroup.add(willRadButton);
		willRadButton.setBounds(226, 187, 127, 25);
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
		modLabel.setBounds(361, 206, 56, 16);
		base.add(modLabel);
		
		modField.setBounds(429, 203, 32, 22);
		base.add(modField);
		modField.setColumns(10);
		
		JButton addSkillButton = new JButton("Add Skill");
		addSkillButton.setBounds(572, 206, 194, 38);
		base.add(addSkillButton);
		addSkillButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
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
				skillList.add(skill);
				System.out.println(name + " " + description + " " + type);
				listModel.addElement(name);
				//Add the skills that were just created to the database
				CentralDB.skillList = skillList;
			}
		});
		
		return base;
	}
}
