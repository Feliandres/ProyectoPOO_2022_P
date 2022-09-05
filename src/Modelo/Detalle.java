package Modelo;

public class Detalle {

    private int id_venta;
    private int id_detalle;
    private String cod_prod;
    private int cantidad;
    private String nom_prod;
    private String descrip_prod;
    private double precio;

    public Detalle () {

    }

    // CONSTRUCTOR
    public Detalle(int id_venta, int id_detalle, String cod_prod, int cantidad, String nom_prod, String descrip_prod, double precio) {
        this.id_venta = id_venta;
        this.id_detalle = id_detalle;
        this.cod_prod = cod_prod;
        this.cantidad = cantidad;
        this.nom_prod = nom_prod;
        this.descrip_prod = descrip_prod;
        this.precio = precio;
    }


    // GETTERS AND SETTERS
    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

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

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public String getDescrip_prod() {
        return descrip_prod;
    }

    public void setDescrip_prod(String descrip_prod) {
        this.descrip_prod = descrip_prod;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
