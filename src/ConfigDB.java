import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ConfigDB extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtuse;
	private JPasswordField pass;
	private JTextField txthos;
	private JTextField txtpue;
	private JTextField txtnom;

	

	/**
	 * Create the dialog.
	 */
	public ConfigDB(JFrame frame) {
		super(frame,true);
		setTitle("Configuracion de conexion de la DB");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(79, 30, 107, 14);
		contentPanel.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(79, 65, 107, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHost.setBounds(79, 102, 107, 14);
		contentPanel.add(lblHost);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuerto.setBounds(79, 139, 107, 14);
		contentPanel.add(lblPuerto);
		
		JLabel lblNombreDeDb = new JLabel("Nombre de DB");
		lblNombreDeDb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDeDb.setBounds(50, 177, 136, 14);
		contentPanel.add(lblNombreDeDb);
		
		txtuse = new JTextField();
		txtuse.setBounds(203, 27, 86, 20);
		contentPanel.add(txtuse);
		txtuse.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(203, 62, 86, 20);
		contentPanel.add(pass);
		
		txthos = new JTextField();
		txthos.setBounds(202, 99, 86, 20);
		contentPanel.add(txthos);
		txthos.setColumns(10);
		
		txtpue = new JTextField();
		txtpue.setBounds(202, 136, 86, 20);
		contentPanel.add(txtpue);
		txtpue.setColumns(10);
		
		txtnom = new JTextField();
		txtnom.setText("");
		txtnom.setBounds(202, 174, 86, 20);
		contentPanel.add(txtnom);
		txtnom.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout());
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try{
			        		File archivoCong=new File("C:/ProCzo/config.dll");
			        		FileWriter write=new FileWriter(archivoCong);
			        		String con=new String(pass.getPassword());
			        		String cadConf=txtuse.getText()+"\n"+con+"\n"+txthos.getText()+
			        				"\n"+txtpue.getText()+"\n"+txtnom.getText();
			        		write.write(cadConf);
			        		write.close();
			        		
			        		}catch(Exception e){
			        			System.out.println("Error al crear directorio");
			        		}
						JOptionPane.showMessageDialog(null, "Informacion actualizada exitosamente");
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
			}
		}
		leerArchCong();
		
	}
	
	private void leerArchCong() {
		try
		{
		FileReader lector=new FileReader("C:/ProCzo/config.dll");
		BufferedReader contenido=new BufferedReader(lector);
		txtuse.setText(contenido.readLine());
		pass.setText(contenido.readLine());
		txthos.setText(contenido.readLine());
		txtpue.setText(contenido.readLine());
		txtnom.setText(contenido.readLine());
		
		}
		catch(Exception e)
		{
		System.out.println("Error al leer");
		}
		
	}
}
