
package Controller;
import View.alertas;
import View.buscarCuentasAsociadas;
import Model.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author charl
 */
public class buscarCuentasAsociadasController {
    private buscarCuentasAsociadas vista;
    private Banco banco;

    public buscarCuentasAsociadasController(buscarCuentasAsociadas vista, Banco banco) {
        this.vista = vista;
        this.banco = banco;
        cargarClientes();
        initListeners();
        
    }
    
    private void cargarClientes(){
      Cliente[] clientes = banco.getClientes();
      DefaultTableModel model = (DefaultTableModel) vista.getTableUsuarios().getModel();
      model.setRowCount(0);
      
      for(Cliente cliente : clientes){
          if(cliente != null){
              Object[] row = {cliente.getCUI(), cliente.getNombreCliente(), cliente.getApellidoCliente()};
              model.addRow(row);
          }
      }
    }
    private void initListeners(){
      vista.getBtnBuscarCuentas().addActionListener (a -> buscarCuentasAsociadas());
    }
    
    private void buscarCuentasAsociadas(){
        String CUI = vista.getTxtBuscarPorCui().getText().trim();
        if (CUI.isEmpty()){
            alertas.mostrarAdvertencia("Coloque un CUI");
            return;
        }
        Cliente cliente = banco.buscarCliente(CUI);
        if (cliente == null){
            alertas.mostrarError("No existe ese cliente");
            return;
        }
        Cuenta[] cuentas = cliente.getCuentasCliente();
        DefaultTableModel model = (DefaultTableModel) vista.getTableCuentasUsuarios().getModel();
        model.setRowCount(0);
        for (Cuenta cuenta : cuentas){
            if(cuenta != null){
            Object[] row = {cuenta.getIdCuenta()};
            model.addRow(row);
            }
        }
    }
    
}
