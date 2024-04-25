package Cliente.Model;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Encargado de leer los puertos de un prperties
 */
public class LecturaPuertos {
    private Properties prop; // Propiedades del archivo
    private int p1;
    private int p2;

    public int getP1() {
        return p1;
    }
    public void setP1(int p1) {
        this.p1 = p1;
    }
    public int getP2() {
        return p2;
    }
    public void setP2(int p2) {
        this.p2 = p2;
    }
    /**
     * COnstructor de la clase
     */
    public LecturaPuertos() {
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

    public void cargarPuertos(){
        p1 = Integer.parseInt(prop.getProperty("port.1"));
        p2 = Integer.parseInt(prop.getProperty("port.2"));
    }
}
