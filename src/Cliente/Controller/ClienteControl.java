package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Cliente.Model.ConexionCliente;
import Cliente.View.VentanaAyuda;
import Cliente.View.VentanaCliente;
import Cliente.View.Avisos;
import Cliente.View.VentanaPrivada;

public class ClienteControl implements ActionListener {

    private VentanaCliente vClient;
    private ConexionCliente conexion;
    private Avisos avisos;
    private VentanaAyuda vAyuda;
    private VentanaPrivada vPrivada;

    public ClienteControl() throws IOException {
        avisos = new Avisos();
        ConexionCliente.IP_SERVER = avisos.ip();
        vClient = new VentanaCliente();
        vPrivada = new VentanaPrivada();
        conexion = new ConexionCliente(avisos::consola);

        vClient.txtMensage.addActionListener(this);
        vClient.butEnviar.addActionListener(this);
        vClient.butPrivado.addActionListener(this);
        vClient.help.setActionCommand("help");
        vClient.help.addActionListener(this);
        vClient.acercaD.setActionCommand("Acerca");
        vClient.acercaD.addActionListener(this);

        vPrivada.getButton().addActionListener(this);
        vPrivada.getTxtMensage().addActionListener(this);
        iniciar();

    }

    private void iniciar() throws IOException {
        String nick = avisos.nick();
        conexion.setNomCliente(nick);
        vClient.setNombreUser(nick);
        conexion.conexion();
        new HiloCliente(conexion.getEntrada2(), this, vClient, avisos::consola).start();
        vClient.ponerActivos(conexion.pedirUsuarios());
        vClient.setVisible(true);
    }

    public void mensageAmigo(String amigo, String msg) {
        vPrivada.setAmigo(amigo);
        vPrivada.mostrarMsg(msg);
        vPrivada.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().compareTo("help") == 0) {
            vAyuda = new VentanaAyuda();
            vAyuda.setVisible(true);
        }
        if (e.getActionCommand().compareTo("Acerca") == 0) {
            /*
             * Aca deberiamos llamar la clase de los avisos
             * con el aviso que dice en el action performed de la ventana cliente
             */
        }
        if (e.getSource() == vClient.butEnviar || e.getSource() == vClient.txtMensage) {
            String mensaje = vClient.txtMensage.getText();
            conexion.flujo(mensaje);
            vClient.txtMensage.setText("");
        }
        if (e.getSource() == vClient.butPrivado) {
            int pos = vClient.lstActivos.getSelectedIndex();
            if (pos >= 0) {
                vPrivada.setAmigo(vClient.nomUsers.get(pos));
                vPrivada.setVisible(true);
            }
        }
        if (e.getSource() == vPrivada.getButton()) {
            String mensaje = vPrivada.getTxtMensage().getText();
            vPrivada.mostrarMsg(conexion.getNombre() + ">" + mensaje);
            conexion.flujo(vPrivada.getAmigo(), mensaje);
            vPrivada.getTxtMensage().setText("");
        }
    }

}
