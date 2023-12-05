package UI;

import javax.swing.*;

import Servers.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Client_frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2525004457496324369L;
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton joinButton;

    private LoginListener loginListener;

    public Client_frame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("아이디:");
        usernameLabel.setBounds(30, 30, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(110, 30, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setBounds(30, 65, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 65, 160, 25);
        panel.add(passwordField);

        loginButton = new JButton("로그인");
        loginButton.setBounds(30, 110, 100, 25);
        panel.add(loginButton);

        joinButton = new JButton("회원가입");
        joinButton.setBounds(170, 110, 100, 25);
        panel.add(joinButton);

        add(panel);

        joinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                boolean loginSuccess = DAO.login(username, password);

                if (loginSuccess) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    setLoginListener(new LoginListener() {
                        @Override
                        public void onLoginSuccess() {
                          friendTap frame = new friendTap();
                            frame.setVisible(true);
                        }
                    });
                    dispose();
                    if (loginListener != null) {
                        loginListener.onLoginSuccess();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login Failed");
                }
            }
        });

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Join_form joinForm = new Join_form();
                joinForm.setLoginListener(new LoginListener() {
                    @Override
                    public void onLoginSuccess() {
                        Client_frame fra = new Client_frame();
                        fra.setVisible(true);
                    }
                });
                joinForm.setVisible(true);
                dispose();
            }
        });
    }

    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    public interface LoginListener {
        void onLoginSuccess();
    }
}
