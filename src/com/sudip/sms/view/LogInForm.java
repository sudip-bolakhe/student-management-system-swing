package com.sudip.sms.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LogInForm extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	/**
	 * Create the frame.
	 */
	public LogInForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(193, 41, 90, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(193, 90, 90, 26);
		contentPane.add(lblNewLabel_1);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(293, 41, 131, 26);
		contentPane.add(usernameField);
		
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(293, 93, 131, 26);
		contentPane.add(passwordField);
		
		
		JButton logInBtn = new JButton("Log In");
		logInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName= usernameField.getText();
				char[] pass=passwordField.getPassword();
				String password=new String(pass);
				if(userName.equalsIgnoreCase("admin")&&password.equalsIgnoreCase("admin")){
					new Mainframe().setVisible(true);
					dispose();
				}else
					JOptionPane.showMessageDialog(LogInForm.this, "username and password donot match");
				usernameField.setText("");
				passwordField.setText("");
			}
		});
		logInBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logInBtn.setBounds(293, 141, 89, 23);
		contentPane.add(logInBtn);
		
		JLabel label = new JLabel("");
		Image image=new ImageIcon(this.getClass().getResource("/icon1.png")).getImage();
		label.setIcon(new ImageIcon(image));
		label.setBounds(0, 11, 183, 197);
		contentPane.add(label);
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInForm frame = new LogInForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

	
