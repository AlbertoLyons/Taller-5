package src.Form.menu;

import src.models.Libro;
import src.models.Usuario;
import src.Form.inicioSesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class prestarLibro extends JFrame { // Hereda JFrame para su funcionalidad
    /**
     * The provideForm
     */
    private JPanel provideForm;
    /**
     * The isbn
     */
    private JLabel isbn;
    /**
     * The isbnField
     */
    private JTextField isbnField;
    /**
     * The prestamoButton
     */
    private JButton prestamoButton;
    /**
     * The volverButton
     */
    private JButton volverButton;
    /**
     * The txtPrestar
     */
    private JLabel txtPrestar;
    /**
     * The libros
     */
    private List<Libro> libros;
    /**
     * The usuario
     */
    private Usuario usuario;
    /**
     * The instanciaInicio
     */
    private inicioSesion instanciaInicio;

    /**
     * The constructor
     * @param libros
     * @param usuarioIngresado
     * @param instanciaInicioSesion
     */
    public prestarLibro(List<Libro> libros, Usuario usuarioIngresado, inicioSesion instanciaInicioSesion) {
        this.libros = libros;
        this.usuario = usuarioIngresado;
        this.instanciaInicio = instanciaInicioSesion;
        setContentPane(provideForm);
        setTitle("Menu Prestar");
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Boton para realizar el prestamo
        prestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestamoLibro();
            }
        });
        // Boton para volver al menu principal
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Realiza el prestamo del libro con los parametros ingresados por el usuario y con los parametros de inicio de sesion para el registro
     */
    public void prestamoLibro(){

        try {
            String isbn = isbnField .getText();
            // Verifica si el isbn esta vacio
            if (!isbn.isEmpty()){

                boolean encontrado = false;
                for (Libro aux: libros){
                    // Si lo encuentra, se procede a hacer el prestamo del libro
                    if (aux.getISBN().equals(isbn)){
                        encontrado = true;
                        // Verifica si el libro tiene stock
                        if (aux.getStock() <= 0){
                            JOptionPane.showMessageDialog(provideForm,"El libro solicitado no cuenta con disponibilidad","Prestamo invalido",JOptionPane.INFORMATION_MESSAGE);
                            clear();
                            break;
                        }
                        aux.setStock(aux.getStock()-1);

                        String rut = usuario.getRut();
                        String nombre = usuario.getNombre();
                        String apellido = usuario.getApellido();
                        String titulo = aux.getTitulo();
                        // Crea el registro del prestamo en reservas.txt
                        instanciaInicio.transaccionPrestamo(rut,nombre,apellido,isbn,titulo);
                        // Sobreescribe en el archivo libros.txt el stock del libro
                        overWriteData(libros);
                        JOptionPane.showMessageDialog(provideForm,"Prestamo realizado con exito","Prestamo valido",JOptionPane.INFORMATION_MESSAGE);
                        clear();
                    }
                }
                if (!encontrado){
                    JOptionPane.showMessageDialog(provideForm,"No fue encontrado un libro con el ISBN ingresado","Error de busqueda",JOptionPane.WARNING_MESSAGE);
                    clear(); return;
                }
            }else {
                JOptionPane.showMessageDialog(provideForm,"Rellene el campo","Error de busqueda",JOptionPane.WARNING_MESSAGE);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(provideForm,"Error");
            clear();
        }
    }

    /**
     * Elimina el campo ingresado para volver a escribir
     */
    public void clear(){
        isbnField.setText("");
    }

    /**
     * Sobreescribe el archivo libros.txt para eliminarle un stock al libro prestado
     * @param libros
     */
    public void overWriteData(List<Libro> libros){

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("libros.txt"));

            for (Libro aux: libros){
                String linea = aux.getISBN() + "," + aux.getTitulo() + "," + aux.getAutor() + "," + aux.getCategoria() + "," + aux.getCantPaginas() + "," + aux.getStock();
                writer.write(linea);
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            System.out.println("[!] Ha ocurrido un error [!]");
            e.printStackTrace();
        }

    }
}
