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


@WebServlet("/AddMovies")
public class AddMovies extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
   
    public AddMovies() 
    {
        super();
   
    }

	
    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{
   		response.setContentType("text/html");
   		PrintWriter out=response.getWriter();
   		
   		int aid=(int)(Math.random()+99999)+1;
   		String mname=request.getParameter("mname");
   		String lang=request.getParameter("lang");
   		String aname=request.getParameter("aname");
   		String acname=request.getParameter("acname");
   		String dname=request.getParameter("dname");
   		String cost=request.getParameter("cost");
   		String tickets=request.getParameter("tickets");
   		String report=request.getParameter("report");
   		String tname=request.getParameter("tname");
   		String location=request.getParameter("location");
   		
   		
   		try
   		{
   			Connection con=null;
   			Class.forName("oracle.jdbc.driver.OracleDriver");
   			System.out.println("Driver class loaded");
   			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
   			System.out.println("Connetion established");
   			
   			String qry="insert into addmovies values(?,?,?,?,?,?,?,?,?,?,?)";
   			PreparedStatement pstmt=con.prepareStatement(qry);
   			
   			pstmt.setInt(1, aid);
   			pstmt.setString(2, mname);
   			pstmt.setString(3, lang);
   			pstmt.setString(4, aname);
   			pstmt.setString(5, acname);
   			pstmt.setString(6, dname);
   			pstmt.setString(7, cost);
   			pstmt.setString(8, tickets);
   			pstmt.setString(9, report);
   			pstmt.setString(10, tname);
   			pstmt.setString(11, location);
   			int n=pstmt.executeUpdate();
   			
   			if(n>0)
   			{
   				out.println("Movies Added..");
   				RequestDispatcher rd=request.getRequestDispatcher("ViewMovies");
   				rd.forward(request, response);
   			}
   			else
   			{
   				out.println("No Movie Added");
   			}
   			con.close();
   			
   			
   		}
   		catch(Exception e)
   		{
   			System.out.println(e);
   		}
   		
   	}

}
