
package Controller;
import Model.*;
import View.*;
import View.alertas;

/**
 * @author charl
 */
public class crearCuentaController {
    private crearCuenta vista;
    private Banco banco;
    private short contador = 0;

    public crearCuentaController(crearCuenta vista, Banco banco) {
        this.vista = vista;
        this.banco = banco;
        cargarClientes();
        initListeners();
    }
    
    private void cargarClientes(){
        Cliente[] clientes = banco.getClientes();
        vista.getCmbxClientes().removeAllItems();
        for (Cliente cliente : clientes){
            if (cliente != null){
                vista.getCmbxClientes().addItem(cliente.getCUI() + " - "+ cliente.getNombreCliente()+ " "+ cliente.getApellidoCliente()); 
            }
        }
    }
    private void crearCuenta(){
        String clienteSeleccionado = (String) vista.getCmbxClientes().getSelectedItem();
        if(clienteSeleccionado == null){
            alertas.mostrarAdvertencia("seleccione un cliente!");
            return;
        }
        String CUI = clienteSeleccionado.split(" - ")[0];
        String idCuenta = generarIdCuenta();
        contador += 1;
        if(banco.crearCuenta(CUI, idCuenta)){
            alertas.mostrarOk("Cuenta creada Exitosamente");
        }else {
            alertas.mostrarError("Excedio el numero de Cuentas por este cliente");
        }
    }
    private String generarIdCuenta(){
        return "D2D025" + contador;
    }
    
    private void initListeners(){
        vista.getBtnCrearCuenta().addActionListener(a -> crearCuenta());
    }
}
