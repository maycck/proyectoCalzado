import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
	 private String username="";
	private String pass="";
	  private String host="";
	  private String port="";
	  private String database="";
	  private String classname="com.mysql.jdbc.Driver";
	  private String url="";
	  private Connection con;
	  
	  public Conexion()
	  {
		  leerArchCong();
		 // System.out.println(url);
	      try{
	        
				Class.forName(classname);
	          con = DriverManager.getConnection(url, username, pass);
	      } catch (ClassNotFoundException ex) {
	          Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
	      } catch(SQLException e){
	          
	      }
	  }
	  public Connection getConexion()
	  {
	      return con;
	  }
	  public void closeConexion()
	  {
		  try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  }
	  private void leerArchCong() {
			try
			{
			FileReader lector=new FileReader("C:/ProCzo/config.dll");
			BufferedReader contenido=new BufferedReader(lector);
			setUsername(contenido.readLine());
			setPass(contenido.readLine());
			setHost(contenido.readLine());
			setPort(contenido.readLine());
			setDatabase(contenido.readLine());
			url="jdbc:mysql://"+host+":"+port+"/"+database+"?requireSSL=false&useUnicode=true&characterEncoding=utf8";
			}
			catch(Exception e)
			{
			System.out.println("Error al leer");
			}
			
		}
	    
	  public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
		public String getDatabase() {
			return database;
		}
		public void setDatabase(String database) {
			this.database = database;
		}
	    
	   
}
