import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Imagenes extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Imagenes().setVisible(true);
	}
	JButton btn1,btn2,btn3,btn4,btnmos;
	JLabel lbl1,lbl2,lbl3,lbl4;
	JComboBox cmbmod;
	String unit[]; 
	String id_img[];
	
	
	
	/**
	 * Create the frame.
	 */
	public Imagenes() {
		setBounds(100, 100, 700, 450);
		getContentPane().setLayout(null);
		
		cmbmod = new JComboBox();
		cmbmod.setBounds(171, 14, 160, 22);
		//cmbmod.setModel(new DefaultComboBoxModel(unit));
		LlenarCombo();
		getContentPane().add(cmbmod);
		
		lbl1 = new JLabel("?");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBackground(Color.RED);
		lbl1.setBounds(160, 65, 125, 109);
		getContentPane().add(lbl1);
		
		lbl2 = new JLabel("?");
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setBackground(Color.RED);
		lbl2.setBounds(410, 65, 125, 109);
		getContentPane().add(lbl2);
		//label.setBorder(BorderFactory.createLoweredBevelBorder());
		
		lbl3 = new JLabel("?");
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3.setBackground(Color.RED);
		lbl3.setBounds(160, 270, 125, 109);
		getContentPane().add(lbl3);
		
		lbl4 = new JLabel("?");
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4.setBackground(Color.RED);
		lbl4.setBounds(410, 270, 125, 109);
		getContentPane().add(lbl4);
		
		btn1 = new JButton("Subir Imag");
		btn1.setBounds(160, 187, 97, 25);
		getContentPane().add(btn1);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Conexion c=new Conexion();
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG, PNG & GIF Images", "jpg", "png", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String cad= chooser.getSelectedFile().getName().substring(chooser.getSelectedFile().getName().length()-4);
			    	String origen = chooser.getSelectedFile().getPath();
			    	System.out.println("You chose to open this file: " +origen+"\n"+cad);
			        String carp = "C:/ProCzo/img";
			        if(!new File(carp).exists())
			        {
			        	new File(carp).mkdir();
			        }
			        String id_modelo=Consultas.RetornaValorbyTabla("modelos", "id", "nombre", "'"+cmbmod.getSelectedItem().toString()+"'", c);
			        String destino=carp+"/"+id_modelo+"img1"+cad;
			        if(new File(destino).exists())
			        {
			        	new File(destino).delete();
			        }
			        
			        System.out.println("Proceso de copiar archivo: " + CopiarArchivo.getInstance().copiar(origen, destino));
			      
			        lbl1.setIcon(new ImageIcon(origen));
			
					String id=Consultas.RetornaId(c, "img");

					System.out.println("Valor del id de la imagen "+id);
					if(Consultas.InsertaImagenes(Integer.parseInt(id), destino, c)){
						if(DetalleImg(id))	
							JOptionPane.showMessageDialog(null, "Imagen agregada con exito ");
						else
							JOptionPane.showMessageDialog(null, "Error al insertar inesperado");
					}else
						JOptionPane.showMessageDialog(null, "Error al insertar imagen "+Integer.parseInt(id)+" "+ destino);
					
					c.closeConexion();
					
			    }
				
			}
		});
		
		btn2 = new JButton("Subir Imag");
		btn2.setBounds(410, 187, 97, 25);
		getContentPane().add(btn2);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Conexion c=new Conexion();
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG, PNG & GIF Images", "jpg", "png", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String cad= chooser.getSelectedFile().getName().substring(chooser.getSelectedFile().getName().length()-4);
			    	String origen = chooser.getSelectedFile().getPath();
			    	System.out.println("You chose to open this file: " +origen+"\n"+cad);
			        String carp = "C:/ProCzo/img";
			        if(!new File(carp).exists())
			        {
			        	new File(carp).mkdir();
			        }
			        String id_modelo=Consultas.RetornaValorbyTabla("modelos", "id", "nombre", "'"+cmbmod.getSelectedItem().toString()+"'", c);
			        String destino=carp+"/"+id_modelo+"img2"+cad;
			        if(new File(destino).exists())
			        {
			        	new File(destino).delete();
			        }
			        
			        System.out.println("Proceso de copiar archivo: " + CopiarArchivo.getInstance().copiar(origen, destino));
			      
			        lbl2.setIcon(new ImageIcon(origen));
			
					String id=Consultas.RetornaId(c, "img");

					System.out.println("Valor del id de la imagen "+id);
					if(Consultas.InsertaImagenes(Integer.parseInt(id), destino, c)){
						if(DetalleImg(id))	
							JOptionPane.showMessageDialog(null, "Imagen agregada con exito ");
						else
							JOptionPane.showMessageDialog(null, "Error al insertar inesperado");
					}else
						JOptionPane.showMessageDialog(null, "Error al insertar imagen "+Integer.parseInt(id)+" "+ destino);
					
					c.closeConexion();
					
			    }
				
			}
		});
		
		btn3 = new JButton("Subir Imag");
		btn3.setBounds(160, 378, 97, 25);
		getContentPane().add(btn3);
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Conexion c=new Conexion();
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG, PNG & GIF Images", "jpg", "png", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String cad= chooser.getSelectedFile().getName().substring(chooser.getSelectedFile().getName().length()-4);
			    	String origen = chooser.getSelectedFile().getPath();
			    	System.out.println("You chose to open this file: " +origen+"\n"+cad);
			        String carp = "C:/ProCzo/img";
			        if(!new File(carp).exists())
			        {
			        	new File(carp).mkdir();
			        }
			        String id_modelo=Consultas.RetornaValorbyTabla("modelos", "id", "nombre", "'"+cmbmod.getSelectedItem().toString()+"'", c);
			        String destino=carp+"/"+id_modelo+"img3"+cad;
			        if(new File(destino).exists())
			        {
			        	new File(destino).delete();
			        }
			        
			        System.out.println("Proceso de copiar archivo: " + CopiarArchivo.getInstance().copiar(origen, destino));
			      
			        lbl3.setIcon(new ImageIcon(origen));
			
					String id=Consultas.RetornaId(c, "img");

					System.out.println("Valor del id de la imagen "+id);
					if(Consultas.InsertaImagenes(Integer.parseInt(id), destino, c)){
						if(DetalleImg(id))	
							JOptionPane.showMessageDialog(null, "Imagen agregada con exito ");
						else
							JOptionPane.showMessageDialog(null, "Error al insertar inesperado");
					}else
						JOptionPane.showMessageDialog(null, "Error al insertar imagen "+Integer.parseInt(id)+" "+ destino);
					
					c.closeConexion();
					
			    }
				
			}
		});
		
		btn4 = new JButton("Subir Imag");
		btn4.setBounds(410, 378, 97, 25);
		getContentPane().add(btn4);
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Conexion c=new Conexion();
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG, PNG & GIF Images", "jpg", "png", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String cad= chooser.getSelectedFile().getName().substring(chooser.getSelectedFile().getName().length()-4);
			    	String origen = chooser.getSelectedFile().getPath();
			    	System.out.println("You chose to open this file: " +origen+"\n"+cad);
			        String carp = "C:/ProCzo/img";
			        if(!new File(carp).exists())
			        {
			        	new File(carp).mkdir();
			        }
			        String id_modelo=Consultas.RetornaValorbyTabla("modelos", "id", "nombre", "'"+cmbmod.getSelectedItem().toString()+"'", c);
			        String destino=carp+"/"+id_modelo+"img4"+cad;
			        if(new File(destino).exists())
			        {
			        	new File(destino).delete();
			        }
			        
			        System.out.println("Proceso de copiar archivo: " + CopiarArchivo.getInstance().copiar(origen, destino));
			      
			        lbl4.setIcon(new ImageIcon(origen));
			
					String id=Consultas.RetornaId(c, "img");

					System.out.println("Valor del id de la imagen "+id);
					if(Consultas.InsertaImagenes(Integer.parseInt(id), destino, c)){
						if(DetalleImg(id))	
							JOptionPane.showMessageDialog(null, "Imagen agregada con exito ");
						else
							JOptionPane.showMessageDialog(null, "Error al insertar inesperado");
					}else
						JOptionPane.showMessageDialog(null, "Error al insertar imagen "+Integer.parseInt(id)+" "+ destino);
					
					c.closeConexion();
					
			    }
				
			}
		});
		
		btnmos = new JButton("Mostrar");
		btnmos.setBounds(351, 13, 97, 25);
		getContentPane().add(btnmos);
		btnmos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Conexion c=new Conexion();
				
				
				String id_modelo=Consultas.RetornaValorbyTabla("modelos", "id", "nombre", "'"+cmbmod.getSelectedItem().toString()+"'", c);
				id_img=Consultas.RetornaArrayDetalleImg("detalle_img","id_img",Integer.parseInt(id_modelo),c);
				if(id_img.length!=0){
				String src[]=new String[id_img.length];
				
				System.out.println("for id "+id_img.length);
				
				for(int i=0;i<id_img.length;i++)
				{
					src[i]=Consultas.RetornaValorbyTabla("img","src","id",id_img[i],c);
					System.out.println(id_modelo+" idimg "+id_img[i]+" src "+src[i] );					
				}
				
				lbl2.setIcon(new ImageIcon(src[0]));
				lbl2.setIcon(new ImageIcon(src[1]));
				lbl3.setIcon(new ImageIcon(src[2]));
				lbl4.setIcon(new ImageIcon(src[3]));
				}else{
					lbl2.setIcon(new ImageIcon());
					lbl2.setIcon(new ImageIcon());
					lbl3.setIcon(new ImageIcon());
					lbl4.setIcon(new ImageIcon());
				}
