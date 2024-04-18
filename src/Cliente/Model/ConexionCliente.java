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
 public class ConexionCliente
 {
    public static String IP_SERVER;
    DataInputStream entrada = null;
    DataOutputStream salida = null;
    DataInputStream entrada2 = null;

    Socket comunication = null;//para la comunicacion
    Socket comunication2 = null;//para recivir msg
    
    String nomCliente;

    private Consumer<String> avisos;
    /** Creates a new instance of Cliente */
    public ConexionCliente(Consumer<String> avisos) throws IOException
    {      
        this.avisos = avisos;
    }
    
    public void conexion() throws IOException 
    {
       try {
          comunication = new Socket(ConexionCliente.IP_SERVER, 8081);
          comunication2 = new Socket(ConexionCliente.IP_SERVER, 8082);
          entrada = new DataInputStream(comunication.getInputStream());
          salida = new DataOutputStream(comunication.getOutputStream());
          entrada2 = new DataInputStream(comunication2.getInputStream());   
          salida.writeUTF(nomCliente);
       } catch (IOException e) {
          avisos.accept("\tEl servidor no esta levantado");
          avisos.accept("\t=============================");
       }
    }
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
    public Vector<String> pedirUsuarios()
    {
       Vector<String> users = new Vector<>();
       try {         
          salida.writeInt(2);
          int numUsers=entrada.readInt();
          for(int i=0;i<numUsers;i++)
             users.add(entrada.readUTF());
       } catch (IOException ex) {
          Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
       }
       return users;
    }
    public void flujo(String mens) 
    {
       try {             
          avisos.accept("el mensaje enviado desde el cliente es :"
              + mens);
          salida.writeInt(1);
          salida.writeUTF(mens);
       } catch (IOException e) {
          avisos.accept("error...." + e);
       }
    }
    
    public void flujo(String amigo,String mens) 
    {
       try {             
          avisos.accept("el mensaje enviado desde el cliente es :"
              + mens);
          salida.writeInt(3);//opcion de mensage a amigo
          salida.writeUTF(amigo);
          salida.writeUTF(mens);
       } catch (IOException e) {
          avisos.accept("error...." + e);
       }
    }
    
   
 }