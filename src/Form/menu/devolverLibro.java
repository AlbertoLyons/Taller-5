package src.Form.menu;

import src.models.Libro;
import src.models.TransaccionLibro;
import src.models.Usuario;
import src.Form.inicioSesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class devolverLibro extends JFrame{ // Hereda JFrame para su funcionalidad
    /**
     * The returnForm
     */
    private JPanel returnForm;
    /**
     * The isbnField
     */
    private JTextField isbnField;
    /**
     * The devolverButton
     */
    private JButton devolverButton;
    /**
     * The isbn
     */
    private JLabel isbn;
    /**
     * The volverButton
     */
    private JButton volverButton;
    /**
     * The txtDevolver
     */
    private JLabel txtDevolver;
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
     * The transacciones
     */
    private List<TransaccionLibro> transacciones;

    /**
     * The constructor
     * @param libros
     * @param usuario
     * @param instanciaInicioSesion
     */
    public devolverLibro(List<Libro> libros, Usuario usuario, inicioSesion instanciaInicioSesion) {
        this.libros = libros;
        this.usuario = usuario;
        this.instanciaInicio = instanciaInicioSesion;
        setContentPane(returnForm);
        setTitle("Menu Devolver");
        setSize(400,400);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Boton para devolver el libro
        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolucionLibro();
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
     * Devuelve el libro con los parametros ingresados en la interfaz grafica, con el parametro del usuario ingresado
     */
    public void devolucionLibro(){
        try {
            String isbn = isbnField .getText();
            // Verifica si el isbn ingresado esta vacio
            if (!isbn.isEmpty()){
                boolean encontrado = false;
                //Busca si el libro coincide con el que existe
                for (Libro aux: libros){
                    // Si lo encuentra, se procede a hacer la devolucion
                    if (aux.getISBN().equals(isbn)){
                        encontrado = true;
                        aux.setStock(aux.getStock()+1);

                        String rut = usuario.getRut();
                        String nombre = usuario.getNombre();
                        String apellido = usuario.getApellido();
                        String titulo = aux.getTitulo();
                        // Crea el registro de la devolucion en el txt
                        instanciaInicio.transaccionDevolucion(rut,nombre,apellido,isbn,titulo);
                        // Se sobreescribe en el archivo libros.txt el stock del libro
                        overWriteData(libros);
                        JOptionPane.showMessageDialog(returnForm,"Devolucion realizada con exito","Devolucion valida",JOptionPane.INFORMATION_MESSAGE);
                        clear();
                    }
                }

                if (!encontrado){
                    JOptionPane.showMessageDialog(returnForm,"Dentro del sistema no figura un libro con el ISBN " + isbn + " como prestado.","Error de busqueda",JOptionPane.WARNING_MESSAGE);
                    clear(); return;
                }



            }else {
                JOptionPane.showMessageDialog(returnForm,"Rellene el campo","Error de busqueda",JOptionPane.WARNING_MESSAGE);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(returnForm,"Error");
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
     * Sobreescribe el archivo libros.txt para añadirle un stock al libro
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
