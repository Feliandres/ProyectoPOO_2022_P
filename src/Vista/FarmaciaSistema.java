package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class FarmaciaSistema extends JFrame{
    private JTabbedPane crudTabbedPanel;
    private JPanel mainPanel;
    private JPanel principal_1Panel;
    private JPanel nuevaVentaPanel;
    private JPanel clientesPanel;
    private JPanel productosPanel;
    private JButton clientesButton;
    private JPanel p0Panel;
    private JPanel proveedoresPanel;
    private JPanel empleadosPanel;
    private JPanel ventasPanel;
    private JPanel reportesPanel;
    private JPanel ajustesPanel;
    private JPanel tituloPanel;
    private JTable TablaPanel;
    private JTextField dni_cli_TF;
    private JTextField nom_cli_TF;
    private JTextField apel_cli_TF;
    private JTextField direc_cli_TF;
    private JTextField email_cli_TF;
    private JTextField telf_cli_TF;
    private JButton crearButton;
    private JButton eliminarButton;
    private JButton modificarButton;
    private JButton buscarButton;
    private JLabel dni_cli_Txt;
    private JTable clientesTable;
    private JButton nuevaVentaButton;
    private JButton productosButton;
    private JButton proveedoresButton;
    private JButton ventasButton;
    private JButton empleadosButton;
    private JButton reportesButton;
    private JButton ajustesButton;
    private JPanel botonesPanel;
    private JLabel productoTxt;
    private JScrollPane productosScroll;
    private JTable productosTable;
    private JTextField cod_prod_TF;
    private JTextField nom_prod_TF;
    private JTextField ruc_prod_TF;
    private JTextField descrip_prod_TF;
    private JTextField stock_prod_TF;
    private JTextField pvp_prod_TF;
    private JTable proveedoresTable;
    private JTextField ruc_prov_TF;
    private JTextField nom_prov_TF;
    private JTextField direc_prov_TF;
    private JTextField telf_prov_TF;
    private JTable empleadosTable;
    private JTextField telf_emp_TF;
    private JTextField email_emp_TF;
    private JTextField pass_emp_TF;
    private JTextField conf_pass_emp_TF;
    private JComboBox rol_emp_CB;
    private JTextField dni_emp_TF;
    private JTextField nom_emp_TF;
    private JTextField apel_emp_TF;
    private JTextField username__emp_TF;

    // Varibles CONNECTION SQL
    Conexion conectar = new Conexion();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    ResultSetMetaData rsmd;

    // JTABLE VARIABLES
    String[] clientesHeader =  {"DNI","Nombre","Apellido","Direccion","Email","Telefono"};
    String[] productosHeader = {"Codigo","Nombre","RUC","Descripcion","Stock","PvP"};
    String[] proveedoresHeader = {"RUC","Nombre","Direccion","Telefono"};
    String[] empleadosHeader =  {"DNI","Nombre","Apellido","Username","Telefono","Email","Rol"};

    // INICIALIZAR VARIABLES
    Clientes clientes = new Clientes();
    Clientes_SQL clientes_sql = new Clientes_SQL();

    public FarmaciaSistema () {

        setContentPane(mainPanel);
        setTitle("Farmacia Real Audiencia");
        setSize(800,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // centrar la pantalla
        //cargarTablaClientes();

        // BOTON CLIENTES
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTablaClientes();
                limpiarClientes();
                crudTabbedPanel.setSelectedIndex(2);
            }
        });
        // INTERACCION TABLA CLIENTES
        clientesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaClientes();
            }
        });
        // BOTON PRODUCTOS
        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTablaProductos();
                limpiarProductos();
                crudTabbedPanel.setSelectedIndex(3);
            }
        });
        // INTERACCION TABLA PRODUCTOS
        productosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaProductos();
            }
        });
        // BOTON PROVEEDORES
        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTablaProveedores();
                limpiarProveedores();
                crudTabbedPanel.setSelectedIndex(4);
            }
        });
        // INTERACCION TABLA PROVEEDORES
        proveedoresTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaProveedores();
            }
        });
        // BOTON EMPLEADOS
        empleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTablaEmpleados();
                cargarComboBoxEmpleados();
                limpiarEmpleados();
                pass_emp_TF.setEnabled(true);
                conf_pass_emp_TF.setEnabled(true);
                crudTabbedPanel.setSelectedIndex(5);
            }
        });
        // INTERACCION TABLA EMPLEADOS
        empleadosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaEmpleados();
            }
        });
    }


    // FUNCIONES PARA CARGAR JTABLES
    public void cargarTablaClientes () {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(clientesHeader);
        clientesTable.setModel(modelo);

        try {

            pst = null;
            rs = null;
            con = conectar.getConnection();
            String SQL  = "SELECT * FROM clientes";
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();

            rsmd = rs.getMetaData();
            int columnas = rsmd.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[columnas];
                for (int i=0; i< columnas; i++) {
                    filas[i] = rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void cargarTablaProductos () {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(productosHeader);
        productosTable.setModel(modelo);

        try {

            pst = null;
            rs = null;
            con = conectar.getConnection();
            String SQL  = "SELECT * FROM productos";
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();

            rsmd = rs.getMetaData();
            int columnas = rsmd.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[columnas];
                for (int i=0; i< columnas; i++) {
                    filas[i] = rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void cargarTablaProveedores () {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(proveedoresHeader);
        proveedoresTable.setModel(modelo);

        try {

            pst = null;
            rs = null;
            con = conectar.getConnection();
            String SQL  = "SELECT * FROM proveedores";
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();

            rsmd = rs.getMetaData();
            int columnas = rsmd.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[columnas];
                for (int i=0; i< columnas; i++) {
                    filas[i] = rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void cargarTablaEmpleados () {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(empleadosHeader);
        empleadosTable.setModel(modelo);

        try {

            pst = null;
            rs = null;
            con = conectar.getConnection();
            String SQL  = "SELECT dni_emp,nom_emp,apel_emp,username_emp,telf_emp,email_emp,cod_rol from empleados";
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();

            rsmd = rs.getMetaData();
            int columnas = rsmd.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[columnas];
                for (int i=0; i< columnas; i++) {
                    filas[i] = rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void cargarComboBoxEmpleados () {
        con = null;
        pst = null;
        String SQL = "SELECT * FROM roles";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();

            while (rs.next()) {
                rol_emp_CB.addItem(rs.getString("tipo_rol").toUpperCase());
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // FUNCIONES LIMPIAR CONTENIDO DE LAS JTABLES
    private void limpiarClientes () {
        dni_cli_TF.setText("");
        nom_cli_TF.setText("");
        apel_cli_TF.setText("");
        direc_cli_TF.setText("");
        email_cli_TF.setText("");
        telf_cli_TF.setText("");
    }
    private void limpiarProductos () {
        cod_prod_TF.setText("");
        nom_prod_TF.setText("");
        ruc_prod_TF.setText("");
        descrip_prod_TF.setText("");
        stock_prod_TF.setText("");
        pvp_prod_TF.setText("");
    }
    private void limpiarProveedores () {
        ruc_prov_TF.setText("");
        nom_prov_TF.setText("");
        direc_prov_TF.setText("");
        telf_prov_TF.setText("");
    }
    private void limpiarEmpleados () {
        dni_emp_TF.setText("");
        nom_emp_TF.setText("");
        apel_emp_TF.setText("");
        username__emp_TF.setText("");
        telf_emp_TF.setText("");
        email_emp_TF.setText("");
        pass_emp_TF.setText("");
        conf_pass_emp_TF.setText("");
    }

    // FUNCIONES PARA INTERACTUAR CON JTABLES
    public void interaccionTablaClientes () {

        try{

            con = conectar.getConnection();
            int fila = clientesTable.getSelectedRow();
            String dni = String.valueOf(clientesTable.getValueAt(fila,0));
            String SQL = "SELECT * from clientes where dni_cli = ?";

            pst = con.prepareStatement(SQL);
            pst.setString(1,dni);
            rs = pst.executeQuery();

            while (rs.next()){
                dni_cli_TF.setText(dni);
                nom_cli_TF.setText(rs.getString("nom_cli"));
                apel_cli_TF.setText(rs.getString("apel_cli"));
                direc_cli_TF.setText(rs.getString("direc_cli"));
                email_cli_TF.setText(rs.getString("email_cli"));
                telf_cli_TF.setText(rs.getString("telf_cli"));
            }


        }catch (Exception e){
            System.out.println(e.toString());

        } finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
    public void interaccionTablaProductos () {

        try{

            con = conectar.getConnection();
            int fila = productosTable.getSelectedRow();
            String cod = String.valueOf(productosTable.getValueAt(fila,0));
            String SQL = "SELECT * from productos where cod_prod = ?";

            pst = con.prepareStatement(SQL);
            pst.setString(1,cod);
            rs = pst.executeQuery();

            while (rs.next()){
                cod_prod_TF.setText(cod);
                nom_prod_TF.setText(rs.getString("nom_prod"));
                ruc_prod_TF.setText(rs.getString("ruc_prov"));
                descrip_prod_TF.setText(rs.getString("descrip_prod"));
                stock_prod_TF.setText(rs.getString("stock_prod"));
                pvp_prod_TF.setText(rs.getString("pvp_prod"));
            }


        }catch (Exception e){
            System.out.println(e.toString());

        } finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
    public void interaccionTablaProveedores () {

        try{

            con = conectar.getConnection();
            int fila = proveedoresTable.getSelectedRow();
            String ruc = String.valueOf(proveedoresTable.getValueAt(fila,0));
            String SQL = "SELECT * from proveedores where ruc_prov = ?";

            pst = con.prepareStatement(SQL);
            pst.setString(1,ruc);
            rs = pst.executeQuery();

            while (rs.next()){
                ruc_prov_TF.setText(ruc);
                nom_prov_TF.setText(rs.getString("nom_prov"));
                direc_prov_TF.setText(rs.getString("direc_prov"));
                telf_prov_TF.setText(rs.getString("telf_prov"));

            }


        }catch (Exception e){
            System.out.println(e.toString());

        } finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
    public void interaccionTablaEmpleados () {

        try{

            con = conectar.getConnection();
            int fila = empleadosTable.getSelectedRow();
            String dni = String.valueOf(empleadosTable.getValueAt(fila,0));
            String SQL = "SELECT dni_emp,nom_emp,apel_emp,username_emp,telf_emp,email_emp from empleados where dni_emp = ?";

            pst = con.prepareStatement(SQL);
            pst.setString(1,dni);
            rs = pst.executeQuery();

            while (rs.next()){
                dni_emp_TF.setText(dni);
                nom_emp_TF.setText(rs.getString("nom_emp"));
                apel_emp_TF.setText(rs.getString("apel_emp"));
                username__emp_TF.setText(rs.getString("username_emp"));
                telf_emp_TF.setText(rs.getString("telf_emp"));
                email_emp_TF.setText(rs.getString("email_emp"));
                pass_emp_TF.setEnabled(false);
                conf_pass_emp_TF.setEnabled(false);
                rol_emp_CB.setEnabled(false);
            }


        }catch (Exception e){
            System.out.println(e.toString());

        } finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }





    public static void main(String[] args) {
        FarmaciaSistema farmaciaSistema = new FarmaciaSistema();
    }

}
