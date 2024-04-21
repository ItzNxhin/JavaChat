package Cliente.View;

import javax.swing.JOptionPane;

public class Avisos {
    public Avisos(){}
    
    public void consola(String x){
        System.out.println(x);
    }
    public String nick(){
        return JOptionPane.showInputDialog("Introducir Nick :");
    }
}
