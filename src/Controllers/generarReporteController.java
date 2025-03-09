package Controllers;

import Models.ClienteModel;
import Models.BancoModel;
import Models.CuentaModel;
import Models.TransaccionModel;
import View.GenerarReportePdfView;
import View.AlertasViews;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class generarReporteController {
    private BancoModel banco;
    private GenerarReportePdfView vista;

    public generarReporteController(BancoModel banco, GenerarReportePdfView vista) {
        this.banco = banco;
        this.vista = vista;
        initListeners();
    }

    private void initListeners() {
        vista.getBtnGenerarHistorialTransaccion().addActionListener(e -> generarReporteTransacciones());
        vista.getBtnGererarHistorialdeCredito().addActionListener(e -> generarReporteDepositos());
        vista.getBtnGenerarHistorialDebito().addActionListener(e -> generarReporteRetiros());
    }

   private void generarReporteTransacciones() {
    String idCuenta = vista.getTxtCuenta().getText().trim();
    if (idCuenta.isEmpty()) {
        AlertasViews.mostrarAdvertencia("Por favor, ingrese el ID de la cuenta.");
        return;
    }

    ClienteModel clienteEncontrado = null;
    CuentaModel cuentaEncontrada = null;

    ClienteModel[] clientes = banco.getClientes();
    for (ClienteModel cliente : clientes) {
        if (cliente != null) {
            CuentaModel cuenta = cliente.buscarCuenta(idCuenta);
            if (cuenta != null) {
                clienteEncontrado = cliente;
                cuentaEncontrada = cuenta;
                break;
            }
        }
    }

    if (clienteEncontrado == null || cuentaEncontrada == null) {
        AlertasViews.mostrarError("No se encontró la cuenta.");
        return;
    }

    String filePath = "C:\\Users\\charl\\OneDrive\\Documentos\\NetBeansProjects\\BancaUsac\\src\\Reportes\\reporteTransacciones_" + obtenerFechaHoraActual() + ".pdf";
    generarReportePDF(clienteEncontrado, cuentaEncontrada, filePath, "Reporte de Transacciones", "todos");
    AlertasViews.mostrarInfo("Reporte de transacciones generado exitosamente.");
}

    private void generarReporteDepositos() {
        String idCuenta = vista.getTxtCuenta().getText().trim();
        if (idCuenta.isEmpty()) {
            AlertasViews.mostrarAdvertencia("Por favor, ingrese el ID de la cuenta.");
            return;
        }

        ClienteModel clienteEncontrado = null;
        CuentaModel cuentaEncontrada = null;

        ClienteModel[] clientes = banco.getClientes();
        for (ClienteModel cliente : clientes) {
            if (cliente != null) {
                CuentaModel cuenta = cliente.buscarCuenta(idCuenta);
                if (cuenta != null) {
                    clienteEncontrado = cliente;
                    cuentaEncontrada = cuenta;
                    break;
                }
            }
        }

        if (clienteEncontrado == null || cuentaEncontrada == null) {
            AlertasViews.mostrarError("No se encontró la cuenta.");
            return;
        }

        String filePath = "C:\\Users\\charl\\OneDrive\\Documentos\\NetBeansProjects\\BancaUsac\\src\\Reportes\\reporteDepositos_" + obtenerFechaHoraActual() + ".pdf";
        generarReportePDF(clienteEncontrado, cuentaEncontrada, filePath, "Reporte de Depósitos","deposito");
        AlertasViews.mostrarInfo("Reporte de depósitos generado exitosamente.");
    }

    private void generarReporteRetiros() {
        String idCuenta = vista.getTxtCuenta().getText().trim();
        if (idCuenta.isEmpty()) {
            AlertasViews.mostrarAdvertencia("Por favor, ingrese el ID de la cuenta.");
            return;
        }

        ClienteModel clienteEncontrado = null;
        CuentaModel cuentaEncontrada = null;

        ClienteModel[] clientes = banco.getClientes();
        for (ClienteModel cliente : clientes) {
            if (cliente != null) {
                CuentaModel cuenta = cliente.buscarCuenta(idCuenta);
                if (cuenta != null) {
                    clienteEncontrado = cliente;
                    cuentaEncontrada = cuenta;
                    break;
                }
            }
        }

        if (clienteEncontrado == null || cuentaEncontrada == null) {
            AlertasViews.mostrarError("No se encontró la cuenta.");
            return;
        }

        String filePath = "C:\\Users\\charl\\OneDrive\\Documentos\\NetBeansProjects\\BancaUsac\\src\\Reportes\\reporteRetiros_" + obtenerFechaHoraActual() + ".pdf";
        generarReportePDF(clienteEncontrado, cuentaEncontrada, filePath, "Reporte de Retiros","debito");
        AlertasViews.mostrarInfo("Reporte de retiros generado exitosamente.");
    }

    private void generarReportePDF(ClienteModel cliente, CuentaModel cuenta, String filePath, String titulo, String tipoTransaccion) {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Agregar información del cliente
        document.add(new Paragraph(titulo));
        document.add(new Paragraph("CUI: " + cliente.getCUI()));
        document.add(new Paragraph("Nombre: " + cliente.getNombreCliente() + " " + cliente.getApellidoCliente()));
        document.add(new Paragraph("Cuenta: " + cuenta.getIdCuenta()));
        document.add(new Paragraph(" "));

        // Crear tabla para las transacciones
        PdfPTable table = new PdfPTable(5);
        table.addCell("ID Transacción");
        table.addCell("Fecha");
        table.addCell("Tipo");
        table.addCell("Monto Débito");
        table.addCell("Monto Depósito");

        TransaccionModel[] transacciones = cuenta.getTransacciones();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (TransaccionModel transaccion : transacciones) {
            if (transaccion != null) {
                // Mostrar todas las transacciones si el tipo es "todos"
                if (tipoTransaccion.equals("todos") || transaccion.getTipo().equals(tipoTransaccion)) {
                    table.addCell(transaccion.getIdTransaccion());
                    table.addCell(dateFormat.format(transaccion.getFecha()));
                    table.addCell(transaccion.getTipo());
                    table.addCell(String.valueOf(transaccion.getMontoDebito()));
                    table.addCell(String.valueOf(transaccion.getMontoDeposito()));
                }
            }
        }

        document.add(table);
        document.close();
    } catch (DocumentException | IOException e) {
        e.printStackTrace();
    }
}
     private String obtenerFechaHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        return sdf.format(new Date());
    }
}
