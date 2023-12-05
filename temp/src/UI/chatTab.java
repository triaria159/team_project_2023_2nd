package UI;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Checkbox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class chatTab implements ActionListener{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatTab window = new chatTab();
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
	public chatTab() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImageIcon peopleIcon = new ImageIcon("C:\\Users\\ssh01\\eclipse-workspace\\Chat\\image\\people.jfif");
		Image peopleImage = peopleIcon.getImage();
		Image peopleResizedImage = peopleImage.getScaledInstance(75, 71, Image.SCALE_DEFAULT);
		ImageIcon peopleResizedIcon = new ImageIcon(peopleResizedImage);
		
		ImageIcon chatIcon = new ImageIcon("C:\\Users\\ssh01\\eclipse-workspace\\Chat\\image\\chat.png");
		Image chatImage = chatIcon.getImage();
		Image chatResizedImage = chatImage.getScaledInstance(75, 71, Image.SCALE_DEFAULT);
		ImageIcon chatResizedIcon = new ImageIcon(chatResizedImage);
		
		ImageIcon profileIcon = new ImageIcon("C:\\Users\\ssh01\\eclipse-workspace\\Chat\\image\\profile.png");
		Image profileImage = profileIcon.getImage();
		Image profileResizedImage = profileImage.getScaledInstance(75, 71, Image.SCALE_DEFAULT);
		ImageIcon profileResizedIcon = new ImageIcon(profileResizedImage);
		
		ImageIcon settingIcon = new ImageIcon("C:\\Users\\ssh01\\eclipse-workspace\\Chat\\image\\setting.png");
		Image settingImage = settingIcon.getImage();
		Image settingResizedImage = settingImage.getScaledInstance(75, 71, Image.SCALE_DEFAULT);
		ImageIcon settingResizedIcon = new ImageIcon(settingResizedImage);
		
		frame = new JFrame();
		frame.setTitle("ä�ù� ���");
		frame.setBounds(100, 100, 450, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton chatRoom_btu = new JButton(chatResizedIcon);
		chatRoom_btu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chatRoom chatroom = new chatRoom();
					chatroom.setVisible(true);
					frame.dispose();
				}
			});
		chatRoom_btu.setBounds(0, 81, 75, 71);
		frame.getContentPane().add(chatRoom_btu);
		
		JButton proFile = new JButton(profileResizedIcon);
		proFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myProfile myprofile = new myProfile();
				myprofile.setVisible(true);
				frame.dispose();
			}
		});
		proFile.setBounds(0, 162, 75, 71);
		frame.getContentPane().add(proFile);
		
		JButton setting_Btu = new JButton(settingResizedIcon);
		setting_Btu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setting Setting = new setting();
				Setting.setVisible(true);
				frame.dispose();
			}
		});
		setting_Btu.setBounds(0, 243, 75, 71);
		frame.getContentPane().add(setting_Btu);
		
		JButton friend = new JButton(peopleResizedIcon);
		friend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				friendTap friendtap = new friendTap();
				friendtap.setVisible(true);
				frame.dispose();
			}
		});
		friend.setBounds(0, 0, 75, 71);
		frame.getContentPane().add(friend);
		


		
		Button makeChatRoom = new Button("+");
		makeChatRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog jDialog = new JDialog();
				jDialog.setBounds(140, 125,345, 345);
				jDialog.setVisible(true);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(2, 100, 325, 200);
				jDialog.getContentPane().add(scrollPane);
				
				JLabel lblNewLabel = new JLabel("\uB300\uD654\uC0C1\uB300 \uC120\uD0DD");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 10));
				lblNewLabel.setBounds(2, 30, 100, 30);
				jDialog.getContentPane().add(lblNewLabel);
				
				textField = new JTextField();
				textField.setBounds(2, 50, 325, 50);
				jDialog.getContentPane().add(textField);
				textField.setColumns(10);
				
				JList list = new JList();
				scrollPane.setViewportView(list);
				list.addMouseListener(new MouseAdapter() {	
					@Override
					public void mouseClicked(MouseEvent e) {
					
				}
					
			});
				
				jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		makeChatRoom.setBounds(408, 0, 26, 20);
		frame.getContentPane().add(makeChatRoom);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 26, 354, 635);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);

	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