//				if(Consultas.InsertaInterImagMod(Integer.parseInt(id), Integer.parseInt(id_modelo),Integer.parseInt(id_img), c)){
//					JOptionPane.showMessageDialog(null, "Relacion agregada con exito ");
//					c.closeConexion();
//					return true;
//				}
//				else{
//					JOptionPane.showMessageDialog(null, "Error al insertar la relacion inesperado");
//					c.closeConexion();
//					return false;
//				}
//				
//				
				
				
		}
		});

	}
	private void LlenarCombo()
	{ 
		Conexion c=new Conexion();
		unit=Consultas.RetornaArrayComboModelos("modelos", "nombre", c);
		c.closeConexion();
		cmbmod.setModel(new DefaultComboBoxModel(unit));
	}
	private boolean DetalleImg(String id_img)
	{
		Conexion c=new Conexion();
		String id=Consultas.RetornaId(c, "detalle_img");
		String id_modelo=Consultas.RetornaValorbyTabla("modelos", "id", "nombre", "'"+cmbmod.getSelectedItem().toString()+"'", c);
		
		if(Consultas.InsertaInterImagMod(Integer.parseInt(id), Integer.parseInt(id_modelo),Integer.parseInt(id_img), c)){
			JOptionPane.showMessageDialog(null, "Relacion agregada con exito ");
			c.closeConexion();
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "Error al insertar la relacion inesperado");
			c.closeConexion();
			return false;
		}
		
	}

}
