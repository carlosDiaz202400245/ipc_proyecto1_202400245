
package Controllers;
import Models.*;
import View.AlertasViews;
import View.DepositosView;
import Models.BancoModel;
import Models.CuentaModel;
import Models.ClienteModel;
/**
 * @author charl
 */
public class depositosController {
    private BancoModel banco;
    DepositosView vista;

    public depositosController( DepositosView vista, BancoModel banco) {
        this.banco = banco;
        this.vista = vista;
       initListener();
        cargarCuentas();
    }
    
    private void cargarCuentas (){
    ClienteModel[] clientes = banco.getClientes();
        vista.getComboCuentas().removeAllItems();
        for(ClienteModel cliente : clientes){
            if(cliente != null){
                CuentaModel[] cuentas = cliente.getCuentasCliente();
                for (CuentaModel cuenta : cuentas){
                    if(cuenta != null){
                        vista.getComboCuentas().addItem(cuenta.getIdCuenta() + " - "+ cliente.getNombreCliente() + " " + cliente.getApellidoCliente() );
                    }
                }
            }
        }
     }
    
    private void hacerDeposito() {
    try {
        String objetoSeleccionado = (String) vista.getComboCuentas().getSelectedItem();
        if (objetoSeleccionado == null) {
            AlertasViews.mostrarAdvertencia("Seleccione una cuenta!");
            return;
        }

        String idCuenta = objetoSeleccionado.split(" - ")[0];
        double monto = Double.parseDouble(vista.getTxtMonto().getText().trim());

        if (monto <= 0) {
            AlertasViews.mostrarAdvertencia("El monto debe ser mayor a 0");
            return;
        }

        ClienteModel[] clientes = banco.getClientes();
        boolean cuentaEncontrada = false; 

        for (ClienteModel cliente : clientes) {
            if (cliente != null) {
                CuentaModel cuenta = cliente.buscarCuenta(idCuenta);
                if (cuenta != null) {
                    cuentaEncontrada = true; 
                    if (cuenta.realizarDeposito(monto)) {
                        AlertasViews.mostrarOk("Depósito Exitoso, cantidad Depositada: Q" + monto);
                    } else {
                        AlertasViews.mostrarError("Se han excedido las transacciones (25)");
                    }
                    break; 
                }
            }
        }

        if (!cuentaEncontrada) {
            AlertasViews.mostrarAdvertencia("No existe esa cuenta");
        }
    } catch (NumberFormatException e) {
        AlertasViews.mostrarError("Ingrese el monto, no texto!");
    }
}
    
    private void initListener (){
        vista.getBtnDepositar().addActionListener(k -> hacerDeposito());
    }
     
    }
    

