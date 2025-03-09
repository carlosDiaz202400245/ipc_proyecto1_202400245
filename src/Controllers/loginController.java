
package Controllers;
import View.AlertasViews;
import View.MenuPrincipalView;
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
            AlertasViews.mostrarOk("Bienvenido");
            loginFrame.dispose();
            MenuPrincipalView menu = new MenuPrincipalView();
            menu.setVisible(true);
        }else{
            AlertasViews.mostrarError("Datos de ingreso inválidos, revise sus credenciales");
        }
    }
}
