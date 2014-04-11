package com.stLukes.harmon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Major extends JFrame{

	private BuildPanel panel;
	private InfoPanel infoPanel;
	private ButtonPanel btnPanel;
	
	public Major() {
		
		setVisible(true);
		setLayout(new BorderLayout());
		setSize(500, 600);
		setResizable(false);
		setTitle("St. Luke's Hospital Organizational Addendum");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panel = new BuildPanel();
		infoPanel = new InfoPanel();
		btnPanel = new ButtonPanel();
		
		add(panel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);
		
		pack();
		
	}
	public class InfoPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private JLabel lblPatient;
		private JLabel lblReason;
		private JLabel lblMinutes;
		private JTextField txtPatient;
		private JTextField txtMinutes;
		private String[] comboBox = {"","Affordable Care Act", "Insurance", "Employee Interaction", "Lunch", "Idle time", 
				"Travel time", "Converse with doctor", "Computer time", "Phone Time", "Meeting"};
		private JComboBox cbReason = new JComboBox(comboBox);
			
		public InfoPanel(){
			
			setLayout(new GridLayout(2, 3));
			setBackground(Color.BLUE);
			
			lblPatient = new JLabel("Patient's name");
			lblReason = new JLabel("Reason");
			lblMinutes = new JLabel("How long");
			
			txtPatient = new JTextField(20);
			txtMinutes = new JTextField(10);
			
			txtPatient.setForeground(Color.GRAY);
			txtPatient.setText("Enter patient name");
			
			txtMinutes.setForeground(Color.GRAY);
			txtMinutes.setText("hh:mm");
			
			cbReason = new JComboBox(comboBox);
			
			add(lblPatient);
			add(lblReason);
			add(lblMinutes);
			add(txtPatient);
			add(cbReason);
			add(txtMinutes);
			
			txtPatient.addFocusListener(new PatientFocusListener());
			txtMinutes.addFocusListener(new MinuteFocusListener());
			
			pack();
		}
			public class PatientFocusListener implements FocusListener{
				public void focusGained(FocusEvent arg0) {
					infoPanel.txtPatient.setText("");
					infoPanel.txtPatient.setForeground(Color.BLACK);
				}
				public void focusLost(FocusEvent arg0) {}
			}
			public class MinuteFocusListener implements FocusListener{
				public void focusGained(FocusEvent arg0) {
					infoPanel.txtMinutes.setText("");
					infoPanel.txtMinutes.setForeground(Color.BLACK);
				}
				public void focusLost(FocusEvent arg0) {}
			}
		}
		public class ButtonPanel extends JPanel {

			private static final long serialVersionUID = 1L;
			private JButton btnPrint, btnClear, btnAdd, btnFile, btnRemove;
			
			public ButtonPanel() {
				
				setBackground(Color.GRAY);
				
				btnPrint = new JButton("Print");
				btnClear = new JButton("Clear");
				btnAdd = new JButton("Add");
				btnRemove = new JButton("Remove");
				btnFile = new JButton("File/Save");
				
				add(btnPrint);
				add(btnClear);
				add(btnAdd);
				add(btnRemove);
				add(btnFile);
				
				btnPrint.addActionListener(new PrintActionListener());
				btnClear.addActionListener(new BtnClearListener());
				btnAdd.addActionListener(new AddActionListener());
				btnRemove.addActionListener(new RemoveActionListener());
				btnFile.addActionListener(new FileActionListener());
				
				pack();
			}
		}
			public class PrintActionListener implements ActionListener{
		
				public void actionPerformed(ActionEvent arg0) {
					try {
						boolean complete = panel.table.print();
						if(complete){
							JOptionPane.showMessageDialog(null, "Done printing","Information", JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, "This document did not print." +
									"", "Printer", JOptionPane.ERROR_MESSAGE);
						}
					} catch (PrinterException e) {
						JOptionPane.showMessageDialog(null, e);
						e.printStackTrace();
					}
				}
			}
			public class BtnClearListener implements ActionListener{
				
				public void actionPerformed(ActionEvent arg0){
					infoPanel.txtPatient.setText("");
					infoPanel.txtMinutes.setText("");
					infoPanel.txtPatient.setForeground(Color.BLACK);
					infoPanel.txtMinutes.setForeground(Color.BLACK);
					infoPanel.cbReason.setSelectedIndex(0);
				}
			}
			public class AddActionListener implements ActionListener{
				private Calendar calendar;
				private Date ampm2;
				public void actionPerformed(ActionEvent e){
					
					String sPatient = "", sReason = "", sLength = "";
					sPatient = infoPanel.txtPatient.getText();
					sReason = (String)infoPanel.cbReason.getSelectedItem();
					sLength = infoPanel.txtMinutes.getText();
					ampm2 = new Date();
					
					Object[] info = {sPatient, sReason, sLength, ampm2.toString()};
					panel.dTableModel.addRow(info);
				}
			}
			public class RemoveActionListener implements ActionListener{
				public void actionPerformed(ActionEvent arg0) {
					panel.dTableModel.removeRow(panel.table.getSelectedRow());
				}
				
			}
			private class FileActionListener implements ActionListener{
				private JFileChooser file = new JFileChooser();
				public void actionPerformed(ActionEvent arg){
					int returnValue = file.showSaveDialog(null);
					if(returnValue == file.APPROVE_OPTION){
						File selectedFile = file.getSelectedFile();
						System.out.println(selectedFile.getName());
					}
				}
			}
}

	
