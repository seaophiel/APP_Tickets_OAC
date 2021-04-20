package DAO;

import Entidad.OAC_Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OAC_Ticket_DAO {
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String  server = "jdbc:oracle:thin:@localhost:1521:xe";
    private String user = "sys as sysdba";
    private String pass = "123456";
    private Connection con;
    
    public OAC_Ticket getUltimoTicket(String sucursal, String dubDpto){
        OAC_Ticket ticket = null;
        try{
            String sql = "select * from oac_tickets_atencion where sucursal = '" + sucursal + 
                    "' and sub_departamento = '" + dubDpto + "' order by id desc fetch next 1 rows only";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(server, user, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                ticket = new OAC_Ticket();
                ticket.setOacTicketId(rs.getInt("id"));
                ticket.setOacSucursal(rs.getString("sucursal"));
                ticket.setOacSubDepartamento(rs.getString("sub_departamento"));
                int num = rs.getInt("numero");
                ticket.setOacTicket(num);
            }
            
            return ticket;
        }
        catch (Exception ex){
            System.out.println("Error en getUltimoTicket. EX: " + ex.toString());
        }
        finally{
            try{
                con.close();    
            }catch (Exception e) {}
        }
        
        return ticket;
    }
    
    public int getTotalTickets(String sucursal, String sbDpto, String desde, String hasta) throws ClassNotFoundException, 
            SQLException
    {
        try{
            String sql = "select count(*) from oac_tickets_atencion where fecha between to_date(to_char('" + 
                    desde + "'),'DD-MM-YYYY HH24:MI:SS') and to_date(to_char('" +
                    hasta + "'),'DD-MM-YYYY HH24:MI:SS')";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(server, user, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                int total = rs.getInt(1);
                return total;
            }
            
            return 0;
        }
        catch (Exception ex){
            System.out.println("Error en getTotalTickets. EX: " + ex.toString());
        }
        
        return 0;
    }
    
    public List<OAC_Ticket> getTicketsIntervalo(String sucursal, String desde, String hasta) throws ClassNotFoundException, 
            SQLException
    {
        List<OAC_Ticket> listado = new ArrayList();
        try{
            String sql = "select * from oac_tickets_atencion where sucursal = '" + 
                    sucursal + "' and fecha between to_date(to_char('" +
                    desde + "'),'DD-MM-YYYY HH24:MI:SS') and to_date(to_char('" +
                    hasta + "'),'DD-MM-YYYY HH24:MI:SS')";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(server, user, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                OAC_Ticket ticket = new OAC_Ticket();
                ticket.setOacTicketId(rs.getInt("ID"));
                ticket.setOacSucursal(rs.getString("SUCURSAL"));
                ticket.setOacSubDepartamento(rs.getString("SUB_DEPARTAMENTO"));
                ticket.setOacTicket(rs.getInt("NUMERO"));
                listado.add(ticket);
            }
            
            return listado;
        }
        catch (Exception ex){
            System.out.println("Error en getTicketsIntervalo. EX: " + ex.toString());
        }
        
        return listado;
    }
    
    public boolean insertTicket(OAC_Ticket ticket){
        
        String sql = "";                
        try
        {
            sql = "insert into oac_tickets_atencion(sucursal, sub_departamento, numero) values('" + 
                    ticket.getOacSucursal() + "', '" + ticket.getOacSubDepartamento() + "', " + 
                    ticket.getOacTicket() + ")";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(server, user, pass);
            Statement st = con.createStatement();
            int res = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception ex){
            System.out.println("Error en insertTicket. EX: " + ex.toString());
            return false;
        }
        finally{
            try{
                con.close();    
            }catch (Exception e) {}            
        }        
    }    
}
