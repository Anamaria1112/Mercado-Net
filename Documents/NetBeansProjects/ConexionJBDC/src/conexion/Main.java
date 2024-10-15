/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conexion;

/**
 *
 * @author anama
 */
public class Main {

    public static void main(String[] args) {
        TareasBaseDatos objcli = new TareasBaseDatos();
       objcli.insertarUsuario(0, "ana", 123456, 3051234);
    }
}
