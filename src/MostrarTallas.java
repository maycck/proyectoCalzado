import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;

public class MostrarTallas extends JInternalFrame {
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarTallas frame = new MostrarTallas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MostrarTallas() {
		setTitle("Tallas");
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("Id");
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblid.setBounds(153, 16, 56, 16);
		getContentPane().add(lblid);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setColumns(10);
		txtid.setBounds(225, 13, 116, 22);
		getContentPane().add(txtid);
		
		JLabel lbltal = new JLabel("Talla");
		lbltal.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltal.setBounds(134, 66, 75, 16);
		getContentPane().add(lbltal);
		
		JButton butbus = new JButton("Buscar");
		butbus.setMnemonic('B');
		butbus.setBounds(35, 118, 97, 25);
		getContentPane().add(butbus);
		
		JButton butmod = new JButton("Modificar");
		butmod.setMnemonic('M');
		butmod.setEnabled(false);
		butmod.setBounds(189, 118, 97, 25);
		getContentPane().add(butmod);
		
		JButton buteli = new JButton("Eliminar");
		buteli.setMnemonic('E');
		buteli.setEnabled(false);
		buteli.setBounds(355, 118, 97, 25);
		getContentPane().add(buteli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(35, 172, 417, 123);
		getContentPane().add(scrollPane);
		
		JSpinner spital = new JSpinner();
		spital.setEnabled(false);
		spital.setModel(new SpinnerNumberModel(17, 10, 40, 1));
		spital.setBounds(225, 67, 65, 20);
		getContentPane().add(spital);
		
		JCheckBox chbtal = new JCheckBox("medio 1/2");
		chbtal.setEnabled(false);
		chbtal.setBounds(296, 66, 97, 23);
		getContentPane().add(chbtal);

	}
}
