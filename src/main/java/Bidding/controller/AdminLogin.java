package Bidding.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bidding.Bid;
import Bidding.Book;
import Bidding.Category;
import Bidding.Department;
import Bidding.User;
import Bidding.dao.BookDao;
import Bidding.dao.UserDao;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/admin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDao bookdao = new BookDao();
	private UserDao userdao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		RequestDispatcher dispatcher;
		if(session.isNew()) {
			dispatcher = request.getRequestDispatcher("index.html");
		} else {
			String username = (String) session.getAttribute("username");
			if(username != null) {
				User user = userdao.getUserbyName(username);
				if(user != null) {
					populateData(user, request);
					request.setAttribute("user", user);
					dispatcher = request.getRequestDispatcher("admin.jsp");
				} else {
					request.setAttribute("mssg", "Invalid User ID or Password. Try Again!");
					dispatcher = request.getRequestDispatcher("error.jsp");
				}
			} else {
				dispatcher = request.getRequestDispatcher("index.html");
			}
		}
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession(true);

		User user = null;
		
		if(session.isNew() || username != null) {
			System.out.println("New Session");
			session.setAttribute("username", username);
			user = userdao.validateUser(username, password);
		} else {
			String user_name = (String) session.getAttribute("username");
			if(user_name == null) {
				session.setAttribute("username", username);
				user = userdao.validateUser(username, password);
			} else {
				user = userdao.getUserbyName(user_name);
			}
		}
		
		RequestDispatcher dispatcher ;
		if(user != null) {
			populateData(user, request);
			request.setAttribute("user", user);
			request.setAttribute("mssg", "You are in Admin Page!");
			System.out.println("Redirecting to home page");
			dispatcher = request.getRequestDispatcher("admin.jsp");
		} else {
			request.setAttribute("mssg", "Invalid User ID or Password. Try Again!");
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}
	
	public static void populateData(User user, HttpServletRequest request) {
		List<Book> books;
		List<Book> mybooks = null;
		List<Bid> mybids;
		List<Category> categories;
		BookDao bookdao1 = new BookDao();
		UserDao userDao1 = new UserDao();
		
		Department dept = userDao1.getDepartmentDetails(user.getUserid());
		books = bookdao1.getAllBooks();
		mybids = bookdao1.getAllBids(user);
		categories = bookdao1.getCategory();
		
		
		if(books.size() > 0) {
			mybooks = new ArrayList<Book>();
		} else {
			System.out.println("mybooks not initialized: " + mybooks);
		}
		
		for(Book b : books) {
			if(b.getD_id().equals(dept.getDept_id())) {
				System.out.println("Adding: " + b);
				mybooks.add(b);
			}
		}
//		request.setAttribute("user", user);
		request.setAttribute("dept", dept);
		request.setAttribute("books", books);
		request.setAttribute("mybooks", mybooks);
		request.setAttribute("mybids", mybids);
		request.setAttribute("categories", categories);
	}

}
