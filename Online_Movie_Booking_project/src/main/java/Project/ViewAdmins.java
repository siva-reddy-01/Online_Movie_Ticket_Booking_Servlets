package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewAdmins")
public class ViewAdmins extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
  
    public ViewAdmins() 
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
			
			String qry="select * from addadmin";
			PreparedStatement pstmt=con.prepareStatement(qry);
			ResultSet rs=pstmt.executeQuery();
			
			
			out.println("<h2 align=center>Veiw Managers Records</h2><br>");
			out.println("<table align=center border='2'>");
			out.println("<tr bgcolor='blue'>");
			out.println("<th>Manager ID</th>");
			out.println("<th>Manager NAME</th>");
			out.println("<th>Manager GENDER</th>");
			out.println("<th>Manager EMAIL</th>");
			out.println("<th>Manager THEATRE</th>");
			out.println("<th>Manager LOCATION</th>");
			out.println("<th>Manager PASSWORD</th>");
			out.println("<th>Delete Manager</th>");
			out.println("</tr>");
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>"+rs.getString(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getString(5)+"</td>");
				out.println("<td>"+rs.getString(6)+"</td>");
				out.println("<td>"+rs.getString(7)+"</td>");
				out.println("<td><a href='DeleteAdmin?aid=\"+rs.getString(8)+\"'>Delete</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
