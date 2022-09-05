package Modelo;

public class Ventas {
    private int id_venta;
    private String dni_cli;
    private String nombre_cli;
    private String empleado;
    private double total;
    private String fecha;

    public Ventas () {

    }
    // CONSTRUCTOR
    public Ventas(int id_venta, String dni_cli, String nombre_cli, String empleado, double total, String fecha) {
        this.id_venta = id_venta;
        this.dni_cli = dni_cli;
        this.nombre_cli = nombre_cli;
        this.empleado = empleado;
        this.total = total;
        this.fecha = fecha;
    }
    // GETTERS AND SETTERS
    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getDni_cli() {
        return dni_cli;
    }

    public void setDni_cli(String dni_cli) {
        this.dni_cli = dni_cli;
    }

    public String getNombre_cli() {
        return nombre_cli;
    }

    public void setNombre_cli(String nombre_cli) {
        this.nombre_cli = nombre_cli;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
