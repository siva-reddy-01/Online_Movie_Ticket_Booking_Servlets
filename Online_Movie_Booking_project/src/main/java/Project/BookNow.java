package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookNow")
public class BookNow extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
  
    public BookNow() 
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
			
			String qry="select * from addmovies";
			PreparedStatement pstmt=con.prepareStatement(qry);
			ResultSet rs=pstmt.executeQuery();
			
			
			out.println("<h2 align=center>Veiw Movies & Book Here</h2><br>");
			out.println("<table align=center border='2'>");
			out.println("<tr bgcolor='blue'>");
			out.println("<th>S.No</th>");
			out.println("<th>Movie Name</th>");
			out.println("<th>Language</th>");
			out.println("<th>Actor Name</th>");
			out.println("<th>Actress Name</th>");
			out.println("<th>Director Name</th>");
			out.println("<th>Ticket Cost</th>");
			out.println("<th>No Of Tickets Added</th>");
			out.println("<th>Sensor Report</th>");
			out.println("<th>Thetare Name</th>");
			out.println("<th>Thetare Location</th>");
			out.println("<th>Book Here</th>");
			out.println("</tr>");
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getString(5)+"</td>");
				out.println("<td>"+rs.getString(6)+"</td>");
				out.println("<td>"+rs.getString(7)+"</td>");
				out.println("<td>"+rs.getString(8)+"</td>");
				out.println("<td>"+rs.getString(9)+"</td>");
				out.println("<td>"+rs.getString(10)+"</td>");
				out.println("<td>"+rs.getString(11)+"</td>");
				out.println("<td><a href='booknow.html'>Book Now</a></td>");
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
