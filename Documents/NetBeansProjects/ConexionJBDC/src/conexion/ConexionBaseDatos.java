
package conexion;

/**
 *
 * @author Anamaria Torres
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBaseDatos {
    // atributos de la clase
    private String url="jdbc:mysql://localhost:3306/";
    private String bd="mercadonet";
    private String user="root";
    private String password="";
    private String driver ="com.mysql.cj.jdbc.Driver";
    protected Connection conexion;
    
    public ConexionBaseDatos(){
        try{
            // Llamada para cargar el driver para poder conectarse a una base de datos
            Class.forName(driver);
            // DriverManager es una clase que conecta una aplicación a una fuente de datos
            conexion = DriverManager.getConnection(url+bd,user,password);         
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
