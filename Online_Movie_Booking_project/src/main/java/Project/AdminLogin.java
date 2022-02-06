package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       

    public AdminLogin()
    {
        super();
    }

    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String aid= request.getParameter("aid");
	    String pwd= request.getParameter("pwd");
		
		try
		{
			Connection con=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver class loaded");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
			System.out.println("Connection established..");
			
			PreparedStatement pstmt=con.prepareStatement("select * from addadmin where aid=? and pwd=?");
			 
			pstmt.setString(1,aid);
			pstmt.setString(2,pwd);
			PrintWriter out =response.getWriter();
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				
				RequestDispatcher rd=request.getRequestDispatcher("adminhome.html");
				rd.forward(request, response);
			}
			else
			{
	
				RequestDispatcher rd=request.getRequestDispatcher("adminlogin.html");
				rd.forward(request, response);
				out.println("Sorry email or password wrong!!");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
