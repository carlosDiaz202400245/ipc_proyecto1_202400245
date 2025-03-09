
package Controller;
import Model.*; 
import View.historialTransacciones;
import View.alertas;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author charl
 */
public class historialTransaccionesController {
    private Banco banco;
    private historialTransacciones vista;

    public historialTransaccionesController(Banco banco, historialTransacciones vista) {
        this.banco = banco;
        this.vista = vista;
        initListener();
    }
    
    private void buscarCuentaYMostrarHistorial(){
        String idCuenta =  vista.getTxtSolicitarIdCuenta().getText().trim();
        if(idCuenta.isEmpty()){
            alertas.mostrarAdvertencia("coloque un Id");
            return;
        }
        
        Cliente clienteEncontrado=null;
        Cuenta cuentaEncontrada = null;
        
        Cliente[] clientes = banco.getClientes();
        
        for(Cliente cliente : clientes){
            if(cliente != null){
               Cuenta cuenta = cliente.buscarCuenta(idCuenta);
               if(cuenta !=null ){
               clienteEncontrado = cliente;
               cuentaEncontrada = cuenta;
               break;
               }
            }
        }
        if (clienteEncontrado == null || cuentaEncontrada == null){
            alertas.mostrarError("No se encontro la Cuenta");
            return;
        }
        vista.getTxtMostrarCui().setText(clienteEncontrado.getCUI());
        vista.getTxtMostrarNombre().setText(clienteEncontrado.getNombreCliente());
        vista.getTxtMostrarApellido().setText(clienteEncontrado.getApellidoCliente());
        
        mostrarHistorialTransacciones(cuentaEncontrada);
    }
    
    private void mostrarHistorialTransacciones(Cuenta cuenta){
        DefaultTableModel model = (DefaultTableModel) vista.getTableReporte().getModel();
        model.setRowCount(0);
        Transaccion[] transacciones = cuenta.getTransacciones();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        double saldoVariable = 0.0;
        
        for (Transaccion transaccion : transacciones){
            if(transaccion != null){
                
                if(transaccion.getTipo().equals("deposito")){
                    saldoVariable += transaccion.getMontoDeposito();
                }else if (transaccion.getTipo().equals("debito")){
                    saldoVariable -= transaccion.getMontoDebito();
                
                }
                
                Object[] row = {
                    transaccion.getIdTransaccion(),
                    dateFormat.format(transaccion.getFecha()),
                    transaccion.getTipo(),
                    transaccion.getMontoDebito(),
                    transaccion.getMontoDeposito(),
                    saldoVariable
            };
                model.addRow(row);
            }
        }
    }
    
    public void initListener(){
        vista.getBtnBuscarCuenta().addActionListener(a -> buscarCuentaYMostrarHistorial());
    }
}
