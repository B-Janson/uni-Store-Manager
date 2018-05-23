package main.java.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import main.java.controller.Store;
import main.java.exceptions.StockException;
import main.java.stock.Item;
import net.miginfocom.swing.MigLayout;

public class ApplicationInterface {

	private JFrame frame;

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
		frame.getContentPane().setLayout(new MigLayout("", "[][][]", "[][][][][][][grow][grow][]"));

		
		JLabel lblSupermart = new JLabel("SuperMart", SwingConstants.LEFT);
		lblSupermart.setHorizontalTextPosition(SwingConstants.LEFT);
		lblSupermart.setForeground(Color.WHITE);
		lblSupermart.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSupermart.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblCapital = new JLabel("Capital:");
		lblCapital.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCapital.setForeground(Color.WHITE);

		JLabel lblCapVal = new JLabel(String.format("$%,.2f", Store.getInstance().getCapital()));
		lblCapVal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCapVal.setForeground(Color.WHITE);
		lblCapVal.setBackground(Color.BLACK);

		
		JPanel panelLbls = new JPanel();
		panelLbls.setBackground(Color.BLACK);
		frame.getContentPane().add(panelLbls, "cell 1 2");

		GridBagLayout gridLayoutLbls = new GridBagLayout();
		panelLbls.setLayout(gridLayoutLbls);
		panelLbls.add(lblSupermart);
		Component horizontalStrut = Box.createHorizontalStrut(180);
		panelLbls.add(horizontalStrut);
		panelLbls.add(lblCapital);
		panelLbls.add(lblCapVal);

		
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

		DefaultTableModel model = (DefaultTableModel) tableInventory.getModel();
		Object rowData[] = new Object[3];
		for (Item item : Store.getInstance().getInventory().getItems()) {
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
		frame.getContentPane().add(scrollPane, "cell 1 4");

		JButton btnSales = new JButton("Read Sales");
		btnSales.setForeground(UIManager.getColor("Button.foreground"));
		btnSales.setBackground(UIManager.getColor("Button.background"));
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        FileNameExtensionFilter filter = new FileNameExtensionFilter(
		                "Comma separated variables", "csv");
		        chooser.setFileFilter(filter);
		        int returnVal = chooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	try {
						Store.getInstance().doSale(chooser.getSelectedFile().getName());
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

			}
		});

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
			}
		});

		JPanel panelBtns = new JPanel();
		panelBtns.setBackground(Color.BLACK);
		frame.getContentPane().add(panelBtns, "cell 1 6,grow");
		panelBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBtns.add(btnOrder);
		panelBtns.add(btnSales);
	}
}
