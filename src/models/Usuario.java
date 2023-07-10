package src.models;

public class Usuario {

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
     * The contrasenia
     */
    private String contrasenia;

    /**
     * The constructor
     * @param rut
     * @param nombre
     * @param apellido
     * @param contrasenia
     */
    public Usuario(String rut, String nombre, String apellido, String contrasenia) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
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
     * @return contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }
}
