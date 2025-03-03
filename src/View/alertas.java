
package View;
import javax.swing.JOptionPane;
/**
 *
 * @author carlos Diaz
 */
public class alertas {
    public static void mostrarInfo(String mensaje){ 
        JOptionPane.showMessageDialog(null, mensaje,"Informacion", JOptionPane.INFORMATION_MESSAGE); 
    }
    public static void mostrarError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
}
    public static void mostrarAdvertencia(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje,"Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    public static boolean mostrarConfirmacion(String mensaje){
       int respuesta =  JOptionPane.showConfirmDialog(null,mensaje,"Confirmacion",JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_NO_OPTION;
    }
    public static void mostrarOk(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje,"Exito",JOptionPane.INFORMATION_MESSAGE);
    }
}

