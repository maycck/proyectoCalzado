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
	public static String RetornaColumbyID(String tabla, String column, int id,Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
		try{
            String consulta="select "+column+" from "+tabla+" where id=?";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            if(rs.absolute(1))
            {
            	return rs.getString(1);
            }
                   
        }catch(Exception e){ System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
		return null;
        
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
	public static String[][] RetornaArrayCT(String tabla, String colum,Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
        ArrayList<String> arr=new ArrayList<>();
        int row=0;
		try{
            String consulta="select * from "+tabla+" order by "+colum;
            pst = c.getConexion().prepareStatement(consulta);
            rs=pst.executeQuery();
            while(rs.next())
            {
            	row++;
            	arr.add(rs.getInt(1)+"");
            	arr.add(rs.getString(2));
            }
            String array[][]=new String[row][2];
            row=0;
            for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					array[i][j]=arr.get(row);row++;
				}
			}
            return array;
                   
        }catch(Exception e){ System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
		return null;
        
    }
	public static String[][] RetornaArraybyColumConsumos(String tabla, int colum,Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
        ArrayList<String> arr=new ArrayList<>();
        int row=0;
		try{
            String consulta="select * from "+tabla+" where id_modelo="+colum;
            pst = c.getConexion().prepareStatement(consulta);
            rs=pst.executeQuery();
            while(rs.next())
            {
            	row++;
            	arr.add(rs.getInt(1)+"");
            	arr.add(rs.getInt(2)+"");
            	arr.add(rs.getInt(3)+"");
            	arr.add(rs.getDouble(4)+"");
            	arr.add(rs.getDouble(5)+"");
            }
            String array[][]=new String[row][5];
            row=0;
            for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					array[i][j]=arr.get(row);row++;
				}
			}
            return array;
                   
        }catch(Exception e){ System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
		return null;
        
    }
	public static String[][] RetornaArrayClientes(Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
        ArrayList<String> arr=new ArrayList<>();
        int row=0;
		try{
            String consulta="select * from clientes";
            pst = c.getConexion().prepareStatement(consulta);
            rs=pst.executeQuery();
            while(rs.next())
            {
            	row++;
            	arr.add(rs.getInt(1)+"");
            	arr.add(rs.getString(2)+""); //importante checar el tipo que regresa si es Srting con getString si es int con getInt,etc
            	arr.add(rs.getString(3)+"");
            	arr.add(rs.getString(4)+"");
            	arr.add(rs.getString(5)+"");
            	arr.add(rs.getString(6)+"");
            	arr.add(rs.getString(7)+"");
            	arr.add(rs.getString(8)+"");
            }
            String array[][]=new String[row][8]; //8 numero de campos
            row=0;
            for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					array[i][j]=arr.get(row);row++;
				}
			}
            return array;
                   
        }catch(Exception e){ System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
		return null;
        
    }
	public static String RetornaValorbyTabla(String tabla,String column,String condicion1,String condicion2,Conexion c)
    {
        PreparedStatement pst= null;
        ResultSet rs=null;
        String res="";
		try{
            String consulta="select "+column+" from "+tabla+" where "+condicion1+"="+condicion2;
            pst = c.getConexion().prepareStatement(consulta);
            rs=pst.executeQuery();
            if(rs.next())
            {
            	res=rs.getString(1);
            }
            return res;
                   
        }catch(Exception e){ System.out.println("error");}
        finally{
            try{
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
            }catch(Exception e){}
        }
		return "";
        
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
	public static boolean InsertaInsumos(int id,String nombre, String unidad,int can, String linea, double precio,Conexion c)
    {
        PreparedStatement pst= null;
		try{
            String consulta="insert into insumos values (?,?,?,?,?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setString(2, nombre);
            pst.setString(3, unidad);
            pst.setInt(4, can);
            pst.setString(5, linea);
            pst.setDouble(6, precio);
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
	public static boolean InsertaSuelas(int id,String nombre,Conexion c)
    {
        PreparedStatement pst= null;
		try{
            String consulta="insert into suelas values (?,?)";
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
	public static boolean InsertaDetalleTallaColor(String tabla,int id, int idMS,int idCT,Conexion c)
    {
        PreparedStatement pst= null;
		try{
            String consulta="insert into "+tabla+" values (?,?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setInt(2, idMS);
            pst.setInt(3, idCT);
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
	public static boolean InsertaLinea(int id,String nombre,Conexion c)
    {
        PreparedStatement pst= null;
		try{
            String consulta="insert into linea values (?,?)";
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
	public static boolean InsertaModelos(int id,String nombre,int id_suela,Conexion c)
    {
        PreparedStatement pst= null;
		try{
            String consulta="insert into modelos values (?,?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setString(2, nombre);
            pst.setInt(3, id_suela);
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
	public static boolean InsertaClientes(int id,String nombre, String apellidop,String apellidom,String direccion,String tel,String email,String rfc,Conexion c)
	{
		PreparedStatement pst= null;
		try{
            String consulta="insert into Clientes values (?,?,?,?,?,?,?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setString(2, nombre);
            pst.setString(3, apellidop);
            pst.setString(4, apellidom);
            pst.setString(5, direccion);
            pst.setString(6, tel);
            pst.setString(7, email);
            pst.setString(8, rfc);
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
	public static boolean InsertaConsumos(int id,int id_modelo, int id_insumo, double tiempo, double costo,Conexion c)
    {
        PreparedStatement pst= null;
		try{
            String consulta="insert into consumos values (?,?,?,?,?)";
            pst = c.getConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setInt(2, id_modelo);
            pst.setInt(3, id_insumo);
            pst.setDouble(4, tiempo);
            pst.setDouble(5, costo);
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
	public static boolean ModificaGeneral(String tabla,String column, String value, String condicion1,String condicion2,Conexion c)
    {
        PreparedStatement pst= null;
		try{
        	
            String consulta="update "+tabla+" set "+column+"=? where "+condicion1+"="+condicion2;
            pst = c.getConexion().prepareStatement(consulta);
            pst.setString(1, value);
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
	//-------- ELIMINACIONES -------------
	public static boolean EliminaGeneral(String tabla, String condicion1,String condicion2,Conexion c)
    {
        PreparedStatement pst= null;
		try{
        	
            String consulta="delete from "+tabla+" where "+condicion1+"="+condicion2;
            pst = c.getConexion().prepareStatement(consulta);
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
