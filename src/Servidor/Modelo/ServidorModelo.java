package Servidor.Modelo;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Sara
 */
public class ServidorModelo{
   public void runServer()
   {
      ServerSocket serv=null;//para comunicacion
      ServerSocket serv2=null;//para enviar mensajes
      boolean listening=true;
      try{
         serv=new ServerSocket(8081);
         serv2=new ServerSocket(8082);
         mostrar(".::Servidor activo :");
         while(listening)
         {
            Socket sock=null,sock2=null;
            try {
               mostrar("Esperando Usuarios");
               sock=serv.accept();
               sock2=serv2.accept();
            } catch (IOException e)
            {
               mostrar("Accept failed: " + serv + ", " + e.getMessage());
               continue;
            }
            threadServidor user=new threadServidor(sock,sock2,this);            
	    user.start();
         }
         
      }catch(IOException e){
         mostrar("error :"+e);
      }
   }{
    
}
}