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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CapturaTallas extends JInternalFrame {
	private JTextField txtid;
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");
	JLabel lbl1;
	JSpinner spinner;
	JCheckBox chckbxMedio;
	String cadv = "";
	String ch="";

	public CapturaTallas() {
		setClosable(true);
		setTitle("Capturar Tallas");
		setBounds(100, 100, 310, 170);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(26, 14, 79, 14);
		getContentPane().add(lblId);
		
		JLabel lblTalla = new JLabel("Talla");
		lblTalla.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTalla.setBounds(10, 51, 79, 14);
		getContentPane().add(lblTalla);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(17, 10, 40, 1));
		spinner.setBounds(99, 48, 65, 20);
		getContentPane().add(spinner);
		spinner.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				cadv = spinner.getValue().toString()+ch;
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
		
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		txtid.setBounds(115, 11, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		chckbxMedio = new JCheckBox("medio 1/2");
		chckbxMedio.setBounds(170, 47, 97, 23);
		getContentPane().add(chckbxMedio);
		chckbxMedio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxMedio.isSelected())
					ch=" 1/2";
				else
					ch="";
				cadv=spinner.getValue().toString()+ch;
				Conexion c=new Conexion();
				if(Consultas.CompruebaDup("talla","tallas",cadv, c))
				{
					lbl1.setIcon(new ImageIcon(mal));
				}else
					lbl1.setIcon(new ImageIcon(bien));
				c.closeConexion();
			}
		});
		
		JButton btnAgregarTalla = new JButton("Agregar talla");
		btnAgregarTalla.setMnemonic('A');
		btnAgregarTalla.setBounds(85, 106, 123, 23);
		getContentPane().add(btnAgregarTalla);
		btnAgregarTalla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar();
				
			}
		});
		
		JLabel lblValidarInformacionNo = new JLabel("Validar informacion no repetida:");
		lblValidarInformacionNo.setBounds(10, 81, 274, 14);
		getContentPane().add(lblValidarInformacionNo);
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaTallas.class.getResource("/img/X.png")));
		lbl1.setBounds(220, 81, 46, 14);
		getContentPane().add(lbl1);
		
		Conexion co=new Conexion();
		txtid.setText(Consultas.RetornaId(co, "tallas"));
		co.closeConexion();
		
	}
	private void Agregar() {
		Icon im=new ImageIcon(bien);
		if(lbl1.getIcon().toString().equals(im.toString()))
		{
			Conexion c=new Conexion();
			
			if(Consultas.InsertaTallas(Integer.parseInt(txtid.getText()), cadv, c))
				{
					JOptionPane.showMessageDialog(null, "Talla agregada con exito ");
					txtid.setText(Consultas.RetornaId(c, "tallas"));
					lbl1.setIcon(new ImageIcon(mal));
					spinner.requestFocus();
				}
			else
				JOptionPane.showMessageDialog(null, "Error al insertar inesperado");
			
			c.closeConexion();
		}else{
			JOptionPane.showMessageDialog(null, "oh no la talla ya se encuentra en la base de datos ");
		}
		
	}
}
