public class ConexionModelo {

    private String nombre;
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public ConexionModelo(String nombre, Socket socket, DataInputStream entrada, DataOutputStream salida) {
        this.nombre = nombre;
        this.socket = socket;
        this.entrada = entrada;
        this.salida = salida;
    }

    public String getNombre() {
        return nombre;
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public DataOutputStream getSalida() {
        return salida;
    }
}
