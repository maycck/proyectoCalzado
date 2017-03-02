import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class CapturaTallas extends JInternalFrame {
	private JTextField txtid;


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
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(17, 10, 40, 1));
		spinner.setBounds(99, 48, 65, 20);
		getContentPane().add(spinner);
		
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		txtid.setBounds(115, 11, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		JCheckBox chckbxMedio = new JCheckBox("medio 1/2");
		chckbxMedio.setBounds(170, 47, 97, 23);
		getContentPane().add(chckbxMedio);
		
		JButton btnAgregarTalla = new JButton("Agregar talla");
		btnAgregarTalla.setMnemonic('A');
		btnAgregarTalla.setBounds(85, 106, 123, 23);
		getContentPane().add(btnAgregarTalla);
		
		JLabel lblValidarInformacionNo = new JLabel("Validar informacion no repetida:");
		lblValidarInformacionNo.setBounds(10, 81, 274, 14);
		getContentPane().add(lblValidarInformacionNo);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CapturaTallas.class.getResource("/img/X.png")));
		label.setBounds(220, 81, 46, 14);
		getContentPane().add(label);

	}
}
