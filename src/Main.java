package src;

import src.services.SistemaImpl;

public class Main {
    public static void main(String[] args) {
        // Inicializamos una variable sistema para dar inicio al programa
        SistemaImpl sistema = new SistemaImpl();
        sistema.leerArchivoUsuarios();
        sistema.leerArchivoLibros();
        sistema.inicioPrograma();
    }
}