package Servidor.Controller;
import Servidor.threadServidor;
import Servidor.Model.ServidorModelo;
import Servidor.View.ServidorVista;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorControl {
    
    private ServidorVista vista;

    public ServidorControl() {
        vista = new ServidorView();
        runServer();
    }
    public void runServer()
   {
      ServerSocket serv=null;//para comunicacion
      ServerSocket serv2=null;//para enviar mensajes
      boolean listening=true;
      try{
        serv=new ServerSocket(8081);
         serv2=new ServerSocket(8082);
		 //variable tipo vista.mostrar
         mostrar(".::Servidor activo :");
         while(listening)
         {
            Socket sock=null,sock2=null;
            try {
				//variable tipo vista.mostrar
               mostrar("Esperando Usuarios");
               sock=serv.accept();
               sock2=serv2.accept();
            } catch (IOException e)
            {
				//variable tipo vista.mostrar
               mostrar("Accept failed: " + serv + ", " + e.getMessage());
               continue;
            }
            threadServidor user=new threadServidor(sock,sock2,this);   
			//importante el start
	    user.start();
         }
         
      }catch(IOException e){
		  //variable tipo vista.mostrar
         mostrar("error :"+e);
      }
   }
}