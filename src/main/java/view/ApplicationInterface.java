package main.java.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import main.java.controller.Store;
import main.java.exceptions.StockException;
import main.java.stock.Item;
import net.miginfocom.swing.MigLayout;

public class ApplicationInterface {

	private JFrame frame;
	private DefaultTableModel model;

	/**
	 * 
	 * @param args
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
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow,center][]", "[][][][][][grow][]"));

		JLabel lblSupermart = new JLabel("SuperMart", SwingConstants.CENTER);
		lblSupermart.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSupermart.setForeground(Color.WHITE);
		lblSupermart.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSupermart.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblSupermart, "cell 1 1");
		
		JTable tableInventory = new JTable(0, 3);
		tableInventory
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Quantity", "Reorder?" }) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 8357534138767998861L;
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] { Object.class, String.class, String.class };

					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

		model = (DefaultTableModel) tableInventory.getModel();
		Object rowData[] = new Object[3];
		for (Item item : Store.getInstance().getInventory().getItemList().values()) {
			rowData[0] = item.getName();
			rowData[1] = item.getCurrentAmount();
			if (item.requiresOrder()) {
				rowData[2] = "Yes";
			} else {
				rowData[2] = "No";
			}
			model.addRow(rowData);
		}
		// Make the table vertically scrollable
		JScrollPane scrollPane = new JScrollPane(tableInventory);
		frame.getContentPane().add(scrollPane, "cell 1 3");

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(Color.BLACK);
		splitPane.setResizeWeight(.5d);
		frame.getContentPane().add(splitPane, "cell 1 5, grow");

		JButton btnSales = new JButton("Read Sales");
		btnSales.setForeground(UIManager.getColor("Button.foreground"));
		btnSales.setBackground(UIManager.getColor("Button.background"));
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SalesSelection.ChooseSales(model);
			}
		});
		splitPane.setLeftComponent(btnSales);
		
		JButton btnOrder = new JButton("Generate Order");
		btnOrder.setForeground(UIManager.getColor("Button.foreground"));
		btnOrder.setBackground(UIManager.getColor("Button.background"));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Store.getInstance().generateOrder();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (StockException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Object rowData[] = new Object[3];
				int itemCount = 0;
				for (Item item : Store.getInstance().getInventory().getItemList().values()) {
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
			}
		});
		splitPane.setRightComponent(btnOrder);

	}

}
