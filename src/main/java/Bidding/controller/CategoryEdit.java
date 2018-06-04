package Bidding.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bidding.User;
import Bidding.dao.BookDao;
import Bidding.dao.UserDao;

/**
 * Servlet implementation class CategoryEdit
 */
@WebServlet("/category")
public class CategoryEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDao bookdao = new BookDao();
	private UserDao userdao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
		if(session == null) {
			dispatcher = request.getRequestDispatcher("index.html");
		} else {
			User user = userdao.getUserbyName((String) session.getAttribute("username"));
			if(user != null) {
				AdminLogin.populateData(user, request);
				request.setAttribute("user", user);
				dispatcher = request.getRequestDispatcher("admin.jsp");
			} else {
				request.setAttribute("mssg", "Your Session Timed out, Login again!");
				dispatcher = request.getRequestDispatcher("error.jsp");
			}
		}
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("auctionstatus");
		String cat_id = request.getParameter("catid");
		String user_id = request.getParameter("userid");
		
		HttpSession session = request.getSession();

		User user = null;
		
		if(session == null) {
			user = userdao.getUserbyID(user_id);
		} else {
			String username = (String) session.getAttribute("username");
			if(username == null) {
				user = userdao.getUserbyID(user_id);
			} else {
				user = userdao.getUserbyName(username);
			}
			session.setAttribute("username", user.getName());
		}
		
		int res = bookdao.updateCategory(status, cat_id);
		
		RequestDispatcher dispatcher ;
		if(res > 0) {
			AdminLogin.populateData(user, request);
			request.setAttribute("user", user);
			request.setAttribute("mssg", "Category Updated");
			System.out.println("Redirecting to home page");
			dispatcher = request.getRequestDispatcher("admin.jsp");
		} else {
			request.setAttribute("mssg", "Unable to update data!");
			dispatcher = request.getRequestDispatcher("admin.jsp");
		}
		dispatcher.forward(request, response);
	}

}
