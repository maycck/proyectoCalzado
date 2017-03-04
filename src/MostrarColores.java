import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

public class MostrarColores extends JInternalFrame {
	private JTextField txtid;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarColores frame = new MostrarColores();
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
	public MostrarColores() {
		setTitle("Colores");
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("Id");
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblid.setBounds(144, 16, 56, 16);
		getContentPane().add(lblid);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setColumns(10);
		txtid.setBounds(216, 13, 116, 22);
		getContentPane().add(txtid);
		
		JLabel lblnom = new JLabel("Nombre");
		lblnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnom.setBounds(125, 66, 75, 16);
		getContentPane().add(lblnom);
		
		JButton butbus = new JButton("Buscar");
		butbus.setMnemonic('B');
		butbus.setBounds(26, 118, 97, 25);
		getContentPane().add(butbus);
		
		JButton butmod = new JButton("Modificar");
		butmod.setMnemonic('M');
		butmod.setEnabled(false);
		butmod.setBounds(180, 118, 97, 25);
		getContentPane().add(butmod);
		
		JButton buteli = new JButton("Eliminar");
		buteli.setMnemonic('E');
		buteli.setEnabled(false);
		buteli.setBounds(346, 118, 97, 25);
		getContentPane().add(buteli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(26, 172, 417, 123);
		getContentPane().add(scrollPane);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		textField.setBounds(212, 63, 116, 22);
		getContentPane().add(textField);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(MostrarColores.class.getResource("/img/X.png")));
		lbl1.setBounds(346, 71, 46, 14);
		getContentPane().add(lbl1);

	}

}
