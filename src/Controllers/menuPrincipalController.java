
package Controllers;

import View.*;
import Models.BancoModel;

/**
 *
 * @author charl
 */
public class menuPrincipalController {
    private MenuPrincipalView view;
    BancoModel banco = new BancoModel();
    public menuPrincipalController (MenuPrincipalView view){
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
        DatosEstudianteView datos = new DatosEstudianteView();
        datos.setVisible(true);
        datos.setLocationRelativeTo(null);
    }

    private void crearCuenta() {
        System.out.println("Creando cuenta");
        CrearCuentaView vistaCrearCuenta = new CrearCuentaView();
        new crearCuentaController(vistaCrearCuenta,banco);
        vistaCrearCuenta.setVisible(true);
        vistaCrearCuenta.setLocationRelativeTo(null);
    }

    private void realizarRetiro() {
        System.out.println("Realizando retiro");
        RetirosView vistaRetiro = new RetirosView();
        new debitosController(vistaRetiro,banco);
        vistaRetiro.setVisible(true);
        vistaRetiro.setLocationRelativeTo(null);
        
    }

    private void realizarDeposito() {
        System.out.println("Realizando depósito");
       DepositosView vistaDepositos = new DepositosView();
       new depositosController(vistaDepositos, banco);
       vistaDepositos.setVisible(true);
       vistaDepositos.setLocationRelativeTo(null);
       
    }

    private void registrarUsuario() {
        System.out.println("Registrando usuario");
        CrearUsuarioView vistaCrearUsuario = new CrearUsuarioView();
        new registroUsuarioController(vistaCrearUsuario,banco);
        vistaCrearUsuario.setVisible(true);
        vistaCrearUsuario.setLocationRelativeTo(null);
        
    }

    private void buscarCuenta() {
        System.out.println("Buscando cuenta");
        BuscarCuentasAsociadasView vistaBuscarCuentas = new BuscarCuentasAsociadasView();
        new buscarCuentasAsociadasController(vistaBuscarCuentas,banco);
        vistaBuscarCuentas.setVisible(true);
        vistaBuscarCuentas.setLocationRelativeTo(null);
        
    }

    private void generarReportes() {
        System.out.println("Generando reportes");
       GenerarReportePdfView vistaReporte = new GenerarReportePdfView();
       new generarReporteController(banco, vistaReporte);
       vistaReporte.setVisible(true);
       vistaReporte.setLocationRelativeTo(null);
    }

    private void verHistorial() {
        System.out.println("Viendo historial");
       HistorialTransaccionesView historialTransacciones = new HistorialTransaccionesView();
       new historialTransaccionesController(banco, historialTransacciones);
       historialTransacciones.setVisible(true);
       historialTransacciones.setLocationRelativeTo(null);
    }
}
