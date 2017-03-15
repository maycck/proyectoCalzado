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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.log.SysoCounter;

import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class MostrarTallas extends JInternalFrame {
	private JTextField txtid;

	/**
	 variables que deben de declarase globales
	 */
	JButton btnbus,btneli,btnmod;
	private JTable table;
	JLabel lbl1;
	JSpinner spital;
	JCheckBox chbtal;
	String cadv="";
	String ch="";
	private String [][]arrt; //arreglo que tendra la informacion para mostrar en la tabla
	DefaultTableModel modelo; //modelo de la tabla
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	

	/**
	 * Create the frame.
	 */
	public MostrarTallas() {
		setClosable(true);
		setTitle("Tallas");
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("Id");
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblid.setBounds(153, 16, 56, 16);
		getContentPane().add(lblid);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setColumns(10);
		txtid.setBounds(225, 13, 116, 22);
		getContentPane().add(txtid);
		
		JLabel lbltal = new JLabel("Talla");
		lbltal.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltal.setBounds(134, 66, 75, 16);
		getContentPane().add(lbltal);
		
		btnbus = new JButton("Buscar");
		btnbus.setMnemonic('B');
		btnbus.setBounds(35, 118, 97, 25);
		getContentPane().add(btnbus);
		
		btnmod = new JButton("Modificar");
		btnmod.setMnemonic('M');
		btnmod.setEnabled(false);
		btnmod.setBounds(189, 118, 97, 25);
		getContentPane().add(btnmod);
		
		btneli = new JButton("Eliminar");
		btneli.setMnemonic('E');
		btneli.setEnabled(false);
		btneli.setBounds(355, 118, 97, 25);
		getContentPane().add(btneli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(54, 187, 377, 123);
		getContentPane().add(scrollPane);
		
		//-------------------- Ojo se crea esta tabla y se añade al viewport del jcrollpane y se borra el panel que tenia
		
		table = new JTable();
		scrollPane.setViewportView(table);
		//-----------------------------------------
				
		
		spital = new JSpinner();
		spital.setEnabled(false);
		spital.setModel(new SpinnerNumberModel(17, 10, 40, 1));
		spital.setBounds(225, 67, 65, 20);
		getContentPane().add(spital);
		
		chbtal = new JCheckBox("medio 1/2");
		chbtal.setEnabled(false);
		chbtal.setBounds(296, 66, 97, 23);
		getContentPane().add(chbtal);
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(MostrarTallas.class.getResource("/img/X.png")));
		lbl1.setBounds(395, 69, 46, 14);
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
		spital.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				cadv = spital.getValue().toString()+ch;
				Conexion c=new Conexion();
				if(Consultas.CompruebaDup("talla","tallas",cadv, c))
				{
					lbl1.setIcon(new ImageIcon(mal));
				}else
					lbl1.setIcon(new ImageIcon(bien));
				c.closeConexion();
			}
			public void focusGained(FocusEvent e) {
				lbl1.setIcon(null);
			}
		});
		
		chbtal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chbtal.isSelected())
					ch=" 1/2";
				else
					ch="";
				cadv=spital.getValue().toString()+ch;
				Conexion c=new Conexion();
				if(Consultas.CompruebaDup("talla","tallas",cadv, c))
				{
					lbl1.setIcon(new ImageIcon(mal));
				}else
					lbl1.setIcon(new ImageIcon(bien));
				c.closeConexion();
			}
		});
		
	}
	
	private void modificar(){
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()))
		{
			Conexion c=new Conexion();
			if(!Consultas.ModificaGeneral("tallas", "talla", cadv, "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar la talla");
			c.closeConexion();
			JOptionPane.showMessageDialog(null, "Campos actualizados");
			obtInfo();
			datostabla();
		}
	}
	private void eliminar(){
		Conexion c=new Conexion();
		if(Consultas.EliminaGeneral("tallas","id", txtid.getText(), c))
			JOptionPane.showMessageDialog(null, "Error al eliminar");
		else{
			String idaeli;
			idaeli=Consultas.RetornaIdToDelete(c, "detalle_talla", "id_talla="+txtid.getText());
			while(!idaeli.equals("-1")){
				if(Consultas.EliminaGeneral("detalle_talla","id",idaeli, c))
					JOptionPane.showMessageDialog(null, "Error al eliminar la relacion de talla con suela");
				idaeli=Consultas.RetornaIdToDelete(c, "detalle_talla", "id_talla="+txtid.getText());
			}
			txtreset();
			lblreset(mal);
			btnhab(false);
			txtid.setText("");
			JOptionPane.showMessageDialog(null, "Talla eliminada satisfactoriamente");
		}
		c.closeConexion();
		
		obtInfo();
		datostabla();
		
	}
	private void buscar() {
		if(!txtid.equals(""))
		{
			Conexion c=new Conexion();
			if(Consultas.CompruebaDup("id", "tallas", txtid.getText(), c)) //si el id existe
			{
				cadv=Consultas.RetornaValorbyTabla("tallas", "talla", "id", txtid.getText(), c);
				if(cadv.length()>2){
					spital.setValue(Integer.parseInt(cadv.substring(0,2)));
					chbtal.setSelected(true);
				}
				else{
					spital.setValue(Integer.parseInt(cadv));
					chbtal.setSelected(false);
				}
				
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
		spital.setValue(17);
		chbtal.setSelected(false);
	}
	
	private void btnhab(boolean ban)
	{
		btnmod.setEnabled(ban);
		btneli.setEnabled(ban);
		spital.setEnabled(ban);
		chbtal.setEnabled(ban);
	}
	private void lblreset(URL rut)
	{
		lbl1.setIcon(new ImageIcon(rut));
		
	}
	
	@SuppressWarnings("serial")
	private void datostabla() {
		modelo=new DefaultTableModel(new Object[][]{},new String[] {"ID", "Talla"}){};

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
		arrt=Consultas.RetornaArrayCT("tallas", "id", c); //consulta que se debe generar para cada tabla
		c.closeConexion();
		
	}
}
