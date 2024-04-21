package Controlador;
import View.Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorControl {
    private Conexion modelo;
    private Servidor vista;
    private ArrayList<ConexionCliente> clientesActivos;

    public ServidorControl(ServidorModelo modelo, ServidorVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.clientesActivos = new ArrayList<>();
    }

    public void manejarConexion(ConexionCliente conexionCliente) {
        try {
            DataInputStream entrada = new DataInputStream(conexionCliente.getSocket().getInputStream());
            DataOutputStream salida = new DataOutputStream(conexionCliente.getSocket().getOutputStream());

            // Asignar nombre de usuario
            String nombreUsuario = entrada.readUTF();
            conexionCliente.setNombreUsuario(nombreUsuario);
            // Agregar cliente a la lista de activos
            clientesActivos.add(conexionCliente);

            // Lógica para manejar los mensajes
            while (true) {
                int opcion = entrada.readInt();
                switch (opcion) {
                    case 1: // Envío de mensaje a todos
                        String mensaje = entrada.readUTF();
                        enviarMensajeATodos(conexionCliente, mensaje);
                        break;
                    case 2: // Envío de lista de activos
                        enviarListaActivos(conexionCliente);
                        break;
                    case 3: // Envío de mensaje a uno solo
                        String amigo = entrada.readUTF();
                        String mensajePrivado = entrada.readUTF();
                        enviarMensajeAUno(conexionCliente, amigo, mensajePrivado);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void enviarMensajeATodos(ConexionCliente remitente, String mensaje) {
        for (ConexionCliente cliente : clientesActivos) {
            try {
                DataOutputStream salida = new DataOutputStream(cliente.getSocket().getOutputStream());
                salida.writeInt(1); // Opción de mensaje
                salida.writeUTF(remitente.getNombreUsuario() + ": " + mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void enviarListaActivos(ConexionCliente destino) {
        try {
            DataOutputStream salida = new DataOutputStream(destino.getSocket().getOutputStream());
            salida.writeInt(2); // Opción de lista de activos
            salida.writeInt(clientesActivos.size()); // Número de clientes activos
            for (ConexionCliente cliente : clientesActivos) {
                salida.writeUTF(cliente.getNombreUsuario());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarMensajeAUno(ConexionCliente remitente, String destinatario, String mensaje) {
        for (ConexionCliente cliente : clientesActivos) {
            if (cliente.getNombreUsuario().equals(destinatario)) {
                try {
                    DataOutputStream salida = new DataOutputStream(cliente.getSocket().getOutputStream());
                    salida.writeInt(3); // Opción de mensaje privado
                    salida.writeUTF(remitente.getNombreUsuario());
                    salida.writeUTF(mensaje);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
