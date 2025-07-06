package si2024.daniellinfonyealu.p06;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import si2024.daniellinfonyealu.p06.Acciones.Accion;

public class GeneradorFichero {

    private String nomFichero;

    public GeneradorFichero(String nomFichero) {
        this.nomFichero = nomFichero;
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(this.nomFichero, false))) {

        } catch (IOException e) {
            System.err.println("Error abriendo el fichero: " + e.getMessage());
        }
    }

    public void escribeFich(List<Accion> list) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(this.nomFichero, true))) {
            for(Accion accion : list) {
            	escritor.write(accion.getNombre());
            	escritor.newLine(); // Escribir una nueva línea después de cada accion
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo el fichero: " + e.getMessage());
        }
    }
}