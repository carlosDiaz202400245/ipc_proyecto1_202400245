package Model;

/**
 *
 * @author charl
 */
public class Banco {
    private Cliente[] clientes; 
    private int numClientes;

    public Banco() {
        this.clientes = new Cliente[6];
        this.numClientes = 0;
    }
    
    
    
    public boolean registrarCliente(String CUI, String nombre, String apellido){
        if(numClientes < 6 ){
        for(int i =0; i<numClientes;i++){
            if (clientes[i].getCUI().equals(CUI)){
            return false;
            }
        }
      
        Cliente nuevoCliente = new Cliente(CUI,nombre, apellido);
        clientes[numClientes] = nuevoCliente;
        numClientes++;
        return true;
        }else{
            return false; //se llego al max de clientes
        }
        
    }
    
    public Cliente buscarCliente(String CUI){
        for (int i =0; i<numClientes;i++){
            if (clientes[i].getCUI().equals(CUI)){
                return clientes[i];
            }   
        }
        return null; //no se encontro el clietne
    }
//crear una cuenta a un cliente
    public boolean crearCuenta(String CUI,String idCuenta){
        Cliente cliente = buscarCliente(CUI);
        if(cliente != null){
            Cuenta nuevaCuenta = new Cuenta(CUI);
            return cliente.agregarCuenta(nuevaCuenta);
        }else{
            return  false;
        }
    }
    
//hacer deposito
    public boolean realizarDeposito(String CUI, String idCuenta, double monto){
        Cliente cliente = buscarCliente(CUI);
        if(cliente !=null){
            Cuenta cuenta = cliente.buscarCuenta(idCuenta);
            if(cuenta != null){
                return cuenta.realizarDeposito(monto);
            }
        }
        return false; // por si el cliente o cuenta no existe
    }
    
    public boolean realizarDebito(String CUI, String idCuenta, double monto){
        Cliente cliente = buscarCliente(CUI);
        if(cliente !=null){
            Cuenta cuenta = cliente.buscarCuenta(idCuenta);
            if(cuenta != null){
                return cuenta.realizarRetiro(monto);
            }
        }
        return false; // por si el cliente o cuenta no existe
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public int getNumClientes() {
        return numClientes;
    } 
    
}
