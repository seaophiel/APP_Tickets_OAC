package Requerimiento1;

public class TicketServicioAlCliente implements ITicketAtencion {
    
    public String getSubDepartamento(){
        return this.subDepartamento;
    }
    
    public TicketServicioAlCliente(){
        this.subDepartamento = "servicio al cliente";
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
        System.out.println("Se genero el ticket de servicio al cliente numero: " + numeroAtencion + " para la sucursal " + sucursal);
    }    
}
