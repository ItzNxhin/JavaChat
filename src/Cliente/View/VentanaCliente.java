package Cliente.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaCliente extends JFrame {

    private JTextArea panMostrar;// Área de texto para mostrar mensajes
    private JMenu JMAcerca;// Menú
    private JLabel lblNomUser; // Etiqueta para mostrar el nombre de usuario
    private JMenuBar barraMenu; // Barra de menú
    private JMenu JMAyuda; // Menú "Ayuda"

    @SuppressWarnings("rawtypes")
    public JList lstActivos; // Lista de usuarios activos
    public JButton butPrivado; // Botón para enviar mensajes privados
    public JMenuItem help; // Elemento de menú para la ayuda
    public JMenuItem acercaD; // Elemento de menú para los créditos
    public JTextField txtMensage; // Campo de texto para escribir mensajes
    public JButton butEnviar; // Botón para enviar mensajes
    // Vector para almacenar los nombres de usuario activos
    public Vector<String> nomUsers;

    /** Creates a new instance of Cliente */
    public VentanaCliente() throws IOException {
        super("Cliente Chat");
        txtMensage = new JTextField(30); // Campo de texto para escribir mensajes
        butEnviar = new JButton("Enviar"); // Botón para enviar mensajes
        lblNomUser = new JLabel("Usuario <<  >>"); // Etiqueta para mostrar el nombre de usuario
        lblNomUser.setHorizontalAlignment(JLabel.CENTER);
        panMostrar = new JTextArea();
        panMostrar.setColumns(25);

        lstActivos = new JList<>();// Lista de usuarios activos
        butPrivado = new JButton("Privado"); // Botón para enviar mensajes privados

        barraMenu = new JMenuBar();
        JMAyuda = new JMenu("Ayuda");
        help = new JMenuItem("Ayuda");

        JMAcerca = new JMenu("Acerca de");
        acercaD = new JMenuItem("Creditos");

        JMAyuda.add(help);
        JMAcerca.add(acercaD);
        barraMenu.add(JMAcerca);
        barraMenu.add(JMAyuda);

        panMostrar.setEditable(false);
        panMostrar.setForeground(Color.BLUE);
        // Borde del área de texto
        panMostrar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(25, 10, 80)));

        JPanel panAbajo = new JPanel(); // Panel para la parte inferior de la ventana
        panAbajo.setLayout(new BorderLayout()); // Diseño del panel como BorderLayout

        panAbajo.add(new JLabel("  Ingrese mensage a enviar:"), BorderLayout.NORTH);
        panAbajo.add(txtMensage, BorderLayout.CENTER); // Campo de texto para escribir mensajes
        panAbajo.add(butEnviar, BorderLayout.EAST); // Botón para enviar mensajes
        JPanel panRight = new JPanel(); // Panel para la parte derecha de la ventana
        panRight.setLayout(new BorderLayout()); // Diseño del panel como BorderLayout
        panRight.add(lblNomUser, BorderLayout.NORTH);// Etiqueta para mostrar el nombre de usuario
        panRight.add(new JScrollPane(panMostrar), BorderLayout.CENTER);
        panRight.add(panAbajo, BorderLayout.SOUTH);
        JPanel panLeft = new JPanel(); // Panel para la parte izquierda de la ventana
        panLeft.setLayout(new BorderLayout()); // Diseño del panel como BorderLayout
        panLeft.add(new JScrollPane(this.lstActivos), BorderLayout.CENTER);// Lista de usuarios activos con barra de
                                                                           // desplazamiento
        // Botón para enviar mensajes privados en la parte superior del panel
        panLeft.add(this.butPrivado, BorderLayout.NORTH);
        JSplitPane sldCentral = new JSplitPane();
        sldCentral.setDividerLocation(100); // Ubicación inicial del separador
        sldCentral.setDividerSize(7); // Tamaño del separador
        sldCentral.setOneTouchExpandable(true); // Permite expandir o contraer el panel izquierdo
        sldCentral.setLeftComponent(panLeft);
        sldCentral.setRightComponent(panRight);

        setLayout(new BorderLayout()); // Diseño de la ventana como BorderLayout
        add(sldCentral, BorderLayout.CENTER); // Agrega el separador
        add(barraMenu, BorderLayout.NORTH); // Agrega la barra de menú

        txtMensage.requestFocus();// pedir el focus

        nomUsers = new Vector<>(); // Inicializa el vector

        setSize(450, 430);
        setLocation(120, 90);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la ventana
    }

    /**
     * Método para establecer el nombre de usuario en la etiqueta
     */

    public void setNombreUser(String user) {
        lblNomUser.setText("Usuario " + user);
    }

    /**
     * Método para mostrar un mensaje en el área de texto
     */

    public void mostrarMsg(String msg) {
        this.panMostrar.append(msg + "\n");
    }

    /**
     * Método para actualizar la lista de usuarios activos
     */

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void ponerActivos(Vector datos) {
        nomUsers = datos;
        ponerDatosList(this.lstActivos, nomUsers);
    }

    /**
     * Método para agregar un usuario a la lista de usuarios activos
     */
    public void agregarUser(String user) {
        nomUsers.add(user);
        ponerDatosList(this.lstActivos, nomUsers);
    }

    /**
     * Método para eliminar un usuario de la lista de usuarios activos
     */

    public void retirraUser(String user) {
        nomUsers.remove(user);
        ponerDatosList(this.lstActivos, nomUsers);
    }

    /**
     * Método privado para configurar los datos en la lista
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void ponerDatosList(JList list, final Vector datos) {
        list.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return datos.size();
            }

            @Override
            public Object getElementAt(int i) {
                return datos.get(i);
            }
        });
    }

}
