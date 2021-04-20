package Requerimiento3;

import Entidad.OAC_Ticket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReporteBase {
    public void muestraReporte(List<OAC_Ticket> listaTicketsPeriodo, String sucursal, String desde, String hasta){
        
    }
    
    protected <T> ArrayList<T> getArrayListFromStream(Stream<T> stream)
    {
        List<T> list = stream.collect(Collectors.toList());
        ArrayList<T> arrayList = new ArrayList<T>(list);
        return arrayList;
    }
    
    protected String obtenerFechaString(LocalDate fecha){
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
}
