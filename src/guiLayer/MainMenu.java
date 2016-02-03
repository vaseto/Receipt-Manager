package guiLayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controlLayer.ManageObligation;
import controlLayer.PersonCtrl;
import modelLayer.Person;

public class MainMenu {
	private static MainMenu instance;
	protected static  JFrame frame;
	private JList list;
	private JLabel lblNewLabel;
	
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	private JRadioButton rdbtnYes;
	
	
	
	private ManageObligation manageObligation;
	private PersonCtrl personCtrl;
	private JButton btnRemove;
	private JButton btnCalculate;
	private JTextField textField_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNumberOfPeople;
	private JLabel label_1;
	private JLabel lblNewLabel_4;
	private JButton btnReset;
	private Queue<Integer> indices;
	private int counter ;
	private ButtonGroup bg  ; 
	private int number = 1;// it is 1 when radio button is yes and 2 otherwise
	private int index = 0;
	private JScrollPane scrollPane ;
	private JScrollPane scrollPaneCalculatoin ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}
	static{
		instance = new MainMenu();
	}
	public static MainMenu getInstance(){
		return instance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setFrame(new JFrame());
		
		getFrame().setBounds(100, 100, 751, 446);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		mainMenu();
	}
	
	
	
	
	public void mainMenu(){
		getFrame().getContentPane().removeAll();
		getFrame().getContentPane().repaint();
		JLabel lblNewLabel = new JLabel("Receipt Manager - Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(295, 29, 180, 14);
		getFrame().getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Do calculation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCalculation();
			}
		});
		btnNewButton.setBounds(260, 69, 212, 23);
		getFrame().getContentPane().add(btnNewButton);
		
		JButton btnPeopleWhoOwing = new JButton("People who owing money");
		btnPeopleWhoOwing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allPeople("All people who owing money");
			}
		});
		btnPeopleWhoOwing.setBounds(260, 111, 212, 23);
		getFrame().getContentPane().add(btnPeopleWhoOwing);
		
		JButton btnAllPeople = new JButton("All People");
		btnAllPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allPeople("All People");
			}
		});
		btnAllPeople.setBounds(260, 152, 212, 23);
		getFrame().getContentPane().add(btnAllPeople);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrame().dispose();
			}
		});
		btnExit.setBounds(295, 341, 140, 23);
		getFrame().getContentPane().add(btnExit);
		
		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPerson();
			}
		});
		btnAddPerson.setBounds(260, 191, 212, 23);
		getFrame().getContentPane().add(btnAddPerson);

		JButton btnRemovePerson = new JButton("Remove Person");
		btnRemovePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePerson();
			}
		});
		btnRemovePerson.setBounds(260, 233, 212, 23);
		getFrame().getContentPane().add(btnRemovePerson);

		JButton btnChangePerson = new JButton("Change Person characteristics");
		btnChangePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePerson cp = new ChangePerson();
				cp.changePerson();
			}
		});
		btnChangePerson.setBounds(260, 276, 212, 23);
		frame.getContentPane().add(btnChangePerson);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void allPeople(String text) {
		list = new JList();
		getFrame().getContentPane().removeAll();
		getFrame().getContentPane().repaint();
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		 scrollPane = new JScrollPane(list);
		scrollPane.setBounds(88, 59, 556, 249);

		getFrame().getContentPane().add(scrollPane);

		lblNewLabel = new JLabel(text);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(266, 22, 230, 14);
		getFrame().getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu();
			}
		});
		btnNewButton.setBounds(288, 350, 147, 23);
		getFrame().getContentPane().add(btnNewButton);
		if (text.contains("owing") == true) {
			appendOwing();
		} else if (text.contains("Remove") == true) {
			lblNewLabel.setBounds(315, 25, 127, 14);
			btnNewButton.setBounds(125, 350, 147, 23);
			append();
			JLabel lblNewLabel_1 = new JLabel("                            / Click on the person you want to remove and click button remove /");
			scrollPane.setColumnHeaderView(lblNewLabel_1);

		} else {
			lblNewLabel.setBounds(337, 24, 139, 14);
			append();
			
		}

	}
	protected void append(){
		PersonCtrl personCtrl = new PersonCtrl();
		ArrayList<Person> persons = personCtrl.getPersonsCollection(); 
		DefaultListModel dlm = new DefaultListModel<>();
		
		if(persons.isEmpty()== true){
			String text = "";
			if(index == 1 ){
				index = 0;
				JLabel lblNewLabel_1 = new JLabel("                                   There is no people");
				scrollPaneCalculatoin.setColumnHeaderView(lblNewLabel_1);
			}else{
				JLabel lblNewLabel_1 = new JLabel("                                                                         There is no people");
				scrollPane.setColumnHeaderView(lblNewLabel_1);
			}
		
			dlm.addElement(text);
		}else{
			for(Person p : persons){
				dlm.addElement(p);
			}
		}
		
		list.setModel(dlm);
	}

	protected void appendOwing() {
		PersonCtrl personCtrl = new PersonCtrl();
		ArrayList<Person> persons = personCtrl.getPersonsCollection();
		DefaultListModel dlm = new DefaultListModel<>();
		int counter = 0;
		if (persons.isEmpty() == true) {
			
			JLabel lblNewLabel_1 = new JLabel("                                                                         There is no people");
			scrollPane.setColumnHeaderView(lblNewLabel_1);
		} else {
			for (Person p : persons) {
				if (p.getObligation() > 0) {
					dlm.addElement(p);
					counter++;
				}
				if (counter == 0) {
					JLabel lblNewLabel_1 = new JLabel("                                                                         There is no people");
					scrollPane.setColumnHeaderView(lblNewLabel_1);
				}
			}
		}
		list.setModel(dlm);
	}

	protected void addPerson() {
		getFrame().getContentPane().removeAll();
		getFrame().getContentPane().repaint();
		JLabel lblAddPerson = new JLabel("Add person");
		lblAddPerson.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddPerson.setBounds(333, 23, 104, 14);
		getFrame().getContentPane().add(lblAddPerson);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(298, 149, 46, 14);
		getFrame().getContentPane().add(lblNewLabel);

		JLabel lblObligation = new JLabel("Obligation");
		lblObligation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblObligation.setBounds(298, 201, 75, 14);
		getFrame().getContentPane().add(lblObligation);

		textField = new JTextField();
		textField.setBounds(383, 147, 151, 20);
		getFrame().getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField("0");
		textField_1.setColumns(10);
		textField_1.setBounds(383, 199, 54, 20);
		getFrame().getContentPane().add(textField_1);

		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu();
			}
		});
		btnNewButton.setBounds(134, 335, 110, 23);
		getFrame().getContentPane().add(btnNewButton);

		btnNewButton_1 = new JButton("Add Person");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PersonCtrl person = new PersonCtrl();
					String name = textField.getText();
					double obligation = Double.parseDouble(textField_1.getText());
					int index = person.createPerson(name, obligation);
					if (index == 1) {
						JOptionPane.showMessageDialog(null, "The person was added successfully!");
					} else {
						JOptionPane.showMessageDialog(null,
								"The person was not added successfully! The user" + "name is not available!");
					}
					textField.setText("");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Incorrect data!");
				}
			}
		});
		btnNewButton_1.setBounds(510, 335, 110, 23);
		getFrame().getContentPane().add(btnNewButton_1);
	}

	protected void removePerson() {
		JButton btnRemove;
		allPeople("Remove person");

		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		btnRemove.setBounds(470, 350, 147, 23);
		frame.getContentPane().add(btnRemove);
		append();

	}

	// remove person from the Person collection
	protected void remove() {
		try {
			PersonCtrl personCtrl = new PersonCtrl();

			int[] numbers = list.getSelectedIndices();
			if(numbers.length == 0){
				
			}else{
				for (int i = 0; i < numbers.length; i++) {
					personCtrl.removePerson(numbers[i]);
				}
				JOptionPane.showMessageDialog(null, "Removing was successfull!");
				list.removeAll();
				append();
			}
			JOptionPane.showMessageDialog(null, "Removing was not successfull! You have to mark the person you want to remove!");
		} catch (Exception ex) {
			ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Removing was not successfull!");
			}
			
		
		}
		// calculation frame
		protected void doCalculation(){
			
			
			bg = new ButtonGroup();	
			manageObligation = new ManageObligation();
			personCtrl = new PersonCtrl();
			indices = new LinkedList<Integer>();
			JLabel lblRemovePerson = new JLabel("Calculate bills");
			lblRemovePerson.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblRemovePerson.setBounds(315, 25, 127, 14);
			getFrame().getContentPane().add(lblRemovePerson);
			
			list = new JList();
			getFrame().getContentPane().removeAll();
			getFrame().getContentPane().repaint();
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			scrollPaneCalculatoin = new JScrollPane(list);
			scrollPaneCalculatoin.setBounds(86, 68, 315, 238);
			getFrame().getContentPane().add(scrollPaneCalculatoin);
			JLabel lblNewLabel_1 = new JLabel("/Double click  on the person(s) participated in the bill /");
			scrollPaneCalculatoin.setColumnHeaderView(lblNewLabel_1);
			
			MouseListener mouseListener = new MouseAdapter() {
				public void mouseClicked(MouseEvent mouseEvent) {
					JList theList = (JList) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2) {
						if (indices.contains(theList.locationToIndex(mouseEvent.getPoint())) == false) {
							indices.add(theList.locationToIndex(mouseEvent.getPoint()));
							counter++;
							printCounter(counter);
						} else {
							int value = JOptionPane.showConfirmDialog(null,
									"This person is already included in the bill! Are you sure you"
											+ " want to add him again?");
							switch (value) {
							case 0:
								indices.add(theList.locationToIndex(mouseEvent.getPoint()));
								counter++;
								printCounter(counter);
								break;
							case 1: case 2:
								break;
							}
						}
					}
				}
			};
			list.addMouseListener(mouseListener);

				lblNewLabel = new JLabel("");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel.setBounds(266, 22, 230, 14);
				getFrame().getContentPane().add(lblNewLabel);
				
				btnNewButton = new JButton("Back");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainMenu();
					}
				});
				btnNewButton.setBounds(26, 360, 147, 23);
				getFrame().getContentPane().add(btnNewButton);
				
				JRadioButton rdbtnNewRadioButton = new JRadioButton("No");
				rdbtnNewRadioButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(number == 1){
							counter--;
							printCounter(counter);
							number++;
						}else{
							JOptionPane.showMessageDialog(null, "It is already selected!");
						}
					}
				});
				rdbtnNewRadioButton.setBounds(620, 140, 55, 23);
				getFrame().getContentPane().add(rdbtnNewRadioButton);
				
				 rdbtnYes = new JRadioButton("Yes");
				rdbtnYes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(number == 2){
							counter++;
							printCounter(counter);
							number--;
						}else{
							JOptionPane.showMessageDialog(null, "It is already selected!");
						}
					}
				});
				rdbtnYes.setSelected(true);
				rdbtnYes.setBounds(563, 140, 55, 23);
				getFrame().getContentPane().add(rdbtnYes);
				bg.add(rdbtnNewRadioButton);
				bg.add(rdbtnYes);
				
				btnRemove = new JButton("Calculate and save");
				btnRemove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double sum = calculationSum();
						if(sum == -1){
							
						}else{
							while(indices.isEmpty() != true){
								int index = indices.poll();					
								String name = personCtrl.getPersonsCollection().get(index).getName();
								personCtrl.addSum(name,sum );
							}
							JOptionPane.showMessageDialog(null, "You have added "+ sum + " per person successfully!");
							reset();
							list.removeAll();
							index = 1;
							append();
						}
					}
				});
				btnRemove.setBounds(451, 220, 147, 23);
				getFrame().getContentPane().add(btnRemove);
				
				
				btnCalculate = new JButton("Calculate");
				btnCalculate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						calculationSum();
					}
				});
				btnCalculate.setBounds(451, 186, 147, 23);
				getFrame().getContentPane().add(btnCalculate);
				
				textField_2 = new JTextField("0");
				textField_2.setBounds(594, 104, 48, 20);
				getFrame().getContentPane().add(textField_2);
				textField_2.setColumns(10);
				
				lblNewLabel_1 = new JLabel("Insert the whole sum");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel_1.setBounds(451, 106, 147, 14);
				getFrame().getContentPane().add(lblNewLabel_1);
				
				lblNewLabel_2 = new JLabel("The sum per person is:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel_2.setBounds(451, 279, 147, 14);
				getFrame().getContentPane().add(lblNewLabel_2);
				
				lblNewLabel_3 = new JLabel("1");
				lblNewLabel_3.setBounds(594, 254, 24, 14);
				getFrame().getContentPane().add(lblNewLabel_3);
				counter = Integer.parseInt(lblNewLabel_3.getText());
				
				lblNumberOfPeople = new JLabel("Number of people:");
				lblNumberOfPeople.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNumberOfPeople.setBounds(451, 254, 147, 14);
				getFrame().getContentPane().add(lblNumberOfPeople);
				
				label_1 = new JLabel("0");
				label_1.setBounds(594, 280, 24, 14);
				getFrame().getContentPane().add(label_1);
				
				
				
				btnReset = new JButton("Reset");
				btnReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reset();
						
					}
				});
				btnReset.setBounds(197, 360, 147, 23);
				getFrame().getContentPane().add(btnReset);
				
				
				
				JLabel lblNewLabel_5 = new JLabel("Am I included?");
				lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel_5.setBounds(451, 143, 116, 14);
				getFrame().getContentPane().add(lblNewLabel_5);
				index = 1;
				append();
				
		}
		private void printCounter(int counter){
			 lblNewLabel_3.setText(Integer.toString(counter));
		}
		private double calculationSum(){
			try{
			double sum = Double.parseDouble(textField_2.getText());
			sum = manageObligation.devideSum(counter, sum);
			label_1.setText(Double.toString(sum));
			return sum;
			}catch(Exception ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Incorrect calculatoin!");
			}
			return -1;
		}
		private void reset(){
			label_1.setText("0");
			indices.clear();
			textField_2.setText("0");
			if(rdbtnYes.isSelected() == false){
				rdbtnYes.setSelected(true);
			}
			counter = 1;
			lblNewLabel_3.setText("1");
		}

	
}
