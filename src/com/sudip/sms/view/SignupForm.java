package com.sudip.sms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sudip.sms.dao.UserDao;
import com.sudip.sms.dto.UserDto;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignupForm extends JInternalFrame {

	private JPanel contentPane;
	public JTextField UserName;
	public JTextField UserAddress;
	public JTextField UserEmail;
	public JTextField UserPhone;
	public JPasswordField UserPassword;
	public JRadioButton rdbtnMale;
	public JRadioButton rdbtnFemale;
	public JComboBox UserCountry;
	public JDateChooser UserDob;
	public JLabel idLbl;
	public JLabel picLabel;
	public String imageUrl="./ImageSave/";
	File file;

	/**
	 * Create the frame.
	 */
	public SignupForm() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		setClosable(true);
		setTitle("Sign Up  Form");
		
		setBounds(100, 100, 393, 500);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.MAGENTA);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 idLbl = new JLabel("");
		idLbl.setBounds(10, 49, 46, 14);
		contentPane.add(idLbl);

		JLabel Username = new JLabel("Name");
		Username.setForeground(Color.BLACK);
		Username.setBounds(10, 123, 63, 23);
		contentPane.add(Username);

		JLabel Useraddress = new JLabel("Address");
		Useraddress.setBounds(10, 191, 63, 23);
		contentPane.add(Useraddress);

		JLabel Useremail = new JLabel("Email");
		Useremail.setBounds(10, 225, 63, 23);
		contentPane.add(Useremail);

		JLabel Userphone = new JLabel("Phone");
		Userphone.setBounds(10, 284, 63, 23);
		contentPane.add(Userphone);

		JLabel Usergender = new JLabel("Gender");
		Usergender.setBounds(10, 318, 63, 23);
		contentPane.add(Usergender);

		JLabel Usercountry = new JLabel("Country");
		Usercountry.setBounds(10, 352, 63, 23);
		contentPane.add(Usercountry);

		UserName = new JTextField();
		UserName.setBounds(107, 123, 142, 22);
		contentPane.add(UserName);
		UserName.setColumns(10);

		UserAddress = new JTextField();
		UserAddress.setColumns(10);
		UserAddress.setBounds(107, 191, 142, 22);
		contentPane.add(UserAddress);

		UserEmail = new JTextField();
		UserEmail.setColumns(10);
		UserEmail.setBounds(107, 225, 142, 22);
		contentPane.add(UserEmail);

		UserPhone = new JTextField();
		UserPhone.setColumns(10);
		UserPhone.setBounds(107, 284, 142, 22);
		contentPane.add(UserPhone);

		 rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(107, 318, 63, 23);
		contentPane.add(rdbtnMale);

		 rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(186, 318, 63, 23);
		contentPane.add(rdbtnFemale);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnMale);
		buttonGroup.add(rdbtnFemale);

		 UserCountry = new JComboBox();
		UserCountry.setModel(
				new DefaultComboBoxModel(new String[] { "Nepal", "India", "China", "USA", "Russia", "England" }));
		UserCountry.setBounds(107, 353, 142, 20);
		contentPane.add(UserCountry);

		JLabel userpassword = new JLabel("Password");
		userpassword.setForeground(Color.BLACK);
		userpassword.setBounds(10, 157, 63, 23);
		contentPane.add(userpassword);

		UserPassword = new JPasswordField();
		UserPassword.setBounds(107, 156, 142, 24);
		contentPane.add(UserPassword);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(10, 404, 71, 34);
		contentPane.add(btnClear);

	 picLabel = new JLabel("              Photo");
		picLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser imgChooser = new JFileChooser();
				if (imgChooser.showOpenDialog(picLabel) == JFileChooser.APPROVE_OPTION) {
					 file = imgChooser.getSelectedFile();
					ImageIcon icon = new ImageIcon(imgChooser.getSelectedFile().getPath());
					Image newImage = icon.getImage().getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
					icon = new ImageIcon(newImage);
					picLabel.setIcon(icon);

				}

			}
		});
		picLabel.setBounds(145, 27, 104, 85);
		contentPane.add(picLabel);

		JLabel Userdob = new JLabel("Date of Birth");
		Userdob.setBounds(10, 248, 89, 34);
		contentPane.add(Userdob);

		UserDob = new JDateChooser();
		UserDob.setBounds(107, 258, 142, 20);
		contentPane.add(UserDob);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDto userDto = new UserDto();
				UserDao userDao = new UserDao();
				userDto.setName(UserName.getText());
				userDto.setPassword(UserPassword.getPassword());
				userDto.setAddress(UserAddress.getText());
				userDto.setEmail(UserEmail.getText());
				userDto.setCountry((String) UserCountry.getSelectedItem());
				userDto.setDob(UserDob.getDate());
				if (rdbtnMale.isSelected())
					userDto.setGender(rdbtnMale.getText());
				if (rdbtnFemale.isSelected())
					userDto.setGender(rdbtnFemale.getText());
				userDto.setPhone(Long.parseLong(UserPhone.getText()));
				
				String filePath=imageUrl+file.getName();
				try {
				BufferedImage bfImage=	ImageIO.read(file);
				ImageIO.write(bfImage, "jpg", new File(filePath));
				} catch (IOException e) {
					e.printStackTrace();
				}
				userDto.setImageUrl(filePath);
				
				String userId=idLbl.getText();
				if(userId==null||userId.isEmpty()){
				userDao.saveUserInfo(userDto);
				}
				else{
					userDto.setId(Integer.parseInt(userId));
					userDao.updateUserInfo(userDto);
					
				}
				showDetail();
				
				
			}
		});
		btnSubmit.setBounds(126, 404, 71, 34);
		contentPane.add(btnSubmit);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showDetail();
			
			}
		});
		btnNewButton.setBounds(255, 404, 89, 34);
		contentPane.add(btnNewButton);

	}
	public void showDetail(){
		JDesktopPane desktopPane =getDesktopPane();
		UserDetail detail =new UserDetail();
		desktopPane.add(detail);
		detail.show();
		dispose();
	}
	
}
