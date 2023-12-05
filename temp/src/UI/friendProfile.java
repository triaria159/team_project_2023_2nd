package UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class friendProfile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					friendProfile frame = new friendProfile();
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
	public friendProfile() {
		frame.setTitle("ģ�� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton delButton = new JButton("\uCE5C\uAD6C \uC0AD\uC81C");
		delButton.setBounds(12, 10, 97, 23);
		contentPane.add(delButton);
		
		JButton chatButton = new JButton("\uB300\uD654\uD558\uAE30");
		chatButton.setBounds(155, 345, 97, 23);
		contentPane.add(chatButton);
		
		JLabel nameLabel = new JLabel("\uC774\uB984");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(155, 245, 92, 23);
		contentPane.add(nameLabel);
		
		JLabel stateLabel = new JLabel("\uC0C1\uD0DC \uBA54\uC2DC\uC9C0");
		stateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stateLabel.setBounds(155, 278, 92, 23);
		contentPane.add(stateLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 43, 410, 192);
		contentPane.add(panel);
		
		JButton backMain = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backMain.setBounds(325, 378, 97, 23);
		backMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				friendTap friendtap = new friendTap();
				friendtap.setVisible(true);
				frame.dispose();
			}
		});
		contentPane.add(backMain);
		
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
