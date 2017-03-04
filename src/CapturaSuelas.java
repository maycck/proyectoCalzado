import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CapturaSuelas extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;
	JLabel lbl1,lbl2;
	JPanel panel;
	JCheckBox chk[];
	String arr[][];
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	
	public CapturaSuelas() {
		setClosable(true);
		setTitle("Captura Suelas");
		setBounds(100, 100, 481, 360);
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
		scrollPane.setBounds(105, 107, 289, 162);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JLabel lblTallas = new JLabel("Tallas");
		lblTallas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTallas.setBounds(12, 107, 83, 14);
		getContentPane().add(lblTallas);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		txtid.setBounds(209, 28, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		txtnom = new JTextField();
		txtnom.setBounds(209, 59, 86, 20);
		getContentPane().add(txtnom);
		txtnom.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(203, 283, 89, 23);
		getContentPane().add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaSuelas.class.getResource("/img/X.png")));
		lbl1.setBounds(305, 62, 46, 14);
		getContentPane().add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(CapturaSuelas.class.getResource("/img/X.png")));
		lbl2.setBounds(404, 167, 46, 14);
		getContentPane().add(lbl2);
		
		addTorC();
		
		
	}
	private void addTorC() //metodo que agrega los valores de las tallas a un arreglo de strng para mostrar los checkboxes
	{
		Conexion co=new Conexion();
		txtid.setText(Consultas.RetornaId(co, "suelas"));
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
	private void eventosF()
	{
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
	private void agregar() {
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()) && lbl2.getIcon().toString().equals(im.toString()))
		{
			Conexion c=new Conexion();
			if(Consultas.InsertaSuelas(Integer.parseInt(txtid.getText()), txtnom.getText(), c))
				{
				for (int i = 0; i < arr.length; i++) {
					if(chk[i].isSelected())
					{
						if(!Consultas.InsertaDetalleTallaColor("detalle_talla", Integer.parseInt(txtid.getText()), Integer.parseInt(arr[i][0]), c))
						{
							JOptionPane.showMessageDialog(null, "Error al agregar talla "+arr[i][1]);
						}
						chk[i].setSelected(false);
					}
				}
					JOptionPane.showMessageDialog(null, "Suela agregada con exito ");
					txtid.setText(Consultas.RetornaId(c, "suelas"));
					txtnom.setText("");
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
		lbl2.setIcon(new ImageIcon(mal));
	}
}
