package Servidor.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * Esta clase es encargarda de leer las palabras prohibidas en el chat publico
 */
public class LecturaBadWords {
    private Properties prop; // Propiedades del archivo
    private ArrayList<String> badwords; // Lista de palabras cargadas desde el archivo de propiedades

    /**
     * COnstructor de la clase
     */
    public LecturaBadWords() {
        this.badwords = new ArrayList<>(); // Inicializa la lista de palabras cargadas
        this.prop = new Properties(); // Inicializa el objeto de propiedades
    }
    /**
     * Metodo para establecer el archivo de propiedades
     * 
     * @param f
     */
    public void setProp(File f) {
        try {
            this.prop.load(new FileInputStream(f)); // Carga el archivo de propiedades
        } catch (IOException e) {

        }
    }
    // Métodos getter y setter para la lista de palabras cargadas

    /**
     *
     * @return
     */
    public ArrayList<String> getBadwords() {
        return badwords;
    }

    /**
     *
     * @param badwords
     */
    public void setBadwords(ArrayList<String> badwords) {
        this.badwords = badwords;
    }
    /**
     * Metodo para cargar las malas palabras
     */
    public void cargarPalabras() {
        try {
            ArrayList<String> listaProp = new ArrayList<>(); // Lista para almacenar las claves de las palabras en el archivo
            for (String key : prop.stringPropertyNames()) {
                if (key.startsWith("w")) { // Si la clave comienza con "w" (indicando una palabra)
                    if (!listaProp.contains(key.split("\\.")[0])) { // Verifica si la lista ya contiene la clave principal (para evitar duplicados)
                        listaProp.add(key.split("\\.")[0]); // Agrega la clave principal a la lista
                    }
                }
            }
            for (String i : listaProp) {
                String x = prop.getProperty(i + ".p"); // Obtiene la palabra
                badwords.add(x);// Guarda la palabra
            }
        } catch (Exception e) {
        }
    }
    
}
