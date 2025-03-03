
package Model;

/**
 *
 * @author charl
 */
public class Cliente {
    private String CUI;
    private String nombreCliente;
    private String apellidoCliente;
    private Cuenta[] cuentasCliente;
    private int numCuentas;

    public Cliente(String CUI, String nombreCliente, String apellidoCliente) {
        this.CUI = CUI;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.cuentasCliente = new Cuenta[3];
        this.numCuentas = 0;
    }

    public String getCUI() {
        return CUI;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public Cuenta[] getCuentasCliente() {
        return cuentasCliente;
    }

    public int getNumCuentas() {
        return numCuentas;
    }
    
    public boolean agregarCuenta(Cuenta cuenta){
        if (numCuentas < 3){
            cuentasCliente[numCuentas] = cuenta;
            numCuentas ++;
            return true;
        }else{
            return false; // no se pueden agregar mas cuentas
        }
    }
    
    public Cuenta buscarCuenta(String idCuenta){
        for(int i =0; i<numCuentas;i++){
            if (cuentasCliente[i].getIdCuenta().equals(idCuenta)){
            return cuentasCliente[i];
            }
        }
        return null; //no se encontro la cuenta
    }

}
