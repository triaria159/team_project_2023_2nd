package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class logIn implements ActionListener{
	

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField idText;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logIn window = new logIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public logIn() {
		initialize();
	}


	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("�α���");
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane errorText = new JTextPane();
		errorText.setEditable(false);
		errorText.setVisible(false);
		errorText.setForeground(new Color(255, 0, 0));
		errorText.setFont(new Font("����", Font.PLAIN, 8));
		errorText.setText("\uC544\uC774\uB514 \uD639\uC740 \uBE44\uBC00\uBC88\uD638\uAC00 \uC77C\uCE58\uD558\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4");
		errorText.setBackground(new Color(240, 240, 240));
		errorText.setBounds(143, 169, 163, 21);
		panel.add(errorText);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setEditable(false);
		txtpnPassword.setBackground(new Color(240, 240, 240));
		txtpnPassword.setText("PW");
		txtpnPassword.setBounds(106, 133, 25, 23);
		panel.add(txtpnPassword);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(143, 119, 156, 43);
		panel.add(passwordField);
		
		JButton LOGINButton = new JButton("LOGIN");
		LOGINButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = 0;
				if(a == 0) {
					errorText.setVisible(false);
					friendTap friendtab = new friendTap();
					friendtab.setVisible(true);
					frame.dispose();
				}
				else {
					errorText.setVisible(true);
				}
			}
		});
		LOGINButton.setBounds(165, 190, 97, 23);
		panel.add(LOGINButton);
		
		JButton makeAccount = new JButton("\uD68C\uC6D0\uAC00\uC785");
		makeAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signIn signin = new signIn();
				signin.setVisible(true);
				frame.dispose();
			}
		});
		makeAccount.setBounds(325, 228, 97, 23);
		panel.add(makeAccount);
		
		JButton findAccount = new JButton("\uACC4\uC815\uCC3E\uAE30");
		findAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findAccount findaccount = new findAccount();
				findaccount.setVisible(true);
				frame.dispose();
			}
		});

		findAccount.setBounds(12, 228, 97, 23);
		panel.add(findAccount);
		
		JTextPane headText = new JTextPane();
		headText.setEditable(false);
		headText.setText("\uCE74\uCE74\uC624\uD1A1 \uBCA4\uCE58\uB9C8\uD0B9");
		headText.setBackground(UIManager.getColor("Button.background"));
		headText.setBounds(175, 34, 126, 21);
		panel.add(headText);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setEditable(false);
		txtpnId.setText("ID");
		txtpnId.setBackground(UIManager.getColor("Button.background"));
		txtpnId.setBounds(106, 73, 25, 23);
		panel.add(txtpnId);
		
		idText = new JTextField();
		idText.setBounds(143, 65, 156, 44);
		panel.add(idText);
		idText.setColumns(10);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

