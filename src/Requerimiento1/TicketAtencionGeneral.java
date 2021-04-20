package Requerimiento1;

public class TicketAtencionGeneral implements ITicketAtencion {
    
    public String getSubDepartamento(){
        return this.subDepartamento;
    }
    
    public TicketAtencionGeneral(){
        this.subDepartamento = "general";
    }
    
    private String subDepartamento;

    public int getNumeroAtencion() {
        return numeroAtencion;
    }

    public void setNumeroAtencion(int numeroAtencion) {
        this.numeroAtencion = numeroAtencion;
    }
    
    private int numeroAtencion = 0;
    
    @Override
    public void generaTicketAtencion(String sucursal) {
        numeroAtencion++;
        System.out.println("Se genero el ticket de atencion general numero: " + numeroAtencion + " para la sucursal " + sucursal);
    }    
}
