package src.services;
import src.Form.inicioSesion;
import src.models.Libro;
import src.models.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SistemaImpl implements Sistema{

    private inicioSesion inicioSesion;
    private List<Usuario> usuarios;
    private List<Libro> libros;
    private List<String> reservas;

    public SistemaImpl(){
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
    }

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

    @Override
    public void inicioPrograma() {
        this.inicioSesion = new inicioSesion(usuarios,libros);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Libro> getLibros() {
        return libros;
    }
}
