
package Controllers;
import Models.ClienteModel; 
import Models.BancoModel;
import Models.CuentaModel;
import Models.TransaccionModel;
import View.HistorialTransaccionesView;
import View.AlertasViews;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author charl
 */
public class historialTransaccionesController {
    private BancoModel banco;
    private HistorialTransaccionesView vista;

    public historialTransaccionesController(BancoModel banco, HistorialTransaccionesView vista) {
        this.banco = banco;
        this.vista = vista;
        initListener();
    }
    
    private void buscarCuentaYMostrarHistorial(){
        String idCuenta =  vista.getTxtSolicitarIdCuenta().getText().trim();
        if(idCuenta.isEmpty()){
            AlertasViews.mostrarAdvertencia("coloque un Id");
            return;
        }
        
        ClienteModel clienteEncontrado=null;
        CuentaModel cuentaEncontrada = null;
        
        ClienteModel[] clientes = banco.getClientes();
        
        for(ClienteModel cliente : clientes){
            if(cliente != null){
               CuentaModel cuenta = cliente.buscarCuenta(idCuenta);
               if(cuenta !=null ){
               clienteEncontrado = cliente;
               cuentaEncontrada = cuenta;
               break;
               }
            }
        }
        if (clienteEncontrado == null || cuentaEncontrada == null){
            AlertasViews.mostrarError("No se encontro la Cuenta");
            return;
        }
        vista.getTxtMostrarCui().setText(clienteEncontrado.getCUI());
        vista.getTxtMostrarNombre().setText(clienteEncontrado.getNombreCliente());
        vista.getTxtMostrarApellido().setText(clienteEncontrado.getApellidoCliente());
        
        mostrarHistorialTransacciones(cuentaEncontrada);
    }
    
    private void mostrarHistorialTransacciones(CuentaModel cuenta){
        DefaultTableModel model = (DefaultTableModel) vista.getTableReporte().getModel();
        model.setRowCount(0);
        TransaccionModel[] transacciones = cuenta.getTransacciones();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        double saldoVariable = 0.0;
        
        for (TransaccionModel transaccion : transacciones){
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
