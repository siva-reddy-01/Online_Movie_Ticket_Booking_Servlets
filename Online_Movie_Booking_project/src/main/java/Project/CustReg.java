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

@WebServlet("/CustReg")
public class CustReg extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public CustReg() 
    {
        super();
       
    }

    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=(int)(Math.random()+99999)+1;
		String fname=request.getParameter("fname");
		String gender=request.getParameter("gender");
		String uname=request.getParameter("uname");
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		
		
		try
		{
			Connection con=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver class loaded");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
			System.out.println("Connetion established");
			
			String qry="insert into custreg values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(qry);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, fname);
			pstmt.setString(3, gender);
			pstmt.setString(4, uname);
			pstmt.setString(5, email);
			pstmt.setString(6, pwd);
			pstmt.setString(7, mobile);
			pstmt.setString(8, address);
			
			int n=pstmt.executeUpdate();
			
			if(n>0)
			{
				out.println("Register Sucuessfully..");
				RequestDispatcher rd=request.getRequestDispatcher("custlogin.html");
				rd.forward(request, response);
			}
			else
			{
				out.println("Not Register...");
			}
			con.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}


}
