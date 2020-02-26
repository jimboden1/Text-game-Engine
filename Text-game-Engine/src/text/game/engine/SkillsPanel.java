package text.game.engine;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SkillsPanel extends JPanel 
{
	JPanel base;
	JList skillsList = new JList();
	JTextField textField = new JTextField();
	JTextPane textPane_1 = new JTextPane();
	JTextField modField = new JTextField();
	
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
		
		JRadioButton strRadButton = new JRadioButton("Strength");
		strRadButton.setBounds(226, 37, 127, 25);
		base.add(strRadButton);
		
		JRadioButton dexRadButton = new JRadioButton("Dexterity");
		dexRadButton.setBounds(226, 67, 127, 25);
		base.add(dexRadButton);
		
		JRadioButton IQRadButton = new JRadioButton("IQ");
		IQRadButton.setBounds(226, 97, 127, 25);
		base.add(IQRadButton);
		
		JRadioButton HPRadButton = new JRadioButton("Health Points");
		HPRadButton.setBounds(226, 127, 127, 25);
		base.add(HPRadButton);
		
		JRadioButton percRadButton = new JRadioButton("Perception");
		percRadButton.setBounds(226, 157, 127, 25);
		base.add(percRadButton);
		
		JRadioButton willRadButton = new JRadioButton("Will");
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
		
		return base;
	}
}
