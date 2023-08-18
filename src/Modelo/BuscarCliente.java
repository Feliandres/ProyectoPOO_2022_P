public class BuscarClientes extends BaseOperacionClientes {

    @Override
    public boolean ejecutarOperacion(Clientes clientes) {
        String SQL = "SELECT * FROM clientes WHERE dni_cli = ?";
        try {
            con = conectar.getConnection();
            pst = con.prepareStatement(SQL);
            pst.setString(1, clientes.getDni());
            rs = pst.executeQuery();

            if (rs.next()) {
                clientes.setNombre(rs.getString("nom_cli"));
                clientes.setApellido(rs.getString("apel_cli"));
                clientes.setDireccion(rs.getString("direc_cli"));
                clientes.setEmail(rs.getString("email_cli"));
                clientes.setTelefono(rs.getString("telf_cli"));
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        } finally {
            cerrarConexion();
        }
    }
