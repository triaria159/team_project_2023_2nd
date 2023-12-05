package UI;

import javax.swing.*;

import Servers.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Join_form extends JFrame {
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JLabel nameLabel;
    private JLabel nicknameLabel;
    private JLabel genderLabel;
    private JTextField idField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField nicknameField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton joinButton;
    private JButton duplicateButton;
    private boolean isIdDuplicated = false;
    private Client_frame.LoginListener loginListener;

    public Join_form() {

        setTitle("회원가입");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        titleLabel = new JLabel("회원가입", JLabel.CENTER);
        titleLabel.setFont(new Font("함초롬돋움", Font.BOLD, 40));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        panel.add(formPanel, BorderLayout.CENTER);

        idLabel = new JLabel("ID:", JLabel.RIGHT);
        idLabel.setBounds(170, 130, 80, 30);
        formPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(260, 130, 150, 30);
        formPanel.add(idField);

        duplicateButton = new JButton("중복 확인");
        duplicateButton.setBounds(420, 130, 90, 30);
        formPanel.add(duplicateButton);

        duplicateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAO foodDAO = new DAO();
                if (foodDAO != null) {
                    String id = idField.getText();
                    if (foodDAO.checkDuplicateID(id)) {
                        JOptionPane.showMessageDialog(Join_form.this, "이미 사용 중인 아이디입니다.", "중복", JOptionPane.WARNING_MESSAGE);
                        isIdDuplicated = true;
                    } else {
                        JOptionPane.showMessageDialog(Join_form.this, "사용 가능한 아이디입니다.", "중복 없음", JOptionPane.INFORMATION_MESSAGE);
                        isIdDuplicated = false;
                    }
                }
            }
        });

        passwordLabel = new JLabel("Password:", JLabel.RIGHT);
        passwordLabel.setBounds(170, 175, 80, 30);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(260, 175, 150, 30);
        formPanel.add(passwordField);

        nameLabel = new JLabel("이름:", JLabel.RIGHT);
        nameLabel.setBounds(170, 220, 80, 30);
        formPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(260, 220, 150, 30);
        formPanel.add(nameField);

        nicknameLabel = new JLabel("닉네임:", JLabel.RIGHT);
        nicknameLabel.setBounds(170, 265, 80, 30);
        formPanel.add(nicknameLabel);

        nicknameField = new JTextField();
        nicknameField.setBounds(260, 265, 150, 30);
        formPanel.add(nicknameField);

        genderLabel = new JLabel("성별:", JLabel.RIGHT);
        genderLabel.setBounds(170, 305, 80, 30);
        formPanel.add(genderLabel);

        maleRadioButton = new JRadioButton("남자");
        maleRadioButton.setBounds(260, 305, 60, 30);
        formPanel.add(maleRadioButton);

        femaleRadioButton = new JRadioButton("여자");
        femaleRadioButton.setBounds(325, 305, 60, 30);
        formPanel.add(femaleRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        joinButton = new JButton("회원가입");
        joinButton.setBounds(250, 400, 100, 30);
        formPanel.add(joinButton);

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String nickname = nicknameField.getText();
                int gender = maleRadioButton.isSelected() ? 1 : 2;

                if (isIdDuplicated) {
                    JOptionPane.showMessageDialog(Join_form.this, "아이디 중복 확인을 해주세요.", "회원가입 실패", JOptionPane.WARNING_MESSAGE);
                } else if (id.isEmpty() || password.isEmpty() || name.isEmpty() || nickname.isEmpty() || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())) {
                    JOptionPane.showMessageDialog(Join_form.this, "모든 항목을 입력해주세요.", "회원가입 실패", JOptionPane.WARNING_MESSAGE);
                } else {
                    DAO chatDAO = new DAO();
                    boolean joinSuccess = chatDAO.register(Server.DTO);
                    if (joinSuccess) {
                        JOptionPane.showMessageDialog(Join_form.this, "회원가입이 완료되었습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
                        if (loginListener != null) {
                            loginListener.onLoginSuccess(); 
                        }
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(Join_form.this, "회원가입에 실패하였습니다.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        add(panel);
    }

   
    public void setLoginListener(Client_frame.LoginListener listener) {
        this.loginListener = listener;
    }
}
