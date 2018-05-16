package main.java.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class SalesSelection {

	private JFrame frame;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][][]"));
		
		JLabel lblSelectWhichSales = new JLabel("Select which sales log you would like to read");
		frame.getContentPane().add(lblSelectWhichSales, "cell 1 0");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sales log 1", "Sales log 2", "Sales log 3", "Sales log 4", "Sales log 5"}));
		frame.getContentPane().add(comboBox, "cell 1 1,growx");
		
		JButton btnConfirm = new JButton("Confirm");
		frame.getContentPane().add(btnConfirm, "cell 1 2,alignx center,aligny center");
	}

}
