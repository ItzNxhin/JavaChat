package Cliente.View;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

/**
 *
 * @author Administrador
 */
/**
 * Ventana para conversaciones privadas entre usuarios.
 */
public class VentanaPrivada extends JFrame {
   JTextArea panMostrar; // Área de texto para mostrar mensajes
   JTextField txtMensage; // Campo de texto para escribir mensajes
   JButton butEnviar; // Botón para enviar mensajes
   String amigo; // Nombre del amigo con el que se está chateando


   public VentanaPrivada() {
      super("Amigo"); // Título de la ventana
      txtMensage = new JTextField(30); // Campo de texto para escribir mensajes
      butEnviar = new JButton("Enviar"); // Botón para enviar mensajes
      panMostrar = new JTextArea(); // Área de texto para mostrar mensajes
      panMostrar.setEditable(false); // Área de texto no editable
      txtMensage.requestFocus(); // Pide el enfoque al campo de texto para escribir mensajes

      JPanel panAbajo = new JPanel(); // Panel para la parte inferior de la ventana
      panAbajo.setLayout(new BorderLayout()); // Diseño del panel como BorderLayout
      panAbajo.add(new JLabel("  Ingrese mensage a enviar:"), // Etiqueta para pedir al usuario que ingrese un mensaje
            BorderLayout.NORTH);
      panAbajo.add(txtMensage, BorderLayout.CENTER); // Campo de texto para escribir mensajes
      panAbajo.add(butEnviar, BorderLayout.EAST); // Botón para enviar mensajes

      setLayout(new BorderLayout());// Diseño del panel como BorderLayout
      // Área de texto para mostrar mensajes con barra de desplazamiento
      add(new JScrollPane(panMostrar), BorderLayout.CENTER); 
      add(panAbajo, BorderLayout.SOUTH);
// Inicializa el nombre del amigo como una cadena vacía
      amigo = "";

      this.addWindowListener(new WindowListener() {
         // Método llamado cuando se cierra la ventana
         public void windowClosing(WindowEvent e) {
            cerrarVentana(); // Método para cerrar la ventana
         }

         public void windowClosed(WindowEvent e) {
         }

         public void windowOpened(WindowEvent e) {
         }

         public void windowIconified(WindowEvent e) {
         }

         public void windowDeiconified(WindowEvent e) {
         }

         public void windowActivated(WindowEvent e) {
         }

         public void windowDeactivated(WindowEvent e) {
         }

      });

      setSize(300, 300); //Tamaño de la ventana
      setLocation(570, 90); //Ubicación de la ventana
   }
 // Método para establecer el nombre del amigo en el título de la ventana
   public void setAmigo(String ami) {
      this.amigo = ami;
      this.setTitle(ami);
   }
 // Método para cerrar la ventana
   private void cerrarVentana() {
      this.setVisible(false); // Hace que la ventana no sea visible
   }
// Método para mostrar un mensaje en el área de texto
   public void mostrarMsg(String msg) {
      this.panMostrar.append(msg + "\n");
   }
//Getters 
   public JButton getButton() {
      return this.butEnviar;
   }

   public JTextField getTxtMensage() {
      return this.txtMensage;
   }

   public String getAmigo() {
      return this.amigo;
   }
}
