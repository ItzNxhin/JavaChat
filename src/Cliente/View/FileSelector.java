package Cliente.View;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileSelector {
    public JFileChooser fProp; // JFileChooser para el archivo de propiedades
    public FileSelector() {
        // Constructor vacío
    }
    /**
     * Método para configurar el JFileChooser para el archivo de propiedades
     */
     
    public void fileP() {
        fProp = new JFileChooser(System.getProperty("user.dir")); // Crea un JFileChooser en el directorio del usuario
                                                            
        fProp.setDialogTitle("Seleccionar el archivo de propiedades para los puertos del servidor");
    }

    // Método para mostrar un mensaje de error
    public void error() {
        JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
