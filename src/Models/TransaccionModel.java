
package Models;
import java.util.Date;

/**
 *
 * @author carlos Diaz
 */
public class TransaccionModel {
    private String idTransaccion;
    private Date fecha;
    private String tipo;
    private double montoDebito;
    private double montoDeposito;

    public TransaccionModel(String idTransaccion, String tipo, double montoDebitado, double montoDepositado) {
        this.idTransaccion = idTransaccion;
        this.fecha = new Date();
        this.tipo = tipo;
        this.montoDebito = montoDebitado;
        this.montoDeposito = montoDepositado;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMontoDebito() {
        return montoDebito;
    }

    public double getMontoDeposito() {
        return montoDeposito;
    }
    
}
