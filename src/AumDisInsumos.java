import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class AumDisInsumos extends JFrame//JInternalFrame 
{
	private JTextField txtcan;
	JTable table;
	JButton butmen,butmas;
	JComboBox cmbins;
	JLabel lbl1;
	JScrollPane scrollPane;
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	String [][] coin;
	Boolean noins=false;
	String comsel,ncan;
	int can_ins,fila,colu;
	DefaultTableModel modelo; //modelo de la tabla

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AumDisInsumos a=new AumDisInsumos();
		a.setVisible(true);
		a.setDefaultCloseOperation(EXIT_ON_CLOSE);
		a.setTitle("Aumenta o Disminuye Insumos");
	}

	/**
	 * Create the frame.
	 */
	public AumDisInsumos() {
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(144, 16, 56, 16);
		getContentPane().add(lblNewLabel);
		
		cmbins = new JComboBox();
		cmbins.setBounds(212, 13, 130, 22);
		getContentPane().add(cmbins);
		cmbins.addItem("Elige un Insumo");
		
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(12, 115, 56, 16);
		getContentPane().add(lblCantidad);
		
		txtcan = new JTextField();
		txtcan.setEditable(false);
		txtcan.setEnabled(false);
		txtcan.setBounds(80, 112, 116, 22);
		getContentPane().add(txtcan);
		txtcan.setColumns(10);
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(AumDisInsumos.class.getResource("/img/X.png")));
		lbl1.setBounds(208, 117, 46, 14);
		getContentPane().add(lbl1);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(12, 208, 460, 145);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Aumentar o Disminuir Cantidad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)), "Aumentar o Disminuir Cantidad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.setBounds(259, 63, 213, 119);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		butmen = new JButton("-");
		butmen.setEnabled(false);
		butmen.setBounds(12, 75, 53, 31);
		panel.add(butmen);
		butmen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		butmas = new JButton("+");
		butmas.setEnabled(false);
		butmas.setBounds(12, 31, 53, 31);
		panel.add(butmas);
		butmas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		CombLle();
		
		cmbins.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InfoInsumoSel();
				
			}
		});
		datostabla();
		eventosF();
		
		butmas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BotonMas();
			}
		});
		butmen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BotonMen();
			}
		});

	}
	private void BotonMas() {
		ncan = (String)JOptionPane.showInputDialog("Escribe la cantidad que deseas Aumentar");
		
		if(ncan!=null)
		{
			int ca=-1;
			try{
				ca=Integer.parseInt(ncan);
			}catch(NumberFormatException exet){			}
			if(ca<0)
			{
				JOptionPane.showMessageDialog(null, "Asegurate de haber escrito correctamente la cantidad", "ERROR", JOptionPane.ERROR_MESSAGE);
				BotonMas();
			}else{
				JOptionPane.showMessageDialog(null,"Se ha completado el Aumento en la cantidad del insumo", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
				can_ins=Integer.parseInt(coin[fila][3]);
				coin[fila][3]=(can_ins+ca)+"";
				txtcan.setText(coin[fila][3]);
				
				Conexion c=new Conexion();
				if(!Consultas.ModificaGeneral("insumos", "cantidad", coin[fila][3], "id", coin[fila][0], c))
					JOptionPane.showMessageDialog(null, "Error al actualizar la cantidad");
				c.closeConexion();
				datostabla();
			}
		}else{
			JOptionPane.showMessageDialog(null, "No se ha recibido ningun dato");
		}
	

	}
	private void BotonMen() {
		ncan = (String)JOptionPane.showInputDialog("Escribe la cantidad que deseas Disminuir");
		
		if(ncan!=null)
		{
			int ca=-1;
			try{
				ca=Integer.parseInt(ncan);
			}catch(NumberFormatException exet){			}
			if(ca>-1)
			{
				if((can_ins-ca)<0){
					JOptionPane.showMessageDialog(null, "Existencias insuficientes para: "+cmbins.getSelectedItem().toString()+"\n"+"Solo se han registrado "+can_ins, "ERROR", JOptionPane.ERROR_MESSAGE);
					BotonMen();
				}else{
					JOptionPane.showMessageDialog(null,"Se ha completado la Disminucion en la cantidad del insumo", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
					can_ins=Integer.parseInt(coin[fila][3]);
					coin[fila][3]=(can_ins-ca)+"";
					txtcan.setText(coin[fila][3]);
					
					Conexion c=new Conexion();
					if(!Consultas.ModificaGeneral("insumos", "cantidad", coin[fila][3], "id", coin[fila][0], c))
						JOptionPane.showMessageDialog(null, "Error al actualizar la cantidad");
					c.closeConexion();
					datostabla();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Asegurate de haber escrito correctamente la cantidad", "ERROR", JOptionPane.ERROR_MESSAGE);
				BotonMen();
			}
		}else{
			JOptionPane.showMessageDialog(null, "No se ha recibido ningun dato");
		}
	

	}
	private void eventosF()
	{
		cmbins.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!cmbins.getSelectedItem().toString().equals("Elige un Insumo")){
					try{
						can_ins=Integer.parseInt(coin[fila][3]);//cantidad de insumos de  la bd
						lbl1.setIcon(new ImageIcon(bien));
					}catch(NumberFormatException exet){
						lbl1.setIcon(new ImageIcon(mal));
					}
				}else
					lbl1.setIcon(new ImageIcon(mal));
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				lbl1.setIcon(null);
			}
		});
	}
	private void CombLle()
	{
		Conexion c=new Conexion();
		coin=Consultas.RetornaArrayInsumos(c);
		if(coin!=null){
			String nin[]=new String[coin[0].length];//nombres de los insumos
			for (int j =0; j < coin.length; j++) {
					nin[j]=coin[j][1];
					cmbins.addItem(nin[j]);
				}
			noins=true;
		}
		
		c.closeConexion();
	}
	private void Hab(boolean b)
	{
		txtcan.setEnabled(b);
		butmas.setEnabled(b);
		butmen.setEnabled(b);
	}
	private void InfoInsumoSel()
	{
		if(noins){
			Conexion c=new Conexion();
			Hab(true);
			comsel=cmbins.getSelectedItem().toString();//Nombre del Insumo Seleccionado
			if(comsel!="Elige un Insumo"){
				for (int j =0; j < coin.length; j++) {
					if(comsel.equals(coin[j][1]))
					{
						txtcan.setText(coin[j][3]);
						fila=j;
					}
			
				}
				c.closeConexion();
		}else
		{
			Hab(false);
			txtcan.setText("");
			lbl1.setIcon(new ImageIcon(mal));
		}
		}
	}
	@SuppressWarnings("serial")
	private void datostabla() {
		modelo=new DefaultTableModel(new Object[][]{},new String[] {"ID", "Nombre", "Unidad", "Cantidad", "Linea","Precio"}){};
				
		Conexion c=new Conexion();
		for (int i = 0; i < coin.length; i++) {
			modelo.addRow(new Object[]{coin[i][0],coin[i][1],coin[i][2],coin[i][3],coin[i][4],coin[i][5]});
		}
		c.closeConexion();
		table.setModel(modelo);
		
		//para centrar las columnas que queramos
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
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
		table.setRowSelectionAllowed(true);
		
		
	}
}
