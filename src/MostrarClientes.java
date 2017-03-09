import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
	 variables que deben de declarase globales
	 */
	JButton btnbus,btneli,btnmod;
	private JTable table;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7;
	private String [][]arrt; //arreglo que tendra la informacion para mostrar en la tabla
	DefaultTableModel modelo; //modelo de la tabla
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	
	

	/**
	 * Create the frame.
	 */
	public MostrarClientes() {
		setTitle("Clientes");
		getContentPane().setEnabled(false);
		setClosable(true);
		setBounds(100, 100, 558, 500);
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
		
		btnbus = new JButton("Buscar");
		btnbus.setMnemonic('B');
		btnbus.setBounds(36, 246, 97, 25);
		getContentPane().add(btnbus);
		
		btnmod = new JButton("Modificar");
		btnmod.setEnabled(false);
		btnmod.setMnemonic('M');
		btnmod.setBounds(207, 246, 97, 25);
		getContentPane().add(btnmod);
		
		btneli = new JButton("Eliminar");
		btneli.setEnabled(false);
		btneli.setMnemonic('E');
		btneli.setBounds(356, 246, 97, 25);
		getContentPane().add(btneli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(12, 282, 520, 171);
		getContentPane().add(scrollPane);
		
		//-------------------- Ojo se crea esta tabla y se añade al viewport del jcrollpane y se borra el panel que tenia
		
		table = new JTable();
		scrollPane.setViewportView(table);
		//-----------------------------------------
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl1.setBounds(446, 18, 46, 14);
		getContentPane().add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl2.setBounds(348, 54, 46, 14);
		getContentPane().add(lbl2);
		
		lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl3.setBounds(348, 86, 46, 14);
		getContentPane().add(lbl3);
		
		lbl4 = new JLabel("");
		lbl4.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl4.setBounds(348, 116, 46, 14);
		getContentPane().add(lbl4);
		
		lbl5 = new JLabel("");
		lbl5.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl5.setBounds(348, 148, 46, 14);
		getContentPane().add(lbl5);
		
		lbl6 = new JLabel("");
		lbl6.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl6.setBounds(348, 182, 46, 14);
		getContentPane().add(lbl6);
		
		lbl7 = new JLabel("");
		lbl7.setIcon(new ImageIcon(MostrarClientes.class.getResource("/img/X.png")));
		lbl7.setBounds(348, 218, 46, 14);
		getContentPane().add(lbl7);
		
		//------------------------------------------eventos de focus
		eventosF(); /// ----------  ojo los eventosF son exactamente los mismos que se validan en capturaclientes es otra cosa que ya nos ahorramos
		eventosBTN();
		obtInfo();
		datostabla();
	}
	private void eventosBTN()
	{
		btnbus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnmod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btneli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
	}
	private void eventosF()
	{
		txtid.addFocusListener(new FocusListener() { //solo para el de id resetee los campos los demas son igual al de capturaclientes
			public void focusLost(FocusEvent e) {
			}
			public void focusGained(FocusEvent e) {
				txtreset();
				lblreset(mal);
				btnhab(false);
			}
		});
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
	private void modificar(){
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()) &&
				lbl3.getIcon().toString().equals(im.toString()) && lbl4.getIcon().toString().equals(im.toString())
				&& lbl5.getIcon().toString().equals(im.toString())&& lbl6.getIcon().toString().equals(im.toString())
				&& lbl7.getIcon().toString().equals(im.toString()))
		{
			Conexion c=new Conexion();
			if(!Consultas.ModificaGeneral("Clientes", "nombre", txtnom.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el nombre");
			if(!Consultas.ModificaGeneral("Clientes", "apellidop", txtapp.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el apellido paterno");
			if(!Consultas.ModificaGeneral("Clientes", "apellidom", txtapm.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el apellido materno");
			if(!Consultas.ModificaGeneral("Clientes", "direccion", txtdir.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar la direccion");
			if(!Consultas.ModificaGeneral("Clientes", "tel", txttel.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el telefono");
			if(!Consultas.ModificaGeneral("Clientes", "email", txtema.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el correo electronico");
			if(!Consultas.ModificaGeneral("Clientes", "rfc", txtrfc.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el RFC");		
			c.closeConexion();
			JOptionPane.showMessageDialog(null, "Campos actualizados");
			obtInfo();
			datostabla();
		}
	}
	private void eliminar(){
		Conexion c=new Conexion();
		if(Consultas.EliminaGeneral("Clientes","id", txtid.getText(), c))
			JOptionPane.showMessageDialog(null, "Error al eliminar");
		else{
			txtreset();
			lblreset(mal);
			btnhab(false);
			txtid.setText("");
			JOptionPane.showMessageDialog(null, "Cliente eliminado satisfactoriamente");
		}
		c.closeConexion();
		
		obtInfo();
		datostabla();
		
	}
	private void buscar() {
		if(!txtid.equals(""))
		{
			Conexion c=new Conexion();
			if(Consultas.CompruebaDup("id", "Clientes", txtid.getText(), c)) //si el id existe
			{
				txtnom.setText(Consultas.RetornaValorbyTabla("Clientes", "nombre", "id", txtid.getText(), c));
				txtapp.setText(Consultas.RetornaValorbyTabla("Clientes", "apellidop", "id", txtid.getText(), c));
				txtapm.setText(Consultas.RetornaValorbyTabla("Clientes", "apellidom", "id", txtid.getText(), c));
				txtdir.setText(Consultas.RetornaValorbyTabla("Clientes", "direccion", "id", txtid.getText(), c));
				txttel.setText(Consultas.RetornaValorbyTabla("Clientes", "tel", "id", txtid.getText(), c));
				txtema.setText(Consultas.RetornaValorbyTabla("Clientes", "email", "id", txtid.getText(), c));
				txtrfc.setText(Consultas.RetornaValorbyTabla("Clientes", "rfc", "id", txtid.getText(), c));
				lblreset(bien);
				btnhab(true);
			}else{
				JOptionPane.showMessageDialog(null, "El id no corresponde o no coincide");
			}
			c.closeConexion();
		}else{
			JOptionPane.showMessageDialog(null, "Selecciona primero el id a buscar");
		}
		
	}
	private void txtreset()
	{
		txtnom.setText("");
		txtapp.setText("");
		txtapm.setText("");
		txtdir.setText("");
		txttel.setText("");
		txtema.setText("");
		txtrfc.setText("");
	}
	private void btnhab(boolean ban)
	{
		btnmod.setEnabled(ban);
		btneli.setEnabled(ban);
		txtapp.setEnabled(ban);
		txtapm.setEnabled(ban);
		txtdir.setEnabled(ban);
		txttel.setEnabled(ban);
		txtema.setEnabled(ban);
		txtrfc.setEnabled(ban);
	}
	private void lblreset(URL rut)
	{
		lbl1.setIcon(new ImageIcon(rut));
		lbl2.setIcon(new ImageIcon(rut));
		lbl3.setIcon(new ImageIcon(rut));
		lbl4.setIcon(new ImageIcon(rut));
		lbl5.setIcon(new ImageIcon(rut));
		lbl6.setIcon(new ImageIcon(rut));
		lbl7.setIcon(new ImageIcon(rut));
		
	}
//	
	@SuppressWarnings("serial")
	private void datostabla() {
		modelo=new DefaultTableModel(new Object[][]{},new String[] {"ID", "Nombre", "AP", "AM", "Dir","Tel","E-mail","RFC"}){};

		Conexion c=new Conexion();
		for (int i = 0; i < arrt.length; i++) {
			modelo.addRow(new Object[]{arrt[i][0],arrt[i][1],arrt[i][2],arrt[i][3],arrt[i][4],arrt[i][5],arrt[i][6],arrt[i][7]});
		}
		c.closeConexion();
		table.setModel(modelo);
		
		//para centrar las columnas que queramos
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(5).setCellRenderer(tcr);
		
		
//		tamaño de cada columna
		table.getColumnModel().getColumn(0).setMinWidth(40);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(180);
		table.getColumnModel().getColumn(5).setMinWidth(80);
		table.getColumnModel().getColumn(6).setMinWidth(160);
		table.getColumnModel().getColumn(7).setMinWidth(120);
		
		//para que el JScrollPane pueda hacer scroll horizontal
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setEnabled(false); //para que no sea posible editar
		
	}

	private void obtInfo()
	{
		Conexion c=new Conexion();
		arrt=Consultas.RetornaArrayClientes(c); //consulta que se debe generar para cada tabla
		c.closeConexion();
		
	}
}
