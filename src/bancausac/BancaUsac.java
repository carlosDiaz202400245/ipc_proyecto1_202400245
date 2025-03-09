
package bancausac;

import Controllers.loginController;
import View.LoginView;

/**
 *
 * @author Carlos Díaz
 */
public class BancaUsac {

    public static void main(String[] args) {
       LoginView vistaLogin = new LoginView();
       
       loginController controller = new loginController();
       
       vistaLogin.setController(controller);
       
       vistaLogin.setVisible(true);
    }
    
}
