package Modelo;

public class Empleados {

    private String dni;
    private String nombre;
    private String apellido;
    private String username;
    private String telefono;
    private String email;
    private String password;
    private String confirm_password;
    private String rol;

    public Empleados () {

    }

    // CONSTRUCTOR
    public Empleados(String dni, String nombre, String apellido, String username, String telefono, String email, String password, String confirm_password, String rol) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.rol = rol;
    }

    // GETTERS AND SETTERS
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}