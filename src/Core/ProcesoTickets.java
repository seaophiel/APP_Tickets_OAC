package Core;
import DAO.OAC_Ticket_DAO;
import Entidad.OAC_Ticket;
import Requerimiento1.TicketAtencionGeneral;
import Requerimiento1.TicketFabrica;
import Requerimiento1.TicketReclamo;
import Requerimiento1.TicketServicioAlCliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;  

public class ProcesoTickets {
    
    private TicketFabrica fabrica;
    private TicketAtencionGeneral general;
    private TicketServicioAlCliente servicioCliente;
    private TicketReclamo reclamos;
    private String sucursal;
    private BufferedReader reader;
    private Connection con;
    
    public ProcesoTickets(){
        fabrica = new TicketFabrica();
        general = (TicketAtencionGeneral) fabrica.creacreaTicket(TicketFabrica.SubDepartamento.AtencionGeneral);
        servicioCliente = (TicketServicioAlCliente) fabrica.creacreaTicket(TicketFabrica.SubDepartamento.ServicioAlCliente);
        reclamos = (TicketReclamo) fabrica.creacreaTicket(TicketFabrica.SubDepartamento.Reclamos);
        sucursal = "";
        reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            iniciaConexionBD();
        }
        catch (Exception ex) {
            String err = ex.toString();
        }
    }
    
    private void iniciaConexionBD() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver");  
        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sys as sysdba","123456");
    }
    
    public void iniciaProceso() throws IOException{
        System.out.flush();
        System.out.println("Bienvenido al sistema de tickets de atencion del OAC");
        if (sucursal == "")
        {
            System.out.println("Favor ingrese el nombre de la sucursal (solo se solicita esta informacion la primera vez");
            sucursal = reader.readLine();
            inicializaContadores();
        }
        
        seleccioneTipoTicket();
    }
    
    private void inicializaContadores() {
        OAC_Ticket_DAO dao = new OAC_Ticket_DAO();
        OAC_Ticket ultimoTicketGeneral = dao.getUltimoTicket(sucursal, "general");
        OAC_Ticket ultimoTicketServicioCliente = dao.getUltimoTicket(sucursal, "servicio al cliente");
        OAC_Ticket ultimoTicketReclamos = dao.getUltimoTicket(sucursal, "reclamos");
        
        if (ultimoTicketGeneral != null){
            general.setNumeroAtencion(ultimoTicketGeneral.getOacTicket());
        }
        else{
            general.setNumeroAtencion(0);
        }
        
        if (ultimoTicketServicioCliente != null){
            servicioCliente.setNumeroAtencion(ultimoTicketServicioCliente.getOacTicket());
        }
        else{
            servicioCliente.setNumeroAtencion(0);
        }
        
        if (ultimoTicketReclamos != null){
            reclamos.setNumeroAtencion(ultimoTicketReclamos.getOacTicket());
        }
        else{
            reclamos.setNumeroAtencion(0);
        }
    }
    
    private void seleccioneTipoTicket() throws IOException{
        OAC_Ticket_DAO dao = new OAC_Ticket_DAO();
        OAC_Ticket ticket = null;
        System.out.println("Favor, ingresar el tipo de de ticket que desea");
        
        System.out.println("1) Atencion general");
        System.out.println("2) Servicio al cliente");
        System.out.println("3) Reclamos");
        
        String tipo = reader.readLine();
        
        switch (tipo){
            case "1":
                general.generaTicketAtencion(sucursal);
                ticket = new OAC_Ticket(sucursal, general.getSubDepartamento(), general.getNumeroAtencion());
                dao.insertTicket(ticket);
                break;
            case "2":
                servicioCliente.generaTicketAtencion(sucursal);
                ticket = new OAC_Ticket(sucursal, servicioCliente.getSubDepartamento(), servicioCliente.getNumeroAtencion());
                dao.insertTicket(ticket);
                break;
            case "3":
                reclamos.generaTicketAtencion(sucursal);
                ticket = new OAC_Ticket(sucursal, reclamos.getSubDepartamento(), reclamos.getNumeroAtencion());
                dao.insertTicket(ticket);
                break;
            default:
                System.out.println("Opcion invalida");
                seleccioneTipoTicket();
                break;
        }
        
        seleccioneTipoTicket();
    }
}
