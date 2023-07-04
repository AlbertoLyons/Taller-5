package src.Form.menu;

import src.models.Libro;
import src.Form.inicioSesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class agregarLibro extends JFrame {
    private JPanel addForm;
    private JTextField isbnField;
    private JTextField tituloField;
    private JTextField autorField;
    private JTextField categoriaField;
    private JTextField paginasField;
    private JTextField stockField;
    private JButton agregarButton;
    private JButton volverButton;
    private JLabel txtAgregar;
    private List<Libro> libros;
    private inicioSesion instanciaInicio;

    public agregarLibro(List<Libro> libros, inicioSesion instanciaInicioSesion) {
        this.libros = libros;
        this.instanciaInicio = instanciaInicioSesion;
        setContentPane(addForm);
        setTitle("Menu Agregar");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void agregarLibro(){

        try {

            String isbn = isbnField.getText();
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            String categoria = categoriaField.getText();
            String paginasString = paginasField.getText();
            String stockString = stockField.getText();
            int paginas = 0;
            int stock = 0;

            if (!isbn.isEmpty() && !titulo.isEmpty() && !autor.isEmpty() && !categoria.isEmpty() && !paginasString.isEmpty() && !stockString.isEmpty()){
                if (!existe(isbn)){

                    boolean paginasCorrecto = false;
                    boolean stockCorrecto = false;

                    try {
                        paginas = Integer.parseInt(paginasString);
                        paginasCorrecto = true;
                    }catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(addForm,"El valor ingresado en el campo paginas debe ser numerico.");
                    }

                    try {
                        stock = Integer.parseInt(stockString);
                        stockCorrecto = true;
                    }catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(addForm,"El valor ingresado en el campo stock debe ser numerico.");
                    }

                    if (paginasCorrecto && stockCorrecto){
                        // Creamos el nuevo libro en una variable auxiliar
                        Libro aux = new Libro(isbn,titulo,autor,categoria,paginas,stock);
                        // Lo agregamos a la lista principal de libros
                        instanciaInicio.agregarLibro(aux);
                        // Mandamos el libro auxiliar como parametro al subprograma que se encargara de agregarlo al txt
                        escrituraLibroNuevo(aux);
                        JOptionPane.showMessageDialog(addForm,"Libro agregado con exito","Operacion valida",JOptionPane.INFORMATION_MESSAGE);
                        clear();

                    }else if (!paginasCorrecto && stockCorrecto){
                        JOptionPane.showMessageDialog(addForm,"Ingrese bien el dato de paginas para poder añadir el libro.");
                        paginasField.setText("");
                    } else if (paginasCorrecto && !stockCorrecto) {
                        JOptionPane.showMessageDialog(addForm,"Ingrese bien el dato de stock para poder añadir el libro.");
                        stockField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(addForm,"Ingrese bien los datos para poder añadir el libro.");
                        paginasField.setText("");
                        stockField.setText("");
                    }


                }else {
                    JOptionPane.showMessageDialog(addForm,"Ya existe un libro con el ISBN ingresado","Error al añadir",JOptionPane.WARNING_MESSAGE);
                    clear();
                }

            }else {
                JOptionPane.showMessageDialog(addForm,"Rellene el campo","Error de busqueda",JOptionPane.WARNING_MESSAGE);
            }

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(addForm,"Error");
            clear();
        }
    }

    public void clear(){
        isbnField.setText("");
        tituloField.setText("");
        autorField.setText("");
        categoriaField.setText("");
        paginasField.setText("");
        stockField.setText("");
    }

    public boolean existe(String isbn){
        for (Libro aux: libros){
            if (aux.getISBN().equals(isbn)){
                return true;
            }
        }
        return false;
    }

    public void escrituraLibroNuevo(Libro libro){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("libros.txt",true));
            String linea = libro.getISBN() + "," + libro.getTitulo() + "," + libro.getAutor() + "," + libro.getCategoria() + "," + libro.getCantPaginas() + "," + libro.getStock();
            writer.write(linea);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            System.out.println("[!] Ha ocurrido un error [!]");
            e.printStackTrace();
        }
    }
}