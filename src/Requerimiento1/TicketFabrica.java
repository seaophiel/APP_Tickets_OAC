package Requerimiento1;

public class TicketFabrica {
    
    public enum SubDepartamento {
        AtencionGeneral,
        ServicioAlCliente,
        Reclamos
    }
    
    public ITicketAtencion creacreaTicket(SubDepartamento subDpto){
        
        switch (subDpto)
        {
            case AtencionGeneral:
                return new TicketAtencionGeneral();  
            case ServicioAlCliente:
                return new TicketServicioAlCliente();
            case Reclamos:
                return new TicketReclamo();
            default:
                System.out.println("El subdepartamento " + subDpto + " no esta implementado.");
                return null;
                
        }
    }
    
}

