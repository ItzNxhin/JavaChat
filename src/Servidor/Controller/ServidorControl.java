package Servidor.Controller;

import Servidor.View.FileSelector;
import Servidor.View.ServidorVista;
import Servidor.model.ConexionServidor;
import Servidor.model.LecturaBadWords;
import Servidor.model.LecturaPuertos;

import java.io.IOException;

public class ServidorControl {

   private FileSelector fc;
   private LecturaBadWords bd;
   private LecturaPuertos pt;
   private ServidorVista vista; // Vista del servidor
   private ConexionServidor con;// Conexión del servidor

   // Constructor de la clase
   public ServidorControl() throws IOException{
      bd = new LecturaBadWords();
      pt = new LecturaPuertos();
      fc = new FileSelector();
      fc.fileP1();
      fc.fProp.showOpenDialog(fc.fProp);
      bd.setProp(fc.fProp.getSelectedFile());
      try {
         bd.cargarPalabras();
      } catch (Exception e) {
         fc.error();
      }
      fc.fileP2();
      fc.fProp2.showOpenDialog(fc.fProp2);
      pt.setProp(fc.fProp2.getSelectedFile());
      try {
         pt.cargarPuertos();
      } catch (Exception e) {
         fc.error();
      }
      
       // Inicializa la vista del servidor y la conexión del servidor
      vista = new ServidorVista(); 
      con = new ConexionServidor(vista::mostrar, this::hilo, pt.getP1(), pt.getP2());
       // Inicia el servidor llamando al método runServer() de la conexión del servidor
      con.runServer();
   }

   /**
    * Método para manejar un hilo de conexión del servidor
    */
   public void hilo(Object x) {
      // Crea un nuevo hilo de servidor
      threadServidor user = new threadServidor(con.getSock(), con.getSock2(), this, bd.getBadwords());
      // Inicia el hilo de servidor
      user.start();
   }

   /**
    * Método para mostrar mensajes en la vista del servidor
    */
   public void mostrar(String x) {
      // Llama al método mostrar de la vista del servidor
      vista.mostrar(x);
   }
}