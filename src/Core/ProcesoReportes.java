package Core;

import DAO.OAC_Ticket_DAO;
import Entidad.OAC_Ticket;
import Requerimiento3.ReporteDiario;
import Requerimiento3.ReporteMensual;
import Requerimiento3.ReporteSemanal;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ProcesoReportes {
    
    public void muestraLosReportes(){
        OAC_Ticket_DAO dao = new OAC_Ticket_DAO();
        List<OAC_Ticket> listaTemp = null;
        List<OAC_Ticket> listaSemana = null;
        List<OAC_Ticket> listaMes = null;
        String sucursal = "teatinos";
        String strFecha = obtenerFechaString(LocalDate.now());
        String desde = "";
        String hasta = "";
        
        //Reporte diario
        try{
            desde = strFecha + " 00:00:00";
            hasta = strFecha + " 23:59:59";
            listaTemp = dao.getTicketsIntervalo(sucursal, desde, hasta);
            ReporteDiario reporte = new ReporteDiario();
            reporte.muestraReporte(listaTemp, sucursal, desde, hasta);
        }
        catch (Exception ex) {
            System.out.println("Error en proceso de reporte diario. EX: " + ex.toString());
        }
        
        //Reporte semanal
        try{
            LocalDate lunes = obtenerFechaSemana(1);
            LocalDate domingo = obtenerFechaSemana(7);
            desde = obtenerFechaString(lunes) + " 00:00:00";
            hasta = obtenerFechaString(domingo) + " 23:59:59";
            listaTemp = dao.getTicketsIntervalo(sucursal, desde, hasta);
            ReporteSemanal reporte = new ReporteSemanal();
            reporte.muestraReporte(listaTemp, sucursal, desde, hasta);
        }
        catch (Exception ex) {
            System.out.println("Error en proceso de reporte semanal. EX: " + ex.toString());
        }
        
        //Reporte mensual
        try{
            desde = obtenerPrimerDiaMes() + " 00:00:00";
            hasta = obtenerUltimoDiaMes() + " 23:59:59";
            listaTemp = dao.getTicketsIntervalo(sucursal, desde, hasta);
            ReporteMensual reporte = new ReporteMensual();
            reporte.muestraReporte(listaTemp, sucursal, desde, hasta);
        }
        catch (Exception ex) {
            System.out.println("Error en proceso de reporte mensual. EX: " + ex.toString());
        }
    }
    
    private String obtenerFechaString(LocalDate fecha){
        String strFecha = "";
        
        String dia = Integer.toString(fecha.getDayOfMonth());
        String mes = Integer.toString(fecha.getMonthValue());
        String anio = Integer.toString(fecha.getYear());
        
        if (dia.length() < 2){
            dia = "0" + dia;
        }
        
        if (mes.length() < 2){
            mes = "0" + mes;
        }
        
        strFecha = dia + "-" + mes + "-" + anio;
        return strFecha;
    }
    
    private LocalDate obtenerFechaSemana(int diaDeSemana){
        LocalDate ahora = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.UK).dayOfWeek();
        return ahora.with(fieldISO, diaDeSemana);
    }
    
    private String obtenerPrimerDiaMes(){
        String dia = "01";
        String mes = Integer.toString(LocalDate.now().getMonthValue());
        String anio = Integer.toString(LocalDate.now().getYear());
        if (mes.length() < 2){
            mes = "0" + mes;
        }
        
        String fecha = dia + "-" + mes + "-" + anio;
        return fecha;
    }
    
    private String obtenerUltimoDiaMes(){
        Calendar c = Calendar.getInstance();
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        String dia = "";
        
        String mes = Integer.toString(LocalDate.now().getMonthValue());
        String anio = Integer.toString(LocalDate.now().getYear());
        
        if (day < 10){
            dia = "0" + day;
        }
        else{
            dia = day + "";
        }
        
        if (mes.length() < 2){
            mes = "0" + mes;
        }
        
        String fecha = dia + "-" + mes + "-" + anio;
        return fecha;
    }
}
