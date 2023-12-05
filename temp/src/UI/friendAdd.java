package UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;

public class friendAdd implements ActionListener{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					friendAdd window = new friendAdd();
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
	public friendAdd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ģ�� �߰�");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uCE5C\uAD6C\uCD94\uAC00");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(144, 39, 113, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText("\uC544\uC774\uB514 \uC785\uB825");
		textField.setBounds(144, 66, 131, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 113, 410, 138);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel errorLable = new JLabel("\uC77C\uCE58\uD558\uB294 \uC544\uC774\uB514\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4");
		errorLable.setForeground(new Color(255, 0, 0));
		errorLable.setHorizontalAlignment(SwingConstants.CENTER);
		errorLable.setBounds(87, 90, 238, 15);
		errorLable.setVisible(false);
		frame.getContentPane().add(errorLable);
		
		JButton backMain = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				friendTap friendtap = new friendTap();
				friendtap.setVisible(true);
				frame.dispose();
			}
		});
		backMain.setBounds(334, 10, 88, 21);
		
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
