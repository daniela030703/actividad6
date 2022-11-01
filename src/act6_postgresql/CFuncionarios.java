/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package act6_postgresql;



import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextField;
import java.sql.CallableStatement;  
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;





/**
 *
 * @author danielachirivi
 */
public class CFuncionarios {

    int id_serial_funcionario;
    String tipo_documento;
    int  numero_documento;
    String nombreCompleto;
    String  est_civil;
    String sexo;
    String  direccion;
    String telefono;
    String fecha_nacimiento;


   
   
    
    public int getId_serial_funcionario() {
        return id_serial_funcionario;
    }

    public void setId_serial_funcionario(int id_serial_funcionario) {
        this.id_serial_funcionario = id_serial_funcionario;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public int getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(int numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEst_civil() {
        return est_civil;
    }

    public void setEst_civil(String est_civil) {
        this.est_civil = est_civil;
    }

   public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public Date getFecha_nacimiento() throws ParseException {
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       java.sql.Date fechaConvertida=null;

       try {
           java.util.Date parsed =  dateFormat.parse(this.fecha_nacimiento);
           fechaConvertida = new java.sql.Date(parsed.getTime());
       } catch(Exception e) {
           System.out.println("Error occurred"+ e.getMessage());
       }
       return fechaConvertida;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
   
  
    
    public void MostrarFuncionarios (JTable paramTableTotalFuncionarios){
        
        Conexion objetoConexion = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "";
     
        modelo.addColumn("Id_serial_funcionario");
        modelo.addColumn("tipo_documento");
        modelo.addColumn("numero_documento");
        modelo.addColumn("nombreCompleto");
        modelo.addColumn("est_civil");
        modelo.addColumn("sexo");
        modelo.addColumn("direccion");
        modelo.addColumn("telefono");
        modelo.addColumn("fecha_nacimiento");
        
        paramTableTotalFuncionarios.setModel(modelo);
        
        sql = "SELECT * FROM funcionario.funcionario;";
        
        String [] datos = new String[9];
        Statement st;
        
        try{
        
            st= objetoConexion.establecerConexion().createStatement();

           ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                datos[3]= rs.getString(4);
                datos[4]= rs.getString(5);
                datos[5]= rs.getString(6);
                datos[6]= rs.getString(7);
                datos[7]= rs.getString(8);
                datos[8]= rs.getString(9);

                modelo.addRow(datos);
                  
        }
        
        paramTableTotalFuncionarios.setModel(modelo);
    
        }catch(Exception e){
        
             JOptionPane.showMessageDialog(null, "ERROR:"+ e.toString());
             
        }
        
    }
    
    public boolean verficarCampos( JTextField paramTipo_documento, JTextField paramNumero_documento, JTextField paramNombreCompleto, JTextField paramEst_civil, JTextField paramSexo, JTextField paramDireccion, JTextField paramTelefono, JTextField paramFecha){
        
        boolean enblanco = false;
        
        if ( "".equals(paramTipo_documento.getText()) || "".equals(paramNumero_documento.getText()) || "".equals(paramNombreCompleto.getText()) || "".equals(paramEst_civil.getText()) || "".equals(paramSexo.getText()) || "".equals(paramDireccion.getText()) || "".equals(paramTelefono.getText()) || "".equals(paramFecha.getText())){
            enblanco = true;
            JOptionPane.showMessageDialog(null, "Campos vacios, Ingresa todos los datos");
        }
        return enblanco;
    }
    
    public void insertarFuncionarios( JTextField paramTipo_documento, JTextField paramNumero_documento, JTextField paramNombreCompleto, JTextField paramEst_civil, JTextField paramSexo, JTextField paramDireccion, JTextField paramTelefono, JTextField paramFecha){
        
        boolean vacios = verficarCampos(paramTipo_documento, paramNumero_documento, paramNombreCompleto, paramEst_civil, paramSexo, paramDireccion, paramTelefono, paramFecha  );
      
        if(vacios==true){
            return;
        }
        
        setTipo_documento(paramTipo_documento.getText()); 
        setNumero_documento(Integer.parseInt( paramNumero_documento.getText()));
        setNombreCompleto(paramNombreCompleto.getText());
        setEst_civil(paramEst_civil.getText());
        setSexo(paramSexo.getText());       
        setDireccion(paramDireccion.getText());
        setTelefono(paramTelefono.getText());
        setFecha_nacimiento(paramFecha.getText());
        
        Conexion objetoConexion = new Conexion();
        
          String consulta2 = "INSERT INTO funcionario.sexo(\"Nombre\") SELECT UPPER(?) WHERE NOT EXISTS(SELECT \"Nombre\" FROM funcionario.sexo WHERE \"Nombre\" = UPPER(?));";
        
        try {
            
        CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta2);
        cs.setString(1, getSexo());
        cs.setString(2, getSexo());
        
        cs.execute();
         
        JOptionPane.showMessageDialog(null, "Inserto Correctamente"); 
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR:"+ e.toString());
        }
        
        String consulta = "INSERT INTO funcionario.funcionario(tipo_documento, numero_documento, \"nombreCompleto\", est_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
        
        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getTipo_documento());
            cs.setInt(2, getNumero_documento());
            cs.setString(3, getNombreCompleto());
            cs.setString(4, getEst_civil());
            cs.setString(5,  getSexo().toUpperCase());
            cs.setString(6, getDireccion());
            cs.setString(7, getTelefono());
            cs.setDate(8,  getFecha_nacimiento());
            
            cs.execute();
            
            
            
            
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR:"+ e.toString());
        }
        
      
        
     
     
    }
    
    
    public void SeleccionarFuncionario(JTable paramTablaFuncionario,JTextField paramId_serial_funcionario, JTextField paramTipo_documento, JTextField paramNumero_documento, JTextField paramNombreCompleto, JTextField paramEst_civil, JTextField paramSexo, JTextField paramDireccion, JTextField paramTelefono, JTextField paramFecha){
        try {
            
            int fila =paramTablaFuncionario.getSelectedRow();
            
            if (fila>=0){
                
              paramId_serial_funcionario.setText(paramTablaFuncionario.getValueAt(fila, 0).toString());
              paramTipo_documento.setText(paramTablaFuncionario.getValueAt(fila, 1).toString());  
              paramNumero_documento.setText(paramTablaFuncionario.getValueAt(fila, 2).toString());
              paramNombreCompleto.setText(paramTablaFuncionario.getValueAt(fila, 3).toString());
              paramEst_civil.setText(paramTablaFuncionario.getValueAt(fila, 4).toString());
              paramSexo.setText(paramTablaFuncionario.getValueAt(fila, 5).toString());
              paramDireccion.setText(paramTablaFuncionario.getValueAt(fila, 6).toString());
              paramTelefono.setText(paramTablaFuncionario.getValueAt(fila, 7).toString());
              paramFecha.setText(paramTablaFuncionario.getValueAt(fila, 8).toString());
              
                  
            }else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR:"+ e.toString());
        }
       
        
    }
    
    public void actualizarFuncionarios(JTextField paramId_serial_funcionario, JTextField paramTipo_documento, JTextField paramNumero_documento, JTextField paramNombreCompleto, JTextField paramEst_civil, JTextField paramSexo, JTextField paramDireccion, JTextField paramTelefono, JTextField paramFecha){
        
          boolean vacios = verficarCampos(paramTipo_documento, paramNumero_documento, paramNombreCompleto, paramEst_civil, paramSexo, paramDireccion, paramTelefono, paramFecha  );
      
        if(vacios==true){
            return;
        }
        
        
        setId_serial_funcionario(Integer.parseInt( paramId_serial_funcionario.getText()));
        setTipo_documento(paramTipo_documento.getText()); 
        setNumero_documento(Integer.parseInt( paramNumero_documento.getText()));
        setNombreCompleto(paramNombreCompleto.getText());
        setEst_civil(paramEst_civil.getText());
        setSexo(paramSexo.getText());       
        setDireccion(paramDireccion.getText());
        setTelefono(paramTelefono.getText());
        setFecha_nacimiento(paramFecha.getText());
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "UPDATE funcionario.funcionario SET tipo_documento=?, numero_documento=?, \"nombreCompleto\"=?, est_civil=?, sexo=?, direccion=?, telefono=?, fecha_nacimiento=? WHERE id_serial_funcionario=?;";
        
        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getTipo_documento());
            cs.setInt(2, getNumero_documento());
            cs.setString(3, getNombreCompleto());
            cs.setString(4, getEst_civil());
            cs.setString(5, getSexo().toUpperCase());
            cs.setString(6, getDireccion());
            cs.setString(7, getTelefono());
            cs.setDate(8,  getFecha_nacimiento());
            cs.setInt(9, getId_serial_funcionario());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Modificó Correctamente"); 
            
            
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR:"+ e.toString());
        }
     
     
    }
    
    public void EliminarFuncionario(JTextField paramId_serial_funcionario){
        
        setId_serial_funcionario(Integer.parseInt( paramId_serial_funcionario.getText()));
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "DELETE FROM funcionario.funcionario WHERE id_serial_funcionario=?;";
        
        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
           
            cs.setInt(1, getId_serial_funcionario());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Elimino Correctamente"); 
            
            
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR:"+ e.toString());
        }
     
        
        
        
    }
}


