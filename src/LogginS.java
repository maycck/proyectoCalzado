import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LogginS extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	String nameuser;
	private int te;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogginS frame = new LogginS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LogginS() {
		setTitle("Sistema GPPCINCA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 200);
		if(!new File("C:/ProCzo").exists())
    	{
    		try{
        		new File("C:/ProCzo").mkdir();
        		File archivoCong=new File("C:/ProCzo/config.dll");
        		FileWriter write=new FileWriter(archivoCong);
        		write.write("root\n1234\nlocalhost\n3306\nPCalzado");
        		write.close();
        		
        		}catch(Exception e){
        			System.out.println("Error al crear directorio");
        		}
    		
    	}
		setLocationRelativeTo(null);
	//	setType(Type.UTILITY);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(132, 138, 120));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user = new JTextField();
		user.setBounds(169, 32, 162, 31);
		contentPane.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setToolTipText("Escribe el password de usuario");
		pass.setBounds(169, 74, 162, 31);
		contentPane.add(pass);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(85, 40, 74, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(69, 82, 99, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		JButton btnConfig=new JButton("",imagenScale("img/conf.png"));
		btnConfig.setRolloverEnabled(true);
		btnConfig.setRolloverIcon(imagenScale("img/conf2.png"));
		btnConfig.setBorder(null);
		btnConfig.setMnemonic('c');
		btnConfig.setContentAreaFilled(false);
		btnConfig.setSelected(false);
		btnConfig.setToolTipText("Configura los datos de conexion");
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confg();
			}
		});
		btnConfig.setBounds(10, 10, 24, 24);
		contentPane.add(btnConfig);
		btnConfig.setFocusable(false);
		
		
		JButton btnAceptar = 	new JButton("Aceptar");
		btnAceptar.setMnemonic('A');
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(0, 0, 0));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aceptar();
			}
		});
		btnAceptar.setBounds(242, 127, 89, 23);
		contentPane.add(btnAceptar);
		
		addkl(user);addkl(pass);
		
	}
	
	private ImageIcon imagenScale(String string) {
		ImageIcon ic=new ImageIcon(getClass().getResource(string));
		Image im = ic.getImage(); //convertimos icon en una imagen
		Image oim = im.getScaledInstance(24,24,java.awt.Image.SCALE_SMOOTH);
		ic=new ImageIcon(oim);
		return ic;
	}


	private void confg() {
		ConfigDB dialog = new ConfigDB(this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
	}


	//
	private void aceptar()
	{
		te=0;
		char[] input = pass.getPassword();
		if(isPasswordCorrect(user.getText(),input))
		{
			dispose();		
			Principal p=new Principal(nameuser);
			p.setVisible(true);
			
		//	System.out.println("ingreso correcto");
		//	p.setVisible(true);
				

		}else{
			JOptionPane.showMessageDialog(null, "Error en usuario o password incorrectos\nSi los datos son correcto revisa la configuracion de la conexion");
		}
		pass.setText("");
		if(te==1)
			user.requestFocus();
		else
			pass.requestFocus();

	}
	//añade keylistener
	public void addkl(JComponent j)
	{
		j.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					aceptar();
				}
			}
		});
	}
	//Password
	private boolean isPasswordCorrect(String usuario, char[] input) {
	    boolean isCorrect = true;
	    Conexion con=new Conexion();
	    char [] correctPassword = Consultas.RetornaPass(usuario,con);
	    nameuser=Consultas.RetornaUser(usuario, con);
        if(con!=null)con.closeConexion();
      System.out.println("name user: "+nameuser+" pass: ");
	   if (correctPassword==null) {
	       isCorrect = false;
	      te=1;
	   } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	   }

	    return isCorrect;
	}

	 
}
