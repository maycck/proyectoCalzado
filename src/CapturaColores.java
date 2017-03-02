import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class CapturaColores extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;
	private JLabel lbl1;
	URL mal=getClass().getResource("img/X.png"),bien=getClass().getResource("img/a.gif");

	public CapturaColores() {
		setTitle("Captura Colores");
		setClosable(true);
		setBounds(100, 100, 400, 180);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(33, 14, 120, 14);
		getContentPane().add(lblId);
		
		txtid = new JTextField();
		txtid.setBounds(172, 11, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		txtid.setEditable(false);
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(83, 55, 79, 14);
		getContentPane().add(lblNombre);
		
		txtnom = new JTextField();
		txtnom.setBounds(172, 52, 86, 20);
		getContentPane().add(txtnom);
		txtnom.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setMnemonic('A');
		btnAgregar.setBounds(145, 111, 89, 23);
		getContentPane().add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		
		lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaColores.class.getResource("/img/X.png")));
		lbl1.setBounds(267, 55, 46, 14);
		getContentPane().add(lbl1);
		Conexion co=new Conexion();
		txtid.setText(Consultas.RetornaId(co, "colores"));
		co.closeConexion();
		eventosF();
	}
	private void eventosF() {
		txtnom.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				Conexion c=new Conexion();
				if(Consultas.CompruebaDup("nombre","colores",txtnom.getText().trim(), c))
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
		
	}
	private void agregar() {
		
		
	}
}
