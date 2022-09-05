package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Modelo.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
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
    private JButton buscar_cli_BTN;
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
    private JTextField cod_venta_TF;
    private JTextField cantidad_venta_TF;
    private JTextField nom_venta_TF;
    private JTextField precio_venta_TF;
    private JTextField stock_venta_TF;
    private JTable ventasTable;
    private JTextField dni_cli_venta_TF;
    private JTextField nom_cli_venta_TF;
    private JTextField subotal_venta_TF;
    private JButton generar_factura_venta_BTN;
    private JTextField iva_venta_TF;
    private JTextField total_venta_TF;
    private JButton eliminar_venta_BTN;
    private JButton buscar_cli_venta_BTN;
    private JButton agregar_venta_BTN;
    private JButton buscar_prod_venta_BTN;
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
    private JButton buscar_prov_BTN;
    private JButton eliminar_prod_BTN;
    private JButton crear_prod_BTN;
    private JButton modificar_prod_BTN;
    private JButton guardar_prod_BTN;
    private JPanel ajustesPanel;
    private JButton guardar_emp_BTN;
    private JButton eliminar_emp_BTN;
    private JButton crear_emp_BTN;
    private JButton modificar_emp_BTN;
    // private JDateChooser Midate;


    // Variables de FECHA
/*    Calendar cld = Calendar.getInstance();
    Date fechaVenta = new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);*/

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
    String[] detalleDeFacturaHeader =  {"Codigo","Nombre","Cantidad","Descripcion","Total"};
    int fila = 0;
    double totalPagar = 0.00;
    double totalFinal = 0.00;
    double iva = 0.00;
    int item;

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

    public FarmaciaSistema (Empleados login) {
        setContentPane(mainPanel);
        setTitle("Farmacia Real Audiencia");
        setSize(800, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // centrar la pantalla


        verTablaAjustes();
        if (login.getRol().equals("bode")) {
            nuevaVentaButton.setEnabled(false);
            clientesButton.setEnabled(false);
            ventasTable.setEnabled(false);
            ajustesButton.setEnabled(false);
            reportesButton.setEnabled(false);
            empleadosButton.setEnabled(false);
            productosButton.setEnabled(true);
            proveedoresButton.setEnabled(true);
            nom_empTxT.setText(login.getNombre()+" "+login.getApellido());
        } else if (login.getRol().equals("caje")) {
            nuevaVentaButton.setEnabled(true);
            clientesButton.setEnabled(false);
            ventasTable.setEnabled(false);
            ajustesButton.setEnabled(false);
            reportesButton.setEnabled(false);
            empleadosButton.setEnabled(false);
            productosButton.setEnabled(false);
            proveedoresButton.setEnabled(false);
            nom_empTxT.setText(login.getNombre()+" "+login.getApellido());
        } else if (login.getRol().equals("admi")) {
            nuevaVentaButton.setEnabled(true);
            clientesButton.setEnabled(true);
            ventasTable.setEnabled(true);
            ajustesButton.setEnabled(true);
            reportesButton.setEnabled(true);
            empleadosButton.setEnabled(true);
            productosButton.setEnabled(true);
            proveedoresButton.setEnabled(true);
            nom_empTxT.setText(login.getNombre()+" "+login.getApellido());
        } else {
            nom_empTxT.setText(login.getNombre()+" "+login.getApellido());
        }
        JTableHeader header = ventasTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 110, 255));
        header.setForeground(Color.white);
        /*Midate = new JDateChooser(cld.getTime());*/
    }
    public FarmaciaSistema () {

        setContentPane(mainPanel);
        setTitle("Farmacia Real Audiencia");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // centrar la pantalla


        // BOTON CLIENTES
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar_cli_BTN.setEnabled(false);
                modificar_cli_BTN.setEnabled(false);
                LimpiarTable();
                verTablaClientes();
                limpiarClientes();
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
                pass_emp_TF.setEnabled(true);
                conf_pass_emp_TF.setEnabled(true);
                crudTabbedPanel.setSelectedIndex(5);
            }
        });
        // BOTON NUEVA VENTA
        nuevaVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTablaVentas();
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
                verTablaVentas();
                crudTabbedPanel.setSelectedIndex(6);
            }
        });
        // BOTON GUARDAR - PANEL EMPLEADOS
        guardar_emp_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nom_emp_TF.getText().equals("") || email_emp_TF.getText().equals("") || pass_emp_TF.getText().equals("")
                        || conf_pass_emp_TF.getText().equals("") || apel_emp_TF.getText().equals("") || username__emp_TF.getText().equals("")
                        || telf_emp_TF.getText().equals("") || dni_cli_TF.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todo los campos son requeridos");
                } else {
                    String email = email_emp_TF.getText();
                    String pass = pass_emp_TF.getText();
                    String nombre = nom_emp_TF.getText();
                    String apellido = apel_emp_TF.getText();
                    String telefono = telf_emp_TF.getText();
                    String dni = dni_cli_TF.getText();
                    String username = username__emp_TF.getText();
                    String conf_pass = conf_pass_emp_TF.getText();
                    String rol = rol_emp_CB.getSelectedItem().toString().substring(0,4).toLowerCase();
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
                    verTablaEmpleados();
                    JOptionPane.showMessageDialog(null, "Usuario Registrado");
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
                    if (!"".equals(cod_prod_TF.getText()) || !"".equals(descrip_prod_TF.getText()) || !"".equals(stock_prod_TF.getText()) || !"".equals(pvp_prod_TF.getText()) || !"".equals(nom_prod_TF.getText()) || !"".equals(ruc_prod_TF.getText())) {
                        productos.setCodigo(cod_prod_TF.getText());
                        productos.setNombre(nom_prod_TF.getText());
                        productos.setDescripcion(descrip_prod_TF.getText());
                        //Combo itemP = (Combo) cbxProveedorPro.getSelectedItem();
                        //pro.setProveedor(itemP.getId());
                        productos.setStock(Integer.parseInt(stock_prod_TF.getText()));
                        productos.setPVP(Double.parseDouble(pvp_prod_TF.getText()));
                        productos.setRuc_prov(ruc_prod_TF.getText());
                        productos_sql.modificarProductos(productos);
                        JOptionPane.showMessageDialog(null, "Producto Modificado");
                        LimpiarTable();
                        verTablaProductos();
                        limpiarProductos();
                        //cbxProveedorPro.removeAllItems();
                        //llenarProveedor();
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
                if (!"".equals(cod_prod_TF.getText()) || !"".equals(descrip_prod_TF.getText()) || !"".equals(ruc_prod_TF.getText()) || !"".equals(stock_prod_TF.getText()) || !"".equals(pvp_prod_TF.getText())) {
                    productos.setCodigo(cod_prod_TF.getText());
                    productos.setNombre(nom_prod_TF.getText());
                    //Combo itemP = (Combo) cbxProveedorPro.getSelectedItem();
                    productos.setRuc_prov(ruc_prod_TF.getText());
                    productos.setStock(Integer.parseInt(stock_venta_TF.getText()));
                    productos.setPVP(Double.parseDouble(pvp_prod_TF.getText()));
                    productos_sql.crearProductos(productos);
                    JOptionPane.showMessageDialog(null, "Productos Registrado");
                    LimpiarTable();
                    verTablaProductos();
                    limpiarProductos();
                    //cbxProveedorPro.removeAllItems();
                    //llenarProveedor();
                    modificar_prod_BTN.setEnabled(false);
                    eliminar_prod_BTN.setEnabled(false);
                    guardar_prod_BTN.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Los campos estan vacios");
                }
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

        // INTERACCION TABLA PRODUCTOS
        productosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaProductos();
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

        // INTERACCION TABLA EMPLEADOS
        empleadosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                interaccionTablaEmpleados();
            }
        });


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

        nom_venta_TF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                eventos.textKeyPress(e);
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

        cantidad_venta_TF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                eventos.numberKeyPress(e);
            }
        });

        eliminar_venta_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo = (DefaultTableModel) ventasTable.getModel();
                modelo.removeRow(ventasTable.getSelectedRow());
                totalPagar();
                cod_venta_TF.requestFocus();
            }
        });

        dni_cli_venta_TF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!"".equals(dni_cli_venta_TF.getText())) {
                        String dni = dni_cli_TF.getText();
                        clientes = clientes_sql.buscarClientes(dni);
                        if (clientes.getNombre() != null) {
                            nom_cli_venta_TF.setText("" + clientes.getNombre());
                        } else {
                            dni_cli_venta_TF.setText("");
                            JOptionPane.showMessageDialog(null, "El cliente no existe");
                        }
                    }
                }
            }
        });

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
                    JOptionPane.showMessageDialog(null, "Noy productos en la venta");
                }
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
    private void limpiarVenta() {
        cod_venta_TF.setText("");
        nom_venta_TF.setText("");
        cantidad_venta_TF.setText("");
        stock_venta_TF.setText("");
        precio_venta_TF.setText("");
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

    // FUNCIONES PANEL NUEVA VENTA - VENTA
    private void totalPagar() {
        totalPagar = 0.00;
        totalFinal = 0.00;
        iva = 0.00;
        int numFila = ventasTable.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(ventasTable.getModel().getValueAt(i, 4)));
            totalPagar = totalPagar + cal;
            totalFinal = totalPagar + (cal * 0.12);
            iva = totalFinal - totalPagar;
        }
        subotal_venta_TF.setText(String.format("%.2f", totalPagar));
        iva_venta_TF.setText(String.format("%.2f",iva));
        total_venta_TF.setText(String.format("%.2f",totalFinal));
    }
    private void registrarVenta() {
        String cliente = String.valueOf(dni_cli_venta_TF.getText());
        String empleado = nom_empTxT.getText();
        double monto = totalPagar;
        ventas.setDni_cli(cliente);
        ventas.setEmpleado(empleado);
        ventas.setTotal(monto);
        //ventas.setFecha(fechaActual);
        ventas_sql.crearVenta(ventas);
    }
    private void registrarDetalle () {
        int id_venta = ventas_sql.idVenta();
        for (int i = 0; i < ventasTable.getRowCount(); i++) {
            String cod_prod = String.valueOf(ventasTable.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(ventasTable.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(ventasTable.getValueAt(i, 3).toString());
            detalle.setCod_prod(cod_prod);
            detalle.setCantidad(cantidad);
            detalle.setPrecio(precio);
            detalle.setId_detalle(id_venta);
            ventas_sql.registrarDetalle(detalle);

        }
        String cliente = String.valueOf(dni_cli_venta_TF.getText());
        //ventas_sql.pdfV(id, cliente, Totalpagar, LabelVendedor.getText());
    } // falta el pdf
    private void actualizarStock() {
        for (int i = 0; i < ventasTable.getRowCount(); i++) {
            String codigo = String.valueOf(ventasTable.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(ventasTable.getValueAt(i, 1).toString());
            productos = productos_sql.buscarCodigo(codigo);
            int StockActual = productos.getStock() - cantidad;
            ventas_sql.actualizarStock(StockActual, codigo);

        }
    }
    private void limpiarTablaVentas() {
        temporal = (DefaultTableModel) ventasTable.getModel();
        int fila = ventasTable.getRowCount();
        for (int i = 0; i < fila; i++) {
            temporal.removeRow(0);
        }
    }
    private void limpiarClienteVenta() {
        dni_cli_venta_TF.setText("");
        nom_cli_venta_TF.setText("");
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
        List<Ventas> verVentas = ventas_sql.verVentas();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(detalleDeFacturaHeader);
        ventasTable.setModel(modelo);
        Object[] ob = new Object[4];
        for (int i = 0; i < verVentas.size(); i++) {
            ob[0] = verVentas.get(i).getId_venta();
            ob[1] = verVentas.get(i).getDni_cli();
            ob[2] = verVentas.get(i).getNombre_cli();
            ob[3] = verVentas.get(i).getEmpleado();
            ob[4] = verVentas.get(i).getTotal();
            modelo.addRow(ob);
        }
        ventasTable.setModel(modelo);
        JTableHeader header = ventasTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 110, 255));
        header.setForeground(Color.white);
    }
    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public static void main(String[] args) {
        FarmaciaSistema farmaciaSistema = new FarmaciaSistema();
    }

/*    private void createUIComponents() {
        Midate.setDateFormatString("dd/MM/yyyy");
        Midate.setDate(cld.getTime());
    }*/
}
