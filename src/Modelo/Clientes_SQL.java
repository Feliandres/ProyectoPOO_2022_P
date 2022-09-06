package Modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Clientes_SQL {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public boolean registrarClientes (Clientes clientes) {

        String SQL = "INSERT INTO clientes (dni_cli,nom_cli,apel_cli,direc_cli,email_cli,telf_cli) VALUES (?,?,?,?,?,?)";

        try {

            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1,clientes.getDni());
            pst.setString(2,clientes.getNombre());
            pst.setString(3,clientes.getApellido());
            pst.setString(4,clientes.getDireccion());
            pst.setString(5,clientes.getEmail());
            pst.setString(6,clientes.getTelefono());
            pst.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public  boolean modificarClientes (Clientes clientes) {

        String SQL = "UPDATE clientes SET nom_cli = ?, apel_cli = ?, direc_cli = ?, email_cli = ?, telf_cli = ? where dni_cli = ?";

        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, clientes.getNombre());
            pst.setString(2, clientes.getApellido());
            pst.setString(3, clientes.getDireccion());
            pst.setString(4, clientes.getEmail());
            pst.setString(5, clientes.getTelefono());
            pst.setString(6, clientes.getDni());
            pst.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean eliminarClientes (String dni) {

        String SQL = "DELETE FROM clientes where dni_cli = ?";

        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, dni);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public List verClientes () {

        List<Clientes> listaClientes = new ArrayList<>();
        String SQL = "SELECT * FROM clientes";

        try {

            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();

            while (rs.next()) {

                Clientes clientes  =new Clientes();
                clientes.setDni(rs.getString("dni_cli"));
                clientes.setNombre(rs.getString("nom_cli"));
                clientes.setApellido(rs.getString("apel_cli"));
                clientes.setDireccion(rs.getString("direc_cli"));
                clientes.setEmail(rs.getString("email_cli"));
                clientes.setTelefono(rs.getString("telf_cli"));
                listaClientes.add(clientes);

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listaClientes;
    }

    public Clientes buscarClientes (String dni) {

        Clientes clientes = new Clientes();
        String SQL = "SELECT * FROM clientes where dni_cli = ?";

        try {

            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1,dni);
            rs = pst.executeQuery();

            if (rs.next()) {
                clientes.setDni(rs.getString("dni_cli"));
                clientes.setNombre(rs.getString("nom_cli"));
                clientes.setApellido(rs.getString("apel_cli"));
                clientes.setDireccion(rs.getString("direc_cli"));
                clientes.setEmail(rs.getString("email_cli"));
                clientes.setTelefono(rs.getString("telf_cli"));
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return clientes;
    }

}
