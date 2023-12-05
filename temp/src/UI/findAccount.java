package UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;

public class findAccount implements ActionListener{

	private JFrame frame;
	private JTextField idName;
	private JTextField idPhone;
	private JLabel passwordFind;
	private JTextField pwId;
	private JTextField pwName;
	private JTextField pwPhone;
	private JButton id_Find;
	private JButton pw_Find;
	private JPanel panel_1;
	private JButton backMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					findAccount window = new findAccount();
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
	public findAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("����ã��");
		frame.setBounds(100, 100, 450, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(63, 10, 248, 109);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel idError = new JLabel("\uC77C\uCE58\uD558\uB294 \uC815\uBCF4\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4");
		idError.setForeground(new Color(255, 0, 0));
		idError.setBounds(0, 90, 151, 15);
		panel.add(idError);
		idError.setVisible(false);
		
		JLabel idFind = new JLabel("\uC544\uC774\uB514 \uCC3E\uAE30");
		idFind.setBounds(86, 0, 141, 29);
		panel.add(idFind);
		idFind.setHorizontalAlignment(SwingConstants.CENTER);
		
		idName = new JTextField();
		idName.setBounds(69, 27, 179, 21);
		panel.add(idName);
		idName.setColumns(10);
		
		JLabel id_Name = new JLabel("\uC774\uB984");
		id_Name.setBounds(27, 30, 30, 15);
		panel.add(id_Name);
		
		JLabel id_Phone = new JLabel("\uC804\uD654 \uBC88\uD638");
		id_Phone.setBounds(0, 61, 57, 15);
		panel.add(id_Phone);
		
		idPhone = new JTextField();
		idPhone.setBounds(69, 58, 179, 21);
		panel.add(idPhone);
		idPhone.setColumns(10);
		
		id_Find = new JButton("\uCC3E\uAE30");
		id_Find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = 0;
				if(a == 0) {
					JOptionPane.showConfirmDialog(null, "ȸ������ ���̵�� " + "test" +"�Դϴ�", "", JOptionPane.PLAIN_MESSAGE);
					logIn login = new logIn();
					login.setVisible(true);
					frame.dispose();
				}
				else {
					idError.setVisible(true);
				}
			}
		});
		id_Find.setBounds(151, 86, 97, 23);
		panel.add(id_Find);
		
		panel_1 = new JPanel();
		panel_1.setBounds(70, 129, 241, 147);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel pwError = new JLabel("\uC77C\uCE58\uD558\uB294 \uC815\uBCF4\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4");
		pwError.setForeground(Color.RED);
		pwError.setBounds(0, 128, 151, 15);
		panel_1.add(pwError);
		pwError.setVisible(false);
		
		passwordFind = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		passwordFind.setBounds(100, 0, 78, 15);
		panel_1.add(passwordFind);
		
		JLabel pw_Id = new JLabel("\uC544\uC774\uB514");
		pw_Id.setBounds(16, 28, 36, 15);
		panel_1.add(pw_Id);
		
		JLabel pw_Name = new JLabel("\uC774\uB984");
		pw_Name.setBounds(26, 58, 24, 15);
		panel_1.add(pw_Name);
		
		JLabel pw_Phone = new JLabel("\uC804\uD654 \uBC88\uD638");
		pw_Phone.setBounds(0, 91, 52, 15);
		panel_1.add(pw_Phone);
		
		pwId = new JTextField();
		pwId.setBounds(62, 25, 179, 21);
		panel_1.add(pwId);
		pwId.setColumns(10);
		
		pwName = new JTextField();
		pwName.setBounds(62, 56, 179, 21);
		panel_1.add(pwName);
		pwName.setColumns(10);
		
		pwPhone = new JTextField();
		pwPhone.setBounds(62, 88, 179, 21);
		panel_1.add(pwPhone);
		pwPhone.setColumns(10);
		
		pw_Find = new JButton("\uCC3E\uAE30");
		pw_Find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = 0;
				if(a == 0) {
					JOptionPane.showConfirmDialog(null, "ȸ������ ��й�ȣ�� " + "test" +"�Դϴ�", "", JOptionPane.PLAIN_MESSAGE);
					logIn login = new logIn();
					login.setVisible(true);
					frame.dispose();
				}
				else {
					pwError.setVisible(true);
				}
			}
		});
		pw_Find.setBounds(144, 124, 97, 23);
		panel_1.add(pw_Find);
		
		backMain = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backMain.setBounds(325, 253, 97, 23);
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
