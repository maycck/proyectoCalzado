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
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	 variables que deben de declarase globales
	 */
	JButton btnbus,btneli,btnmod;
	private JTable table;
	JLabel lbl1,lbl2,lbl3,lbl4;
	JComboBox cmbuni;
	String unit[]=new String[] {"m", "dm", "pieza", "par"}; 
	private String [][]arrt; //arreglo que tendra la informacion para mostrar en la tabla
	DefaultTableModel modelo; //modelo de la tabla
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	

	/**
	 * Create the frame.
	 */
	public MostrarInsumos() {
		setClosable(true);
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
		
		btnbus = new JButton("Buscar");
		btnbus.setMnemonic('B');
		btnbus.setBounds(36, 187, 97, 25);
		getContentPane().add(btnbus);
		
		btnmod = new JButton("Modificar");
		btnmod.setMnemonic('M');
		btnmod.setEnabled(false);
		btnmod.setBounds(190, 187, 97, 25);
		getContentPane().add(btnmod);
		
		btneli = new JButton("Eliminar");
		btneli.setMnemonic('E');
		btneli.setEnabled(false);
		btneli.setBounds(356, 187, 97, 25);
		getContentPane().add(btneli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(12, 256, 452, 123);
		getContentPane().add(scrollPane);
		
		//-------------------- Ojo se crea esta tabla y se añade al viewport del jcrollpane y se borra el panel que tenia
		
		table = new JTable();
		scrollPane.setViewportView(table);
		//-----------------------------------------
				
		cmbuni = new JComboBox();
		cmbuni.setEnabled(false);
		cmbuni.setBounds(218, 51, 120, 20);
		cmbuni.setModel(new DefaultComboBoxModel(unit));
		getContentPane().add(cmbuni);
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(MostrarInsumos.class.getResource("/img/X.png")));
		lbl1.setBounds(448, 16, 46, 14);
		getContentPane().add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(MostrarInsumos.class.getResource("/img/X.png")));
		lbl2.setBounds(350, 88, 46, 14);
		getContentPane().add(lbl2);
		
		lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon(MostrarInsumos.class.getResource("/img/X.png")));
		lbl3.setBounds(350, 120, 46, 14);
		getContentPane().add(lbl3);
		
		lbl4 = new JLabel("");
		lbl4.setIcon(new ImageIcon(MostrarInsumos.class.getResource("/img/X.png")));
		lbl4.setBounds(350, 152, 46, 14);
		getContentPane().add(lbl4);
		
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
	
	private void modificar(){
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()) &&
				lbl3.getIcon().toString().equals(im.toString()) && lbl4.getIcon().toString().equals(im.toString()))
			{
			Conexion c=new Conexion();
			if(!Consultas.ModificaGeneral("insumos", "nombre", txtnom.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el nombre");
			if(!Consultas.ModificaGeneral("insumos", "unidad", cmbuni.getSelectedItem().toString(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar la unidad");
			if(!Consultas.ModificaGeneral("insumos", "cantidad", txtcan.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar la cantidad");
			if(!Consultas.ModificaGeneral("insumos", "linea", txtlin.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar la linea");
			if(!Consultas.ModificaGeneral("insumos", "precio", txtpre.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el precio");		
			c.closeConexion();
			JOptionPane.showMessageDialog(null, "Campos actualizados");
			obtInfo();
			datostabla();
		}
	}
	private void eliminar(){
		Conexion c=new Conexion();
		if(Consultas.EliminaGeneral("insumos","id", txtid.getText(), c))
			JOptionPane.showMessageDialog(null, "Error al eliminar");
		else{
			String idaeli;
			idaeli=Consultas.RetornaIdToDelete(c, "consumos", "id_insumo="+txtid.getText());
			while(!idaeli.equals("-1")){
				if(Consultas.EliminaGeneral("consumos","id",idaeli, c))
					JOptionPane.showMessageDialog(null, "Error al eliminar el consumo de los modelos");
				idaeli=Consultas.RetornaIdToDelete(c, "consumos", "id_insumo="+txtid.getText());
			}
			txtreset();
			lblreset(mal);
			btnhab(false);
			txtid.setText("");
			JOptionPane.showMessageDialog(null, "Insumo eliminado satisfactoriamente");
		}
		c.closeConexion();
		
		obtInfo();
		datostabla();
		
	}
	private void buscar() {
		String r;
		if(!txtid.equals(""))
		{
			Conexion c=new Conexion();
			if(Consultas.CompruebaDup("id", "insumos", txtid.getText(), c)) //si el id existe
			{
				
				txtnom.setText(Consultas.RetornaValorbyTabla("insumos", "nombre", "id", txtid.getText(), c));
				r=Consultas.RetornaValorbyTabla("insumos", "unidad", "id", txtid.getText(), c);
				for(int i=0;i<unit.length;i++)
				{
					if(unit[i].equalsIgnoreCase(r))
						cmbuni.setSelectedItem(unit[i]);
				}
				txtcan.setText(Consultas.RetornaValorbyTabla("insumos", "cantidad", "id", txtid.getText(), c));
				txtlin.setText(Consultas.RetornaValorbyTabla("insumos", "linea", "id", txtid.getText(), c));
				txtpre.setText(Consultas.RetornaValorbyTabla("insumos", "precio", "id", txtid.getText(), c));
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
		txtcan.setText("");
		txtlin.setText("");
		txtpre.setText("");
		cmbuni.setEnabled(false);
	}
	private void btnhab(boolean ban)
	{
		btnmod.setEnabled(ban);
		btneli.setEnabled(ban);
		txtnom.setEnabled(ban);
		txtcan.setEnabled(ban);
		txtlin.setEnabled(ban);
		txtpre.setEnabled(ban);
		cmbuni.setEnabled(ban);
	}
	private void lblreset(URL rut)
	{
		lbl1.setIcon(new ImageIcon(rut));
		lbl2.setIcon(new ImageIcon(rut));
		lbl3.setIcon(new ImageIcon(rut));
		lbl4.setIcon(new ImageIcon(rut));
		
	}
//	
	@SuppressWarnings("serial")
	private void datostabla() {
		modelo=new DefaultTableModel(new Object[][]{},new String[] {"ID", "Nombre", "Unidad", "Cantidad", "Linea","Precio"}){};

		Conexion c=new Conexion();
		for (int i = 0; i < arrt.length; i++) {
			modelo.addRow(new Object[]{arrt[i][0],arrt[i][1],arrt[i][2],arrt[i][3],arrt[i][4],arrt[i][5]});
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
		table.getColumnModel().getColumn(0).setMinWidth(10);
		table.getColumnModel().getColumn(1).setMinWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(20);
		table.getColumnModel().getColumn(3).setMinWidth(60);
		table.getColumnModel().getColumn(4).setMinWidth(60);
		table.getColumnModel().getColumn(5).setMinWidth(60);
		
		//para que el JScrollPane pueda hacer scroll horizontal
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setEnabled(false); //para que no sea posible editar
		
	}

	private void obtInfo()
	{
		Conexion c=new Conexion();
		arrt=Consultas.RetornaArrayInsumos(c); //consulta que se debe generar para cada tabla
		c.closeConexion();
		
	}
}
