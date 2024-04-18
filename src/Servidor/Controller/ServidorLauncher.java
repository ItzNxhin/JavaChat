/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Controller;

import java.io.IOException;

/**
 *
 * @author Sara
 */
public class ServidorLauncher {
       public static void main(String abc[]) throws IOException
   {                
     Servidor ser= new Servidor();
     ser.runServer();
   }
}
