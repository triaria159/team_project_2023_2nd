package UI;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class myProfile implements ActionListener{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myProfile window = new myProfile();
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
	public myProfile() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("�� ������");
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel myName = new JLabel("name");
		myName.setBounds(165, 44, 57, 15);
		frame.getContentPane().add(myName);
		
		JLabel myId = new JLabel("New label");
		myId.setBounds(211, 69, 57, 15);
		frame.getContentPane().add(myId);
		
		JLabel lblNewLabel = new JLabel("\uB0B4 \uC544\uC774\uB514: ");
		lblNewLabel.setBounds(151, 69, 60, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JButton changePassword = new JButton("\uBE44\uBC00\uBC88\uD638 \uBCC0\uACBD");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountSetting accountsetting = new AccountSetting();
				accountsetting.setVisible(true);
				frame.dispose();
			}
		});
		changePassword.setBounds(32, 300, 362, 43);
		frame.getContentPane().add(changePassword);
		
		JButton secession = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		secession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = 0;
				if(a == 0) {
					int result = JOptionPane.showConfirmDialog(null, "���� Ż���Ͻðڽ��ϱ�?", "ȸ��Ż��", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						logIn login = new logIn();
						login.setVisible(true);
						frame.dispose();
					}
				}
			}
		});
		secession.setBounds(32, 353, 362, 48);
		frame.getContentPane().add(secession);
		
		JButton backMain = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backMain.setBounds(325, 10, 97, 23);
		backMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				friendTap friendtap = new friendTap();
				friendtap.setVisible(true);
				frame.dispose();
			}
		});
		frame.getContentPane().add(backMain);
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
