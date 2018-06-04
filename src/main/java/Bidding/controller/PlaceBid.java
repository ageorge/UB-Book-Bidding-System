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
 * Servlet implementation class PlaceBid
 */
@WebServlet("/bid")
public class PlaceBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookdao = new BookDao();
	private UserDao userdao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceBid() {
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
		String bookid = request.getParameter("bookid");
		int price = Integer.parseInt(request.getParameter("price"));
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
		
		Bid bid = new Bid();
		bid.setBidder_id(user_id);
		bid.setBook_id(bookid);
		bid.setOffer_price(price);
		
		System.out.println("Bookid=" + bookid);
		System.out.println("userid=" + user_id);
		System.out.println("price=" + price);
		System.out.println("user=" + user.getName());
				
		int res = bookdao.placeBid(bid);
//		int res = 1;
		Login.populateData(user, request);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher;
		if(res > 0) {
			request.setAttribute("mssg", "You have successfully placed a bid!");
			dispatcher = request.getRequestDispatcher("home.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}

}
