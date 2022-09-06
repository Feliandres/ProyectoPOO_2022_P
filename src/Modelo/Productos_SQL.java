package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Productos_SQL {
    Connection con;
    Conexion conectar = new Conexion();
    PreparedStatement pst;
    ResultSet rs;

    public boolean crearProductos(Productos productos){
        String SQL = "INSERT INTO productos (cod_prod, nom_prod, ruc_prov, descrip_prod, stock_prod, pvp_prod) VALUES (?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, productos.getCodigo());
            pst.setString(2, productos.getNombre());
            pst.setString(3, productos.getRuc_prov());
            pst.setString(4, productos.getDescripcion());
            pst.setInt(5, productos.getStock());
            pst.setDouble(6, productos.getPVP());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List verProductos(){
        List<Productos> verProductos = new ArrayList();
        String SQL = "SELECT p.cod_prod, pr.ruc_prov AS ruc_prov, p.* FROM proveedores pr INNER JOIN productos p ON pr.ruc_prov = p.ruc_prov ORDER BY p.cod_prod";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Productos productos = new Productos();
                productos.setCodigo(rs.getString("cod_prod"));
                productos.setNombre(rs.getString("nom_prod"));
                productos.setRuc_prov(rs.getString("ruc_prov"));
                productos.setDescripcion(rs.getString("descrip_prod"));
                productos.setStock(rs.getInt("stock_prod"));
                productos.setPVP(rs.getDouble("pvp_prod"));

                verProductos.add(productos);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return verProductos;
    }

    public boolean eliminarProductos(String cod_prod){
        String SQL = "DELETE FROM productos WHERE cod_prod = ?";
        try {
            pst = con.prepareStatement(SQL);
            pst.setString(1, cod_prod);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public boolean modificarProductos(Productos productos){
        String sql = "UPDATE productos SET nom_prod=?, ruc_prov=?, descrip_prod = ? ,stock_prod =?, pvp_prod=? WHERE cod_prod=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, productos.getNombre());
            pst.setString(2, productos.getRuc_prov());
            pst.setString(3, productos.getDescripcion());
            pst.setInt(4, productos.getStock());
            pst.setDouble(5, productos.getPVP());
            pst.setString(6, productos.getCodigo());
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

    public Productos buscarProductos (String codigo){
        Productos productos = new Productos();
        String SQL = "SELECT * FROM productos WHERE cod_prod = ?";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                productos.setCodigo(rs.getString("cod_prod"));
                productos.setNombre(rs.getString("nom_prod"));
                productos.setPVP(rs.getDouble("pvp_prod"));
                productos.setStock(rs.getInt("stock_prod"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return productos;
    }
    public Productos buscarCodigo(String codigo){
        Productos productos = new Productos();
        String SQL = "SELECT pr.ruc_prov AS ruc_prov, p.* FROM proveedores pr INNER JOIN productos p ON p.ruc_prov = pr.ruc_prov WHERE p.cod_prod = ?";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                productos.setCodigo(rs.getString("cod_prod"));
                productos.setNombre(rs.getString("nom_prod"));
                productos.setRuc_prov(rs.getString("ruc_prov"));
                productos.setStock(rs.getInt("stock_prod"));
                productos.setPVP(rs.getDouble("pvp_prod"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return productos;
    }
    public Proveedores buscarProveedor(String nombre){
        Proveedores proveedores = new Proveedores();
        String SQL = "SELECT * FROM proveedores WHERE nom_prov = ?";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            if (rs.next()) {
                proveedores.setRuc(rs.getString("ruc_prov"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return proveedores;
    }
    public Ajustes buscarDatos(){
        Ajustes ajustes = new Ajustes();
        String SQL = "SELECT * FROM ajustes";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            if (rs.next()) {
                ajustes.setId_farmacia(rs.getInt("id_farmacia"));
                ajustes.setRuc_farmacia(rs.getString("ruc_farmacia"));
                ajustes.setNom_farmacia(rs.getString("nom_farmacia"));
                ajustes.setTelf_farmacia(rs.getString("telf_farmacia"));
                ajustes.setDirec_farmacia(rs.getString("direc_farmacia"));
                ajustes.setMensaje_farmacia(rs.getString("mensaje_farmacia"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ajustes;
    }

    public boolean modificarDatos(Ajustes ajustes){
        String SQL = "UPDATE ajustes SET ruc_farmacia=?, nom_farmacia=?, telf_farmacia=?, direc_farmacia=?, mensaje_farmacia=? WHERE id_farmacia=?";
        try {
            pst = con.prepareStatement(SQL);
            pst.setString(1, ajustes.getRuc_farmacia());
            pst.setString(2, ajustes.getNom_farmacia());
            pst.setString(3, ajustes.getTelf_farmacia());
            pst.setString(4, ajustes.getDirec_farmacia());
            pst.setString(5, ajustes.getMensaje_farmacia());
            pst.setInt(6, ajustes.getId_farmacia());
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
