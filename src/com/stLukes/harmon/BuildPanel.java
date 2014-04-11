package com.stLukes.harmon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class BuildPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static JLabel lblImage, lblTime;
	private ImageIcon image = new ImageIcon(getClass().getResource("stlukes.png"));
	public JTextArea taList;
	public JTable table;
	DefaultTableModel dTableModel;
	public Date ampm;
	private JScrollPane scroll;
	Object[] columns = {"Patient", "Reason", "Length of Time", "Date/Time"};
	Object[][] rows;
	
	public BuildPanel(){
		
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		lblImage = new JLabel();
		
		lblTime = new JLabel();
		ampm = new Date();
		
		dTableModel = new DefaultTableModel(rows, columns);
		table = new JTable(dTableModel);
		table.setAutoCreateRowSorter(true);
		table.setPreferredScrollableViewportSize(new Dimension(750, 100));
		table.setFillsViewportHeight(true);
	
		lblImage.setIcon(image);
		
		Timer time = new Timer(1000, new Listener());
		//time.start();
		
		add(lblImage, BorderLayout.NORTH);
		add(lblTime, BorderLayout.SOUTH);
		add(table, BorderLayout.CENTER);
		
		scroll = new JScrollPane(table);
		add(scroll);
	}
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Calendar now = Calendar.getInstance();
			Date ampm = now.getTime( );
			lblTime.setText("" + ampm);
		}
	}
}
