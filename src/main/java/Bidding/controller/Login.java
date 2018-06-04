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
import Bidding.User;
import Bidding.dao.BookDao;
import Bidding.dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDao bookdao = new BookDao();
		UserDao userdao = new UserDao();
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
					request.setAttribute("mssg", "Welcome Back!");
					dispatcher = request.getRequestDispatcher("home.jsp");
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
		BookDao bookdao = new BookDao();
		UserDao userdao = new UserDao();
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
			request.setAttribute("mssg", "Welcome to UB Bidding!");
			System.out.println("Redirecting to home page");
			dispatcher = request.getRequestDispatcher("home.jsp");
		} else {
			request.setAttribute("mssg", "Invalid User ID or Password. Try Again!");
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}
	
	public static void populateData(User user, HttpServletRequest request) {
		List<Book> books, catalogue = new ArrayList<>();
		List<Book> mybooks = null;
		List<Bid> mybids;
		
		BookDao bookdao1 = new BookDao();
		
		books = bookdao1.getAllBooks();
		mybids = bookdao1.getAllBids(user);
		
		if(books.size() > 0) {
			mybooks = new ArrayList<Book>();
		} else {
			System.out.println("mybooks not initialized: " + mybooks);
		}
		
		for(Book b : books) {
			if(b.getU_id().equals(user.getUserid())) {
//				System.out.println("Adding: " + b);
				mybooks.add(b);
			}
			if(b.getStatus().equals("available")) {
				catalogue.add(b);
			}
		}
//		System.out.println("Books availble: " + catalogue);
//		request.setAttribute("user", user);
		request.setAttribute("books", catalogue);
		request.setAttribute("mybooks", mybooks);
		request.setAttribute("mybids", mybids);
	}

}
