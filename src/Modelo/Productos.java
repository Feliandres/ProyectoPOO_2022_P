package Modelo;

public class Productos {

    private String codigo;
    private String nombre;
    private String ruc_prov;
    private String descripcion;
    private int stock;
    private double PVP;

    public Productos () {

    }

    // CONSTRUCTOR
    public Productos(String codigo, String nombre, String ruc_prov, String descripcion, int stock, double PVP) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ruc_prov = ruc_prov;
        this.descripcion = descripcion;
        this.stock = stock;
        this.PVP = PVP;
    }

    // GETTERS AND SETTERS
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc_prov() {
        return ruc_prov;
    }

    public void setRuc_prov(String ruc_prov) {
        this.ruc_prov = ruc_prov;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPVP() {
        return PVP;
    }

    public void setPVP(double PVP) {
        this.PVP = PVP;
    }
}
