package main.java.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import main.java.controller.Store;
import main.java.exceptions.StockException;
import main.java.stock.Item;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SalesSelection {

	private JFrame frame;
	private static DefaultTableModel model;
	private static JLabel lblCapVal;

	/**
	 * 
	 * @param model 
	 * @param args
	 */
	public static void ChooseSales(DefaultTableModel model2, JLabel lblCap) {
		model = model2;
		lblCapVal = lblCap;
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][][]"));
		
		JLabel lblSelectWhichSales = new JLabel("Select which sales log you would like to read");
		frame.getContentPane().add(lblSelectWhichSales, "cell 1 0");
		
		JComboBox<String> comboBoxSales = new JComboBox<>();
		comboBoxSales.setModel(new DefaultComboBoxModel<String>(new String[] {"Sales log 1", "Sales log 2", "Sales log 3", "Sales log 4", "Sales log 5"}));
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
				Object rowData[] = new Object[3];
				int itemCount = 0;
				for (Item item : Store.getInstance().getInventory().getItems()) {
					rowData[0] = item.getName();
					rowData[1] = item.getCurrentAmount();
					if (item.requiresOrder()) {
						rowData[2] = "Yes";
					} else {
						rowData[2] = "No";
					}
					for (int i = 0; i < rowData.length; i++) {
						model.setValueAt(rowData[i], itemCount, i);
					}
					itemCount++;
				}
				lblCapVal.setText(String.format("$%,.2f", Store.getInstance().getCapital()));
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnConfirm, "cell 1 2,alignx center,aligny center");
	}

}
