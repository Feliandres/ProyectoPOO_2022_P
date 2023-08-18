public class EliminarClientes extends BaseOperacionClientes {

    @Override
    public boolean ejecutarOperacion(Clientes clientes) {
        String SQL = "DELETE FROM clientes WHERE dni_cli = ?";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, clientes.getDni());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        } finally {
            cerrarConexion();
        }
    }
}
