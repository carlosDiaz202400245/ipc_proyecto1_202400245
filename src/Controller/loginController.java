
package Controller;
import View.alertas;
import View.menuPrincipal;
import javax.swing.JFrame;
/**
 *
 * @author charl
 */
public class loginController {
    private static final String USUARIO_CORRECTO= "ipcD1s2025";
    private static final String CONTRASEÑA_CORRECTA = "ipcLogin";
    
    public void validarCredenciales (String usuario, String contraseña, JFrame loginFrame){
        if (usuario.equals(USUARIO_CORRECTO) && contraseña.equals(CONTRASEÑA_CORRECTA)){
            alertas.mostrarOk("Bienvenido");
            loginFrame.dispose();
            menuPrincipal menu = new menuPrincipal();
            menu.setVisible(true);
        }else{
            alertas.mostrarError("Datos de ingreso inválidos, revise sus credenciales");
        }
    }
}
