package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Modelo.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private JPanel tituloPanel;
    private JTable TablaPanel;
    private JTextField dni_cli_TF;
    private JTextField nom_cli_TF;
    private JTextField apel_cli_TF;
    private JTextField direc_cli_TF;
    private JTextField email_cli_TF;
    private JTextField telf_cli_TF;
    private JButton crear_cli_BTN;
    private JButton eliminar_cli_BTN;
    private JButton modificar_cli_BTN;
    private JButton guardar_cli_BTN;
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
    private JTextField cod_venta_TF; // PARA BUSCAR PRODUCTOS
    private JTextField cantidad_venta_TF;
    private JTextField nom_venta_TF; // PARA BUSCAR PRODUCTOS
    private JTextField precio_venta_TF; // PARA BUSCAR PRODUCTOS
    private JTextField stock_venta_TF; // PARA BUSCAR PRODUCTOS
    private JTable ventasTable;
    private JTextField dni_cli_venta_TF;
    private JTextField nom_cli_venta_TF;
    private JTextField subotal_venta_TF;
    private JButton generar_factura_venta_BTN;
    private JTextField iva_venta_TF;
    private JTextField total_venta_TF;
    private JButton eliminar_venta_BTN;
    private JLabel nom_empTxT;
    private JTextField ruc_ajustes_TF;
    private JTextField nom_ajustes_TF;
    private JTextField direc_ajustes_TF;
    private JTextField telf_ajustes_TF;
    private JTextField mensaje_ajustes_TF;
    private JButton actualizar_ajustes_BTN;
    private JButton eliminar_prov_BTN;
    private JButton crear_prov_BTN;
    private JButton modificar_prov_BTN;
    private JButton guardar_prov_BTN;
    private JButton eliminar_prod_BTN;
    private JButton crear_prod_BTN;
    private JButton modificar_prod_BTN;
    private JButton guardar_prod_BTN;
    private JPanel ajustesPanel;
    private JButton guardar_emp_BTN;
    private JButton eliminar_emp_BTN;
    private JButton crear_emp_BTN;
    private JButton modificar_emp_BTN;
    private JTable historial_ventaTable;
    private JButton PDF_historial_venta_BTN;
    private JTextField id_venta_historial_TF;
    private JLabel id_venta_historial_Txt;
    private JLabel nom_emp_inicio_Txt;
    private JLabel username_emp_inicio_Txt;
    private JLabel dni_emp_inicio_Txt;
    private JLabel email_emp_inicio_Txt;
    private JLabel telf_emp_inicio_Txt;
    private JLabel rol_emp_inicio_Txt;
    private JButton cerrar_sesion_BTN;
    private JTextField fecha_venta_TF;
    private JComboBox ruc_prod_CB;
    private JButton graficar_reporte_BTN;
    private JPasswordField pass_emp_PF;
    private JPasswordField conf_pass_emp_PF;
    private JLabel empleado_inicio_Txt;


    // Varibles CONNECTION SQL
    Conexion conectar = new Conexion();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    ResultSetMetaData rsmd;

    // JTABLE VARIABLES
    DefaultTableModel temporal = new DefaultTableModel();
    DefaultTableModel modelo = new DefaultTableModel();
    String[] clientesHeader =  {"DNI","Nombre","Apellido","Direccion","Email","Telefono"};
    String[] productosHeader = {"Codigo","Nombre","RUC","Descripcion","Stock","PvP"};
    String[] proveedoresHeader = {"RUC","Nombre","Direccion","Telefono"};
    String[] empleadosHeader =  {"DNI","Nombre","Apellido","Username","Telefono","Email","Rol"};
    String[] detalleDeFacturaHeader =  {"Codigo","Nombre","Cantidad","PVP","Total"};
    String[] ventasHeader =  {"ID","Cliente","Empleado","Total"};

    int fila = 0;
    double totalPagar = 0.00;
    double totalFinal = 0.00;
    double iva = 0.00;
    int item;
    LocalDate fechaActual = LocalDate.now();

    // INICIALIZAR VARIABLES
    Clientes clientes = new Clientes();
    Clientes_SQL clientes_sql = new Clientes_SQL();
    Ventas ventas = new Ventas();
    Ventas_SQL ventas_sql = new Ventas_SQL();
    Detalle detalle = new Detalle();
    Productos productos = new Productos();
    Productos_SQL productos_sql = new Productos_SQL();
    Eventos eventos = new Eventos();
    Proveedores proveedores = new Proveedores();
    Proveedores_SQL proveedores_sql = new Proveedores_SQL();
    Empleados empleados = new Empleados();
    Empleados_SQL empleados_sql = new Empleados_SQL();
    Ajustes ajustes = new Ajustes();

    public FarmaciaSistema (String rol,String nombre, String apellido, String username, String dni, String telefono, String email) {

        setContentPane(mainPanel);
        setTitle("Farmacia Real Audiencia");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // centrar la pantalla
        verTablaAjustes();

        if (rol.equals("bode")) {
            nuevaVentaButton.setEnabled(false);
            clientesButton.setEnabled(false);
            ventasTable.setEnabled(false);
            ajustesButton.setEnabled(false);
            reportesButton.setEnabled(false);
            empleadosButton.setEnabled(false);
            productosButton.setEnabled(true);
            proveedoresButton.setEnabled(true);

            nom_emp_inicio_Txt.setText(nombre + " " + apellido);
            username_emp_inicio_Txt.setText(username);
            dni_emp_inicio_Txt.setText(dni);
            email_emp_inicio_Txt.setText(email);
            telf_ajustes_TF.setText(telefono);
            rol_emp_inicio_Txt.setText("Bodeguero");
        } else if (rol.equals("caje")) {
            nuevaVentaButton.setEnabled(true);
            clientesButton.setEnabled(false);
            ventasTable.setEnabled(false);
            ajustesButton.setEnabled(false);
            reportesButton.setEnabled(false);
            empleadosButton.setEnabled(false);
            productosButton.setEnabled(false);
            proveedoresButton.setEnabled(false);

            nom_emp_inicio_Txt.setText(nombre + " " + apellido);
            username_emp_inicio_Txt.setText(username);
            dni_emp_inicio_Txt.setText(dni);
            email_emp_inicio_Txt.setText(email);
            telf_emp_inicio_Txt.setText(telefono);
            rol_emp_inicio_Txt.setText("Cajero");
        } else if (rol.equals("admi")) {
            nuevaVentaButton.setEnabled(true);
            clientesButton.setEnabled(true);
            ventasTable.setEnabled(true);
            ajustesButton.setEnabled(true);
            reportesButton.setEnabled(true);
            empleadosButton.setEnabled(true);
            productosButton.setEnabled(true);
            proveedoresButton.setEnabled(true);

            nom_emp_inicio_Txt.setText(nombre + " " + apellido);
            username_emp_inicio_Txt.setText(username);
            dni_emp_inicio_Txt.setText(dni);
            email_emp_inicio_Txt.setText(email);
            telf_emp_inicio_Txt.setText(telefono);
            rol_emp_inicio_Txt.setText("Administrador");
        } else {
            nom_emp_inicio_Txt.setText(nombre + " " + apellido);
            username_emp_inicio_Txt.setText(username);
            dni_emp_inicio_Txt.setText(dni);
            email_emp_inicio_Txt.setText(email);
            telf_emp_inicio_Txt.setText(telefono);
            rol_emp_inicio_Txt.setText("Desconocido");
        }


        // BOTON CLIENTES
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar_cli_BTN.setEnabled(false);
                modificar_cli_BTN.setEnabled(false);
                LimpiarTable();
                verTablaClientes();
                limpiarClientes();
                cargarComboBoxProductos();
                crudTabbedPanel.setSelectedIndex(2);
            }
        });
        // BOTON PRODUCTOS
        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar_prod_BTN.setEnabled(false);
                modificar_prod_BTN.setEnabled(false);
                crear_prod_BTN.setEnabled(true);
                cargarComboBoxProductos();
                LimpiarTable();
                verTablaProductos();
                limpiarProductos();
                crudTabbedPanel.setSelectedIndex(3);
            }
        });
        // BOTON PROVEEDORES
        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar_prov_BTN.setEnabled(true);
                modificar_prov_BTN.setEnabled(true);
                LimpiarTable();
                verTablaProveedores();
                limpiarProveedores();
                crudTabbedPanel.setSelectedIndex(4);
            }
        });
        // BOTON EMPLEADOS
        empleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTablaEmpleados();
                cargarComboBoxEmpleados();
                limpiarEmpleados();
                pass_emp_PF.setEnabled(true);
                conf_pass_emp_PF.setEnabled(true);
                crudTabbedPanel.setSelectedIndex(5);
            }
        });
        // BOTON NUEVA VENTA
        nuevaVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTablaVentas();
                fecha_venta_TF.setText(String.valueOf(fechaActual));
                crudTabbedPanel.setSelectedIndex(1);
            }
        });
        // BOTON CLIENTES
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificar_cli_BTN.setEnabled(false);
                eliminar_cli_BTN.setEnabled(false);
                LimpiarTable();
                verTablaClientes();
                limpiarClientes();
                crudTabbedPanel.setSelectedIndex(2);
            }
        });
        // BOTON PROVEEDORES
        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar_prov_BTN.setEnabled(false);
                modificar_prov_BTN.setEnabled(false);
                crear_prov_BTN.setEnabled(true);
                LimpiarTable();
                verTablaProveedores();
                limpiarProveedores();
                crudTabbedPanel.setSelectedIndex(3);
            }
        });
        // BOTON REPORTES
        reportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crudTabbedPanel.setSelectedIndex(7);
            }
        });
        // BOTON AJUSTES
        ajustesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crudTabbedPanel.setSelectedIndex(8);
            }
        });
        // BOTON VENTAS
        ventasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimpiarTable();
                verTablaVentasHistorial();
                crudTabbedPanel.setSelectedIndex(6);
            }
        });
        // BOTON GUARDAR - PANEL EMPLEADOS
        guardar_emp_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nom_emp_TF.getText().equals("") || email_emp_TF.getText().equals("") || String.valueOf(pass_emp_PF.getPassword()).equals("")
                        || String.valueOf(conf_pass_emp_PF.getPassword()).equals("") || apel_emp_TF.getText().equals("") || username__emp_TF.getText().equals("")
                        || telf_emp_TF.getText().equals("") || dni_emp_TF.getText().equals("") || rol_emp_CB.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todo los campos son requeridos");
                } else {
                    String email = email_emp_TF.getText();
                    String pass = String.valueOf(pass_emp_PF.getPassword());
                    String nombre = nom_emp_TF.getText();
                    String apellido = apel_emp_TF.getText();
                    String telefono = telf_emp_TF.getText();
                    String dni = dni_emp_TF.getText();
                    String username = username__emp_TF.getText();
                    String conf_pass = String.valueOf(conf_pass_emp_PF.getPassword());
                    String rol = rol_emp_CB.getSelectedItem().toString().substring(0, 4).toLowerCase();
                    empleados.setNombre(nombre);
                    empleados.setEmail(email);
                    empleados.setPassword(pass);
                    empleados.setConfirm_password(conf_pass);
                    empleados.setDni(dni);
                    empleados.setApellido(apellido);
                    empleados.setTelefono(telefono);
                    empleados.setUsername(username);
                    empleados.setRol(rol);
                    empleados_sql.registrarEmpleados(empleados);
                    LimpiarTable();
                    limpiarEmpleados();
                    verTablaEmpleados();
                    JOptionPane.showMessageDialog(null, "Usuario Registrado");
                }
            }
        });
        // BOTON MODIFICAR - PANEL EMPLEADOS
        modificar_emp_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(dni_emp_TF.getText())) {
                    JOptionPane.showMessageDialog(null, "Seleecione una fila");
                } else {
                    if (!"".equals(dni_emp_TF.getText()) || !"".equals(nom_emp_TF.getText()) || !"".equals(apel_emp_TF.getText()) || !"".equals(username__emp_TF.getText()) || !"".equals(telf_emp_TF.getText()) || !"".equals(email_emp_TF.getText())
                            || !"".equals(String.valueOf(pass_emp_PF.getPassword())) || !"".equals(String.valueOf(conf_pass_emp_PF.getPassword())) || !"".equals(rol_emp_CB.getSelectedItem())) {

                        empleados.setDni(dni_emp_TF.getText());
                        empleados.setNombre(nom_emp_TF.getText());
                        empleados.setApellido(apel_emp_TF.getText());
                        empleados.setUsername(username__emp_TF.getText());
                        empleados.setTelefono(telf_emp_TF.getText());
                        empleados.setEmail(email_emp_TF.getText());
                        empleados.setPassword(String.valueOf(pass_emp_PF.getPassword()));
                        empleados.setConfirm_password(String.valueOf(conf_pass_emp_PF.getPassword()));
                        empleados.setRol(rol_emp_CB.getSelectedItem().toString().substring(0,4).toLowerCase());
                        empleados_sql.modificarEmpleado(empleados);
                        JOptionPane.showMessageDialog(null, "Empleado Modificado");
                        LimpiarTable();
                        verTablaEmpleados();
                        limpiarEmpleados();
                        modificar_emp_BTN.setEnabled(false);
                        eliminar_emp_BTN.setEnabled(false);
                        guardar_emp_BTN.setEnabled(true);
                    }
                }
            }
        });
        // BOTON CREAR - PANEL EMPLEADOS
        crear_emp_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarEmpleados();
                eliminar_emp_BTN.setEnabled(false);
                modificar_emp_BTN.setEnabled(false);
            }
        });
        // BOTON ELIMINAR - PANEL EMPLEADOS
        eliminar_emp_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(dni_emp_TF.getText())) {
                    int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
                    if (pregunta == 0) {
                        String dni = String.valueOf(dni_emp_TF.getText());
                        empleados_sql.eliminarEmpleados(dni);
                        LimpiarTable();
                        limpiarEmpleados();
                        verTablaEmpleados();
                        modificar_prod_BTN.setEnabled(false);
                        eliminar_prod_BTN.setEnabled(false);
                        guardar_prod_BTN.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una fila");
                }
            }
        });
        // BOTON ACTUALIZAR - PANEL AJUSTES
        actualizar_ajustes_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(ruc_ajustes_TF.getText()) || !"".equals(nom_ajustes_TF.getText()) || !"".equals(telf_ajustes_TF.getText()) || !"".equals(direc_ajustes_TF.getText())) {
                    ajustes.setRuc_farmacia(ruc_ajustes_TF.getText());
                    ajustes.setNom_farmacia(nom_ajustes_TF.getText());
                    ajustes.setTelf_farmacia(telf_ajustes_TF.getText());
                    ajustes.setDirec_farmacia(direc_ajustes_TF.getText());
                    ajustes.setMensaje_farmacia(mensaje_ajustes_TF.getText());
                    productos_sql.modificarDatos(ajustes);
                    JOptionPane.showMessageDialog(null, "Datos de la empresa modificado");
                    verTablaAjustes();
                } else {
                    JOptionPane.showMessageDialog(null, "Los campos estan vacios");
                }
            }
        });
        // BOTON CREAR - PANEL PRODUCTOS
        crear_prod_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarProductos();
            }
        });
        // BOTON ELIMINAR - PANEL PRODUCTOS
        eliminar_prod_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(cod_prod_TF.getText())) {
                    int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
                    if (pregunta == 0) {
                        String codigo = String.valueOf(cod_prod_TF.getText());
                        productos_sql.eliminarProductos(codigo);
                        LimpiarTable();
                        limpiarProductos();
                        verTablaProductos();
                        modificar_prod_BTN.setEnabled(false);
                        eliminar_prod_BTN.setEnabled(false);
                        guardar_prod_BTN.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una fila");
                }
            }
        });
        // BOTON MODIFICAR - PANEL PRODUCTOS
        modificar_prod_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(cod_prod_TF.getText())) {
                    JOptionPane.showMessageDialog(null, "Seleecione una fila");
                } else {
                    if (!"".equals(cod_prod_TF.getText()) || !"".equals(descrip_prod_TF.getText()) || !"".equals(stock_prod_TF.getText()) || !"".equals(pvp_prod_TF.getText()) || !"".equals(nom_prod_TF.getText()) || !"".equals(ruc_prod_CB.getSelectedItem())) {
                        productos.setCodigo(cod_prod_TF.getText());
                        productos.setNombre(nom_prod_TF.getText());
                        productos.setRuc_prov(String.valueOf(ruc_prod_CB.getSelectedItem()));
                        productos.setDescripcion(descrip_prod_TF.getText());
                        productos.setStock(Integer.parseInt(stock_prod_TF.getText()));
                        productos.setPVP(Double.parseDouble(pvp_prod_TF.getText()));
                        productos_sql.modificarProductos(productos);
                        JOptionPane.showMessageDialog(null, "Producto Modificado");
                        LimpiarTable();
                        verTablaProductos();
                        limpiarProductos();
                        modificar_prod_BTN.setEnabled(false);
                        eliminar_prod_BTN.setEnabled(false);
                        guardar_prod_BTN.setEnabled(true);
                    }
                }
            }
        });
        // BOTON GUARDAR - PANEL PRODUCTOS
        guardar_prod_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(cod_prod_TF.getText()) || !"".equals(descrip_prod_TF.getText()) || !"".equals(ruc_prod_CB.getSelectedItem()) || !"".equals(stock_prod_TF.getText()) || !"".equals(pvp_prod_TF.getText())) {
                    productos.setCodigo(cod_prod_TF.getText());
                    productos.setNombre(nom_prod_TF.getText());
                    productos.setDescripcion(descrip_prod_TF.getText());
                    productos.setRuc_prov(String.valueOf(ruc_prod_CB.getSelectedItem()));
                    productos.setStock(Integer.parseInt(stock_prod_TF.getText()));
                    productos.setPVP(Double.parseDouble(pvp_prod_TF.getText()));
                    productos_sql.crearProductos(productos);
                    JOptionPane.showMessageDialog(null, "Productos Registrado");
                    LimpiarTable();
                    verTablaProductos();
                    limpiarProductos();
                    modificar_prod_BTN.setEnabled(false);
                    eliminar_prod_BTN.setEnabled(false);
                    guardar_prod_BTN.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Los campos estan vacios");
                }
            }
        });
        // BOTON ELIMINAR - PANEL PROVEEDORES
        eliminar_prov_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(ruc_prov_TF.getText())) {
                    int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
                    if (pregunta == 0) {
                        String ruc = ruc_prov_TF.getText();
                        proveedores_sql.eliminarProveedores(ruc);
                        LimpiarTable();
                        verTablaProveedores();
                        limpiarProveedores();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila");
                }
            }
        });
        // BOTON CREAR - PANEL PROVEEDORES
        crear_prov_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarProveedores();
                modificar_prov_BTN.setEnabled(false);
                eliminar_prov_BTN.setEnabled(false);
                guardar_prov_BTN.setEnabled(true);
            }
        });
        // BOTON MODIFICAR - PANEL PROVEEDORES
        modificar_prov_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(ruc_prov_TF.getText())) {
                    JOptionPane.showMessageDialog(null, "Seleecione una fila");
                } else {
                    if (!"".equals(ruc_prov_TF.getText()) || !"".equals(nom_prov_TF.getText()) || !"".equals(telf_prov_TF.getText()) || !"".equals(direc_prov_TF.getText())) {
                        proveedores.setRuc(ruc_prov_TF.getText());
                        proveedores.setNombre(nom_prov_TF.getText());
                        proveedores.setTelefono(telf_prov_TF.getText());
                        proveedores.setDireccion(direc_prov_TF.getText());
                        proveedores_sql.modificarProveedores(proveedores);
                        JOptionPane.showMessageDialog(null, "Proveedor Modificado");
                        LimpiarTable();
                        verTablaProveedores();
                        limpiarProveedores();
                        modificar_prov_BTN.setEnabled(false);
                        eliminar_prov_BTN.setEnabled(false);
                        guardar_prov_BTN.setEnabled(true);
                    }
                }
            }
        });
        // BOTON GUARDAR - PANEL PROVEEDORES
        guardar_prov_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(ruc_prov_TF.getText()) || !"".equals(nom_prov_TF.getText()) || !"".equals(telf_prov_TF.getText()) || !"".equals(direc_prov_TF.getText())) {
                    proveedores.setRuc(ruc_prov_TF.getText());
                    proveedores.setNombre(nom_prov_TF.getText());
                    proveedores.setTelefono(telf_prov_TF.getText());
                    proveedores.setDireccion(direc_prov_TF.getText());
                    proveedores_sql.registrarProveedores(proveedores);
                    JOptionPane.showMessageDialog(null, "Proveedor Registrado");
                    LimpiarTable();
                    verTablaProveedores();
                    limpiarProveedores();
                    modificar_prov_BTN.setEnabled(false);
                    eliminar_prov_BTN.setEnabled(false);
                    guardar_prov_BTN.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Los campos esta vacios");
                }

            }
        });
        // BOTON CREAR - PANEL CLIENTES
        crear_cli_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarClientes();
                modificar_cli_BTN.setEnabled(false);
                eliminar_cli_BTN.setEnabled(false);
                guardar_cli_BTN.setEnabled(true);
            }
        });
        // BOTON MODIFICAR - PANEL CLIENTES
        modificar_cli_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(dni_cli_TF.getText())) {
                    JOptionPane.showMessageDialog(null, "seleccione una fila");
                } else {

                    if (!"".equals(dni_cli_TF.getText()) || !"".equals(nom_cli_TF.getText()) || !"".equals(apel_cli_TF.getText()) || !"".equals(direc_cli_TF.getText()) || !"".equals(email_cli_TF.getText()) || !"".equals(telf_cli_TF.getText())) {
                        clientes.setDni(dni_cli_TF.getText());
                        clientes.setNombre(nom_cli_TF.getText());
                        clientes.setApellido(apel_cli_TF.getText());
                        clientes.setDireccion(direc_cli_TF.getText());
                        clientes.setEmail(email_cli_TF.getText());
                        clientes.setTelefono(telf_cli_TF.getText());
                        clientes_sql.modificarClientes(clientes);
                        JOptionPane.showMessageDialog(null, "Cliente Modificado");
                        LimpiarTable();
                        limpiarClientes();
                        verTablaClientes();
                    } else {
                        JOptionPane.showMessageDialog(null, "Los campos estan vacios");
                    }
                }
            }
        });
        // BOTON GUARDAR - PANEL CLIENTES
        guardar_cli_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(dni_cli_TF.getText()) || !"".equals(nom_cli_TF.getText()) || !"".equals(apel_cli_TF.getText()) || !"".equals(direc_cli_TF.getText()) || !"".equals(email_cli_TF.getText()) || !"".equals(telf_cli_TF.getText())) {
                    clientes.setDni(dni_cli_TF.getText());
                    clientes.setNombre(nom_cli_TF.getText());
                    clientes.setApellido(apel_cli_TF.getText());
                    clientes.setDireccion(direc_cli_TF.getText());
                    clientes.setEmail(email_cli_TF.getText());
                    clientes.setTelefono(telf_cli_TF.getText());
                    clientes_sql.registrarClientes(clientes);
                    JOptionPane.showMessageDialog(null, "Cliente Registrado");
                    LimpiarTable();
                    limpiarClientes();
                    verTablaClientes();
                    modificar_cli_BTN.setEnabled(false);
                    eliminar_cli_BTN.setEnabled(false);
                    guardar_cli_BTN.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Los campos estan vacios");
                }
            }
        });
        // BOTON ELIMINAR - PANEL CLIENTES
        eliminar_cli_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(dni_cli_TF.getText())) {
                    int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
                    if (pregunta == 0) {
                        String dni = dni_cli_TF.getText();
                        clientes_sql.eliminarClientes(dni);
                        LimpiarTable();
                        limpiarClientes();
                        verTablaClientes();
                    }
                }
            }
        });


        // INTERACCION TABLA CLIENTES
        clientesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaClientes();
                eliminar_cli_BTN.setEnabled(true);
                modificar_cli_BTN.setEnabled(true);
            }
        });

        // INTERACCION TABLA PRODUCTOS
        productosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaProductos();
                modificar_prod_BTN.setEnabled(true);
                eliminar_prod_BTN.setEnabled(true);
            }
        });

        // INTERACCION TABLA PROVEEDORES
        proveedoresTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaProveedores();
                modificar_prov_BTN.setEnabled(true);
                eliminar_prov_BTN.setEnabled(true);
            }
        });

        // INTERACCION TABLA EMPLEADOS
        empleadosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaEmpleados();
                eliminar_emp_BTN.setEnabled(true);
            }
        });
        // INTERACCION TABLA HISTORIAL VENTA
        historial_ventaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = historial_ventaTable.rowAtPoint(e.getPoint());
                id_venta_historial_TF.setText(historial_ventaTable.getValueAt(fila, 0).toString());
            }
        });
        // EVENTO ENTER CODIGO - PANEL NUEVA VENTA
        cod_venta_TF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!"".equals(cod_venta_TF.getText())) {
                        String codigo = cod_venta_TF.getText();
                        productos = productos_sql.buscarProductos(codigo);
                        if (productos.getNombre() != null) {
                            precio_venta_TF.setText("" + productos.getPVP());
                            stock_venta_TF.setText("" + productos.getStock());
                            nom_venta_TF.setText("" + productos.getNombre());
                            cantidad_venta_TF.requestFocus();
                        } else {
                            limpiarVenta();
                            cod_venta_TF.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el codigo del productos");
                        cod_venta_TF.requestFocus();
                    }
                }
            }
        });

        // EVENTO ENTER CANTIDAD - PANEL NUEVA VENTA
        cantidad_venta_TF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!"".equals(cantidad_venta_TF.getText())) {
                        String codigo = cod_venta_TF.getText();
                        String nombre = nom_venta_TF.getText();
                        int cantidad = Integer.parseInt(cantidad_venta_TF.getText());
                        double precio = Double.parseDouble(precio_venta_TF.getText());
                        double subtotal = cantidad * precio;
                        int stock = Integer.parseInt(stock_venta_TF.getText());
                        if (stock >= cantidad) {
                            item = item + 1;
                            temporal = (DefaultTableModel) ventasTable.getModel();
                            for (int i = 0; i < ventasTable.getRowCount(); i++) {
                                if (ventasTable.getValueAt(i, 2).equals(nom_venta_TF.getText())) {
                                    JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                                    return;
                                }
                            }
                            ArrayList lista = new ArrayList();
                            lista.add(item);
                            lista.add(codigo);
                            lista.add(nombre);
                            lista.add(cantidad);
                            lista.add(precio);
                            lista.add(subtotal);
                            Object[] O = new Object[5];
                            O[0] = lista.get(1);
                            O[1] = lista.get(2);
                            O[2] = lista.get(3);
                            O[3] = lista.get(4);
                            O[4] = lista.get(5);
                            temporal.addRow(O);
                            ventasTable.setModel(temporal);
                            totalPagar();
                            actualizarStock();
                            limpiarVenta();
                            cod_venta_TF.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(null, "Stock no disponible");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
                    }
                }
            }
        });
        // EVENTO SOLO NUMEROS EN CANTIDAD - PANEL NUEVA VENTA
        cantidad_venta_TF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                eventos.numberKeyPress(e);
            }
        });
        // BOTON ELIMINAR VENTA - PANEL NUEVA VENTA
        eliminar_venta_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo = (DefaultTableModel) ventasTable.getModel();
                modelo.removeRow(ventasTable.getSelectedRow());
                totalPagar();
                cod_venta_TF.requestFocus();
            }
        });
        // EVENTO BUSCAR DNI - PANEL NUEVA VENTA
        dni_cli_venta_TF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!"".equals(dni_cli_venta_TF.getText())) {
                        String dni = String.valueOf(dni_cli_venta_TF.getText());
                        clientes = clientes_sql.buscarClientes(dni);
                        if (clientes.getNombre() != null) {
                            nom_cli_venta_TF.setText(clientes.getNombre() + " " + clientes.getApellido());
                        } else {
                            dni_cli_venta_TF.setText("");
                            JOptionPane.showMessageDialog(null, "El cliente no existe");
                        }
                    }
                }
            }
        });
        // BOTON GENERAR FACTURA - PANEL NUEVA VENTA
        generar_factura_venta_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ventasTable.getRowCount() > 0) {
                    if (!"".equals(nom_cli_venta_TF.getText())) {
                        registrarVenta();
                        registrarDetalle();
                        actualizarStock();
                        limpiarClienteVenta();
                        limpiarTablaVentas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes buscar un cliente");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No hay productos en la venta");
                }
            }
        }); // falta implementacion
        // BOTON CERRAR SESION
        cerrar_sesion_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
                login.setVisible(true);
            }
        });
        // BOTON PDF - PANEL HISTORIAL VENTA
        PDF_historial_venta_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (id_venta_historial_TF.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Selecciona una fila");
                } else {
                    ventas = ventas_sql.buscarVenta(Integer.parseInt(id_venta_historial_TF.getText()));
                    ventas_sql.pdfV(ventas.getId_venta(), ventas.getNombre_cli(), ventas.getTotal(), ventas.getEmpleado());
                }
            }
        });
        // BOTON GRAFICAR - PANEL REPORTES
        graficar_reporte_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reportes.Graficar(String.valueOf(fechaActual));
            }
        });



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
        //ruc_prod_TF.setText("");
        ruc_prod_CB.setSelectedItem(null);
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
        pass_emp_PF.setText("");
        conf_pass_emp_PF.setText("");
        rol_emp_CB.setSelectedItem(null);
    }
    private void limpiarVenta() {
        cod_venta_TF.setText("");
        nom_venta_TF.setText("");
        cantidad_venta_TF.setText("");
        stock_venta_TF.setText("");
        precio_venta_TF.setText("");
    }
    private void limpiarTablaVentas() {
        temporal = (DefaultTableModel) ventasTable.getModel();
        int fila = ventasTable.getRowCount();
        for (int i = 0; i < fila; i++) {
            temporal.removeRow(0);
        }
        iva_venta_TF.setText("");
        subotal_venta_TF.setText("");
        total_venta_TF.setText("");
    }
    private void limpiarClienteVenta() {
        dni_cli_venta_TF.setText("");
        nom_cli_venta_TF.setText("");
    }
    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
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
                ruc_prod_CB.setSelectedItem(rs.getString("ruc_prov"));
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
            String SQL = "SELECT * from empleados where dni_emp = ?";

            pst = con.prepareStatement(SQL);
            pst.setString(1,dni);
            rs = pst.executeQuery();
            String rol;
            while (rs.next()){
                dni_emp_TF.setText(dni);
                nom_emp_TF.setText(rs.getString("nom_emp"));
                apel_emp_TF.setText(rs.getString("apel_emp"));
                username__emp_TF.setText(rs.getString("username_emp"));
                telf_emp_TF.setText(rs.getString("telf_emp"));
                email_emp_TF.setText(rs.getString("email_emp"));
                pass_emp_PF.setText(rs.getString("pass_emp"));
                conf_pass_emp_PF.setText(rs.getString("conf_pass_emp"));
                rol = rs.getString("cod_rol");
                if (rol == "admi"){
                    rol_emp_CB.setSelectedItem("ADMINISTRADOR");
                } else if (rol == "bode") {
                    rol_emp_CB.setSelectedItem("BODEGUERO");
                } else {
                    rol_emp_CB.setSelectedItem("CAJERO");
                }
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

    // FUNCIONES PANEL NUEVA VENTA - VENTA
    private void totalPagar() {
        totalPagar = 0.00;
        totalFinal = 0.00;
        iva = 0.00;
        int numFila = ventasTable.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(ventasTable.getModel().getValueAt(i, 4)));
            totalPagar = totalPagar + cal;
            iva = iva + cal*0.12;
            totalFinal = totalPagar + iva;

        }
        subotal_venta_TF.setText(String.format("%.2f", totalPagar));
        iva_venta_TF.setText(String.format("%.2f",iva));
        total_venta_TF.setText(String.format("%.2f",totalFinal));
        //totalPagar = totalFinal;
    }
    private void registrarVenta() {
        String cliente = dni_cli_venta_TF.getText();
        String nom_cliente = nom_cli_venta_TF.getText();
        String empleado = nom_emp_inicio_Txt.getText();
        double monto = totalPagar;
        ventas.setDni_cli(cliente);
        ventas.setNombre_cli(nom_cliente);
        ventas.setEmpleado(empleado);
        ventas.setTotal(monto);
        ventas.setFecha(fecha_venta_TF.getText());
        ventas_sql.crearVenta(ventas);
    }
    private void registrarDetalle () {
        int id_venta = ventas_sql.idVenta();
        for (int i = 0; i < ventasTable.getRowCount(); i++) {
            String cod_prod = String.valueOf(ventasTable.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(ventasTable.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(ventasTable.getValueAt(i, 4).toString());
            detalle.setCod_prod(cod_prod);
            detalle.setCantidad(cantidad);
            detalle.setPrecio(precio);
            detalle.setId_detalle(id_venta);
            ventas_sql.registrarDetalle(detalle);

        }
        String cliente = dni_cli_venta_TF.getText();
        ventas_sql.pdfV(id_venta, cliente, totalPagar, nom_emp_inicio_Txt.getText());
    } // falta el pdf
    private void actualizarStock() {
        for (int i = 0; i < ventasTable.getRowCount(); i++) {
            String codigo = String.valueOf(ventasTable.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(ventasTable.getValueAt(i, 2).toString());
            productos = productos_sql.buscarCodigo(codigo);
            int StockActual = productos.getStock() - cantidad;
            ventas_sql.actualizarStock(StockActual, codigo);

        }
    }


    // FUNCIONES PARA VER LAS TABLAS
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
    public void cargarComboBoxProductos () {
        con = null;
        pst = null;
        String SQL = "SELECT ruc_prov FROM proveedores";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();

            while (rs.next()) {
                ruc_prod_CB.addItem(rs.getString("ruc_prov"));
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void verTablaClientes() {
        List<Clientes> verClientes = clientes_sql.verClientes();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(clientesHeader);
        clientesTable.setModel(modelo);
        Object[] ob = new Object[6];
        for (int i = 0; i < verClientes.size(); i++) {
            ob[0] = verClientes.get(i).getDni();
            ob[1] = verClientes.get(i).getNombre();
            ob[2] = verClientes.get(i).getApellido();
            ob[3] = verClientes.get(i).getDireccion();
            ob[4] = verClientes.get(i).getEmail();
            ob[5] = verClientes.get(i).getTelefono();
            modelo.addRow(ob);
        }
        clientesTable.setModel(modelo);
        JTableHeader header = clientesTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 110, 255));
        header.setForeground(Color.white);
    }
    public void verTablaProveedores() {
        List<Proveedores> verProveedores = proveedores_sql.verProveedores();//
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(proveedoresHeader);
        proveedoresTable.setModel(modelo);
        Object[] ob = new Object[5];
        for (int i = 0; i < verProveedores.size(); i++) {
            ob[0] = verProveedores.get(i).getRuc();
            ob[1] = verProveedores.get(i).getNombre();
            ob[2] = verProveedores.get(i).getTelefono();
            ob[3] = verProveedores.get(i).getDireccion();
            modelo.addRow(ob);
        }
        proveedoresTable.setModel(modelo);
        JTableHeader header = proveedoresTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 110, 255));
        header.setForeground(Color.white);

    }
    public void verTablaEmpleados () {
        List<Empleados> verEmpleados = empleados_sql.verEmpleados();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(empleadosHeader);
        empleadosTable.setModel(modelo);
        Object[] ob = new Object[9];
        for (int i = 0; i < verEmpleados.size(); i++) {
            ob[0] = verEmpleados.get(i).getDni();
            ob[1] = verEmpleados.get(i).getNombre();
            ob[2] = verEmpleados.get(i).getApellido();
            ob[3] = verEmpleados.get(i).getUsername();
            ob[4] = verEmpleados.get(i).getTelefono();
            ob[5] = verEmpleados.get(i).getEmail();
            ob[6] = verEmpleados.get(i).getRol();
            modelo.addRow(ob);
        }
        empleadosTable.setModel(modelo);
        JTableHeader header = empleadosTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 110, 255));
        header.setForeground(Color.white);
    }
    public void verTablaProductos () {
        List<Productos> verProductos = productos_sql.verProductos();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(productosHeader);
        productosTable.setModel(modelo);
        Object[] ob = new Object[6];
        for (int i = 0; i < verProductos.size(); i++) {
            ob[0] = verProductos.get(i).getCodigo();
            ob[1] = verProductos.get(i).getNombre();
            ob[2] = verProductos.get(i).getRuc_prov();
            ob[3] = verProductos.get(i).getDescripcion();
            ob[4] = verProductos.get(i).getStock();
            ob[5] = verProductos.get(i).getPVP();
            modelo.addRow(ob);
        }
        productosTable.setModel(modelo);
        JTableHeader header = productosTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 110, 255));
        header.setForeground(Color.white);
    }
    public void verTablaAjustes () {
        ajustes = productos_sql.buscarDatos();
        ruc_ajustes_TF.setText("" + ajustes.getRuc_farmacia());
        nom_ajustes_TF.setText("" + ajustes.getNom_farmacia());
        telf_ajustes_TF.setText("" + ajustes.getTelf_farmacia());
        direc_ajustes_TF.setText("" + ajustes.getDirec_farmacia());
        mensaje_ajustes_TF.setText("" + ajustes.getMensaje_farmacia());
    }
    public void verTablaVentas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(detalleDeFacturaHeader);
        ventasTable.setModel(modelo);
        JTableHeader header = ventasTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 110, 255));
        header.setForeground(Color.white);
    }
    public void verTablaVentasHistorial() {
        List<Ventas> verVentas = ventas_sql.verVentas();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(ventasHeader);
        historial_ventaTable.setModel(modelo);
        Object[] ob = new Object[4];
        for (int i = 0; i < verVentas.size(); i++) {
            ob[0] = verVentas.get(i).getId_venta();
            //ob[1] = verVentas.get(i).getDni_cli();
            ob[1] = verVentas.get(i).getNombre_cli();
            ob[2] = verVentas.get(i).getEmpleado();
            ob[3] = verVentas.get(i).getTotal();
            modelo.addRow(ob);
        }
        historial_ventaTable.setModel(modelo);
        JTableHeader header = historial_ventaTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 110, 255));
        header.setForeground(Color.white);
    }




}