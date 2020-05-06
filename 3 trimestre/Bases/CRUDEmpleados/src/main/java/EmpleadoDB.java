
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heidi
 */
public class EmpleadoDB {
    Connection conexion = null;
    
    public EmpleadoDB() {
        try {
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/jardineria", "root", "");
        } catch (SQLException ex) {
            System.out.println("Connection Error: "+ ex.getMessage());
        }
    }
    //LECTURA
    public EmpleadoDB read(Integer idEmpleado){
        EmpleadoDB emp = null;
        PreparedStatement stm = null;
        String sql = "SELECT * FROM Empleados where CodigoEmpleados = ?";
        try{
            stm = conexion.prepareStatement(sql);
            stm.setInt(1, idEmpleado);
            ResultSet rs = stm.executeQuery();
        }catch(SQLException ex){
            System.err.println("Error en la lectura : "+ex.getMessage()+" "+ stm.toString());
        }
        return emp;
    }
    //MODIFICACION
    public Boolean update(Empleado empleado){
        Boolean resultado = false;
        PreparedStatement stm = null;
        String sql = "UPDATE EMPLEADOS SET nombre = ?, apellido1 = ?,apellido2= ? where codigoEmpleado = ?";
        try{
            stm = conexion.prepareStatement(sql);
            stm.setString(1, empleado.getNombre());
            stm.setString(2, empleado.getApellido1());
            stm.setString(3, empleado.getApellido2());
            stm.setInt(4, empleado.getCodigoEmpleado());
            resultado = stm.execute();
        }catch (Exception ex){
                System.out.println("Error en la modificacion: "+ ex.getMessage());
        }
        return resultado;
    }
    //CREACIÓN
    public Boolean insert(Empleado empleado){
        Boolean resultado = false;
        PreparedStatement stm = null;
        String sql = "INSERT INTO empleados(CodigoEmpleado,Nombre,Apellido1,Apellido2) values(?,?,?,?)";
        try{
            stm = conexion.prepareStatement(sql);
            stm.setString(1, empleado.getNombre());
            stm.setString(2, empleado.getApellido1());
            stm.setString(3, empleado.getApellido2());
            stm.setInt(4, empleado.getCodigoEmpleado());
            resultado = stm.execute();
        }catch(SQLException ex){
            System.out.println("Error al crear : "+ ex.getMessage());
        }
        return resultado;
    }
     //BORRADO DE RESGITROS
    public Boolean delete(Empleado empleado){
        Boolean resultado = false;
        PreparedStatement stm = null;
        String sql = "DELETE FROM empleados where codigoEmpleado = ?";
        try{
            stm = conexion.prepareStatement(sql);
            stm.setInt(3, empleado.getCodigoEmpleado());
            resultado = stm.execute();
        }catch (SQLException ex){
                System.out.println("Error en el borrado de registros: "+ ex.getMessage());
        }
        return resultado;
    }
}
