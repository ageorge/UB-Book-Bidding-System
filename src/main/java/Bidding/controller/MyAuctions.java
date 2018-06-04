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

import Bidding.Auction_Transaction;
import Bidding.Book;
import Bidding.Feedback;
import Bidding.Message;
import Bidding.User;
import Bidding.dao.BookDao;
import Bidding.dao.UserDao;

/**
 * Servlet implementation class MyAuctions
 */
@WebServlet("/auctionwindow")
public class MyAuctions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	private BookDao bookdao = new BookDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAuctions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		request.setAttribute("mssg", "You are now redirected to a different site than UB Bidding System.");
		request.setAttribute("userid", userid);
		
		List<Auction_Transaction> alltrans = bookdao.getAuction(userid);
		List<Auction_Transaction> allauction = bookdao.getMyBookAuction(userid);
		
		
		request.setAttribute("alltrans", alltrans);
		request.setAttribute("allauctions", allauction);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("auction.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int auctionid = Integer.parseInt(request.getParameter("auctionid"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		String type = request.getParameter("type");
		String userid = request.getParameter("userid");
		
		Feedback feedback = new Feedback();
		feedback.setAuction_id(auctionid);
		feedback.setSeller_rate(rating);
		
		int res = -1;
		
		if(type.equals("seller"))
			res = bookdao.addFeedback(feedback);
		else 
			res = bookdao.addBuyerFeedback(feedback);
		
		List<Auction_Transaction> alltrans = bookdao.getAuction(userid);
		List<Auction_Transaction> allauction = bookdao.getMyBookAuction(userid);
		
		
		request.setAttribute("alltrans", alltrans);
		request.setAttribute("allauctions", allauction);

		request.setAttribute("mssg", "Feedback sent");

		RequestDispatcher dispatcher;
		if(res > 0) {
			dispatcher = request.getRequestDispatcher("auction.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}

}
