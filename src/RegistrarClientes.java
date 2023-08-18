public class RegistrarClientes extends BaseOperacionClientes {

    @Override
    public boolean ejecutarOperacion(Clientes clientes) {
        String SQL = "INSERT INTO clientes (dni_cli, nom_cli, apel_cli, direc_cli, email_cli, telf_cli) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, clientes.getDni());
            pst.setString(2, clientes.getNombre());
            pst.setString(3, clientes.getApellido());
            pst.setString(4, clientes.getDireccion());
            pst.setString(5, clientes.getEmail());
            pst.setString(6, clientes.getTelefono());
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
