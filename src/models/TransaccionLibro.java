package src.models;

public class TransaccionLibro {

    /**
     * The rut
     */
    private String rut;

    /**
     * The nombre
     */
    private String nombre;

    /**
     * The apellido
     */
    private String apellido;

    /**
     * The isbn
     */
    private String isbn;

    /**
     * The tituloLibro
     */
    private String tituloLibro;

    /**
     * The tipoTransaccion
     */
    private String tipoTransaccion;

    /**
     * The constructor
     * @param rut
     * @param nombre
     * @param apellido
     * @param isbn
     * @param tituloLibro
     * @param tipoTransaccion
     */
    public TransaccionLibro(String rut, String nombre, String apellido, String isbn, String tituloLibro, String tipoTransaccion) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.isbn = isbn;
        this.tituloLibro = tituloLibro;
        this.tipoTransaccion = tipoTransaccion;
    }

    /**
     * @return rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @return tituloLibro
     */
    public String getTituloLibro() {
        return tituloLibro;
    }

    /**
     * @return tipoTransaccion
     */
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    /**
     * Obtiene los atributos de la clase TransaccionLibro y forma un string
     * @param transaccion
     * @return transaccion
     */
    public String transaccionToString(TransaccionLibro transaccion){
        String transaccionString = transaccion.getRut() + "," + transaccion.getNombre() + "," + transaccion.getApellido() + "," + transaccion.getIsbn() + "," + transaccion.getTituloLibro() + "," + transaccion.getTipoTransaccion();
        return transaccionString;
    }
}
