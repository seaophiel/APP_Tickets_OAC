package Entidad;

public class OAC_Ticket {

    public int getOacTicketId() {
        return oacTicketId;
    }

    public void setOacTicketId(int oacTicketId) {
        this.oacTicketId = oacTicketId;
    }

    public String getOacSucursal() {
        return oacSucursal;
    }

    public void setOacSucursal(String oacSucursal) {
        this.oacSucursal = oacSucursal;
    }

    public String getOacSubDepartamento() {
        return oacSubDepartamento;
    }

    public void setOacSubDepartamento(String oacSubDepartamento) {
        this.oacSubDepartamento = oacSubDepartamento;
    }

    public int getOacTicket() {
        return oacTicket;
    }

    public void setOacTicket(int oacTicket) {
        this.oacTicket = oacTicket;
    }
    
    private int oacTicketId;
    private String oacSucursal;
    private String oacSubDepartamento;
    private int oacTicket;
    
    public OAC_Ticket(){
        
    }
    
    public OAC_Ticket(String sucursal, String subDpto, int ticketNum){
        this.oacSucursal = sucursal;
        this.oacSubDepartamento = subDpto;
        this.oacTicket = ticketNum;
                
    }
    
}
