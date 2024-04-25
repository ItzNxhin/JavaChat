package Servidor.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class threadServidor extends Thread {
   Socket scli = null; // Socket para la comunicación con el cliente para mensajes generales
   Socket scli2 = null;// Socket para la comunicación con el cliente para enviar mensajes privados
   DataInputStream entrada = null; // Stream de entrada de datos desde el cliente
   DataOutputStream salida = null;// Stream de salida de datos hacia el cliente para mensajes generales
   DataOutputStream salida2 = null; // Stream de salida de datos hacia el cliente para enviar mensajes privados
   // Vector que almacena los hilos activos de los clientes
   public static Vector<threadServidor> clientesActivos = new Vector<>();
   String nameUser;// Nombre del usuario
   ServidorControl serv; // Referencia al controlador del servidor
   ArrayList<String> badwords; //Lista de palabras a beanear

   // Constructor de la clase
   public threadServidor(Socket scliente, Socket scliente2, ServidorControl serv, ArrayList<String> badwords) {
      this.badwords = badwords;
      scli = scliente; // Asigna el socket para mensajes generales
      scli2 = scliente2; // Asigna el socket para mensajes privados
      this.serv = serv;// Asigna la referencia al controlador del servidor
      nameUser = ""; // Inicializa el nombre del usuario
      clientesActivos.add(this);// Agrega este hilo a la lista de clientes activos
      // variable de tipo servidor con mensaje extraido de la vista
      serv.mostrar("cliente agregado: " + this);
   }

   // Getter para obtener el nombre del usuario
   public String getNameUser() {
      return nameUser;
   }

   // Setter para establecer el nombre del usuario
   public void setNameUser(String name) {
      nameUser = name;
   }

   /**
    * Método que se ejecuta cuando el hilo inicia
    */
   public void run() {
      // Muestra un mensaje en la vista del servidor indicando que está esperando mensajes
      serv.mostrar(".::Esperando Mensajes :");

      try {
         // Establece los flujos de entrada y salida de datos para mensajes generales
         entrada = new DataInputStream(scli.getInputStream());
         salida = new DataOutputStream(scli.getOutputStream());
         salida2 = new DataOutputStream(scli2.getOutputStream());
          // Establece el nombre de usuario recibido desde el cliente
         this.setNameUser(entrada.readUTF());
         // Envía la lista de usuarios activos a este cliente
         enviaUserActivos();
      } catch (IOException e) {
         e.printStackTrace();
      }

      int opcion = 0, numUsers = 0;
      String amigo = "", mencli = "";

      while (true) {
         try {
            // Lee la opción enviada por el cliente
            opcion = entrada.readInt();
            switch (opcion) {
               case 1:// envio de mensage a todos
                  Boolean baneo=false;
                  mencli = entrada.readUTF();
                  String aux = mencli.toLowerCase();
                  //Verificar si el mensaje no es vulgar, en caso afirmativo, banear
                  for(String i : badwords){
                     if (aux.contains(i)){
                        baneo();
                        baneo=true;
                     }
                  }
                  if(!baneo){
                     serv.mostrar("mensaje recibido " + mencli);
                     enviaMsg(mencli);
                  }
                  break;
               case 2:// envio de lista de activos
                  numUsers = clientesActivos.size();
                  salida.writeInt(numUsers);
                  for (int i = 0; i < numUsers; i++)
                     salida.writeUTF(clientesActivos.get(i).nameUser);
                  break;
               case 3: // envia mensage a uno solo
                  amigo = entrada.readUTF();// captura nombre de amigo
                  mencli = entrada.readUTF();// mensage enviado
                  enviaMsg(amigo, mencli);
                  break;
            }
         } catch (IOException e) {
            // Muestra un mensaje en la vista del servidor si el cliente termina la conexión
            System.out.println("El cliente termino la conexion");
            break;
         }
      }
      // Muestra un mensaje en la vista del servidor indicando que se removió un usuario
      serv.mostrar("Se removio un usuario");
      // Remueve este hilo de la lista de clientes activos
      clientesActivos.removeElement(this);
      try {
         serv.mostrar("Se desconecto un usuario");
         // Cierra el socket de comunicación con el cliente
         scli.close();
      } catch (Exception et) {
         // Muestra un mensaje en la vista del servidor si no se pudo cerrar el socket
         serv.mostrar("no se puede cerrar el socket");
      }
   }

   /**
    * Método para enviar un mensaje a todos los usuarios
    */
   public void enviaMsg(String mencli2) {
      threadServidor user = null;
      for (int i = 0; i < clientesActivos.size(); i++) {
         serv.mostrar("MENSAJE DEVUELTO:" + mencli2);
         try {
            user = clientesActivos.get(i);
            // Envía el mensaje a cada usuario activo
            user.salida2.writeInt(1);// opcion de mensage
            user.salida2.writeUTF("" + this.getNameUser() + " >" + mencli2); // Mensaje con nombre de usuario
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   /**
    * Método para enviar la lista de usuarios activos a este usuario
    */

   public void enviaUserActivos() {
      threadServidor user = null;
      for (int i = 0; i < clientesActivos.size(); i++) {
         try {
            user = clientesActivos.get(i);
            if (user == this)
               continue;// ya se lo envie
            user.salida2.writeInt(2);// opcion de agregar
            user.salida2.writeUTF(this.getNameUser());// Envía el nombre de este usuario
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   /**
    * Método para enviar un mensaje privado a un usuario específico
    */
   private void enviaMsg(String amigo, String mencli) {
      threadServidor user = null;
      for (int i = 0; i < clientesActivos.size(); i++) {
         try {
            user = clientesActivos.get(i);
            // Si el nombre del usuario coincide con el destinatario, envía el mensaje
            if (user.nameUser.equals(amigo)) {
               user.salida2.writeInt(3);// opcion de mensage amigo
               user.salida2.writeUTF(this.getNameUser());// Nombre de usuario remitente
               user.salida2.writeUTF("" + this.getNameUser() + ">" + mencli);// Mensaje con nombre de usuario
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   /**
    * Metodo encargado de ejecutar el baneo, y ordenar los respectivos avisos
    */
   private void baneo(){
      String userBan = "";
      threadServidor user = null;
      //Hacer el baneo del usuario
      for (int i = 0; i < clientesActivos.size(); i++) {
         serv.mostrar("EL USUARIO "+nameUser+" HA SIDO BANEADO POR VULGAR");
         try {
            user = clientesActivos.get(i);
            if (user.nameUser.equals(nameUser)) {
               user = clientesActivos.get(i);
               userBan = nameUser;
               user.salida2.writeInt(4);// opcion de mensage          
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      //Avisar del baneo a los demas usuarios
      for (int i = 0; i < clientesActivos.size(); i++) {
         try {
            user = clientesActivos.get(i);
            // Envía el mensaje a cada usuario activo
            user.salida2.writeInt(1);// opcion de mensage
            user.salida2.writeUTF("" + this.getNameUser() + " >" + "HA SIDO VULGAR Y SE FUE BANEAO"); // Mensaje con nombre de usuario
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      //Quitar de los usuarios activos, en cliente, el usuario baneado
      for (int i = 0; i < clientesActivos.size(); i++) {
         try {
            user = clientesActivos.get(i);
            // Envía el mensaje a cada usuario activo
            user.salida2.writeInt(5);// opcion de mensage
            user.salida2.writeUTF(userBan); // Mensaje con nombre de usuario
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      
   }
}