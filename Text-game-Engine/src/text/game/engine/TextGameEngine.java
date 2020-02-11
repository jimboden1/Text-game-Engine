package text.game.engine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class TextGameEngine {

	private JFrame frame;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextGameEngine window = new TextGameEngine();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TextGameEngine() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		//creating the GUI
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Creating the tab layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(tabbedPane);
		
		//Player tab
		JPanel playerTab = new JPanel();
		playerTab.setBorder(null);
		tabbedPane.addTab("Player", null, playerTab, null);
		playerTab.setLayout(null);
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(10, 11, 86, 20);
		playerTab.add(txtName);
		txtName.setColumns(10);
		JTextArea txtrDescription = new JTextArea();
		txtrDescription.setText("Description");
		txtrDescription.setBounds(10, 42, 270, 82);
		playerTab.add(txtrDescription);
		JList skillsList = new JList();
		skillsList.setVisibleRowCount(4);
		skillsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		skillsList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Example", "Example", "Example", "Example", "Example", "Example", "Example", "Example"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		skillsList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Skill List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		skillsList.setBounds(10, 135, 270, 87);
		playerTab.add(skillsList);
		
		//Room Tab
		JPanel roomsTab = new JPanel();
		tabbedPane.addTab("Rooms", null, roomsTab, null);
		
		//Events Tab
		JPanel eventsTab = new JPanel();
		tabbedPane.addTab("Events", null, eventsTab, null);
		eventsTab.setLayout(null);
		
		//Extra Tab
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
	}
}
