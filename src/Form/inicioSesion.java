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

public class inicioSesion extends JFrame {

    private JPanel registerForm;
    private JLabel rut;
    private JTextField rutField;
    private JLabel contrasenia;
    private JTextField contraseniaField;
    private JButton iniciarSesionButton;
    private JButton cerrarButton;
    private List<Usuario> usuarios;
    private List<Libro> libros;
    private Usuario usuarioIngresado;
    private List<TransaccionLibro> transacciones;
    final inicioSesion instanciaInicio = this;

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

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }
    public void iniciarSesion(){

        try {
            String rut = rutField.getText();
            String contrasenia = contraseniaField.getText();

            if (!rut.isEmpty() && !contrasenia.isEmpty()){
                boolean encontrado = false;

                for (Usuario aux: usuarios){
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
    public void transaccionPrestamo(String rut, String nombre, String apellido, String isbn, String tituloLibro) {
        TransaccionLibro transaccion = new TransaccionLibro(rut, nombre, apellido, isbn, tituloLibro, "Préstamo");
        String linea = transaccion.transaccionToString(transaccion);
        agregarLineaArchivoReservas(linea);
    }
    public void transaccionDevolucion(String rut, String nombre, String apellido, String isbn, String tituloLibro) {
        TransaccionLibro transaccion = new TransaccionLibro(rut, nombre, apellido, isbn, tituloLibro, "Devolución");
        String linea = transaccion.transaccionToString(transaccion);
        agregarLineaArchivoReservas(linea);
    }
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
    public void agregarLibro(Libro libro){
        this.libros.add(libro);
    }
    public void clear(){
        rutField.setText("");
        contraseniaField.setText("");
    }
    public void close(){
        System.exit(0);
    }
}
