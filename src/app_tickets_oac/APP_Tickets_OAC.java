package app_tickets_oac;
import Core.ProcesoReportes;
import Core.ProcesoTickets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class APP_Tickets_OAC { 
    
    public static void main(String[] args) throws IOException {
        
        System.out.println("Bienvenido al sistema OAC desarrollado para la evaluacion 3 de Patrones de diseño");
        System.out.println("Favor ingrese una opcion");
        System.out.println("1) Ingreso de tickets de atencion");
        System.out.println("2) Ver reportes de tickets emitidos");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion = reader.readLine();
        
        if (opcion.equalsIgnoreCase("1")){
            ProcesoTickets procT = new ProcesoTickets();
            procT.iniciaProceso();
        }
        else if (opcion.equalsIgnoreCase("2")){
            ProcesoReportes procR = new ProcesoReportes();
            procR.muestraLosReportes();
        }
    }
}
