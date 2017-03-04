import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

public class MostrarLinea extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarLinea frame = new MostrarLinea();
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
	public MostrarLinea() {
		setTitle("Linea");
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("Id");
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblid.setBounds(149, 16, 56, 16);
		getContentPane().add(lblid);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setColumns(10);
		txtid.setBounds(221, 13, 116, 22);
		getContentPane().add(txtid);
		
		JLabel lblnom = new JLabel("Nombre");
		lblnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnom.setBounds(130, 66, 75, 16);
		getContentPane().add(lblnom);
		
		JButton butbus = new JButton("Buscar");
		butbus.setMnemonic('B');
		butbus.setBounds(31, 118, 97, 25);
		getContentPane().add(butbus);
		
		JButton butmod = new JButton("Modificar");
		butmod.setMnemonic('M');
		butmod.setEnabled(false);
		butmod.setBounds(185, 118, 97, 25);
		getContentPane().add(butmod);
		
		JButton buteli = new JButton("Eliminar");
		buteli.setMnemonic('E');
		buteli.setEnabled(false);
		buteli.setBounds(351, 118, 97, 25);
		getContentPane().add(buteli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(31, 172, 417, 123);
		getContentPane().add(scrollPane);
		
		txtnom = new JTextField();
		txtnom.setHorizontalAlignment(SwingConstants.CENTER);
		txtnom.setEnabled(false);
		txtnom.setColumns(10);
		txtnom.setBounds(217, 63, 116, 22);
		getContentPane().add(txtnom);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(MostrarLinea.class.getResource("/img/X.png")));
		lbl1.setBounds(351, 66, 46, 14);
		getContentPane().add(lbl1);

	}

}
