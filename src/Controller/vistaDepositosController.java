
package Controller;
import model.*;
import View.alertas;
import View.vistaDepositos;
import Model.Banco;
import Model.Cuenta;
import Model.Cliente;
/**
 * @author charl
 */
public class vistaDepositosController {
    private Banco banco;
    vistaDepositos vista;

    public vistaDepositosController( vistaDepositos vista, Banco banco) {
        this.banco = banco;
        this.vista = vista;
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
    
    private void hacerDeposito(){
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
                            if(cuenta.realizarDeposito(monto)){
                               alertas.mostrarOk("Deposito Exitoso, cantidad Depositada: Q"+monto);
                               break;
                            }else{
                                alertas.mostrarError("Se han excedido las transacciones (25)");
                                break;
                            }
                    } else{
                        alertas.mostrarAdvertencia("No existe esa cuenta");
                        return;
                    }
                } else {
                    alertas.mostrarAdvertencia("No existe el cliente relacionado a esa cuenta");
                }
            }
        } catch(NumberFormatException k){
            alertas.mostrarError("Ingrese el monto, no texto!");
        }
    }
    
    private void initListener (){
        vista.getBtnDepositar().addActionListener(k -> hacerDeposito());
    }
     
    }
    

