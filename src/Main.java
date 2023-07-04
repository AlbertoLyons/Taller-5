package src;

import src.services.SistemaImpl;

public class Main {
    public static void main(String[] args) {
        SistemaImpl sistema = new SistemaImpl();
        sistema.leerArchivoUsuarios();
        sistema.leerArchivoLibros();
        sistema.inicioPrograma();
    }
}