package Bidding;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/user")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection connection = new DatabaseConnection();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    		super.init();
    		connection.ConnectToDB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>USERS</title></head><body>");
		out.println("<h2>Welcome!</h2>");
		out.println("<h3>User Details</h3>");
		
		out.println("<table><tr><td>Name</td><td>SSN</td></tr>");
//		for(User user : users) {
//			out.println("<tr><td>"+user.getName()+"</td><td>"+user.getSsn()+"</td></tr>");
//		}
		out.println("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
