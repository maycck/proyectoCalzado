import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class MostrarModelos extends JInternalFrame {
	
	private JTextField txtid;
	private JTextField txtnom;
	JPanel panel;
	private JTextField txtnue;
	JComboBox cbsue,cblin;
	JLabel lbl1,lbl3;
	JCheckBox chk[];
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	String [][]arr,arrs,arrl;
	private JScrollPane scrollPane_1;
	private JButton btnmod;
	private JButton btneli;
	private JButton btnbus;
	private JTable table;
	private JTextField txtsue;
	private String [][]arrt; 
	DefaultTableModel modelo;
	/**
	 * Create the frame.
	 */
	public MostrarModelos() {
		setClosable(true);
		setTitle("Modelos");
		setResizable(true);
		setBounds(100, 100, 546, 480);
		getContentPane().setLayout(null);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setBounds(240, 27, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		JLabel lblId = new JLabel("id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(184, 30, 46, 14);
		getContentPane().add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(85, 61, 86, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblUnidad = new JLabel("Suela");
		lblUnidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnidad.setBounds(47, 94, 70, 14);
		getContentPane().add(lblUnidad);
		
		txtnom = new JTextField();
		txtnom.setHorizontalAlignment(SwingConstants.CENTER);
		txtnom.setBounds(194, 58, 180, 20);
		getContentPane().add(txtnom);
		txtnom.setColumns(10);
		
		cbsue = new JComboBox();
		cbsue.setBounds(127, 91, 134, 20);
		getContentPane().add(cbsue);
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		lbl1.setBounds(396, 61, 46, 14);
		getContentPane().add(lbl1);
		
		btnbus = new JButton("Buscar");
		btnbus.setMnemonic('A');
		btnbus.setBounds(22, 416, 89, 23);
		getContentPane().add(btnbus);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(119, 175, 302, 106);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon(CapturaModelos.class.getResource("/img/X.png")));
		lbl3.setBounds(431, 222, 46, 14);
		getContentPane().add(lbl3);
		
		cblin = new JComboBox();
		cblin.setBounds(128, 131, 133, 20);
		getContentPane().add(cblin);
		cblin.addItem("Ninguna");
		
		JLabel lblLinea = new JLabel("Linea");
		lblLinea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLinea.setBounds(72, 137, 46, 14);
		getContentPane().add(lblLinea);
		
		txtnue = new JTextField();
		txtnue.setEditable(false);
		txtnue.setBounds(287, 131, 134, 20);
		getContentPane().add(txtnue);
		txtnue.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 292, 481, 113);
		getContentPane().add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		btnmod = new JButton("Modificar");
		btnmod.setBounds(237, 416, 89, 23);
		getContentPane().add(btnmod);
		
		btneli = new JButton("Eliminar");
		btneli.setBounds(414, 416, 89, 23);
		getContentPane().add(btneli);
		
		txtsue = new JTextField();
		txtsue.setEditable(false);
		txtsue.setBounds(287, 91, 134, 20);
		getContentPane().add(txtsue);
		txtsue.setColumns(10);
		
		preparativos();
	}
	private void preparativos()
	{
		eventosBTN();
		obtInfo();
		datostabla();
		Conexion co=new Conexion();
		arr=Consultas.RetornaArrayCT("colores","nombre", co);
		arrs=Consultas.RetornaArrayCT("suelas","nombre", co);
		arrl=Consultas.RetornaArrayCT("linea","nombre", co);
		co.closeConexion();
		if(arrs==null)
			JOptionPane.showMessageDialog(null, "Agrega primero suelas");
		if(arr!=null && arrs!=null){
			ChkBoxes();
			eventosF();
		}
		else{
			JOptionPane.showMessageDialog(null, "Debes de insertar tallas primero");
		}
		for (int i = 0; i < arrs.length; i++) {
			cbsue.addItem(arrs[i][1]);
		}
		for (int i = 0; i < arrl.length; i++) {
			cblin.addItem(arrl[i][1]);
		}

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
	@SuppressWarnings("serial")
	private void datostabla() {
		modelo=new DefaultTableModel(new Object[][]{},new String[] {"ID", "Modelo","Suela"}){};

		Conexion c=new Conexion();
		for (int i = 0; i < arrt.length; i++) {
			String suela=Consultas.RetornaValorbyTabla("suelas", "nombre", "id", arrt[i][2], c);
			if(suela.equals(""))
			{
				suela="Eliminada";
			}
			modelo.addRow(new Object[]{arrt[i][0],arrt[i][1],suela});
		}
		c.closeConexion();
		table.setModel(modelo);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(1).setMinWidth(220);
		table.getColumnModel().getColumn(2).setMinWidth(220);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setEnabled(false); //para que no sea posible editar
		
	}

	private void obtInfo()
	{
		Conexion c=new Conexion();
		arrt=Consultas.RetornaArrayModelos(c); //
		c.closeConexion();
		
	}
	
	private void ChkBoxes() {
		int x=10,y=10;
		int col=0;
		panel.removeAll();
		chk=new JCheckBox[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if(col==2)
			{
				col=0;
				x=10;
				y+=20;
			}
			col++;
			chk[i]=new JCheckBox(arr[i][1]);
			chk[i].setBounds(x, y, 160, 20);
			panel.add(chk[i]);
			addCHK(chk[i]);
			x+=160;
		}
		
		panel.setPreferredSize(new Dimension(x+80,y+40));
		
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
			public void focusLost(FocusEvent arg0) {
				if(txtnom.getText().equals(""))
				{
					lbl1.setIcon(new ImageIcon(mal));
				}else{
					Conexion c=new Conexion();
					if(Consultas.CompruebaDup("nombre", "modelos", txtnom.getText().trim(), c))
					{
						lbl1.setIcon(new ImageIcon(mal));
					}else{
						lbl1.setIcon(new ImageIcon(bien));
					}
					c.closeConexion();
				}
			}
			public void focusGained(FocusEvent arg0) {
				lbl1.setIcon(null);
			}
		});
		cblin.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				txtnue.setText(cblin.getSelectedItem().toString());			
			}
		});
		cbsue.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				txtsue.setText(cbsue.getSelectedItem().toString());			
			}
		});
	}
	private void addCHK(JCheckBox chks)
	{
		chks.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				boolean chkTrue=false;
				for (int i = 0; i < arr.length; i++) {
					if(chk[i].isSelected())
					{
						chkTrue=true;
						break;
					}
				}
				if(chkTrue)
					lbl3.setIcon(new ImageIcon(bien));
				else
					lbl3.setIcon(new ImageIcon(mal));
			}
			public void focusGained(FocusEvent arg0) {
				lbl3.setIcon(null);
			}
		});
	}
	private void modificar(){
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl3.getIcon().toString().equals(im.toString()))
		{
			String idaeli;
			Conexion c=new Conexion();
			if(!Consultas.ModificaGeneral("modelos", "nombre", txtnom.getText().toString(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el nombre");
			if(!Consultas.ModificaGeneral("modelos", "id_suela",arrs[cbsue.getSelectedIndex()][0], "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar la suela");
			idaeli=Consultas.RetornaIdToDelete(c, "detalle_color", "id_modelo="+txtid.getText());
			while(!idaeli.equals("-1")){
				if(Consultas.EliminaGeneral("detalle_color","id",idaeli, c))
					JOptionPane.showMessageDialog(null, "Error al actualizar un color del modelo");
				idaeli=Consultas.RetornaIdToDelete(c, "detalle_color", "id_modelo="+txtid.getText());
			}
			for (int i = 0; i < arr.length; i++) {
				if(chk[i].isSelected())
				{
					int idB=Integer.parseInt(Consultas.RetornaId(c, "detalle_color"));
					if(!Consultas.InsertaDetalleTallaColor("detalle_color",idB, Integer.parseInt(txtid.getText()), Integer.parseInt(arr[i][0]), c))
					{
						JOptionPane.showMessageDialog(null, "Error al agregar talla "+arr[i][1]);
					}
				}
			}
			if(txtnue.getText().equals("Ninguna"))
			{
				if(Consultas.CompruebaDup("id_modelo", "detalle_linea", txtid.getText(), c)) //si el id existe
				{
					String idelim=Consultas.RetornaValorbyTabla("detalle_linea","id","id_modelo",txtid.getText(),c);
					if(Consultas.EliminaGeneral("detalle_linea","id", idelim, c))
					{
						JOptionPane.showMessageDialog(null, "Error al modificar linea");
					}
				}
			}else{
				if(Consultas.CompruebaDup("id_modelo", "detalle_linea", txtid.getText(), c)) //si el id existe
				{
					String idelim=Consultas.RetornaValorbyTabla("detalle_linea","id","id_modelo",txtid.getText(),c);
					if(!Consultas.ModificaGeneral("detalle_linea", "id_linea", arrl[cblin.getSelectedIndex()-1][0], "id", idelim, c))
						JOptionPane.showMessageDialog(null, "Error al actualizar linea");
				}else{
					int idB=Integer.parseInt(Consultas.RetornaId(c, "detalle_linea"));
					if(Consultas.InsertaDetalleTallaColor("detalle_linea",idB, Integer.parseInt(txtid.getText()),
							Integer.parseInt(arrl[cblin.getSelectedIndex()-1][0]), c))
					;
				}
			}
			c.closeConexion();
			JOptionPane.showMessageDialog(null, "Campos actualizados");
			obtInfo();
			datostabla();
		}
	}
	private void eliminar(){
		Conexion c=new Conexion();
		if(Consultas.EliminaGeneral("modelos","id", txtid.getText(), c))
			JOptionPane.showMessageDialog(null, "Error al eliminar");
		else{
			String idaeli;
			idaeli=Consultas.RetornaIdToDelete(c, "detalle_color", "id_modelo="+txtid.getText());
			while(!idaeli.equals("-1")){
				if(Consultas.EliminaGeneral("detalle_color","id",idaeli, c))
					JOptionPane.showMessageDialog(null, "Error al actualizar un color del modelo");
				idaeli=Consultas.RetornaIdToDelete(c, "detalle_color", "id_modelo="+txtid.getText());
			}
			if(Consultas.CompruebaDup("id_modelo", "detalle_linea", txtid.getText(), c)) //si el id existe
			{
				String idelim=Consultas.RetornaValorbyTabla("detalle_linea","id","id_modelo",txtid.getText(),c);
				if(Consultas.EliminaGeneral("detalle_linea","id", idelim, c))
				{
					JOptionPane.showMessageDialog(null, "Error al eliminar linea");
				}
			}
			txtreset();
			lblreset(mal);
			btnhab(false);
			txtid.setText("");
			JOptionPane.showMessageDialog(null, "Eliminada satisfactoriamente");
		}
		c.closeConexion();
		
		obtInfo();
		datostabla();
		
	}
	private void buscar() {
		if(!txtid.equals(""))
		{
			Conexion c=new Conexion();
			if(Consultas.CompruebaDup("id", "modelos", txtid.getText(), c)) //si el id existe
			{
				txtnom.setText(Consultas.RetornaValorbyTabla("modelos", "nombre", "id", txtid.getText(), c));
				if(Consultas.CompruebaDup("id_modelo", "detalle_linea", txtid.getText(), c)) //si el id existe
				{
					String idLinea=Consultas.RetornaValorbyTabla("detalle_linea","id_linea","id_modelo",txtid.getText(),c);
					txtnue.setText(Consultas.RetornaValorbyTabla("linea", "nombre", "id", idLinea, c));
				}else{
					txtnue.setText("Ninguna");
				}
				cblin.setSelectedItem(txtnue.getText());
				String idsue=Consultas.RetornaValorbyTabla("modelos","id_suela","id",txtid.getText(),c);
				if(idsue.equals(""))
				{
					txtsue.setText("");
				}else
					txtsue.setText(Consultas.RetornaValorbyTabla("suelas", "nombre", "id", idsue, c));
				cbsue.setSelectedItem(txtsue.getText());
				String arrC[][]=Consultas.RetornaArrayDetalle3val("detalle_color", "id_modelo="+txtid.getText(), c);
				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < arrC.length; j++) {
						if(arr[i][0].equals(arrC[j][2]))
						{
							chk[i].setSelected(true);
						}
					}
					
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
		txtnom.setText("");
		txtnue.setText("");
		txtsue.setText("");
		for (int i = 0; i < arr.length; i++) {
			chk[i].setSelected(false);
		}
		
	}
	private void btnhab(boolean ban)
	{
		btnmod.setEnabled(ban);
		btneli.setEnabled(ban);
		
	}
	private void lblreset(URL rut)
	{
		lbl1.setIcon(new ImageIcon(rut));
		lbl3.setIcon(new ImageIcon(rut));
		
	}
}
