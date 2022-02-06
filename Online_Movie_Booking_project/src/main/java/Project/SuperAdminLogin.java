package Project;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SuperAdminLogin")
public class SuperAdminLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
     
    public SuperAdminLogin() 
    {
        super();
       
    }
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String said=request.getParameter("said").trim();
		String pwd=request.getParameter("pwd").trim();
		System.out.println("data requested");
		if(said==null || said.equals("") || pwd==null || pwd.equals(""))
		{
			out.println("<h1>Please enter both username " +"and password. <br/><br/></h1>");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("superadminlogin.html");
		}
		else if(said.equals("190031290") && pwd.equals("190031290"))
		{
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("superadminhome.html");
			requestDispatcher.forward(request, response);
		}
		else
		{
			out.println("Wrong username or password. <br/><br/>");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("superadminlogin.html");
			requestDispatcher.include(request, response);
		}
	}

}
