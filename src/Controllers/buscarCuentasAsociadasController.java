
package Controllers;
import Models.ClienteModel;
import Models.BancoModel;
import Models.CuentaModel;
import View.AlertasViews;
import View.BuscarCuentasAsociadasView;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author charl
 */
public class buscarCuentasAsociadasController {
    private BuscarCuentasAsociadasView vista;
    private BancoModel banco;

    public buscarCuentasAsociadasController(BuscarCuentasAsociadasView vista, BancoModel banco) {
        this.vista = vista;
        this.banco = banco;
        cargarClientes();
        initListeners();
        
    }
    
    private void cargarClientes(){
      ClienteModel[] clientes = banco.getClientes();
      DefaultTableModel model = (DefaultTableModel) vista.getTableUsuarios().getModel();
      model.setRowCount(0);
      
      for(ClienteModel cliente : clientes){
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
            AlertasViews.mostrarAdvertencia("Coloque un CUI");
            return;
        }
        ClienteModel cliente = banco.buscarCliente(CUI);
        if (cliente == null){
            AlertasViews.mostrarError("No existe ese cliente");
            return;
        }
        CuentaModel[] cuentas = cliente.getCuentasCliente();
        DefaultTableModel model = (DefaultTableModel) vista.getTableCuentasUsuarios().getModel();
        model.setRowCount(0);
        for (CuentaModel cuenta : cuentas){
            if(cuenta != null){
            Object[] row = {cuenta.getIdCuenta()};
            model.addRow(row);
            }
        }
    }
    
}
