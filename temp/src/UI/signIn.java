package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class signIn implements ActionListener{

	private JFrame frame;
	private JTextField idText;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField nameText;
	private JTextField phoneText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signIn window = new signIn();
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
	public signIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ȸ������");
		frame.setBounds(100, 100, 450, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel idPanel = new JPanel();
		idPanel.setBounds(68, 39, 318, 56);
		frame.getContentPane().add(idPanel);
		idPanel.setLayout(null);
		
		JLabel errorLabel = new JLabel("\uC911\uBCF5\uB41C \uC544\uC774\uB514\uAC00 \uC788\uC2B5\uB2C8\uB2E4");
		errorLabel.setBounds(32, 41, 190, 15);
		idPanel.add(errorLabel);
		errorLabel.setForeground(new Color(255, 0, 0));
		errorLabel.setVisible(false);
		
		JLabel OKLabel = new JLabel("\uC0AC\uC6A9\uD560 \uC218 \uC788\uB294 \uC544\uC774\uB514\uC785\uB2C8\uB2E4");
		OKLabel.setBounds(32, 41, 190, 15);
		idPanel.add(OKLabel);
		OKLabel.setForeground(new Color(0, 0, 255));
		OKLabel.setVisible(false);
		
		JLabel IDLabel = new JLabel("ID");
		IDLabel.setBounds(5, 0, 20, 35);
		idPanel.add(IDLabel);
		IDLabel.setFont(new Font("����", Font.PLAIN, 17));
		
		idText = new JTextField();
		idText.setBounds(32, 1, 190, 35);
		idPanel.add(idText);
		idText.setColumns(10);
		
		JButton checkId = new JButton("\uC911\uBCF5\uD655\uC778");
		checkId.setFont(new Font("����", Font.PLAIN, 10));
		checkId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = 0;
				if(a == 0) {
					OKLabel.setVisible(true);
				}
				else {
					errorLabel.setVisible(true);
				}
			}
		});
		checkId.setBounds(234, 0, 76, 35);
		idPanel.add(checkId);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(68, 105, 223, 179);
		frame.getContentPane().add(passwordPanel);
		passwordPanel.setLayout(null);
		
		JLabel passwordLabel = new JLabel("PW");
		passwordLabel.setBounds(0, 0, 28, 26);
		passwordPanel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(35, 0, 188, 36);
		passwordPanel.add(passwordField);
		
		JLabel passwordRule = new JLabel("\uBE44\uBC00\uBC88\uD638 \uADDC\uCE59: ");
		passwordRule.setBounds(35, 46, 188, 42);
		passwordPanel.add(passwordRule);
		passwordRule.setFont(new Font("����", Font.PLAIN, 9));
		
		JLabel passwordCheck = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		passwordCheck.setBounds(0, 85, 87, 26);
		passwordPanel.add(passwordCheck);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(35, 110, 188, 36);
		passwordPanel.add(passwordField_1);
		
		JLabel pwck_OK = new JLabel("\uBE44\uBC00\uBC88\uD638\uAC00 \uC77C\uCE58\uD569\uB2C8\uB2E4");
		pwck_OK.setForeground(new Color(0, 128, 255));
		pwck_OK.setBounds(30, 154, 181, 15);
		passwordPanel.add(pwck_OK);
		pwck_OK.setVisible(false);
		
		JLabel pwck_NO = new JLabel("\uBE44\uBC00\uBC88\uD638\uAC00 \uC77C\uCE58\uD558\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4");
		pwck_NO.setForeground(new Color(255, 0, 0));
		pwck_NO.setBounds(30, 156, 181, 15);
		passwordPanel.add(pwck_NO);
		pwck_NO.setVisible(false);
		
		JPanel namePanel = new JPanel();
		namePanel.setBounds(68, 294, 227, 36);
		frame.getContentPane().add(namePanel);
		namePanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("\uC774\uB984");
		nameLabel.setBounds(0, 10, 28, 15);
		namePanel.add(nameLabel);
		
		nameText = new JTextField();
		nameText.setBounds(39, 0, 188, 36);
		namePanel.add(nameText);
		nameText.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(68, 351, 227, 61);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel phoneLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		phoneLabel.setBounds(0, 0, 56, 26);
		panel.add(phoneLabel);
		
		phoneText = new JTextField();
		phoneText.setBounds(39, 25, 188, 36);
		panel.add(phoneText);
		phoneText.setColumns(10);
		
		
		JLabel error2Label = new JLabel("\uBAA8\uB4E0\uC815\uBCF4\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		error2Label.setForeground(new Color(255, 0, 0));
		error2Label.setBounds(242, 450, 144, 15);
		frame.getContentPane().add(error2Label);
		error2Label.setVisible(false);
		
		JButton signUp = new JButton("\uD68C\uC6D0 \uAC00\uC785");
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = 0;
				if(a == 0) {
					JOptionPane.showConfirmDialog(null, "���� ����!", "", JOptionPane.PLAIN_MESSAGE);
					logIn login = new logIn();
					login.setVisible(true);
					frame.dispose();
				}
				else {
					error2Label.setVisible(true);
				}
			}
		});
		
		
		signUp.setBounds(289, 475, 97, 36);
		frame.getContentPane().add(signUp);
		
		JButton backMain = new JButton("\uCDE8\uC18C");
		backMain.setBounds(12, 482, 105, 29);
		backMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn login = new logIn();
				login.setVisible(true);
				frame.dispose();
			}
		});
		frame.getContentPane().add(backMain);
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
