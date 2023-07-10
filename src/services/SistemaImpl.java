package src.services;
import src.Form.inicioSesion;
import src.models.Libro;
import src.models.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SistemaImpl implements Sistema{

    /**
     * The inicioSesion
     */
    private inicioSesion inicioSesion;
    /**
     * The usuarios
     */
    private List<Usuario> usuarios;
    /**
     * The libros
     */
    private List<Libro> libros;
    /**
     * The reservas
     */
    private List<String> reservas;

    /**
     * The constructor
     */
    public SistemaImpl(){
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
    }

    /**
     * Lee el archivo "libros.txt" y los agrega a la lista libros
     */
    public void leerArchivoLibros() {

        // Leer el archivo "libros.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("libros.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String isbn = chain[0];
                String title = chain[1];
                String author = chain[2];
                String category = chain[3];
                int copies = Integer.parseInt(chain[4]);
                int price = Integer.parseInt(chain[5]);

                // Guardamos en la estructura de datos
                Libro libroPorAgregar = new Libro(isbn,title,author,category,copies,price);
                this.libros.add(libroPorAgregar);

            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Lee el archivo "usuarios.txt" y los agrega a la lista usuarios
     */
    public void leerArchivoUsuarios() {

        // Leer el archivo "usuarios.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String rut = chain[0];
                String name = chain[1];
                String lastname = chain[2];
                String password = chain[3];

                // Guardamos en la estructura de datos
                Usuario usuarioPorAgregar = new Usuario(rut,name,lastname,password);
                this.usuarios.add(usuarioPorAgregar);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Inicia el programa llamando al creando una instancia del formulario inicioSesion
     */
    @Override
    public void inicioPrograma() {
        this.inicioSesion = new inicioSesion(usuarios,libros);
    }
}
