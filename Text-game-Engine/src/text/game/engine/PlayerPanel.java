package text.game.engine;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PlayerPanel extends JPanel
{
	JPanel base;
	JTextField txtName = new JTextField();
	JTextArea txtrDescription = new JTextArea();
	JList playerSkillsList = new JList();
	JEditorPane picPane = new JEditorPane();
	JTextField strField = new JTextField();
	JTextField dexField = new JTextField();
	JTextField iqField = new JTextField();
	JTextField hpField = new JTextField();
	JTextField perField = new JTextField();
	JTextField willField = new JTextField();
	
	JButton addPicButton = new JButton();
	JButton createPlayer = new JButton();
	Player player;
	
	
	public PlayerPanel(JPanel base)
	{
        this.base = base;
    }
	
	public JPanel createPlayerPanel()
	{
		base.setLayout(null);
		
		txtName.setText("Name");
		txtName.setBounds(10, 10, 70, 40);
		base.add(txtName);
		txtName.setColumns(10);
		
		txtrDescription.setText("Description");
		txtrDescription.setBounds(10, 62, 756, 86);
		base.add(txtrDescription);	
		
		playerSkillsList.setBounds(546, 176, 220, 335);
		playerSkillsList.setBorder(new TitledBorder(null, "Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		base.add(playerSkillsList);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 161, 756, 2);
		base.add(separator_4);		
		
		//Picture of the player if desired. 
		picPane.setBounds(10, 176, 188, 174);
		picPane.setEditable(false);
		base.add(picPane);
		addPicButton.setBounds(10, 350, 100, 20);
		addPicButton.setText("Upload");
		base.add(addPicButton);
		addPicButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		//Create Player object
		createPlayer.setBounds(300 ,350,100,20);
		createPlayer.setText("Create");
		base.add(createPlayer);
		createPlayer.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent e)
				{
					player = new Player();
					player.setName(txtName.getText());
					player.setDescription(txtrDescription.getText());
					player.setStrength(Integer.parseInt(strField.getText()));
					player.setDexterity(Integer.parseInt(dexField.getText()));
					player.setIQ(Integer.parseInt(iqField.getText()));
					player.setHealth(Integer.parseInt(hpField.getText()));
					player.setPerception(Integer.parseInt(perField.getText()));
					player.setWill(Integer.parseInt(willField.getText()));
					player.getInfo();
					//Set the skills 
					//Set the image
				}
		});
		
		strField.setBounds(290, 219, 48, 22);
		base.add(strField);
		strField.setColumns(10);
		
		dexField.setColumns(10);
		dexField.setBounds(290, 254, 48, 22);
		base.add(dexField);
		
		iqField.setColumns(10);
		iqField.setBounds(290, 289, 48, 22);
		base.add(iqField);
		
		hpField.setColumns(10);
		hpField.setBounds(474, 219, 48, 22);
		base.add(hpField);
		
		perField.setColumns(10);
		perField.setBounds(474, 254, 48, 22);
		base.add(perField);
		
		willField.setColumns(10);
		willField.setBounds(474, 289, 48, 22);
		base.add(willField);
		
		JLabel strLabel = new JLabel("Strength:");
		strLabel.setBounds(210, 219, 56, 16);
		base.add(strLabel);
		
		JLabel dexLabel = new JLabel("Dexterity:");
		dexLabel.setBounds(210, 254, 56, 16);
		base.add(dexLabel);
		
		JLabel IQLabel = new JLabel("IQ:");
		IQLabel.setBounds(210, 289, 56, 16);
		base.add(IQLabel);
		
		JLabel HPLabel = new JLabel("HP:");
		HPLabel.setBounds(375, 219, 56, 16);
		base.add(HPLabel);
		
		JLabel perLabel = new JLabel("Perception:");
		perLabel.setBounds(375, 254, 70, 16);
		base.add(perLabel);
		
		JLabel willLabel = new JLabel("Will:");
		willLabel.setBounds(375, 289, 56, 16);
		base.add(willLabel);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(210, 324, 324, 2);
		base.add(separator_5);
		
		JLabel statsLabel = new JLabel("Stats:");
		statsLabel.setBounds(341, 176, 40, 16);
		base.add(statsLabel);
		
		JLabel placeholder = new JLabel("put other stuff here");
		placeholder.setBounds(180, 414, 129, 16);
		base.add(placeholder);
		
		return base;
	}

}
