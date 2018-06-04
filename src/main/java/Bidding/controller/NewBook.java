package Bidding.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bidding.Auction_Type;
import Bidding.Book;
import Bidding.User;
import Bidding.dao.BookDao;
import Bidding.dao.UserDao;

/**
 * Servlet implementation class NewBook
 */
@WebServlet("/addbook")
public class NewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userdao = new UserDao();
	private BookDao bookdao = new BookDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewBook() {
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
		String bookid = request.getParameter("bookid");
		String user_id = request.getParameter("userid");
		String book_name = request.getParameter("bookname");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String status = "available";
		int iPrice = Integer.parseInt(request.getParameter("iPrice"));
		int oPrice = Integer.parseInt(request.getParameter("oPrice"));
		String book_condition = request.getParameter("condition");
		String dept_id = request.getParameter("department");
		String cat_id = request.getParameter("category");
		
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
		
		Book book = new Book();
		book.setBook_id(bookid);
		book.setAuthor(author);
		book.setBook_name(book_name);
		book.setCondition(book_condition);
		book.setIsbn(isbn);
		book.setStatus(status);
		book.setC_id(cat_id);
		book.setD_id(dept_id);
		book.setU_id(user_id);
		book.setiPrice(iPrice);
		book.setoPrice(oPrice);
		
		Auction_Type at = new Auction_Type();
		at.setBook_id(bookid);
		at.setCat_id(cat_id);
		at.setDue_date(null);
		at.setType_name();
		
		int res = bookdao.addNewBook(book, at);
//		int res = 1;
		Login.populateData(user, request);
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher;
		if(res > 0) {
			request.setAttribute("mssg", "New Book Added");
			dispatcher = request.getRequestDispatcher("home.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}

}
