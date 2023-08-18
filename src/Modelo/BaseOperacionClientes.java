public abstract class BaseOperacionClientes {

    protected Connection con;
    protected Conexion conectar = new Conexion();
    protected PreparedStatement pst;
    protected ResultSet rs;

    public abstract boolean ejecutarOperacion(Clientes clientes);

    protected void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
