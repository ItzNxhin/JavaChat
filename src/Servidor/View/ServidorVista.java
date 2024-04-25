/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.View;

import javax.swing.*;

/**
 *
 * @author Sara
 */
/**
 * Clase que define la interfaz gráfica de usuario del servidor.
 * Extiende JFrame para crear una ventana.
 */
public class ServidorVista extends JFrame{
   private JTextArea txaMostrar; // Área de texto 

   /**
    * Constructor de la clase.
    */
   public ServidorVista()    {
      super("Consola servidor"); // Establece el título de la ventana
      txaMostrar = new JTextArea(); // Crea un JTextArea para mostrar mensajes

      // Agrega el JTextArea a un JScrollPane para el desplazamiento
      this.setContentPane(new JScrollPane(txaMostrar));
      setSize(350,350); // Establece el tamaño de la ventana
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece la operación de cierre
      setVisible(true); // Hace visible la ventana 
      
   }
   // Método para mostrar un mensaje en el JTextArea
   public void mostrar(String msg)
   {
      txaMostrar.append(msg+"\n"); // Agrega el mensaje al JTextArea
   }
   //Método para imprimir un mensaje en la consola.
   public void mostrarMensaje(String mensaje) {
      System.out.println(mensaje);// Imprime el mensaje en la consola
  }
  
}