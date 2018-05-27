package main.java.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import main.java.controller.Store;
import main.java.exceptions.CSVException;
import main.java.exceptions.StockException;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import net.miginfocom.swing.MigLayout;

/**
 * Front-end class displays store name, capital and current inventory. Allows
 * user to generate an order when items require reordering or select a weekly
 * sales log to read. Inventory and capital adjusts accordingly.
 * 
 * @author Chris Martin
 */
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
					Store.getInstance().reset();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"The program could not find the file 'item_properties' in this directory", "File Not Found",
							JOptionPane.ERROR_MESSAGE);
				} catch (CSVException e) {
					JOptionPane.showMessageDialog(null,
							"The file 'item_properties' does not conform to the expected standard.", "CSV Error",
							JOptionPane.ERROR_MESSAGE);
				}
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
	 * Initialise the contents of the frame.
	 */
	/**
	 * 
	 */
	private void initialize() {
		/*
		 * Sets up the frame
		 */
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1000, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[100px:n,grow,fill]", "[][grow,center][grow]"));

		/**
		 * Two labels created to display store name and capital. Capital label adjusts
		 * accordingly throughout use. Labels added into panel.
		 */
		JLabel lblSupermart = new JLabel("SuperMart", SwingConstants.LEFT);
		lblSupermart.setHorizontalTextPosition(SwingConstants.LEFT);
		lblSupermart.setForeground(Color.WHITE);
		lblSupermart.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSupermart.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblCapital = new JLabel("Capital:" + String.format("$%,.2f", Store.getInstance().getCapital()));
		lblCapital.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCapital.setForeground(Color.WHITE);

		JPanel panelLbls = new JPanel();
		panelLbls.setBackground(Color.BLACK);
		frame.getContentPane().add(panelLbls, "cell 0 0");

		GridBagLayout gridLayoutLbls = new GridBagLayout();
		panelLbls.setLayout(gridLayoutLbls);
		panelLbls.add(lblSupermart);
		Component horizontalStrut = Box.createHorizontalStrut(180);
		panelLbls.add(horizontalStrut);
		panelLbls.add(lblCapital);

		/**
		 * JTable created to display inventory in GUI. Consists of three rows: item
		 * name; item quantity; whether or not item needs to be reordered. Table is
		 * updated as manifests are added and weekly sales logs are taken away.
		 */
		JTable tableInventory = new JTable(0, 8);
		tableInventory.setEnabled(false);

		tableInventory.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Reorder?", "Quantity",
				"Cost", "Price", "Reorder Point", "Reorder Amount", "Temperature" }) {
			/**
			 * Auto-Generated serialVersionID Used for DefaultTableModel as it implements
			 * Serializable. Not needed but is good to have.
			 */
			private static final long serialVersionUID = 8357534138767998861L;

			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class };
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		updateTable(tableInventory, true);

		/**
		 * Make the table vertically scrollable
		 */
		JScrollPane scrollPane = new JScrollPane(tableInventory);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setSize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
		frame.getContentPane().add(scrollPane, "cell 0 1,alignx center");

		/**
		 * Buttons created to implement doSale and generateOrder method. Upon clicking,
		 * the appropriate method is run, and the capital and inventory is then
		 * adjusted.
		 */
		JButton btnItemProperties = new JButton("Load Item Properties");
		JButton btnSales = new JButton("Read Sales");
		JButton btnOrder = new JButton("Generate Order");

		btnItemProperties.setForeground(UIManager.getColor("Button.foreground"));
		btnItemProperties.setBackground(UIManager.getColor("Button.background"));
		btnItemProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma separated variables", "csv");
				chooser.setFileFilter(filter);

				// Open the dialog in the current directory
				File workingDirectory = new File(System.getProperty("user.dir"));
				chooser.setCurrentDirectory(workingDirectory);
				int returnVal = chooser.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						Store.getInstance().loadItems(chooser.getSelectedFile().getCanonicalPath());
						updateTable(tableInventory, true);
						btnItemProperties.setEnabled(false);
						btnOrder.setEnabled(true);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null,
								"Unable to read this file. Make sure the csv file actually exists or that it is not in use.",
								"Unable to access file", JOptionPane.ERROR_MESSAGE);
					} catch (CSVException e) {
						JOptionPane.showMessageDialog(null,
								"This item properties does not contain the correct number of columns.\n"
										+ "Item properties files should be:\n"
										+ "\titem_name,cost,price,reorderpoint,reorderamount,temperature",
								"Incorrect CSV File", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnSales.setForeground(UIManager.getColor("Button.foreground"));
		btnSales.setBackground(UIManager.getColor("Button.background"));
		btnSales.setEnabled(false);
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * If the Sales button is pressed: 
				 * Open a FileChooser 
				 * Return the path and file name of the sales log 
				 * Use this file name to perform the sale 
				 * Update the Window.
				 */
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma separated variables", "csv");
				chooser.setFileFilter(filter);

				// Open the dialog in the current directory
				File workingDirectory = new File(System.getProperty("user.dir"));
				chooser.setCurrentDirectory(workingDirectory);
				int returnVal = chooser.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						Store.getInstance().doSale(chooser.getSelectedFile().getCanonicalPath());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null,
								"Unable to read this file. Make sure the csv file actually exists or that it is not in use.",
								"Unable to access file", JOptionPane.ERROR_MESSAGE);
					} catch (StockException e) {
						JOptionPane.showMessageDialog(null,
								"This Sale would result in negative stock. Please double check that you have enough stock before performing this sale.",
								"Sale Not Legitmate", JOptionPane.ERROR_MESSAGE);
					} catch (CSVException e) {
						JOptionPane.showMessageDialog(null,
								"This sales log does not contain the correct number of columns.\n"
										+ "Sales log files should be:\n" + "\titem_name,amount\n\titem_name,amount",
								"Incorrect CSV File", JOptionPane.ERROR_MESSAGE);
					}
				}
				// Returns true if any items fall below their reorder point as a result of this
				// action.
				boolean firstTable = false;
				boolean enableGenerateOrder = updateTable(tableInventory, firstTable);
				btnOrder.setEnabled(enableGenerateOrder);

				// Update the capital label
				lblCapital.setText("Capital:" + String.format("$%,.2f", Store.getInstance().getCapital()));
			}
		});

		btnOrder.setForeground(UIManager.getColor("Button.foreground"));
		btnOrder.setBackground(UIManager.getColor("Button.background"));
		btnOrder.setEnabled(false);
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * If the Generate Order button is pressed: 
				 * Generate a Manifest based on the current state of the store's inventory 
				 * Print this manifest to manifest.csv
				 * Read this manifest in and update the stock 
				 * Update the Window.
				 */
				try {
					Store.getInstance().generateOrder();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Unable to read this file. Make sure it actually exists or that it is not in use.",
							"Unable to access file", JOptionPane.ERROR_MESSAGE);
				} catch (StockException e1) {
					JOptionPane.showMessageDialog(null,
							"This error should never occur. It will only occur if by some miracle, you have managed to add negative stock from an order.",
							"Order Failed", JOptionPane.ERROR_MESSAGE);
				}
				boolean firstTable = false;
				updateTable(tableInventory, firstTable);
				lblCapital.setText("Capital:" + String.format("$%,.2f", Store.getInstance().getCapital()));
				btnSales.setEnabled(true);
				btnOrder.setEnabled(false);
			}
		});

		JPanel panelBtns = new JPanel();
		panelBtns.setBackground(Color.BLACK);
		frame.getContentPane().add(panelBtns, "cell 0 2,grow");
		panelBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBtns.add(btnItemProperties);
		panelBtns.add(btnOrder);
		panelBtns.add(btnSales);
	}

	/**
	 * Internal function to update the state of the table. Used to prevent repeated
	 * code.
	 *
	 * @param tableInventory
	 *            the table to update
	 * @param firstTable
	 *            true if this is the first time setting up, false if editing
	 * @return true if the generateOrder button should be enabled.
	 */

	private boolean updateTable(JTable tableInventory, boolean firstTable) {
		boolean enableGenerateOrder = false;
		DefaultTableModel model = (DefaultTableModel) tableInventory.getModel();
		Object rowData[] = new Object[8];
		int itemCount = 0;
		for (Item item : Store.getInstance().getInventory().getItems()) {
			rowData[0] = item.getName();
			if (item.requiresOrder()) {
				rowData[1] = "Yes";
				enableGenerateOrder = true;
			} else {
				rowData[1] = "No";
			}
			rowData[2] = item.getCurrAmount();
			rowData[3] = item.getCost();
			rowData[4] = item.getPrice();
			rowData[5] = item.getReorderPoint();
			rowData[6] = item.getReorderAmount();
			if (item instanceof ColdItem) {
				rowData[7] = ((ColdItem) item).getTemperature();
			} else {
				rowData[7] = null;
			}

			if (firstTable) {
				model.addRow(rowData);
			} else {
				for (int i = 0; i < rowData.length; i++) {
					model.setValueAt(rowData[i], itemCount, i);
				}
				itemCount++;
			}

		}

		return enableGenerateOrder;
	}
}
