
package Controller;
import Model.*;
import View.alertas;
import View.vistaRetiros;
/**
 *
 * @author charl
 */
public class vistaDebitosController {
    vistaRetiros vista;
    private Banco banco;

    public vistaDebitosController(vistaRetiros vista, Banco banco) {
        this.vista = vista;
        this.banco = banco;
        initListener();
        cargarCuentas();
    }
    
   private void cargarCuentas (){
    Cliente[] clientes = banco.getClientes();
        vista.getComboCuentas().removeAllItems();
        for(Cliente cliente : clientes){
            if(cliente != null){
                Cuenta[] cuentas = cliente.getCuentasCliente();
                for (Cuenta cuenta : cuentas){
                    if(cuenta != null){
                        vista.getComboCuentas().addItem(cuenta.getIdCuenta() + " - "+ cliente.getNombreCliente() + " " + cliente.getApellidoCliente() );
                    }
                }
            }
        }
     }
    
    private void hacerDebito(){
        try{
            String objetoSeleccionado = (String) vista.getComboCuentas().getSelectedItem();
            String idCuenta = objetoSeleccionado.split(" - ")[0];
            if(objetoSeleccionado == null){
                alertas.mostrarAdvertencia("Seleccione una cuenta!");
                return;
            }
            
            double monto = Double.parseDouble(vista.getTxtMonto().getText().trim());
            if(monto <=0){
                alertas.mostrarAdvertencia("El monto debe ser mayor a 0");
                return;
            }
            
            
       
            Cliente[] clientes = banco.getClientes();
            for(Cliente cliente : clientes){
                if(cliente != null){
                    Cuenta cuenta = cliente.buscarCuenta(idCuenta);
                        if(cuenta != null){
                            if(cuenta.getSaldoCuenta() > 0){
                                if(cuenta.getSaldoCuenta() >= monto){
                                if(cuenta.realizarRetiro(monto)){
                                    alertas.mostrarOk("Retiro Exitoso, cantidad Debitada: Q"+monto);
                                    break;
                                }else{
                                alertas.mostrarError("Se han excedido las transacciones (25)");
                                break;
                                }
                               }else{
                                   alertas.mostrarError("Saldo de la cuenta insuficiente");
                                   return;
                                           
                                }
                            }else{
                                alertas.mostrarAdvertencia("Esta cuenta no tiene fondos, seleccione otra!");
                                return;
                            }
                        }else{
                        alertas.mostrarAdvertencia("No existe esa cuenta");
                        return;
                        }
                }else {
                    alertas.mostrarAdvertencia("No existe el cliente relacionado a esa cuenta");
                }
            }
        } catch(NumberFormatException k){
            alertas.mostrarError("Ingrese el monto, no texto!");
        }
    }
    
    private void initListener (){
        vista.getBtnDepositar().addActionListener(k -> hacerDebito());
    }
     
    
    
}
