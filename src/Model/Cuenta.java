
package Model;

/**
 *
 * @author charl
 */
public class Cuenta {
    private String idCuenta;
    private double saldoCuenta;
    private Transaccion[] transacciones;
    private int numTransacciones; //contador

    public Cuenta(String idCuenta) {
        this.idCuenta = idCuenta;
        this.saldoCuenta = 0.0;
        this.transacciones = new Transaccion[25]; // solo 25 transaccines
        this.numTransacciones = 0; //contador
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public Transaccion[] getTransacciones() {
        return transacciones;
    }

    public int getNumTransacciones() {
        return numTransacciones;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public void setTransacciones(Transaccion[] transacciones) {
        this.transacciones = transacciones;
    }

    public void setNumTransacciones(int numTransacciones) {
        this.numTransacciones = numTransacciones;
    }
    
     public boolean realizarDeposito(double monto) {
        // Crear una nueva transacción de depósito
        Transaccion deposito = new Transaccion(
            "TXN" + (numTransacciones + 1), // ID de la transacción
            "deposito",                     // Tipo de transacción
            0.0,                            
            monto                           
        );

        // Agregar la transacción a la cuenta
        if (agregarTransaccion(deposito)) {
            saldoCuenta += monto; 
            return true;          
        } else {
            return false;         
        }
    }

    // Método para agregar una transacción
    private boolean agregarTransaccion(Transaccion transaccion) {
        if (numTransacciones < 25) {
            transacciones[numTransacciones] = transaccion;
            numTransacciones++;
            return true; 
        } else {
            return false; 
        }
    }
     public boolean realizarRetiro(double monto) {
   
    Transaccion retiro = new Transaccion(
        "TXN" + (numTransacciones + 1), 
        "debito",                       
        monto,                         
        0.0                            
    );

    // Agregar la transacción a la cuenta
    if (agregarTransaccion(retiro)) {
        saldoCuenta -= monto; 
        return true;          
    } else {
        return false;        
    }
}
}