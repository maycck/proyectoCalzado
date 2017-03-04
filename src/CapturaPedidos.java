import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class CapturaPedidos extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtfce;
	JPanel jpatal,jpacol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapturaPedidos frame = new CapturaPedidos();
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
	public CapturaPedidos() {
		setTitle("Captura Pedidos");
		setBounds(100, 100, 500, 600);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("Id");
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblid.setBounds(137, 17, 46, 14);
		getContentPane().add(lblid);
		
		txtid = new JTextField();
		txtid.setColumns(10);
		txtid.setBounds(193, 14, 86, 20);
		getContentPane().add(txtid);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaPedidos.class.getResource("/img/X.png")));
		lbl1.setBounds(289, 17, 46, 14);
		getContentPane().add(lbl1);
		
		JLabel lblcli = new JLabel("Cliente");
		lblcli.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcli.setBounds(137, 45, 46, 14);
		getContentPane().add(lblcli);
		
		JComboBox cmbcli = new JComboBox();
		cmbcli.setBounds(193, 44, 128, 22);
		getContentPane().add(cmbcli);
		
		JLabel lblmod = new JLabel("Modelo");
		lblmod.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmod.setBounds(137, 85, 46, 14);
		getContentPane().add(lblmod);
		
		JComboBox cmbmod = new JComboBox();
		cmbmod.setBounds(193, 81, 128, 22);
		getContentPane().add(cmbmod);
		
		JLabel lblcol = new JLabel("Color");
		lblcol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcol.setBounds(193, 126, 46, 14);
		getContentPane().add(lblcol);
		
		JLabel lbltal = new JLabel("Tallas");
		lbltal.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltal.setBounds(193, 257, 46, 22);
		getContentPane().add(lbltal);
		
		JLabel lblfcr = new JLabel("Fecha de pedido");
		lblfcr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblfcr.setBounds(78, 404, 105, 14);
		getContentPane().add(lblfcr);
		
		JLabel lblfcp = new JLabel("Fecha de entrega");
		lblfcp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblfcp.setBounds(67, 474, 116, 14);
		getContentPane().add(lblfcp);
		
		JLabel lblcan = new JLabel("Cantidad");
		lblcan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcan.setBounds(123, 501, 60, 14);
		getContentPane().add(lblcan);
		
		JLabel lbl7 = new JLabel("");
		lbl7.setIcon(new ImageIcon(CapturaPedidos.class.getResource("/img/X.png")));
		lbl7.setBounds(289, 498, 46, 14);
		getContentPane().add(lbl7);
		
		txtfce = new JTextField();
		txtfce.setEditable(false);
		txtfce.setColumns(10);
		txtfce.setBounds(193, 468, 86, 20);
		getContentPane().add(txtfce);
		
		JScrollPane jsptal = new JScrollPane();
		jsptal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsptal.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsptal.setAutoscrolls(true);
		jsptal.setBounds(12, 279, 460, 89);
		getContentPane().add(jsptal);
		
		jpatal = new JPanel();
		jsptal.setViewportView(jpatal);
		jpatal.setLayout(null);
		
		JScrollPane jspcol = new JScrollPane();
		jspcol.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspcol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspcol.setAutoscrolls(true);
		jspcol.setBounds(12, 141, 460, 106);
		getContentPane().add(jspcol);
		
		jpacol = new JPanel();
		jspcol.setViewportView(jpacol);
		jpacol.setLayout(null);
		
		JButton btnagr = new JButton("Agregar");
		btnagr.setBounds(182, 528, 97, 25);
		getContentPane().add(btnagr);
		
		JSpinner spifcp = new JSpinner();
		spifcp.setModel(new SpinnerDateModel(new Date(1488261600000L), null, null, Calendar.DAY_OF_YEAR));
		spifcp.setBounds(193, 400, 142, 22);
		getContentPane().add(spifcp);
		
		JLabel lbldia = new JLabel("Dias de produccion");
		lbldia.setHorizontalAlignment(SwingConstants.RIGHT);
		lbldia.setBounds(67, 441, 116, 14);
		getContentPane().add(lbldia);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(CapturaPedidos.class.getResource("/img/X.png")));
		label_1.setBounds(289, 438, 46, 14);
		getContentPane().add(label_1);
		
		JSpinner spidia = new JSpinner();
		spidia.setBounds(193, 435, 86, 22);
		getContentPane().add(spidia);
		
		JSpinner spican = new JSpinner();
		spican.setBounds(193, 497, 86, 22);
		getContentPane().add(spican);
		
	}
}