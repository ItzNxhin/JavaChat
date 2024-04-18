package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Cliente.Cliente;
import Cliente.VentCliente;
import Cliente.Model.ConexionCliente;
import Cliente.View.VentanaAyuda;
import Cliente.View.Avisos;

public class ClienteControl implements ActionListener{

    private VentCliente vClient;
    private ConexionCliente conexion;
    private Avisos avisos;
    

    public ClienteControl() throws IOException{
        avisos = new Avisos();
        vClient =  new VentCliente();
        conexion = new ConexionCliente(avisos::consola);
        iniciar();
        
    }
    private void iniciar() throws IOException {
        String nick = avisos.nick() ;
        conexion.setNomCliente(nick);
        vClient.setNombreUser(nick);
        conexion.conexion();
        new HiloCliente(conexion.getEntrada2(), vClient, avisos::consola).start();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().compareTo("help") == 0){
            vAyuda = new VentanaAyuda();
            vAyuda.setVisible(true);
        }
        if (e.getActionCommand().compareTo("Acerca") == 0) {
            /*Aca deberiamos llamar la clase de los avisos 
            con el aviso que dice en el action performed de la ventana cliente*/
        }
        if (e.getSource()==vClient.butEnviar || e.getSource()==vClient.txtMensage){
        String mensaje = vClient.txtMensage.getText();     
           cliente.flujo(mensaje);
           vClient.txtMensage.setText("");
        }
    }
    
}
