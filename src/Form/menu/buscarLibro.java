package src.Form.menu;


import src.models.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class buscarLibro extends JFrame {
    private JTextField isbnField;
    private JButton buscarButton;
    private JPanel searchForm;
    private JLabel tituloBuscado;
    private JLabel isbn;
    private JLabel autorBuscado;
    private JLabel categoriaBuscada;
    private JLabel paginasBuscada;
    private JLabel copiasBuscada;
    private JButton LIMPIARButton;
    private JButton volverButton;
    private JLabel txtBuscar;
    private List<Libro> libros;

    public buscarLibro(List<Libro> libros) {

        this.libros = libros;
        setContentPane(searchForm);
        setTitle("Menu Buscar");
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

        // Con este codigo indicamos que cuando presione la X superior derecha, se cierre la ventana,
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarIsbn();
            }
        });
        LIMPIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void buscarIsbn(){

        try {
            String isbn = isbnField .getText();
            if (!isbn.isEmpty()){
                boolean encontrado = false;
                for (Libro aux: libros){
                    if (aux.getISBN().equals(isbn)){
                        encontrado = true;
                        tituloBuscado.setText(aux.getTitulo());
                        autorBuscado.setText(aux.getAutor());
                        categoriaBuscada.setText(aux.getCategoria());
                        paginasBuscada.setText(Integer.toString(aux.getCantPaginas()));
                        copiasBuscada.setText(Integer.toString(aux.getStock()));
                    }
                }
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

    public void clearIsbn(){
        isbnField.setText("");
    }

    public void clearAll(){
        clearIsbn();
        tituloBuscado.setText("");
        autorBuscado.setText("");
        categoriaBuscada.setText("");
        paginasBuscada.setText("");
        copiasBuscada.setText("");
    }


}
