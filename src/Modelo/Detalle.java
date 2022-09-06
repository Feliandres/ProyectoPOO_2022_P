package Modelo;

public class Detalle {


    private int id_detalle;
    private String cod_prod;
    private int cantidad;
    private double precio;
    private int id_venta;

    public Detalle () {

    }
    // CONSTRUCTOR
    public Detalle(int id_detalle, String cod_prod, int cantidad, double precio, int id_venta) {
        this.id_detalle = id_detalle;
        this.cod_prod = cod_prod;
        this.cantidad = cantidad;
        this.precio = precio;
        this.id_venta = id_venta;
    }

    // GETTERS AND SETTERS
    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(String cod_prod) {
        this.cod_prod = cod_prod;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
}
