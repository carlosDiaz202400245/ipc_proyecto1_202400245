
package Controller;

import View.*;
import Model.Banco;

/**
 *
 * @author charl
 */
public class menuPrincipalController {
    private menuPrincipal view;
    Banco banco = new Banco();
    public menuPrincipalController (menuPrincipal view){
        this.view = view;
        initListeners();
    }
    
    private void initListeners(){
        view.getBtnDatosEstudiante().addActionListener(e -> mostrarDatosEstudiante());

        // Botón "Crear Cuenta"
        view.getBtnCrearCuenta().addActionListener(e -> crearCuenta());

        // Botón "Retiros"
        view.getBtnRetiros().addActionListener(e -> realizarRetiro());

        // Botón "Depósitos"
        view.getBtnDepositos().addActionListener(e -> realizarDeposito());

        // Botón "Registro de Usuario"
        view.getBtnCrearUsuario().addActionListener(e -> registrarUsuario());

        // Botón "Buscar Cuenta"
        view.getBtnBuscarCuenta().addActionListener(e -> buscarCuenta());

        // Botón "Reportes"
        view.getBtnReportes().addActionListener(e -> generarReportes());

        // Botón "Historial"
        view.getBtnHistorial().addActionListener(e -> verHistorial());
}
     private void mostrarDatosEstudiante() {
        System.out.println("Mostrando datos del estudiante");
        datosEstudiante datos = new datosEstudiante();
        datos.setVisible(true);
        datos.setLocationRelativeTo(null);
    }

    private void crearCuenta() {
        System.out.println("Creando cuenta");
        crearCuenta vistaCrearCuenta = new crearCuenta();
        new crearCuentaController(vistaCrearCuenta,banco);
        vistaCrearCuenta.setVisible(true);
        vistaCrearCuenta.setLocationRelativeTo(null);
    }

    private void realizarRetiro() {
        System.out.println("Realizando retiro");
        vistaRetiros vistaRetiro = new vistaRetiros();
        new vistaDebitosController(vistaRetiro,banco);
        vistaRetiro.setVisible(true);
        vistaRetiro.setLocationRelativeTo(null);
        
    }

    private void realizarDeposito() {
        System.out.println("Realizando depósito");
       vistaDepositos vistaDepositos = new vistaDepositos();
       new vistaDepositosController(vistaDepositos, banco);
       vistaDepositos.setVisible(true);
       vistaDepositos.setLocationRelativeTo(null);
       
    }

    private void registrarUsuario() {
        System.out.println("Registrando usuario");
        crearUsuario vistaCrearUsuario = new crearUsuario();
        new registroUsuarioController(vistaCrearUsuario,banco);
        vistaCrearUsuario.setVisible(true);
        vistaCrearUsuario.setLocationRelativeTo(null);
        
    }

    private void buscarCuenta() {
        System.out.println("Buscando cuenta");
        buscarCuentasAsociadas vistaBuscarCuentas = new buscarCuentasAsociadas();
        new buscarCuentasAsociadasController(vistaBuscarCuentas,banco);
        vistaBuscarCuentas.setVisible(true);
        vistaBuscarCuentas.setLocationRelativeTo(null);
        
    }

    private void generarReportes() {
        System.out.println("Generando reportes");
       generarReportePdf vistaReporte = new generarReportePdf();
       new generarReporteController(banco, vistaReporte);
       vistaReporte.setVisible(true);
       vistaReporte.setLocationRelativeTo(null);
    }

    private void verHistorial() {
        System.out.println("Viendo historial");
       historialTransacciones historialTransacciones = new historialTransacciones();
       new historialTransaccionesController(banco, historialTransacciones);
       historialTransacciones.setVisible(true);
       historialTransacciones.setLocationRelativeTo(null);
    }
}
