/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.View;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Sara
 */
public class Servidor extends JFrame
{
   JTextArea txaMostrar;
   public Servidor()
   {
      super("Consola servidor");
      txaMostrar=new JTextArea();      
    
      this.setContentPane(new JScrollPane(txaMostrar));
      setSize(350,350);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);      
      
   }
   public void mostrar(String msg)
   {
      txaMostrar.append(msg+"\n");
   }
}