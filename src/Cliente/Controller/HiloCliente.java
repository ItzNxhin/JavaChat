package Cliente.Controller;

import java.io.*;
import java.util.function.Consumer;
import Cliente.View.VentanaCliente;

public class HiloCliente extends Thread {
    Consumer<String> mensajes;
    DataInputStream entrada;
    VentanaCliente vcli;
    ClienteControl principal;

    public HiloCliente(DataInputStream entrada,ClienteControl principal, VentanaCliente vcli, Consumer<String> mensajes) throws IOException {
        this.entrada = entrada;
        this.vcli = vcli;
        this.mensajes = mensajes;
        this.principal = principal;
    }

    public void run() {
        String menser = "", amigo = "";
        int opcion = 0;
        while (true) {
            try {
                opcion = entrada.readInt();
                switch (opcion) {
                    case 1:// mensage enviado
                        menser = entrada.readUTF();
                        mensajes.accept("ECO del servidor:" + menser);
                        vcli.mostrarMsg(menser);
                        break;
                    case 2:// se agrega
                        menser = entrada.readUTF();
                        vcli.agregarUser(menser);
                        break;
                    case 3:// mensage de amigo
                        amigo = entrada.readUTF();
                        menser = entrada.readUTF();
                        principal.mensageAmigo(amigo, menser);
                        mensajes.accept("ECO del servidor:" + menser);
                        break;
                }
            } catch (IOException e) {
                mensajes.accept("Error en la comunicaci�n " + "Informaci�n para el usuario");
                break;
            }
        }
        mensajes.accept("se desconecto el servidor");
    }

}