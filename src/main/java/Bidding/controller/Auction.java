package Bidding.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bidding.Bid;
import Bidding.User;
import Bidding.dao.BookDao;
import Bidding.dao.UserDao;

/**
 * Servlet implementation class Auction
 */
@WebServlet("/auction")
public class Auction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userdao = new UserDao();
	private BookDao bookdao = new BookDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auction() {
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
				Login.populateData(user, request);
				request.setAttribute("user", user);
				request.setAttribute("mssg", "Welcome Back!");
				dispatcher = request.getRequestDispatcher("home.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("error.jsp");
			}
		}
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid_id = request.getParameter("bid_id");
		String user_id = request.getParameter("userid");
		
		User user;
		
		HttpSession session = request.getSession();
		if(session == null || user_id != null) {
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
		
		Login.populateData(user, request);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher;
		
		int res = bookdao.auction(Integer.parseInt(bid_id));
		
		if(res > 0) {
			request.setAttribute("mssg", "You have successfully auctioned a book!");
			dispatcher = request.getRequestDispatcher("home.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}

}
