package Cliente.View;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

/**
 *
 * @author Administrador
 */
public class VentanaPrivada extends JFrame {
   JTextArea panMostrar;
   JTextField txtMensage;
   JButton butEnviar;
   String amigo;

   public VentanaPrivada() {
      super("Amigo");
      txtMensage = new JTextField(30);
      butEnviar = new JButton("Enviar");
      panMostrar = new JTextArea();
      panMostrar.setEditable(false);
      txtMensage.requestFocus();

      JPanel panAbajo = new JPanel();
      panAbajo.setLayout(new BorderLayout());
      panAbajo.add(new JLabel("  Ingrese mensage a enviar:"),
            BorderLayout.NORTH);
      panAbajo.add(txtMensage, BorderLayout.CENTER);
      panAbajo.add(butEnviar, BorderLayout.EAST);

      setLayout(new BorderLayout());
      add(new JScrollPane(panMostrar), BorderLayout.CENTER);
      add(panAbajo, BorderLayout.SOUTH);

      amigo = "";

      this.addWindowListener(new WindowListener() {
         public void windowClosing(WindowEvent e) {
            cerrarVentana();
         }

         public void windowClosed(WindowEvent e) {
         }

         public void windowOpened(WindowEvent e) {
         }

         public void windowIconified(WindowEvent e) {
         }

         public void windowDeiconified(WindowEvent e) {
         }

         public void windowActivated(WindowEvent e) {
         }

         public void windowDeactivated(WindowEvent e) {
         }

      });

      setSize(300, 300);
      setLocation(570, 90);
   }

   public void setAmigo(String ami) {
      this.amigo = ami;
      this.setTitle(ami);
   }

   private void cerrarVentana() {
      this.setVisible(false);
   }

   public void mostrarMsg(String msg) {
      this.panMostrar.append(msg + "\n");
   }

   public JButton getButton() {
      return this.butEnviar;
   }

   public JTextField getTxtMensage() {
      return this.txtMensage;
   }

   public String getAmigo() {
      return this.amigo;
   }
}
