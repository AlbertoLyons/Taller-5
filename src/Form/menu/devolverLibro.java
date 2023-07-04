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

public class devolverLibro extends JFrame{
    private JPanel returnForm;
    private JTextField isbnField;
    private JButton devolverButton;
    private JLabel isbn;
    private JButton volverButton;
    private JLabel txtDevolver;
    private List<Libro> libros;
    private Usuario usuario;
    private inicioSesion instanciaInicio;
    private List<TransaccionLibro> transacciones;
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
        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolucionLibro();
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void devolucionLibro(){
        try {
            String isbn = isbnField .getText();
            if (!isbn.isEmpty()){
                boolean encontrado = false;
                for (Libro aux: libros){

                    if (aux.getISBN().equals(isbn)){
                        encontrado = true;
                        aux.setStock(aux.getStock()+1);

                        String rut = usuario.getRut();
                        String nombre = usuario.getNombre();
                        String apellido = usuario.getApellido();
                        String titulo = aux.getTitulo();
                        instanciaInicio.transaccionDevolucion(rut,nombre,apellido,isbn,titulo);
                        overwriteData(libros);
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
