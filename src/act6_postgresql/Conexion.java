/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package act6_postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;



/**
 *
 * @author danielachirivi
 */
public class Conexion {
  
    Connection conectar = null;
    
    String usuario = "postgres";
    String password = "ChiriviDA6";
    String bd = "CrudFuncionarios";
    String ip = "localhost";
    String puerto = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;

  
    
    public Connection establecerConexion(){
        
        try{
            Class.forName("org.postgresql.Driver");
        
            conectar = DriverManager.getConnection(cadena,usuario,password);

            JOptionPane.showMessageDialog(null, "conecto correctamente");
        
        }catch (Exception e){
            
            JOptionPane.showMessageDialog(null, "ERROR:"+ e.toString());
        }
        
        return conectar;
    }
 
}
