package src.Form.menu;


import src.models.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class buscarLibro extends JFrame { // Hereda JFrame para su funcionalidad
    /**
     * The isbnField
     */
    private JTextField isbnField;
    /**
     * The buscarButton
     */
    private JButton buscarButton;
    /**
     * The searchForm
     */
    private JPanel searchForm;
    /**
     * The tituloBuscado
     */
    private JLabel tituloBuscado;
    /**
     * The isbn
     */
    private JLabel isbn;
    /**
     * The autorBuscado
     */
    private JLabel autorBuscado;
    /**
     * The categoriaBuscada
     */
    private JLabel categoriaBuscada;
    /**
     * The paginasBuscada
     */
    private JLabel paginasBuscada;
    /**
     * The copiasBuscada
     */
    private JLabel copiasBuscada;
    /**
     * The LIMPIARButton
     */
    private JButton LIMPIARButton;
    /**
     * The volverButton
     */
    private JButton volverButton;
    /**
     * The txtBuscar
     */
    private JLabel txtBuscar;
    /**
     * The libros
     */
    private List<Libro> libros;
    /**
     * The constructor
     * @param libros
     */

    public buscarLibro(List<Libro> libros) {

        this.libros = libros;
        setContentPane(searchForm);
        setTitle("Menu Buscar");
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

        // Con este codigo indicamos que cuando presione la X superior derecha, se cierre la ventana,
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Boton para buscar el libro
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarIsbn();
            }
        });
        // Boton para limpiar todos los datos buscados
        LIMPIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
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
     * Busca el libro ingresado por el usuario en la interfaz grafica
     */
    public void buscarIsbn(){

        try {
            String isbn = isbnField .getText();
            // Verifica si el isbn ingresado esta vacio
            if (!isbn.isEmpty()){
                boolean encontrado = false;
                for (Libro aux: libros){
                    // Si lo encuentra, se procede a desplegar los datos
                    if (aux.getISBN().equals(isbn)){
                        encontrado = true;
                        tituloBuscado.setText(aux.getTitulo());
                        autorBuscado.setText(aux.getAutor());
                        categoriaBuscada.setText(aux.getCategoria());
                        paginasBuscada.setText(Integer.toString(aux.getCantPaginas()));
                        copiasBuscada.setText(Integer.toString(aux.getStock()));
                    }
                }
                // Se despliega un mensaje al usuario de que el isbn buscado no fue encontrado
                if (!encontrado){
                    JOptionPane.showMessageDialog(searchForm,"El ISBN no fue encontrado","Error de busqueda",JOptionPane.WARNING_MESSAGE);
                    clearIsbn(); return;
                }
            }else {
                JOptionPane.showMessageDialog(searchForm,"Rellene el campo","Error de busqueda",JOptionPane.WARNING_MESSAGE);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(searchForm,"Error");
            clearIsbn();
        }
    }
    /**
     *  Elimina el campo ingresado en la interfaz grafica para volver a escribir
     */
    public void clearIsbn(){
        isbnField.setText("");
    }

    /**
     * Elimina todos los datos (campos) de algun libro ya buscado
     */
    public void clearAll(){
        clearIsbn();
        tituloBuscado.setText("");
        autorBuscado.setText("");
        categoriaBuscada.setText("");
        paginasBuscada.setText("");
        copiasBuscada.setText("");
    }

}
