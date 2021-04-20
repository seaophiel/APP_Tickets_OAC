package Requerimiento3;

import Entidad.OAC_Ticket;
import java.time.LocalDate;
import java.util.List;

public class ReporteDiario extends ReporteBase {

    @Override
    public void muestraReporte(List<OAC_Ticket> lista, String sucursal, String desde, String hasta) {
        String fecha = obtenerFechaString(LocalDate.now());
        int totalTickets = lista.size();
        
        List<OAC_Ticket> listaGral =  getArrayListFromStream(lista.stream().filter(ticket -> "general".equals(ticket.getOacSubDepartamento())));
        List<OAC_Ticket> listaServicio =  getArrayListFromStream(lista.stream().filter(ticket -> "servicio al cliente".equals(ticket.getOacSubDepartamento())));
        List<OAC_Ticket> listaReclamos =  getArrayListFromStream(lista.stream().filter(ticket -> "reclamos".equals(ticket.getOacSubDepartamento())));
        
        int totalGral = listaGral.size();
        int totalServicio = listaServicio.size();
        int totalReclamos = listaReclamos.size();
        
        System.out.println("-------------------------------------");
        System.out.println("Reporte diario de tickets emitidos en sucursal: " + sucursal);
        System.out.println("Fecha: " + fecha);
        System.out.println("Tickets por departamento");
        System.out.println("Gemeral:                " + totalGral);
        System.out.println("Servicio al Cliente:    " + totalServicio);
        System.out.println("Reclamos:               " + totalReclamos);
        System.out.println("Total tickets:          " + totalTickets);
    }    
}
