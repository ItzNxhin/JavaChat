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

    private JTextArea panMostrar;
    private JMenu JMAcerca;
    private JLabel lblNomUser;
    private JMenuBar barraMenu;
    private JMenu JMAyuda;

    @SuppressWarnings("rawtypes")
    public JList lstActivos;
    public JButton butPrivado;
    public JMenuItem help;
    public JMenuItem acercaD;
    public JTextField txtMensage;
    public JButton butEnviar;

    public Vector<String> nomUsers;

    /** Creates a new instance of Cliente */
    public VentanaCliente() throws IOException {
        super("Cliente Chat");
        txtMensage = new JTextField(30);
        butEnviar = new JButton("Enviar");
        lblNomUser = new JLabel("Usuario <<  >>");
        lblNomUser.setHorizontalAlignment(JLabel.CENTER);
        panMostrar = new JTextArea();
        panMostrar.setColumns(25);

        lstActivos = new JList<>();
        butPrivado = new JButton("Privado");

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
        panMostrar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(25, 10, 80)));

        JPanel panAbajo = new JPanel();
        panAbajo.setLayout(new BorderLayout());
        panAbajo.add(new JLabel("  Ingrese mensage a enviar:"), BorderLayout.NORTH);
        panAbajo.add(txtMensage, BorderLayout.CENTER);
        panAbajo.add(butEnviar, BorderLayout.EAST);
        JPanel panRight = new JPanel();
        panRight.setLayout(new BorderLayout());
        panRight.add(lblNomUser, BorderLayout.NORTH);
        panRight.add(new JScrollPane(panMostrar), BorderLayout.CENTER);
        panRight.add(panAbajo, BorderLayout.SOUTH);
        JPanel panLeft = new JPanel();
        panLeft.setLayout(new BorderLayout());
        panLeft.add(new JScrollPane(this.lstActivos), BorderLayout.CENTER);
        panLeft.add(this.butPrivado, BorderLayout.NORTH);
        JSplitPane sldCentral = new JSplitPane();
        sldCentral.setDividerLocation(100);
        sldCentral.setDividerSize(7);
        sldCentral.setOneTouchExpandable(true);
        sldCentral.setLeftComponent(panLeft);
        sldCentral.setRightComponent(panRight);

        setLayout(new BorderLayout());
        add(sldCentral, BorderLayout.CENTER);
        add(barraMenu, BorderLayout.NORTH);

        txtMensage.requestFocus();// pedir el focus

        nomUsers = new Vector<>();

        setSize(450, 430);
        setLocation(120, 90);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setNombreUser(String user) {
        lblNomUser.setText("Usuario " + user);
    }

    public void mostrarMsg(String msg) {
        this.panMostrar.append(msg + "\n");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void ponerActivos(Vector datos) {
        nomUsers = datos;
        ponerDatosList(this.lstActivos, nomUsers);
    }

    public void agregarUser(String user) {
        nomUsers.add(user);
        ponerDatosList(this.lstActivos, nomUsers);
    }

    public void retirraUser(String user) {
        nomUsers.remove(user);
        ponerDatosList(this.lstActivos, nomUsers);
    }

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
