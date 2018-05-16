package main.java.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import main.java.controller.Store;
import main.java.exceptions.StockException;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ApplicationInterface {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationInterface window = new ApplicationInterface();
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
	public ApplicationInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][grow][][]", "[][][][][][grow][]"));
		
		JLabel lblSupermart = new JLabel("SuperMart", SwingConstants.CENTER);
		lblSupermart.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSupermart.setHorizontalTextPosition(SwingConstants.CENTER);
		frame.getContentPane().add(lblSupermart, "cell 2 1");
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(.5d);
		frame.getContentPane().add(splitPane, "cell 2 5,grow");
		
		JButton btnSales = new JButton("Read Sales");
		btnSales.addActionListener(new ActionListener() {
			int salesCounter = 0;
			String[] logsList = {"sales_log_0", "sales_log_1", "sales_log_2", "sales_log_3","sales_log_4"};
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < logsList.length; i++) {
					if (salesCounter == i) {
						try {
							Store.getInstance().doSale(logsList[i]);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (StockException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				salesCounter++;
			}
		});
		splitPane.setLeftComponent(btnSales);
		
		JButton btnOrder = new JButton("Generate Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Store.getInstance().generateOrder();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (StockException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		splitPane.setRightComponent(btnOrder);
		
		JTable tableInventory = new JTable(25, 3);
		tableInventory.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Name", "Quantity", "Reorder?"
			}
		){
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

	    // Make the table vertically scrollable
	    JScrollPane scrollPane = new JScrollPane(tableInventory);
	    frame.getContentPane().add(scrollPane, "cell 2 3");
	}

}
