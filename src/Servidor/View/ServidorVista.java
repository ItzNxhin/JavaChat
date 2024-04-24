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
public class ServidorVista extends JFrame{
   private JTextArea txaMostrar;

   public ServidorVista()    {
      super("Consola servidor");
      txaMostrar = new JTextArea();   
      
      this.setContentPane(new JScrollPane(txaMostrar));
      setSize(350,350);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);      
      
   }
   public void mostrar(String msg)
   {
      txaMostrar.append(msg+"\n");
   }
   public void mostrarMensaje(String mensaje) {
      System.out.println(mensaje);
  }
  
}