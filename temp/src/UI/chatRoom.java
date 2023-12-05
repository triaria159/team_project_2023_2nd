package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class chatRoom implements ActionListener{

	private JFrame frame;
	private JTextField inputText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatRoom window = new chatRoom();
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
	public chatRoom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ä�ù�");
		frame.setBounds(100, 100, 450, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 13, 434, 600);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		JButton showMember = new JButton("New button");
		showMember.setBounds(400, 0, 34, 13);
		frame.getContentPane().add(showMember);
		
		inputText = new JTextField();
		inputText.setBounds(0, 613, 395, 48);
		frame.getContentPane().add(inputText);
		inputText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String message = inputText.getText(); // �Էµ� �޽��� ��������
                sendMessage(message, true); // �޽��� ������ (�����ʿ� ǥ��)
                inputText.setText(""); // �Է� �ʵ� �ʱ�ȭ
            }
            
            private void sendMessage(String message, boolean isMyMessage) {
                // ���� �ð� ��������
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String time = sdf.format(new Date());

                // �޽��� ����: [�ð�] �޽���
                String formattedMessage = message + "\n[" + time + "] ";

                // �ؽ�Ʈ ������ �޽��� �߰�
                String alignment = isMyMessage ? "��" : "���";
                textArea.append(alignment + ": " + formattedMessage + "\n");
            }
        });
		inputText.setColumns(10);
		
		JButton sendFile = new JButton("New button");
		sendFile.setBounds(393, 613, 41, 48);
		frame.getContentPane().add(sendFile);
		
		JButton backMain = new JButton("New button");
		backMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				friendTap friendtap = new friendTap();
				friendtap.setVisible(true);
				frame.dispose();
			}
		});
		backMain.setBounds(351, 0, 49, 13);
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
