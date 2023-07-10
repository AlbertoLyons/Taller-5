package src.Form;

import src.models.Libro;
import src.models.TransaccionLibro;
import src.models.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class inicioSesion extends JFrame { // Hereda JFrame para su funcionalidad
    /**
     * The registerForm
     */
    private JPanel registerForm;
    /**
     * The rut
     */
    private JLabel rut;
    /**
     * The rutField
     */
    private JTextField rutField;
    /**
     * The contrasenia
     */
    private JLabel contrasenia;
    /**
     * The contraseniaField
     */
    private JTextField contraseniaField;
    /**
     * The iniciarSesionButton
     */
    private JButton iniciarSesionButton;
    /**
     * The cerrarButton
     */
    private JButton cerrarButton;
    /**
     * The usuarios
     */
    private List<Usuario> usuarios;
    /**
     * The libros
     */
    private List<Libro> libros;
    /**
     * The usuarioIngresado
     */
    private Usuario usuarioIngresado;
    /**
     * The transacciones
     */
    private List<TransaccionLibro> transacciones;
    /**
     * The instanciaInicio
     */
    final inicioSesion instanciaInicio = this;

    /**
     * The constructor
     * @param usuarios
     * @param libros
     */
    public inicioSesion(List<Usuario> usuarios, List<Libro> libros){

        this.usuarios = usuarios;
        this.libros = libros;
        this.transacciones = new ArrayList<>();
        setContentPane(registerForm);
        setTitle("Iniciar sesion");
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Boton para iniciar sesion
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        // Boton para cerrar el programa
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }

    /**
     * Inicia sesion en el sistema con los datos ingresados en la interfaz para poder ingresar al sistema
     */
    public void iniciarSesion(){

        try {
            String rut = rutField.getText();
            String contrasenia = contraseniaField.getText();
            // Verifica que el rut y la contrasenia no esten vacios
            if (!rut.isEmpty() && !contrasenia.isEmpty()){
                boolean encontrado = false;
                // Verifica que el rut y contrasenia ingresados coincidan con los usuarios registrados en el usuarios.txt
                for (Usuario aux: usuarios){
                    // Si encuentra al usuario, se procede a hacer el inicio de sesion
                    if (aux.getRut().equals(rut)){
                        encontrado = true;
                        if (aux.getContrasenia().equals(contrasenia)){
                            usuarioIngresado = aux;
                            JOptionPane.showMessageDialog(registerForm,"Inicio de sesion exitoso. Bienvenid@ " + usuarioIngresado.getNombre(),"Sesion Iniciada",JOptionPane.INFORMATION_MESSAGE);
                            menuBiblioteca menuBiblioteca = new menuBiblioteca(this.libros,usuarioIngresado,instanciaInicio);
                            menuBiblioteca.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    setVisible(true);
                                }
                            });
                            setVisible(false);
                            menuBiblioteca.setVisible(true);
                            break;
                        }else {
                            JOptionPane.showMessageDialog(registerForm,"La contraseña es incorrecta. Intentelo nuevamente.","Error de inicio de sesion",JOptionPane.WARNING_MESSAGE);
                            contraseniaField.setText("");
                        }
                    }
                }
                if (!encontrado){
                    JOptionPane.showMessageDialog(registerForm,"El rut no fue encontrado.","Error de inicio de sesion",JOptionPane.WARNING_MESSAGE);
                    clear(); return;
                }
            }else {
                JOptionPane.showMessageDialog(registerForm,"Complete todos los campos.","Error de inicio de sesion",JOptionPane.WARNING_MESSAGE);
            }

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(registerForm,"[!] Ha ocurrido un error [!]","Error",JOptionPane.WARNING_MESSAGE);
            clear();
        }
    }

    /**
     * Genera una linea la cual con un tipo de transaccion prestamo, esta es enviada al subprograma que se encarga de agregarlas al txt
     * @param rut
     * @param nombre
     * @param apellido
     * @param isbn
     * @param tituloLibro
     */
    public void transaccionPrestamo(String rut, String nombre, String apellido, String isbn, String tituloLibro) {
        TransaccionLibro transaccion = new TransaccionLibro(rut, nombre, apellido, isbn, tituloLibro, "Préstamo");
        String linea = transaccion.transaccionToString(transaccion);
        agregarLineaArchivoReservas(linea);
    }

    /**
     * Genera una linea la cual con un tipo de transaccion devolucion, esta es enviada al subprograma que se encarga de agregarlas al txt
     * @param rut
     * @param nombre
     * @param apellido
     * @param isbn
     * @param tituloLibro
     */
    public void transaccionDevolucion(String rut, String nombre, String apellido, String isbn, String tituloLibro) {
        TransaccionLibro transaccion = new TransaccionLibro(rut, nombre, apellido, isbn, tituloLibro, "Devolución");
        String linea = transaccion.transaccionToString(transaccion);
        agregarLineaArchivoReservas(linea);
    }

    /**
     * Agrega una nueva linea al archivo reservas.txt
     * @param linea
     */
    public void agregarLineaArchivoReservas(String linea){

        String lineaNueva = linea;
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("reservas.txt",true));
            writer.write(linea);
            writer.newLine();
            writer.close();

        }catch (IOException e){
            System.out.println("Error al manipular el archivo: " + e.getMessage());
        }

    }

    /**
     * Agrega un libro a la lista libros
     * @param libro
     */
    public void agregarLibro(Libro libro){
        this.libros.add(libro);
    }

    /**
     * Limpia los campos rut y contrasenia
     */
    public void clear(){
        rutField.setText("");
        contraseniaField.setText("");
    }

    /**
     * Cierra el programa
     */
    public void close(){
        System.exit(0);
    }
}
