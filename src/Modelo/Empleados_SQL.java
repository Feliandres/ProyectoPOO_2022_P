package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Empleados_SQL {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Conexion conectar = new Conexion();

    public Empleados login (String email, String pass, String rol){
        Empleados login = new Empleados();
        String SQL = "SELECT email_emp, pass_emp, cod_rol FROM empleados WHERE email_emp = ? AND pass_emp = ? AND cod_rol = ?";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, email);
            pst.setString(2, pass);
            pst.setString(3,rol);
            rs= pst.executeQuery();
            if (rs.next()) {

                //login.setNombre(rs.getString("nom_emp"));
                login.setEmail(rs.getString("email_emp"));
                login.setPassword(rs.getString("pass_emp"));
                login.setRol(rs.getString("cod_rol"));

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return login;
    }

    public boolean registrarEmpleados (Empleados empleados){
        String SQL = "INSERT INTO empleados (dni_emp, nom_emp, apel_emp, username_emp, telf_emp, email_emp, pass_emp, conf_pass_emp, cod_rol) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, empleados.getDni());
            pst.setString(2, empleados.getNombre());
            pst.setString(3, empleados.getApellido());
            pst.setString(4, empleados.getUsername());
            pst.setString(5, empleados.getTelefono());
            pst.setString(6, empleados.getEmail());
            pst.setString(7, empleados.getPassword());
            pst.setString(8, empleados.getConfirm_password());
            pst.setString(9, empleados.getRol());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List verEmpleados (){
        List<Empleados> verEmpleados = new ArrayList();
        String sql = "SELECT * FROM empleados";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Empleados empleados = new Empleados();
                empleados.setDni(rs.getString("dni_emp"));
                empleados.setNombre(rs.getString("nom_emp"));
                empleados.setApellido(rs.getString("apel_emp"));
                empleados.setUsername(rs.getString("username_emp"));
                empleados.setTelefono(rs.getString("telf_emp"));
                empleados.setEmail(rs.getString("email_emp"));
                empleados.setPassword(rs.getString("pass_emp"));
                empleados.setConfirm_password(rs.getString("conf_pass_emp"));
                empleados.setRol(rs.getString("cod_rol"));
                verEmpleados.add(empleados);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return verEmpleados;
    }
}
