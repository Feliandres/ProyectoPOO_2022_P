package Modelo;

public class Ajustes {
    private int id_farmacia;
    private String ruc_farmacia;
    private String nom_farmacia;
    private String telf_farmacia;
    private String direc_farmacia;
    private String mensaje_farmacia;

    public Ajustes () {

    }

    // CONSTRUCTOR
    public Ajustes(int id_farmacia, String ruc_farmacia, String nom_farmacia, String telf_farmacia, String direc_farmacia, String mensaje_farmacia) {
        this.id_farmacia = id_farmacia;
        this.ruc_farmacia = ruc_farmacia;
        this.nom_farmacia = nom_farmacia;
        this.telf_farmacia = telf_farmacia;
        this.direc_farmacia = direc_farmacia;
        this.mensaje_farmacia = mensaje_farmacia;
    }

    // GETTERS AND SETTERS
    public int getId_farmacia() {
        return id_farmacia;
    }

    public void setId_farmacia(int id_farmacia) {
        this.id_farmacia = id_farmacia;
    }

    public String getRuc_farmacia() {
        return ruc_farmacia;
    }

    public void setRuc_farmacia(String ruc_farmacia) {
        this.ruc_farmacia = ruc_farmacia;
    }

    public String getNom_farmacia() {
        return nom_farmacia;
    }

    public void setNom_farmacia(String nom_farmacia) {
        this.nom_farmacia = nom_farmacia;
    }

    public String getTelf_farmacia() {
        return telf_farmacia;
    }

    public void setTelf_farmacia(String telf_farmacia) {
        this.telf_farmacia = telf_farmacia;
    }

    public String getDirec_farmacia() {
        return direc_farmacia;
    }

    public void setDirec_farmacia(String direc_farmacia) {
        this.direc_farmacia = direc_farmacia;
    }

    public String getMensaje_farmacia() {
        return mensaje_farmacia;
    }

    public void setMensaje_farmacia(String mensaje_farmacia) {
        this.mensaje_farmacia = mensaje_farmacia;
    }
}
