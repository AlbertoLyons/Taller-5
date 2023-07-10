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

public class agregarLibro extends JFrame { // Hereda JFrame para su funcionalidad
    /**
     * The addForm
     */
    private JPanel addForm;
    /**
     * The isbnField
     */
    private JTextField isbnField;
    /**
     * The tituloField
     */
    private JTextField tituloField;
    /**
     * The autorField
     */
    private JTextField autorField;
    /**
     * The categoriaField
     */
    private JTextField categoriaField;
    /**
     * The paginasField
     */
    private JTextField paginasField;
    /**
     * The stockField
     */
    private JTextField stockField;
    /**
     * The agregarButton
     */
    private JButton agregarButton;
    /**
     * The volverButton
     */
    private JButton volverButton;
    /**
     * The txtAgregar
     */
    private JLabel txtAgregar;
    /**
     * The libros
     */
    private List<Libro> libros;
    /**
     * The instanciaInicio
     */
    private inicioSesion instanciaInicio;

    /**
     * The constructor
     * @param libros
     * @param instanciaInicioSesion
     */
    public agregarLibro(List<Libro> libros, inicioSesion instanciaInicioSesion) {
        this.libros = libros;
        this.instanciaInicio = instanciaInicioSesion;
        setContentPane(addForm);
        setTitle("Menu Agregar");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        // Boton para agregar el libro
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
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
     * Agrega un libro al txt libros con los parametros ingresados en la interfaz grafica, con el parametro del usuario ingresado
     */
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

            // Verifica si los parametros ingresados estan vacios
            if (!isbn.isEmpty() && !titulo.isEmpty() && !autor.isEmpty() && !categoria.isEmpty() && !paginasString.isEmpty() && !stockString.isEmpty()){
                // Verifica si el libro existe con los parametros ingresados
                if (!existe(isbn)){

                    boolean paginasCorrecto = false;
                    boolean stockCorrecto = false;

                    // Try para cada variable numerica, uno para paginas y otro para stock hasta que estos valores sean ingresados correctamente
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

                    // Si la variable paginas y stock fueron rellenados correctamente con valores numericos entra
                    if (paginasCorrecto && stockCorrecto){
                        // Creamos el nuevo libro en una variable auxiliar
                        Libro aux = new Libro(isbn,titulo,autor,categoria,paginas,stock);
                        // Lo agregamos a la lista principal de libros
                        instanciaInicio.agregarLibro(aux);
                        // Mandamos el libro auxiliar como parametro al subprograma que se encargara de agregarlo al txt
                        escrituraLibroNuevo(aux);
                        JOptionPane.showMessageDialog(addForm,"Libro agregado con exito","Operacion valida",JOptionPane.INFORMATION_MESSAGE);
                        clear();

                        // Si la pagina fue rellanada mal pero el stock bien, advertimos sobre las paginas
                    }else if (!paginasCorrecto && stockCorrecto){
                        JOptionPane.showMessageDialog(addForm,"Ingrese bien el dato de paginas para poder añadir el libro.","Error al añadir",JOptionPane.WARNING_MESSAGE);
                        paginasField.setText("");
                        // Si la pagina fue rellanada bien pero el stock mal, advertimos sobre las paginas
                    } else if (paginasCorrecto && !stockCorrecto) {
                        JOptionPane.showMessageDialog(addForm,"Ingrese bien el dato de stock para poder añadir el libro.","Error al añadir",JOptionPane.WARNING_MESSAGE);
                        stockField.setText("");
                        // Si ninguno de los datos fue ingresado bien, advertimos sobre esto
                    } else {
                        JOptionPane.showMessageDialog(addForm,"Ingrese bien los datos para poder añadir el libro.","Error al añadir",JOptionPane.WARNING_MESSAGE);
                        paginasField.setText("");
                        stockField.setText("");
                    }


                    // Si existe, advertimos sobre esto
                }else {
                    JOptionPane.showMessageDialog(addForm,"Ya existe un libro con el ISBN ingresado","Error al añadir",JOptionPane.WARNING_MESSAGE);
                    clear();
                }

                // Si alguno de los campos no ha sido rellenado, advertimos sobre esto
            }else {
                JOptionPane.showMessageDialog(addForm,"Rellene el(los) campo(s)","Error al añadir",JOptionPane.WARNING_MESSAGE);
            }

            // Hubo algun error al tratar de ingresar datos
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(addForm,"Error","Error al añadir",JOptionPane.WARNING_MESSAGE);
            clear();
        }
    }

    /**
     *  Elimina el campo ingresado en la interfaz grafica para volver a escribir
     */
    public void clear(){
        isbnField.setText("");
        tituloField.setText("");
        autorField.setText("");
        categoriaField.setText("");
        paginasField.setText("");
        stockField.setText("");
    }

    /**
     * Verifica si el libro ya existe
     * @param isbn
     * @return
     */
    public boolean existe(String isbn){
        for (Libro aux: libros){
            if (aux.getISBN().equals(isbn)){
                return true;
            }
        }
        return false;
    }

    /**
     * Añade el libro ingresado por el usuario al txt
     * @param libro
     */
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