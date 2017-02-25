import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class CapturaInsumos extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;
	private JTextField txtcan;
	private JTextField txtlin;
	private JTextField txtpre;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public CapturaInsumos() {
		setClosable(true);
		setTitle("Captura Insumos");
		setResizable(true);
		setBounds(100, 100, 334, 300);
		getContentPane().setLayout(null);
		
		txtid = new JTextField();
		txtid.setBounds(138, 27, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		JLabel lblId = new JLabel("id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(82, 30, 46, 14);
		getContentPane().add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(58, 55, 70, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblUnidad = new JLabel("Unidad");
		lblUnidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnidad.setBounds(58, 94, 70, 14);
		getContentPane().add(lblUnidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(42, 127, 86, 14);
		getContentPane().add(lblCantidad);
		
		JLabel lblLinea = new JLabel("Linea");
		lblLinea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLinea.setBounds(58, 164, 70, 14);
		getContentPane().add(lblLinea);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecio.setBounds(58, 202, 70, 14);
		getContentPane().add(lblPrecio);
		
		txtnom = new JTextField();
		txtnom.setBounds(138, 58, 86, 20);
		getContentPane().add(txtnom);
		txtnom.setColumns(10);
		
		txtcan = new JTextField();
		txtcan.setBounds(138, 124, 86, 20);
		getContentPane().add(txtcan);
		txtcan.setColumns(10);
		
		JComboBox cmbuni = new JComboBox();
		cmbuni.setModel(new DefaultComboBoxModel(new String[] {"m", "dm", "pieza", "par"}));
		cmbuni.setBounds(138, 91, 86, 20);
		getContentPane().add(cmbuni);
		
		txtlin = new JTextField();
		txtlin.setBounds(138, 161, 86, 20);
		getContentPane().add(txtlin);
		txtlin.setColumns(10);
		
		txtpre = new JTextField();
		txtpre.setText("");
		txtpre.setBounds(138, 199, 86, 20);
		getContentPane().add(txtpre);
		txtpre.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		label.setBounds(234, 30, 46, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		label_1.setBounds(234, 61, 46, 14);
		getContentPane().add(label_1);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		label_3.setBounds(234, 127, 46, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		label_4.setBounds(234, 164, 46, 14);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		label_5.setBounds(234, 202, 46, 14);
		getContentPane().add(label_5);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setMnemonic('A');
		btnAgregar.setBounds(135, 236, 89, 23);
		getContentPane().add(btnAgregar);

	}
}
