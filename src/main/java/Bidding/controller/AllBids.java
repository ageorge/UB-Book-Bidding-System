package Bidding.controller;

import java.io.IOException;
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
 * Servlet implementation class AllBids
 */
@WebServlet("/allBids")
public class AllBids extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userdao = new UserDao();
	private BookDao bookdao = new BookDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllBids() {
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
		String user_id = request.getParameter("userid");
		String book_id = request.getParameter("bookid");
		User user;
		
		HttpSession session = request.getSession();
		if(session == null || user_id != null) {
			user = userdao.getUserbyID(user_id);
		} else {
			String user_name = (String) session.getAttribute("username");
			if(user_name == null) {
				user = userdao.getUserbyID(user_id);
				
			} else {
				user = userdao.getUserbyName(user_name);
			}
			session.setAttribute("username", user.getName());
		}
		
		Login.populateData(user, request);
		
		
		Book book = bookdao.getBook(book_id);
		List<Bid> mybookbids = bookdao.getMyBooksBids(user, book_id);
		
		request.setAttribute("mybookbids", mybookbids);
		request.setAttribute("user", user);
		
		if(!mybookbids.isEmpty()) {
			request.setAttribute("selbook", mybookbids.get(0).getBook_name());
			request.setAttribute("mssg", "Here are your bids!");
		} else if(!book.getU_id().equals(user_id))  {
			request.setAttribute("mssg", "You are not authorized to view the Bids on Book: " + book_id);
		} else {
			request.setAttribute("mssg", "You don't have any bids yet for the book: " + book.getBook_name());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
