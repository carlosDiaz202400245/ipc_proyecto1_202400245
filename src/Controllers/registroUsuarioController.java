package Controllers;
import Models.ClienteModel;
import Models.BancoModel;
import View.AlertasViews;
import View.CrearUsuarioView;
/**
 *
 * @author charl
 */
public class registroUsuarioController {
    private CrearUsuarioView vistaCrearUsuario;
    private BancoModel banco;

    public registroUsuarioController(CrearUsuarioView vistaCrearUsuario, BancoModel banco) {
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
            AlertasViews.mostrarAdvertencia("verifique que los campos esten llenos!");
            return;
       }else if(existeCliente(CUI)){
           AlertasViews.mostrarAdvertencia("El CUI: "+CUI+" ya existe!");
       }else if (banco.registrarCliente(CUI,nombre,apellido)){
           AlertasViews.mostrarOk("Cliente registrado exitosamente");
           vistaCrearUsuario.getBtnCUI().setText("");
           vistaCrearUsuario.getBtnNombre().setText("");
           vistaCrearUsuario.getBtnApellido().setText("");
       }else{
           AlertasViews.mostrarError("Se alcanzo el limite de clientes");
       }
   } 
   
   public boolean existeCliente(String CUI){
       ClienteModel[] clientes = banco.getClientes();
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
