package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Cliente.Cliente;
import Cliente.VentCliente;
import Cliente.Model.ConexionCliente;
import Cliente.View.VentanaAyuda;

public class ClienteControl implements ActionListener{

    public VentCliente vClient;
    private ConexionCliente conexion;
    private VentanaAyuda vAyuda;
    private Cliente cliente;

    public ClienteControl() throws IOException{
        vClient =  new VentCliente();
        cliente =  new Cliente(vClient);
        vClient.setNombreUser(cliente.getNomCliente());
        cliente.conexion();
        conexion = new ConexionCliente();
        conexion.conexion();
        new HiloCliente(conexion.getEntrada2(), vClient).start();
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
