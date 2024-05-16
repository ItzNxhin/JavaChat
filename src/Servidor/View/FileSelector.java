package Servidor.View;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author nahin
 */
public class FileSelector {

    /**
     *
     */
    public JFileChooser fProp; // JFileChooser para el archivo de propiedades

    /**
     *
     */
    public JFileChooser fProp2;

    /**
     *
     */
    public FileSelector() {
        // Constructor vacío
    }
    /**
     * Método para configurar el JFileChooser para el archivo de propiedades
     */
     
    public void fileP1() {
        fProp = new JFileChooser(System.getProperty("user.dir")); // Crea un JFileChooser en el directorio del usuario
                                                            
        fProp.setDialogTitle("Seleccionar el archivo de propiedades para las palabras prohibidas");
    }

    /**
     *
     */
    public void fileP2() {
        fProp2 = new JFileChooser(System.getProperty("user.dir")); // Crea un JFileChooser en el directorio del usuario
                                                            
        fProp2.setDialogTitle("Seleccionar el archivo de propiedades para los puertos del servidor");
    }

    // Método para mostrar un mensaje de error

    /**
     *
     */
    public void error() {
        JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
