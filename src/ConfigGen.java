import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;

public class ConfigGen {
	
	public static ImageIcon imagenScale(String ruta,int width,int height) {
		ImageIcon ic=new ImageIcon(ruta);
		Image im = ic.getImage(); //convertimos icon en una imagen
		Image oim = im.getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
		ic=new ImageIcon(oim);
		return ic;
	}
	public static int WeekOfYear(String fecha)
	{
		//------------- Sacar Semana del mes
   	 SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fec = null;
        try {
            fec = formato.parse(fecha);
        } catch (ParseException ex) {System.out.println("error al convertir fecha"); return 0; }
     
   	Calendar cal=Calendar.getInstance();
   	cal.setTime(fec);
   	return cal.get(cal.WEEK_OF_YEAR)-1;
   	//-------------------------------------------
	}
}
