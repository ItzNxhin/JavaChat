package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Cliente.Model.ConexionCliente;
import Cliente.View.VentanaAyuda;
import Cliente.View.VentanaCliente;
import Cliente.View.Avisos;
import Cliente.View.FileSelector;
import Cliente.View.VentanaPrivada;
import Cliente.Model.LecturaPuertos;

public class ClienteControl implements ActionListener {

    private FileSelector fc;
    private LecturaPuertos pt;
    public VentanaCliente vClient;
    private ConexionCliente conexion;
    public Avisos avisos;
    private VentanaAyuda vAyuda;
    public VentanaPrivada vPrivada;

    // Constructor del controlador del cliente
    public ClienteControl() throws IOException {
        fc = new FileSelector();
        pt = new LecturaPuertos();
        fc.fileP();
        fc.fProp.showOpenDialog(fc.fProp);
        pt.setProp(fc.fProp.getSelectedFile());
        try {
            pt.cargarPuertos();
        } catch (Exception e) {
            fc.error();
        }
        avisos = new Avisos(); // Crea una instancia del componente de avisos
        ConexionCliente.IP_SERVER = avisos.ip(); // Obtiene la dirección IP del servidor desde el usuario
        vClient = new VentanaCliente();// Crea la ventana principal del cliente
        vPrivada = new VentanaPrivada(); // Crea la ventana de chat privado
        conexion = new ConexionCliente(avisos::consola, pt.getP1(), pt.getP2()); // Inicia la conexión con el servidor
        // ActionListener para la ventana principal del cliente
        vClient.txtMensage.addActionListener(this);
        vClient.butEnviar.addActionListener(this);
        vClient.butPrivado.addActionListener(this);
        vClient.help.setActionCommand("help");
        vClient.help.addActionListener(this);
        vClient.acercaD.setActionCommand("Acerca");
        vClient.acercaD.addActionListener(this);
        // ActionListener para la ventana de chat privado
        vPrivada.getButton().addActionListener(this);
        vPrivada.getTxtMensage().addActionListener(this);
        // Inicia el cliente
        iniciar();

    }

    /**
     * Método para inicializar el cliente
     */
    private void iniciar() throws IOException {
        String nick = avisos.nick(); // Solicita al usuario que ingrese su nombre de usuario
        conexion.setNomCliente(nick); // Establece el nombre de usuario en la conexión
        vClient.setNombreUser(nick); // Muestra el nombre de usuario en la ventana principal del cliente
        conexion.conexion(); // Establece la conexión con el servidor
        new HiloCliente(conexion.getEntrada2(),this, this::mensaje).start();// Inicia el hilo para recibir mensajes
        vClient.ponerActivos(conexion.pedirUsuarios()); // Obtiene y muestra la lista de usuarios activos
        vClient.setVisible(true);// Hace visible la ventana principal del cliente
    }

    public void mensageAmigo(String amigo, String msg) {
        vPrivada.setAmigo(amigo);
        vPrivada.mostrarMsg(msg);
        vPrivada.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Maneja las acciones del menú de ayuda
        if (e.getActionCommand().compareTo("help") == 0) {
            vAyuda = new VentanaAyuda();
            vAyuda.setVisible(true);
        }
        if (e.getActionCommand().compareTo("Acerca") == 0) {
            avisos.aceraDe();
        }
        // Maneja el envío de mensajes desde la ventana principal del cliente
        if (e.getSource() == vClient.butEnviar || e.getSource() == vClient.txtMensage) {
            String mensaje = vClient.txtMensage.getText();// Obtiene el mensaje ingresado por el usuario
            conexion.flujo(mensaje);// Envía el mensaje al servidor
            vClient.txtMensage.setText(""); // Limpia el campo de texto
        }
        if (e.getSource() == vClient.butPrivado) {
            int pos = vClient.lstActivos.getSelectedIndex();
            if (pos >= 0) {
                vPrivada.setAmigo(vClient.nomUsers.get(pos));
                vPrivada.setVisible(true); // Muestra la ventana de chat privado
            }
        }
        if (e.getSource() == vPrivada.getButton()) {
            String mensaje = vPrivada.getTxtMensage().getText();// Obtiene el mensaje
            vPrivada.mostrarMsg(conexion.getNombre() + ">" + mensaje);// Muestra el mensaje
            conexion.flujo(vPrivada.getAmigo(), mensaje); // Envía el mensaje
            vPrivada.getTxtMensage().setText("");// Limpia el campo de texto
        }
    }

    public void ban() {
        vClient.dispose();
        vPrivada.dispose();
        avisos.baneado();
    }

    public void removerUser(String menser) {
        vClient.removerUser(menser);
    }

    public void mensaje(String x){
        vClient.mostrarMsg(x);
    }

}
