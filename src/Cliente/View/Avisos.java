package Cliente.View;

import javax.swing.JOptionPane;
// Clase para mostrar avisos

public class Avisos {
    // Constructor
    public Avisos() {
    }

    /**
     * Método para mostrar un mensaje en la consola
     */
    public void consola(String x) {
        System.out.println(x);
    }

    /**
     * Método para obtener la dirección IP del servidor
     */

    public String ip() {
        // Muestra un cuadro de diálogo para que el usuario introduzca la dirección IP
        // del servidor
        // El valor por defecto es "localhost"
        return JOptionPane.showInputDialog("Introducir IP_SERVER :", "localhost");
    }

    /**
     * Método para obtener el nombre de usuario (nick)
     */
    public String nick() {
        // Muestra un cuadro de diálogo para que el usuario introduzca su nombre de
        // usuario (nick)
        return JOptionPane.showInputDialog("Introducir Nick :");
    }
}
