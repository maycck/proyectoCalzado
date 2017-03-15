import java.awt.Image;

import javax.swing.ImageIcon;

public class ConfigGen {
	
	public static ImageIcon imagenScale(String ruta,int width,int height) {
		ImageIcon ic=new ImageIcon(ruta);
		Image im = ic.getImage(); //convertimos icon en una imagen
		Image oim = im.getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
		ic=new ImageIcon(oim);
		return ic;
	}
}
