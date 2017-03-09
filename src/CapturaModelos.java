import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
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

public class CapturaModelos extends JInternalFrame {

	private JTextField txtid;
	private JTextField txtnom;
	JPanel panel;
	private JTextField txtnue;
	JComboBox cbsue,cblin;
	JLabel lbl1,lbl2,lbl3;
	JCheckBox chk[];
	JCheckBox ck;
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	String [][]arr,arrs,arrl;
	/**
	 * Create the frame.
	 */
	public CapturaModelos() {
		setClosable(true);
		setTitle("Captura Modelos");
		setResizable(true);
		setBounds(100, 100, 435, 360);
		getContentPane().setLayout(null);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		txtid.setBounds(172, 27, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		JLabel lblId = new JLabel("id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(116, 30, 46, 14);
		getContentPane().add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(76, 61, 86, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblUnidad = new JLabel("Suela");
		lblUnidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnidad.setBounds(92, 94, 70, 14);
		getContentPane().add(lblUnidad);
		
		txtnom = new JTextField();
		txtnom.setBounds(172, 58, 86, 20);
		getContentPane().add(txtnom);
		txtnom.setColumns(10);
		
		cbsue = new JComboBox();
		cbsue.setBounds(172, 91, 86, 20);
		getContentPane().add(cbsue);
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		lbl1.setBounds(268, 61, 46, 14);
		getContentPane().add(lbl1);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setMnemonic('A');
		btnAgregar.setBounds(168, 296, 89, 23);
		getContentPane().add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(51, 162, 302, 123);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon(CapturaModelos.class.getResource("/img/X.png")));
		lbl3.setBounds(363, 215, 46, 14);
		getContentPane().add(lbl3);
		
		lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(CapturaModelos.class.getResource("/img/a.gif")));
		lbl2.setBounds(373, 137, 46, 14);
		getContentPane().add(lbl2);
		
		cblin = new JComboBox();
		cblin.setBounds(70, 131, 102, 20);
		getContentPane().add(cblin);
		cblin.addItem("Ninguna");
		
		JLabel lblLinea = new JLabel("Linea");
		lblLinea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLinea.setBounds(14, 134, 46, 14);
		getContentPane().add(lblLinea);
		
		ck = new JCheckBox("nueva linea");
		ck.setBounds(178, 132, 97, 23);
		getContentPane().add(ck);
		ck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(ck.isSelected())
				{
					txtnue.setEditable(true);
					txtnue.setEnabled(true);
					cblin.setEnabled(false);
					txtnueValid();
				}else{
					txtnue.setEditable(false);
					txtnue.setEnabled(false);
					cblin.setEnabled(true);
					lbl2.setIcon(new ImageIcon(bien));
				}
			}
		});
		
		txtnue = new JTextField();
		txtnue.setEnabled(false);
		txtnue.setEditable(false);
		txtnue.setBounds(277, 131, 86, 20);
		getContentPane().add(txtnue);
		txtnue.setColumns(10);
		
		addTorC();

	}
	private void addTorC() //metodo que agrega los valores de las tallas a un arreglo de strng para mostrar los checkboxes
	{
		cbsue.removeAllItems();
		cblin.removeAllItems();
		cblin.addItem("Ninguna");
		Conexion co=new Conexion();
		txtid.setText(Consultas.RetornaId(co, "modelos"));
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
		txtnue.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				txtnueValid();
			}
			public void focusGained(FocusEvent arg0) {
				lbl2.setIcon(null);
			}
		});
	}
	private void txtnueValid()
	{
		if(txtnue.getText().equals(""))
		{
			lbl2.setIcon(new ImageIcon(mal));
		}else{
			Conexion c=new Conexion();
			if(Consultas.CompruebaDup("nombre", "linea", txtnue.getText().trim(), c))
			{
				lbl2.setIcon(new ImageIcon(mal));
			}else{
				lbl2.setIcon(new ImageIcon(bien));
			}
			c.closeConexion();
		}
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
	private void agregar() {
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()) &&
				lbl3.getIcon().toString().equals(im.toString()))
		{
			Conexion c=new Conexion(); //insertModelos
			if(ck.isSelected())
			{
				int x=Integer.parseInt(Consultas.RetornaId(c, "linea"));
				if(Consultas.InsertaLinea(x,txtnue.getText(),c)){
					int idB=Integer.parseInt(Consultas.RetornaId(c, "detalle_linea"));
					if(Consultas.InsertaDetalleTallaColor("detalle_linea",idB, Integer.parseInt(txtid.getText()),x, c))
					JOptionPane.showMessageDialog(null, "Nueva linea agregada");
				}
			}else{
				if(cblin.getSelectedIndex()>0){
					int idB=Integer.parseInt(Consultas.RetornaId(c, "detalle_linea"));
				if(Consultas.InsertaDetalleTallaColor("detalle_linea",idB, Integer.parseInt(txtid.getText()),
						Integer.parseInt(arrl[cblin.getSelectedIndex()-1][0]), c))
				;
				}
			}
			if(Consultas.InsertaModelos(Integer.parseInt(txtid.getText()), txtnom.getText(),Integer.parseInt(arrs[cbsue.getSelectedIndex()][0]), c))
				{
				for (int i = 0; i < arr.length; i++) {
					if(chk[i].isSelected())
					{
						int idB=Integer.parseInt(Consultas.RetornaId(c, "detalle_color"));
						if(!Consultas.InsertaDetalleTallaColor("detalle_color",idB, Integer.parseInt(txtid.getText()), Integer.parseInt(arr[i][0]), c))
						{
							JOptionPane.showMessageDialog(null, "Error al agregar color "+arr[i][1]);
						}
						chk[i].setSelected(false);
					}
				}
					JOptionPane.showMessageDialog(null, "Modelo agregado con exito ");
					txtid.setText(Consultas.RetornaId(c, "modelos"));
					txtnom.setText("");
					txtnue.setText("");
					ck.setSelected(false);
					txtnue.setEnabled(false);
					txtnue.setEditable(false);
					cblin.setEnabled(true);
					addTorC();
					lblreset();
					txtnom.requestFocus();
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
		lbl2.setIcon(new ImageIcon(bien));
		lbl3.setIcon(new ImageIcon(mal));
	}
}
