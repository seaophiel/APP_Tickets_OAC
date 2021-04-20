package Requerimiento1;

public class TicketReclamo implements ITicketAtencion {
    
    public String getSubDepartamento(){
        return this.subDepartamento;
    }
    
    public TicketReclamo(){
        this.subDepartamento = "reclamos";
    }
    
    private String subDepartamento;
    
    private int numeroAtencion = 0;
    
    public int getNumeroAtencion() {
        return numeroAtencion;
    }

    public void setNumeroAtencion(int numeroAtencion) {
        this.numeroAtencion = numeroAtencion;
    }
    
    @Override
    public void generaTicketAtencion(String sucursal) {
        numeroAtencion++;
        System.out.println("Se genero el ticket de reclamo numero: " + numeroAtencion + " para la sucursal " + sucursal);
    }    
}
