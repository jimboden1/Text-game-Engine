package text.game.engine;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;

public class PlayerPanel
{
	JPanel base;
	JTextField txtName = new JTextField();
	JTextArea txtrDescription = new JTextArea();
	JList playerSkillsList = new JList();
	JLabel picPane = new JLabel();
	JTextField strField = new JTextField();
	JTextField dexField = new JTextField();
	JTextField iqField = new JTextField();
	JTextField hpField = new JTextField();
	JTextField perField = new JTextField();
	JTextField willField = new JTextField();
	JButton addPicButton = new JButton();
	JButton createPlayer = new JButton();
	Player player;
	File playerPic;
	
	
	public PlayerPanel(JPanel base)
	{
        this.base = base;
    }
	
	public JPanel createPlayerPanel()
	{
		base.setLayout(null);
		
		txtName.setText("Name");
		txtName.setBounds(10, 10, 100, 30);
		base.add(txtName);
		txtName.setColumns(100);
		
		txtrDescription.setText("Description");
		txtrDescription.setBounds(10, 62, 755, 85);
		base.add(txtrDescription);	
		
                JScrollPane js = new JScrollPane(playerSkillsList);
		js.setBounds(546, 176, 220, 335);
                js.setBorder(new TitledBorder(null, "Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                
                IntFilter.makeIntOnly(strField);
                IntFilter.makeIntOnly(dexField);
                IntFilter.makeIntOnly(iqField);
                IntFilter.makeIntOnly(hpField);
                IntFilter.makeIntOnly(perField);
                IntFilter.makeIntOnly(willField);
        
                base.add(js);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 160, 755, 2);
		base.add(separator_4);		
		
		//Picture of the player if desired. 
		picPane.setBounds(10, 176, 188, 174);
		picPane.setVisible(true);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		picPane.setBorder(border);
		//picPane.setEditable(false);
		base.add(picPane);
		addPicButton.setBounds(10, 350, 100, 20);
		addPicButton.setText("Upload");
		base.add(addPicButton);
		addPicButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG File", "jpg","jpeg");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(base);
			    if(returnVal == JFileChooser.APPROVE_OPTION)
			    {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
			       
			       File file = new File(chooser.getSelectedFile().getPath());
			       BufferedImage img;
			       try
			       {
			    	   img = ImageIO.read(file);
			    	   Image scaled = img.getScaledInstance(picPane.getWidth(), picPane.getHeight(), Image.SCALE_SMOOTH);
			    	   ImageIcon icon = new ImageIcon(scaled);
			    	   picPane.setIcon(icon);
			    	   base.repaint();
			    	   base.revalidate();
			       }catch (IOException e1)
			       {
			    	   // TODO Auto-generated catch block
			    	   e1.printStackTrace();
			       }
			       
			       
			    }
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
					player.setPic(playerPic);
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
