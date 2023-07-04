package src.models;

public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private String categoria;
    private int cantPaginas;
    private int stock;

    public Libro(String ISBN, String titulo, String autor, String categoria, int cantPaginas, int stock) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.cantPaginas = cantPaginas;
        this.stock = stock;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantPaginas() {
        return cantPaginas;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /*
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Titulo    : ").append(this.titulo).append("\n");
        sb.append("Autor     : ").append(this.autor).append("\n");
        sb.append("Categoria : ").append(this.categoria).append("\n");
        sb.append("Numero de paginas : ").append(this.cantPaginas).append("\n");
        sb.append("Numero de copias : ").append(this.stock).append("\n");
        sb.append("\n");
        return sb.toString();
    }
     */
}
