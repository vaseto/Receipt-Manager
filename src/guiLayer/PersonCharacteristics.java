package guiLayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlLayer.PersonCtrl;

public class PersonCharacteristics extends JFrame {

	private  static JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonCharacteristics frame = new PersonCharacteristics();
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
	public PersonCharacteristics() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		
	}
	public void PersonCharacteristics(){
		contentPane = (JPanel) MainMenu.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		
		PersonCtrl personCtrl = new PersonCtrl();
		ChangePerson changePerson = new ChangePerson();
		
		lblNewLabel = new JLabel("Change person characteristics");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(273, 22, 230, 14);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePerson cp = new ChangePerson();
				cp.changePerson();
			}
		});
		btnNewButton.setBounds(82, 350, 147, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(335, 119, 168, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(personCtrl.getPersonName(ChangePerson.getIndex()));
		
		textField_1 = new JTextField();
		textField_1.setBounds(336, 178, 75, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		String obligation = Double.toString(personCtrl.getPersonObligation(ChangePerson.getIndex()));
		textField_1.setText(obligation);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(273, 121, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Obligation");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(273, 180, 75, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Change and Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int counter = 0;
				if(textField.getText().equals("") == false){
					boolean var = personCtrl.setPersonName(textField.getText(), ChangePerson.getIndex());
					if(var == false){
						JOptionPane.showMessageDialog(null, "There is already person with this name!");
						counter++;
					}
				}else{
					JOptionPane.showMessageDialog(null, "You have not typed the name of the person!");
					counter++;
					
				}
				if(textField_1.getText().equals("") == false){
					double sum = Double.parseDouble(textField_1.getText());
					personCtrl.setObligation(ChangePerson.getIndex(),sum);
				}else{
					JOptionPane.showMessageDialog(null, "You have not typed the amount of obligation of the person!");
					counter++;
				}
				if(counter == 0){
					JOptionPane.showMessageDialog(null, "Successfully made changes!");
					textField.setText("");
					textField_1.setText("");
				}
					
			}
		});
		btnNewButton_1.setBounds(300, 233, 134, 23);
		contentPane.add(btnNewButton_1);
	}
}
