import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class CapturaInsumos extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;
	private JTextField txtcan;
	private JTextField txtlin;
	private JTextField txtpre;
	JLabel lbl1,lbl2,lbl3,lbl4;
	JComboBox cmbuni;
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
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
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		
		JLabel lblId = new JLabel("id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(82, 30, 46, 14);
		getContentPane().add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(58, 61, 70, 14);
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
		
		cmbuni = new JComboBox();
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
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		lbl1.setBounds(234, 61, 46, 14);
		getContentPane().add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		lbl2.setBounds(234, 127, 46, 14);
		getContentPane().add(lbl2);
		
		lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		lbl3.setBounds(234, 164, 46, 14);
		getContentPane().add(lbl3);
		
		lbl4 = new JLabel("");
		lbl4.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		lbl4.setBounds(234, 202, 46, 14);
		getContentPane().add(lbl4);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setMnemonic('A');
		btnAgregar.setBounds(135, 236, 89, 23);
		getContentPane().add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar();
			}
		});
		
		Conexion co=new Conexion();
		txtid.setText(Consultas.RetornaId(co, "insumos"));
		co.closeConexion();
		
		eventosF();
	}
	private void Agregar() {
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()) &&
				lbl3.getIcon().toString().equals(im.toString()) && lbl4.getIcon().toString().equals(im.toString()))
		{
			Conexion c=new Conexion();
			if(Consultas.InsertaInsumos(Integer.parseInt(txtid.getText()), txtnom.getText().trim(), cmbuni.getSelectedItem().toString(),
					Integer.parseInt(txtcan.getText()), txtlin.getText().trim()	, Double.parseDouble(txtpre.getText()), c))
				{
					JOptionPane.showMessageDialog(null, "Insumo agregado con exito ");
					txtid.setText(Consultas.RetornaId(c, "insumos"));
					txtnom.setText("");
					txtcan.setText("");
					txtlin.setText("");
					txtpre.setText("");
					lblreset();
					txtnom.requestFocus();
				}
			else
				JOptionPane.showMessageDialog(null, "Error de la base de datos al insertar inesperado ");
			
			c.closeConexion();
		}else{
			JOptionPane.showMessageDialog(null, "oh no faltan completar algunos campos ");
		}
		
	}
	private void lblreset()
	{
		lbl1.setIcon(new ImageIcon(mal));
		lbl2.setIcon(new ImageIcon(mal));
		lbl3.setIcon(new ImageIcon(mal));
		lbl4.setIcon(new ImageIcon(mal));
	}
	private void eventosF() {
		txtnom.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				Conexion c=new Conexion();
				if(Consultas.CompruebaDup("nombre","insumos",txtnom.getText().trim(), c))
				{
					lbl1.setIcon(new ImageIcon(mal));
				}else{
					if(txtnom.getText().equals(""))
					{
						lbl1.setIcon(new ImageIcon(mal));
					}else
						lbl1.setIcon(new ImageIcon(bien));
				}
				c.closeConexion();
			}
			public void focusGained(FocusEvent e) {
				lbl1.setIcon(null);
			}
		});
		txtcan.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				try{
					int test=Integer.parseInt(txtcan.getText());
					lbl2.setIcon(new ImageIcon(bien));
				}catch(NumberFormatException exet){
					lbl2.setIcon(new ImageIcon(mal));
				}
			}
			public void focusGained(FocusEvent e) {
				lbl2.setIcon(null);
			}
		});
		txtlin.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(!txtlin.getText().toString().equals("")){
					
					lbl3.setIcon(new ImageIcon(bien));
				}else
					lbl3.setIcon(new ImageIcon(mal));
				
			}
			public void focusGained(FocusEvent e) {
				lbl3.setIcon(null);
			}
		});
		txtpre.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				try{
					double test=Double.parseDouble(txtpre.getText());
					lbl4.setIcon(new ImageIcon(bien));
				}catch(NumberFormatException exet){
					lbl4.setIcon(new ImageIcon(mal));
				}
			}
			public void focusGained(FocusEvent e) {
				lbl4.setIcon(null);
			}
		});
		
	}
}
