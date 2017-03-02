import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class CapturaSuelas extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnom;

	
	public CapturaSuelas() {
		setClosable(true);
		setTitle("Captura Suelas");
		setBounds(100, 100, 413, 300);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(64, 31, 83, 14);
		getContentPane().add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(64, 62, 83, 14);
		getContentPane().add(lblNombre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(105, 107, 234, 124);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JLabel lblTallas = new JLabel("Tallas");
		lblTallas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTallas.setBounds(12, 107, 83, 14);
		getContentPane().add(lblTallas);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		txtid.setBounds(157, 28, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		txtnom = new JTextField();
		txtnom.setBounds(157, 59, 86, 20);
		getContentPane().add(txtnom);
		txtnom.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(154, 236, 89, 23);
		getContentPane().add(btnAgregar);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon(CapturaSuelas.class.getResource("/img/X.png")));
		lbl1.setBounds(253, 62, 46, 14);
		getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon(CapturaSuelas.class.getResource("/img/X.png")));
		lbl2.setBounds(341, 155, 46, 14);
		getContentPane().add(lbl2);

	}
}
