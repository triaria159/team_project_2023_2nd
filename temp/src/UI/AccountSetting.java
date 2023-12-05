package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class AccountSetting implements ActionListener{

	private JFrame frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountSetting window = new AccountSetting();
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
	public AccountSetting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("���� ����");
		frame.setBounds(100, 100, 450, 196);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel errorText = new JLabel("\uBE44\uBC00\uBC88\uD638\uADDC\uCE59\uC5D0 \uB9DE\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4");
		errorText.setForeground(new Color(255, 0, 0));
		errorText.setFont(new Font("����", Font.PLAIN, 9));
		errorText.setBounds(195, 128, 131, 15);
		frame.getContentPane().add(errorText);
		errorText.setVisible(false);
		
		JLabel name = new JLabel("\uC774\uB984");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBounds(155, 30, 100, 15);
		frame.getContentPane().add(name);
		
		JLabel id = new JLabel("\uC544\uC774\uB514");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(111, 55, 190, 15);
		frame.getContentPane().add(id);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(121, 80, 180, 21);
		frame.getContentPane().add(passwordField);
		
		JLabel id_1 = new JLabel("\uC544\uC774\uB514: ");
		id_1.setHorizontalAlignment(SwingConstants.CENTER);
		id_1.setBounds(65, 55, 69, 15);
		frame.getContentPane().add(id_1);
		
		JLabel pw = new JLabel("\uBE44\uBC00\uBC88\uD638\uBCC0\uACBD ");
		pw.setHorizontalAlignment(SwingConstants.CENTER);
		pw.setBounds(25, 83, 94, 15);
		frame.getContentPane().add(pw);
		
		JButton changePassword = new JButton("\uBCC0\uACBD");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = 0;
				if(a == 0) {
					int result = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "��й�ȣ ����", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						logIn login = new logIn();
						login.setVisible(true);
						frame.dispose();
					}
				}
				else {
					errorText.setVisible(true);
				}
			}
		});
		changePassword.setBounds(325, 124, 97, 23);
		frame.getContentPane().add(changePassword);
		
		JButton cancel = new JButton("\uCDE8\uC18C");
		cancel.setBounds(12, 124, 97, 23);
		frame.getContentPane().add(cancel);
		
		JLabel pwrule = new JLabel("\uBE44\uBC00\uBC88\uD638 \uADDC\uCE59: ");
		pwrule.setBounds(120, 102, 206, 15);
		frame.getContentPane().add(pwrule);
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
