import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MostrarLinea extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;

	/**
	 variables que deben de declarase globales
	 */
	JButton btnbus,btneli,btnmod;
	private JTable table;
	JLabel lbl1;
	private String [][]arrt; //arreglo que tendra la informacion para mostrar en la tabla
	DefaultTableModel modelo; //modelo de la tabla
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	
	/**
	 * Create the frame.
	 */
	public MostrarLinea() {
		setClosable(true);
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
		
		btnbus = new JButton("Buscar");
		btnbus.setMnemonic('B');
		btnbus.setBounds(31, 118, 97, 25);
		getContentPane().add(btnbus);
		
		btnmod = new JButton("Modificar");
		btnmod.setMnemonic('M');
		btnmod.setEnabled(false);
		btnmod.setBounds(185, 118, 97, 25);
		getContentPane().add(btnmod);
		
		btneli = new JButton("Eliminar");
		btneli.setMnemonic('E');
		btneli.setEnabled(false);
		btneli.setBounds(351, 118, 97, 25);
		getContentPane().add(btneli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(53, 187, 377, 123);
		getContentPane().add(scrollPane);
		
		//-------------------- Ojo se crea esta tabla y se añade al viewport del jcrollpane y se borra el panel que tenia
		
		table = new JTable();
		scrollPane.setViewportView(table);
		//-----------------------------------------
					
		
		txtnom = new JTextField();
		txtnom.setHorizontalAlignment(SwingConstants.CENTER);
		txtnom.setEnabled(false);
		txtnom.setColumns(10);
		txtnom.setBounds(217, 63, 116, 22);
		getContentPane().add(txtnom);
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(MostrarLinea.class.getResource("/img/X.png")));
		lbl1.setBounds(351, 66, 46, 14);
		getContentPane().add(lbl1);
		
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
				Conexion c=new Conexion();
				if(Consultas.CompruebaDup("nombre","linea",txtnom.getText().trim(), c))
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
		
	}
	
	private void modificar(){
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()))
		{
			Conexion c=new Conexion();
			if(!Consultas.ModificaGeneral("linea", "nombre", txtnom.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el nombre");
			c.closeConexion();
			JOptionPane.showMessageDialog(null, "Campos actualizados");
			obtInfo();
			datostabla();
		}
	}
	private void eliminar(){
		Conexion c=new Conexion();
		if(Consultas.EliminaGeneral("linea","id", txtid.getText(), c))
			JOptionPane.showMessageDialog(null, "Error al eliminar");
		else{
			txtreset();
			lblreset(mal);
			btnhab(false);
			txtid.setText("");
			JOptionPane.showMessageDialog(null, "Linea eliminada satisfactoriamente");
		}
		c.closeConexion();
		
		obtInfo();
		datostabla();
		
	}
	private void buscar() {
		if(!txtid.equals(""))
		{
			Conexion c=new Conexion();
			if(Consultas.CompruebaDup("id", "linea", txtid.getText(), c)) //si el id existe
			{
				txtnom.setText(Consultas.RetornaValorbyTabla("linea", "nombre", "id", txtid.getText(), c));
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
	}
	
	private void btnhab(boolean ban)
	{
		btnmod.setEnabled(ban);
		btneli.setEnabled(ban);
		txtnom.setEnabled(ban);
	}
	private void lblreset(URL rut)
	{
		lbl1.setIcon(new ImageIcon(rut));
		
	}
	
	@SuppressWarnings("serial")
	private void datostabla() {
		modelo=new DefaultTableModel(new Object[][]{},new String[] {"ID", "Nombre"}){};

		Conexion c=new Conexion();
		for (int i = 0; i < arrt.length; i++) {
			modelo.addRow(new Object[]{arrt[i][0],arrt[i][1]});
		}
		c.closeConexion();
		table.setModel(modelo);
		
		//para centrar las columnas que queramos
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
		
//		tamaño de cada columna
		table.getColumnModel().getColumn(0).setMinWidth(40);
		table.getColumnModel().getColumn(1).setMinWidth(300);
		
		//para que el JScrollPane pueda hacer scroll horizontal
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setEnabled(false); //para que no sea posible editar
		
	}

	private void obtInfo()
	{
		Conexion c=new Conexion();
		arrt=Consultas.RetornaArrayCT("linea", "id", c); //consulta que se debe generar para cada tabla
		c.closeConexion();
		
	}
}
