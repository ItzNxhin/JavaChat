package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Cliente.VentCliente;
import Cliente.Model.ConexionCliente;
import Cliente.View.VentanaAyuda;
import Cliente.View.Avisos;
import Cliente.View.VentanaPrivada;

public class ClienteControl implements ActionListener{

    private VentCliente vClient;
    private ConexionCliente conexion;
    private Avisos avisos;
    private VentanaAyuda vAyuda;
    private VentanaPrivada vPrivada;
    private ConexionCliente cliente;

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

        vPrivada.getButton().addActionListener(this);
        vPrivada.getTxtMensage().addActionListener(this);
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
           conexion.flujo(mensaje);
           vClient.txtMensage.setText("");
        }
        if(e.getSource()==vClient.butPrivado)
        {
           int pos=vClient.lstActivos.getSelectedIndex();
           if(pos>=0)              
           {
              vPrivada.setAmigo(vClient.nomUsers.get(pos));           
              vPrivada.setVisible(true);
              if(e.getSource() == vPrivada.getButton()){
                String mensaje = vPrivada.getTxtMensage().getText();              
                vPrivada.mostrarMsg(cliente.getNombre()+">"+mensaje);
                cliente.flujo(vPrivada.getAmigo(),mensaje);
                vPrivada.getTxtMensage().setText("");
              }
           }
        }
    }
    
}
