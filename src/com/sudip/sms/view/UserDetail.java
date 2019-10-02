package com.sudip.sms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sudip.sms.dao.UserDao;
import com.sudip.sms.dto.UserDto;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class UserDetail extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField searchField;
	
	
	
	/**
	 * Create the frame.
	 */
	public UserDetail() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		setClosable(true);
		setResizable(false);
		
		setBounds(100, 100, 673, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		String[] columnName={"Id", "Name", "Address", "Email", "Country", "Phone", "Gender","Dob","Image Url"};
		DefaultTableModel tableModel =new  DefaultTableModel(columnName, 0);
		table.setModel(tableModel);
		
		table.setSize( 645, 440);
		
		JScrollPane jScrollPane =new JScrollPane(table);
		jScrollPane.setLocation(0,51);
		jScrollPane.setSize(667,309);
		contentPane.add(jScrollPane);
		loadDataInTable();
		
		searchField = new JTextField();
		searchField.setBounds(442, 20, 99, 20);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName=searchField.getText();
				if(userName==null||userName.isEmpty()){
					loadDataInTable();
					
				}
				else{
					searchInTable(userName);
				}
			}
		});
		btnSearch.setBounds(553, 17, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDesktopPane pane=getDesktopPane();
				SignupForm form =new SignupForm();
				 pane.add(form);
				 form.show();
				 dispose();
				}
		});
		btnNewButton.setBounds(10, 395, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnDelet = new JButton("Delete");
		btnDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel  defaultTableModel=(DefaultTableModel) table.getModel();
				int row=table.getSelectedRow();
				Object id=defaultTableModel.getValueAt(row, 0);
				int status=JOptionPane.showConfirmDialog(UserDetail.this, "Do you want to delete?","Delete",JOptionPane.YES_NO_OPTION);
				if(status==0){
					UserDao dao=new UserDao();
					dao.deletData(Integer.parseInt(id.toString()));
					loadDataInTable();
					
				
				}
			}
		});
		btnDelet.setBounds(300, 395, 89, 23);
		contentPane.add(btnDelet);
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel  defaultTableModel=(DefaultTableModel) table.getModel();
				int row=table.getSelectedRow();
				Object id=defaultTableModel.getValueAt(row, 0);
				Object name=defaultTableModel.getValueAt(row, 1);
				Object address=defaultTableModel.getValueAt(row, 2);
				Object email=defaultTableModel.getValueAt(row, 3);
				Object country=defaultTableModel.getValueAt(row, 4);
				Object phone=defaultTableModel.getValueAt(row, 5);
				Object gender=defaultTableModel.getValueAt(row, 6);
				Object dob=defaultTableModel.getValueAt(row, 7);
				Object image=defaultTableModel.getValueAt(row, 8);
				SignupForm form =new SignupForm();
				form.idLbl.setText(id.toString());
				form.UserName.setText(name.toString());
				form.UserAddress.setText(address.toString());
				form.UserEmail.setText(email.toString());
				form.UserPhone.setText(phone.toString());
				form.UserCountry.setSelectedItem(country);
				
				ImageIcon icon = new ImageIcon(image.toString());
				Image newImage = icon.getImage().getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(newImage);
				form.file=new File(image.toString());
						
				form.picLabel.setIcon(icon);
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
				Date dobs=null;;
				try {
					dobs=dateFormat.parse(dob.toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				form.UserDob.setDate(dobs);
				String genders=gender.toString();
				if(genders.equalsIgnoreCase("Male"))
					form.rdbtnMale.setSelected(true);
				else
					form.rdbtnFemale.setSelected(true);
				JDesktopPane pane=getDesktopPane();
				 pane.add(form);
				 form.show();
				 dispose();
			}
		});
		btnEdit.setBounds(153, 395, 89, 23);
		contentPane.add(btnEdit);
		loadDataInTable();
		
	}
	
	public void loadDataInTable(){
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		UserDao userDao=new UserDao();
		List<UserDto> userList =new UserDao().getAlldetail();
		for(UserDto userDto:userList){
			model.addRow(new Object[]{userDto.getId(),userDto.getName(),userDto.getAddress(),userDto.getEmail(),userDto.getCountry(),userDto.getPhone(),userDto.getGender(),userDto.getDob(), userDto.getImageUrl()});
		}
	}
	public void searchInTable(String userName){
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		UserDao userDao=new UserDao();
		List<UserDto> userList =new UserDao().searchdDetail(userName);
		for(UserDto userDto:userList){
			model.addRow(new Object[]{userDto.getId(),userDto.getName(),userDto.getAddress(),userDto.getEmail(),userDto.getCountry(),userDto.getPhone(),userDto.getGender(),userDto.getDob(), userDto.getImageUrl()});
		}
	}

}
