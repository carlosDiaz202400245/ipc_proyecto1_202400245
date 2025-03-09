
package Controllers;
import Models.ClienteModel;
import Models.BancoModel;
import View.*;
import View.AlertasViews;

/**
 * @author charl
 */
public class crearCuentaController {
    private CrearCuentaView vista;
    private BancoModel banco;

    public crearCuentaController(CrearCuentaView vista, BancoModel banco) {
        this.vista = vista;
        this.banco = banco;
        cargarClientes();
        initListeners();
    }
    
    private void cargarClientes(){
        ClienteModel[] clientes = banco.getClientes();
        vista.getCmbxClientes().removeAllItems();
        for (ClienteModel cliente : clientes){
            if (cliente != null){
                vista.getCmbxClientes().addItem(cliente.getCUI() + " - "+ cliente.getNombreCliente()+ " "+ cliente.getApellidoCliente()); 
            }
        }
    }
    private void crearCuenta(){
        String clienteSeleccionado = (String) vista.getCmbxClientes().getSelectedItem();
        if(clienteSeleccionado == null){
            AlertasViews.mostrarAdvertencia("seleccione un cliente!");
            return;
        }
        String CUI = clienteSeleccionado.split(" - ")[0];
        
        if(banco.crearCuenta(CUI)){
            AlertasViews.mostrarOk("Cuenta creada Exitosamente");
        }else {
            AlertasViews.mostrarError("Excedio el numero de Cuentas por este cliente");
        }
    }
    
    private void initListeners(){
        vista.getBtnCrearCuenta().addActionListener(a -> crearCuenta());
    }
}
