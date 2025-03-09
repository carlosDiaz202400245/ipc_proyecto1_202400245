package Controller;
import Model.*;
import View.alertas;
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
        vistaCrearUsuario.getBtnCrearUsuario().addActionListener(e-> registrarCliente());
    }
    
   private void registrarCliente(){
       String CUI = vistaCrearUsuario.getBtnCUI().getText();
       String nombre = vistaCrearUsuario.getBtnNombre().getText();
       String apellido = vistaCrearUsuario.getBtnApellido().getText();
       
       if (CUI.isBlank() || nombre.isEmpty() || apellido.isBlank()){
            alertas.mostrarAdvertencia("verifique que los campos esten llenos!");
            return;
       }else if(existeCliente(CUI)){
           alertas.mostrarAdvertencia("El CUI: "+CUI+" ya existe!");
       }else if (banco.registrarCliente(CUI,nombre,apellido)){
           alertas.mostrarOk("Cliente registrado exitosamente");
           vistaCrearUsuario.getBtnCUI().setText("");
           vistaCrearUsuario.getBtnNombre().setText("");
           vistaCrearUsuario.getBtnApellido().setText("");
       }else{
           alertas.mostrarError("Se alcanzo el limite de clientes");
       }
   } 
   
   public boolean existeCliente(String CUI){
       Cliente[] clientes = banco.getClientes();
       if(clientes == null){
           return false;
       }
       for(int i =0; i < clientes.length ; i++){
           if(clientes[i] != null && clientes[i].getCUI().equals(CUI)){
           return true;
          }  
       } 
       return false;
   }
}
