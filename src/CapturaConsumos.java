import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CapturaConsumos extends JInternalFrame {
	private JTextField txttie;
	private JTextField txtcos;
	private JTable table;
	private JLabel lbl1,lbl2;
	private String [][]arrm,arri,arrt;
	JComboBox cbins,cbmod;
	DefaultTableModel modelo;
	JButton btnEliminar,btnModificar,btnAgregar;
	int id_m;
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");

	public CapturaConsumos() {
		setClosable(true);
		setTitle("Gesti\u00F3n de Consumos");
		setBounds(100, 100, 594, 475);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 232, 476, 202);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		cbmod = new JComboBox();
		cbmod.setBounds(199, 26, 237, 20);
		getContentPane().add(cbmod);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setBounds(112, 29, 77, 14);
		getContentPane().add(lblModelo);
		
		JLabel lblInsumo = new JLabel("Insumo");
		lblInsumo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInsumo.setBounds(92, 67, 97, 14);
		getContentPane().add(lblInsumo);
		
		cbins = new JComboBox();
		cbins.setBounds(199, 64, 202, 17);
		getContentPane().add(cbins);
		
		txttie = new JTextField();
		txttie.setHorizontalAlignment(SwingConstants.CENTER);
		txttie.setBounds(198, 101, 86, 20);
		getContentPane().add(txttie);
		txttie.setColumns(10);
		
		JLabel lblTiempoDeFabricacion = new JLabel("Tiempo de fabricacion");
		lblTiempoDeFabricacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTiempoDeFabricacion.setBounds(10, 104, 179, 14);
		getContentPane().add(lblTiempoDeFabricacion);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCosto.setBounds(112, 140, 77, 14);
		getContentPane().add(lblCosto);
		
		txtcos = new JTextField();
		txtcos.setHorizontalAlignment(SwingConstants.CENTER);
		txtcos.setBounds(199, 137, 86, 20);
		getContentPane().add(txtcos);
		txtcos.setColumns(10);
		
		JLabel lblDias = new JLabel("dias");
		lblDias.setBounds(295, 104, 67, 14);
		getContentPane().add(lblDias);
		
		JLabel lblPorUnidad = new JLabel("% por unidad");
		lblPorUnidad.setBounds(295, 140, 141, 14);
		getContentPane().add(lblPorUnidad);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(143, 185, 89, 23);
		getContentPane().add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregar();
			}
		});
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(242, 185, 89, 23);
		getContentPane().add(btnModificar);
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(341, 185, 89, 23);
		getContentPane().add(btnEliminar);
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaConsumos.class.getResource("/img/X.png")));
		lbl1.setBounds(403, 107, 46, 14);
		getContentPane().add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(CapturaConsumos.class.getResource("/img/X.png")));
		lbl2.setBounds(403, 140, 46, 14);
		getContentPane().add(lbl2);
		
		addTorC();
	}
	
	private void addTorC() //
	{
		cbins.removeAllItems();
		cbmod.removeAllItems();
		cbmod.addItem("Escoge un modelo");
		cbins.addItem("Escoge un insumo");
		Conexion co=new Conexion();
		arrm=Consultas.RetornaArrayCT("modelos","nombre", co);
		arri=Consultas.RetornaArrayCT("insumos","nombre", co);
		co.closeConexion();
		if(arrm!=null && arri!=null){
			eventosF();
		}
		else{
			JOptionPane.showMessageDialog(null, "Debes de insertar modelos e insumos");
		}
		for (int i = 0; i < arrm.length; i++) {
			cbmod.addItem(arrm[i][1]);
		}
		for (int i = 0; i < arri.length; i++) {
			cbins.addItem(arri[i][1]);
		}
	}
	
	private void eventosF()
	{
		txttie.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(txttie.getText().equals(""))
				{
					lbl1.setIcon(new ImageIcon(mal));
				}else{
					try{
						double x=Double.parseDouble(txttie.getText());
						lbl1.setIcon(new ImageIcon(bien));
					}catch(NumberFormatException e){
						lbl1.setIcon(new ImageIcon(mal));
					}
				}
			}
			public void focusGained(FocusEvent arg0) {
				lbl1.setIcon(null);
			}
		});
		txtcos.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(txtcos.getText().equals(""))
				{
					lbl2.setIcon(new ImageIcon(mal));
				}else{
					try{
						double x=Double.parseDouble(txtcos.getText());
						lbl2.setIcon(new ImageIcon(bien));
					}catch(NumberFormatException e){
						lbl2.setIcon(new ImageIcon(mal));
					}
				}
			}
			public void focusGained(FocusEvent arg0) {
				lbl2.setIcon(null);
			}
		});
		cbmod.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cbins.setSelectedIndex(0);
				btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
				btnAgregar.setEnabled(true);
				txttie.setText("");
				txtcos.setText("");
				lbl1.setIcon(new ImageIcon(mal));
				lbl2.setIcon(new ImageIcon(mal));
				if(cbmod.getSelectedIndex()>0)
				{
					id_m=Integer.parseInt(arrm[cbmod.getSelectedIndex()-1][0]);
					obtInfo(id_m);
					datostabla();
				}else{
					obtInfo(-1);
					datostabla();
					table.setModel(modelo);
				}
			}
		});
		cbins.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(cbins.getSelectedIndex()>0)
				{
					if(cbmod.getSelectedIndex()==0)
					{
						cbins.setSelectedIndex(0);
						if(cbins.getSelectedIndex()==0)
							JOptionPane.showMessageDialog(null, "Seleccionar un modelo previamente");
						
						
					}else{
						String x=arri[cbins.getSelectedIndex()-1][0];
						boolean encontrado=false;
						if(modelo.getRowCount()>0)
						for (int i = 0; i < table.getRowCount(); i++) {
							if(modelo.getValueAt(i, 1).toString().equals(x))
							{
								txttie.setText(modelo.getValueAt(i, 3).toString());
								txtcos.setText(modelo.getValueAt(i, 4).toString());
								lbl1.setIcon(new ImageIcon(bien));
								lbl2.setIcon(new ImageIcon(bien));
								btnEliminar.setEnabled(true);
								btnModificar.setEnabled(true);
								btnAgregar.setEnabled(false);
								encontrado=true;
								break;
							}
						}
						if(!encontrado)
						{
							btnEliminar.setEnabled(false);
							btnModificar.setEnabled(false);
							btnAgregar.setEnabled(true);
							txttie.setText("");
							txtcos.setText("");
							lbl1.setIcon(new ImageIcon(mal));
							lbl2.setIcon(new ImageIcon(mal));
						}
					}
				}else{
					btnEliminar.setEnabled(false);
					btnModificar.setEnabled(false);
					btnAgregar.setEnabled(true);
				}
			}
		});
	}
	private void modificar(){
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()) )
		{
			Conexion c=new Conexion();
			String idSpecific=Consultas.RetornaIdSpeficicCondicion(c, "consumos", "id_modelo="+arrm[cbmod.getSelectedIndex()-1][0]+" and id_insumo="+arri[cbins.getSelectedIndex()-1][0]);
			if(!Consultas.ModificaGeneral("consumos", "tiempo", txttie.getText(), "id", idSpecific, c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el tiempo de fabricacion");
			if(!Consultas.ModificaGeneral("consumos", "costo_unidad", txtcos.getText(), "id", idSpecific, c))
				JOptionPane.showMessageDialog(null, "Error al actualizar el costo por unidad");
			c.closeConexion();
			JOptionPane.showMessageDialog(null, "Campos actualizados");
			obtInfo(id_m);
			datostabla();
		}
	}
	private void eliminar(){
		Conexion c=new Conexion();
		String idSpecific=Consultas.RetornaIdSpeficicCondicion(c, "consumos", "id_modelo="+arrm[cbmod.getSelectedIndex()-1][0]+" and id_insumo="+arri[cbins.getSelectedIndex()-1][0]);
		if(Consultas.EliminaGeneral("consumos","id", idSpecific, c))
			JOptionPane.showMessageDialog(null, "Error al eliminar");
		else{
			btnEliminar.setEnabled(false);
			btnModificar.setEnabled(false);
			btnAgregar.setEnabled(true);
			txttie.setText("");
			txtcos.setText("");
			lbl1.setIcon(new ImageIcon(mal));
			lbl2.setIcon(new ImageIcon(mal));
			cbins.setSelectedIndex(0);
			JOptionPane.showMessageDialog(null, "insumo eliminado satisfactoriamente");
		}
		c.closeConexion();
		
		obtInfo(id_m);
		datostabla();
	}
	private void agregar() {
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()) )
		{
			Conexion c=new Conexion(); //insert	
			int x=Integer.parseInt(Consultas.RetornaId(c, "consumos"));
			if(Consultas.InsertaConsumos(x,Integer.parseInt(arrm[cbmod.getSelectedIndex()-1][0]),
					Integer.parseInt(arri[cbins.getSelectedIndex()-1][0]),Double.parseDouble(txttie.getText().trim())
					,Double.parseDouble(txtcos.getText().trim()), c))
				{
				
					JOptionPane.showMessageDialog(null, "Insumo agregado al consumo del modelo");
					txttie.setText("");
					txtcos.setText("");
					cbins.setSelectedIndex(0);
					lblreset();
					obtInfo(id_m);
					datostabla();
				}
			else
				JOptionPane.showMessageDialog(null, "Error al insertar inesperado");
			
			c.closeConexion();
		}else{
			JOptionPane.showMessageDialog(null, "oh no faltan completar algunos campos ");
		}
		
	}
	private void lblreset()
	{
		lbl1.setIcon(new ImageIcon(mal));
		lbl2.setIcon(new ImageIcon(mal));
		
	}
	@SuppressWarnings("serial")
	private void datostabla() {
		modelo=new DefaultTableModel(new Object[][]{},new String[] {"Modelo", "ID_Insumo", "Insumo", "Tiempo", "Costo_unidad"}){};

		Conexion c=new Conexion();
		for (int i = 0; i < arrt.length; i++) {
			String mod=Consultas.RetornaColumbyID("modelos", "nombre", Integer.parseInt(arrt[i][1]), c);
			String insumo=Consultas.RetornaColumbyID("insumos", "nombre", Integer.parseInt(arrt[i][2]), c);
			modelo.addRow(new Object[]{mod,arrt[i][2],insumo,arrt[i][3],arrt[i][4]});
		}
		c.closeConexion();
		table.setModel(modelo);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);

		table.getColumnModel().getColumn(0).setMinWidth(120);
		table.getColumnModel().getColumn(1).setMinWidth(80);
		table.getColumnModel().getColumn(2).setMinWidth(120);
		table.getColumnModel().getColumn(3).setMinWidth(80);
		table.getColumnModel().getColumn(4).setMinWidth(80);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.setEnabled(false);
	}

	private void obtInfo(int id_mod)
	{
		Conexion c=new Conexion();
		arrt=Consultas.RetornaArraybyColumConsumos("consumos",id_mod, c);
		c.closeConexion();
		
	}
}
