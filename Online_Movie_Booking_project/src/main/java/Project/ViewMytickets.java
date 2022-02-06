package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewMytickets")
public class ViewMytickets extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
  
    public ViewMytickets() 
    {
        super();
    }

    public void service(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try
		{
			Connection con=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver class loaded");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
			System.out.println("Connection established");
			
			String qry="select * from booknow";
			PreparedStatement pstmt=con.prepareStatement(qry);
			ResultSet rs=pstmt.executeQuery();
			
			out.println("<h2 align=center>View Mytickets</h2><br>");
			out.println("<table align=center border='2'>");
			out.println("<tr bgcolor='blue'>");
			out.println("<th>Movie Name</th>");
			out.println("<th>Ticket Cost</th>");
			out.println("<th>No Of Tickets Booked</th>");
			out.println("<th>Upate Tickets Here</th>");
			out.println("<th>Cancel Tickets Here</th>");
			out.println("</tr>");
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>"+rs.getString(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println(" ");
				out.println(" ");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<td><a href='custhome.html'>Back??</a></td>");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
