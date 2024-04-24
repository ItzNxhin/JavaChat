package Servidor.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class ConexionServidor {

    Socket sock = null, sock2 = null;

    ServerSocket serv = null;// para comunicacion
    ServerSocket serv2 = null;// para enviar mensajes
    Consumer<Object> lanzarHilo;
    Consumer<String> avisos;

    public ConexionServidor(Consumer<String> avisos, Consumer<Object> lanzarHilo) {
        this.avisos = avisos;
        this.lanzarHilo = lanzarHilo;
    }

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

    public void runServer() {
        boolean listening = true;
        try {
            serv = new ServerSocket(8081);
            serv2 = new ServerSocket(8082);
            avisos.accept(".::Servidor activo :");
            while (listening) {
                
                try {
                    avisos.accept("Esperando Usuarios");
                    sock = serv.accept();
                    sock2 = serv2.accept();
                } catch (IOException e) {
                    avisos.accept("Accept failed: " + serv + ", " + e.getMessage());
                    continue;
                }
                lanzarHilo.accept(new Object());
            }

        } catch (IOException e) {
            avisos.accept("error :" + e);
        }
    }

    {

    }
}
