
package conexion;

/**
 *
 * @author Anamaria Torres
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TareasBaseDatos extends ConexionBaseDatos {
     // atributos de la clase
    private Statement sentencia;
    private ResultSet resultado;
    private PreparedStatement sentenciaps;
    
    
     //método para consultar todos los usuarios
    public void consultarUsuario(){
        try{
            // Crear un objeto Statement para enviar instrucciones SQL
            sentencia=conexion.createStatement();
            // Construcción de sentencia SQL
            String query="SELECT * FROM clientes";
            // El método executeQuery ejecuta una sentencia SELECT y devuelve un objeto ResultSet
            resultado = sentencia.executeQuery(query);
            // Recorrer el objeto ResultSet obtenido
            while(resultado.next()){
                int ID_Usuario = resultado.getInt("ID_Usuario");
                String Login = resultado.getString("Login");
                String Contraseña = resultado.getString("Contraseña");
                System.out.println("Usuario: "+ ID_Usuario + "\nLogin: " + Login + "\nContraseña: "+ Contraseña+"\n");
            }
            // Cerrar las conexiones abiertas
            resultado.close();
            sentencia.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
       //método para consultar por id del Usuario
    public void consultarUsuarioId(int id){
        try{
            // Construcción de sentencia SQL con parámetros
            String query="SELECT * FROM clientes WHERE ID_Usuario=?";
            // Preparación de la consulta para psar los parámetros
            sentenciaps = conexion.prepareStatement(query);
            sentenciaps.setInt(1, id);
            // El método executeQuery ejecuta una sentencia SELECT y devuelve un objeto ResultSet
            resultado = sentenciaps.executeQuery();
            // Recorrer el objeto ResultSet obtenido
            while(resultado.next()){
                int ID_Usuario = resultado.getInt("ID_Usuario");
                String Login = resultado.getString("Login");
                String Contraseña = resultado.getString("Contraseña");
                System.out.println("Usuario: "+ ID_Usuario + "\nLogin: " + Login + "\nContraseña: "+ Contraseña+"\n");
            }
            // Cerrar las conexiones abiertas
            resultado.close();
            sentenciaps.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    //método para insertar un usuario
    public void insertarUsuario(int ID, String Log, int param1, int param2){
        try{
            // Sentencia SQL con parámetros
            String query="INSERT INTO clientes VALUES (?,?,?,?)";
            // Preparación de la consulta para psar los parámetros
            sentenciaps = conexion.prepareStatement(query);
            sentenciaps.setInt(1, ID);
            sentenciaps.setString(2, Log);
            sentenciaps.setInt(3, param1);
            sentenciaps.setInt(4, param2);

            // El método executeUpdate, ejecuta sentencias INSERT, UPDATE, o DELETE, las cuales no retornan datos
            sentenciaps.executeUpdate();
            // Cerrar la conexión abierta
            sentenciaps.close();
            System.out.println("El usuario ha sido registrado satisfactoriamente \n");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
     //método para actualizar un usuario
    public void actualizarUsuario(int ID, String Log, String Con){
        try{
            // Construcción de sentencia SQL con parámetros
            String query="UPDATE clientes SET Login = ?, Contraseña = ? WHERE ID_Usuario=?";
            // Preparación de la consulta para pasar los parámetros
            sentenciaps = conexion.prepareStatement(query);
            sentenciaps.setString(1, Log);
            sentenciaps.setString(2, Con);
            sentenciaps.setInt(3, ID);
            // El método executeUpdate, ejecuta sentencias INSERT, UPDATE, o DELETE, las cuales no retornan datos
            sentenciaps.executeUpdate();
            System.out.println("El usuario ha sido actualizado satisfactoriamente \n");
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
     //método para eliminar un usuario
    public void eliminarUsuario(int ID){
        try{
            // Construcción de sentencia SQL con parámetros
            String query="DELETE FROM clientes WHERE ID_Usuario=?";
            // Preparación de la consulta para psar los parámetros
            sentenciaps = conexion.prepareStatement(query);
            sentenciaps.setInt(1, ID);
            // El método executeUpdate, ejecuta sentencias INSERT, UPDATE, o DELETE, las cuales no retornan datos
            sentenciaps.executeUpdate();
            System.out.println("El usuario ha sido eliminado satisfactoriamente \n");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}


