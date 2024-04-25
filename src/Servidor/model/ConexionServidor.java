package Servidor.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * Clase que gestiona la conexión del servidor.
 */
public class ConexionServidor {
    // Sockets para comunicación
    Socket sock = null, sock2 = null;
    // ServerSockets para comunicación y envío de mensajes
    ServerSocket serv = null;// para comunicacion
    ServerSocket serv2 = null;// para enviar mensajes
    // Consumidores para manejar mensajes y lanzar hilos
    Consumer<Object> lanzarHilo;
    Consumer<String> avisos;

    // Constructor que recibe los consumidores para manejar mensajes y lanzar hilos
    public ConexionServidor(Consumer<String> avisos, Consumer<Object> lanzarHilo) {
        this.avisos = avisos;
        this.lanzarHilo = lanzarHilo;
    }

    // Getters y setters para los sockets y ServerSockets
    public Socket getSock() {
        return sock;
    }

    public void setSock(Socket sock) {
        this.sock = sock;
    }

    public Socket getSock2() {
        return sock2;
    }

    public void setSock2(Socket sock2) {
        this.sock2 = sock2;
    }

    public ServerSocket getServ() {
        return serv;
    }

    public void setServ(ServerSocket serv) {
        this.serv = serv;
    }

    public ServerSocket getServ2() {
        return serv2;
    }

    public void setServ2(ServerSocket serv2) {
        this.serv2 = serv2;
    }

    /**
     * Método para ejecutar el servidor
     */
    public void runServer() {
        boolean listening = true;
        try {
            // Se crean los ServerSockets en los puertos 8081 y 8082
            serv = new ServerSocket(8081);
            serv2 = new ServerSocket(8082);
            avisos.accept(".::Servidor activo :"); // Se emite un aviso de que el servidor está activo
            while (listening) {

                try {
                    avisos.accept("Esperando Usuarios");// Se emite un aviso de que se está esperando a los usuarios
                    // Se aceptan las conexiones de los usuarios
                    sock = serv.accept();
                    sock2 = serv2.accept();
                } catch (IOException e) {
                    // Si ocurre un error al aceptar la conexión, se emite un mensaje de error
                    avisos.accept("Accept failed: " + serv + ", " + e.getMessage());
                    continue;
                }
                // Se lanza un hilo para manejar la conexión
                lanzarHilo.accept(new Object());
            }

        } catch (IOException e) {
            // Si ocurre un error durante la ejecución del servidor, se emite un mensaje de
            // error
            avisos.accept("error :" + e);
        }
    }

    {

    }
}
