package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Ventas_SQL {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int r;

    public int idVenta () {
        int id = 0;
        String SQL = "SELECT MAX(id_venta) FROM ventas";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return id;
    }

    public int crearVenta (Ventas ventas) {
        String SQL = "INSERT INTO ventas (dni_cli,nom_emp, total_venta,fecha_venta) VALUES (?,?,?,?)";

        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, ventas.getDni_cli());
            pst.setString(2, ventas.getEmpleado());
            pst.setDouble(3, ventas.getTotal());
            pst.setString(4, ventas.getFecha());
            pst.execute();

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }

    public int registrarDetalle (Detalle detalle){
        String sql = "INSERT INTO detalle (id_venta, cod_prod, cantidad_prod, nom_prod, descrip_prod, pvp_prod) VALUES (?,?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, detalle.getId_venta());
            pst.setString(2, detalle.getCod_prod());
            pst.setInt(3, detalle.getCantidad());
            pst.setString(4, detalle.getNom_prod());
            pst.setString(5, detalle.getDescrip_prod());
            pst.setDouble(4, detalle.getPrecio());
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }

    public boolean actualizarStock (int cantidad, String cod_prod) {
        String SQL = "UPDATE productos SET stock_prod = ? WHERE cod_prod = ?";

        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setInt(1,cantidad);
            pst.setString(2, cod_prod);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List verVentas(){
        List<Ventas> verVentas = new ArrayList();
        String sql = "SELECT c.dni_cli AS id_cli, c.nom_cli, v.* FROM clientes c INNER JOIN ventas v ON c.dni_cli = v.dni_cli";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Ventas ventas = new Ventas();
                ventas.setDni_cli(rs.getString("dni_cli"));
                ventas.setNombre_cli(rs.getString("nom_cli"));
                ventas.setEmpleado(rs.getString("nom_emp"));
                ventas.setTotal(rs.getDouble("total_venta"));
                verVentas.add(ventas);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return verVentas;
    }

    public Ventas buscarVenta(int id_venta){
        Ventas ventas = new Ventas();
        String sql = "SELECT * FROM ventas WHERE id_venta = ?";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id_venta);
            rs = pst.executeQuery();
            if (rs.next()) {
                ventas.setId_venta(rs.getInt("id_venta"));
                ventas.setDni_cli(rs.getString("dni_cli"));
                ventas.setTotal(rs.getDouble("total"));
                ventas.setEmpleado(rs.getString("nom_emp"));
                ventas.setFecha(rs.getString("fecha_venta"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ventas;
    }

}
