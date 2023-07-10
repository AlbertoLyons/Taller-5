package src.Form;

import src.Form.menu.agregarLibro;
import src.Form.menu.buscarLibro;
import src.Form.menu.devolverLibro;
import src.Form.menu.prestarLibro;
import src.models.Libro;
import src.models.TransaccionLibro;
import src.models.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class menuBiblioteca extends JFrame {

    /**
     * The menuForm
     */
    private JPanel menuForm;

    /**
     * The buscarLibroButton
     */
    private JButton buscarLibroButton;

    /**
     * The prestarLibroButton
     */
    private JButton prestarLibroButton;

    /**
     * The agregarNuevoLibroButton
     */
    private JButton agregarNuevoLibroButton;

    /**
     * The devolverLibroButton
     */
    private JButton devolverLibroButton;

    /**
     * The cerrarSesionButton
     */
    private JButton cerrarSesionButton;

    /**
     * The txtMenu
     */
    private JLabel txtMenu;

    /**
     * The lisbrosList
     */
    private List<Libro> librosList;

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
    public menuBiblioteca(List<Libro> libros, Usuario usuarioIngresado, inicioSesion instanciaInicioSesion) {
        this.librosList = libros;
        this.usuario = usuarioIngresado;
        this.instanciaInicio = instanciaInicioSesion;
        setContentPane(menuForm);
        setTitle("Menu biblioteca");
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

        // Codigo encargado de hacer que mientras se cierre esta ventana, se llame al subprograma cerrarSesion
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarSesion();
            }
        });

        // Boton para llamar a la interfaz de buscar libro
        buscarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibro buscarLibro = new buscarLibro(libros);
                buscarLibro.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
                setVisible(false);
                buscarLibro.setVisible(true);
            }
        });

        // Boton para llamar a la interfaz de prestar libro
        prestarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestarLibro prestarLibro = new prestarLibro(libros,usuarioIngresado,instanciaInicio);
                prestarLibro.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
                setVisible(false);
                prestarLibro.setVisible(true);
            }
        });

        // Boton para llamar a la interfaz de agregar libro
        agregarNuevoLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro agregarLibro = new agregarLibro(libros,instanciaInicio);
                agregarLibro.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
                setVisible(false);
                agregarLibro.setVisible(true);
            }
        });
        // Boton para llamar a la interfaz de devolver libro
        devolverLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLibro devolverLibro = new devolverLibro(libros,usuario,instanciaInicio);
                devolverLibro.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
                setVisible(false);
                devolverLibro.setVisible(true);
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    /**
     * Cierra la sesion de manera correcta
     */
    public void cerrarSesion(){
        // Llama al subprograma del primer formulario que se encarga de limpiar los campos
        instanciaInicio.clear();
        // Mensaje de "exito"
        JOptionPane.showMessageDialog(menuForm,"Sesion cerrada.");
        // Cierra la ventana
        dispose();
    }
}
