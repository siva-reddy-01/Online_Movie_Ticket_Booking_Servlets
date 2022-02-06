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

@WebServlet("/AddAdmin")
public class AddAdmin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
 
    public AddAdmin() 
    {
        super();
    }

	
    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{
   		response.setContentType("text/html");
   		PrintWriter out=response.getWriter();
   		
   		String aid=request.getParameter("aid");
   		String aname=request.getParameter("aname");
   		String agender=request.getParameter("agender");
   		String aemail=request.getParameter("aemail");
   		String atheatre=request.getParameter("atheatre");
   		String alocation=request.getParameter("alocation");
   		String pwd=request.getParameter("pwd");		
   		try
   		{
   			Connection con=null;
   			Class.forName("oracle.jdbc.driver.OracleDriver");
   			System.out.println("Driver class loaded");
   			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
   			System.out.println("Connetion established");
   			
   			String qry="insert into addadmin values(?,?,?,?,?,?,?)";
   			PreparedStatement pstmt=con.prepareStatement(qry);
   			
   			pstmt.setString(1,aid);
   			pstmt.setString(2,aname);
   			pstmt.setString(3,agender);
   			pstmt.setString(4,aemail);
   			pstmt.setString(5,atheatre);
   			pstmt.setString(6,alocation);
   			pstmt.setString(7,pwd);		
   			int n=pstmt.executeUpdate();
   			
   			if(n>0)
   			{
   				out.println("Added Sucuessfully..");
   				RequestDispatcher rd=request.getRequestDispatcher("ViewAdmins");
   				rd.forward(request, response);
   			}
   			else
   			{
   				out.println("Not Added");
   			}
   			con.close();
   				
   		}
   		catch(Exception e)
   		{
   			System.out.println(e);
   		}
   		
   	}
}
