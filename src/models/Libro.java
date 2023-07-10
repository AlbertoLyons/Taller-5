package src.models;

public class Libro {
    /**
     * The ISBN.
     */
    private String ISBN;
    /**
     * The titulo
     */
    private String titulo;
    /**
     * The autor
     */
    private String autor;
    /**
     * The categoria
     */
    private String categoria;
    /**
     * The cantPaginas
     */
    private int cantPaginas;
    /**
     * The stock
     */
    private int stock;

    /**
     * The constructor
     * @param ISBN
     * @param titulo
     * @param autor
     * @param categoria
     * @param cantPaginas
     * @param stock
     */
    public Libro(String ISBN, String titulo, String autor, String categoria, int cantPaginas, int stock) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.cantPaginas = cantPaginas;
        this.stock = stock;
    }

    /**
     * @return ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @return titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @return categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @return cantPaginas
     */
    public int getCantPaginas() {
        return cantPaginas;
    }

    /**
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Actualiza el valor del stock dado uno como parametro
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

}
