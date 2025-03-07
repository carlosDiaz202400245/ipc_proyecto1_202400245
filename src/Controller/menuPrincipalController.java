
package Controller;

import View.datosEstudiante;
import View.menuPrincipal;

/**
 *
 * @author charl
 */
public class menuPrincipalController {
    private menuPrincipal view;
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
        
    }

    private void realizarRetiro() {
        System.out.println("Realizando retiro");
        
    }

    private void realizarDeposito() {
        System.out.println("Realizando depósito");
       
    }

    private void registrarUsuario() {
        System.out.println("Registrando usuario");
        
    }

    private void buscarCuenta() {
        System.out.println("Buscando cuenta");
        
    }

    private void generarReportes() {
        System.out.println("Generando reportes");
      
    }

    private void verHistorial() {
        System.out.println("Viendo historial");
       
    }
}
