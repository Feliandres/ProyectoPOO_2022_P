package Modelo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ventas_SQL {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int r;

    public int idVenta () {
        int id = 0;
        String SQL = "SELECT MAX(id_venta) FROM ventas ";
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
        String SQL = "INSERT INTO ventas (dni_cli, nom_cli, nom_emp, total_venta,fecha_venta) VALUES (?,?,?,?,?)";

        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, ventas.getDni_cli());
            pst.setString(2, ventas.getNombre_cli());
            pst.setString(3, ventas.getEmpleado());
            pst.setDouble(4, ventas.getTotal());
            pst.setString(5, ventas.getFecha());
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
        String sql = "INSERT INTO detalle (cod_prod, cantidad_prod, pvp_prod, id_venta) VALUES (?,?,?,?)";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, detalle.getCod_prod());
            pst.setInt(2, detalle.getCantidad());
            pst.setDouble(3, detalle.getPrecio());
            pst.setInt(4, detalle.getId_detalle());
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
                ventas.setId_venta(rs.getInt("id_venta"));
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
                ventas.setTotal(rs.getDouble("total_venta"));
                ventas.setEmpleado(rs.getString("nom_emp"));
                ventas.setFecha(rs.getString("fecha_venta"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ventas;
    }

    public void pdfV(int idventa, String Cliente, double total, String usuario) {
        try {
            Date date = new Date();
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File salida = new File(url + File.separator + "venta.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            //Image img = Image(getClass().getResource("/images/pdf.png"));
            //Fecha
            Paragraph fecha = new Paragraph();
            //Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            fecha.add("Vendedor: " + usuario + "\nFolio: " + idventa + "\nFecha: "
                    + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            //Encabezado.addCell(img);
            Encabezado.addCell("");
            //info empresa
            String config = "SELECT * FROM ajustes";
            String mensaje = "";
            try {
                con = conectar.getConnection();
                pst = con.prepareStatement(config);
                rs = pst.executeQuery();
                if (rs.next()) {
                    mensaje = rs.getString("mensaje_farmacia");
                    Encabezado.addCell("Ruc:    " + rs.getString("ruc_farmacia") + "\nNombre: " + rs.getString("nom_farmacia") + "\nTeléfono: " + rs.getString("telf_farmacia") + "\nDirección: " + rs.getString("direc_farmacia") + "\n\n");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            //
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            //cliente
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("DATOS DEL CLIENTE" + "\n\n");
            doc.add(cli);

            PdfPTable proveedor = new PdfPTable(3);
            proveedor.setWidthPercentage(100);
            proveedor.getDefaultCell().setBorder(0);
            float[] columnWidthsCliente = new float[]{50f, 25f, 25f};
            proveedor.setWidths(columnWidthsCliente);
            proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
            //PdfPCell cliNom = new PdfPCell(new Phrase("Nombre", negrita));
            //PdfPCell cliTel = new PdfPCell(new Phrase("Télefono", negrita));
            //PdfPCell cliDir = new PdfPCell(new Phrase("Dirección", negrita));
            PdfPCell cliNom = new PdfPCell(new Phrase("Nombre"));
            PdfPCell cliTel = new PdfPCell(new Phrase("Télefono"));
            PdfPCell cliDir = new PdfPCell(new Phrase("Dirección"));
            //cliNom.setBorder(Rectangle.NO_BORDER);
            //cliTel.setBorder(Rectangle.NO_BORDER);
            //cliDir.setBorder(Rectangle.NO_BORDER);
            proveedor.addCell(cliNom);
            proveedor.addCell(cliTel);
            proveedor.addCell(cliDir);
            String prove = "SELECT * FROM clientes WHERE dni_cli = ?";
            try {
                pst = con.prepareStatement(prove);
                pst.setString(1, Cliente);
                rs = pst.executeQuery();
                if (rs.next()) {
                    proveedor.addCell(rs.getString("nom_cli"));
                    proveedor.addCell(rs.getString("telf_cli"));
                    proveedor.addCell(rs.getString("direc_cli") + "\n\n");
                } else {
                    proveedor.addCell("Publico en General");
                    proveedor.addCell("S/N");
                    proveedor.addCell("S/N" + "\n\n");
                }

            } catch (Exception e) {
                System.out.println(e.toString());
            }
            doc.add(proveedor);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = new float[]{10f, 50f, 15f, 15f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            //PdfPCell c1 = new PdfPCell(new Phrase("Cant.", negrita));
            //PdfPCell c2 = new PdfPCell(new Phrase("Descripción.", negrita));
            //PdfPCell c3 = new PdfPCell(new Phrase("P. unt.", negrita));
            //PdfPCell c4 = new PdfPCell(new Phrase("P. Total", negrita));
            PdfPCell c1 = new PdfPCell(new Phrase("Cant."));
            PdfPCell c2 = new PdfPCell(new Phrase("Descripción."));
            PdfPCell c3 = new PdfPCell(new Phrase("P. unt."));
            PdfPCell c4 = new PdfPCell(new Phrase("P. Total"));
            //c1.setBorder(Rectangle.NO_BORDER);
            //c2.setBorder(Rectangle.NO_BORDER);
            //c3.setBorder(Rectangle.NO_BORDER);
            //c4.setBorder(Rectangle.NO_BORDER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);
            String product = "SELECT d.id_detalle, d.cod_prod,d.id_venta, d.pvp_prod, d.cantidad_prod, p.descrip_prod, p.nom_prod FROM detalle d INNER JOIN productos p ON d.cod_prod = p.cod_prod WHERE d.id_venta = ?";
            try {
                pst = con.prepareStatement(product);
                pst.setInt(1, idventa);
                rs = pst.executeQuery();
                while (rs.next()) {
                    double subTotal = rs.getInt("cantidad_prod") * rs.getDouble("pvp_prod") ;
                    tabla.addCell(rs.getString("cantidad_prod"));
                    tabla.addCell(rs.getString("nom_prod"));
                    tabla.addCell(rs.getString("pvp_prod"));
                    tabla.addCell(String.valueOf(subTotal));
                }

            } catch (Exception e) {
                System.out.println(e.toString());
            }
            doc.add(tabla);
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total S/: " + total); // como cambio el total
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion \n\n");
            firma.add("------------------------------------\n");
            firma.add("Firma \n");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            Paragraph gr = new Paragraph();
            gr.add(Chunk.NEWLINE);
            gr.add(mensaje);
            gr.setAlignment(Element.ALIGN_CENTER);
            doc.add(gr);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }

}
