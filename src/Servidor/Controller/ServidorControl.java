package Servidor.Controller;

import Servidor.View.ServidorVista;
import Servidor.model.ConexionServidor;

import java.io.IOException;

public class ServidorControl {

   private ServidorVista vista;
   private ConexionServidor con;

   public ServidorControl() throws IOException{
      vista = new ServidorVista();
      con = new ConexionServidor(vista::mostrar, this::hilo);
      con.runServer();
   }

   public void hilo(Object x){
      threadServidor user = new threadServidor(con.getSock(), con.getSock2(), this);
      user.start();
   }

   public void mostrar(String x) {
      vista.mostrar(x);
   }
}