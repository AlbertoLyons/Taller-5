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

public class prestarLibro extends JFrame {
    private JPanel provideForm;
    private JLabel isbn;
    private JTextField isbnField;
    private JButton prestamoButton;
    private JButton volverButton;
    private JLabel txtPrestar;
    private List<Libro> libros;
    private Usuario usuario;
    private inicioSesion instanciaInicio;

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

        prestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestamoLibro();
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void prestamoLibro(){

        try {
            String isbn = isbnField .getText();
            if (!isbn.isEmpty()){

                boolean encontrado = false;
                for (Libro aux: libros){

                    if (aux.getISBN().equals(isbn)){
                        encontrado = true;

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
                        instanciaInicio.transaccionPrestamo(rut,nombre,apellido,isbn,titulo);
                        overwriteData(libros);
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

    public void clear(){
        isbnField.setText("");
    }

    public void overwriteData(List<Libro> libros){

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
