package UI;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Servers.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class friendTap implements ActionListener{

	private JFrame frame;
	private DefaultListModel model;


	public friendTap() {
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
		frame.setTitle("ģ��â");
		frame.setBounds(100, 100, 450, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton friend = new JButton(peopleResizedIcon);
		friend.setBounds(0, 0, 75, 71);
		friend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				friendAdd friendadd = new friendAdd();
				friendadd.setVisible(true);
				frame.dispose();
			}
		});
		
		JButton chatRoom = new JButton(chatResizedIcon);
		chatRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatTab chattab = new chatTab();
				chattab.setVisible(true);
				frame.dispose();
			}
		});
		chatRoom.setBounds(0, 81, 75, 71);
		
		JButton proFile = new JButton(profileResizedIcon);
		proFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myProfile myprofile = new myProfile();
				myprofile.setVisible(true);
				frame.dispose();
			}
		});
		proFile.setBounds(0, 162, 75, 71);
		
		JButton my_setting = new JButton(settingResizedIcon);
		my_setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setting Setting = new setting();
				Setting.setVisible(true);
				frame.dispose();
			}
		});
		my_setting.setBounds(0, 243, 75, 71);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(friend);
		frame.getContentPane().add(chatRoom);
		frame.getContentPane().add(proFile);
		frame.getContentPane().add(my_setting);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 0, 350, 661);
		frame.getContentPane().add(scrollPane);
		
		model =new DefaultListModel();
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
			
		}
	});
	
		
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
