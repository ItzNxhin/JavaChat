package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Cliente.VentCliente;
import Cliente.Model.ConexionCliente;

public class ClienteControl implements ActionListener{

    public VentCliente vClient;
    private ConexionCliente conexion;

    public ClienteControl() throws IOException{
        vClient =  new VentCliente();
        conexion = new ConexionCliente();
        conexion.conexion();
        new HiloCliente(conexion.getEntrada2(), vClient).start();
    }

    public void actionPerformed(ActionEvent e){

    }
    
}
