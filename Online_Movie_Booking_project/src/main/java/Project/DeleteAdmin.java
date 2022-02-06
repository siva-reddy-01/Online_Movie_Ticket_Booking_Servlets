package Project;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteAdmin")
public class DeleteAdmin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public DeleteAdmin() 
    {
        super();
    }
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String aid=request.getParameter("aid");
	
		try
		{
			Connection con=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver class loaded");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
			System.out.println("Connetion established");
			
			String qry="delete from addadmin where aid=?";
			PreparedStatement pstmt=con.prepareStatement(qry);
			pstmt.setString(1,aid);
			pstmt.executeUpdate();
			response.sendRedirect("ViewAdmins");
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
