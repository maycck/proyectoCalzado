import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class CapturaModelos extends JInternalFrame {

	private JTextField txtid;
	private JTextField txtnom;
	JPanel panel;

	/**
	 * Create the frame.
	 */
	public CapturaModelos() {
		setClosable(true);
		setTitle("Captura Modelos");
		setResizable(true);
		setBounds(100, 100, 435, 330);
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
		lblNombre.setBounds(92, 55, 70, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblUnidad = new JLabel("Suela");
		lblUnidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnidad.setBounds(92, 94, 70, 14);
		getContentPane().add(lblUnidad);
		
		txtnom = new JTextField();
		txtnom.setBounds(172, 58, 86, 20);
		getContentPane().add(txtnom);
		txtnom.setColumns(10);
		
		JComboBox cmbsue = new JComboBox();
		cmbsue.setBounds(172, 91, 86, 20);
		getContentPane().add(cmbsue);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaInsumos.class.getResource("/img/X.png")));
		lbl1.setBounds(268, 61, 46, 14);
		getContentPane().add(lbl1);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setMnemonic('A');
		btnAgregar.setBounds(172, 266, 89, 23);
		getContentPane().add(btnAgregar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(55, 132, 302, 123);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JLabel lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(CapturaModelos.class.getResource("/img/X.png")));
		lbl2.setBounds(367, 185, 46, 14);
		getContentPane().add(lbl2);
		
		
		llamadoprueba();

	}

	private void llamadoprueba() {
		int x=10,y=10;
		int col=0;
		for (int i = 0; i < 40; i++) {
			if(col==3)
			{
				col=0;
				x=10;
				y+=30;
			}
			col++;
			JCheckBox c=new JCheckBox("color "+(i+1));
			c.setBounds(x, y, 120, 20);
			panel.add(c);
			x+=140;
		}
		
		panel.setPreferredSize(new Dimension(x+300,y+100));
		
	}
}
