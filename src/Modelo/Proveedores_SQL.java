package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Proveedores_SQL {

    Connection con;
    Conexion conectar = new Conexion();
    PreparedStatement pst;
    ResultSet rs;
    public boolean registrarProveedores(Proveedores proveedores){
        String SQL = "INSERT INTO proveedores(ruc_prov, nom_prov, direc_prov, telf_prov) VALUES (?,?,?,?)";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, proveedores.getRuc());
            pst.setString(2, proveedores.getNombre());
            pst.setString(3, proveedores.getDireccion());
            pst.setString(4, proveedores.getTelefono());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public List verProveedores (){
        List<Proveedores> verProveedores = new ArrayList();
        String SQL = "SELECT * FROM proveedores";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Proveedores proveedores = new Proveedores();
                proveedores.setRuc(rs.getString("ruc_prov"));
                proveedores.setNombre(rs.getString("nom_prov"));
                proveedores.setTelefono(rs.getString("direc_prov"));
                proveedores.setDireccion(rs.getString("telf_prov"));
                verProveedores.add(proveedores);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return verProveedores;
    }

    public boolean eliminarProveedores (String ruc){
        String SQL = "DELETE FROM proveedores WHERE ruc_prov = ? ";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, ruc);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean modificarProveedores (Proveedores proveedores){
        String sql = "UPDATE proveedores SET nom_prov=?, direc_prov=?, telf_prov=? WHERE ruc_prov=?";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, proveedores.getNombre());
            pst.setString(2, proveedores.getDireccion());
            pst.setString(3, proveedores.getTelefono());
            pst.setString(4, proveedores.getRuc());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

}
