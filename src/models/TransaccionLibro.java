package src.models;

public class TransaccionLibro {

    private String rut;
    private String nombre;
    private String apellido;
    private String isbn;
    private String tituloLibro;
    private String tipoTransaccion;

    public TransaccionLibro(String rut, String nombre, String apellido, String isbn, String tituloLibro, String tipoTransaccion) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.isbn = isbn;
        this.tituloLibro = tituloLibro;
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getRut() {
        return rut;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTituloLibro() {
        return tituloLibro;
    }
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }
    public String transaccionToString(TransaccionLibro transaccion){
        String transaccionString = transaccion.getRut() + "," + transaccion.getNombre() + "," + transaccion.getApellido() + "," + transaccion.getIsbn() + "," + transaccion.getTituloLibro() + "," + transaccion.getTipoTransaccion();
        return transaccionString;
    }
}
