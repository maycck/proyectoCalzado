import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class MostrarInsumos extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;
	private JTextField txtcan;
	private JTextField txtlin;
	private JTextField txtpre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarInsumos frame = new MostrarInsumos();
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
	public MostrarInsumos() {
		setTitle("Insumos");
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
		
		JLabel lbluni = new JLabel("Unidad");
		lbluni.setHorizontalAlignment(SwingConstants.RIGHT);
		lbluni.setBounds(131, 54, 75, 16);
		getContentPane().add(lbluni);
		
		JLabel lblcan = new JLabel("Cantidad");
		lblcan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcan.setBounds(131, 86, 75, 16);
		getContentPane().add(lblcan);
		
		txtcan = new JTextField();
		txtcan.setEnabled(false);
		txtcan.setColumns(10);
		txtcan.setBounds(222, 83, 116, 22);
		getContentPane().add(txtcan);
		
		JLabel lbllin = new JLabel("Linea");
		lbllin.setHorizontalAlignment(SwingConstants.RIGHT);
		lbllin.setBounds(150, 118, 56, 16);
		getContentPane().add(lbllin);
		
		txtlin = new JTextField();
		txtlin.setEnabled(false);
		txtlin.setColumns(10);
		txtlin.setBounds(222, 115, 116, 22);
		getContentPane().add(txtlin);
		
		JLabel lblpre = new JLabel("Precio");
		lblpre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblpre.setBounds(150, 150, 56, 16);
		getContentPane().add(lblpre);
		
		txtpre = new JTextField();
		txtpre.setEnabled(false);
		txtpre.setColumns(10);
		txtpre.setBounds(222, 147, 116, 22);
		getContentPane().add(txtpre);
		
		JButton butbus = new JButton("Buscar");
		butbus.setMnemonic('B');
		butbus.setBounds(36, 187, 97, 25);
		getContentPane().add(butbus);
		
		JButton butmod = new JButton("Modificar");
		butmod.setMnemonic('M');
		butmod.setEnabled(false);
		butmod.setBounds(190, 187, 97, 25);
		getContentPane().add(butmod);
		
		JButton buteli = new JButton("Eliminar");
		buteli.setMnemonic('E');
		buteli.setEnabled(false);
		buteli.setBounds(356, 187, 97, 25);
		getContentPane().add(buteli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(36, 241, 417, 123);
		getContentPane().add(scrollPane);
		
		JComboBox cmbuni = new JComboBox();
		cmbuni.setEnabled(false);
		cmbuni.setBounds(218, 51, 120, 20);
		cmbuni.setModel(new DefaultComboBoxModel(new String[] {"m", "dm", "pieza", "par"}));
		getContentPane().add(cmbuni);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(MostrarInsumos.class.getResource("/img/X.png")));
		lbl1.setBounds(350, 86, 46, 14);
		getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(MostrarInsumos.class.getResource("/img/X.png")));
		lbl2.setBounds(350, 120, 46, 14);
		getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon(MostrarInsumos.class.getResource("/img/X.png")));
		lbl3.setBounds(350, 152, 46, 14);
		getContentPane().add(lbl3);

	}
}
