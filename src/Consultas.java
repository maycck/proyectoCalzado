import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class Consultas {
	
	/* ---------- METODOS ESTATICOS DE LA BASE DE DATOS -------------*/
	
	//-------- CONSULTAS --------------
	
	
	public static char[] RetornaPass(String nick, Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
		try{
        	
            String consulta="select * from usuario where nick=?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, nick);	            
            rs=pst.executeQuery();
            
            if(rs.absolute(1))
            {
            	//char []pas=rs.getString(2).toCharArray();
                return rs.getString(4).toCharArray();
            }
            
        }catch(Exception e){}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
        return null;
}
	public static String RetornaUser(String nick, Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
		try{
        	
            String consulta="select nombre from usuario where nick=?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, nick);	            
            rs=pst.executeQuery();
            
            if(rs.absolute(1))
            {
            	//char []pas=rs.getString(2).toCharArray();
                return rs.getString(1);
            }
            
        }catch(Exception e){}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
        return null;
}
	public static String RetornaId(Conexion c,String tabla)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
        int id=1;
		try{
            String consulta="select max(id) from "+tabla;
            pst = c.getConexion().prepareStatement(consulta);
            rs=pst.executeQuery();
            if(rs.absolute(1))
            {
            	return (rs.getInt(1)+1)+"";
            }
                   
        }catch(Exception e){ System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
		return "1";
        
    }
	public static boolean CompruebaDup(String campo,String tabla,String value,Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
		try{
        	
            String consulta="select "+campo+" from "+tabla+" where "+campo+"=?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, value);
            rs=pst.executeQuery();
            
            if(rs.absolute(1))
            {
            	return true;
            }
            
        }catch(Exception e){System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
        return false;
    }

	//-------- INSERCIONES -------------
	public static boolean InsertaColores(int id,String nombre,Conexion c)
    {
        PreparedStatement pst= null;
		try{
            String consulta="insert into colores values (?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setString(2, nombre);
//            pst.executeUpdate();
            if(pst.executeUpdate()==1)
            {
                return true;
            }     
        }catch(Exception e){}
        finally{
            try{
            if(pst!=null) pst.close();
            }catch(Exception e){}
        }
        return false;
    }
	public static boolean InsertaTallas(int id,String talla,Conexion c)
    {
        PreparedStatement pst= null;
		try{
            String consulta="insert into tallas values (?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setString(2, talla);
//            pst.executeUpdate();
            if(pst.executeUpdate()==1)
            {
                return true;
            }     
        }catch(Exception e){}
        finally{
            try{
            if(pst!=null) pst.close();
            }catch(Exception e){}
        }
        return false;
    }
	
	//-------- MODIFICACIONES ------------
	
	//-------- ELIMINACIONES -------------
	
	
	
	
	
	
	
	//-----------FIN DE METODOS---------------------------
	/*
	
	
	public static boolean InsertaDetalleSinIDP(int id, int idv, int can, String nom, double precio,double total,int tipo,Conexion c)
    {
        PreparedStatement pst= null;
		try{
            String consulta="insert into detalle (id_detalle,id_v,cantidad,nombre,precio_p,total,tipo) values(?,?,?,?,?,?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setInt(2, idv);
            pst.setInt(3, can);
            pst.setString(4, nom);
            pst.setDouble(5, precio);
            pst.setDouble(6, total);
            pst.setInt(7, tipo);
//            pst.executeUpdate();
            if(pst.executeUpdate()==1)
            {
                return true;
            }
            
        }catch(Exception e){}
        finally{
            try{
            if(pst!=null) pst.close();
            }catch(Exception e){}
        }
        return false;
    }
	public static boolean InsertaVenta(int idV, Date fecha, double total,Conexion c)
    {
        PreparedStatement pst= null;
		try{
        	
            String consulta="insert into ventas values (?,?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, idV);
            pst.setDate(2, (java.sql.Date) fecha);
            pst.setDouble(3, total);
//            pst.executeUpdate();
            if(pst.executeUpdate()==1)
            {
                return true;
            }
            
        }catch(Exception e){}
        finally{
            try{
            if(pst!=null) pst.close();
            }catch(Exception e){}
        }
        return false;
    }
	public static boolean ModificaProducto(int id,String nombre, double preciou, double preciop, String des, int tipo,Conexion c)
    {
        PreparedStatement pst= null;
		try{
        	
            String consulta="update productos set nombre=?, precio_u=?, precio_p=?, descripcion=?, tipo=? where id_producto=?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, nombre);
            pst.setDouble(2, preciou);
            pst.setDouble(3, preciop);
            pst.setString(4, des);
            pst.setInt(5, tipo);
            pst.setInt(6, id);
            pst.executeUpdate();
            
            if(pst.executeUpdate()==1)
            {
                return true;
            }
            
        }catch(Exception e){ System.out.println("Error en la consulta");}
        finally{
            try{
            if(pst!=null) pst.close();
            }catch(Exception e){}
        }
        return false;
    }
	public static boolean ModificaUsuario(int id,String nombre, String nick, String pass, int tipo,Conexion c)
    {
        PreparedStatement pst= null;
		try{
        	
            String consulta="update usuario set nombre=?, nick=?, pass=?, tipo=? where id_usuario=?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, nombre);
            pst.setString(2, nick);
            pst.setString(3, pass);
            pst.setInt(4, tipo);
            pst.setInt(5, id);
            pst.executeUpdate();
            
            if(pst.executeUpdate()==1)
            {
                return true;
            }
            
        }catch(Exception e){ System.out.println("Error en la consulta");}
        finally{
            try{
            if(pst!=null) pst.close();
            }catch(Exception e){}
        }
        return false;
    }
	public static String[][] RetornaProductos(Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
        ArrayList<String> arr=new ArrayList<>();
        int row;
        String [][]prod;
		try{
        	
            String consulta="select * from productos";
            pst = c.getConexion().prepareStatement(consulta);            
            rs=pst.executeQuery();
            row=0;
            while(rs.next())
            {
            	row++;
            	arr.add(rs.getInt(1)+"");
            	arr.add(rs.getString(2));
            	arr.add(rs.getDouble(3)+"");
            	arr.add(rs.getDouble(4)+"");
            	arr.add(rs.getString(5)+"");
            	arr.add(rs.getInt(6)+"");
            }
            prod=new String[row][6];
            row=0;
            for (int i = 0; i < prod.length; i++) {
				for (int j = 0; j < prod[0].length; j++) {
					prod[i][j]=arr.get(row);row++;
				}
			}
            return prod;
        }catch(Exception e){}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
        return null;
    }
	public static String[][] RetornaProductosBus(String artbus,Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
        ArrayList<String> arr=new ArrayList<>();
        int row;
        String [][]prod;
		try{
        	
            String consulta="select * from productos where nombre like ?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, artbus);
            rs=pst.executeQuery();
            row=0;
            while(rs.next())
            {
            	row++;
            	arr.add(rs.getInt(1)+"");
            	arr.add(rs.getString(2));
            	arr.add(rs.getDouble(3)+"");
            	arr.add(rs.getDouble(4)+"");
            	arr.add(rs.getString(5)+"");
            	arr.add(rs.getInt(6)+"");
            }
            prod=new String[row][6];
            row=0;
            for (int i = 0; i < prod.length; i++) {
				for (int j = 0; j < prod[0].length; j++) {
					prod[i][j]=arr.get(row);row++;
				}
			}
            return prod;
        }catch(Exception e){System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
        return null;
}
	public static boolean CompruebaNombrePro(String nombre,Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
		try{
        	
            String consulta="select nombre from productos where nombre=?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, nombre);
            rs=pst.executeQuery();
            
            if(rs.absolute(1))
            {
            	return true;
            }
            
        }catch(Exception e){System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
        return false;
    }
	//comprobar sobre usuario
	public static boolean CompruebaNombre(String nombre,Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
		try{
        	
            String consulta="select nombre from usuario where nombre=?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, nombre);
            rs=pst.executeQuery();
            
            if(rs.absolute(1))
            {
            	return true;
            }
            
        }catch(Exception e){System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
        return false;
    }
	public static boolean CompruebaNick(String nick,Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
		try{
        	
            String consulta="select nick from usuario where nick=?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, nick);
            rs=pst.executeQuery();
            
            if(rs.absolute(1))
            {
            	return true;
            }
            
        }catch(Exception e){System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
        return false;
    }
	public static boolean InsertaUsuario(int id,String nombre,String nick,String pass,int tipo,Conexion c)
    {
        PreparedStatement pst= null;
		try{
        	
            String consulta="insert into usuario values(?,?,?,?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setString(2, nombre);
            pst.setString(3, nick);
            pst.setString(4, pass);
            pst.setInt(5, tipo);
//            pst.executeUpdate();
            if(pst.executeUpdate()>0)
            {
            	return true;
            }
            
        }catch(Exception e){System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            }catch(Exception e){}
        }
        return false;
    }
	public static String[][] RetornaUsuarios(Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
        ArrayList<String> arr=new ArrayList<>();
        int row;
        String [][]prod;
		try{
        	
            String consulta="select * from usuario";
            pst = c.getConexion().prepareStatement(consulta);            
            rs=pst.executeQuery();
            row=0;
            while(rs.next())
            {
            	row++;
            	arr.add(rs.getInt(1)+"");
            	arr.add(rs.getString(2));
            	arr.add(rs.getString(3));
            	arr.add(rs.getString(4));
            	arr.add(rs.getInt(5)+"");
            }
            prod=new String[row][5];
            row=0;
            for (int i = 0; i < prod.length; i++) {
				for (int j = 0; j < prod[0].length; j++) {
					prod[i][j]=arr.get(row);row++;
				}
			}
            return prod;
        }catch(Exception e){}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
        return null;
}

	*/
}
