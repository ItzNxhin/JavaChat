/*
 * Cliente.java
 *
 * Created on 21 de marzo de 2008, 12:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

 package Cliente.Model;

 import java.io.*;
 import java.net.*;
 import java.util.Vector;
import java.util.function.Consumer;
import java.util.logging.Level;
 import java.util.logging.Logger;
 
 /**
  *
  * @author Administrador
  */
  /**
 * Clase para establecer la conexión y manejar la comunicación del cliente.
 */
 public class ConexionCliente
 {
    public static String IP_SERVER; // Dirección IP del servidor
    DataInputStream entrada = null; // Flujo de entrada para recibir datos
    DataOutputStream salida = null;  // Flujo de salida para enviar datos
    DataInputStream entrada2 = null; // Segundo flujo de entrada para recibir mensajes

    Socket comunication = null;//para la comunicacion
    Socket comunication2 = null;//para recivir msg
    
    String nomCliente; // Nombre del cliente

    private Consumer<String> avisos; //Mostrar mensajes al usuario
    /** Creates a new instance of Cliente */
    //Constructor de la clase
    public ConexionCliente(Consumer<String> avisos) throws IOException
    {      
        this.avisos = avisos;
    }
    //Método para establecer la conexión con el servidor.
    public void conexion() throws IOException 
    {
       try {
          comunication = new Socket(ConexionCliente.IP_SERVER, 8081); // Establece la conexión en el puerto 8081
          comunication2 = new Socket(ConexionCliente.IP_SERVER, 8082); // Establece la conexión en el puerto 8082
          entrada = new DataInputStream(comunication.getInputStream()); // Obtiene el flujo de entrada del primer socket
          salida = new DataOutputStream(comunication.getOutputStream());// Obtiene el flujo de salida del primer socket
          entrada2 = new DataInputStream(comunication2.getInputStream());   
          salida.writeUTF(nomCliente); // Envía el nombre del cliente al servidor
       } catch (IOException e) {
          avisos.accept("\tEl servidor no esta levantado"); // Avisa al usuario si el servidor no está disponible
          avisos.accept("\t=============================");
       }
    }
    //Getters y Setters
    public String getNombre()
    {
       return nomCliente;
    }
    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public DataInputStream getEntrada2() {
        return entrada2;
    }
    // Método para solicitar la lista de usuarios activos al servidor.
    public Vector<String> pedirUsuarios()
    {
       Vector<String> users = new Vector<>();
       try {         
          salida.writeInt(2); // Envía la solicitud al servidor
          int numUsers=entrada.readInt();  // Lee la cantidad de usuarios desde el flujo de entrada
          for(int i=0;i<numUsers;i++)
          // Lee los nombres de usuario desde el flujo de entrada y los agrega al vector
             users.add(entrada.readUTF());
       } catch (IOException ex) {
          Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
       }
       return users; // Devuelve la lista de usuarios activos
    }
    //Método para enviar un mensaje al servidor.
    public void flujo(String mens) 
    {
       try {          
         // Muestra un mensaje al usuario con el mensaje enviado   
          avisos.accept("el mensaje enviado desde el cliente es :"
              + mens);
          salida.writeInt(1); // Envía la opción al servidor 
          salida.writeUTF(mens); // Envía el mensaje al servidor
       } catch (IOException e) {
         // Avisa al usuario si hay un error de entrada/salida
          avisos.accept("error...." + e);
       }
    }
    //Método para enviar un mensaje privado a un usuario específico.
    public void flujo(String amigo,String mens) 
    {
       try {             
         // Muestra un mensaje al usuario con el mensaje enviado
          avisos.accept("el mensaje enviado desde el cliente es :"
              + mens);
          salida.writeInt(3);//opcion de mensage a amigo
          salida.writeUTF(amigo); // Envía el nombre del amigo al servidor
          salida.writeUTF(mens); // Envía el mensaje al servidor
       } catch (IOException e) {
          avisos.accept("error...." + e); // Avisa al usuario si hay un error
       }
    }
    
   
 }