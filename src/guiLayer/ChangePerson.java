package guiLayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controlLayer.PersonCtrl;
import modelLayer.Person;

public class ChangePerson extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private static int index;
	private Person p;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePerson frame = new ChangePerson();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangePerson() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		
			
	}
	public void changePerson(){
		
		contentPane = (JPanel) MainMenu.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		list = new JList();
		contentPane.removeAll();
		contentPane.repaint();
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		 JScrollPane scrollPane = new JScrollPane(list);
			scrollPane.setBounds(88, 59, 556, 249);
			
			
		
			contentPane.add(scrollPane);
			
			JLabel lblNewLabel_1 = new JLabel("                                                      / Double click on the person you want change /");
			scrollPane.setColumnHeaderView(lblNewLabel_1);
			
			lblNewLabel = new JLabel("Change person characteristics");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(266, 22, 230, 14);
			contentPane.add(lblNewLabel);
			
			btnNewButton = new JButton("Back");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainMenu.getInstance().mainMenu();
				}
			});
			btnNewButton.setBounds(294, 350, 147, 23);
			contentPane.add(btnNewButton);
			
				append();
			
				MouseListener mouseListener = new MouseAdapter() {
					public void mouseClicked(MouseEvent mouseEvent) {
						JList theList = (JList) mouseEvent.getSource();
						if (mouseEvent.getClickCount() == 2) {
							setIndex(theList.locationToIndex(mouseEvent.getPoint()));
							;
							PersonCharacteristics pc = new PersonCharacteristics();
							pc.PersonCharacteristics();
						}
					}
					};
						list.addMouseListener(mouseListener);
	}
	protected void append(){
		try{
			PersonCtrl personCtrl = new PersonCtrl();
			ArrayList<Person> persons = personCtrl.getPersonsCollection(); 
			DefaultListModel dlm = new DefaultListModel<>();
			if(persons.isEmpty()== true){
				String text = "                                                                         There is no people";
				dlm.addElement(text);
			}else{
				for(Person p : persons){
					dlm.addElement(p);
				}
			}
			
			list.setModel(dlm);
		
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		ChangePerson.index = index;
	}
}
