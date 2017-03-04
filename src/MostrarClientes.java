import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

public class MostrarClientes extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;
	private JTextField txtapp;
	private JTextField txtapm;
	private JTextField txtdir;
	private JTextField txttel;
	private JTextField txtema;
	private JTextField txtrfc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarClientes frame = new MostrarClientes();
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
	public MostrarClientes() {
		setTitle("Clientes");
		getContentPane().setEnabled(false);
		setClosable(true);
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("Id");
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblid.setBounds(12, 16, 56, 16);
		getContentPane().add(lblid);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setColumns(10);
		txtid.setBounds(84, 13, 116, 22);
		getContentPane().add(txtid);
		
		JLabel lblnom = new JLabel("Nombre");
		lblnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnom.setBounds(248, 19, 56, 16);
		getContentPane().add(lblnom);
		
		txtnom = new JTextField();
		txtnom.setColumns(10);
		txtnom.setBounds(320, 16, 116, 22);
		getContentPane().add(txtnom);
		
		JLabel lblapp = new JLabel("Apellido Pat");
		lblapp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblapp.setBounds(131, 54, 75, 16);
		getContentPane().add(lblapp);
		
		txtapp = new JTextField();
		txtapp.setEnabled(false);
		txtapp.setColumns(10);
		txtapp.setBounds(222, 51, 116, 22);
		getContentPane().add(txtapp);
		
		JLabel lblapm = new JLabel("Apellido Mat");
		lblapm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblapm.setBounds(131, 86, 75, 16);
		getContentPane().add(lblapm);
		
		txtapm = new JTextField();
		txtapm.setEnabled(false);
		txtapm.setColumns(10);
		txtapm.setBounds(222, 83, 116, 22);
		getContentPane().add(txtapm);
		
		JLabel lbldir = new JLabel("Direccion");
		lbldir.setHorizontalAlignment(SwingConstants.RIGHT);
		lbldir.setBounds(150, 118, 56, 16);
		getContentPane().add(lbldir);
		
		txtdir = new JTextField();
		txtdir.setEnabled(false);
		txtdir.setColumns(10);
		txtdir.setBounds(222, 115, 116, 22);
		getContentPane().add(txtdir);
		
		JLabel lbltel = new JLabel("Telefono");
		lbltel.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltel.setBounds(150, 150, 56, 16);
		getContentPane().add(lbltel);
		
		txttel = new JTextField();
		txttel.setEnabled(false);
		txttel.setColumns(10);
		txttel.setBounds(222, 147, 116, 22);
		getContentPane().add(txttel);
		
		JLabel lblema = new JLabel("Email");
		lblema.setHorizontalAlignment(SwingConstants.RIGHT);
		lblema.setBounds(150, 182, 56, 16);
		getContentPane().add(lblema);
		
		txtema = new JTextField();
		txtema.setEnabled(false);
		txtema.setColumns(10);
		txtema.setBounds(222, 179, 116, 22);
		getContentPane().add(txtema);
		
		JLabel lblrfc = new JLabel("RFC");
		lblrfc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblrfc.setBounds(150, 216, 56, 16);
		getContentPane().add(lblrfc);
		
		txtrfc = new JTextField();
		txtrfc.setEnabled(false);
		txtrfc.setColumns(10);
		txtrfc.setBounds(222, 213, 116, 22);
		getContentPane().add(txtrfc);
		
		JButton butbus = new JButton("Buscar");
		butbus.setMnemonic('B');
		butbus.setBounds(36, 278, 97, 25);
		getContentPane().add(butbus);
		
		JButton butmod = new JButton("Modificar");
		butmod.setEnabled(false);
		butmod.setMnemonic('M');
		butmod.setBounds(190, 278, 97, 25);
		getContentPane().add(butmod);
		
		JButton buteli = new JButton("Eliminar");
		buteli.setEnabled(false);
		buteli.setMnemonic('E');
		buteli.setBounds(356, 278, 97, 25);
		getContentPane().add(buteli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(36, 330, 417, 123);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl1.setBounds(350, 56, 46, 14);
		getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl2.setBounds(350, 88, 46, 14);
		getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl3.setBounds(350, 120, 46, 14);
		getContentPane().add(lbl3);
		
		JLabel lbl4 = new JLabel("");
		lbl4.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl4.setBounds(350, 150, 46, 14);
		getContentPane().add(lbl4);
		
		JLabel lbl5 = new JLabel("");
		lbl5.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl5.setBounds(350, 182, 46, 14);
		getContentPane().add(lbl5);
		
		JLabel lbl6 = new JLabel("");
		lbl6.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl6.setBounds(350, 216, 46, 14);
		getContentPane().add(lbl6);
		

	}
}
