package Vista;

import Modelo.Conexion;
import Modelo.Empleados;
import Modelo.Empleados_SQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login extends JFrame {
    private JPanel loginPanel;
    private JButton inicia_sesionBTN;
    private JLabel Login_PNG;
    private JLabel Email_PNG;
    private JLabel Password_PNG;
    private JButton registrarseBTN;
    private JPasswordField passwordTF;
    private JLabel passwordTxt;
    private JLabel emailTxt;
    private JTextField email_TF;
    private JPanel Titulo_Panel;
    private JLabel rolTxt;
    private JLabel accesoTxt;
    private JPanel Logo_Panel;
    private JPanel Subtitulo_Panel;
    private JPanel Registrarse_Panel;
    private JPanel iniciar_sesion_Panel;
    private JPanel Campos_Panel;
    private JButton salirButton;
    public JComboBox rolCB;

    // INICIALIZAR VARIABLES
    Empleados empleados = new Empleados();
    Empleados_SQL empleados_sql = new Empleados_SQL();
    // VARIABLES DE CONEXION Y CONNECTION
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public static String rol1,nombre,apellido,email1,username,dni,telefono;

    public Login () {

        setContentPane(loginPanel);
        setTitle("Login");
        setSize(460,560);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // centrar la pantalla
        cargarComboBox();

        // BOTON SALIR
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.this.dispose();
                Login.this.setVisible(false);
                Login.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        // BOTON INICIAR SESION
        inicia_sesionBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validar();
            }
        });
    }

    public void validar () {
        String email = email_TF.getText();
        String pass = String.valueOf(passwordTF.getPassword());
        String rol = String.valueOf(rolCB.getSelectedItem()).toLowerCase().substring(0,4);
        //System.out.println(rol);
        if (!"".equals(email) || !"".equals(pass)) {
            empleados = empleados_sql.login(email, pass, rol);
            // System.out.println(email + pass + rol);
            if (empleados.getEmail()!= null && empleados.getPassword() != null) {
                JOptionPane.showMessageDialog(null,"Inicio de Sesion Correcto");

                rol1 = empleados.getRol();
                nombre = empleados.getNombre();
                apellido = empleados.getApellido();
                email1 = empleados.getEmail();
                telefono = empleados.getTelefono();
                username = empleados.getUsername();
                dni = empleados.getDni();
                new FarmaciaSistema(rol1,nombre,apellido,username,dni,telefono,email1);
                dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Correo, Password o Rol incorrectos");
            }
        }

    }
    public void cargarComboBox () {
        String SQL = "SELECT * FROM roles";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                rolCB.addItem(rs.getString(2).toUpperCase());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    // CAMBIAR ICONO DE JOPTIONPANE
    public Icon icono (String path, int widht, int height) {

        Icon img = new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(widht,height, Image.SCALE_SMOOTH));
        return img;
    }



/*    public static void main(String[] args) {
        Login login = new Login();
    }*/

}