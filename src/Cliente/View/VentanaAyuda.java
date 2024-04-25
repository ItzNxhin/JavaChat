/*
 * VentanaAyuda.java
 *
 * Created on 23 de marzo de 2008, 17:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Cliente.View;
import javax.swing.*;
import java.net.URL;

/**
 *
 * @author Administrador
 */
/**
 * Ventana de ayuda que muestra un archivo HTML en un JEditorPane.
 */
public class VentanaAyuda extends JFrame{
    
    /** Creates a new instance of VentanaAyuda */
    JScrollPane panelPrincipal; // Panel principal con barra de desplazamiento
    JEditorPane html; // Mostrar el contenido HTML
     // Constructor de la clase
    public VentanaAyuda() {
        super("Ventana de Ayuda :"); //Título de la ventana
        setSize(600,700); // Tamaño de la ventana
        setLocation(450,0);//Posición de la ventana
        panelPrincipal=new JScrollPane(); // Crea un panel principal con barra de desplazamiento
        
        try{ 
            // Obtiene la URL del archivo HTML
            URL url=getClass().getResource("index.html");
            html=new JEditorPane(url); // Crea un JEditorPane con la URL del archivo HTML
            html.setEditable(false); // Hace que el JEditorPane no sea editable
            setVisible(true); // Hace visible la ventana
             
        }catch(Exception e){
            e.getMessage(); // Captura cualquier excepción y muestra el mensaje de error
        }
        
        JViewport jv=panelPrincipal.getViewport(); // Obtiene la vista del panel principal
        jv.add(html); // Agrega el JEditorPane al panel principal
        
        setContentPane(panelPrincipal);
    }
    
}
