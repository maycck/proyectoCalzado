import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CapturaClientes extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;
	private JTextField txtapp;
	private JTextField txtapm;
	private JTextField txtdir;
	private JTextField txttel;
	private JTextField txtema;
	private JTextField txtrfc;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7;
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public CapturaClientes() {
		setTitle("Captura Clientes");
		setClosable(true);
		setBounds(100, 100, 450, 360);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("Id");
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblid.setBounds(112, 30, 56, 16);
		getContentPane().add(lblid);
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setBounds(184, 27, 116, 22);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		JLabel lblnom = new JLabel("Nombre");
		lblnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnom.setBounds(112, 62, 56, 16);
		getContentPane().add(lblnom);
		
		txtnom = new JTextField();
		txtnom.setBounds(184, 59, 116, 22);
		getContentPane().add(txtnom);
		txtnom.setColumns(10);
		
		JLabel lblapp = new JLabel("Apellido Pat");
		lblapp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblapp.setBounds(93, 94, 75, 16);
		getContentPane().add(lblapp);
		
		txtapp = new JTextField();
		txtapp.setBounds(184, 91, 116, 22);
		getContentPane().add(txtapp);
		txtapp.setColumns(10);
		
		JLabel lblapm = new JLabel("Apellido Mat");
		lblapm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblapm.setBounds(93, 126, 75, 16);
		getContentPane().add(lblapm);
		
		txtapm = new JTextField();
		txtapm.setColumns(10);
		txtapm.setBounds(184, 123, 116, 22);
		getContentPane().add(txtapm);
		
		JLabel lbldir = new JLabel("Direccion");
		lbldir.setHorizontalAlignment(SwingConstants.RIGHT);
		lbldir.setBounds(112, 158, 56, 16);
		getContentPane().add(lbldir);
		
		txtdir = new JTextField();
		txtdir.setBounds(184, 155, 116, 22);
		getContentPane().add(txtdir);
		txtdir.setColumns(10);
		
		JLabel lbltel = new JLabel("Telefono");
		lbltel.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltel.setBounds(112, 190, 56, 16);
		getContentPane().add(lbltel);
		
		txttel = new JTextField();
		txttel.setColumns(10);
		txttel.setBounds(184, 187, 116, 22);
		getContentPane().add(txttel);
		
		JLabel lblema = new JLabel("Email");
		lblema.setHorizontalAlignment(SwingConstants.RIGHT);
		lblema.setBounds(112, 222, 56, 16);
		getContentPane().add(lblema);
		
		txtema = new JTextField();
		txtema.setColumns(10);
		txtema.setBounds(184, 219, 116, 22);
		getContentPane().add(txtema);
		
		JLabel lblrfc = new JLabel("RFC");
		lblrfc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblrfc.setBounds(112, 256, 56, 16);
		getContentPane().add(lblrfc);
		
		txtrfc = new JTextField();
		txtrfc.setColumns(10);
		txtrfc.setBounds(184, 253, 116, 22);
		getContentPane().add(txtrfc);
		
		JButton btnagr = new JButton("Agregar");
		btnagr.setMnemonic('A');
		btnagr.setBounds(145, 288, 97, 25);
		getContentPane().add(btnagr);
		btnagr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar();
			}
		});
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaClientes.class.getResource("/img/X.png")));
		lbl1.setBounds(312, 62, 46, 14);
		getContentPane().add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(CapturaClientes.class.getResource("/img/X.png")));
		lbl2.setBounds(312, 94, 46, 14);
		getContentPane().add(lbl2);
		
		lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon(CapturaClientes.class.getResource("/img/X.png")));
		lbl3.setBounds(312, 126, 46, 14);
		getContentPane().add(lbl3);
		
		lbl4 = new JLabel("");
		lbl4.setIcon(new ImageIcon(CapturaClientes.class.getResource("/img/X.png")));
		lbl4.setBounds(312, 158, 46, 14);
		getContentPane().add(lbl4);
		
		lbl5 = new JLabel("");
		lbl5.setIcon(new ImageIcon(CapturaClientes.class.getResource("/img/X.png")));
		lbl5.setBounds(312, 190, 46, 14);
		getContentPane().add(lbl5);
		
		lbl6 = new JLabel("");
		lbl6.setIcon(new ImageIcon(CapturaClientes.class.getResource("/img/X.png")));
		lbl6.setBounds(312, 222, 46, 14);
		getContentPane().add(lbl6);
		
		lbl7 = new JLabel("");
		lbl7.setIcon(new ImageIcon(CapturaClientes.class.getResource("/img/X.png")));
		lbl7.setBounds(312, 256, 46, 14);
		getContentPane().add(lbl7);
		
		Conexion co=new Conexion();
		txtid.setText(Consultas.RetornaId(co, "clientes"));
		co.closeConexion();
		
		eventosF();	

	}
	private void Agregar() {
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()) &&
				lbl3.getIcon().toString().equals(im.toString()) && lbl4.getIcon().toString().equals(im.toString())
				&& lbl5.getIcon().toString().equals(im.toString())&& lbl6.getIcon().toString().equals(im.toString())
				&& lbl7.getIcon().toString().equals(im.toString()))
		{
			Conexion c=new Conexion();
			if(Consultas.InsertaClientes(Integer.parseInt(txtid.getText()), txtnom.getText().trim(), txtapp.getText().trim(), txtapm.getText().trim(),
					txtdir.getText().trim(), txttel.getText().trim(), txtema.getText().trim(), txtrfc.getText().trim(), c))
				{
					JOptionPane.showMessageDialog(null, "Cliente agregado con exito ");
					txtid.setText(Consultas.RetornaId(c, "clientes"));
					txtnom.setText("");
					txtapp.setText("");
					txtapm.setText("");
					txtdir.setText("");
					txttel.setText("");
					txtema.setText("");
					txtrfc.setText("");
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
		lbl5.setIcon(new ImageIcon(mal));
		lbl6.setIcon(new ImageIcon(mal));
		lbl7.setIcon(new ImageIcon(mal));
	}
	private void eventosF() {
		txtnom.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(txtnom.getText().equals(""))
				{
					lbl1.setIcon(new ImageIcon(mal));
				}else
					lbl1.setIcon(new ImageIcon(bien));
			}
			public void focusGained(FocusEvent e) {
				lbl1.setIcon(null);
			}
		});
		txtapp.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(txtapp.getText().equals(""))
				{
					lbl2.setIcon(new ImageIcon(mal));
				}else
					lbl2.setIcon(new ImageIcon(bien));
			}
			public void focusGained(FocusEvent e) {
				lbl2.setIcon(null);
			}
		});
		txtapm.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(txtapm.getText().equals(""))
				{
					lbl3.setIcon(new ImageIcon(mal));
				}else
					lbl3.setIcon(new ImageIcon(bien));
			}
			public void focusGained(FocusEvent e) {
				lbl3.setIcon(null);
			}
		});
		txtdir.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(txtdir.getText().equals(""))
				{
					lbl4.setIcon(new ImageIcon(mal));
				}else
					lbl4.setIcon(new ImageIcon(bien));
			}
			public void focusGained(FocusEvent e) {
				lbl4.setIcon(null);
			}
		});
		txttel.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(txttel.getText().equals(""))
				{
					lbl5.setIcon(new ImageIcon(mal));
				}else
					lbl5.setIcon(new ImageIcon(bien));
			}
			public void focusGained(FocusEvent e) {
				lbl5.setIcon(null);
			}
		});
		txtema.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				 String email = txtema.getText();
				if(email.equals(""))
				{
					lbl6.setIcon(new ImageIcon(mal));
				}else
				{
					Pattern patron = Pattern
			                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			 
			        Matcher reconocer = patron.matcher(email);
			 
			        if (reconocer.find() == true) {
			        	lbl6.setIcon(new ImageIcon(bien));
			        } else {
			        	lbl6.setIcon(new ImageIcon(mal));
			        }
					
				}
			}
			public void focusGained(FocusEvent e) {
				lbl6.setIcon(null);
			}
		});
		txtrfc.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(txtrfc.getText().equals(""))
				{
					lbl7.setIcon(new ImageIcon(mal));
				}else{
					String t =txtrfc.getText();
					if(t.length()==12 || t.length()==13){
						lbl7.setIcon(new ImageIcon(bien));
					}else{
						lbl7.setIcon(new ImageIcon(mal));
						}
				}
			}
			public void focusGained(FocusEvent e) {
				lbl7.setIcon(null);
			}
		});
		
	}
}