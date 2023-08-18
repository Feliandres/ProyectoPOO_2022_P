public class ModificarClientes extends BaseOperacionClientes {

    @Override
    public boolean ejecutarOperacion(Clientes clientes) {
        String SQL = "UPDATE clientes SET nom_cli = ?, apel_cli = ?, direc_cli = ?, email_cli = ?, telf_cli = ? WHERE dni_cli = ?";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, clientes.getNombre());
            pst.setString(2, clientes.getApellido());
            pst.setString(3, clientes.getDireccion());
            pst.setString(4, clientes.getEmail());
            pst.setString(5, clientes.getTelefono());
            pst.setString(6, clientes.getDni());
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
