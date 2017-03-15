import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MostrarSuelas extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;
	JButton btnbus,btnmod,btneli;
	JLabel lbl1,lbl2;
	JPanel panel;
	JCheckBox chk[];
	String arr[][];
	private String [][]arrt; 
	DefaultTableModel modelo;
	private JTable table;
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	
	public MostrarSuelas() {
		setClosable(true);
		setTitle("Suelas");
		setBounds(100, 100, 481, 462);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(116, 31, 83, 14);
		getContentPane().add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(116, 62, 83, 14);
		getContentPane().add(lblNombre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(105, 107, 289, 127);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JLabel lblTallas = new JLabel("Tallas");
		lblTallas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTallas.setBounds(12, 107, 83, 14);
		getContentPane().add(lblTallas);
		
		txtid = new JTextField();
		txtid.setEnabled(true);
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(true);
		txtid.setBounds(209, 28, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		txtnom = new JTextField();
		txtnom.setHorizontalAlignment(SwingConstants.CENTER);
		txtnom.setBounds(209, 59, 86, 20);
		getContentPane().add(txtnom);
		txtnom.setColumns(10);
		
		btnbus= new JButton("Buscar");
		btnbus.setMnemonic('b');
		btnbus.setBounds(77, 387, 89, 23);
		getContentPane().add(btnbus);
	
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaSuelas.class.getResource("/img/X.png")));
		lbl1.setBounds(305, 62, 46, 14);
		getContentPane().add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(CapturaSuelas.class.getResource("/img/X.png")));
		lbl2.setBounds(404, 167, 46, 14);
		getContentPane().add(lbl2);
		
		btnmod = new JButton("Modificar");
		btnmod.setMnemonic('m');
		btnmod.setBounds(206, 387, 89, 23);
		getContentPane().add(btnmod);
		
		btneli = new JButton("Eliminar");
		btneli.setMnemonic('e');
		btneli.setBounds(334, 387, 89, 23);
		getContentPane().add(btneli);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(105, 245, 289, 127);
		getContentPane().add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JLabel lblSuelas = new JLabel("Suelas");
		lblSuelas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSuelas.setBounds(31, 259, 64, 14);
		getContentPane().add(lblSuelas);
		
		eventosBTN();
		obtInfo();
		datostabla();
		Conexion co=new Conexion();
		arr=Consultas.RetornaArrayCT("tallas","talla", co);
		co.closeConexion();
		if(arr!=null){
			ChkBoxes();
			eventosF();
		}
		else{
			JOptionPane.showMessageDialog(null, "Debes de insertar tallas primero");
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
					if(Consultas.CompruebaDup("nombre", "suelas", txtnom.getText().trim(), c))
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
		
	}
	
	private void ChkBoxes() {
		int x=10,y=10;
		int col=0;
		chk=new JCheckBox[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if(col==3)
			{
				col=0;
				x=10;
				y+=20;
			}
			col++;
			chk[i]=new JCheckBox(arr[i][1]);
			chk[i].setBounds(x, y, 80, 20);
			panel.add(chk[i]);
			addCHK(chk[i]);
			x+=80;
		}
		
		panel.setPreferredSize(new Dimension(x+80,y+40));
		
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
					lbl2.setIcon(new ImageIcon(bien));
				else
					lbl2.setIcon(new ImageIcon(mal));
			}
			public void focusGained(FocusEvent arg0) {
				lbl2.setIcon(null);
			}
		});
	}
	private void modificar(){
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()))
		{
			String idaeli;
			Conexion c=new Conexion();
			if(!Consultas.ModificaGeneral("suelas", "nombre", txtnom.getText(), "id", txtid.getText(), c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el nombre");
			idaeli=Consultas.RetornaIdToDelete(c, "detalle_talla", "id_suela="+txtid.getText());
			while(!idaeli.equals("-1")){
				if(Consultas.EliminaGeneral("detalle_talla","id",idaeli, c))
					JOptionPane.showMessageDialog(null, "Error al eliminar una talla de la suela");
				idaeli=Consultas.RetornaIdToDelete(c, "detalle_talla", "id_suela="+txtid.getText());
			}
			for (int i = 0; i < arr.length; i++) {
				if(chk[i].isSelected())
				{
					int idB=Integer.parseInt(Consultas.RetornaId(c, "detalle_talla"));
					if(!Consultas.InsertaDetalleTallaColor("detalle_talla",idB, Integer.parseInt(txtid.getText()), Integer.parseInt(arr[i][0]), c))
					{
						JOptionPane.showMessageDialog(null, "Error al agregar talla "+arr[i][1]);
					}
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
		if(Consultas.EliminaGeneral("suelas","id", txtid.getText(), c))
			JOptionPane.showMessageDialog(null, "Error al eliminar");
		else{
			String idaeli;
			idaeli=Consultas.RetornaIdToDelete(c, "detalle_talla", "id_suela="+txtid.getText());
			while(!idaeli.equals("-1")){
				if(Consultas.EliminaGeneral("detalle_talla","id",idaeli, c))
					JOptionPane.showMessageDialog(null, "Error al eliminar una talla de la suela");
				idaeli=Consultas.RetornaIdToDelete(c, "detalle_talla", "id_suela="+txtid.getText());
			}
			idaeli=Consultas.RetornaIdToDelete(c, "modelos", "id_suela="+txtid.getText());
			while(!idaeli.equals("-1")){
				if(!Consultas.ModificaGeneral("modelos", "id_suela", "-1", "id", idaeli, c))
					JOptionPane.showMessageDialog(null, "Error al actualizar el nombre");
				idaeli=Consultas.RetornaIdToDelete(c, "modelos", "id_suela="+txtid.getText());
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
			if(Consultas.CompruebaDup("id", "suelas", txtid.getText(), c)) //si el id existe
			{
				txtnom.setText(Consultas.RetornaValorbyTabla("suelas", "nombre", "id", txtid.getText(), c));
				String arrC[][]=Consultas.RetornaArrayDetalle3val("detalle_talla", "id_suela="+txtid.getText(), c);
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
		lbl2.setIcon(new ImageIcon(rut));
		
	}
//	
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
		table.getColumnModel().getColumn(1).setMinWidth(210);
		
		//para que el JScrollPane pueda hacer scroll horizontal
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setEnabled(false); //para que no sea posible editar
		
	}

	private void obtInfo()
	{
		Conexion c=new Conexion();
		arrt=Consultas.RetornaArraySuelas(c); //consulta que se debe generar para cada tabla
		c.closeConexion();
		
	}
	
}
