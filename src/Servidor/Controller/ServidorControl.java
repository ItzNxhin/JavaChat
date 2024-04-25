package Servidor.Controller;

import Servidor.View.ServidorVista;
import Servidor.model.ConexionServidor;

import java.io.IOException;

public class ServidorControl {

   private ServidorVista vista; // Vista del servidor
   private ConexionServidor con;// Conexión del servidor

// Constructor de la clase
   public ServidorControl() throws IOException{
       // Inicializa la vista del servidor y la conexión del servidor
      vista = new ServidorVista(); 
      con = new ConexionServidor(vista::mostrar, this::hilo);
       // Inicia el servidor llamando al método runServer() de la conexión del servidor
      con.runServer();
   }
// Método para manejar un hilo de conexión del servidor
   public void hilo(Object x){
      // Crea un nuevo hilo de servidor
      threadServidor user = new threadServidor(con.getSock(), con.getSock2(), this);
      // Inicia el hilo de servidor
      user.start();
   }
// Método para mostrar mensajes en la vista del servidor
   public void mostrar(String x) {
      // Llama al método mostrar de la vista del servidor
      vista.mostrar(x);
   }
}