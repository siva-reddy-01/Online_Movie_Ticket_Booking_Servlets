package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TicketBooked")
public class TicketBooked extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
   
    public TicketBooked() 
    {
        super();
   
    }

	
    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{
   		response.setContentType("text/html");
   		PrintWriter out=response.getWriter();
   		
   		
   		String mname=request.getParameter("mname");
   		String cost=request.getParameter("cost");
   		String tickets=request.getParameter("tickets");
   		
   		try
   		{
   			Connection con=null;
   			Class.forName("oracle.jdbc.driver.OracleDriver");
   			System.out.println("Driver class loaded");
   			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
   			System.out.println("Connetion established");
   			
   			String qry="insert into booknow values(?,?,?)";
   			PreparedStatement pstmt=con.prepareStatement(qry);
   			
   			pstmt.setString(1, mname);
   			pstmt.setString(2, cost);
   			pstmt.setString(3, tickets);
   			int n=pstmt.executeUpdate();
   			
   			if(n>0)
   			{
   				out.println("Tickets Booked");
   				RequestDispatcher rd=request.getRequestDispatcher("booked.html");
   				rd.forward(request, response);
   			}
   			else
   			{
   				out.println("No Tickets Booked");
   			}
   			con.close();
   			
   		}
   		catch(Exception e)
   		{
   			System.out.println(e);
   		}
   		
   	}

}
