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
    private JPanel menuForm;
    private JButton buscarLibroButton;
    private JButton prestarLibroButton;
    private JButton agregarNuevoLibroButton;
    private JButton devolverLibroButton;
    private JButton cerrarSesionButton;
    private JLabel txtMenu;
    private src.Form.menu.buscarLibro buscarLibro;
    private List<Libro> librosList;
    private List<TransaccionLibro> transacciones;
    private Usuario usuario;
    private inicioSesion instanciaInicio;
    public menuBiblioteca(List<Libro> libros, Usuario usuarioIngresado, inicioSesion instanciaInicioSesion) {
        this.librosList = libros;
        this.usuario = usuarioIngresado;
        this.instanciaInicio = instanciaInicioSesion;
        setContentPane(menuForm);
        setTitle("Menu biblioteca");
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarSesion();
            }
        });

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

    public void cerrarSesion(){
        instanciaInicio.clear();
        JOptionPane.showMessageDialog(menuForm,"Sesion cerrada.");
        dispose();
    }
}
