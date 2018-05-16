package main.java.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import main.java.controller.Store;
import main.java.exceptions.StockException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SalesSelection {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void ChooseSales() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesSelection window = new SalesSelection();
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
	public SalesSelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 452, 178);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][][]"));
		
		JLabel lblSelectWhichSales = new JLabel("Select which sales log you would like to read");
		frame.getContentPane().add(lblSelectWhichSales, "cell 1 0");
		
		JComboBox comboBoxSales = new JComboBox();
		comboBoxSales.setModel(new DefaultComboBoxModel(new String[] {"Sales log 1", "Sales log 2", "Sales log 3", "Sales log 4", "Sales log 5"}));
		frame.getContentPane().add(comboBoxSales, "cell 1 1,growx");
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxSales.getSelectedIndex() == 0) {
					try {
						Store.getInstance().doSale("sales_log_0.csv");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (StockException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(comboBoxSales.getSelectedIndex() == 1){
					try {
						Store.getInstance().doSale("sales_log_1.csv");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (StockException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(comboBoxSales.getSelectedIndex() == 2){
					try {
						Store.getInstance().doSale("sales_log_2.csv");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (StockException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(comboBoxSales.getSelectedIndex() == 3){
					try {
						Store.getInstance().doSale("sales_log_3.csv");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (StockException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(comboBoxSales.getSelectedIndex() == 4){
					try {
						Store.getInstance().doSale("sales_log_4.csv");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (StockException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		frame.getContentPane().add(btnConfirm, "cell 1 2,alignx center,aligny center");
	}

}
