import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class CapturaPedidos extends  JFrame//JInternalFrame 
{
	private JTextField txtid;
	private JTextField txtfce;
	private JTable table;
	JButton btnmos,btnpro,btnmas,btnmod,btneli;
	JLabel lbl1,lbl2,lbl5,lbl3,lbl4;
	JComboBox cmbcli,cmbmod,cmbtal,cmbcol,cmbsue,cmbcsu;
	String [][] cocl, comd,cmbnot,cmbnoc,caracP,caracPb=new String[50][11];
	String consin,id_sue,id_mod;
	int r=0,c=0,x=8,y=9;//indices para el arreglo del boton + y para los radiob
	int selec=-1;
	Date fecha = new Date();
	Calendar fecped=new GregorianCalendar();
	Calendar fecent;
	private JTextField txtcan;
	JRadioButton[] arrchb;
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	DefaultTableModel modelo; //modelo de la tabla
	private JTextField txtfcp;
	private JTextField txtdia;
	private JPanel panel;
	private JPanel panel_1;
	JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new CapturaPedidos().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public CapturaPedidos() {
		//setClosable(true);
		setTitle("Captura Pedidos");
		setBounds(100, 100, 830, 600);
		getContentPane().setLayout(null);
		
		
		
		btnpro = new JButton("Producir");
		btnpro.setBounds(128, 351, 97, 25);
		getContentPane().add(btnpro);
		btnpro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Producir();
				
			}
		});
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		String fch=formatoFecha.format(fecha);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 397, 788, 145);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		getContentPane().add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Conexion co=new Conexion();
		int ano=fecped.get(Calendar.YEAR);
		int mes=fecped.get(Calendar.MONTH)+1;
		int day=fecped.get(Calendar.DATE);
		
		panel = new JPanel();
		panel.setBounds(12, 13, 788, 62);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Pedido", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtid = new JTextField();
		txtid.setBounds(68, 29, 86, 20);
		panel.add(txtid);
		txtid.setEditable(false);
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setColumns(10);
		txtid.setText(Consultas.RetornaId(co, "pedidos"));
		
		JLabel lblid = new JLabel("Id");
		lblid.setBounds(12, 32, 46, 14);
		panel.add(lblid);
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cmbmod = new JComboBox();
		cmbmod.setBounds(612, 27, 128, 22);
		panel.add(cmbmod);
		cmbmod.addItem("Elige un Modelo");
		
		
		JLabel lblmod = new JLabel("Modelo");
		lblmod.setBounds(544, 30, 46, 14);
		panel.add(lblmod);
		lblmod.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cmbcli = new JComboBox();
		cmbcli.setBounds(314, 29, 128, 22);
		panel.add(cmbcli);
		cmbcli.addItem("Elige un Cliente");
		
		JLabel lblcli = new JLabel("Cliente");
		lblcli.setBounds(246, 33, 46, 14);
		panel.add(lblcli);
		lblcli.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaPedidos.class.getResource("/img/X.png")));
		lbl1.setBounds(450, 37, 46, 14);
		panel.add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(CapturaPedidos.class.getResource("/img/X.png")));
		lbl2.setBounds(752, 35, 46, 14);
		panel.add(lbl2);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 200, 788, 107);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Carga del Pedido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 255)));
		getContentPane().add(panel_1);
		
		JLabel lbltal = new JLabel("Tallas");
		lbltal.setBounds(12, 31, 46, 14);
		panel_1.add(lbltal);
		lbltal.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cmbtal = new JComboBox();
		cmbtal.setBounds(68, 27, 128, 22);
		panel_1.add(cmbtal);
		cmbtal.setEnabled(false);
		
		cmbcol = new JComboBox();
		cmbcol.setBounds(310, 23, 128, 22);
		panel_1.add(cmbcol);
		cmbcol.setEnabled(false);
		
		
		JLabel lblcol = new JLabel("Color Talla");
		lblcol.setBounds(227, 27, 73, 14);
		panel_1.add(lblcol);
		lblcol.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtcan = new JTextField();
		txtcan.setBounds(535, 49, 86, 20);
		panel_1.add(txtcan);
		txtcan.setHorizontalAlignment(SwingConstants.RIGHT);
		txtcan.setEnabled(false);
		txtcan.setColumns(10);
		
		JLabel lblcan = new JLabel("Cantidad");
		lblcan.setBounds(463, 51, 60, 14);
		panel_1.add(lblcan);
		lblcan.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lbl5 = new JLabel("");
		lbl5.setBounds(633, 51, 46, 14);
		panel_1.add(lbl5);
		lbl5.setIcon(new ImageIcon(CapturaPedidos.class.getResource("/img/X.png")));
		
		btnmas = new JButton("+");
		btnmas.setBounds(721, 41, 55, 32);
		panel_1.add(btnmas);
		btnmas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnmas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BotonMas();
				
			}
		});
		
		cmbsue = new JComboBox();
		cmbsue.setEnabled(false);
		cmbsue.setBounds(68, 72, 128, 22);
		panel_1.add(cmbsue);
		
		JLabel lblsue = new JLabel("Suelas");
		lblsue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblsue.setBounds(12, 76, 46, 14);
		panel_1.add(lblsue);
		
		JLabel lblcsu = new JLabel("Color Suela");
		lblcsu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcsu.setBounds(227, 76, 73, 14);
		panel_1.add(lblcsu);
		
		cmbcsu = new JComboBox();
		cmbcsu.setEnabled(false);
		cmbcsu.setBounds(310, 72, 128, 22);
		panel_1.add(cmbcsu);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 110, 788, 62);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fechas del Pedido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 255)));
		getContentPane().add(panel_2);
		
		JLabel lblfcr = new JLabel("Fecha de pedido");
		lblfcr.setBounds(12, 29, 105, 14);
		panel_2.add(lblfcr);
		lblfcr.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblfcp = new JLabel("Fecha de entrega");
		lblfcp.setBounds(512, 29, 116, 14);
		panel_2.add(lblfcp);
		lblfcp.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtfce = new JTextField();
		txtfce.setBounds(640, 26, 131, 20);
		panel_2.add(txtfce);
		txtfce.setHorizontalAlignment(SwingConstants.CENTER);
		txtfce.setEditable(false);
		txtfce.setColumns(10);
		
		txtfcp = new JTextField();
		txtfcp.setBounds(118, 23, 128, 20);
		panel_2.add(txtfcp);
		txtfcp.setHorizontalAlignment(SwingConstants.CENTER);
		txtfcp.setEnabled(false);
		txtfcp.setColumns(10);
		txtfcp.setText(day+"/"+mes+"/"+ano);
		
		lbl3 = new JLabel("");
		lbl3.setBounds(247, 29, 46, 14);
		panel_2.add(lbl3);
		lbl3.setIcon(new ImageIcon(CapturaPedidos.class.getResource("/img/a.gif")));
		
		JLabel lbldia = new JLabel("Semana");
		lbldia.setBounds(269, 25, 116, 14);
		panel_2.add(lbldia);
		lbldia.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtdia = new JTextField();
		txtdia.setBounds(395, 23, 86, 20);
		panel_2.add(txtdia);
		txtdia.setHorizontalAlignment(SwingConstants.RIGHT);
		txtdia.setEnabled(false);
		txtdia.setColumns(10);
		
		lbl4 = new JLabel("");
		lbl4.setBounds(482, 27, 46, 14);
		panel_2.add(lbl4);
		lbl4.setIcon(new ImageIcon(CapturaPedidos.class.getResource("/img/X.png")));
		
		btnmod = new JButton("Modificar");
		btnmod.setBounds(350, 351, 97, 25);
		btnmod.setEnabled(false);
		getContentPane().add(btnmod);
		btnmod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BotonModi();
				
			}
		});
		
		btneli = new JButton("Eliminar");
		btneli.setBounds(575, 351, 97, 25);
		btneli.setEnabled(false);
		getContentPane().add(btneli);
		
		CombLle();
		cmbmod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ConsultaIModelo();
				
			}
		});
		
		co.closeConexion();
		
		eventosF();
		
		
		
	}
	private void BotonMas()
	{
		
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()) && lbl5.getIcon().toString().equals(im.toString()) && lbl3.getIcon().toString().equals(im.toString()) &&
				lbl4.getIcon().toString().equals(im.toString()))
		{
			caracPb=caracP;
			caracP=new String [r+1][11];
			
		caracP[r][c]=txtid.getText().toString();
		c++;
		caracP[r][c]=cmbcli.getSelectedItem().toString();
		c++;
		caracP[r][c]=cmbmod.getSelectedItem().toString();
		c++;
		caracP[r][c]=txtfcp.getText().toString();
		c++;
		caracP[r][c]=txtdia.getText().toString();
		c++;
		caracP[r][c]=txtfce.getText().toString();
		c++;
		caracP[r][c]=cmbtal.getSelectedItem().toString();
		c++;				
		caracP[r][c]=cmbcol.getSelectedItem().toString();
		c++;
		caracP[r][c]=txtcan.getText().toString();
		c++;
		caracP[r][c]=cmbsue.getSelectedItem().toString();
		c++;
		cmbcsu.addItem("Ambar");//Prueba
		caracP[r][c]=cmbcsu.getSelectedItem().toString();
		r++;
		c=0;	
		if(r==1)
			caracPb=caracP;
		else{
		for (int i = 0; i < r-1; i++) {
			for (int j = 0; j < caracP[i].length; j++) {
				caracP[i][j]=caracPb[i][j];
				//System.out.println(caracP[i][j]);
				//System.out.println("B"+caracPb[i][j]);
			}
		}
		}
		System.out.println("*********************************************");
		txtcan.setText("");
		lbl5.setIcon(new ImageIcon(mal));
		datostabla();
		}else{
			if(caracP==null)
				JOptionPane.showMessageDialog(null, "oh no faltan completar algunos campos ");
		}
		
	}
	private void lblreset()
	{
		lbl1.setIcon(new ImageIcon(mal));
		lbl2.setIcon(new ImageIcon(mal));
		lbl4.setIcon(new ImageIcon(mal));
		lbl5.setIcon(new ImageIcon(mal));
	}
	private void Producir()
	{
		Conexion c=new Conexion();
		BotonMas();
		if(caracP!=null){
			for (int i = 0; i < caracP.length; i++) {
				for (int j = 0; j < caracP[i].length; j++) {
					System.out.print(caracP[i][j]+" ");
				}
				System.out.println();
			}
		
		if(Consultas2.InsertaPedidos(Integer.parseInt(caracP[0][0]),caracP[0][1],caracP[0][3],caracP[0][5],1,Integer.parseInt(caracP[0][4]),c))
		{
		for (int i = 0; i < caracP.length; i++) {
				
					//PEDIDO SUELA
					String id_detalle_pedido= Consultas.RetornaId(c, "detalle_pedido_s");
					if(Consultas2.InsertaDetallePedidoS("detalle_pedido_s",Integer.parseInt(id_detalle_pedido), Integer.parseInt(caracP[i][0]), caracP[i][9], caracP[i][10], c))
					{
						JOptionPane.showMessageDialog(null, "Detalle de Pedido agregado con exito ");
					}
					else
					JOptionPane.showMessageDialog(null, "Error de la base de datos al insertar inesperado Detalle de Pedido");
					
					//PEDIDO MODELO
					id_detalle_pedido= Consultas.RetornaId(c, "detalle_pedido");
					if(Consultas2.InsertaDetallePedidoS("detalle_pedido",Integer.parseInt(id_detalle_pedido), Integer.parseInt(caracP[i][0]), caracP[i][2], caracP[i][7], c))
					{
						JOptionPane.showMessageDialog(null, "Detalle de PedidoM agregado con exito ");
					}
					else
					JOptionPane.showMessageDialog(null, "Error de la base de datos al insertar inesperado Detalle de PedidoM");
					
					//PEDIDO TALLA
					id_detalle_pedido= Consultas.RetornaId(c, "pedido_talla");
					if(Consultas2.InsertaDetallePedidoS("pedido_talla",Integer.parseInt(id_detalle_pedido), Integer.parseInt(caracP[i][0]), caracP[i][6], caracP[i][8], c))
					{
						JOptionPane.showMessageDialog(null, "Detalle de PedidoT agregado con exito ");
					}
					else
					JOptionPane.showMessageDialog(null, "Error de la base de datos al insertar inesperado Detalle de PedidoT");
					
					
					JOptionPane.showMessageDialog(null, "Pedido agregado con exito ");
					txtid.setText(Consultas.RetornaId(c, "Pedidos"));
					Reiniciar();//se devuelven las etiquetas, combos y campos de texto a como cuando la ventana se abrio para volver a capturar datos
		}
		}
		else
			JOptionPane.showMessageDialog(null, "Error de la base de datos al insertar inesperado ");
		}
		
		c.closeConexion();
	}
	private void Reiniciar()
	{
		txtcan.setText("");
		txtdia.setText("");
		txtfce.setText("");
		Hab(false);
		cmbcol.removeAllItems();
		cmbcsu.removeAllItems();
		cmbsue.removeAllItems();
		cmbtal.removeAllItems();
		lblreset();
	}
	private void SelecRowTabla()
	{
		btnpro.setEnabled(false);
		btnmas.setEnabled(false);
		btnmod.setEnabled(true);
		btneli.setEnabled(true);
		selec=table.getSelectedRow();
		if(selec!=-1)
		{
			txtid.setText(caracP[selec][0]);
			cmbcli.setSelectedItem(caracP[selec][1]);
			cmbmod.setSelectedItem(caracP[selec][2]);
			txtfcp.setText(caracP[selec][3]);
			txtdia.setText(caracP[selec][4]);
			txtfce.setText(caracP[selec][5]);
			cmbtal.setSelectedItem(caracP[selec][6]);
			cmbcol.setSelectedItem(caracP[selec][7]);
			txtcan.setText(caracP[selec][8]);
			lbl5.setIcon(new ImageIcon(CapturaPedidos.class.getResource("/img/a.gif")));
			cmbsue.setSelectedItem(caracP[selec][9]);			
			cmbcsu.setSelectedItem(caracP[selec][10]);
			
		}
		
	}
	private void BotonModi()
	{
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()) && lbl5.getIcon().toString().equals(im.toString()) && lbl3.getIcon().toString().equals(im.toString()) &&
				lbl4.getIcon().toString().equals(im.toString()))
		{
			caracPb=caracP;
			caracP[selec][0]=txtid.getText().toString();
			caracP[selec][1]=cmbcli.getSelectedItem().toString();
			caracP[selec][2]=cmbmod.getSelectedItem().toString();
			caracP[selec][3]=txtfcp.getText().toString();
			caracP[selec][4]=txtdia.getText().toString();
			caracP[selec][5]=txtfce.getText().toString();
			caracP[selec][6]=cmbtal.getSelectedItem().toString();
			caracP[selec][7]=cmbcol.getSelectedItem().toString();
			caracP[selec][8]=txtcan.getText().toString();
			caracP[selec][9]=cmbsue.getSelectedItem().toString();
			cmbcsu.addItem("Ambar");//Prueba
			caracP[selec][10]=cmbcsu.getSelectedItem().toString();
			if(r==1)
				caracPb=caracP;
			else{
				for (int j = 0; j < caracP[selec].length; j++) {
					caracP[selec][j]=caracPb[selec][j];
					//System.out.println(caracP[i][j]);
					//System.out.println("B"+caracPb[i][j]);
				}
		}
		cmbmod.setSelectedItem("Elige un Modelo");
		datostabla();
		}else{
			JOptionPane.showMessageDialog(null, "oh no faltan completar algunos campos ");
		}
		btnpro.setEnabled(true);
		btnmas.setEnabled(true);
		btnmod.setEnabled(false);
		btneli.setEnabled(false);
		
	}
	private void Hab(boolean b)
	{
		cmbcol.setEnabled(b);
		cmbtal.setEnabled(b);
		txtfcp.setEnabled(b);
		txtdia.setEnabled(b);
		txtcan.setEnabled(b);
		txtfce.setEnabled(b);
		cmbcsu.setEnabled(b);
		cmbsue.setEnabled(b);
	}
	private void ConsultaIModelo()
	{
		Conexion c=new Conexion();
		Hab(true);
		consin=cmbmod.getSelectedItem().toString();//Nombre del Modelo Seleccionado
		if(consin!="Elige un Modelo"){
		for (int j =0; j < comd.length; j++) {
			if(consin.equals(comd[j][1]))
			{
				
				//System.out.println(j+consin);
				id_sue=comd[j][2];//id de la suela del modelo seleccionado
				id_mod=comd[j][0];//id del modelo seleccionado
			}
			
		}
		String [][] det_tal=Consultas2.RetornaArrayModelos("detalle_talla","id", c);//id,id_suela,id_talla
		String [] id_tal=new String[det_tal.length];
		int i=0;
		for (int j =0; j < det_tal.length; j++) {
			if(id_sue.equals(det_tal[j][1]))
			{
				id_tal[i]=det_tal[j][2];//ids de las tallas
				i++;
			}
		}
		cmbnot=Consultas.RetornaArrayCT("tallas", "id", c);
		
		String [] tallas=new String[i];//ciclo para sacar las tallas
		cmbtal.removeAllItems();
		//cmbtal.addItem("Elige una Talla");
		for (int k = 0; k < tallas.length; k++) {
	
		for (int j=0; j < cmbnot.length;j++ ) {
			
			if(id_tal[k].equals(cmbnot[j][0])){
				
				tallas[k]=cmbnot[j][1];
				j=cmbnot.length;
				cmbtal.addItem(tallas[k]);
			} 
		}
		}
		//cmbtal.setModel(new DefaultComboBoxModel<>(tallas));
		
		String [][] det_col=Consultas2.RetornaArrayModelos("detalle_color","id", c);
		String [] id_col=new String[det_col.length];
		int t=0;
		for (int j =0; j < det_col.length; j++) {
			if(id_mod.equals(det_col[j][1]))
			{
				id_col[t]=det_col[j][2];//ids de los colores
				//System.out.println(id_col[t]);
				t++;
			}
		}
		
		cmbnoc=Consultas.RetornaArrayCT("colores", "id", c);
		cmbcol.removeAllItems();
		//cmbcol.addItem("Elige un Color");
		String [] colores=new String[t];//ciclo para sacar los colores
		for (int k = 0; k < colores.length; k++) {
	
			for (int j=0; j < cmbnoc.length;j++ ) {
			
				if(id_col[k].equals(cmbnoc[j][0])){
					colores[k]=cmbnoc[j][1];
					j=cmbnoc.length;
					cmbcol.addItem(colores[k]);
				} 
			}
		}
		
		//cmbcol.setModel(new DefaultComboBoxModel<>(colores));
		String [][] sue=Consultas.RetornaArrayCT("suelas","id", c);
		String [] nom_sue=new String[sue.length];
		i=0;
		cmbsue.removeAllItems();
		//cmbsue.addItem("Elige una Suela");
		System.out.println(id_sue);
		for (int j =0; j < sue.length; j++) {
			if(id_sue.equals(sue[j][0]))
			{
				nom_sue[i]=sue[j][1];//nombre de las suelas
				cmbsue.addItem(nom_sue[i]);
				i++;
			}
		}
		
		//Falta Agregar los colores de suela
		
		
		
		c.closeConexion();
		}else
		{
			Hab(false);
			cmbcol.removeAllItems();
			cmbcsu.removeAllItems();
			cmbsue.removeAllItems();
			cmbtal.removeAllItems();
			txtcan.setText("");
			txtdia.setText("");
			txtfce.setText("");
			lbl4.setIcon(new ImageIcon(mal));
			lbl5.setIcon(new ImageIcon(mal));
			
		}
	}
	private void CombLle()
	{
		Conexion c=new Conexion();
		cocl=Consultas.RetornaArrayClientes(c);
		String ncl[]=new String[cocl[0].length];
		for (int i=1, j =0; j < cocl.length; j++) {
				ncl[j]=cocl[j][i]+" "+cocl[j][i+1]+" "+cocl[j][i+1];
				cmbcli.addItem(ncl[j]);
			}
		//cmbcli.setModel(new DefaultComboBoxModel<>(ncl));
		
		comd=Consultas2.RetornaArrayModelos("modelos", "id", c);
		for (int j =0; j < comd.length; j++) {
			ncl[j]=comd[j][1];
			cmbmod.addItem(ncl[j]);
		}
	
		//cmbmod.setModel(new DefaultComboBoxModel<>(ncl));
		
		c.closeConexion();
	}
	
	private void eventosF()
	{
		cmbcli.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!cmbcli.getSelectedItem().toString().equals("Elige un Cliente")){
					
					lbl1.setIcon(new ImageIcon(bien));
				}else
					lbl1.setIcon(new ImageIcon(mal));
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				lbl1.setIcon(null);
			}
		});
		cmbmod.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!cmbmod.getSelectedItem().toString().equals("Elige un Modelo")){
					
					lbl2.setIcon(new ImageIcon(bien));
				}else
					lbl2.setIcon(new ImageIcon(mal));
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				lbl2.setIcon(null);
			}
		});
		
		txtcan.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				try{
					int test=Integer.parseInt(txtcan.getText());
					lbl5.setIcon(new ImageIcon(bien));
				}catch(NumberFormatException exet){
					lbl5.setIcon(new ImageIcon(mal));
				}
			}
			public void focusGained(FocusEvent e) {
				lbl5.setIcon(null);
			}
		});
		txtfcp.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtfcp.getText().toString().equals("")){
					
					lbl3.setIcon(new ImageIcon(bien));
				}else
					lbl3.setIcon(new ImageIcon(mal));
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				lbl3.setIcon(null);
				
			}
		});
		
		txtdia.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				try{
					int test=Integer.parseInt(txtdia.getText());
					lbl4.setIcon(new ImageIcon(bien));
					
					String fchp=txtfcp.getText().toString();
					String anop=fchp.substring(5,9);
					String mesp=fchp.substring(2,4);
					String diap=fchp.substring(0, 2);
					if(mesp.substring(0,1).equals("/"))
						mesp="0"+mesp.substring(1);
					int semac=ConfigGen.WeekOfYear(anop+"-"+mesp+"-"+diap);//Semana actual
					int semre=Integer.parseInt(txtdia.getText());//Semana recibida
					int dif=semre-semac;//las semanas de diferencia para calcular el dia de la semana
					System.out.println(semre+"  "+semac);
					System.out.println(anop+"-"+mesp+"-"+diap);
					dif*=7;//multiplicar la dif por 7 que son los dias de la semana
					fecent = new GregorianCalendar();
					fecent.add(Calendar.DAY_OF_MONTH, dif);
					int ano=fecent.get(Calendar.YEAR);
					int mes=fecent.get(Calendar.MONTH)+1;
					int day=fecent.get(Calendar.DATE);
					txtfce.setText(day+"/"+mes+"/"+ano);
				}catch(NumberFormatException exet){
					lbl4.setIcon(new ImageIcon(mal));
				}
			}
			public void focusGained(FocusEvent e) {
				lbl4.setIcon(null);
			}
		});
	}
	
	@SuppressWarnings("serial")
	private void datostabla() {
		modelo=new DefaultTableModel(new Object[][]{},new String[] {"ID", "Cliente", "Modelo", "Fch Pedido", "Dias Produccion"
				,"Fch Entrega","Talla","Color Talla","Cantidad","Suela","Color Suela"}){};
				
		Conexion c=new Conexion();
		for (int i = 0; i < caracP.length; i++) {
			modelo.addRow(new Object[]{caracP[i][0],caracP[i][1],caracP[i][2],caracP[i][3],caracP[i][4],caracP[i][5],caracP[i][6],caracP[i][7],caracP[i][8],caracP[i][9],caracP[i][10]});
		}
		c.closeConexion();
		table.setModel(modelo);
		
		//para centrar las columnas que queramos
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(5).setCellRenderer(tcr);
		table.getColumnModel().getColumn(6).setCellRenderer(tcr);
		table.getColumnModel().getColumn(7).setCellRenderer(tcr);
		table.getColumnModel().getColumn(8).setCellRenderer(tcr);
		table.getColumnModel().getColumn(9).setCellRenderer(tcr);
		table.getColumnModel().getColumn(10).setCellRenderer(tcr);
		
		
//		tamaño de cada columna
		table.getColumnModel().getColumn(0).setMinWidth(10);
		table.getColumnModel().getColumn(1).setMinWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(20);
		table.getColumnModel().getColumn(3).setMinWidth(60);
		table.getColumnModel().getColumn(4).setMinWidth(60);
		table.getColumnModel().getColumn(5).setMinWidth(60);
		table.getColumnModel().getColumn(6).setMinWidth(10);
		table.getColumnModel().getColumn(7).setMinWidth(70);
		table.getColumnModel().getColumn(8).setMinWidth(20);
		table.getColumnModel().getColumn(8).setMinWidth(60);
		table.getColumnModel().getColumn(9).setMinWidth(60);
		table.getColumnModel().getColumn(10).setMinWidth(60);
		
		//para que el JScrollPane pueda hacer scroll horizontal
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table.setEnabled(false); //para que no sea posible editar
		//table.setCellSelectionEnabled(true);
		table.setRowSelectionAllowed(true);
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				SelecRowTabla();
			}
		});
		
		
	}
}