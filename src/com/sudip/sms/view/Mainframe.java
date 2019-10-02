package com.sudip.sms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import java.awt.Font;

public class Mainframe extends JFrame {

	private JPanel contentPane;
	JLabel clockLbl;

	public JDesktopPane desktopPane;

	/**
	 * Create the frame.
	 */

	public Mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int heigth = dimension.height;
		int width = dimension.width;
		setSize(width, heigth);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 71, 1366, 689);
		desktopPane.setBackground(Color.MAGENTA);
		contentPane.add(desktopPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1350, 26);
		contentPane.add(menuBar);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);

		

		JMenuItem mntmCalculator = new JMenuItem("Calculator");
		mntmCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Runtime runtime= Runtime.getRuntime();
				try {
					runtime.exec("calc.exe");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmNotepad = new JMenuItem("Notepad");
		mntmNotepad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runtime runtime=Runtime.getRuntime();
				
					try {
						runtime.exec("notepad.exe");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				
				
			}
		});
		mnTools.add(mntmNotepad);
		
		mnTools.add(mntmCalculator);

		JMenuItem mntmStudentView = new JMenuItem("Student View");
		mntmStudentView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDetail detail = new UserDetail();
				desktopPane.add(detail);
				detail.show();
				

			}
		});
		mnView.add(mntmStudentView);
		
		 clockLbl = new JLabel("Clock");
		 clockLbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		clockLbl.setBounds(486, 21, 567, 51);
		contentPane.add(clockLbl);
		clock();
		
	}
	
	public void clock()
	{
		Thread clock = new Thread(){
			public void run(){
				for(;;){
				Calendar cal= new GregorianCalendar();
				int day=cal.get(Calendar.DAY_OF_MONTH);
				int month=cal.get(Calendar.MONTH);
				int year=cal.get(Calendar.YEAR);
				 int second =cal.get(Calendar.SECOND);
				 int minute=cal.get(Calendar.MINUTE);
				 int hour=cal.get(Calendar.HOUR);
				 
				 clockLbl.setText("Time:  " +hour+" : "+minute+" : "+second+ "     Date  "+year+"/ "+month+"/ "+day);
				 	try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				 
			}
		};
		clock.start();
	}


	
}
