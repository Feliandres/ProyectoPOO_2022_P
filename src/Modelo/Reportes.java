package Modelo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Reportes {
    public static void Graficar (String fecha){
        Connection con;
        Conexion conectar = new Conexion();
        PreparedStatement pst;
        ResultSet rs;
        try {
            String sql = "SELECT total_venta FROM ventas WHERE fecha_venta = ?";
            con = conectar.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, fecha);
            rs = pst.executeQuery();
            DefaultPieDataset dateset = new DefaultPieDataset();
            while(rs.next()){
                dateset.setValue(rs.getString("total_venta"), rs.getDouble("total_venta"));
            }
            JFreeChart jf = ChartFactory.createPieChart("Reporte de Venta", dateset);
            ChartFrame f = new ChartFrame("Total de Ventas por dia", jf);
            f.setSize(1000, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
