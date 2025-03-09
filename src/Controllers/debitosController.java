
package Controllers;
import Models.ClienteModel;
import Models.BancoModel;
import Models.CuentaModel;
import View.AlertasViews;
import View.RetirosView;
/**
 *
 * @author charl
 */
public class debitosController {
    RetirosView vista;
    private BancoModel banco;

    public debitosController(RetirosView vista, BancoModel banco) {
        this.vista = vista;
        this.banco = banco;
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
    
   private void hacerDebito() {
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
        boolean cuentaEncontrada = false; //

        for (ClienteModel cliente : clientes) {
            if (cliente != null) {
                CuentaModel cuenta = cliente.buscarCuenta(idCuenta);
                if (cuenta != null) {
                    cuentaEncontrada = true; // 

                    if (cuenta.getSaldoCuenta() > 0) {
                        if (cuenta.getSaldoCuenta() >= monto) {
                            if (cuenta.realizarRetiro(monto)) {
                                AlertasViews.mostrarOk("Retiro Exitoso, cantidad Debitada: Q" + monto);
                            } else {
                                AlertasViews.mostrarError("Se han excedido las transacciones (25)");
                            }
                        } else {
                            AlertasViews.mostrarError("Saldo de la cuenta insuficiente");
                        }
                    } else {
                        AlertasViews.mostrarAdvertencia("Esta cuenta no tiene fondos, seleccione otra!");
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
        vista.getBtnDepositar().addActionListener(k -> hacerDebito());
    }
     
    
    
}
