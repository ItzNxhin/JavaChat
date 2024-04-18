package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Cliente.VentCliente;
import Cliente.Model.ConexionCliente;
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

    }
    
}
