package Models;

/**
 *
 * @author charl
 */
public class BancoModel {
    private ClienteModel[] clientes; 
    private int numClientes;
    private static short contadorCuentas = 0; 

    public BancoModel() {
        this.clientes = new ClienteModel[6];
        this.numClientes = 0;
    }

    
    private String generarIdCuenta() {
        contadorCuentas++; 
        return "D2D025" + contadorCuentas; 
    }

    public boolean registrarCliente(String CUI, String nombre, String apellido) {
        if (numClientes < 6) {
            for (int i = 0; i < numClientes; i++) {
                if (clientes[i].getCUI().equals(CUI)) {
                    return false;
                }
            }

            ClienteModel nuevoCliente = new ClienteModel(CUI, nombre, apellido);
            clientes[numClientes] = nuevoCliente;
            numClientes++;
            return true;
        } else {
            return false; 
        }
    }

    public ClienteModel buscarCliente(String CUI) {
        for (int i = 0; i < numClientes; i++) {
            if (clientes[i].getCUI().equals(CUI)) {
                return clientes[i];
            }
        }
        return null; 
    }

    // Crear una cuenta a un cliente
    public boolean crearCuenta(String CUI) {
        ClienteModel cliente = buscarCliente(CUI);
        if (cliente != null) {
            String idCuenta = generarIdCuenta(); 
            CuentaModel nuevaCuenta = new CuentaModel(idCuenta);
            return cliente.agregarCuenta(nuevaCuenta);
        } else {
            return false;
        }
    }

    // Hacer depósito
    public boolean realizarDeposito(String CUI, String idCuenta, double monto) {
        ClienteModel cliente = buscarCliente(CUI);
        if (cliente != null) {
            CuentaModel cuenta = cliente.buscarCuenta(idCuenta);
            if (cuenta != null) {
                return cuenta.realizarDeposito(monto);
            }
        }
        return false; 
    }

    public boolean realizarDebito(String CUI, String idCuenta, double monto) {
        ClienteModel cliente = buscarCliente(CUI);
        if (cliente != null) {
            CuentaModel cuenta = cliente.buscarCuenta(idCuenta);
            if (cuenta != null) {
                return cuenta.realizarRetiro(monto);
            }
        }
        return false; 
    }

    public ClienteModel[] getClientes() {
        return clientes;
    }

    public int getNumClientes() {
        return numClientes;
    }
}