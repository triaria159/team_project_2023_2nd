package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class setting implements ActionListener{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setting window = new setting();
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
	public setting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("����");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton accountSetting = new JButton("\uACC4\uC815\uC124\uC815");
		accountSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myProfile myprofile = new myProfile();
				myprofile.setVisible(true);
				frame.dispose();
			}
		});
		accountSetting.setBounds(77, 76, 101, 79);
		frame.getContentPane().add(accountSetting);
		
		JButton profileSetting = new JButton("\uD504\uB85C\uD544\r\n\uC124\uC815");
		profileSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		profileSetting.setBounds(259, 76, 101, 79);
		frame.getContentPane().add(profileSetting);
		
		JButton backMain = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backMain.setBounds(325, 228, 97, 23);
		backMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				friendTap friendtap = new friendTap();
				friendtap.setVisible(true);
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
