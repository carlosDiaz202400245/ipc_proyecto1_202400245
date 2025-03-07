package Controller;
import Model.*;
import View.crearUsuario;
/**
 *
 * @author charl
 */
public class registroUsuarioController {
    private crearUsuario vistaCrearUsuario;
    private Banco banco;

    public registroUsuarioController(crearUsuario vistaCrearUsuario, Banco banco) {
        this.vistaCrearUsuario = vistaCrearUsuario;
        this.banco=banco;
        initListeners();
    }
    private void initListeners(){
        vistaCrearUsuario.getBtnCrearUsuario().addActionListener(e-> registrarCliente);
    }
    
}
