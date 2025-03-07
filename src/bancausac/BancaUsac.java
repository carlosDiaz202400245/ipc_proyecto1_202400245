
package bancausac;

import Controller.loginController;
import View.Login;

/**
 *
 * @author Carlos Díaz
 */
public class BancaUsac {

    public static void main(String[] args) {
       Login vistaLogin = new Login();
       
       loginController controller = new loginController();
       
       vistaLogin.setController(controller);
       
       vistaLogin.setVisible(true);
    }
    
}
