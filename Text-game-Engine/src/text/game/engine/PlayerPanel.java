package text.game.engine;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PlayerPanel
{
	JPanel base= new JPanel();
	JTextField txtName = new JTextField(), funds = new JTextField("0");
	JTextArea txtrDescription = new JTextArea();
	DefaultListModel<String> skilllm = new DefaultListModel<String>();
	JList<String> playerSkillsList = new JList<String>(skilllm);
	DefaultListModel<String> itemlm = new DefaultListModel<String>();
	JList<String> playerItemList = new JList<String>(itemlm);
	JLabel picPane = new JLabel();
	JTextField strField = new JTextField("0");
	JTextField dexField = new JTextField("0");
	JTextField iqField = new JTextField("0");
	JTextField hpField = new JTextField("0");
	JTextField perField = new JTextField("0");
	JTextField willField = new JTextField("0");
	JButton addPicButton = new JButton();
	JButton createPlayer = new JButton();
	JButton addSkill = new JButton();
	JButton removeSkill = new JButton();
	Player player = new Player();
	ImageIcon playerPic = new ImageIcon();
	ArrayList<Integer> skillList = new ArrayList<>();
	ArrayList<Integer> itemList = new ArrayList<>();
	
	
	public PlayerPanel()
	{
    }
	
	public JPanel createPlayerPanel()
	{
		base.setLayout(null);
		
		txtName.setText("Name");
		txtName.setBounds(10, 10, 300, 30);
		base.add(txtName);
		txtName.setColumns(100);
		
		funds.setBorder(new TitledBorder("Starting Funds"));
		IntFilter.makeIntOnly(funds);
		funds.setBounds(350, 10, 300, 50);
		base.add(funds);
		
		txtrDescription.setText("Description");
		txtrDescription.setBounds(10, 62, 755, 85);
		base.add(txtrDescription);	
		
        JScrollPane js = new JScrollPane(playerSkillsList);
		js.setBounds(546, 176, 220, 335);
        js.setBorder(new TitledBorder(null, "Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        for(Skill skill: CentralDB.skillList)
        {
            skilllm.addElement(skill.getName());
        }
        base.add(js);
        base.repaint();
        base.revalidate();
        
        addSkill.setText("Add Skill");
        addSkill.setBounds(430,450,110,20);
        base.add(addSkill);
        addSkill.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame popupFrame = new JFrame();
				JPanel popupPanel = new JPanel();
				popupFrame.add(popupPanel);
				popupFrame.setVisible(true);
				popupFrame.setBounds(200,175, 750, 500 );
				popupPanel.setLayout(null);
				DefaultListModel<String> listModel2 = new DefaultListModel<>();
				JList<String>  skills = new JList<>(listModel2);
				JScrollPane scrollPane = new JScrollPane(skills);
				scrollPane.setBorder(new TitledBorder(null, "Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				scrollPane.setBounds(250,25,220,335);
				popupPanel.add(scrollPane);
				for(Skill skill : CentralDB.skillList)
				{
					listModel2.addElement(skill.getName());
				}
				JButton confirm = new JButton();
				confirm.setText("Confirm");
				confirm.setBounds(305, 360, 100, 20);
				popupPanel.add(confirm);
				confirm.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int[] selected = skills.getSelectedIndices();
						for(int i = 0; i<selected.length;i++)
						{
							skilllm.addElement(CentralDB.skillList.get(i).getName());
							skillList.add(selected[i]);
						}
						player.setSkills(skillList);
						popupFrame.dispose();
					}
				});
			}
		});
        
        removeSkill.setText("Remove Skill");
        removeSkill.setBounds(430,485,110,20);
        base.add(removeSkill);
        removeSkill.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!playerSkillsList.isSelectionEmpty()){
		            if(playerSkillsList.getSelectedIndices().length > 1){
		                int[] selection = playerSkillsList.getSelectedIndices();
		                for(int i = selection.length-1 ; i>=0; i--){
		                	skilllm.remove(selection[i]);
		                	skillList.remove(selection[i]);
		                }
		            }
		            else{
		                int selection = playerSkillsList.getSelectedIndex();
		                skilllm.remove(selection);
		                skillList.remove(selection);
		            }
		            player.setSkills(skillList);
		        }
			}
		});
        
        
        
        IntFilter.makeIntOnly(strField);
        IntFilter.makeIntOnly(dexField);
        IntFilter.makeIntOnly(iqField);
        IntFilter.makeIntOnly(hpField);
        IntFilter.makeIntOnly(perField);
        IntFilter.makeIntOnly(willField);
        
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 160, 755, 2);
		base.add(separator_4);
		
		//Create Player object
		createPlayer.setBounds(330 ,350,100,20);
		createPlayer.setText("Create");
		base.add(createPlayer);
		createPlayer.addActionListener(e->savePlayer());
		
		strField.setBounds(305, 219, 48, 22);
		base.add(strField);
		strField.setColumns(10);
		
		dexField.setColumns(10);
		dexField.setBounds(305, 254, 48, 22);
		base.add(dexField);
		
		iqField.setColumns(10);
		iqField.setBounds(305, 289, 48, 22);
		base.add(iqField);
		
		hpField.setColumns(10);
		hpField.setBounds(484, 219, 48, 22);
		base.add(hpField);
		
		perField.setColumns(10);
		perField.setBounds(484, 254, 48, 22);
		base.add(perField);
		
		willField.setColumns(10);
		willField.setBounds(484, 289, 48, 22);
		base.add(willField);
		
		JLabel strLabel = new JLabel("Strength:");
		strLabel.setBounds(235, 219, 56, 16);
		base.add(strLabel);
		
		JLabel dexLabel = new JLabel("Dexterity:");
		dexLabel.setBounds(235, 254, 56, 16);
		base.add(dexLabel);
		
		JLabel IQLabel = new JLabel("IQ:");
		IQLabel.setBounds(235, 289, 56, 16);
		base.add(IQLabel);
		
		JLabel HPLabel = new JLabel("HP:");
		HPLabel.setBounds(400, 219, 56, 16);
		base.add(HPLabel);
		
		JLabel perLabel = new JLabel("Perception:");
		perLabel.setBounds(400, 254, 70, 16);
		base.add(perLabel);
		
		JLabel willLabel = new JLabel("Will:");
		willLabel.setBounds(400, 289, 56, 16);
		base.add(willLabel);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(240, 324, 295, 2);
		base.add(separator_5);
		
		JLabel statsLabel = new JLabel("Stats:");
		statsLabel.setBounds(355, 176, 40, 16);
		base.add(statsLabel);

		JScrollPane itemScrollPane = new JScrollPane(playerItemList);
		itemScrollPane.setBorder(new TitledBorder(null, "Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		itemScrollPane.setBounds(10,176,220,335);
		base.add(itemScrollPane);
		
		JButton addItem = new JButton("Add Item");
		addItem.addActionListener(e->addItem());
		addItem.setBounds(235,450,110,20);
		base.add(addItem);
		
		JButton removeItem = new JButton("Remove Item");
		removeItem.addActionListener(e->removeItem());
		removeItem.setBounds(235,485,110,20);
		base.add(removeItem);
		
		return base;
	}
	
	public void savePlayer() {
		player = new Player();
		player.setName(txtName.getText());
		player.setDescription(txtrDescription.getText());
		player.money = Integer.parseInt(funds.getText());
		player.setStrength(Integer.parseInt(strField.getText()));
		player.setDexterity(Integer.parseInt(dexField.getText()));
		player.setIQ(Integer.parseInt(iqField.getText()));
		player.setMaxHealth(Integer.parseInt(hpField.getText()));
		player.setPerception(Integer.parseInt(perField.getText()));
		player.setWill(Integer.parseInt(willField.getText()));
		player.setSkills(skillList);
		player.inventory = itemList;
		CentralDB.player = player;
	}
	
	public void addItem() {
    	JPanel main = new JPanel(new BorderLayout(10,10));
		DefaultListModel<String> items = new DefaultListModel<>();
    	JList<String> itemsList = new JList<>(items);
    	for(Item item : CentralDB.itemList)
		{
    		items.addElement(item.getName());
		}
    	JScrollPane js = new JScrollPane(itemsList);
        js.setBorder(new TitledBorder(null, "Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	main.add(js);
    	JOptionPane.showMessageDialog(main, main);
        int[] selection = itemsList.getSelectedIndices();
        for(int i:selection) {
            itemlm.addElement(CentralDB.itemList.get(i).getName());
            itemList.add(i);
        }
    }
    
    public void removeItem() {
    	if(!playerItemList.isSelectionEmpty()){
            if(playerItemList.getSelectedIndices().length > 1){
                int[] selection = playerItemList.getSelectedIndices();
                for(int i = selection.length-1 ; i>=0; i--){
                	itemlm.remove(selection[i]);
                	itemList.remove(selection[i]);
                }
            }
            else{
                int selection = playerItemList.getSelectedIndex();
                itemlm.remove(selection);
                itemList.remove(selection);
            }
        }
    }
	
	public void update() {
		player = CentralDB.player;
		txtName.setText(player.getName());
		txtrDescription.setText(player.getDescription());
		funds.setText("" + player.money);
		strField.setText(""+ player.getStrength());
		dexField.setText(""+player.getDexterity());
		iqField.setText(""+ player.getIQ());
		hpField.setText(""+ player.getMaxHealth());
		perField.setText(""+ player.getPerception());
		willField.setText(""+ player.getWill());
		skillList=player.getSkills();
		skilllm.clear();
		for(int index: skillList) {
			skilllm.addElement(CentralDB.skillList.get(index).getName());
		}
		itemList = player.inventory;
		itemlm.clear();
		for(int index:itemList) {
			itemlm.addElement(CentralDB.itemList.get(index).getName());
		}
	}

}
