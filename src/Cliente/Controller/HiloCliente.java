package Cliente.Controller;

import java.io.*;
import java.util.function.Consumer;

public class HiloCliente extends Thread {
    Consumer<String> vAvisos;
    DataInputStream entrada; // Flujo de entrada para recibir mensajes del servidor
    ClienteControl principal; // Referencia al controlador principal del cliente

    // Constructor de la clase
    public HiloCliente(DataInputStream entrada,ClienteControl principal, Consumer<String> vAvisos) throws IOException {
        this.entrada = entrada;
        this.principal = principal;
        this.vAvisos = vAvisos;
    }

    /**
     * Método que se ejecuta cuando se inicia el hilo
     */
    public void run() {
        String menser = "", amigo = "";
        int opcion = 0;
        // Bucle para recibir mensajes del servidor
        while (true) {
            try {
                opcion = entrada.readInt(); // Lee la opción del servidor
                switch (opcion) {
                    case 1:// mensage enviado
                        menser = entrada.readUTF(); // Lee el mensaje del servidor
                        principal.avisos.consola("ECO del servidor:" + menser);
                        vAvisos.accept(menser); // Muestra el mensaje en la ventana del cliente
                        break;
                    case 2:// se agrega
                        menser = entrada.readUTF(); // Lee el nombre del usuario del servidor
                        principal.vClient.agregarUser(menser); // Agrega el usuario a la lista de usuarios
                        break;
                    case 3:// mensage de amigo
                        amigo = entrada.readUTF(); // Lee el nombre del amigo del servidor
                        menser = entrada.readUTF(); // Lee el mensaje del servidor
                        principal.mensageAmigo(amigo, menser);
                        principal.avisos.consola("ECO del servidor:" + menser);
                        break;
                    case 4:// Banea al cliente
                        principal.ban();
                        interrupt();
                        return;
                    case 5://Elimina el usuario baneado
                        menser = entrada.readUTF();
                        principal.removerUser(menser);
                        break;
                }
            } catch (IOException e) {
                // Muestra un mensaje de error al usuario
                principal.avisos.consola("Error en la comunicaci�n " + "Informaci�n para el usuario");
                break;
            }
        }
         // Muestra un mensaje de que el servidor se desconectó
         principal.avisos.consola("se desconecto el servidor");
    }

}